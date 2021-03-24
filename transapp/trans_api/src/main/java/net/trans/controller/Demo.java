package net.trans.controller;

import java.io.File;

/**
 * @Author Cx
 * @Date 2021-02-20
 * @Version 1.0
 */
public class Demo {
    public static void main(String[] args)throws Exception {

        File file = new File("src\\main\\resources\\static\\id_key_rsa.pub");
        System.out.println();
        File file2 = new File("C:\\Users\\ASUS\\Desktop\\trans\\Token\\id_key_rsa");

        System.out.println(file.toPath());
        System.out.println(file.exists());
        System.out.println(file2.getAbsolutePath());
//        System.out.println(file.createNewFile());
    }
}
