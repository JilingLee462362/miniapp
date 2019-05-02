package com.heyi.mini;

import com.heyi.mini.dao.UserDao;
import com.heyi.mini.model.UserDO;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Test {
    @Autowired
    UserDao dao;
    @org.junit.Test
    public void getUUID(){
        //System.out.println(UUID.randomUUID());
        UserDO s =  new UserDO("adsfad","AFADS","ASDFADSFADS");
        UserDO save = dao.save(s);
        System.out.println(save.toString());

    }

}
