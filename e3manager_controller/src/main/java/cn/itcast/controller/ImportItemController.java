package cn.itcast.controller;

import cn.itcast.pojo.E3Result;
import cn.itcast.search.service.ImportItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created with IntelliJ IDEA.
 * User: 徐冬虎
 * Date: 2018/3/13 0013
 * Time: 下午 10:47
 */
@Controller
public class ImportItemController {

    @Autowired
    ImportItem importItem;

    @RequestMapping("/index/item/import")
    @ResponseBody
    public E3Result importItem(){
        E3Result e3Result = importItem.importItem();
        return  e3Result;
    }}
