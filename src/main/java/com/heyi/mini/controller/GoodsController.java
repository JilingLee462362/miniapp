package com.heyi.mini.controller;

import com.alibaba.fastjson.JSONObject;
import com.heyi.mini.dao.GoodsDao;
import com.heyi.mini.model.Goods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.util.List;

@RestController
@RequestMapping(value = "/goods")
@ResponseBody
public class GoodsController {
    @Autowired
    GoodsDao dao;

    @RequestMapping(value = "selectByType")
    @Transactional
    public String selectByType(HttpServletRequest request) {
        JSONObject result = new JSONObject();
        String type = request.getParameter("type");
        List<Goods> goodsList = dao.findByType(type);
        if (goodsList.size()>0) {
            Object o = JSONObject.toJSON(goodsList);
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
}