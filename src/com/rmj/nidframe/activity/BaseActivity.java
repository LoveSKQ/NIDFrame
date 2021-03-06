package com.rmj.nidframe.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.*;
import com.rmj.nidframe.R;
import com.rmj.nidframe.app.NidApplication;
import com.rmj.nidframe.common.Constants;
import com.rmj.nidframe.manager.NidActivityManager;
import com.rmj.nidframe.view.TitleBar;

/**
 * Created by G11 on 2014/9/12.
 */
public abstract class BaseActivity extends Activity {
    NidApplication mApplication;
    ViewGroup mRootView;
    TitleBar mTitleBar;
    Handler mHandler;
    ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.setContentView(R.layout.activity_base);
        mRootView = (ViewGroup) findViewById(R.id.rootView);
        mTitleBar = (TitleBar) findViewById(R.id.titleBar);

        // 获取应用实例
        mApplication = (NidApplication) getApplication();

        NidActivityManager.getInstance().addActivity(this);

        //初始化Handler
        initHandler();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        NidActivityManager.getInstance().finishActivity(this);
    }

    @Override
    public void setContentView(int layoutResID) {
        LayoutInflater inflater = LayoutInflater.from(this);
        inflater.inflate(layoutResID, mRootView);
    }

    public TitleBar getTitleBar() {
        return mTitleBar;
    }

    public void showTitleBar(boolean show) {
        if (show) {
            mTitleBar.setVisibility(View.VISIBLE);
        } else {
            mTitleBar.setVisibility(View.GONE);
        }
    }

    /**
     * 执行返回上一个界面操作
     * @param v
     */
    public void onReturnClick(View v) {
        Activity parent = this.getParent();
        if (parent != null) {
            parent.finish();
        } else {
            this.finish();
        }
    }

    /**
     * 初始化界面组件
     */
    public abstract void initComponents();

    /**
     * 显示加载数据进度条
     * @param message
     */
    public void showProgressDialog(String message) {
        if (mProgressDialog == null) {
            mProgressDialog = ProgressDialog.show(this, "", message);
            mProgressDialog.setCancelable(true);
            mProgressDialog.setCanceledOnTouchOutside(false);
        } else {
            if (!mProgressDialog.isShowing()) {
                mProgressDialog.show();
            }
            mProgressDialog.setMessage(message);
        }
    }

    /**
     * 隐藏记载数据的进度条
     */
    public void hideProgressDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }

    /**
     * 初始化Handler
     */
    public void initHandler() {
        mHandler = new Handler() {

            @Override
            public void handleMessage(Message msg) {
                Bundle _data = msg.getData();
                switch (msg.what) {
                    case Constants.HTTP_REQUEST_START:
                        httpRequestStart(_data);
                        break;
                    case Constants.HTTP_REQUEST_FAILED:
                        httpRequestFailed(_data);
                        break;
                    case Constants.HTTP_RESULT_SUCCESS:
                        httpResultSuccess(_data);
                        break;
                    case Constants.HTTP_RESULT_UNSUCCESS:
                        httpResultUnsuccess(_data);
                        break;
                    case Constants.HTTP_RESULT_JSON_ERROR:
                        httpResultJsonError(_data);
                        break;
                    case Constants.HTTP_RESULT_NO_RESULTCODE:
                        httpResultCodeError(_data);
                        break;
                }
            };
        };
    }

    /**
     * (Http请求处理) 开始执行发送请求
     */
    public void httpRequestStart(Bundle data) {
        showProgressDialog("正在加载数据...");
    }

    /**
     * (Http请求处理) 请求出现错误（网络问题 , 超时，或服务器未响应）
     */
    public void httpRequestFailed(Bundle data) {
        hideProgressDialog();
    }

    /**
     * (Http请求处理) 请求数据成功，成功返回Json结果
     */
    public void httpResultSuccess(Bundle data) {
        hideProgressDialog();
    }

    /**
     * (Http请求处理) 服务器成功返回数据，但返回请求未成功信息
     */
    public void httpResultUnsuccess(Bundle data) {
        hideProgressDialog();
    }

    /**
     * (Http请求处理) 解析服务器返回json数据失败
     */
    public void httpResultJsonError(Bundle data) {
        hideProgressDialog();
    }

    /**
     * (Http请求处理) 返回结果里不存在resultcode的错误
     */
    public void httpResultCodeError(Bundle data) {
        hideProgressDialog();
    }
}