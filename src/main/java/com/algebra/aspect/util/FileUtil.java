package com.algebra.aspect.util;

import java.io.*;

/**
 * @author al
 * @date 2019/6/14 13:56
 * @description
 */
public class FileUtil {

    /**
     * 显示输入流中还剩的字节数
     */
    private static void showAvailableBytes(InputStream in) {
        try {
            System.out.println("当前字节输入流中的字节数为:" + in.available());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        File file = new File("E:\\tempCache\\users.data");

        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(file);
            showAvailableBytes(inputStream);

            byte[] tempByte = new byte[1024];
            int len = 0;
            while ((len = inputStream.read()) != -1) {
                System.out.write(tempByte,0,len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(inputStream != null){
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

}
