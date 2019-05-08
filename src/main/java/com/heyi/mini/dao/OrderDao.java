package com.heyi.mini.dao;

import com.heyi.mini.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 商品服务数据接口类
 *
 * @author LEE
 * @since 2019-04-30
 */

@Repository
public interface OrderDao extends JpaRepository<Order, Long> {
    @Query(value = "select * from auth_cart where userinfoid = ?", nativeQuery = true)
        // nativeQuery = true表示使用sql自己的方言查询，想查什么查什么， 按照字段数据类型返回就行了
    List<Order> findByUserinfoid(Long userinfoid);

}
