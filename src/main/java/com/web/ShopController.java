package com.web;

import com.service.ShopService;
import com.service.impl.ShopServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


// 访问地址： http://localhost:8080/shop/cutReserve/1
@WebServlet("/")
public class ShopController extends HttpServlet {

    ShopService shopService = new ShopServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf-8");
//        Shop shop = shopService.queryShop();
//        String str = JSON.toJSONString(shop);
//        resp.getWriter().print(str);
    }
}
