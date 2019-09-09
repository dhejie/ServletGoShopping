package com.service;

import com.bean.Shop;

public interface ShopService {

    /**
     * 按照ID查询商品
     * @param id
     * @return
     */
    Shop queryShopById(Integer id);

    /**
     * 按照商品ID减少一件库存
     * @param id
     * @return
     *   返回减少后的库存， 返回-1代表减少库存失败
     */
    int cutReserve(Integer id);

    /**
     * 按照商品ID减少count件库存
     * @param id
     * @return
     *  返回减少后的库存， 返回-1代表减少库存失败
     */
    int cutReserve(Integer id, int count);
}
