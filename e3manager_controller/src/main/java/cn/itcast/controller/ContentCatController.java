package cn.itcast.controller;

import cn.itcast.pojo.CatNode;
import cn.itcast.pojo.E3Result;
import cn.itcast.protal.service.ContentCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class ContentCatController {

    @Autowired
    ContentCatService contentCatService;

    /**
     * 展示商品内容分录
     * @param partentId
     * @return
     */
    @RequestMapping("/content/category/list")
    @ResponseBody
    public  List<CatNode> findAllContentCat(@RequestParam(name ="id",defaultValue = "0")long partentId){

        List<CatNode> allContentCat = contentCatService.findAllContentCat(partentId);
        return  allContentCat;
    }

    /**
     * 增加商品分录
     * @param
     * @return
     */
    @RequestMapping("/content/category/create")
    @ResponseBody
    public E3Result addContentCat(Long parentId, String name){
        E3Result e3Result = contentCatService.createContentCat(parentId,name);
        return  e3Result;
    }

    /**
     * 重命名商品分录
     * @param id
     * @param name
     * @return
     */
    @RequestMapping("/content/category/update")
    @ResponseBody
    public E3Result updateContentCat(Long id, String name){
        E3Result e3Result = contentCatService.updateContentCat(id,name);
        return  e3Result;
    }

    /**
     * 删除商品分录
     * @param id
     * @return
     */
    @RequestMapping("/content/category/delete/")
    @ResponseBody
    public E3Result deleteContentCat(Long id){
        E3Result e3Result = contentCatService.deleteContentCat(id);
        return  e3Result;
    }

}
