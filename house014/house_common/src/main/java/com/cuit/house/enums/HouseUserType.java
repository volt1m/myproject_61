package com.cuit.house.enums;

public enum HouseUserType {
    SALE(1),BOOKMARK(2);

    public final Integer value;

    HouseUserType(Integer value){
        this.value=value;
    }
}
