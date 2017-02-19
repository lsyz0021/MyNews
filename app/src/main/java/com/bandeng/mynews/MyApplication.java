package com.bandeng.mynews;

import android.app.Application;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheMode;

import java.util.logging.Level;

/**
 * Created by Lilu on 2017/2/19.
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        OkGo.init(this);
        try {
            OkGo.getInstance()
                    .debug("OkGo", Level.INFO, true)
                    .setConnectTimeout(OkGo.DEFAULT_MILLISECONDS)
                    .setReadTimeOut(OkGo.DEFAULT_MILLISECONDS)
                    .setWriteTimeOut(OkGo.DEFAULT_MILLISECONDS)
                    .setCacheMode(CacheMode.NO_CACHE);

        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
