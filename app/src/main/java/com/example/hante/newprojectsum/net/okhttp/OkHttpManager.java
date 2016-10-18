package com.example.hante.newprojectsum.net.okhttp;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 *  OKHttp 2016/10/17.
 */

public class OkHttpManager {

    OkHttpClient mOkHttpClient = new OkHttpClient();
    public static final MediaType JSON
            = MediaType.parse("application/json; charset=utf-8");

     public void getOkHttp(String url){
         Request getBuild = new Request
                 .Builder()
                 .url("")
                 .build();
         Call getCall = mOkHttpClient.newCall(getBuild);
         // 异步
         getCall.enqueue(new Callback() {
             @Override
             public void onFailure (Call call, IOException e) {

             }

             @Override
             public void onResponse (Call call, Response response) throws IOException {

             }
         });
         // 同步
         try {
             Response execute = getCall.execute();
             String responseString = execute.body().toString();
         } catch(IOException e) {
             e.printStackTrace();
         }
     }


    public void postOkHttp(String url,String name,String pass,JSONObject json) throws JSONException {
        RequestBody body = RequestBody.create(JSON, String.valueOf(json));
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        mOkHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure (Call call, IOException e) {

            }

            @Override
            public void onResponse (Call call, Response response) throws IOException {

            }
        });

    }
}
