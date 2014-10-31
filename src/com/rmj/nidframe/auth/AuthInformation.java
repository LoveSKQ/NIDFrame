package com.rmj.nidframe.auth;

/**
 * 保存用户登录后的认证信息
 * Created by G11 on 2014/10/31.
 */
public class AuthInformation {

    private static AuthInformation mInstance = new AuthInformation();

    private String mUserName = null;

    private AuthInformation() {}

    public static AuthInformation getInstance() {
        return mInstance;
    }

    /**
     * 清空用户登录信息
     */
    public void clearInfo() {
        mUserName = null;
    }

    public String getUserName() {
        return mUserName;
    }

    public void setUserName(String userName) {
        this.mUserName = userName;
    }




}
