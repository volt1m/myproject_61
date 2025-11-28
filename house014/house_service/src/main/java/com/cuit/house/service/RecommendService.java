package com.cuit.house.service;

import com.cuit.house.page.PageParams;
import com.cuit.house.pojo.House;
import com.google.common.collect.Ordering;
import org.assertj.core.util.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class RecommendService {
    private static final String HOT_HOUSE_KEY="hot_hosue";

    @Autowired
    private HouseService houseService;

    public void increase(Long id){
        Jedis jedis = new Jedis("localhost",6379);
        jedis.zincrby(HOT_HOUSE_KEY,1.00,id+"");
        //设置固定长度
        jedis.zremrangeByScore(HOT_HOUSE_KEY,0,-11);
        jedis.close();
    }

    //我们来个方法拿到热度
    public List<Long> getHot(){
        Jedis jedis = new Jedis("localhost" , 6379);
        //拿出房屋热度
        Set<String> idSet = jedis.zrevrange(HOT_HOUSE_KEY,0,-1);
        List<Long> collectids = idSet.stream().map(Long::parseLong).collect(Collectors.toList());
        return collectids;
    }

    //根据热度id，返回房屋集合
    public  List<House> getHotHouse(Integer size){
        List<Long> list= getHot();
        list = list.subList(0,Math.min(size,list.size()));
        if(list.isEmpty()){
            return Lists.newArrayList();
        }
        House query =new House();
        query.setIds(list);
        List<House> houses = houseService.queryAndSetImg(query, PageParams.build(size,1));
        //排序guava提供
        List<Long> order =list;
        Ordering<House> houseOrder=Ordering.natural().onResultOf(hs->{
            return order.indexOf(hs.getId());//下标进行排序
        });
        //把排序的结果copy到一个新的list里面去
        return null;
    }
}
