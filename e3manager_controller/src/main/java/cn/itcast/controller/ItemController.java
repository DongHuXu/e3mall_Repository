package cn.itcast.controller;

import cn.itcast.DataGridResult;
import cn.itcast.pojo.TbItem;
import cn.itcast.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ItemController {

    @Autowired
    ItemService itemService;

    @RequestMapping("/item/{itemId}")
    @ResponseBody
    public TbItem getItemById(@PathVariable long itemId){
        return itemService.findTbItemById(itemId);
    }

    @RequestMapping("/item/list")
    @ResponseBody
    public DataGridResult findAll(int page , int rows){

        DataGridResult dataGridResult = itemService.findAll(page, rows);
        return dataGridResult;
    }

}
