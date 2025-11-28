package com.cuit.house.mapper;

import com.cuit.house.page.PageParams;
import com.cuit.house.pojo.Community;
import com.cuit.house.pojo.House;
import com.cuit.house.pojo.HouseUser;
import com.cuit.house.pojo.UserMsg;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface HouseMapper {
    //分页数据
    public List<House> selectPageHouse(@Param("house") House house, PageParams pageParams);
    //总数
    public Long selectPageCount(@Param("house") House house);

    List<Community> selectCommunity(Community community);

    HouseUser selectSaleHouseUser(Long houseId);

    void insertUserMsg(UserMsg userMsg);

    void insert(House house);

    void insertHouseUser(HouseUser houseUser);

    void updateHouse(House updateHouse);

    void deleteHouseUser(@Param("id") Long id,@Param("userId") Long userId,@Param("type") Integer value);
}
