package com.rmj.nidframe.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by G11 on 2014/10/31.
 */
public class EncryptUtils {

    /**
     * MD5加密算法
     *
     * @param password
     * @return
     * @throws NoSuchAlgorithmException
     */
    public static String md5(String password) throws NoSuchAlgorithmException {
        String _result = null;
        MessageDigest _md5 = MessageDigest.getInstance("MD5");
        _md5.update(password.getBytes());
        byte[] _resultBytes = _md5.digest();
        StringBuffer _sb = new StringBuffer();
        for (int i = 0; i < _resultBytes.length; i++) {
            if (_resultBytes[i] >= 0 && _resultBytes[i] < 16) {
                _sb.append("0");
            }
            _sb.append(Integer.toHexString(_resultBytes[i] & 0xff));
        }
        _result = _sb.toString();
        return _result;
    }
}
