package com.rmj.nidframe.util;

import android.util.Log;
import com.rmj.nidframe.app.Configs;

/**
 * Created by G11 on 2014/9/15.
 */
public class LogUtil {
    /**
     * 输出日志信息
     * @param tag
     * @param msg
     */
    public static void i(String tag,String msg) {
        if (Configs.DEVELOP_VERSION) {
            Log.i(tag, msg);
        }
    }

    /**
     * 输出错误信息
     * @param tag
     * @param msg
     */
    public static void e(String tag,String msg) {
        if (Configs.DEVELOP_VERSION) {
            Log.e(tag, msg);
        }
    }
    /**
     * 输出异常详情
     * @param e
     */
    public static void exception(Exception e) {
        if (Configs.DEVELOP_VERSION) {
            e.printStackTrace();
        }
    }
}
