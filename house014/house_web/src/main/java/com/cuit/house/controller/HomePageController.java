package com.cuit.house.controller;

import com.cuit.house.pojo.House;
import com.cuit.house.service.HouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class HomePageController {
    @Autowired
    private HouseService houseService;

    @RequestMapping("index")
    public String indexHouse(ModelMap modelMap){
        List<House> houses = houseService.getLasttest();
        modelMap.addAttribute("recomHouses",houses);
        return "/homepage/index";
    }

    @RequestMapping("")
    public String index(){
        return "redirect:/index";
    }
}
