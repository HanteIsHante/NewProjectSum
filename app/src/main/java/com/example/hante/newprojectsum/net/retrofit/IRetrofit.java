package com.example.hante.newprojectsum.net.retrofit;

import com.example.hante.newprojectsum.bean.douban.DouBanMovies;
import com.example.hante.newprojectsum.okhttpactivity.model.ZhiHu;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 *  Retrofit 请求接口类
 */

public interface IRetrofit {

    /**
     * 获取今日信息
     * @return ZhiHu
     */

        @GET("latest")
        Call<ZhiHu> getTodayNews();
    /**
     * 查看历史信息
     * Path：代表动态路径
     * @param path  日期 格式：20131119
     * @return ZhiHu
     */
        @GET("before/{path}")
        Call<ZhiHu> getHistoryNews(@Path("path") String path);


    /**
     * @param start 参数 请求位置
     * @param count 参数 请求数量
     * @return DouBanMovies
     */
        @GET("top250")
        Call<DouBanMovies> getDouBanMovies(@Query("start") int start, @Query("count") int
                count);



}
