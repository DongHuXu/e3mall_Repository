package cn.itcast.service.impl;

import cn.itcast.mapper.TbItemDescMapper;
import cn.itcast.pojo.*;
import cn.itcast.mapper.TbItemMapper;
import cn.itcast.service.ItemService;
import cn.itcast.utils.IDUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    TbItemDescMapper tbItemDescMapper;
    @Autowired
    TbItemMapper tbItemMapper;
    @Override
    public TbItem findTbItemById(long id) {
        return tbItemMapper.selectByPrimaryKey(id);
    }

    @Override
    public DataGridResult findAll(int page ,int rows) {

        PageHelper.startPage(page,rows);
        TbItemExample example = new TbItemExample();
        List<TbItem> tbItems = tbItemMapper.selectByExample(example);
        DataGridResult dataGridResult = new DataGridResult();
        dataGridResult.setRows(tbItems);
        dataGridResult.setTotal((int) new PageInfo<>(tbItems).getTotal());
        return dataGridResult;
    }

    @Override
    public E3Result saveItem(TbItem tbItem, String desc) {
        //保存商品
        long itemId = IDUtils.genItemId();
        tbItem.setId(itemId);
        tbItem.setCreated(new Date());
        tbItem.setUpdated(new Date());
        //1正常 2 下架  3 删除
        tbItem.setStatus((byte) 1);
        tbItemMapper.insert(tbItem);
        //保存商品描述
        TbItemDesc tbItemDesc = new TbItemDesc();
        tbItemDesc.setItemId(itemId);
        tbItemDesc.setCreated(new Date());
        tbItemDesc.setItemDesc(desc);
        tbItemDesc.setUpdated(new Date());
        tbItemDescMapper.insert(tbItemDesc);
        return E3Result.ok();
    }

    @Override
    public TbItemDesc updateitem(Long id) {
        TbItemDesc tbItemDesc = tbItemDescMapper.selectByPrimaryKey(id);
        return tbItemDesc;
    }

    @Override
    public E3Result deleteItem(String ids,Byte status) {
        if (null!=ids&&ids.trim().length()>0){
            String[] split = ids.split(",");
            for (String s : split) {
                TbItemExample tbItemExample = new TbItemExample();
                TbItem tbItem = new TbItem();
                long itemId = Long.parseLong(s);
                tbItemExample.createCriteria().andIdEqualTo(itemId);
                tbItem.setStatus(status);
                tbItem.setUpdated(new Date());
                tbItemMapper.updateByExampleSelective(tbItem,tbItemExample);
            }

        }
        return E3Result.ok();
    }

}
