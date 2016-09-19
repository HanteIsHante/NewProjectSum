package com.example.hante.newprojectsum.dialogfragment;

import android.app.DialogFragment;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.tweetcomposer.TweetComposer;

import io.fabric.sdk.android.Fabric;

/**
 * Created by handan on 2016/9/18.
 */
public class ShareDialogFragment extends DialogFragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TwitterAuthConfig mTwitterAuthConfig =
                new TwitterAuthConfig("","");
        Fabric.with(getActivity(),new TwitterCore(mTwitterAuthConfig),new TweetComposer());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        return super.onCreateView(inflater, container, savedInstanceState);
    }

    public void shareTwitterContent(){

        Uri uriParse = Uri.parse("https://pbs.twimg.com/media/CCif1CSUwAApVAL.jpg");
        TweetComposer.Builder TCbuilder = new TweetComposer.Builder(getActivity())
                .text("Share Twitter")
                .image(uriParse);
        TCbuilder.show();



    }
}
