package com.lord.test;

import cc.lord.merchant.domain.MchLabel;
import org.junit.Test;

public class TestDemo {

    @Test
    public void bitmapDemo(){
        int a=3;
        int b=9;

        System.out.println(a&=(a-1));
        System.out.println(3^4);
    }

    @Test
    public void demo(){
        String[] labels="123,123".split(",");
        for (String str:labels) {
            System.out.println(str);
        }
    }
}
