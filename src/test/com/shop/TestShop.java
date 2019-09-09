package com.shop;

import com.service.ShopService;
import com.service.impl.ShopServiceImpl;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class TestShop {
    ShopService shopService = new ShopServiceImpl();
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");


    @Test
    public void testUpdate() {


        int size = 100;

        ExecutorService threadPool = Executors.newFixedThreadPool(100);

        for( int i = 0; i < size; i++)
        threadPool.execute(new Runnable() {
            @Override
            public void run() {
                String threadName = Thread.currentThread().getName();

                System.out.println(sdf.format(new Date()) + "  ["+threadName+"]: " + shopService.cutReserve(1));
            }
        });

        threadPool.shutdown();

        try {
            threadPool.awaitTermination(1, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}