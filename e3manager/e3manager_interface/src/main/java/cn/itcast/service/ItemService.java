package cn.itcast.service;

import cn.itcast.pojo.DataGridResult;
import cn.itcast.pojo.E3Result;
import cn.itcast.pojo.TbItem;
import cn.itcast.pojo.TbItemDesc;

public interface ItemService {

    TbItem findTbItemById (long id);
    TbItemDesc findItemDescById(long id);

    DataGridResult findAll(int page, int rows);

    E3Result saveItem(TbItem tbItem,String desc);

    TbItemDesc updateitem(Long id);

    E3Result updateitem(TbItem tbItem,String desc);

    E3Result deleteItem(String ids,Byte status);

}
