package cn.itcast.service;

import cn.itcast.pojo.DataGridResult;
import cn.itcast.pojo.E3Result;
import cn.itcast.pojo.TbItem;

public interface ItemService {

    TbItem findTbItemById (long id);

    DataGridResult findAll(int page, int rows);

    E3Result saveItem(TbItem tbItem,String desc);

}
