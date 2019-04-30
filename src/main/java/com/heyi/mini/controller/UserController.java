package com.heyi.mini.controller;

import com.alibaba.fastjson.JSONObject;
import com.heyi.mini.dao.UserinfoDao;
import com.heyi.mini.model.Userinfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@RestController
@RequestMapping(value = "/user")
public class UserController {
@Autowired
private  UserinfoDao dao;


    @ResponseBody
    @RequestMapping(value = "login")
    public String login(HttpServletResponse response, HttpServletRequest request) {
        String userinfoStr = request.getParameter("userinfo");
        Userinfo userinfo = JSONObject.parseObject(userinfoStr, Userinfo.class);
        dao.save(userinfo);
        return "ok";

    }
}
