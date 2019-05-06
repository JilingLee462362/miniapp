package com.heyi.mini.dao;


import com.heyi.mini.model.Goods;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Setter
@Getter
@ToString

@Entity
public class GoodsNum {
    @Id
    @GeneratedValue
        private Long goodsnumid;
    @Column
        private Long id;
        private Goods goods;

    @Column
        private String name;
    @Column
        private Integer num;

}
