package com.heyi.mini.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.heyi.mini.dao.CartDao;
import com.heyi.mini.dao.GoodsDao;
import com.heyi.mini.dao.GoodsNum;
import com.heyi.mini.model.Cart;
import com.heyi.mini.model.Goods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.LinkedList;
import java.util.List;

@RestController
@RequestMapping(value = "/cart")
@ResponseBody
public class CartController {
    @Autowired
    CartDao cartDao;
    @Autowired
    GoodsDao goodsDao;
    @RequestMapping("insertCart")
    public String insertCart(@RequestBody JSONObject jsonParam){
        JSONObject result = new JSONObject();
        String cart = jsonParam.getString("cart");
        System.out.println(cart);

        Cart cart1 = JSONObject.parseObject(cart, Cart.class);
        Cart byUserinfoid = cartDao.findByUserinfoid(cart1.getUserinfoid());
        if (byUserinfoid == null) {

            Cart save = cartDao.save(cart1);
            result.put("msg", "ok");
            result.put("state", 1);
            result.put("data", save);
        }else {
            cart1.setCartid(byUserinfoid.getCartid());
            Cart save = cartDao.save(cart1);
            result.put("msg", "ok");
            result.put("state", 1);
            result.put("data", save);
        }

        return result.toJSONString();
    }
    @RequestMapping("selectCart")
    public String insertCart(HttpServletRequest request, HttpServletResponse response){
        JSONObject result = new JSONObject();
        String userid = request.getParameter("userid");
        Cart byUserinfoid = cartDao.findByUserinfoid(Long.valueOf(userid));
        if (byUserinfoid != null) {
            String details = byUserinfoid.getDetails();
            List<GoodsNum> goodsNums = JSONObject.parseArray(details, GoodsNum.class);
            List<Goods> goodsList = new LinkedList<>();
            for (GoodsNum g: goodsNums
                 ) {
                Goods goods = goodsDao.findById(g.getId()).get();
                goods.setNum(g.getNum());
                goodsList.add(goods);
            }
            Object o = JSONObject.toJSON(goodsList);
            result.put("msg", "ok");
                result.put("state", 1);
                result.put("data", o.toString());
        }else {

            result.put("msg", "ok");
            result.put("state", 0);
            result.put("data", null);
        }

        return result.toString();
    }
}
