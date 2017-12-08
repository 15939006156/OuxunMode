package com.ouxun.ouxunmode.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.ouxun.ouxunmode.R;
import com.ouxun.ouxunmode.base.BaseActivity;
import com.qingtian.mylibrary.utils.MyActivityStackUtils;

import java.util.Timer;
import java.util.TimerTask;

/**
 * 改编者: lkp
 * 时间: 2017/12/1 0001 下午 3:51
 * 功能描述: 这是主页
 * 备注:
 */
public class MainActivity extends BaseActivity {

    private FragmentManager manager;
    private Fragment[] fragments;
    private ImageView[] images;
    private TextView[] texts;
    private int[] imageResouse0;
    private int[] imageResouse1;
    private int temp = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    protected void initView() {

        manager = getSupportFragmentManager();
        fragments = new Fragment[]{manager.findFragmentById(R.id.main_fragment1)
                , manager.findFragmentById(R.id.main_fragment2)
                , manager.findFragmentById(R.id.main_fragment3)
                , manager.findFragmentById(R.id.main_fragment4)
                , manager.findFragmentById(R.id.main_fragment5)};

        images = new ImageView[]{(ImageView) findViewById(R.id.img_maintab_1)
                , (ImageView) findViewById(R.id.img_maintab_2)
                , (ImageView) findViewById(R.id.img_maintab_3)
                , (ImageView) findViewById(R.id.img_maintab_4)
                , (ImageView) findViewById(R.id.img_maintab_5)};
        //底部导航字体
        texts = new TextView[]{(TextView) findViewById(R.id.text_maintab_1)
                , (TextView) findViewById(R.id.text_maintab_2)
                , (TextView) findViewById(R.id.text_maintab_3)
                , (TextView) findViewById(R.id.text_maintab_4)
                , (TextView) findViewById(R.id.text_maintab_5)};

        imageResouse0 = new int[]{R.mipmap.maintab_1_uncheck
                , R.mipmap.maintab_2_uncheck
                , R.mipmap.maintab_3_uncheck
                , R.mipmap.maintab_4_uncheck
                , R.mipmap.maintab_5_uncheck};

        imageResouse1 = new int[]{R.mipmap.maintab_1_check
                , R.mipmap.maintab_2_check
                , R.mipmap.maintab_3_check
                , R.mipmap.maintab_4_check
                , R.mipmap.maintab_5_check};

        FragmentTransaction transaction = manager.beginTransaction();
        for (int i = 1; i < 5; i++) {
            transaction.hide(fragments[i]);
        }
        transaction.show(fragments[0]);
        transaction.commit();

        int intent_type = getIntent().getIntExtra("Intent_type", -1);
        if (intent_type != -1) {
            changeLayout(intent_type);
        }
    }


    @Override
    public void initHeaderLayout() {

    }


    public void LayoutOnclickMethod(View layout) {
        switch (layout.getId()) {
            case R.id.layout1:
                changeLayout(0);
                break;
            case R.id.layout2:
                changeLayout(1);
                break;
            case R.id.layout3:
                changeLayout(2);
                break;
            case R.id.layout4:
                changeLayout(3);
                break;
            case R.id.layout5:
                changeLayout(4);
                break;
        }
    }


    public void changeLayout(int position) {
        if (position != temp) {
            manager.beginTransaction().hide(fragments[temp]).show(fragments[position]).commit();
            images[temp].setImageResource(imageResouse0[temp]);
            images[position].setImageResource(imageResouse1[position]);
            texts[temp].setTextColor(getResources().getColor(R.color.main_text999));
            texts[position].setTextColor(getResources().getColor(R.color.tab_text_press));
            temp = position;
        }
    }

    //双击退出
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exitBy2Click();
        }
        return super.onKeyDown(-1, event);
    }


    /**
     * 双击退出函数
     */
    private Boolean isExit = false;

    private void exitBy2Click() {
        Timer tExit = null;
        if (isExit == false) {
            isExit = true; // 准备退出
            Toast.makeText(this, "再按一次退出"+getResources().getString(R.string.app_name), Toast.LENGTH_SHORT).show();
            tExit = new Timer();
            tExit.schedule(new TimerTask() {
                public void run() {
                    isExit = false; // 取消退出
                }
            }, 2000);
        } else {
//清除缓存   SPUtils.clear(MainActivity.this);
            MyActivityStackUtils.getInstance().exitApp();
            System.exit(0);
        }
    }
}
