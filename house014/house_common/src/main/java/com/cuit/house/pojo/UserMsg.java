package com.cuit.house.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserMsg {
    private Long id;
    private String msg;
    private Long userId;
    private Date createTime;
    private Long agentId;
    private Long houseId;
    private String email;
    private String userName;
}
