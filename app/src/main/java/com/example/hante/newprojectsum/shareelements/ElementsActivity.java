package com.example.hante.newprojectsum.shareelements;

import android.os.Build;
import android.os.Bundle;
import android.transition.Fade;
import android.transition.TransitionInflater;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.hante.newprojectsum.BaseActivity;
import com.example.hante.newprojectsum.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ElementsActivity extends BaseActivity {
    private static final String TAG = "ElementsActivity";
    @Bind(R.id.share_img)
    ImageView mShareElementsImg;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_elements);
        ButterKnife.bind(this);

        initData();
        newAnimationDesign();
    }

    private void initData () {
        String url = getIntent().getStringExtra("url");
        Log.d(TAG, "initData: " + url);
        Glide.with(this).load(url).into(mShareElementsImg);

    }
    private void newAnimationDesign() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Fade fade = (Fade) TransitionInflater.from(ElementsActivity.this).inflateTransition
                    (R.transition.fade);
            getWindow().setEnterTransition(fade);
        }
    }
}
