package com.example.hante.newprojectsum.net.okhttp;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;

import okhttp3.Cache;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 *  OKHttp 2016/10/17.
 */

public class OkHttpManager {

    private OkHttpClient mOkHttpClient;

    /**
     *  json ：application/json
        xml：application/xml
        png：image/png
        jpg： image/jpeg
        gif：image/gif
     */
    private static final MediaType JSON
            = MediaType.parse("application/json; charset=utf-8");

    private static final MediaType MEDIA_TYPE_MARKDOWN
            = MediaType.parse("text/x-markdown; charset=utf-8");

    private static final MediaType MEDIA_TYPE_PNG
            = MediaType.parse("image/png");
    /**
     * 异步
     * @param url url
     */
     public void getOkHttpAsyn(String url){
         Request getBuild = new Request
                 .Builder()
                 .url(url)
                 .get()
                 .build();
         Call getCall = mOkHttpClient.newCall(getBuild);
         getCall.enqueue(new Callback() {
             @Override
             public void onFailure (Call call, IOException e) {

             }

             @Override
             public void onResponse (Call call, Response response) throws IOException {
                 String s = response.body().toString();
                 Headers headers = response.headers();
             }
         });
     }

    /**
     * 同步(阻塞)
     * @param url url
     */
    public String getOkHttpSyn(String url){
        String responseBack = null;
        try {
            Request getBuild = new Request
                    .Builder()
                    .url(url)
                    .build();
            Call getCall = mOkHttpClient.newCall(getBuild);

            Response execute = getCall.execute();
            if(execute.isSuccessful()){
                responseBack = execute.body().toString();
            } else {
                responseBack = null;
            }
        } catch(IOException e) {
            e.printStackTrace();
        }
        return responseBack;
    }

    /**
     *
     * @param url  请求地址
     * @param json  请求参数
     * @throws JSONException
     */
    public void postOkHttp(String url,JSONObject json) throws JSONException {
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
    /**
     *  post  发送文件
     * @param url 请求地址
     */
    public void postFile(String url){
        File mFile = new File("");
        Request buildReq = new Request.Builder()
                .url(url)
                .post(RequestBody.create(MEDIA_TYPE_MARKDOWN, mFile))
                .build();
        mOkHttpClient.newCall(buildReq).enqueue(new Callback() {
            @Override
            public void onFailure (Call call, IOException e) {

            }

            @Override
            public void onResponse (Call call, Response response) throws IOException {

            }
        });

    }

    /**
     * 用POST发送Form表单中的键值对
     * @param url 请求地址
     */
    public void postKeyValue(String url) throws IOException {
        RequestBody formBody = new FormBody.Builder()
                .add("name", "HAN")
                .add("pass", "Pas")
                .build();
        Request request = new Request.Builder()
                .url(url)
                .post(formBody)
                .build();
        Response execute = mOkHttpClient.newCall(request).execute();
        if(execute.isSuccessful()){

        }else {

        }
    }


    /**
     *
     * 既有参数又有文件的 post请求
     * @param url 请求地址
     * @param name 参数名
     * @param img  参数名
     * @param file  文件
     */
    public void postParamsFile(String url,String name,String img,File file){
        MultipartBody build = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("name", name)
                .addFormDataPart("img", img,
                        RequestBody.create(MEDIA_TYPE_PNG, file))
                .build();
        Request request = new Request.Builder()
                .post(build)
                .url(url)
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

    /**
     * 设置缓存
     */
    public void cacheResponse(File dir){
        int cacheSize = 10 * 1024 * 1024 ; // 10M
        Cache cache = new Cache(dir, cacheSize);
        mOkHttpClient  = new OkHttpClient.Builder()
                .cache(cache).build();
    }
    /**
     *
     */
}
