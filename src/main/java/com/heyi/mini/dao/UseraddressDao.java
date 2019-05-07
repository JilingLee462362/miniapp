package com.heyi.mini.dao;

import com.heyi.mini.model.Useraddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UseraddressDao extends JpaRepository<Useraddress, Long> {
    @Query(value = "select * from auth_useraddress where userinfoid = ?", nativeQuery = true)
        // nativeQuery = true表示使用sql自己的方言查询，想查什么查什么， 按照字段数据类型返回就行了
    List<Useraddress> findByUserinfoid(Long userinfoid);

}
