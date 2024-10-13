package com.hongtao.base.baseFramwork.user.login;

import cn.dev33.satoken.interceptor.SaAnnotationInterceptor;
import cn.dev33.satoken.interceptor.SaRouteInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


/**
 * SA-token全局拦截器
 */
@Configuration
public class SaTokenConfigure implements WebMvcConfigurer {
    // 注册sa-token的登录拦截器
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 注册登录拦截器，并排除登录接口地址
        registry.addInterceptor(new SaAnnotationInterceptor()).addPathPatterns("/**");
    }
}
