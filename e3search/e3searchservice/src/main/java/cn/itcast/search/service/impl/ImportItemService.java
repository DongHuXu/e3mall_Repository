package cn.itcast.search.service.impl;

import cn.itcast.pojo.E3Result;
import cn.itcast.search.mapper.ItemMapper;
import cn.itcast.search.pojo.Item;
import cn.itcast.search.service.ImportItem;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: 徐冬虎
 * Date: 2018/3/13 0013
 * Time: 下午 10:30
 */
@Service
public class ImportItemService implements ImportItem {

    @Autowired
    ItemMapper itemMapper;

    @Autowired
    SolrServer solrServer;

    @Override
    public E3Result importItem() {
        try {
            List<Item> items = itemMapper.importItem();
            for (Item item : items) {
                SolrInputDocument document = new SolrInputDocument();
                document.addField("id",item.getId());
                document.addField("item_title", item.getTitle());
                document.addField("item_sell_point", item.getSell_point());
                document.addField("item_price", item.getPrice());
                document.addField("item_image", item.getImage());
                document.addField("item_category_name", item.getCategory_name());
                solrServer.add(document);
            }
            solrServer.commit();
            return E3Result.ok();
        }catch (Exception e){
            return E3Result.build(500,"导入索引库失败");
        }

    }
}
