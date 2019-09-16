package com.esop.airport.utils;

import java.security.MessageDigest;

public class MD5Utils {
    /**
     * 使用md5的算法进行加密
     */

    public static String getMd5(String val) {
        char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
        try {
            byte[] btInput = val.getBytes();
            // 获得MD5摘要算法的 MessageDigest 对象
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            // 使用指定的字节更新摘要
            mdInst.update(btInput);
            // 获得密文
            byte[] md = mdInst.digest();
            // 把密文转换成十六进制的字符串形式
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) {
        String jjjj = "图片图片图片.jpg";

        String[] aa = jjjj.split("\\.");

        System.out.println(getMd5("123456"));
    }

    /**
     * 校验用户名密码是否正确
     * @param userPassword 用户手输的密码
     * @param salt 盐值
     * @param validPassword 数据库中用于salt和md5加密后的密码
     * @return
     */
    public static boolean checkPassword(String userPassword, String salt, String validPassword){

        boolean flag = false;

        String md5Password = MD5Utils.getMd5(userPassword);
        String saltPassword = MD5Utils.getMd5(md5Password+salt);

        if(StringUtils.equals(saltPassword,validPassword)){
            flag = true;
        }
        return flag;
    }
}
