package cn.itcast.item.controller;

import cn.itcast.item.pojo.Item;
import cn.itcast.pojo.TbItem;
import cn.itcast.pojo.TbItemDesc;
import cn.itcast.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created with IntelliJ IDEA.
 * User: 徐冬虎
 * Date: 2018/3/17 0017
 * Time: 下午 1:24
 */
@Controller
public class ItemController {

    @Autowired
    ItemService itemService;

    @RequestMapping("/item/{id}")
    public String  getItem(@PathVariable("id") long id, Model model){
        TbItem tbItem = itemService.findTbItemById(id);
        Item item = new Item(tbItem);
        TbItemDesc itemDesc = itemService.findItemDescById(id);
        model.addAttribute("item",item);
        model.addAttribute("itemDesc",itemDesc);
        return "item";
    }
}
