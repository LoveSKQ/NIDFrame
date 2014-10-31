package com.rmj.nidframe.app;

import com.rmj.nidframe.util.FileUtils;

/**
 * Created by G11 on 2014/9/12.
 */
public class Configs {

    public static final boolean DEVELOP_VERSION = true;//是否为开发版本
    public static final boolean WEB_DATA_INTERFACE = true;//是否使用网络数据接口

    public static final int HTTP_REQUEST_TIMEOUT = 10000;

    public static final String ROOT_PATH = FileUtils.getSDCardPath() + "/";
    public static final String PHOTO_PATH = ROOT_PATH + "/photos";
}
