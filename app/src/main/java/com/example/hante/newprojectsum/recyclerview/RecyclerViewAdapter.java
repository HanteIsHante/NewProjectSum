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


    onItemClickListener mOnItemClickListener;

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

    public interface onItemClickListener {
         void  onItemClick(View view,int position);
    }

    /**
     *
     * @param onItemClickListener adapter 调用
     */
    public void SetOnItemClicListener(final onItemClickListener onItemClickListener){
        this.mOnItemClickListener = onItemClickListener;
    }
    // ViewHolder 复用
     class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView mTextView;
        ImageView mImageView;
        public MyViewHolder(View itemView) {
            super(itemView);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick (View view) {
            if(mOnItemClickListener != null){
                mOnItemClickListener.onItemClick(view,getPosition());
            }
        }
    }
}
