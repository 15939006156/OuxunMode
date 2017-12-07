package com.qingtian.mylibrary.popwindow;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.qingtian.mylibrary.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 改编者: lkp
 * 时间: 2017/6/15 0015 上午 11:27
 * 功能描述: 上面的pop王者农药adapter
 * 备注:
 */
public class PopupAdapter extends RecyclerView.Adapter<PopupAdapter.MyViewHolder> {
    private Context mContext;
    private List<String> list;
    private MypopwindowOnclickListener myItemClickListener;

    public PopupAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public PopupAdapter(Context mContext, ArrayList<String> ticketlist) {
        this.mContext = mContext;
        list=ticketlist;
    }

    public void setOnItemClickListener(MypopwindowOnclickListener listener) {
        this.myItemClickListener = listener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.popup_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {

        holder.choice_text.setText(list.get(position));
        holder.choice_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (myItemClickListener != null) {
                    myItemClickListener.onItemClick(v, position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView choice_text;

        public MyViewHolder(final View itemView) {
            super(itemView);
            choice_text = (TextView) itemView.findViewById(R.id.choice_text);
        }
    }
}
