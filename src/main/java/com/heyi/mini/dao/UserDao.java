package com.heyi.mini.dao;


import com.heyi.mini.model.UserDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * 用户服务数据接口类
 *
 * @author LEE
 * @since 2019-04-30
 */

@Repository
public interface UserDao extends JpaRepository<UserDO, Long> {
}