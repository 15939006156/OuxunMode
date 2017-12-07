package com.ouxun.ouxunmode.base;
import android.animation.Animator;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * Created by Administrator on 2017/8/7 0007.
 * 编写人：li
 * 功能描述：这是RecyclerView的基类 所有类继承此类实现其方法即可
 *  MyImageLoaderUtils.loaderFromDrawable(item.img, (ImageView) holder.getView(R.id.img_fragment1_gv));
 */
public abstract class BaseRecyclerAdapter<T> extends BaseQuickAdapter<T, BaseViewHolder> {

    private final List<T> data;
    public BaseRecyclerAdapter(@LayoutRes int layoutResId, @Nullable List<T> data) {
        super(layoutResId, data);
        isFirstOnly(false);//判断是否重复动画
//        setPreLoadNumber(2);// 当列表滑动到倒数第N个Item的时候(默认是1)回调onLoadMoreRequested方法
        this.data=data;
    }


    /**
     * 如果需要在点击事件中获取其他子控件可以使用
     * 在条目点击回调时有时如：setOnItemChildClickListener
     */
    @Nullable
    public View getViewByPosition(RecyclerView recyclerView, int position, @IdRes int viewId) {
        if (recyclerView == null) {
            return null;
        }
        BaseViewHolder viewHolder = (BaseViewHolder) recyclerView.findViewHolderForLayoutPosition(position);
        if (viewHolder == null) {
            return null;
        }
        return viewHolder.getView(viewId);
    }

    //第一屏显示动画
    @Override
    protected void startAnim(Animator anim, int index) {
        super.startAnim(anim, index);
        if (index <8 )
            anim.setStartDelay(index * 100);
    }

    @Override
    protected void convert(BaseViewHolder holder, T itemdata) {
        int position = holder.getLayoutPosition();//获取对应的position
        getRecyclerView(holder, itemdata,position);
    }

    protected abstract void getRecyclerView(BaseViewHolder holder, T itemData, int position);

}
