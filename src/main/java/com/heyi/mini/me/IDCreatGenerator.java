package com.heyi.mini.me;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.heyi.mini.tools.Tools;
import org.hibernate.MappingException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentityGenerator;

import java.util.Random;

/**
 * 自定义UUID生成器
 * @author sevenlin
 */
public class IDCreatGenerator extends IdentityGenerator {
    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object object) throws MappingException {
        Long orderIdByTime = Tools.getOrderIdByTime();
        return orderIdByTime;// 当前时间
    }


}