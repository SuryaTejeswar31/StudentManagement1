package org.example.studentmanagementsecure.config;

import org.example.studentmanagementsecure.interceptors.AuthorizationInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    AuthorizationInterceptor interceptor;
    public WebConfig(AuthorizationInterceptor interceptor) {
        this.interceptor = interceptor;
    }
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(interceptor).addPathPatterns("/api/student/**");
    }
}
