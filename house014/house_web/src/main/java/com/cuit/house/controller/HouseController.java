package com.cuit.house.controller;

import com.cuit.house.constrants.CommonConstants;
import com.cuit.house.enums.HouseUserType;
import com.cuit.house.page.PageData;
import com.cuit.house.page.PageParams;
import com.cuit.house.pojo.House;
import com.cuit.house.pojo.HouseUser;
import com.cuit.house.pojo.User;
import com.cuit.house.pojo.UserMsg;
import com.cuit.house.result.ResultMsg;
import com.cuit.house.service.AgencyService;
import com.cuit.house.service.CityService;
import com.cuit.house.service.HouseService;
import com.cuit.house.service.RecommendService;
import com.cuit.house.utils.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class HouseController {
    @Autowired
    private AgencyService agencyService;

    @Autowired
    private HouseService houseService;

    @Autowired
    private RecommendService recommendService;

    @Autowired
    private CityService cityService;

    @RequestMapping("/house/list")
    public  String list(Integer pageNum, Integer pageSize, House query, ModelMap modelMap){
        PageData<House> ps = houseService.queryHouse(query, PageParams.build(pageSize,pageNum));
        List<House> hotHouse = recommendService.getHotHouse(CommonConstants.RECOM_SIZE);
        modelMap.put("recomHouses",hotHouse);
        modelMap.put("ps",ps);
        modelMap.put("vo",query);
        return "house/listing";
    }

    //查询房屋详情
    @RequestMapping("/house/detail")
    public String houseDetail(Long id,ModelMap modelMap){
        recommendService.increase(id);
        House house = houseService.queryOneHouse(id);
        HouseUser houseUser = houseService.getHouseUser(id);
        if(houseUser.getUserId()!=null&&!houseUser.getUserId().equals(0)){
            modelMap.put("agent",agencyService.getAgentDetail(houseUser.getUserId()));
        }
        List<House> hotHouse = recommendService.getHotHouse(CommonConstants.RECOM_SIZE);
        modelMap.put("recomHouses",hotHouse);
        modelMap.put("house",house);
        return "house/detail";
    }
    //留言
    @RequestMapping("/house/leaveMsg")
    public String leaveMsg(UserMsg userMsg){
        houseService.addUserMsg(userMsg);
        return "redirect:/house/detail?id="+userMsg.getHouseId();
    }

    @RequestMapping("house/toAdd")
    public String toAdd(ModelMap modelMap){
        modelMap.put("citys",cityService.getAllCitys());
        modelMap.put("communitys",houseService.getAllCommunitys());
        return "/house/add";
    }

    @RequestMapping("house/add")
    public String add(House house){
        User user = UserContext.getUser();
        house.setState(CommonConstants.HOUSE_STATE_UP);
        houseService.addHouse(house,user);
        return "redirect:/index";
    }
    //个人房产
    @RequestMapping("house/ownlist")
    public String ownList(Integer pageNum,Integer pageSize,House house,ModelMap modelMap){
        User user=UserContext.getUser();
        house.setUserId(user.getId());
        house.setBookmarked(false);
        modelMap.put("ps",houseService.queryHouse(house,PageParams.build(pageSize,pageNum)));
        modelMap.put("pageType","own");
        return "/house/ownlist";
    }
    //评分
    @ResponseBody
    @RequestMapping("house/rating")
    public ResultMsg houseRate(Double rating,Long id){
        houseService.updateRating(id,rating);
        return ResultMsg.successMsg("ok");
    }
    //收藏
    @ResponseBody
    @RequestMapping("/house/bookmark")
    public ResultMsg houseBookmark(Long id){
        User user=UserContext.getUser();
        houseService.bindUserToHouse(id,user.getId(),true);
        return ResultMsg.successMsg("ok");
    }
    //删除收藏
    @ResponseBody
    @RequestMapping("house/unbookmark")
    public ResultMsg houseunBookmark(Long id){
        User user = UserContext.getUser();
        houseService.unbindUserToHouse(id,user.getId(), HouseUserType.BOOKMARK);
        return ResultMsg.successMsg("ok");
    }
    //收藏列表
    @RequestMapping("house/bookmarked")
    public String bookmarked(Integer pageNum,Integer pageSize,House house,ModelMap modelMap) {
        User user=UserContext.getUser();
        house.setUserId(user.getId());
        house.setBookmarked(true);
        modelMap.put("ps",houseService.queryHouse(house,PageParams.build(pageSize, pageNum)));
        modelMap.put("pageType","book");
        return "/house/ownlist";
    }
    //删除房产
    @RequestMapping("/house/del")
    public String del(Long id,String pageType){
        User user = UserContext.getUser();
        houseService.unbindUserToHouse(id, user.getId(), pageType.equals("own")?HouseUserType.SALE:HouseUserType.BOOKMARK);
        return "redirect:/house/ownlist";
    }
}
