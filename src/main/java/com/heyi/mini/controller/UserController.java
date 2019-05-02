package com.heyi.mini.controller;

import com.alibaba.fastjson.JSONObject;
import com.heyi.mini.dao.UserinfoDao;
import com.heyi.mini.model.Userinfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;


@RestController
@RequestMapping(value = "/user")
@ResponseBody
public class UserController {
@Autowired
private  UserinfoDao dao;



    @RequestMapping(value = "login")
    @Transactional
    public String  login(@RequestBody JSONObject jsonParam) {
        JSONObject result = new JSONObject();
        // 直接将json信息打印出来
        String s = jsonParam.getString("userinfo");
        System.out.println(s);

        Userinfo userinfo = JSONObject.parseObject(s, Userinfo.class);
        String openid = userinfo.getOpenid();
        Userinfo byOpenid = dao.findByOpenid(openid);
        if (byOpenid == null) {
            Userinfo save = dao.save(userinfo);
            // 将获取的json数据封装一层，然后在给返回

            result.put("msg", "ok");
            result.put("state", 1);
            result.put("data", save.toString());
        }else {
            // 将获取的json数据封装一层，然后在给返回

            result.put("msg", "ok");
            result.put("state", 1);
            result.put("data", userinfo.toString());
        }



        return result.toJSONString();

    }


}
