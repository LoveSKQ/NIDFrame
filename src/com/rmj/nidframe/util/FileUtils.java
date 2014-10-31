package com.rmj.nidframe.util;

import android.os.Environment;

import java.io.*;

/**
 * Created by G11 on 2014/10/31.
 */
public class FileUtils {

    /**
     * 初始化目录
     * @return
     */
    public static void initPath(String path) {
        File _root = new File(path);
        if (!_root.exists()) {
            _root.mkdirs();
            _root.canRead();
            _root.canWrite();
        }
    }

    /**
     * 获得sd卡路径
     * @return
     */
    public static String getSDCardPath() {
        File sdDir = null;
        boolean sdCardExist = Environment.getExternalStorageState().equals(
                android.os.Environment.MEDIA_MOUNTED); // 判断sd卡是否存在
        if (sdCardExist) {
            sdDir = Environment.getExternalStorageDirectory();// 获取跟目录
        } else {
            sdDir = Environment.getDataDirectory();// 获取手机根目录

        }
        return sdDir.toString();

    }

    /**
     * 获得文件名
     * @param path
     * @return
     */
    public static String getFileName(String path) {

        int start = path.lastIndexOf("/");
        int end = path.lastIndexOf(".");
        if (start != -1 && end != -1) {
            return path.substring(start + 1, end);
        } else {
            return null;
        }

    }

    /**
     * 将一个InputStream里面的数据写入到SD卡中
     */
    public File streamToFile(String path, InputStream input) {
        File file = null;
        OutputStream output = null;
        try {
            file = createFile(path);
            output = new FileOutputStream(file);
            byte buffer[] = new byte[4 * 1024];
            while ((input.read(buffer)) != -1) {
                output.write(buffer);
            }
            output.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                output.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return file;
    }

    /**
     * 创建文件
     *
     * @throws IOException
     */
    public static File createFile(String path) throws IOException {
        File file = new File(path);
        file.createNewFile();
        return file;
    }

    /**
     * 创建目录
     *
     * @param path
     */
    public static File createDir(String path) {
        File dir = new File(path);
        dir.mkdir();
        return dir;
    }

    /**
     * 判断文件夹或文件是否存在
     */
    public static boolean isFileExist(String path) {
        File file = new File(path);
        return file.exists();
    }

    /**
     * 删除指定目录或文件
     */
    public static void deleteFiles(File file) {
        if (file.exists()) {
            if (file.isDirectory()) {
                File[] _files = file.listFiles();
                for (int i=0;i<_files.length;i++) {
                    deleteFiles(_files[i]);
                }
            }
            file.delete();
        }
    }

}
