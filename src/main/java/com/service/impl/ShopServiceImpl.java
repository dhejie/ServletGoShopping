package com.service.impl;

import com.bean.Shop;
import com.mapper.ShopMapper;
import com.service.ShopService;
import com.util.MybatisUtil;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import redis.clients.jedis.Jedis;

public class ShopServiceImpl implements ShopService {
    static SqlSessionFactory sqlSessionFactory = null;
    Jedis jedis = new Jedis("localhost");

    static {
        sqlSessionFactory = MybatisUtil.getSqlSessionFactory();
    }

    @Override
    public Shop queryShopById(Integer id) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            ShopMapper shopMapper = sqlSession.getMapper(ShopMapper.class);
            Shop shop = shopMapper.findShopById(id);
            jedis.set("shop_" + id, String.valueOf(shop));
            return shop;
        } finally {
            sqlSession.close();
        }
    }

    @Override
    public int cutReserve(Integer id) {
        return cutReserve(id, 1);
    }

    @Override
    public synchronized int cutReserve(Integer id, int count) {
        Shop shop = null;
        Object str = jedis.get("shop" + id);
        shop = (Shop) str;
        if (str == null) {
            shop = queryShopById(id);
        }


        if (shop == null || shop.getNum() < 1) {
            return -1;
        }
        if (shop.getNum() < count) {
            return -1;
        }
        shop.setNum(shop.getNum() - count);

        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            ShopMapper shopMapper = sqlSession.getMapper(ShopMapper.class);
            int rows = shopMapper.updateNum(shop);
            if (rows > 0) {
                return shop.getNum();   // 返回减完库存的数量
            }
        } finally {
            sqlSession.close();
        }

        return -1;  // 减库存失败
    }
}
