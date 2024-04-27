package org.example.bstest.demos.web.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//用于解决同源策略问题
@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // 允许所有路径的跨域请求
                .allowedOrigins("localhost:9526", "http://localhost:9526") // 允许跨域的域名或IP，可以用*表示允许所有
                .allowedMethods("POST", "GET", "PUT", "OPTIONS", "DELETE") // 允许的请求方法
                .allowedHeaders("*") // 预检请求中允许的请求头
                .maxAge(168000) // 预检请求的有效期，单位为秒
                .allowCredentials(true);// 是否允许携带凭证（cookies, HTTP认证及客户端SSL证明等）

    }
}