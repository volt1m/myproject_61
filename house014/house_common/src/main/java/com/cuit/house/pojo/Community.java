package com.cuit.house.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Community {
    private Integer id;
    private String cityCode;
    private String cityName;
    private String name;
}
