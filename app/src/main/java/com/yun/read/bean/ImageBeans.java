package com.yun.read.bean;

import java.util.List;

/**
 * Created by 街角慢嗨 on 2017/7/26 0026.
 */

public class ImageBeans {
    private boolean error;

    private List<ImageInfo> results;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public List<ImageInfo> getResults() {
        return results;
    }

    public void setResults(List<ImageInfo> results) {
        this.results = results;
    }
}
