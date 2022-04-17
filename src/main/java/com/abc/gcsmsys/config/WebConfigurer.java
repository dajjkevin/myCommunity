package com.abc.gcsmsys.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Author surface
 * @Date 2022-1-24 21:52
 * @Description
 */
@Configuration
public class WebConfigurer implements WebMvcConfigurer {

    /**
     * addPathPatterns 拦截内容
     * excludePathPatterns 表示不进行拦截的内容
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor()).
                addPathPatterns("/**").
                excludePathPatterns("/xtheme/**").
                excludePathPatterns("/tologin").
                excludePathPatterns("/login").
                excludePathPatterns("/uploadImg/img").
                excludePathPatterns("/admin/forget","/create4Email/**","/admin/forgetPwd").
                excludePathPatterns("/lixa/**","/user/**").
                excludePathPatterns("/layui/**","/upload/**","/error");
    }

}
