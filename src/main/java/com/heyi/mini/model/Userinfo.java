package com.heyi.mini.model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

/**
 * 用户实体类
 *@Entity 是一个必选的注解，声明这个类对应了一个数据库表。
 * @Table(name = "AUTH_USER") 是一个可选的注解。声明了数据库实体对应的表信息。包括表名称、索引信息等。这里声明这个实体类对应的表名是 AUTH_USER。如果没有指定，则表名和实体的名称保持一致。
 * @Id 注解声明了实体唯一标识对应的属性。
 * @Column(length = 32) 用来声明实体属性的表字段的定义。默认的实体每个属性都对应了表的一个字段。字段的名称默认和属性名称保持一致（并不一定相等）。字段的类型根据实体属性类型自动推断。这里主要是声明了字符字段的长度。如果不这么声明，则系统会采用 255 作为该字段的长度
 *
 * 作者：阿土伯已经不是我
 * 链接：https://www.jianshu.com/p/c14640b63653
 * 来源：简书
 * 简书著作权归作者所有，任何形式的转载都请联系作者获得授权并注明出处。
 * @author LEE
 * @since 2019-04-30
 */

@Setter
@Getter
@Entity
@Table(name = "AUTH_USERINFO")
public class Userinfo implements java.io.Serializable{

    @Id
    @GeneratedValue
    private Long userinfoid;
    @Column(length = 64)
    private String openid;//用户openid
    @Column(length = 32)
    private String nickName;//用户昵称
    @Column
    private String avatarUrl;//用户头像
    @Column(length = 32)
    private String province;//用户省份
    @Column(length = 32)
    private String city;//用户城市
    @Column(length = 32)
    private String dengji;//用户等级




// @OneToMany (mappedBy = "Articles"),mappedBy指向的是要关联的属性，而不是要关联的类
    @OneToMany(mappedBy = "userinfoid")
    private List<Order> orderList;
    @OneToMany(mappedBy = "userinfoid")
    private List<Useraddress> useraddressList;

    @Override
    public String toString() {
        return "{" +
                "userinfoid=" + userinfoid +
                ", openid='" + openid + '\'' +
                ", nickName='" + nickName + '\'' +
                ", avatarUrl='" + avatarUrl + '\'' +
                ", province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", dengji='" + dengji + '\'' +
                ", orderList=" + orderList +
                "}";
    }
}
