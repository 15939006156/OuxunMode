package com.qingtian.mylibrary.utils;

import android.content.Context;
import android.content.res.AssetManager;
import android.text.TextUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;

/**
 * 获取文件大小及删除文件的缓存用
 */
public class FileManager {


    /**
     * 获取assets下的json文件
     * @param fileName
     * @param context
     * @return
     */
    public static String getAssetsJson(String fileName,Context context) {
        //将json数据变成字符串
        StringBuilder stringBuilder = new StringBuilder();
        try {
            //获取assets资源管理器
            AssetManager assetManager = context.getAssets();
            //通过管理器打开文件并读取
            BufferedReader bf = new BufferedReader(new InputStreamReader(
                    assetManager.open(fileName)));
            String line;
            while ((line = bf.readLine()) != null) {
                stringBuilder.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }

    /**
     * 获取文件夹大小
     * @param file File实例
     * @return long 单位为M
     * @throws Exception
     */
    public static float getFolderSize(File file)throws Exception{
        float size = 0;
        File[] fileList = file.listFiles();
        for (int i = 0; i < fileList.length; i++)
        {
            if (fileList[i].isDirectory())
            {
                size = size + getFolderSize(fileList[i]);
            } else
            {
                size = size + fileList[i].length();
            }
        }

        float file_size = size/1048576f;
        BigDecimal b = new BigDecimal(file_size);

        return b.setScale(2, BigDecimal.ROUND_HALF_UP).floatValue();
    }

    /**
     * 删除指定目录下文件及目录
     *
     * @param deleteThisPath
     * @param filePath
     * @return
     */
    public static void deleteFolderFile(String filePath,boolean deleteThisPath)
            throws IOException {
        if (!TextUtils.isEmpty(filePath)) {
            File file = new File(filePath);

            if (file.isDirectory()) {// 处理目录
                File[] files = file.listFiles();
                int lent = files.length;
                if(lent == 0){
                    if(deleteThisPath){
                        file.delete();
                    }
                }else{
                    for (int i = 0; i < lent; i++) {
                        deleteFolderFile(files[i].getAbsolutePath(),true);
                    }
                }
            }else{
                file.delete();
            }
        }
    }

}
