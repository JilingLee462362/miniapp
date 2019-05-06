package com.heyi.mini;

import com.alibaba.fastjson.JSONObject;
import com.heyi.mini.dao.GoodsDao;
import com.heyi.mini.dao.UserDao;
import com.heyi.mini.model.Goods;
import com.heyi.mini.model.UserDO;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Test {
    @Autowired
    UserDao userDao;
    @Autowired
    GoodsDao goodsDao;
    @org.junit.Test
    public void getUUID(){
        //System.out.println(UUID.randomUUID());
        UserDO s =  new UserDO("adsfad","AFADS","ASDFADSFADS");
        UserDO save = userDao.save(s);
        System.out.println(save.toString());

    }
    @org.junit.Test
    public void getGoods(){
        List<Goods> kk = goodsDao.findByType("快开");
        System.out.println(JSONObject.toJSONString(kk));

    }
}
