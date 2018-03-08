package cn.itcast.service;

import cn.itcast.CatNode;

import java.util.List;

public interface ItemCatService {
    List<CatNode> getItemCat(Long partentId);
}
