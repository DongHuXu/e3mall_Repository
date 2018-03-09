package cn.itcast.protal.service;

import cn.itcast.pojo.CatNode;
import cn.itcast.pojo.E3Result;

import java.util.List;

public interface ContentCatService {
    List<CatNode> findAllContentCat(long partentId);
    E3Result createContentCat(long parentId, String name);
    E3Result updateContentCat(Long id, String name);
    E3Result deleteContentCat(Long id);

}
