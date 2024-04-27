package org.example.bstest.demos.web.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;

public class GlobalInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //打印请求信息
        printMsg(request);
        // 校验登录状态
        boolean isLoggedIn = checkLoginStatus(request);
        if (!isLoggedIn) {
            // 如果未登录，重定向到登录页面或返回错误响应
            response.sendRedirect(request.getContextPath() + "/login");
            return false; // 拦截器返回false，请求处理结束
        }
        return true; // 继续处理请求
    }

    private boolean checkLoginStatus(HttpServletRequest request) {
        // 实现登录状态检查逻辑,举例：检查session中是否有用户信息

        return request.getSession().getAttribute("user") != null;
    }

//    @Override
//    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
//        // 请求处理之后，视图渲染之前的回调
//    }
//
//    @Override
//    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
//        // 整个请求处理完毕之后的回调
//    }


    private void printMsg(HttpServletRequest request){
        // 打印请求方法
        System.out.println("Request Method: " + request.getMethod());

        // 打印请求URL
        System.out.println("Request URL: " + request.getRequestURL());

        // 打印请求头
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            System.out.println("Header Name: " + headerName + ", Value: " + request.getHeader(headerName));
        }

        // 打印请求参数（如果是GET请求或表单POST请求）
        Enumeration<String> paramNames = request.getParameterNames();
        while (paramNames.hasMoreElements()) {
            String paramName = paramNames.nextElement();
            System.out.println("Parameter Name: " + paramName + ", Value: " + request.getParameter(paramName));
        }
    }
}