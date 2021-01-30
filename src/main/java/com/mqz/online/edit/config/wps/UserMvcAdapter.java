package com.mqz.online.edit.config.wps;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


/**
 * @author mqz
 * @since
 * @description mvn拦截器 针对某个请求url请求注册拦截器
 * @abount https://github.com/DemoMeng
 */
@Configuration
public class UserMvcAdapter implements WebMvcConfigurer {

    private static String[] URL_PATTERNS = new String[]{"/v1/**"};

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new UserHandlerAdapter()).addPathPatterns(URL_PATTERNS);
    }

}
