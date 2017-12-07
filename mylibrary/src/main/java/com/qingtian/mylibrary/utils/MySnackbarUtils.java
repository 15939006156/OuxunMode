package com.qingtian.mylibrary.utils;
import android.support.design.widget.Snackbar;
import android.view.View;

/**
 * 这是一个带扩展行的Snackbar
 * 不带icon的
 * SnackBarUtils.makeShort(v, "TEXT").show();
 * SnackBarUtils.makeShort(v, "TEXT").danger();
 * SnackBarUtils.makeShort(v, "TEXT").info();
 * SnackBarUtils.makeShort(v, "TEXT").warning();
 *
 * 带icon的
 *      SnackBarUtils.makeShort(v, "TEXT").info("action", new View.OnClickListener() {
 *
 *      @Override public void onClick(View v) {
 *      SnackBarUtils.makeShort(v, "TEXT").show();
 *
 *          }
 *      });
 */

public class MySnackbarUtils {

    private static final int color_danger = 0xffa94442;//警告提醒
    private static final int color_success = 0xff3c763d;//成功提醒
    private static final int color_warning = 0xff8a6d3b;//错误提醒
    private static final int action_color = 0xffCDC5BF;//按钮背景

    private Snackbar mSnackbar;

    private MySnackbarUtils(Snackbar snackbar) {
        mSnackbar = snackbar;
    }



    public static MySnackbarUtils makeShort(View view, String text) {
        Snackbar snackbar = Snackbar.make(view, text, Snackbar.LENGTH_SHORT);
        return new MySnackbarUtils(snackbar);
    }


    public static MySnackbarUtils makeLong(View view, String text) {
        Snackbar snackbar = Snackbar.make(view, text, Snackbar.LENGTH_LONG);
        return new MySnackbarUtils(snackbar);
    }


    private View getSnackBarLayout(Snackbar snackbar) {
        if (snackbar != null) {
            return snackbar.getView();
        }
        return null;
    }



    private Snackbar setSnackBarBackColor(int colorId) {
        View snackBarView = getSnackBarLayout(mSnackbar);
        if (snackBarView != null) {
            snackBarView.setBackgroundColor(colorId);
        }
        return mSnackbar;
    }



    public void warning() {
        setSnackBarBackColor(color_warning);
        show();
    }


    public void warning(String actionText, View.OnClickListener listener) {
        setSnackBarBackColor(color_warning);
        show(actionText, listener);
    }


    public void danger() {
        setSnackBarBackColor(color_danger);
        show();
    }

    public void danger(String actionText, View.OnClickListener listener) {
        setSnackBarBackColor(color_danger);
        show(actionText, listener);
    }

    public void success() {
        setSnackBarBackColor(color_success);
        show();
    }

    public void success(String actionText, View.OnClickListener listener) {
        setSnackBarBackColor(color_success);
        show(actionText, listener);
    }

    public void show() {
        mSnackbar.show();
    }

    public void show(String actionText, View.OnClickListener listener) {
        mSnackbar.setActionTextColor(action_color);
        mSnackbar.setAction(actionText, listener).show();
    }
}