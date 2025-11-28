package com.cuit.house.utils;

import com.cuit.house.pojo.User;
import com.cuit.house.result.ResultMsg;
import org.apache.commons.lang3.StringUtils;

public class UserHelper {
    public static ResultMsg validatae(User account){
        if(StringUtils.isBlank(account.getEmail())){
            return ResultMsg.errorMsg("email 有误");
        }
        if(StringUtils.isBlank(account.getName())){
            return ResultMsg.errorMsg("name 有误");
        }
        if(StringUtils.isBlank(account.getConfirmPasswd())
        ||StringUtils.isBlank(account.getPasswd())
        ||!account.getPasswd().equals(account.getConfirmPasswd())
        ){
            return ResultMsg.errorMsg("两次密码不一致");
        }
        if(account.getPasswd().length()<6){
            return ResultMsg.errorMsg("密码大于6位");
        }
        return ResultMsg.successMsg("");
    }
}
