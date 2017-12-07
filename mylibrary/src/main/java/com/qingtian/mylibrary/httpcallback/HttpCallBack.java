package com.qingtian.mylibrary.httpcallback;

/**
 * 改编者: lkp
 * 时间: 2017/7/21 0021 上午 11:42
 * 功能描述: 自定义回调的接口
 * 备注:
 */
public abstract class HttpCallBack {

    /**
     * Http请求成功时回调
     *
     * @param json HttpRequest返回信息
     */
    public void onSuccess(String json) {
    }


    /**
     * Http请求失败时回调
     *
     * @param errorcde 错误码
     */
    public void onFailure(int errorcde, String json) {

    }

    /**
     * 进度回调，仅支持Download时使用
     *
     * @param current 当前进度
     */
    public void onLoading(Float current) {
    }

}
