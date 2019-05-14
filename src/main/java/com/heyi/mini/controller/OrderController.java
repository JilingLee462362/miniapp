package com.heyi.mini.controller;

import com.alibaba.fastjson.JSONObject;
import com.heyi.mini.dao.OrderDao;
import com.heyi.mini.model.Order;
import com.heyi.mini.weixinpay.common.Signature;
import com.heyi.mini.weixinpay.common.Configure;
import com.heyi.mini.weixinpay.common.HttpRequest;
import com.heyi.mini.weixinpay.common.RandomStringGenerator;
import com.heyi.mini.weixinpay.common.Signature;
import com.heyi.mini.weixinpay.model.OrderInfo;
import com.heyi.mini.weixinpay.model.OrderReturnInfo;
import com.thoughtworks.xstream.XStream;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Slf4j
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

        if (byUserinfoid.size() > 0) {
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

            if (order != null) {
                result.put("msg", "ok");
                result.put("state", 1);
                result.put("data", o.toString());
            } else {
                result.put("msg", "没有您要查询的");
                result.put("state", 0);
                result.put("data", null);
            }
            return result.toJSONString();
        } catch (Exception e) {
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
        List<Order> byUserinfoid = orderDao.findByUserinfoidandState(Long.valueOf(userinfoid), Integer.valueOf(state));

        if (byUserinfoid.size() > 0) {
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
    public String insertCart(@RequestBody JSONObject jsonParam) {
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
        switch (state) {
            case "付款":
                System.out.println("付款");
                return "redirect:order/xiadan ";

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

        if (save != null) {
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

    @RequestMapping(value = "xiadan")
    public String changeOrderState(HttpServletRequest request, HttpServletResponse response) throws UnrecoverableKeyException, KeyManagementException, NoSuchAlgorithmException, KeyStoreException, IOException {
        JSONObject jsonresult = new JSONObject();
        System.out.println("openid");
        String openid = request.getParameter("openid");
        String orderid = request.getParameter("orderid");
        String state = request.getParameter("state");
        try {
            OrderInfo order = new OrderInfo();
            order.setAppid(Configure.getAppID());//微信开放平台审核通过的应用APPID
            order.setMch_id(Configure.getMch_id());//微信支付分配的商户号
            order.setNonce_str(RandomStringGenerator.getRandomStringByLength(32));//随机字符串，不长于32位。推荐随机数生成算法
            order.setBody("dfdfdf");//商品描述交易字段格式根据不同的应用场景按照以下格式：APP——需传入应用市场上的APP名字-实际商品名称，天天爱消除-游戏充值。
            order.setOut_trade_no(RandomStringGenerator.getRandomStringByLength(32));//商户系统内部订单号，要求32个字符内，只能是数字、大小写字母_-|*且在同一个商户号下唯一。详见商户订单号
            order.setTotal_fee(10);//订单总金额，单位为分，详见支付金额
            order.setSpbill_create_ip("123.57.218.54");//支持IPV4和IPV6两种格式的IP地址。调用微信支付API的机器IP
            order.setNotify_url("https://www.see-source.com/weixinpay/PayResult");//接收微信支付异步通知回调地址，通知url必须为直接可访问的url，不能携带参数
            order.setTrade_type("JSAPI");//支付类型
            order.setOpenid(openid);
            order.setSign_type("MD5");//签名类型，目前支持HMAC-SHA256和MD5，默认为MD5
            //生成签名
            String sign = Signature.getSign(order);
            order.setSign(sign);//签名，详见签名生成算法


            String result = HttpRequest.sendPost("https://api.mch.weixin.qq.com/pay/unifiedorder", order);
            System.out.println(result);
            log.info("---------下单返回:" + result);
            XStream xStream = new XStream();
            xStream.alias("xml", OrderReturnInfo.class);

            OrderReturnInfo returnInfo = (OrderReturnInfo) xStream.fromXML(result);
            JSONObject json = new JSONObject();
            json.put("prepay_id", returnInfo.getPrepay_id());
            response.getWriter().append(json.toJSONString());


            Order orders = orderDao.findById(Long.valueOf(orderid)).get();
            orders.setState(Integer.valueOf(state));
            Order save = orderDao.save(orders);

            if (save != null) {
                Object o = JSONObject.toJSON(save);
                jsonresult.put("msg", "下单成功");
                jsonresult.put("state", 1);
                jsonresult.put("data", o.toString());
            } else {
                jsonresult.put("msg", "修改失败");
                jsonresult.put("state", 0);
                jsonresult.put("data", null);
                return jsonresult.toString();
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("-------", e);
            jsonresult.put("msg", "下单异常");
            jsonresult.put("state", 0);
            jsonresult.put("data", null);
            return jsonresult.toString();
        }


        return jsonresult.toString();
    }


}
