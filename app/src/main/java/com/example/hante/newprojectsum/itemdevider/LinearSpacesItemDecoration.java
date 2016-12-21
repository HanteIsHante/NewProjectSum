package com.example.hante.newprojectsum.itemdevider;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 *  RecyclerView  Item 分割线
 *  LinearLayoutManager
 */

public class LinearSpacesItemDecoration extends RecyclerView.ItemDecoration{

    private int leftRight;
    private int topBottom;
    private Drawable mDividerColor;



    public LinearSpacesItemDecoration (int leftRight, int topBottom, int color) {
        this.leftRight = leftRight;
        this.topBottom = topBottom;
        if(color != 0){
            this.mDividerColor = new ColorDrawable(color);
        }
    }

    /**
     * 对divider的具体实现。
     */
    @Override
    public void onDraw (Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
        LinearLayoutManager layoutManager = (LinearLayoutManager)parent.getLayoutManager();
        if(mDividerColor == null || layoutManager.getChildCount() == 0){
            return;
        }
        int left;
        int right;
        int top;
        int bottom;
        final int childCount = parent.getChildCount();
        if (layoutManager.getOrientation() == GridLayoutManager.VERTICAL) {
            for (int i = 0; i < childCount - 1; i++) {
                final View child = parent.getChildAt(i);
                final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
                //将有颜色的分割线处于中间位置
                float center = (layoutManager.getTopDecorationHeight(child) - topBottom) / 2;
                //计算下边的
                left = layoutManager.getLeftDecorationWidth(child);
                right = parent.getWidth() - layoutManager.getLeftDecorationWidth(child);
                top = (int) (child.getBottom() + params.bottomMargin + center);
                bottom = top + topBottom;
                mDividerColor.setBounds(left, top, right, bottom);
                mDividerColor.draw(c);
            }
        } else {
            for (int i = 0; i < childCount - 1; i++) {
                final View child = parent.getChildAt(i);
                final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
                //将有颜色的分割线处于中间位置
                float center = (layoutManager.getLeftDecorationWidth(child) - leftRight) / 2;
                //计算右边的
                left = (int) (child.getRight() + params.rightMargin + center);
                right = left + leftRight;
                top = layoutManager.getTopDecorationHeight(child);
                bottom = parent.getHeight() - layoutManager.getTopDecorationHeight(child);
                mDividerColor.setBounds(left, top, right, bottom);
                mDividerColor.draw(c);
            }
        }
    }

    //确定divider的范围
    @Override
    public void getItemOffsets (Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        LinearLayoutManager layoutManager = (LinearLayoutManager)parent.getLayoutManager();
        // 竖直方向
        if(layoutManager.getOrientation() == LinearLayoutManager.VERTICAL){
            // 最后一项需要bottom
            if(parent.getChildAdapterPosition(view) == layoutManager.getItemCount() - 1){
                outRect.bottom = topBottom;
            }
            outRect.top = topBottom;
            outRect.left = leftRight;
            outRect.right = leftRight;
        } else {
            // 最后一项需要right
            if(parent.getChildAdapterPosition(view) == layoutManager.getItemCount() - 1){
                outRect.right = leftRight;
            }
            outRect.top = topBottom;
            outRect.bottom = topBottom;
            outRect.left = leftRight;
        }
    }


}
