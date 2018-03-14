package cn.itcast.search.service;

import cn.itcast.search.pojo.SearchResult;

public interface SearchResultService {
    SearchResult searchItem(String keyWord, int page, int rows) throws Exception;
}
