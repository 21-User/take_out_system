//package com.lht.interceptor;
//
//import com.alibaba.fastjson.JSON;
//import com.lht.pojo.vo.Result;
//import lombok.extern.slf4j.Slf4j;
//import org.jetbrains.annotations.NotNull;
//import org.springframework.util.AntPathMatcher;
//import org.springframework.web.servlet.HandlerInterceptor;
//import org.springframework.web.servlet.ModelAndView;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
///**
// * 自定义登录拦截器
// * @author: 21
// * @date: 2022/9/2
// */
//@Slf4j
//public class LoginInterceptor implements HandlerInterceptor {
//
//    /**
//     * 请求路径的匹配器
//     */
//    public static final AntPathMatcher ANT_PATH_MATCHER = new AntPathMatcher();
//    /**
//     * 控制器执行之前执行
//     * @param request
//     * @param response
//     * @param handler
//     * @return 返回值为false代表被拦截了，如果是true说明放行
//     * @throws Exception
//     */
//    @Override
//    public boolean preHandle(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response, @NotNull Object handler) throws Exception {
//
//        //获取请求的路径
//        String requestUri = request.getRequestURI();
//
//        log.info("拦截到的请求：{}",requestUri);
//
//        //获取域对象中的信息
//        Object employee = request.getSession().getAttribute("employee");
//
//        LoginInterceptor loginInterceptor = new LoginInterceptor();
//
//
//        //如何域对象中有employee直接放行
//        if (employee != null) {
//            log.info("用户已登录，当前用户id为：{}", employee);
//
//            return true;
//        } else {
//
//            log.info("用户未登录！");
//
//            response.getWriter().write(JSON.toJSONString(Result.error("NOTLOGIN")));
//
//
//            return false;
//        }
//
//    }
//
//    /**
//     *控制器执行之后执行
//     * @param request
//     * @param response
//     * @param handler
//     * @param modelAndView
//     * @throws Exception
//     */
//    @Override
//    public void postHandle(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response, @NotNull Object handler, ModelAndView modelAndView) throws Exception {
//        log.info("控制器已执行：postHandle----------");
//
//    }
//
//    /**
//     * 页面渲染之后，常用于资源清理的操作
//     * @param request
//     * @param response
//     * @param handler
//     * @param ex
//     * @throws Exception
//     */
//    @Override
//    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
//
//    }
//}
