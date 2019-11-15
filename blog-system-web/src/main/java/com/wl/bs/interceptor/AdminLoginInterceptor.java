package com.wl.bs.interceptor;

import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @description: 后台系统身份验证拦截器
 * @author: wanlin
 * @version: 1.0
 * @createtime: 2019/9/28 14:27
 */
@Component
public class AdminLoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String uri = request.getRequestURI();
        if (uri.startsWith("/admin") && StringUtils.isEmpty(request.getSession().getAttribute("loginUserId"))) {
            request.getSession().setAttribute("errorMsg", "请重新登录");
            response.sendRedirect(request.getContextPath() + "/admin/v1/login");
            return false;
        }
        request.getSession().removeAttribute("errorMsg");
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {

    }
}
