package com.sbm.admin.config;

import cn.dev33.satoken.interceptor.SaInterceptor;
import cn.dev33.satoken.stp.StpUtil;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;
import java.util.List;

/**
 * @Description
 * @Author guoxiaoyong
 * @Date 2023/6/17
 */
@Configuration
public class SaTokenConfigure implements WebMvcConfigurer {

    private List<String> excludePathPatterns = Arrays.asList(
            "/user/login",
            "/druid/*"
    );

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 注册 Sa-Token 拦截器，校验规则为 StpUtil.checkLogin() 登录校验。
        registry.addInterceptor(new SaInterceptor(handle -> StpUtil.checkLogin()))
                .addPathPatterns("/user/**")
                .excludePathPatterns(excludePathPatterns);
    }
}
