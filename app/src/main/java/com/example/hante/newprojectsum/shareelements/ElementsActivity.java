package com.example.hante.newprojectsum.shareelements;

import android.os.Build;
import android.os.Bundle;
import android.transition.Fade;
import android.widget.ImageView;

import com.example.hante.newprojectsum.BaseActivity;
import com.example.hante.newprojectsum.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ElementsActivity extends BaseActivity {

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

    }
    private void newAnimationDesign() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Fade fade = new Fade();
            fade.setDuration(500);
            getWindow().setEnterTransition(fade);
        }
    }
}
