package com.rmj.nidframe.iweb.impl;

import com.rmj.nidframe.app.Configs;
import com.rmj.nidframe.iweb.IWeb;

/**
 * Created by G11 on 2014/9/15.
 */
public class IWebImpl implements IWeb {

    @Override
    public void login(String username, String password) {
        if (Configs.WEB_DATA_INTERFACE) {
            //TODO 通过网络数据接口发送网络请求
        }
        else {
            //TODO 不走网络数据接口，根据情况自行模拟数据（测试界面用）
        }
    }
}
