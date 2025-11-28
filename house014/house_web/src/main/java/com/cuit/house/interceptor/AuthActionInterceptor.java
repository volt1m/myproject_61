package com.cuit.house.interceptor;

import com.cuit.house.pojo.User;
import com.cuit.house.utils.UserContext;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;

@Component
public class AuthActionInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //判断threadlocal有没有值
        User user = UserContext.getUser();
        if(user==null){
            String msg= URLEncoder.encode("请先登录","utf-8");
            String target = URLEncoder.encode(request.getRequestURL().toString(),"utf-8");
            response.sendRedirect("/accounts/signin?errorMsg="+msg+"&target="+target);
        }
        return true;
    }
}
