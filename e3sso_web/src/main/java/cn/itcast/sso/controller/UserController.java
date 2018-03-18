package cn.itcast.sso.controller;

import cn.itcast.pojo.E3Result;
import cn.itcast.pojo.TbUser;
import cn.itcast.sso.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Created with IntelliJ IDEA.
 * User: 徐冬虎
 * Date: 2018/3/17 0017
 * Time: 下午 4:24
 */
@Controller
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping("/sso/register")
    public String register(){
        return  "register";
    }

    @RequestMapping("/user/check/{param}/{type}")
    @ResponseBody
    public E3Result checkName(@PathVariable String param,@PathVariable int type){
        E3Result e3Result = userService.checkName(param, type);
        return e3Result;
    }
    @RequestMapping(value = "/user/register",method = RequestMethod.POST)
    @ResponseBody
    public E3Result checkName(TbUser tbUser){
        E3Result e3Result = userService.SaveTbuser(tbUser);
        return e3Result;
    }

}
