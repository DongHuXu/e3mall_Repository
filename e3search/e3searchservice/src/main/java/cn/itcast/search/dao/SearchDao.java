package cn.itcast.search.dao;

import cn.itcast.search.pojo.SearchResult;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;

public interface SearchDao {
    SearchResult searchItem(SolrQuery solrQuery) throws SolrServerException;
}
