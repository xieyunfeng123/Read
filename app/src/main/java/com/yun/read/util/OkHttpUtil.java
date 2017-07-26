package com.yun.read.util;

import android.util.Log;

import com.yun.read.base.Constan;
import com.yun.read.base.HttpListener;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by 街角慢嗨 on 2017/7/26 0026.
 */

public class OkHttpUtil {

    static OkHttpClient okHttpClient = new OkHttpClient.Builder().connectTimeout(10, TimeUnit.SECONDS)
            .readTimeout(20, TimeUnit.SECONDS)
            .build();


    public static void getResult(int position,final HttpListener httpListener) {
        Request request = new Request.Builder()
                .url(Constan.BASE_URL+"福利/20/"+position)
                .build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                httpListener.onError();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
              String result=  response.body().string();
                Log.e("insert",result);
                httpListener.onSucess(result);
            }
        });

    }




}
