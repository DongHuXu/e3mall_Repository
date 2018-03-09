package cn.itcast.controller;

import cn.itcast.pojo.DataGridResult;
import cn.itcast.pojo.E3Result;
import cn.itcast.pojo.TbContent;
import cn.itcast.protal.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ContentController {

    @Autowired
    ContentService contentService;

    /**
     * 保存分类商品
     * @param tbContent
     * @return
     */
    @RequestMapping(value = "/content/save",method = RequestMethod.POST)
    @ResponseBody
    public E3Result saveContent(TbContent tbContent){
        E3Result e3Result = contentService.saveContent(tbContent);
        return e3Result;
    }


    /**
     * 更新分类商品
     * @param tbContent
     * @return
     */
    @RequestMapping(value = "/rest/content/edit",method = RequestMethod.POST)
    @ResponseBody
    public E3Result updateContent(TbContent tbContent){
        E3Result e3Result = contentService.updateContent(tbContent);
        return e3Result;
    }

    /**
     * 删除分类商品
     * @param ids
     * @return
     */
    @RequestMapping("/content/delete")
    @ResponseBody
    public E3Result deleteContent(String ids){
        E3Result e3Result = contentService.deleteContent(ids);
        return e3Result;
    }

    /**
     * 展示该分类商品信息
     * @param categoryId
     * @param page
     * @param rows
     * @return
     */
    @RequestMapping(value = "/content/query/list")
    @ResponseBody
    public DataGridResult findAllContent(long categoryId, int page, int rows){
        DataGridResult dataGridResult =  contentService.findAllContent(categoryId,page,rows);
        return dataGridResult;
    }

}
