package com.gupaoedu.sso.controller;

/**
 * @Author: LIH
 * @Date: 2020/7/10 14:03
 */
public class BaseController {

    private static ThreadLocal<String> uidThreadLocal = new ThreadLocal<>();

    public void setUid(String uid) {
        uidThreadLocal.set(uid);
    }

    public String getUid() {
        return uidThreadLocal.get();
    }
}
