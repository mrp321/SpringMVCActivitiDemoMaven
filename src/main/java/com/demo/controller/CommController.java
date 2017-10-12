package com.demo.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

/**
 * 共通controller
 * 
 * @date 2017-10-10
 * @author qchen
 * 
 * 
 */
public class CommController {

    /**
     * 返回信息
     * 
     * @param success
     *            成功
     * @param data
     *            数据
     * @param msg
     *            信息
     * @param extraData
     *            附加数据
     * @return
     */
    protected Map<String, Object> getReturnData(boolean success, Object data,
            String msg, Map<String, Object> extraData) {
        Map<String, Object> map = new HashMap<>();
        map = this.getReturnMap(success, data, msg);
        if (extraData != null) {
            map.put("extra", extraData);
        }

        return map;
    }

    /**
     * 返回错误信息
     * 
     * @param msg
     *            信息
     * @return
     */
    protected Map<String, Object> getFailureData(String msg) {
        return this.getFailureMap(msg);
    }

    /**
     * 返回信息
     * 
     * @param success
     *            是否成功
     * @param data
     *            返回数据
     * @param msg
     *            信息
     * @return
     */
    protected Map<String, Object> getReturnMap(boolean success, Object data,
            String msg) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("success", success);
        if (data != null) {
            map.put("data", data);
        }
        map.put("msg", msg);
        return map;
    }

    /**
     * 返回失败信息
     * 
     * @param msg
     *            失败信息
     * @return
     */
    protected Map<String, Object> getFailureMap(String msg) {
        return this.getReturnMap(false, null, msg);
    }

    /**
     * 返回成功信息
     * 
     * @param msg
     *            失败信息
     * @return
     */
    protected Map<String, Object> getSuccessMap(String msg) {
        return this.getReturnMap(true, null, msg);
    }

    /**
     * 返回session过期信息
     * 
     * @return
     */
    protected Map<String, Object> getInvalidSessionMap() {
        Map<String, Object> map = new HashMap<>();
        map = this.getFailureMap("未登录，或者登陆过期，请重新登陆");
        map.put("flag", "invalid session");
        return map;
    }

    /**
     * 返回传入参数为空信息
     * 
     * @return
     */
    protected Map<String, Object> getEmptyParamsMap() {
        return this.getFailureMap("传入参数不能为空");
    }

    /**
     * 是否登陆
     * 
     * @param session
     * @return
     */
    protected boolean isLogin(HttpSession session) {
        Object loginUser = session.getAttribute("loginUser");
        if (loginUser != null) {
            return true;
        } else {
            return false;
        }
    }

}
