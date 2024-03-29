package com.abc.gcsmsys.config;

import com.abc.gcsmsys.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @Author surface
 * @Date 2022-1-24 21:47
 * @Description 登录拦截器
 */
@Slf4j
@Component
public class LoginInterceptor implements HandlerInterceptor {

    /**预处理回调方法，实现处理器的预处理(是否登录检查)*/
    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {

        Object admin = request.getSession().getAttribute("adminInfo");

        Object user = request.getSession().getAttribute("userFrontDesk");

        log.info(request.getRequestURI().toString());

        if (request.getRequestURI().toString().contains("/user")){
            return true;
        }else if (admin == null || admin.equals(""))  {
            response.sendRedirect("/tologin");
            log.info("请先登录后台操作！！");
            return false;
        }

        return true;
    }

    /**后处理回调方法，实现处理器的后处理(但在渲染视图之前)，
     此时我们可以通过modelAndView (模型和视图对象)对模型数据进行处理或对视图进行处理，mode lAndView也可能为null。*/
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        log.info("postHandle...");
    }

    /**整个请求处理完毕回调方法.
     即在视图渲染完毕时回调，如性能监控中我们可以在此记录结束时间并输出消耗时间.
     还可以进行一些资源清理，类似于try-catch- finally中的finally,但仅调用处理器执行链中*/
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        log.info("afterCompletion...");
    }
}
