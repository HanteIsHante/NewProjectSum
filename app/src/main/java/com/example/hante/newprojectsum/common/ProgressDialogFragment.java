package com.example.hante.newprojectsum.common;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.example.hante.newprojectsum.R;

/**
 * 进度条dialogfragment
 */

public class ProgressDialogFragment extends DialogFragment{


    @Override
    public void onStart () {
        super.onStart();
        Dialog dialog =getDialog();//获取Dialog
        WindowManager.LayoutParams attr = dialog.getWindow().getAttributes();//获取Dialog属性
        WindowManager wm= (WindowManager) dialog.getContext().getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetric=new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetric);
        attr.width= (int) (outMetric.widthPixels*0.618f);
        dialog.getWindow().setAttributes(attr);
    }

    @Nullable
    @Override
    public View onCreateView (LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.progress_bar, container, false);
        return view;
    }
}
