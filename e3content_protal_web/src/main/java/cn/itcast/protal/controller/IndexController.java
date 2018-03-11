package cn.itcast.protal.controller;

import cn.itcast.pojo.TbContent;
import cn.itcast.protal.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class IndexController {

    @Value("${content.category.id1}")
    private Long content_categoryId;
    @Autowired
    ContentService contentService;

    @RequestMapping("/index")
    public String showIndex(Model model){
        List<TbContent> list = contentService.findAll(content_categoryId);
        System.out.println(list.size());
        for (TbContent tbContent : list) {
            System.out.println(tbContent.getPic());
        }
       model.addAttribute("ad1List",list);
        return "index";
    }}
