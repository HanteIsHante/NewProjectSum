package com.example.hante.newprojectsum.recyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.hante.newprojectsum.R;
import com.example.hante.newprojectsum.bean.PrettyGirl;

import java.util.ArrayList;

/**
 * Created by handan on 2016/9/12.
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

    private static final String TAG = "RecyclerViewAdapter";
    private Context mContext;
    private ArrayList<PrettyGirl> mPrettyGirlsData;
    onItemClickListener mOnItemClickListener;

    public RecyclerViewAdapter (Context applicationContext, ArrayList<PrettyGirl> mArrayList) {
        mContext = applicationContext;
        this.mPrettyGirlsData = mArrayList;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater itemLayout = LayoutInflater.from(parent.getContext());
        View inflate = itemLayout.inflate(R.layout.activity_recycler_view_item, parent,false);
        return new MyViewHolder(inflate);
    }

    @Override
    public int getItemCount() {
        return mPrettyGirlsData.size();
    }


    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        holder.mTextViewWho.setText(mPrettyGirlsData.get(position).getmWho());
        holder.mTextViewTime.setText(mPrettyGirlsData.get(position).getmPublished());
        Log.d(TAG, "图片集合 " + mPrettyGirlsData.get(position).getmImg());
        Log.d(TAG, "图片集合 " + mPrettyGirlsData.get(position).getmWho());
        Glide.with(mContext)
                .load(mPrettyGirlsData.get(position).getmImg())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.mImageView);
        final PrettyGirl prettyGirl = mPrettyGirlsData.get(position);
        if(mOnItemClickListener != null){
            holder.mImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick (View view) {
                    int adapterPosition = holder.getAdapterPosition();
                    mOnItemClickListener.onItemClick(holder.mImageView,adapterPosition);
                }
            });
        }
        // Title Click
        holder.mTextViewWho.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View view) {
                int adapterPosition = holder.getAdapterPosition();
                Toast.makeText(mContext, "点击 ："+ prettyGirl.getmWho() + " ; " + adapterPosition,
                        Toast
                        .LENGTH_SHORT).show();
            }
        });
    }

    public interface onItemClickListener {
         void  onItemClick(View view,int position);
    }

    /**
     *
     * @param onItemClickListener adapter 调用
     */
    public void SetOnItemClickListener (final onItemClickListener onItemClickListener){
        this.mOnItemClickListener = onItemClickListener;
    }
    // ViewHolder 复用
     class MyViewHolder extends RecyclerView.ViewHolder{

        ImageView mImageView;
        TextView mTextViewWho,mTextViewTime;
          MyViewHolder(View itemView) {
            super(itemView);
            mTextViewWho = (TextView)itemView.findViewById(R.id.item_recycler);
            mImageView = (ImageView)itemView.findViewById(R.id.show_img);
            mTextViewTime = (TextView)itemView.findViewById(R.id.item_time);
        }
    }
}
