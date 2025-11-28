package com.cuit.house.mapper;

import com.cuit.house.pojo.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    public List<User> selectUsers();

    void insert(User account);

    void delete(String email);

    void update(User updateUser);

    List<User> selectUsersByQuery(User user);
}
