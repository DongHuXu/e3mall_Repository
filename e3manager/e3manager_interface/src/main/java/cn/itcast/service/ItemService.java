package cn.itcast.service;

import cn.itcast.DataGridResult;
import cn.itcast.pojo.TbItem;

public interface ItemService {

    TbItem findTbItemById (long id);

    DataGridResult findAll(int page, int rows);
}
