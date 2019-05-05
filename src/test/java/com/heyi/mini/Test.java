package com.heyi.mini;

import com.heyi.mini.dao.CartDao;
import com.heyi.mini.dao.GoodsDao;
import com.heyi.mini.dao.UserDao;
import com.heyi.mini.model.Goods;
import com.heyi.mini.model.UserDO;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;
import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Test {
    @Autowired
    UserDao dao;
    @Autowired
    GoodsDao goodsDao;
    @Autowired
    CartDao cartDao;


    @org.junit.Test
    public void getUUID(){
        Optional<Goods> byId = goodsDao.findById(2l);
        System.out.println(byId.get().toString());

    }
    @org.junit.Test
    public void getcar(){
        System.out.println(cartDao.findByCartid(1l));

    }

}
