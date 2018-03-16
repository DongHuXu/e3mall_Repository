package cn.itcast.search.exception;

import cn.itcast.utils.MailUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created with IntelliJ IDEA.
 * User: 徐冬虎
 * Date: 2018/3/16 0016
 * Time: 下午 2:12
 */
public class GlobalException implements HandlerExceptionResolver {

    Logger logger = LoggerFactory.getLogger(GlobalException.class);
    @Override
    public ModelAndView resolveException
            (HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) {
        e.printStackTrace();
        logger.error("异常处理",e);
        MailUtils.sendMail("宜立方异常处理",e.getMessage(),"xiaoboy1202@163.com");
        ModelAndView view = new ModelAndView();
        view.addObject("message","系统发生异常,请您稍后再操作");
        view.setViewName("error/exception");
        return view;
    }
}
