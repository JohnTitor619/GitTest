package cn.yunhe.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GlobalExceptionReslover  implements HandlerExceptionResolver {
    private Logger logger = LoggerFactory.getLogger(GlobalExceptionReslover.class);

    @Override
    public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) {
        e.printStackTrace();
        logger.error("系统出现异常",e);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("message","系统出现异常，请与管理员联系!");
        modelAndView.addObject("stack",e);
        modelAndView.setViewName("error");
        return modelAndView;
    }
}
