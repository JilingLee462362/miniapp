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
        try {
            Long aLong = Long.valueOf(orderid);

            Order order = orderDao.findById(aLong).get();
        Object o = JSONObject.toJSON(order);

        if (order!=null) {
            result.put("msg", "ok");
            result.put("state", 1);
            result.put("data", o.toString());
        } else {
            result.put("msg", "没有您要查询的");
            result.put("state", 0);
            result.put("data", null);
        }
        return result.toJSONString();
        }catch (Exception e){
            e.printStackTrace();
            result.put("msg", "查询异常");
            result.put("state", 0);
            result.put("data", null);
            return result.toJSONString();
        }
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
        order.setState(1);
        order.setCreatdata(new Timestamp(System.currentTimeMillis()));

        Order save = orderDao.save(order);
            result.put("msg", "ok");
            result.put("state", 1);
            result.put("data", save);


        return result.toJSONString();
    }

    @RequestMapping(value = "changeOrderState")
    public String changeOrderState(HttpServletRequest request) {
        //'付款','物流','售后','评价','取消'
        JSONObject result = new JSONObject();
        String orderid = request.getParameter("orderid");
        String state = request.getParameter("state");
        switch (state){
            case "付款":
                System.out.println("付款");
                break;
            case "物流":
                System.out.println("物流");
                break;
            case "售后":
                System.out.println("售后");
                break;
            case "评价":
                System.out.println("评价");
                break;
            case "取消":
                System.out.println("取消");
                break;


                default:
                    break;

        }



        Order order = orderDao.findById(Long.valueOf(orderid)).get();
        order.setState(Integer.valueOf(state));
        Order save = orderDao.save(order);

        if (save!=null) {
            Object o = JSONObject.toJSON(save);
            result.put("msg", "ok");
            result.put("state", 1);
            result.put("data", o.toString());
        } else {
            result.put("msg", "修改失败");
            result.put("state", 0);
            result.put("data", null);
        }
        return result.toJSONString();
    }


}
