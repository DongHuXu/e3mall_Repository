package cn.itcast.sso.controller;

import cn.itcast.pojo.E3Result;
import cn.itcast.pojo.TbUser;
import cn.itcast.sso.service.UserService;
import cn.itcast.utils.CookieUtils;
import cn.itcast.utils.JsonUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

    /**
     * 注册页面跳转
     *
     * @return
     */
    @RequestMapping("/page/register")
    public String register() {
        return "register";
    }

    /**
     * 登录页面跳转
     *
     * @return
     */
    @RequestMapping("/page/login")
    public String login() {
        return "login";
    }

    /**
     * 校验用户名,手机,邮箱是否可用
     *
     * @param param
     * @param type
     * @return
     */
    @RequestMapping("/user/check/{param}/{type}")
    @ResponseBody
    public E3Result checkName(@PathVariable String param, @PathVariable int type) {
        E3Result e3Result = userService.checkName(param, type);
        return e3Result;
    }

    /**
     * 用户注册
     *
     * @param tbUser
     * @return
     */
    @RequestMapping(value = "/user/register", method = RequestMethod.POST)
    @ResponseBody
    public E3Result checkName(TbUser tbUser) {
        E3Result e3Result = userService.SaveTbuser(tbUser);
        return e3Result;
    }

    /**
     * 用户登录
     *
     * @param username
     * @param password
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/user/login", method = RequestMethod.POST)
    @ResponseBody
    public E3Result login(String username, String password, HttpServletRequest request, HttpServletResponse response) {
        E3Result e3Result = userService.login(username, password);
        if (e3Result.getStatus() == 200) {
            CookieUtils.setCookie(request, response, "token", e3Result.getData().toString());
        }
        return e3Result;
    }

    /**
     * 响应用户信息
     * @param token
     * @param response
     * @return
     */
    //    @RequestMapping("/user/token/{token}")
//    @ResponseBody
//    public String getUser(@PathVariable String token,String callback){
//        E3Result e3Result = userService.getUser(token);
//        if (StringUtils.isNotBlank(callback)){
//            return callback+"("+ JsonUtils.objectToJson(e3Result)+");";
//        }
//        return JsonUtils.objectToJson(e3Result);
//    }
    @RequestMapping("/user/token/{token}")
    @ResponseBody
    public E3Result getUser(@PathVariable String token, HttpServletResponse response) {
        E3Result e3Result = userService.getUser(token);
        response.setHeader("Access-Control-Allow-Origin", "*");
        return e3Result;
    }
}
