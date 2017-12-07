package com.ouxun.ouxunmode.base;

import android.app.Application;
import android.content.Context;

import com.mob.MobSDK;
import com.mob.tools.proguard.ProtectedMemberKeeper;
import com.uuzuche.lib_zxing.activity.ZXingLibrary;

import static com.mob.MobSDK.getAppSecret;
import static com.mob.MobSDK.getAppkey;

/**
 * Created：晴天 on 2016/11/2 0002 20:12
 * 描述：这是一个Application的全局变量 用于初始化一些操作
 * qq:425116228
 */
public class BaseApplication extends Application implements ProtectedMemberKeeper {

    public static BaseApplication instance;

    public static BaseApplication getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        ZXingLibrary.initDisplayOpinion(this);//二维码扫描
//        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
//        StrictMode.setVmPolicy(builder.build());
        MobSDK.init(this, getAppkey(), getAppSecret());//初始化sharesdk
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);

    }





}
