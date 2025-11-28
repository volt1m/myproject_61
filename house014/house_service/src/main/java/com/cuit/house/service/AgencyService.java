package com.cuit.house.service;

import com.cuit.house.mapper.AgencyMapper;
import com.cuit.house.page.PageData;
import com.cuit.house.page.PageParams;
import com.cuit.house.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AgencyService {
    @Autowired
    private AgencyMapper agencyMapper;

    @Value("${file.imgprefix}")
    private String imgPrefix;
    //获取经纪人的信息
    //添加用户头像
    public User getAgentDetail(Long userId) {
        User user = new User();
        user.setId(userId);
        //2是经纪人
        user.setType(2);
        List<User> list = agencyMapper.selectAgent(user, PageParams.build(1,1));
        setImg(list);
        if(!list.isEmpty()){
            return list.get(0);
        }
        return null;
    }

    private void setImg(List<User> list) {
        list.forEach(u->{
            u.setAvatar(imgPrefix+u.getAvatar());
        });
    }

    public PageData<User> getAllAgent(PageParams pageParams) {
        List<User> users = agencyMapper.selectAgent(new User(),pageParams);
        setImg(users);
        Long count=agencyMapper.selectAgentCount(new User());
        return PageData.buildPage(users,count,pageParams.getPageSize(),pageParams.getPageNum());
    }
}
