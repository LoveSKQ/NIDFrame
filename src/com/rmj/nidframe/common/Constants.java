package com.rmj.nidframe.common;

/**
 * Created by G11 on 2014/9/12.
 */
public class Constants {

    public static final int HTTP_REQUEST_START = 1;//开始发送请求
    public static final int HTTP_REQUEST_FAILED = 2;//发送请求失败（网络问题或服务器未响应）
    public static final int HTTP_RESULT_SUCCESS = 3;//成功返回正确数据
    public static final int HTTP_RESULT_UNSUCCESS = 4;//成功返回数据，但返回请求未成功信息
    public static final int HTTP_RESULT_JSON_ERROR = 5;//成功返回数据，但Json解析失败
    public static final int HTTP_RESULT_NO_RESULTCODE = 6;//成功返回数据，但未找到resultcode值

    public static final int HTTP_RESULT_CODE_FAILED = 0;
    public static final int HTTP_RESULT_CODE_SUCCESSED = 1;
}
