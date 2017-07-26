package com.yun.read.base;

/**
 * Created by Administrator on 2017/7/26 0026.
 */

public interface HttpListener {

    void onFail();

    void onError();

    void onSucess(String json);
}
