package com.cuit.house.controller;

import com.cuit.house.constrants.CommonConstants;
import com.cuit.house.page.PageData;
import com.cuit.house.page.PageParams;
import com.cuit.house.pojo.House;
import com.cuit.house.pojo.User;
import com.cuit.house.service.AgencyService;
import com.cuit.house.service.HouseService;
import com.cuit.house.service.RecommendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class AgentController {
    @Autowired
    private HouseService houseService;

    @Autowired
    private AgencyService agencyService;

    @Autowired
    private RecommendService recommendService;

    @RequestMapping("/agency/agentList")
    public String agentList(Integer pageSize,Integer pageNum, ModelMap model) {
        PageData<User> ps = agencyService.getAllAgent(PageParams.build(pageSize,pageNum));
        model.put("ps",ps);
        return "/user/agent/agentList";
    }

    @RequestMapping("agency/agentDetail")
    public String agentDetail(Long id,ModelMap modelMap){
        //查询经纪人详情时，会显示经纪人发布的房产
        User user = agencyService.getAgentDetail(id);
        House query = new House();
        query.setUserId(user.getId());
        query.setBookmarked(false); //售卖
        PageData<House> bindHouse = houseService.queryHouse(query,new PageParams(3,1));
        if(bindHouse!=null){
            modelMap.put("bindHouses",bindHouse.getList());
        }
        List<House> hotHouse = recommendService.getHotHouse(CommonConstants.RECOM_SIZE);
        modelMap.put("recomHouses",hotHouse);
        modelMap.put("agent",user);
        return "/user/agent/agentDetail";
    }
}