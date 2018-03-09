package cn.itcast.protal.service.impl;

import cn.itcast.mapper.TbContentCategoryMapper;
import cn.itcast.pojo.CatNode;
import cn.itcast.pojo.E3Result;
import cn.itcast.pojo.TbContentCategory;
import cn.itcast.pojo.TbContentCategoryExample;
import cn.itcast.protal.service.ContentCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ContentCatServiceImpl implements ContentCatService {
    @Autowired
    TbContentCategoryMapper tbContentCategoryMapper;
    @Override
    public List<CatNode> findAllContentCat(long partentId) {
        TbContentCategoryExample tbContentCategoryExample = new TbContentCategoryExample();
        TbContentCategoryExample.Criteria criteria = tbContentCategoryExample.createCriteria();
        criteria.andParentIdEqualTo(partentId);
        criteria.andStatusEqualTo(1);
        List<TbContentCategory> tbContentCategories = tbContentCategoryMapper.selectByExample(tbContentCategoryExample);
        List<CatNode> list = new ArrayList<>();
        for (TbContentCategory tbContentCategory : tbContentCategories) {
            CatNode catNode = new CatNode();
            catNode.setText(tbContentCategory.getName());
            catNode.setState(tbContentCategory.getIsParent()?"closed":"open");
            catNode.setId(tbContentCategory.getId());
            list.add(catNode);
        }
        return list;
    }

    @Override
    public E3Result createContentCat(long parentId, String name) {
        TbContentCategory tbContentCategory = new TbContentCategory();
        tbContentCategory.setCreated(new Date());
        tbContentCategory.setUpdated(new Date());
        tbContentCategory.setIsParent(false);
        tbContentCategory.setName(name);
        tbContentCategory.setParentId(parentId);
        tbContentCategory.setSortOrder(1);
        tbContentCategory.setStatus(1);
        tbContentCategoryMapper.insert(tbContentCategory);
        TbContentCategory contentCategory = tbContentCategoryMapper.selectByPrimaryKey(parentId);
        if(!contentCategory.getIsParent()){
            contentCategory.setIsParent(true);
            tbContentCategoryMapper.updateByPrimaryKey(contentCategory);
        }
        return E3Result.ok(tbContentCategory);
    }

    @Override
    public E3Result updateContentCat(Long id, String name) {
        TbContentCategory tbContentCategory = new TbContentCategory();
        tbContentCategory.setId(id);
        tbContentCategory.setName(name);
        tbContentCategory.setUpdated(new Date());
        tbContentCategoryMapper.updateByPrimaryKeySelective(tbContentCategory);
        return new E3Result();
    }

    @Override
    public E3Result deleteContentCat(Long id) {
        TbContentCategory tbContentCategory = tbContentCategoryMapper.selectByPrimaryKey(id);
        Long parentId = tbContentCategory.getParentId();
        if(tbContentCategory.getIsParent()){
            return  E3Result.build(404,"该商品分录不可删");
        }
        //2删除  1正常
        tbContentCategory.setStatus(2);
        tbContentCategory.setUpdated(new Date());
        tbContentCategoryMapper.updateByPrimaryKeySelective(tbContentCategory);
        //判断该父节点是否有其他子节点
        TbContentCategoryExample tbContentCategoryExample = new TbContentCategoryExample();
        TbContentCategoryExample.Criteria criteria = tbContentCategoryExample.createCriteria();
        criteria.andParentIdEqualTo(parentId);
        List<TbContentCategory> tbContentCategories = tbContentCategoryMapper.selectByExample(tbContentCategoryExample);
        if(tbContentCategories.size()==0){
            TbContentCategory tbContentCategory1 = tbContentCategoryMapper.selectByPrimaryKey(parentId);
            tbContentCategory1.setIsParent(false);
            tbContentCategoryMapper.updateByPrimaryKeySelective(tbContentCategory1);
        }
        return E3Result.ok();
    }
}
