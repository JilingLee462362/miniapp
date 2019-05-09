package com.heyi.mini.dao;

import com.heyi.mini.model.Order;
import javafx.application.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest()// 指定启动类
public class GoodsDaoTest {
@Autowired
    GoodsDao dao;

@Autowired
OrderDao orderDao;
@Test
    public  void getimg(){
    System.out.println(dao.findById(1L).get().toString());

}
@Test
    public void getOrderstate(){

    List<Order> byUserinfoidandState = orderDao.findByUserinfoidandState(3L, 0);
    for (Order o:
         byUserinfoidandState) {
        System.out.println(o);
    }
}
}