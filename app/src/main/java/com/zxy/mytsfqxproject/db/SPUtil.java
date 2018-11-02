package com.zxy.mytsfqxproject.db;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Admin on 2017/7/7.
 * 数据缓存类
 */

public class SPUtil {
    private static SharedPreferences preferences;
    private static SPUtil prefenceUtils;
    private static SharedPreferences.Editor editor;

    public static SPUtil getInstance(Context context) {
        if (prefenceUtils == null) {
            prefenceUtils = new SPUtil(context);
        }
        return prefenceUtils;
    }

    @SuppressLint("CommitPrefEdits")
    private SPUtil(Context context) {
        preferences = context.getSharedPreferences("TSH", Context.MODE_PRIVATE);
        editor = preferences.edit();
    }

    public static void clearData() {
        editor.clear();
        editor.apply();
    }

    public static void putData(String key, String value) {
        editor.putString(key, value);
        editor.apply();
    }

    public static void putData(String key, int value) {
        editor.putInt(key, value);
        editor.apply();
    }

    public static void putData(String key, boolean value) {
        editor.putBoolean(key, value);
        editor.apply();
    }

    public static void putData(Map<String, String> map) {
        if (map != null && map.size() > 0) {
            for (Map.Entry<String, String> entry : map.entrySet()) {
                editor.putString(entry.getKey(), entry.getValue());
                editor.apply();
            }
        }
    }

    public static String getData(String key, String defaultValue) {
        return preferences.getString(key, defaultValue);
    }

    public static int getData(String key, int defaultValue) {
        return preferences.getInt(key, defaultValue);
    }

    public static boolean getData(String key, boolean defaultValue) {
        return preferences.getBoolean(key, defaultValue);
    }

    /**
     * 保存List
     *
     * @param tag
     * @param datalist
     */
    public static <T> void setDataList(String tag, List<T> datalist) {
        if (null == datalist || datalist.size() <= 0)
            return;
        Gson gson = new Gson();
        //转换成json数据，再保存
        String strJson = gson.toJson(datalist);
        editor.clear();
        editor.putString(tag, strJson);
        editor.apply();
    }

    /**
     * 获取List
     *
     * @param tag
     * @return
     */
    public static <T> List<T> getDataList(String tag) {
        List<T> datalist = new ArrayList<>();
        String strJson = preferences.getString(tag, null);
        if (null == strJson) {
            return datalist;
        }
        Gson gson = new Gson();
        datalist = gson.fromJson(strJson, new TypeToken<List<T>>() {}.getType());
        return datalist;
    }

}
