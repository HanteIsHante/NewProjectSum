package com.example.hante.newprojectsum.recyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by handan on 2016/9/12.
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

    private LongClick longClick;
    private ShortClick shortClick;
// 定义两个接口
    public interface LongClick {
        public void onItemClick(View view ,int position);
    }
    public interface ShortClick {
        public void onItemClick(View view ,int position);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

    }

    // ViewHolder 复用
     class MyViewHolder extends RecyclerView.ViewHolder{

        TextView mTextView;
        ImageView mImageView;
        public MyViewHolder(View itemView) {
            super(itemView);

        }
    }
}
