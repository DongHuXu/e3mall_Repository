package cn.itcast.service.impl;

import cn.itcast.pojo.CatNode;
import cn.itcast.mapper.TbItemCatMapper;
import cn.itcast.pojo.TbItemCat;
import cn.itcast.pojo.TbItemCatExample;
import cn.itcast.service.ItemCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ItemCatServiceImpl implements ItemCatService{
    @Autowired
    TbItemCatMapper tbItemCatMapper;
    @Override
    public List<CatNode> getItemCat(Long partentId) {
        TbItemCatExample example = new TbItemCatExample();
        example.createCriteria().andParentIdEqualTo(partentId);
        List<TbItemCat> lists = tbItemCatMapper.selectByExample(example);
        List<CatNode> catNodes = new ArrayList<>();
        for (TbItemCat list : lists) {
            CatNode catNode = new CatNode();
            catNode.setText(list.getName());
            catNode.setId(list.getId());
            catNode.setState(list.getIsParent()?"closed":"open");
            catNodes.add(catNode);
        }
        return catNodes;
    }
}
