package com.algebra.aspect.controller.file;

import lombok.extern.slf4j.Slf4j;

import java.io.*;

/**
 * @author al
 * @date 2019/5/28 16:44
 * @description 文件操作工具类
 */
@Slf4j
public class FileTooUtil {

    /**
     * 验证字符串是否为正确路径名的正则表达式
     */
    private static String matches = "[A-Za-z0-9_]:[^?\"><*]*";

    /**
     * 通过 sPath.matches(matches) 方法的返回值判断是否正确
     * sPath 为上传的文件路径字符串
     */
    private static boolean flag = false;

    /**
     * 文件
     */
    private static File file;

    /**
     * 判断文件夹是否存在
     * @param file 文件路径
     */
    public static void createFileDir(File file) {
        if (file.exists()) {
            if (file.isDirectory()) {
                log.info("dir exists");
            } else {
                log.info("the same name file exists, can not create dir");
            }
        } else {
            log.info("dir not exists, create it ...");
            file.mkdirs();
        }

    }



    /**
     * 根据路径删除指定的目录或文件，无论存在与否
     *
     * @param deletePath
     * @return
     */
    public static boolean deleteFolder(String deletePath) {
        flag = false;
        if (deletePath.matches(matches)) {
            file = new File(deletePath);
            // 判断目录或文件是否存在
            if (!file.exists()) {
                // 不存在返回 false
                return flag;
            } else {
                // 判断是否为文件
                if (file.isFile()) {
                    // 为文件时调用删除文件方法
                    return deleteFile(deletePath);
                } else {
                    // 为目录时调用删除目录方法
                    return deleteDirectory(deletePath);
                }
            }
        } else {
            log.info("要传入正确路径！");
            return false;
        }
    }

    /**
     * 删除单个文件
     *
     * @param filePath 文件路径
     * @return
     */
    public static boolean deleteFile(String filePath) {
        flag = false;
        file = new File(filePath);
        // 路径为文件且不为空则进行删除
        if (file.isFile() && file.exists()) {
            file.delete();// 文件删除
            flag = true;
        }
        return flag;
    }

    /**
     * 删除目录（文件夹）以及目录下的文件
     *
     * @param dirPath
     * @return
     */
    public static boolean deleteDirectory(String dirPath) {
        // 如果sPath不以文件分隔符结尾，自动添加文件分隔符
        if (!dirPath.endsWith(File.separator)) {
            dirPath = dirPath + File.separator;
        }
        File dirFile = new File(dirPath);
        // 如果dir对应的文件不存在，或者不是一个目录，则退出
        if (!dirFile.exists() || !dirFile.isDirectory()) {
            return false;
        }
        flag = true;
        // 获得传入路径下的所有文件
        File[] files = dirFile.listFiles();
        // 循环遍历删除文件夹下的所有文件(包括子目录)
        if (files != null) {
            for (File file1 : files) {
                if (file1.isFile()) {
                    // 删除子文件
                    flag = deleteFile(file1.getAbsolutePath());
                    log.info(file1.getAbsolutePath() + " 删除成功");
                    if (!flag) {
                        break;// 如果删除失败，则跳出
                    }
                } else {// 运用递归，删除子目录
                    flag = deleteDirectory(file1.getAbsolutePath());
                    if (!flag) {
                        break;// 如果删除失败，则跳出
                    }
                }
            }
        }

        if (!flag) {
            return false;
        }
        // 删除当前目录
        return dirFile.delete();
    }

    public static void closeQuietly(InputStream input) {
        closeQuietly((Closeable)input);
    }
    public static void closeQuietly(OutputStream output) {
        closeQuietly((Closeable)output);
    }
    public static void closeQuietly(Closeable closeable) {
        try {
            if (closeable != null) {
                closeable.close();
            }
        } catch (IOException var2) {
            var2.printStackTrace();
        }
    }


}
