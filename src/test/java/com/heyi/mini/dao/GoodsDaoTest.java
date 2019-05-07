package com.heyi.mini.dao;

import javafx.application.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest()// 指定启动类
public class GoodsDaoTest {
@Autowired
    GoodsDao dao;
@Test
    public  void getimg(){
    System.out.println(dao.findById(1L).get().toString());

}
}