package cn.itcast.protal.service;

import cn.itcast.pojo.DataGridResult;
import cn.itcast.pojo.E3Result;
import cn.itcast.pojo.TbContent;

public interface ContentService {

    E3Result saveContent(TbContent tbContent);
    DataGridResult findAllContent(long categoryId, int page, int rows);
    E3Result updateContent(TbContent tbContent);
    E3Result deleteContent(String ids);
}
