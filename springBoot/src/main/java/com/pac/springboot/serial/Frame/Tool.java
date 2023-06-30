package com.pac.springboot.serial.Frame;


import lombok.extern.slf4j.Slf4j;

import java.math.BigInteger;
import java.util.ArrayList;
@Slf4j
public class Tool
{
    public static String leftZero(int Byte, long Data){
        String Hex = Long.toHexString(Data);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < Byte; i++) {
            sb.append("00");
        }
        int sb_len = sb.length();
        int hex_len = Hex.length();
        sb.replace(sb_len-hex_len, sb_len, Hex);
        return sb.toString();
    }



    public static int[] calculateCRC( String frame) {
        int crc = 0xFFFF;
        int j = 0;
        ArrayList<Integer> data = new ArrayList<>();
        for (int i = 0 ; i < frame.length()-1; i+=2){
            data.add(Integer.parseInt(frame.substring(i, i+2)));
        }
        for (int k = 0 ; k < data.size(); k++) {
            int b = data.get(k);
            crc ^= b & 0xFF;
            for (int i = 0; i < 8; i++) {

                if ((crc & 0x0001) != 0) {
                    crc >>= 1;
                    crc ^= 0xA001;
                } else {
                    crc >>= 1;
                }
            }
        }
        return new int[]{(int) (crc & 0x00FF), (int) (crc & 0xFF00 >> 8)};
    }

    public static String bytesToHexString(byte[] bytes, int length) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sb.append(String.format("%02X", bytes[i]));
        }
        return sb.toString();
    }

    public static byte[] hexStringToBytes(String hexString) {
        if (hexString == null || hexString.equals("")) {
            return null;
        }
        hexString = hexString.toUpperCase();
        int length = hexString.length() / 2;
        char[] hexChars = hexString.toCharArray();
        byte[] d = new byte[length];
        for (int i = 0; i < length; i++) {
            int pos = i * 2;
            d[i] = (byte) (charToByte(hexChars[pos]) << 4 | charToByte(hexChars[pos + 1]));
        }
        return d;
    }
    public static int bytesToInt(byte[] a){
        int ans=0;
        for(int i=0;i<4;i++){
            ans<<=8;
            //ans|=(a[3-i]&0xff);
            /* 这种写法会看起来更加清楚一些：*/
            int tmp=a[3-i];
            tmp=tmp&0x000000ff;
            ans|=tmp;
        }
        return ans;
    }

    /**
     * 将16进制转换为二进制
     *
     * @param hexString
     * @return
     */
    public static String hexString2binaryString(String hexString,int bit) {
        //16进制转10进制
        BigInteger sint = new BigInteger(hexString, 16);
        //10进制转2进制
        String s;
        String result = sint.toString(2);
        log.error("奴家一切仰仗将军啦"+result);
        StringBuilder sb = new StringBuilder();
        if (result.length() < bit){
            int x = bit-result.length();
            for (int i = 0 ; i < x ; i++){
                sb.append("0");
            }

        }
        sb.append(result);
        return  sb.toString();
    }

    public static String bin2hex(String input) {
        StringBuilder sb = new StringBuilder();
        int len = input.length();
        //System.out.println("原数据长度：" + (len / 8) + "字节");

        for (int i = 0; i < len / 4; i++){
            //每4个二进制位转换为1个十六进制位
            String temp = input.substring(i * 4, (i + 1) * 4);
            int tempInt = Integer.parseInt(temp, 2);
            String tempHex = Integer.toHexString(tempInt).toUpperCase();
            sb.append(tempHex);
        }

        return sb.toString();
    }

    public static String decTObin(String decade){
        BigInteger sint = new BigInteger(decade,10);
        return sint.toString(2);
    }
    public static String hexTOdec(String hex){
        BigInteger sint = new BigInteger(hex,16);
        return sint.toString(10);
    }


    private static byte charToByte(char c) {
        return (byte) "0123456789ABCDEF".indexOf(c);
    }
    public static void main(String[] args) {
        long l = 38;
        //String s = leftZero(2, l);
        //Long L = new Long(s);
        int [] t = calculateCRC("010203");
        String S= hexString2binaryString("ff00",16);
StringBuilder sb = new StringBuilder();
boolean[] booleans = new boolean[16];
booleans[0] = true;
booleans[1] = false;

        for (int i = 0 ; i< 16; i++){
            if (booleans[i]){
                sb.append("1");
            }else{
                sb.append("0");
            }
        }
        BigInteger sint = new BigInteger("34",10);
        String result = sint.toString(2);
        System.out.println(hexTOdec("10"));
        System.out.println(decTObin("5"));
        System.out.println("22"+result);
        System.out.println(sb.toString());
        System.out.println(" "+t[0]+t[1]+S);
        StringBuilder sb2 = new StringBuilder("01");
        System.out.println(sb2.substring(1,2).toString());
    }

}
