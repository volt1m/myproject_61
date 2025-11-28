package com.cuit.house.utils;

import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;

import java.nio.charset.Charset;

public class HashUtils {
    private static final HashFunction FUNCTION= Hashing.md5();

    //定义一个盐
    private static final String SALT="cuit.com";

    public static String encryPassword(String password){
        String hashCode = FUNCTION.hashString
        (password+SALT, Charset.forName("utf-8")).toString();
        return hashCode;
    }
}
