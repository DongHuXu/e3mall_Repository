package cn.itcast.search.dao.impl;

import cn.itcast.search.dao.SearchDao;
import cn.itcast.search.pojo.Item;
import cn.itcast.search.pojo.SearchResult;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: 徐冬虎
 * Date: 2018/3/14 0014
 * Time: 下午 3:48
 */
@Repository
public class SearchDaoImpl implements SearchDao {

    @Autowired
    SolrServer solrServer;

    @Override
    public SearchResult searchItem(SolrQuery solrQuery) throws SolrServerException {
        QueryResponse query = solrServer.query(solrQuery);
        SolrDocumentList results = query.getResults();
        Map<String, Map<String, List<String>>> highlighting = query.getHighlighting();
        List<Item> lists = new ArrayList<>();
        for (SolrDocument result : results) {
            Item item = new Item();
            item.setId((String) result.get("id"));
            item.setCategory_name((String) result.get("item_category_name"));
            item.setImage((String) result.get("item_image"));
            item.setPrice((Long) result.get("item_price"));
            item.setSell_point((String) result.get("item_sell_point"));
            List<String> list = highlighting.get(result.get("id")).get("item_title");
            String title = "";
            if (list.size()>0&&null!=list){
                title=list.get(0);
            }else {
                title=(String) result.get("item_title");
            }
            item.setTitle(title);
            lists.add(item);
        }
        SearchResult searchResult = new SearchResult();
        searchResult.setItems(lists);
        searchResult.setRecourdCount((int) results.getNumFound());
        return searchResult;
    }
}
