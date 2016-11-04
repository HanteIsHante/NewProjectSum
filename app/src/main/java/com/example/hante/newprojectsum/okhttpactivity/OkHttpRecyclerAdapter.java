package com.example.hante.newprojectsum.okhttpactivity;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.hante.newprojectsum.R;
import com.example.hante.newprojectsum.okhttpactivity.model.ZhiHuInfo;

import java.util.ArrayList;

/**
 * okhttp adapter
 */

public class OkHttpRecyclerAdapter extends RecyclerView.Adapter<OkHttpRecyclerAdapter
        .MyViewHolder>{

    private ArrayList<ZhiHuInfo> mUserInfoData;
    private Context mContext;
    onItemClickListener mOnItemClickListener;
    public OkHttpRecyclerAdapter (ArrayList<ZhiHuInfo> mZhiHuInfoData, Context mContext) {
        this.mUserInfoData = mZhiHuInfoData;
        this.mContext = mContext;
    }

    @Override
    public MyViewHolder onCreateViewHolder (ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(mContext).inflate(R.layout.activity_ok_http_item, parent, false);
        return new MyViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder (final MyViewHolder holder, int position) {

        Glide.with(mContext).load(mUserInfoData.get(position).getmImg())
                .into(holder.mImageIcon);
        holder.mTitle.setText(mUserInfoData.get(position).getmTitle());
        if(mOnItemClickListener != null){
            holder.mLinearLayout.setOnClickListener(new View.OnClickListener() {
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
        private LinearLayout mLinearLayout;
        private TextView mTitle;
        private ImageView mImageIcon;
        public MyViewHolder (View itemView) {
            super(itemView);
            mLinearLayout = (LinearLayout)itemView.findViewById(R.id.card_item);
            mTitle = (TextView)itemView.findViewById(R.id.zhihu_title);
            mImageIcon = (ImageView) itemView.findViewById(R.id.zhihu_img);
        }
    }
}
