package com.heyi.mini.controller;

import com.alibaba.fastjson.JSONObject;
import com.heyi.mini.dao.UseraddressDao;
import com.heyi.mini.model.Useraddress;
import com.heyi.mini.model.Userinfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.util.List;

@RestController
@RequestMapping(value = "/Useraddress")
@ResponseBody
public class UseraddressController {
    @Autowired
    UseraddressDao useraddressDao;

    @RequestMapping(value = "insert")
    @Transactional
    public String insert(@RequestBody JSONObject jsonParam) {
        JSONObject result = new JSONObject();
        // 直接将json信息打印出来
        String s = jsonParam.getString("useraddress");
        System.out.println(s);
        Useraddress useraddress = JSONObject.parseObject(s, Useraddress.class);
        String detailed = useraddress.getDetailed();
        List<Useraddress> byUserinfoid = useraddressDao.findByUserinfoid(useraddress.getUserinfoid());
        if (byUserinfoid.size()>0) {
            for (Useraddress u : byUserinfoid
            ) {
                if (!u.getDetailed().equals(detailed)) {
                    Useraddress save = useraddressDao.save(useraddress);
                    result.put("msg", "ok");
                    result.put("state", 1);
                    result.put("data", save);
                } else {
                    result.put("msg", "重复添加");
                    result.put("state", 0);
                    result.put("data", null);
                }

            }
            return result.toJSONString();
        }else {
            Useraddress save = useraddressDao.save(useraddress);
            result.put("msg", "ok");
            result.put("state", 1);
            result.put("data", save);
            return result.toJSONString();
        }
    }

    @RequestMapping(value = "findByUserid")
    public String findById(HttpServletRequest request) {
        JSONObject result = new JSONObject();
        String userinfoid = request.getParameter("userinfoid");
        List<Useraddress> byUserinfoid = useraddressDao.findByUserinfoid(Long.valueOf(userinfoid));

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
}






