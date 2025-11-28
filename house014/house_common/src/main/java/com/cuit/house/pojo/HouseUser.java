package com.cuit.house.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HouseUser {
    private Long id;
    private Long houseId;
    private Long userId;
    private Date createTime;
    private Integer type;
}
