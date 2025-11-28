package com.cuit.house;

import com.cuit.house.pojo.User;
import com.cuit.house.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AuthTest {
    @Autowired
    private UserService userService;

    @Test
    public void test(){
        User aa= userService.auth("aa","11");
        System.out.println(aa);
    }
}
