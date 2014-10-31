package com.rmj.nidframe.iweb;

/**
 * 网络数据接口
 * Created by G11 on 2014/9/12.
 */
public interface IWeb {

    /**
     * 用户登录接口
     *
     * @param username 用户名
     * @param password 密码
     */
    public abstract void login(String username, String password);
}
