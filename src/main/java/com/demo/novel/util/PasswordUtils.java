package com.demo.novel.util;

import com.demo.novel.entity.UserInfo;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

public class PasswordUtils {
    private String algorithmName = "md5";
    private int hashIterations = 2;

    public void encryptPassword(UserInfo user) {
        String newPassword = new SimpleHash(algorithmName, user.getPwd(), ByteSource.Util.bytes(user.getUsername()), hashIterations).toHex();
        user.setPwd(newPassword);

    }
    public static void main(String[] args) {
        PasswordUtils passwordHelper = new PasswordUtils();
        UserInfo user = new UserInfo();
        user.setUsername("user1");
        user.setPwd("123456");
        passwordHelper.encryptPassword(user);
        System.out.println(user.getPwd());
    }
}
