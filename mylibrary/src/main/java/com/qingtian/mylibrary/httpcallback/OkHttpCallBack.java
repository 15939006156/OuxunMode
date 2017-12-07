package com.qingtian.mylibrary.httpcallback;

import com.zhy.http.okhttp.callback.Callback;

import java.io.IOException;

import okhttp3.Response;

/**
 * 改编者: lkp
 * 时间: 2017/7/21 0021 上午 11:46
 * 功能描述: 自定义回调的接口  减少不必要的回调方法
 * 备注:
 */
public abstract class OkHttpCallBack extends Callback<String> {
    @Override
    public String parseNetworkResponse(Response response, int id) throws IOException {
        return response.body().string();
    }

}
