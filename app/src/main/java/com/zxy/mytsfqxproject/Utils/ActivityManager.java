package com.zxy.mytsfqxproject.Utils;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 2017/7/7.
 */

public class ActivityManager {
    private static List<Activity> activityList = new ArrayList<>();

    /**
     * 添加Activity
     */
    public static void addActivity(Activity activity) {
        if (null != activity) {
            activityList.add(activity);
        }
    }

    /**
     * 移除activity
     */
    public static void removeActivity(Activity activity) {
        if (null != activity) {
            if (activityList.contains(activity)) {
                activityList.remove(activity);
            }
        }
    }

    /**
     * 移除所有的activity
     */
    public static void finishAll() {
        for (Activity activity : activityList) {
            if (!activity.isFinishing()) {
                activity.finish();
            }
        }
    }
}
