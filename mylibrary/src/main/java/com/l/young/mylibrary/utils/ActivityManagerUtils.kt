package com.l.young.mylibrary.utils

import android.app.Activity
import android.util.Log

import java.util.ArrayList


/**
 * Created by Xubowen on 2018/6/6.
 */

class ActivityManagerUtils {
    /**
     * 这里面写一些需要执行初始化的工作
     */


    class Inner {
        companion object {
            val instance = ActivityManagerUtils()
        }
    }

    /**
     * 打开的activity
     */

    private val activities = ArrayList<Activity>()


    /**
     * 新建了一个activity
     *
     * @param activity
     */

    fun addActivity(activity: Activity) {
        activities.add(activity)
        Log.d(TAG, "activities add:" + activity.localClassName)
    }

    /**
     * 结束指定的Activity
     *
     * @param activity
     */

    fun finishActivity(activity: Activity?) {

        if (activity != null) {
            this.activities.remove(activity)
            activity.finish()
        }
    }

    /**
     * 应用退出，结束所有的activity
     */

    fun exit() {

        for (activity in activities) {
            activity?.finish()
        }
        System.exit(0)

    }

    /**
     * 结束指定类名的Activity
     */
    fun finishActivityclass(cls: Class<*>) {
        if (activities != null) {
            for (activity in activities) {
                if (activity::class == cls) {
                    this.activities.remove(activity)
                    finishActivity(activity)
                    break
                }
            }
        }

    }

    /**
     * 清除
     */
    fun clear() {
        for (activity in activities) {
            if (null != activity && !activity.isDestroyed) {
                activity.finish()
            }
        }
    }

    companion object {
        const val TAG = "TSPAPP_TAG"
        @JvmStatic
        fun instance(): ActivityManagerUtils {
            return Inner.instance
        }

    }

}