package com.example.hante.newprojectsum.tablelayout.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.hante.newprojectsum.R;
import com.example.hante.newprojectsum.meterialdesign.bean.ZhihuArt;

import java.util.List;

/**
 *  AFragment  RecyclerView 适配器
 */

public class RecyclerVIewFragA extends RecyclerView.Adapter<RecyclerVIewFragA.FragAViewHolder>{

    private Context mContext;
    private List<ZhihuArt> mZhihuArtList;

    public RecyclerVIewFragA (Context context, List<ZhihuArt> zhihuArtList) {
        mContext = context;
        mZhihuArtList = zhihuArtList;
    }

    @Override
    public RecyclerVIewFragA.FragAViewHolder onCreateViewHolder (ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(mContext)
                .inflate(R.layout.fragment_a_item, parent, false);
        return new FragAViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder (RecyclerVIewFragA.FragAViewHolder holder, int position) {
        holder.mTitleView.setText(mZhihuArtList.get(position).getmTitle());
        Glide.with(mContext).load(mZhihuArtList.get(position).getmImage())
                .into(holder.mImageView);
    }

    @Override
    public int getItemCount () {
        return mZhihuArtList.size();
    }

    public class FragAViewHolder extends RecyclerView.ViewHolder{

        private TextView mTitleView;
        private ImageView mImageView;
        public FragAViewHolder (View itemView) {
            super(itemView);
            mImageView = (ImageView)itemView.findViewById(R.id.image_title);
            mTitleView = (TextView)itemView.findViewById(R.id.text_title);
        }
    }
}
