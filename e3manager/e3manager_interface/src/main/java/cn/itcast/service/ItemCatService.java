package cn.itcast.service;

import cn.itcast.pojo.CatNode;

import java.util.List;

public interface ItemCatService {
    List<CatNode> getItemCat(Long partentId);
}
