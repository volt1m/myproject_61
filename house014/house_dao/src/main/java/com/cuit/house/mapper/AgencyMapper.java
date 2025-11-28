package com.cuit.house.mapper;

import com.cuit.house.page.PageParams;
import com.cuit.house.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface AgencyMapper {
    List<User> selectAgent(@Param("user") User user,@Param("pageParams") PageParams pageParams);

    Long selectAgentCount(@Param("user") User user);
}
