package com.mapper;

import com.bean.Shop;

import java.util.List;

public interface ShopMapper {
    Shop  findShopById(Integer id);
    int updateNum(Shop shop);
}
