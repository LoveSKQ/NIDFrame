package com.rmj.nidframe.util;

import android.widget.Toast;
import com.rmj.nidframe.manager.NidActivityManager;

/**
 * Toast弹出管理，借助NidActivityManager，无需再指定Context
 * Created by G11 on 2014/10/31.
 */
public class ToastUtils {
    public static void showToast(String msg) {
        Toast.makeText(NidActivityManager.getInstance().currentActivity(), msg, Toast.LENGTH_SHORT).show();
    }
}
