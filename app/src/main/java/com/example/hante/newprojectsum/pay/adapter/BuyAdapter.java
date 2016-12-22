package com.example.hante.newprojectsum.pay.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.hante.newprojectsum.R;
import com.example.hante.newprojectsum.common.Constant;
import com.example.hante.newprojectsum.pay.bean.BuyStyle;

import java.util.List;

/**
 *  购买方式列表适配器
 */

public class BuyAdapter extends RecyclerView.Adapter<BuyAdapter.ViewHolder>{

    private List<BuyStyle> mStyleList;
    private Context mContext;
    private static final String TAG = "BuyAdapter";
    private onItemClickListener mOnItemclickListener;

    public BuyAdapter (List<BuyStyle> styleList, Context context) {
        mStyleList = styleList;
        mContext = context;
    }

    public interface onItemClickListener {
       void onItemClick(View view, int position);
    }

    public void setOnItemClickListener(onItemClickListener itemClickListener){
        this.mOnItemclickListener = itemClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder (ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.buy_style_item,
                parent, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder (final ViewHolder holder, int position) {
        int id_code = mStyleList.get(position).getId_code();
        if(id_code == Constant.ALIPAY){
            holder.type.setText("支付宝");
        } else if (id_code == Constant.WEIXIN){
            holder.type.setText("微信");
        } else if (id_code == Constant.PAYPAL){
            holder.type.setText("贝宝");
        }
        holder.go.setImageDrawable(mContext.getResources().getDrawable(R.drawable
                .ic_navigate_next_black_24dp));
        if(mOnItemclickListener != null){
            holder.mRelativeLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick (View v) {
                    int po = holder.getAdapterPosition();
                    mOnItemclickListener.onItemClick(v, po);
                }
            });
        }
    }
    @Override
    public int getItemCount () {
        Log.d(TAG, "getItemCount: " +  mStyleList.size());
        return mStyleList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        private RelativeLayout mRelativeLayout;
        private ImageView icon;
        private TextView  type;
        private ImageView go;
        public ViewHolder (View itemView) {
            super(itemView);
             icon = (ImageView) itemView.findViewById(R.id.style_icon);
             type = (TextView) itemView.findViewById(R.id.style_name);
             go = (ImageView) itemView.findViewById(R.id.go_detail);
             mRelativeLayout = (RelativeLayout) itemView.findViewById(R.id.buy_style_item);
        }
    }
}
