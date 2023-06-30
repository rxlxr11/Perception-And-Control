package org.example;

import Frame.Tool;

import java.math.BigInteger;
import  java.util.*;
public class test {
    public static void main(String[] args) {
        //Integer范围：[-2147483648, 2147483647]
//十进制数字：16, 十六进制表示：1a
        Integer num = new Integer(26);
        //十进制转换十六进制字符串
        String numHex = Integer.toHexString(num);
        System.out.println(numHex);

// 十六进制字符串转换为十进制数字(Integer.valueOf 或 Integer.parseInt均可指定radix)
        System.out.println(Integer.valueOf(numHex, 16));

//Integer上下界
        System.out.println(Integer.MIN_VALUE + " - " + Integer.MAX_VALUE);
        System.out.println(Integer.toHexString(Integer.MIN_VALUE) + " - " + Integer.toHexString(Integer.MAX_VALUE));

        //BigInteger
        String bigIntDec = "2181038088";
        String bigIntHex = "82000008";
        //十六进制转10进制
        BigInteger bigInt = new BigInteger(bigIntHex, 16);
//十进制转十六进制
        System.out.println(bigInt.toString(16));
        StringBuilder NEW = new StringBuilder();
        byte[] buffer = new byte[2048];
        buffer[0] = 1;
        buffer[1] = 0;
        buffer[2] = 0;
        System.out.println("111".getBytes().length);
        System.out.println(buffer.length);
        String s = "FFFF01020304FFF7";
        StringBuilder sb = new StringBuilder(s);
        s = sb.substring(0,4);
        System.out.println(s);
        byte[] b = Tool.hexStringToBytes(s);
        System.out.println(b);
        String s2 = Tool.bytesToHexString(b, b.length);
        System.out.println(s2);
    }

}
