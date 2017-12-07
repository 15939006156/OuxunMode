package com.qingtian.mylibrary.utils;

/**
 * Created by Administrator on 2017/6/19 0019.
 * 编写人：li
 * 功能描述：这是用于String的分割和拼接用的
 */

public class MySplitAndAddUtils {
    /**
     * 两个简单的字符串的拼接
     * @param oldstr 初始值
     * @param newstr 要追加的值
     */
    public static String add(String oldstr,String newstr){
        StringBuffer sb = new StringBuffer(oldstr);
        sb.append(newstr);
        return sb.toString();
    }


    /**
     * 带标点特殊符号的追加
     * @param oldstr
     * @param sign      需要添加的符号 如， . \ ? &等
     * @param newstr
     * @return
     */
    public static String addsign(String oldstr,String sign,String newstr){
        StringBuffer sb=new StringBuffer(oldstr);
        sb.append(sign).append(newstr);
        return sb.toString();
    }


    /**
     * 截取字符串 获取最后的num位的字符
     * @param str
     * @param num
     * @return
     */
    public static String splitLastStr(String str,int num) {
        String usernamesub2 = str.substring(str.length() - num);
        return usernamesub2;
    }


    /**
     * 获取字符串 indexOf和 获取字符串lastIndexOf中间的字符串
     * @param str            要截取的字符串
     * @param indexOf        要截取的字符串开始的字符
     * @param lastIndexOf   要截取的字符串结束的字符
     *
     *用法如：  str.substring(str.indexOf("username=") + 1, str.lastIndexOf("&type"));
     */
    public static String splitStrToLast(String str,String indexOf,String lastIndexOf){
        String getStr = str.substring(str.indexOf(indexOf) + 1, str.lastIndexOf(lastIndexOf));
        return getStr;
    }

    /**
     * 用法如：  str.substring(0, 3)
     * @param str    要截取的字符串
     * @param index 要截取的字符串开始的下标
     * @param end   要截取的字符串结束的下标
     * @return
     */
    public static String splitStartTOEnd(String str,int index,int end) throws Exception {
        String substring = str.substring(index, end);
        return substring;
    }


    //  String url = result.substring(0, result.lastIndexOf("?type"));
    public static String splitStarTOEnd(String str,int index,int end) throws Exception {
        String substring = str.substring(index, end);
        return substring;
    }


    public static boolean iscontains(String str,String str2){
        if (str.contains(str2)){
            return true;
        }
        return false;
    }

}
