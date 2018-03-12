package cn.itcast.protal.service.impl;

import cn.itcast.jedis.JedisClient;
import cn.itcast.mapper.TbContentMapper;
import cn.itcast.pojo.DataGridResult;
import cn.itcast.pojo.E3Result;
import cn.itcast.pojo.TbContent;
import cn.itcast.pojo.TbContentExample;
import cn.itcast.protal.service.ContentService;
import cn.itcast.utils.JsonUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ContentServiceImpl implements ContentService {

    @Autowired
    TbContentMapper tbContentMapper;

    @Autowired
    JedisClient jedisClient;

    @Override
    public E3Result saveContent(TbContent tbContent) {
        tbContent.setCreated(new Date());
        tbContentMapper.insert(tbContent);
        return E3Result.ok();
    }

    @Override
    public DataGridResult findAllContent(long categoryId, int page, int rows) {
        PageHelper.startPage(page, rows);
        TbContentExample tbContentExample = new TbContentExample();
        TbContentExample.Criteria criteria = tbContentExample.createCriteria();
        criteria.andCategoryIdEqualTo(categoryId);
        List<TbContent> tbContents = tbContentMapper.selectByExample(tbContentExample);
        DataGridResult dataGridResult = new DataGridResult();
        PageInfo<TbContent> tbContentPageInfo = new PageInfo<>(tbContents);
        dataGridResult.setRows(tbContents);
        dataGridResult.setTotal((int) tbContentPageInfo.getTotal());
        return dataGridResult;
    }

    @Override
    public E3Result updateContent(TbContent tbContent) {
        tbContent.setUpdated(new Date());
        tbContentMapper.updateByPrimaryKeySelective(tbContent);
        try {
            jedisClient.hdel("content-info", "" + tbContent.getId());
        } catch (Exception e) {
            e.printStackTrace();

        }
        return E3Result.ok();
    }

    @Override
    public E3Result deleteContent(String ids) {
        String[] split = ids.split(",");
        for (String s : split) {
            long id = Long.parseLong(s);
            tbContentMapper.deleteByPrimaryKey(id);
            try {
                jedisClient.hdel("content-info", "" + id);

            } catch (Exception e) {
                e.printStackTrace();

            }
        }
        return E3Result.ok();
    }

    @Override
    public List<TbContent> findAll(long cid) {
        try {
            String s = jedisClient.hget("content-info", "" + cid);

            if (StringUtils.isNoneBlank()) {
                List<TbContent> tbContents = JsonUtils.jsonToList(s, TbContent.class);
                return tbContents;
            }
        } catch (Exception e) {

        }
        TbContentExample tbContentExample = new TbContentExample();
        TbContentExample.Criteria criteria = tbContentExample.createCriteria();
        criteria.andCategoryIdEqualTo(cid);
        List<TbContent> tbContents = tbContentMapper.selectByExampleWithBLOBs(tbContentExample);
        try {
            jedisClient.hset("content-info", "" + cid, JsonUtils.objectToJson(tbContents));
        } catch (Exception e) {
            e.printStackTrace();

        }
        return tbContents;
    }
}
