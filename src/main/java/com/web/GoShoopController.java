package com.web;

import com.service.ShopService;
import com.service.impl.ShopServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@WebServlet(name = "cutReserve", urlPatterns = "/shop/cutReserve/*")
public class GoShoopController extends HttpServlet {

    public static final Pattern pattern = Pattern.compile(".*/shop/cutReserve/(\\d+)$");

    ShopService shopService = new ShopServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("application/json; charset=UTF-8");

        String url = req.getRequestURL().toString();

        resp.getWriter().println("url: " + url);

        Matcher matcher = pattern.matcher(url);

        if(!matcher.find()) {
            resp.getWriter().println("{\"msg\":\"请求接口不支持\", \"code\":403}");
            return;
        }

        String sid =  matcher.group(1);

        Integer id = Integer.parseInt(sid);

        int nowCount = shopService.cutReserve(id);


        String ret = null;
        if(nowCount == -1) {
            ret = "{\"code\": 500, \"msg\": \"减库存失败\"}";
        } else {
            ret = "{\"code\": 200, \"msg\": \"操作成功\", \"data\": {\"id\": "+nowCount+", \"nowCount\": "+nowCount+"}}";
        }


        resp.getWriter().println(ret);
    }

}

