package cn.itcast.controller;

import cn.itcast.pojo.DataGridResult;
import cn.itcast.pojo.E3Result;
import cn.itcast.pojo.TbItem;
import cn.itcast.pojo.TbItemDesc;
import cn.itcast.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ItemController {

    @Autowired
    ItemService itemService;

    /**
     * 根据商品id查询商品明细
     * @param itemId
     * @return
     */
    @RequestMapping("/item/{itemId}")
    @ResponseBody
    public TbItem getItemById(@PathVariable long itemId){
        return itemService.findTbItemById(itemId);
    }

    /**
     * 商品列表展示
     * @param page
     * @param rows
     * @return
     *
     */
    @RequestMapping("/item/list")
    @ResponseBody
    public DataGridResult findAll(int page , int rows){

        DataGridResult dataGridResult = itemService.findAll(page, rows);
        return dataGridResult;
    }

    /**
     * 保存商品
     * @param item
     * @param desc
     * @return
     */
    @RequestMapping("/item/save")
    @ResponseBody
    public E3Result saveItem(TbItem item,String desc){
        E3Result e3Result = itemService.saveItem(item, desc);
        return  e3Result;
    }

    /**
     * 更新商品
     * @param item
     * @param desc
     * @return
     */
    @RequestMapping("/rest/item/update")
    @ResponseBody
    public E3Result updateItem(TbItem item,String desc){
        E3Result e3Result = itemService.updateitem(item,desc);
        return  e3Result;
    }

    /**
     * 商品描述回显
     * @param id
     * @return
     * /rest/item/query/item/desc/152052149514427
     */

    @RequestMapping("/rest/item/query/item/desc/{id}")
    @ResponseBody
    public E3Result findItem(@PathVariable long id){
        TbItemDesc tbItemDesc = itemService.updateitem(id);
        return  E3Result.ok(tbItemDesc);
    }

    private  Byte status;

    /**
     * 删除
     * @param ids
     * @return
     */
    @RequestMapping("/rest/item/delete")
    @ResponseBody
    public E3Result DeleteItem(String ids){
        status=3;
        E3Result e3Result = itemService.deleteItem(ids,status);
        return  e3Result;
    }

    /**
     * 下架
     * @param ids
     * @return
     */
    @RequestMapping("/rest/item/instock")
    @ResponseBody
    public E3Result UpdateItemByInstock(String ids){
        status=2;
        E3Result e3Result = itemService.deleteItem(ids,status);
        return  e3Result;
    }

    /**
     * 上架
     * @param ids
     * @return
     */
    @RequestMapping("/rest/item/reshelf")
    @ResponseBody
    public E3Result UpdateItemByReshelf(String ids){
        status=1;
        E3Result e3Result = itemService.deleteItem(ids,status);
        return  e3Result;
    }
}
