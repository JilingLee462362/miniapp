package com.heyi.mini.model;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

/**
 * 用户实体类
 *@Entity 是一个必选的注解，声明这个类对应了一个数据库表。
 * @Table(name = "AUTH_USER") 是一个可选的注解。声明了数据库实体对应的表信息。包括表名称、索引信息等。这里声明这个实体类对应的表名是 AUTH_USER。如果没有指定，则表名和实体的名称保持一致。
 * @Id 注解声明了实体唯一标识对应的属性。
 * @Column(length = 32) 用来声明实体属性的表字段的定义。默认的实体每个属性都对应了表的一个字段。字段的名称默认和属性名称保持一致（并不一定相等）。字段的类型根据实体属性类型自动推断。这里主要是声明了字符字段的长度。如果不这么声明，则系统会采用 255 作为该字段的长度
 *@GeneratedValue，这注解里面有一些属性，其中一个是策略strategy，生成主键值的方案，JPA里没有Hibernate提供的那么多方案，它提供的方案有一下一些：看图。。
 *
 *
 *
 * 1) AUTO： JPA自动选择合适的策略，是默认选项；
 *
 * 2) IDENTITY：采用数据库ID自增长的方式来生成主键值，Oracle不支持这种方式；
 *
 * 3) SEQUENCE：通过序列产生主键，通过@SequenceGenerator注解指定序列名，MySql不支持这种方式；
 *
 * 4) TABLE：采用表生成方式来生成主键值，那怎么样生成呢？很简单，表里面通常有两个字段，第一个字段是给它一个名称（就是个列名而已），第二个字段专门用来累加用的，就是说没访问一次这个表呢，那么第二个字段就会累加1，不断累加。就是说你们要得到这个主键值的话，访问这个表，然后update这个表的这个字段，把它累加1之后，然后再把这个值取出来作为主键，再给他赋进去，表生成就是这样。
 *
 *
 *
 * IDENTITY和SEQUENCE这两种生成方案通不通用啊？对所有数据库。
 *
 * Oracle数据库默认情况下，不能支持用id自增长方式来生成主键值；
 *
 * mysql在默认情况下不能支持SEQUENCE序列的方式来生成主键值，所以我们一定要注意我们使用的数据库。
 *
 * TABLE表生成方式才是通用的，但是这种方式效率并不高，
 *
 * 如果我们开发的应用，我们不可以预测用户到底使用哪种数据库，那么这个时候应该设为哪个值呢？
 * 答案是AUTO，就是说由持久化实现产品，来根据你使用的方言来决定它采用的主键值的生成方式，到底是IDENTITY？还是SEQUENCE？还是TABLE？
 * 如果用的是Hibernate,那么它会用IDENTITY这种生成方式来生成主键值。
 *
 * @author LEE
 * @since 2019-04-30
 */

@Setter
@Getter
@Entity
@ToString
@Table(name = "AUTH_CART")
public class Cart implements java.io.Serializable {

    @Id
    @GeneratedValue
    private Long cartid;


    @Column
    private String details;


    @Column
    private Long userinfoid;


    private Userinfo userinfo;


    public Long getCartid() {
        return cartid;
    }

    public void setCartid(Long cartid) {
        this.cartid = cartid;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public Long getUserinfoid() {
        return userinfoid;
    }

    public void setUserinfoid(Long userinfoid) {
        this.userinfoid = userinfoid;
    }

    public Userinfo getUserinfo() {
        return userinfo;
    }

    public void setUserinfo(Userinfo userinfo) {
        this.userinfo = userinfo;
    }
}
