package com.example.hante.newprojectsum.meterialdesign.adapter;

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
import com.example.hante.newprojectsum.meterialdesign.bean.ZhihuArt;

import java.util.ArrayList;

/**
 * Zhihu Adapter 适配器
 */

public class ZhihuRecyclerViewAdapterReset extends RecyclerView.Adapter<ZhihuRecyclerViewAdapterReset
        .ZhihuViewHolder>{
    private static final String TAG = "ZhihuRecyclerViewAdapte";
        private Context mContext;
        private ArrayList<ZhihuArt> mStoriesListZhihu;
        private onItemClickListener mOnItemClickListener;

    public ZhihuRecyclerViewAdapterReset (Context mContext, ArrayList<ZhihuArt> mStoriesListZhihu) {
        this.mContext = mContext;
        this.mStoriesListZhihu = mStoriesListZhihu;
    }

    @Override
    public ZhihuRecyclerViewAdapterReset.ZhihuViewHolder onCreateViewHolder (ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.md_recyclerview_item, parent, false);
        return new ZhihuViewHolder(view);
    }

    @Override
    public void onBindViewHolder (ZhihuRecyclerViewAdapterReset.ZhihuViewHolder holder, final int position) {

            Glide.with(mContext).load(mStoriesListZhihu.get(position).getmImage())
                        .into(holder.mImg);
            holder.mDesc.setText(mStoriesListZhihu.get(position).getmTitle());

             holder.mLinearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick (View view) {
                    if(mOnItemClickListener != null){
                        mOnItemClickListener.onItemClick(view,position);
                    }
                }
        });
    }

    public interface onItemClickListener{
        void onItemClick (View view, int position);
    }

    public void setOnItemClickListener(onItemClickListener onItemClickListener){
        this.mOnItemClickListener = onItemClickListener;
    }
    @Override
    public int getItemCount () {

        return mStoriesListZhihu.size();
    }

    public class ZhihuViewHolder extends RecyclerView.ViewHolder {
        private LinearLayout mLinearLayout;
        private ImageView mImg;
        private TextView mDesc;
        public ZhihuViewHolder (View itemView) {
            super(itemView);
            mLinearLayout = (LinearLayout)itemView.findViewById(R.id.card_item);
            mImg = (ImageView)itemView.findViewById(R.id.zhihu_img);
            mDesc = (TextView) itemView.findViewById(R.id.zhihu_title);
        }
    }
}
