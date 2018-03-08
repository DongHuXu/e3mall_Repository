package cn.itcast.controller;

import cn.itcast.pojo.CatNode;
import cn.itcast.service.ItemCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class ItemCatController  {

    @Autowired
    ItemCatService itemCatService;

    @RequestMapping("/item/cat/list")
    @ResponseBody
    public List<CatNode> getAllCatNode(@RequestParam(name = "id",defaultValue = "0") Long partentId){
        List<CatNode> itemCat = itemCatService.getItemCat(partentId);
        return itemCat;
    }
}
