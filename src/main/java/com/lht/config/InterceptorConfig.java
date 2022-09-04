//package com.lht.config;
//
//import com.lht.interceptor.LoginInterceptor;
//import org.jetbrains.annotations.NotNull;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
///**
// *  自定义拦截器的配置类
// * @author: 21
// * @date: 2022/9/2.
// */
//@Configuration
//public class InterceptorConfig implements WebMvcConfigurer {
//
//    /**
//     *
//     * @param registry
//     */
//    @Override
//    public void addInterceptors(@NotNull InterceptorRegistry registry) {
//        registry.addInterceptor(new LoginInterceptor())
//                //添加需要拦截的路径
//                .addPathPatterns("/**")
//                //添加需要放行的路径
//                .excludePathPatterns(
//                        "/*",
//                        "/employee/login",
//                        "/backend/api/login.js",
//                        "/backend/images/**",
//                        "/backend/js/*.js",
//                        "/favicon.ico",
//                        "/backend/favicon.ico",
//                        "/backend/page/login/login.html",
//                        "/backend/styles/*.css",
//                        "/backend/plugins/**",
//                        "/backend/styles/fonts/element-icons.woff",
//                        "/front/images/**",
//                        "/front/fonts/**",
//                        "/front/js/*.js",
//                        "/front/page/login.html",
//                        "/backend/styles/icon/iconfont.css",
//                        "/front/styles/**",
//
//                        "/styles/icon/iconfont.css",
//
//                        "/backend/page/login/login.html",
//                        "/styles/*.css",
//                        "/styles/common.css",
//                        "styles/icon/iconfont.css",
//                        "/styles/login.css",
//
//                        "/plugins/element-ui/*.css",
//
//                        "/plugins/vue/*.js",
//                        "/plugins/element-ui/*.js",
//                        "plugins/axios/*.js",
//                        "/plugins/vue/vue.js",
//                        "/plugins/element-ui/index.js",
//                        "/plugins/axios/axios.min.js",
//
//                        "/js/*.js",
//                        "/js/request.js",
//                        "/js/validate.js",
//
//                        "/api/*.js",
//                        "/api/login.js",
//
//                        "/images/login/*.png",
//                        "/images/login/login-l.png",
//                        "/images/login/logo.png"
//
//                );
//    }
//}
