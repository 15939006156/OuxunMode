package com.qingtian.mylibrary.dialog.listeren;

/**
 * modes: CHOICE_MODE_NONE, CHOICE_MODE_SINGLE
 * 这个接口是用于list的单选或不选 可以在activity中进行实现此接口
 */
public interface IListDialogListener {
     void onListItemSelected(CharSequence value, int number, int requestCode);
}
