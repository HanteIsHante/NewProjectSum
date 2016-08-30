package com.example.hante.newprojectsum.net;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

/**
 * Created by handan on 2016/8/30.
 */
public class AsyncHttpClientManager {
    public  static AsyncHttpClient asyncHttpClient = new AsyncHttpClient();


    /**
     * get
     */
    public static void get(String url , RequestParams params , JsonHttpResponseHandler responseHandler){
        getDefaultHttpClient ();
        asyncHttpClient.get(url,params,responseHandler);
    }

    /**
     * post
     * @return
     */
    public static void post(String urlStr, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        getDefaultHttpClient();
        asyncHttpClient.post(urlStr, params, responseHandler);
    }

    /**
     * 默认参数
     * @return
     */
    public static RequestParams getDefultParmas (){
        RequestParams requestParams = new RequestParams();
        requestParams.put("","");
        return requestParams;
    }

    public static AsyncHttpClient getDefaultHttpClient() {
        if (asyncHttpClient == null) {
            asyncHttpClient = new AsyncHttpClient();
        }
        return asyncHttpClient;
    }



}
