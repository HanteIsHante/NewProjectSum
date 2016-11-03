package com.example.hante.newprojectsum.meterialdesign.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.hante.newprojectsum.R;
import com.example.hante.newprojectsum.meterialdesign.bean.ZhihuArticle;

import java.util.ArrayList;
import java.util.List;

/**
 * Zhihu Adapter 适配器
 */

public class ZhihuRecyclerViewAdapter extends RecyclerView.Adapter<ZhihuRecyclerViewAdapter
        .ZhihuViewHolder>{
    private static final String TAG = "ZhihuRecyclerViewAdapte";
        private Context mContext;
        private ArrayList<ZhihuArticle.StoriesBean> mStoriesListZhihu;
        private ArrayList<ZhihuArticle.TopStoriesBean> mTopStoriesZhihu;
        private onItemClickListener mOnItemClickListener;
    public ZhihuRecyclerViewAdapter (Context mContext, ArrayList<ZhihuArticle.StoriesBean> mStoriesListZhihu,
                                     ArrayList<ZhihuArticle.TopStoriesBean> mTopStoriesZhihu) {
        this.mContext = mContext;
        this.mStoriesListZhihu = mStoriesListZhihu;
        this.mTopStoriesZhihu = mTopStoriesZhihu;
    }

    @Override
    public ZhihuRecyclerViewAdapter.ZhihuViewHolder onCreateViewHolder (ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.md_recyclerview_item, parent, false);
        return new ZhihuViewHolder(view);
    }

    @Override
    public void onBindViewHolder (ZhihuRecyclerViewAdapter.ZhihuViewHolder holder, final int position) {

// 1
        if(mStoriesListZhihu.size() != 0){
            List<String> mImages = mStoriesListZhihu.get(position).getMImages();
            for(int i = 0; i<mImages.size();i++){
                Glide.with(mContext).load(mImages.get(i))
                        .into(holder.mImg);
            }
            holder.mDesc.setText(mStoriesListZhihu.get(position).getMTitle());
        }
// 2
        if(mTopStoriesZhihu.size() != 0){
            Glide.with(mContext).load(mTopStoriesZhihu.get(position).getMImage())
                    .into(holder.mImg);
            holder.mDesc.setText(mTopStoriesZhihu.get(position).getMTitle());
        }
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
        void onItemClick(View view,int position);
    }

    public void setOnItemClickListener(onItemClickListener onItemClickListener){
        this.mOnItemClickListener = onItemClickListener;
    }
    @Override
    public int getItemCount () {
        Log.d(TAG, "数量: Sto" + mStoriesListZhihu.size() + " ; Top"+ mTopStoriesZhihu.size
                ());
        return (mStoriesListZhihu.size() + mTopStoriesZhihu.size());
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
