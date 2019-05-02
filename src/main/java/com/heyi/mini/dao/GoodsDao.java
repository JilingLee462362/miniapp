package com.heyi.mini.dao;

import com.heyi.mini.model.Goods;
import com.heyi.mini.model.Userinfo;
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
public interface GoodsDao extends JpaRepository<Goods, Long> {
    @Query(value = "select * from auth_goods where type = ?", nativeQuery = true)
        // nativeQuery = true表示使用sql自己的方言查询，想查什么查什么， 按照字段数据类型返回就行了
   List<Goods> findByType(String type);
}
