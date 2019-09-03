package com.example.newsrecommendation.util.md5;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * @Description   md5加密 用于数据库密码和输入密码比对
 * @Author 11103882
 * @Date 2019/9/2
 * @Version 1.0
 */
public class MD5util {

//    public static void main(String[] args) {
//
//        System.out.println( inputPassIntoDbPass("123456"));
//    }
    /*password = g_passsword_salt.charAt(5)+g_passsword_salt.charAt(4)+pass+g_passsword_salt.charAt(3)+g_passsword_salt.charAt(2)+pass+g_passsword_salt.charAt(1);*/
    private static final String SALT = "1s2a3l4t";

    public static String inputPassIntoDbPass(String inputPassword){
        String DbPassword =""+SALT.charAt(5)+SALT.charAt(4)+inputPassword+SALT.charAt(3)+SALT.charAt(2)+inputPassword+SALT.charAt(1);
        return md5(DbPassword);
    }

    public static String md5(String password){
        return DigestUtils.md5Hex(password);
    }
}
