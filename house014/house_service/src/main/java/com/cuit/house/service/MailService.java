package com.cuit.house.service;

import com.cuit.house.mapper.UserMapper;
import com.cuit.house.pojo.User;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.RemovalListener;
import com.google.common.cache.RemovalNotification;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
@Service
public class MailService {
    private static UserMapper userMapper;
    @Autowired
    public void setUserMapper(UserMapper userMapper){
        MailService.userMapper = userMapper;
    }
    @Autowired
    private MailSender mailSender;
    @Value("${domain.name}")
    private String domainName;

    @Value("${spring.mail.username}")
    private String form;
    /*
我们用的gava的本地缓存,最大缓存100,
expireAfterAccess 注册后15分钟没有从本地缓存进行移除
*/
    public static Cache<String, String> registerCache =
            CacheBuilder.newBuilder().maximumSize(100).expireAfterAccess(15, TimeUnit.MINUTES)
                    .removalListener(new RemovalListener<String, String>() {
                        @Override
                        public void onRemoval(RemovalNotification<String, String> notification) {
                         String email=notification.getValue();
                         if(email!=null){
                             User user = new User();
                             user.setEmail(email);
                             List<User> targetUser=userMapper.selectUsersByQuery(user);
                             if(!targetUser.isEmpty()&& Objects.equals(targetUser.get(0).getEnable(),0)){
                                 //15分钟还没有激活 ，直接删除
                                 userMapper.delete(notification.getValue());
                             }
                         }
                        }
                    }).build();
    @Async
    public void registerNotify(String email) {
        //生成指定长度的字母和数字随机组合的字符串
        String randomKey =  RandomStringUtils.randomAlphanumeric(10);
        registerCache.put(randomKey,email);
        //组装邮件内容
        //http://127.0.0.1:8080/accounts/verify?key="+randomKey
        String url="http://"+domainName+"/accounts/verify?key="+randomKey;
        //发送邮件
        sendMail("房产平台激活验证码",url,email);
    }

    public void sendMail(String title, String url, String email) {
        SimpleMailMessage message=new SimpleMailMessage();
        message.setFrom(form);
        message.setTo(email);
        message.setSubject(title);
        message.setText(url);
        mailSender.send(message);
    }
}