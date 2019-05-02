package com.heyi.mini.dao;

import com.heyi.mini.model.Userinfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;

/**
 * 用户数据接口类
 *
 * @author LEE
 * @since 2019-04-30
 */

@Repository
public interface UserinfoDao extends JpaRepository<Userinfo, Long> {
    @Query(value = "select * from auth_userinfo where openid = ?", nativeQuery = true)
        // nativeQuery = true表示使用sql自己的方言查询，想查什么查什么， 按照字段数据类型返回就行了
    Userinfo findByOpenid(String openid);
}
