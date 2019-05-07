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
@ToString
@Table(name = "AUTH_GOODS")
public class Goods implements java.io.Serializable {

    @Id
    @GeneratedValue
    private Long goodsid;
    @Column(length = 32)
    private String name;
    @Column
    private String imgName;
    @Column(length = 32)
    private String specs;
    @Column
    private String description;
    @Column
    private double price;
    @Column
    private Integer salesNum;
    @Column
    private Integer pingjia;
    @Column(length = 32)
    private String type;

    private int num;

}
