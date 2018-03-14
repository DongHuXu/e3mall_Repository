package cn.itcast.search.service.impl;

import cn.itcast.search.dao.SearchDao;
import cn.itcast.search.pojo.SearchResult;
import cn.itcast.search.service.SearchResultService;
import com.sun.tools.classfile.AnnotationDefault_attribute;
import org.apache.solr.client.solrj.SolrQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 * User: 徐冬虎
 * Date: 2018/3/14 0014
 * Time: 下午 4:17
 */
@Service
public class SearchResultServiceImpl implements SearchResultService {

    @Autowired
    SearchDao searchDao;

    @Override
    public SearchResult searchItem(String keyWord, int page, int rows) throws Exception {
        SolrQuery query = new SolrQuery();
        query.setQuery(keyWord);
        query.setStart((page-1)*rows);
        query.setRows(rows);
        query.set("df","item_keywords");
        query.setHighlight(true);
        query.addHighlightField("item_title");
        query.setHighlightSimplePre("<em style=\"color:red\">");
        query.setHighlightSimplePost("</em>");
        SearchResult searchResult = searchDao.searchItem(query);
        //总行数
        int recourdCount = searchResult.getRecourdCount();
        int  pageCount = (int) Math.ceil(recourdCount / (rows*1.0));
        /*int pageCount=  recourdCount/rows;
        if (recourdCount%rows>0){
            pageCount++;
        }*/
        searchResult.setTotalPages(pageCount);
        return searchResult;
    }
}
