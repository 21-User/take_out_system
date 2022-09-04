package com.lht.interceptor;

import com.alibaba.fastjson.JSON;
import com.lht.pojo.vo.Result;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.util.AntPathMatcher;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URL;
import java.security.PublicKey;

/**
 * 自定义登录过滤器
 * @author: 21
 * @date: 2022/9/3
 */
@Slf4j
@WebFilter(filterName = "loginFilter", urlPatterns = "/*")
public class LoginFilter implements Filter {

    /**
     * 请求路径的匹配器
     */
    public static final AntPathMatcher ANT_PATH_MATCHER = new AntPathMatcher();

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse rsp = (HttpServletResponse) servletResponse;

        //获取请求的url
        String requestUri = req.getRequestURI();

        log.info("拦截到的请求：{}", requestUri);

        //需要放行的url
        String[] urls = new String[]{
                "/employee/login",
                "/employee/logout",
                "/backend/**",
                "/front/**"
        };

        //校验请求的路径是否放行
        boolean check = check(urls, requestUri);


        //需要放行的路径和请求的路径相匹配直接放行
        if (check) {
             log.info("本次请求放行路径{}", requestUri);
             filterChain.doFilter(req, rsp);
             return;
        }

        if (req.getSession().getAttribute("employee") != null) {
            log.info("用户登录的id{}", req.getSession().getAttribute("employee"));
            filterChain.doFilter(req, rsp);
            return;
        }

        log.info("用户未登录");
        rsp.getWriter().write(JSON.toJSONString(Result.error("NOTLOGIN")));
        return;

    }

    /**
     * 校验是否和需要放行的路径相匹配
     * @param urls
     * @param requestUri
     * @return
     */
    public boolean check(String[] urls, String requestUri) {

        //遍历需要放行的路径
        for (String url : urls) {
            boolean match = ANT_PATH_MATCHER.match(url, requestUri);
            //判断需要放行的路径是否和请求的路径相匹配
            if (match) {
                return true;
            }
        }

        return false;
    }
}
