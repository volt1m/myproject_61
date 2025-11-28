package com.cuit.house.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConf implements WebMvcConfigurer {
    @Autowired
    private AuthInterceptor authInterceptor;
    @Autowired
    private AuthActionInterceptor authActionInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //先配置第一个拦截器
        registry.addInterceptor(authInterceptor)
                .excludePathPatterns("/static").addPathPatterns("/**");
        //配置第二个指定拦截器
        registry.addInterceptor(authActionInterceptor).
                addPathPatterns("/accounts/profile").addPathPatterns("/house/toAdd");
    }
}
