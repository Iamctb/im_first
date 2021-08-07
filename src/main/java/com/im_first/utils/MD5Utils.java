package com.im_first.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

//MD5加密工具类
public class MD5Utils {

    public static String getPwd(String pwd){
        try{
            //创建加密对象
            MessageDigest digest = MessageDigest.getInstance("md5");
            //调用加密对象的方法，加密动作已经完成
            byte[] bs = digest.digest(pwd.getBytes());
            //对加密后的结果进行优化
            //Step 1. 将结果全部转换为正数
            String hexString = "";
            for(byte b : bs){
                int temp = b & 255;
                if(temp < 16 && temp >= 0){
                    hexString = hexString + "0" + Integer.toHexString(temp);
                }else{
                    hexString = hexString + Integer.toHexString(temp);
                }
            }
            return hexString;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }

}
