package com.heyi.mini.tools;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class Tools {
    public static Long getOrderIdByTime() {
        SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmss");
        String newDate=sdf.format(new Date());
        String result="";
        Random random=new Random();
        for(int i=0;i<2;i++){
            result+=random.nextInt(10);
        }
        return Long.valueOf(newDate+result);
    }
}
