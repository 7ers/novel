package com.demo.novel.shiro;

import com.demo.novel.util.Constants;
import com.demo.novel.util.JsonResult;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.tomcat.util.ExceptionUtils;
import org.mybatis.logging.Logger;
import org.mybatis.logging.LoggerFactory;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

public class GlobalExceptionResolver implements HandlerExceptionResolver {

    private static Logger logger = LoggerFactory.getLogger(GlobalExceptionResolver.class);

    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        ModelAndView mv;
        //进行异常判断。如果捕获异常请求跳转。
        if(ex instanceof UnauthorizedException){
            mv = new ModelAndView("/403");
            return mv;
        }else {
            mv = new ModelAndView(new MappingJackson2JsonView());
            JsonResult jr = new JsonResult();
            jr.setCode(Constants.RET_CODE_ERROR);
            jr.setMsg("服务器异常");
            mv.addObject(jr);
            return mv;

        }
    }
}
