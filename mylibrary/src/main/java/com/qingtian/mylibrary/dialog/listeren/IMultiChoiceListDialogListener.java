package com.qingtian.mylibrary.dialog.listeren;

/**
 *  in modes: CHOICE_MODE_MULTIPLE
 * 这个接口用于实现多选的回调  可以在activity或者fragmnet中去实现此接口
 */
public interface IMultiChoiceListDialogListener {

     void onListItemsSelected(CharSequence[] values, int[] selectedPositions, int requestCode);
}
