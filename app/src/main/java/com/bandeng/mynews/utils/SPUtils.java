package com.bandeng.mynews.utils;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Map;

/**
 * Created by Lilu on 2016/12/4.<p>
 * SharedPreferences 的工具类
 */

public class SPUtils {

    private static final String fileName = "config";
    private static SharedPreferences sp;

    private SPUtils() {
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    public static void putBoolean(Context context, String key, boolean value) {
        if (sp == null) {
            sp = context.getSharedPreferences(fileName, Context.MODE_PRIVATE);
        }
        sp.edit().putBoolean(key, value).apply();
    }

    public static void putString(Context context, String key, String value) {
        if (sp == null) {
            sp = context.getSharedPreferences(fileName, Context.MODE_PRIVATE);
        }
        sp.edit().putString(key, value).apply();
    }

    public static void putInt(Context context, String key, int value) {
        if (sp == null) {
            sp = context.getSharedPreferences(fileName, Context.MODE_PRIVATE);
        }
        sp.edit().putInt(key, value).apply();
    }

    public static boolean getBoolean(Context context, String key, boolean defValue) {
        if (sp == null) {
            sp = context.getSharedPreferences(fileName, Context.MODE_PRIVATE);
        }
        return sp.getBoolean(key, defValue);
    }

    public static String getString(Context context, String key, String defValue) {
        if (sp == null) {
            sp = context.getSharedPreferences(fileName, Context.MODE_PRIVATE);
        }
        return sp.getString(key, defValue);
    }

    public static int getInt(Context context, String key, int defValue) {
        if (sp == null) {
            sp = context.getSharedPreferences(fileName, Context.MODE_PRIVATE);
        }
        return sp.getInt(key, defValue);
    }

    /**
     * 查询某个key是否存在
     *
     * @param context
     * @param key
     * @return true or false
     */
    public static boolean contains(Context context, String key) {
        if (sp == null) {
            sp = context.getSharedPreferences(fileName, Context.MODE_PRIVATE);
        }
        return sp.contains(key);
    }

    /**
     * 移除某个key对应的值
     *
     * @param context
     * @param key
     */
    public static void remove(Context context, String key) {
        if (sp == null) {
            sp = context.getSharedPreferences(fileName, Context.MODE_PRIVATE);
        }
        sp.edit().remove(key).apply();
    }

    /**
     * 移除所有的数据
     *
     * @param context
     */
    public static void clear(Context context) {
        SharedPreferences sp = context.getSharedPreferences(fileName, Context.MODE_PRIVATE);
        sp.edit().clear().apply();
    }

    /**
     * 获取所有的键值对
     *
     * @param context
     * @return
     */
    public static Map<String, ?> getAll(Context context) {
        if (sp == null) {
            sp = context.getSharedPreferences(fileName, Context.MODE_PRIVATE);
        }
        return sp.getAll();
    }
}
