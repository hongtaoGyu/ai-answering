package com.hongtao.base.baseFramwork.user.login;

import cn.dev33.satoken.interceptor.SaRouteInterceptor;
import cn.dev33.satoken.router.SaRouter;
import cn.dev33.satoken.stp.StpUtil;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MyWebMvcConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 注册路由拦截器，自定义验证规则
        registry.addInterceptor(new SaRouteInterceptor((request, response, handler) -> {
            // 根据路由划分模块，不同模块不同鉴权
            SaRouter.match("/**")    // 拦截的 path 列表，可以写多个 */
                    .notMatch("/sys/**")//系统服务全排除
                    // 下边的是knife4j使用的
                    .notMatch("/*.html")
                    .notMatch("/swagger-resources")
                    .notMatch("/webjars/**")
                    .notMatch("/**/api-docs")
                    // 排除登录接口
                    .notMatch("/user/login")
                    .notMatch("/user/register")
                    .check(r -> StpUtil.checkLogin());
        })).addPathPatterns("/**");
    }
}
