package com.cuit.house.service;

import com.cuit.house.pojo.City;
import org.assertj.core.util.Lists;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityService {
    public List<City> getAllCitys(){
        City city =new City();
        city.setCityCode("11000");
        city.setCityName("北京");
        city.setId(1);
        return Lists.newArrayList(city);
    }
}
