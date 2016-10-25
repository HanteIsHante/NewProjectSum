package com.example.hante.newprojectsum.okhttpactivity;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.hante.newprojectsum.R;
import com.example.hante.newprojectsum.okhttpactivity.model.UserInfo;

import java.util.ArrayList;

/**
 * okhttp adapter
 */

public class OkHttpRecyclerAdapter extends RecyclerView.Adapter<OkHttpRecyclerAdapter
        .MyViewHolder>{

    private ArrayList<UserInfo> mUserInfoData;
    private Context mContext;
    onItemClickListener mOnItemClickListener;
    public OkHttpRecyclerAdapter (ArrayList<UserInfo> mUserInfoData, Context mContext) {
        this.mUserInfoData = mUserInfoData;
        this.mContext = mContext;
    }

    @Override
    public MyViewHolder onCreateViewHolder (ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(mContext).inflate(R.layout.activity_ok_http_item, parent, false);
        return new MyViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder (final MyViewHolder holder, int position) {
        if(mOnItemClickListener != null){
            holder.mImageIcon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick (View view) {
                    int adapterPosition = holder.getAdapterPosition();
                    mOnItemClickListener.onClickListener(view,adapterPosition);
                }
            });
        }
    }

    @Override
    public int getItemCount () {
        return mUserInfoData.size();
    }


    interface  onItemClickListener {
        void onClickListener(View view,int position);
    }
    void setOnItemClickListener(onItemClickListener onItemClickListener){
        this.mOnItemClickListener = onItemClickListener;
    }
    class MyViewHolder extends RecyclerView.ViewHolder {

        private ImageView mImageIcon;
        public MyViewHolder (View itemView) {
            super(itemView);
        }
    }
}
