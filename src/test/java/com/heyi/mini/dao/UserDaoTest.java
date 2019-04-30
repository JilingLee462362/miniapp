package com.heyi.mini.dao;

import com.heyi.mini.model.UserDO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserDaoTest {
    @Autowired
    private UserDao userDao;
    @Test
    public void testAdd() {
        UserDO UserDO = new UserDO();
        UserDO = new UserDO();
        UserDO.setId(2L);
        UserDO.setName("任我行1");
        UserDO.setAccount("renwox");
        UserDO.setPwd("123456");
        userDao.save(UserDO);
        UserDO = new UserDO();
        UserDO.setId(4L);
        UserDO.setName("令狐冲1");
        UserDO.setAccount("linghuc");
        UserDO.setPwd("123456");
        userDao.save(UserDO);
    }

}