package com.yun.read.base;

/**
 * Created by Administrator on 2017/7/26 0026.
 */

public class Constan {

    //http://gank.io/api/search/query/listview/category/Android/count/10/page/1
    //all | Android | iOS | 休息视频 | 福利 | 拓展资源 | 前端 | 瞎推荐 | App

    /**
     *分类数据: http://gank.io/api/data/数据类型/请求个数/第几页
     *数据类型： 福利 | Android | iOS | 休息视频 | 拓展资源 | 前端 | all
     *请求个数： 数字，大于0
     *第几页：数字，大于0
     *例：
     *http://gank.io/api/data/Android/10/1
     *http://gank.io/api/data/福利/10/1
     *http://gank.io/api/data/iOS/20/2
     *http://gank.io/api/data/all/20/2
     */
    public static String BASE_URL="http://gank.io/api/data/";

    public static String IMAGE_END="?imageView2/0/w/";


}
