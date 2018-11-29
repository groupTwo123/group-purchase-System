package com.felix.grouppurchase.util;

import java.util.Random;

/**
 * @Date: 2018/11/28 21:47
 * @Author: fangyong
 */
public class RandUtil {
    public  static String  getRandomNum(){
        Random random=new Random();
        String randomNum = random.nextInt(1000000) + "";
        if(randomNum.length()!=6){
            System.out.println("6位伪随机数："+randomNum);
            return  getRandomNum();
        }
        System.out.println("6位随机数："+randomNum);
        return randomNum;
    }
}