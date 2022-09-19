package cn.yunhe.demo;

import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.SimpleHash;

public class TestMd5 {
    public static void main(String[] args) {
        //MD5加密
        String password="123";
        String salt="yunhe"; //盐
        String md5 = new Md5Hash(password).toString();
        System.out.println("MD5不加盐:"+md5);
        System.out.println("-----------");
        String md5Salt = new Md5Hash(password,salt).toString();
        System.out.println("MD5加盐:"+md5Salt);
        System.out.println("-----------");
        String md5Salt3 = new Md5Hash(password,salt,3).toString();
        System.out.println("MD5加盐，并散列运算3次:"+md5Salt3);

        SimpleHash simpleHash1 = new SimpleHash("MD5", password, salt, 3);
        System.out.println(simpleHash1);


        System.out.println("------");
        String salt2="zhangsan9fe270f8-f682-4126-92aa-604d945c95f1";
        String md5Salt_pass = new Md5Hash("123",salt2,3).toString();
        System.out.println("MD5加盐，并散列运算3次:"+md5Salt_pass);


    }
}
