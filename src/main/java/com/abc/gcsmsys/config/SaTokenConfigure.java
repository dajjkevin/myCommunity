package com.abc.gcsmsys.config;

/**
 * @Description TODO
 * @Version 1.0
 * @Author kira
 * @Date 2021/11/30 23:08
 */


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 *
 * @author surface
 */
@Configuration
public class SaTokenConfigure implements WebMvcConfigurer {

    /**
     * swagger ui跨域问题
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**").addResourceLocations(
                "classpath:/static/");
        registry.addResourceHandler("doc.html").addResourceLocations(
                "classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations(
                "classpath:/META-INF/resources/webjars/");
    }


//    private CorsConfiguration corsConfig() {
//        CorsConfiguration corsConfiguration = new CorsConfiguration();
//        corsConfiguration.addAllowedOriginPattern("*");//1.设置访问源地址
//        corsConfiguration.addAllowedHeader("*");//2.设置请求头
//        corsConfiguration.addAllowedMethod("*");//3.设置访问源请求方法
//        corsConfiguration.setAllowCredentials(true);
//        corsConfiguration.setMaxAge(3600L);//设置时间
//        return corsConfiguration;
//    }
//    //↑↓跨域请求配置文件
//    @Bean
//    public CorsFilter corsFilter() {
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        source.registerCorsConfiguration("/**", corsConfig()); //4.对接口配置跨域设置
//        return new CorsFilter(source);
//    }





}

