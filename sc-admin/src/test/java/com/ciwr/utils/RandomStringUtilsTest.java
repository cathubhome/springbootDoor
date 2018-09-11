package com.ciwr.utils;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;

/**
 * Created with IDEA
 *
 * @Author:catHome
 * @Description: shiro的token生成时盐通常采用 ByteSource.Util.bytes（账户或随机字符串）
 * 使用org.apache.commons.lang3.RandomStringUtils随机字符串生成工具类
 * @Time:Create on 2018/7/11 10:38
 */
public class RandomStringUtilsTest {


    @Test
    public  void testRandomStirng(){
        long startTime =  System.currentTimeMillis();
        for (;;){
            long endTime = System.currentTimeMillis();
            System.out.println(RandomStringUtils.random(9,true,false));
            if(endTime - startTime > 100){
                break;
            }
        }
    }

}
