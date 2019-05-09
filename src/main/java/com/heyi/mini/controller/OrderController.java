package com.heyi.mini.controller;

import com.alibaba.fastjson.JSONObject;
import com.heyi.mini.dao.OrderDao;
import com.heyi.mini.model.Order;
import com.heyi.mini.tools.Tools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/order")
@ResponseBody
public class OrderController {
    @Autowired
    OrderDao orderDao;

    @RequestMapping(value = "findByUserinfoid")
    public String findById(HttpServletRequest request) {
        JSONObject result = new JSONObject();
        String userinfoid = request.getParameter("userinfoid");
        List<Order> byUserinfoid = orderDao.findByUserinfoid(Long.valueOf(userinfoid));

        if (byUserinfoid.size()>0) {
            Object o = JSONObject.toJSON(byUserinfoid);
            result.put("msg", "ok");
            result.put("state", 1);
            result.put("data", o.toString());
        } else {
            result.put("msg", "null");
            result.put("state", 0);
            result.put("data", null);
        }
        return result.toString();
    }
    @RequestMapping(value = "findByOrderid")
    public String findByOrderid(HttpServletRequest request) {
        JSONObject result = new JSONObject();
        String orderid = request.getParameter("orderid");
        Order order = orderDao.findById(Long.valueOf(orderid)).get();

        if (order!=null) {
            result.put("msg", "ok");
            result.put("state", 1);
            result.put("data", order);
        } else {
            result.put("msg", "没有您要查询的");
            result.put("state", 0);
            result.put("data", null);
        }
        return result.toString();
    }
    @RequestMapping(value = "findByUserinfoidandState")
    public String findByIdandState(HttpServletRequest request) {
        JSONObject result = new JSONObject();
        String userinfoid = request.getParameter("userinfoid");
        String state = request.getParameter("state");
        List<Order> byUserinfoid = orderDao.findByUserinfoidandState(Long.valueOf(userinfoid),Integer.valueOf(state));

        if (byUserinfoid.size()>0) {
            Object o = JSONObject.toJSON(byUserinfoid);
            result.put("msg", "ok");
            result.put("state", 1);
            result.put("data", o.toString());
        } else {
            result.put("msg", "没有您要查询的");
            result.put("state", 0);
            result.put("data", null);
        }
        return result.toJSONString();
    }

    @RequestMapping("insertOrder")
    public String insertCart(@RequestBody JSONObject jsonParam){
        JSONObject result = new JSONObject();
        String order1 = jsonParam.getString("order");
        System.out.println(order1);

        Order order = JSONObject.parseObject(order1, Order.class);
        order.setCreatdata(new Timestamp(System.currentTimeMillis()));
        order.setOrderid(Long.valueOf(Tools.getOrderIdByTime()));

        Order save = orderDao.save(order);
            result.put("msg", "ok");
            result.put("state", 1);
            result.put("data", save);


        return result.toJSONString();
    }

}