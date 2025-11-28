package com.cuit.house.interceptor;


import com.cuit.house.constrants.CommonConstants;
import com.cuit.house.pojo.User;
import com.cuit.house.utils.UserContext;
import com.google.common.base.Joiner;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Map;

@Component
public class AuthInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Map<String, String[]> map = request.getParameterMap();
        map.forEach((k,v)->{
            if(k.equals("errorMsg")||k.equals("successMsg")||k.equals("target")){
                request.setAttribute(k, Joiner.on(",").join(v));
            }
        });

        String requestURI=request.getRequestURI();
        if(requestURI.startsWith("/static")||requestURI.startsWith("/error")){
            return true;
        }
        HttpSession session=request.getSession();
        User user=(User)session.getAttribute(CommonConstants.USER_ATTRIBUTE);
        if(user!=null){
            UserContext.setUser(user);
        }
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        UserContext.remove();
    }
}