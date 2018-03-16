package cn.itcast.search.controller;

import cn.itcast.search.pojo.SearchResult;
import cn.itcast.search.service.SearchResultService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created with IntelliJ IDEA.
 * User: 徐冬虎
 * Date: 2018/3/14 0014
 * Time: 下午 3:26
 */
@Controller
public class SearchController {

    @Value("${search.item.rows}")
    int rows;
    @Autowired
    SearchResultService searchResultService;
    @RequestMapping("/search")
    public String search(String keyword, @RequestParam(defaultValue = "1")int page, Model model) throws Exception {
        int i = 1/0;
        if(StringUtils.isNotBlank(keyword)){
            String s = new String(keyword.getBytes("iso8859-1"), "utf-8");
            keyword=s;
        }
        SearchResult searchResult = searchResultService.searchItem(keyword, page, rows);
        model.addAttribute("query", keyword);
        model.addAttribute("page", page);
        //把 查询结果传递给jsp
        model.addAttribute("totalPages", searchResult.getTotalPages());
        model.addAttribute("recourdCount", searchResult.getRecourdCount());
        model.addAttribute("itemList", searchResult.getItems());
        return  "search";
    }
}
