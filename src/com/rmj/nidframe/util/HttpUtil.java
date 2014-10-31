package com.rmj.nidframe.util;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.rmj.nidframe.app.Configs;
import com.rmj.nidframe.common.Constants;
import org.apache.http.entity.StringEntity;
import org.json.JSONObject;

import java.io.File;

/**
 * Created by G11 on 2014/10/31.
 */
public class HttpUtil {
    Handler mHandler = null;
    String mUrl = null;

    public HttpUtil(Handler handler) {
        com.lidroid.xutils.HttpUtils.sHttpCache.clear();
        mHandler = handler;
    }

    /**
     * 执行Http的Get请求
     * @param url
     * @param params
     * @param obj
     */
    public void httpGet(String url, RequestParams params, JSONObject obj) {
        httpRequest(HttpRequest.HttpMethod.GET, url, params, obj);
    }

    /**
     * 执行Http的Post请求
     * @param url
     * @param params
     * @param obj
     */
    public void httpPost(String url, RequestParams params, JSONObject obj) {
        httpRequest(HttpRequest.HttpMethod.POST, url, params, obj);
    }

    /**
     * 执行Http请求，需要指定请求方式
     * @param method
     * @param url
     * @param params
     * @param obj
     */
    private void httpRequest(HttpRequest.HttpMethod method, String url, RequestParams params, JSONObject obj) {
        try {
            if (obj != null) {
                params.setBodyEntity(new StringEntity(obj.toString()));
            }
        } catch (Exception e) {
            LogUtil.exception(e);
        }

        HttpUtils _http = new HttpUtils(Configs.HTTP_REQUEST_TIMEOUT);
        _http.send(method, url, params, mRequestCallBack);
    }

    /**
     * 下载指定文件（需要指定请求回调方法，执行处理操作）
     * @param url
     * @param filePath
     * @param callback
     */
    public void httpDownloadFile(String url,String filePath,RequestCallBack<File> callback) {
        mUrl = url;
        HttpUtils _http = new HttpUtils();
        _http.download(url, filePath, callback);
    }

    private RequestCallBack<String> mRequestCallBack = new RequestCallBack<String>() {

        @Override
        public void onSuccess(ResponseInfo<String> response) {
            String _result = response.result;
            LogUtil.i("HttpPost Response", _result);
            if (_result != null && !_result.equals("")) {
                // 成功返回数据
                try {
                    // 处理json数据
                    JSONObject _obj = new JSONObject(_result);
                    int _resultCode = -1;

                    // 判断返回结果是否含有resultcode
                    if (_obj.has("resultcode")) {
                        _resultCode = _obj.getInt("resultcode");
                    }

                    if (_resultCode != -1) {
                        if (_resultCode == Constants.HTTP_RESULT_CODE_SUCCESSED) {
                            // 请求数据成功
                            sendSuccessMessage(_obj);
                        } else {
                            // 请求数据失败
                            sendResultCodeErrorMessage(_obj);
                        }
                    } else {
                        // 服务器返回数据没有resultcode
                        sendNoResultCodeMessage();
                    }
                } catch (Exception e) {
                    LogUtil.exception(e);
                    sendJsonErrorMessage();
                }
            } else {
                // TODO 服务器未返回数据(等效于返回数据不包含resultcode的情况)
                sendNoResultCodeMessage();
            }
        }

        @Override
        public void onFailure(HttpException exception, String msg) {
            LogUtil.exception(exception);
            sendNetworkErrorMessage();
        }

        @Override
        public void onStart() {
            sendHttpStartMessage();
            super.onStart();
        }

    };

    /**
     * 开始执行网络请求
     */
    public void sendHttpStartMessage() {
        Message _message = mHandler.obtainMessage(Constants.HTTP_REQUEST_START);
        Bundle _data = new Bundle();
        _data.putString("url", mUrl);
        _message.setData(_data);
        mHandler.sendMessage(_message);
    }

    /**
     * 请求出现错误（网络问题 , 超时，或服务器未响应）
     */
    public void sendNetworkErrorMessage() {
        Message _message = mHandler.obtainMessage(Constants.HTTP_REQUEST_FAILED);
        Bundle _data = new Bundle();
        _data.putString("url", mUrl);
        _message.setData(_data);
        mHandler.sendMessage(_message);
    }

    /**
     * 请求数据成功，成功返回Json结果
     *
     * @param obj
     */
    public void sendSuccessMessage(JSONObject obj) {
        Message _message = mHandler.obtainMessage(Constants.HTTP_RESULT_SUCCESS);
        Bundle _data = new Bundle();
        _data.putString("url", mUrl);
        _data.putString("json", obj.toString());
        _message.setData(_data);
        mHandler.sendMessage(_message);
    }

    /**
     * Json解析错误
     */
    public void sendJsonErrorMessage() {
        Message _message = mHandler
                .obtainMessage(Constants.HTTP_RESULT_JSON_ERROR);
        Bundle _data = new Bundle();
        _data.putString("url", mUrl);
        _message.setData(_data);
        mHandler.sendMessage(_message);
    }

    /**
     * 返回结果里不存在resultcode的错误
     */
    public void sendNoResultCodeMessage() {
        Message _message = mHandler.obtainMessage(Constants.HTTP_RESULT_NO_RESULTCODE);
        Bundle _data = new Bundle();
        _data.putString("url", mUrl);
        _message.setData(_data);
        mHandler.sendMessage(_message);
    }

    /**
     * 返回码resultcode不为1
     */
    public void sendResultCodeErrorMessage(JSONObject obj) {
        Message _message = mHandler.obtainMessage(Constants.HTTP_RESULT_UNSUCCESS);
        Bundle _data = new Bundle();
        _data.putString("url", mUrl);
        _data.putString("json", obj.toString());
        _message.setData(_data);
        mHandler.sendMessage(_message);
    }

}
