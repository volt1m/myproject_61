package com.cuit.house.controller;

import com.cuit.house.constrants.CommonConstants;
import com.cuit.house.pojo.User;
import com.cuit.house.result.ResultMsg;
import com.cuit.house.service.UserService;
import com.cuit.house.utils.UserHelper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.xml.transform.Result;
import java.util.List;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("user")
    @ResponseBody
    public List<User> getUsers(){
        return userService.getUsers();
    }

    @RequestMapping("/accounts/register")
    public String accountRegister(User account, ModelMap modelMap){
        //如果前端传过来的数据为空，跳转到注册页面
        if(account==null||account.getName()==null){
            return "user/accounts/register";
        }
        //不为空就是点击注册
        //用户验证
        ResultMsg resultMsg = UserHelper.validatae(account);
        if(resultMsg.isSuccess()&& userService.addAcount(account)){
            modelMap.put("email",account.getEmail());
            return "user/accounts/registerSubmit";
        }else {
            return "redirect:/accounts/register?"+resultMsg.asUrlParams();
        }
    }
    //激活
    @RequestMapping("/accounts/verify")
    public String verify(String key) {
        boolean result = userService.enable(key);
        if(result){
            return "redirect:/index?"+ ResultMsg.successMsg("激活成功").asUrlParams();
        }else {
            return "redirect:/accounts/register?"+ResultMsg.errorMsg("激活失败，请确认链接是否过期");
        }
    }

    //登录
    @RequestMapping("accounts/signin")
    public String signin(HttpServletRequest request) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String target = request.getParameter("target");
        if (username == null || password == null) {
            request.setAttribute("target", target);
            return "user/accounts/signin";
        }
        //点击登录按钮
        User user = userService.auth(username, password);
        if (user == null) {
            return "redirect:/accounts/signin?" + "target=" + target +
                    "&username=" + username + "&" + ResultMsg.errorMsg("用户名或密码错误").asUrlParams();
        } else {
            HttpSession session = request.getSession();
            session.setAttribute(CommonConstants.USER_ATTRIBUTE, user);
            session.setAttribute(CommonConstants.PLAIN_USER_ATTRIBUTE, user);
            //重定向到目标页面，没有就回首页
            return StringUtils.isNoneBlank(target) ? "redirect:" + target : "redirect:/index";
        }
    }
    //退出登录
    @RequestMapping("accounts/logout")
        public String logout(HttpServletRequest request){
            HttpSession session = request.getSession();
            session.invalidate();
            return "redirect:/index";
        }

        //进入个人中心
    @RequestMapping("/accounts/profile")
    public String profie(HttpServletRequest req,User updateUser){
        if(updateUser.getEmail()==null){
            return "user/accounts/profile";
        }
        return null;
    }
}
