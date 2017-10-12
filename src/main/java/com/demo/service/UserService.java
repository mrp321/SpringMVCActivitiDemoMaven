package com.demo.service;

import java.util.List;

import com.demo.entity.User;

/**
 * 用户服务层
 * 
 * @date 2017-10-10
 * @author qchen
 * 
 */
public interface UserService {

    /**
     * 登录
     * 
     * @param userId
     *            用户id
     * @param password
     *            密码
     * @return
     */
    User login(String userId, String password);

    /**
     * 查询用户
     * 
     * @return
     */
    List<User> queryUser();

    /**
     * 添加用户
     * 
     * @param userId
     *            用户id
     * @param password
     *            密码
     * @return
     */
    int addUser(String userId, String password);

    /**
     * 修改用户
     * 
     * @param userId
     *            用户id
     * @param password
     *            密码
     * @return
     */
    int modiUser(String userId, String password);

    /**
     * 删除用户
     * 
     * @param userId
     *            用户id
     * @return
     */
    int delUser(String userId);

    /**
     * 日志记录
     * 
     * @param url
     *            请求地址
     * @param userId
     *            用户id
     */
    int addLog(String url, String userId);

    /**
     * 将超过指定天数的日志转移到日志记录历史表中
     * 
     * @param logOverDays
     *            超过的天数
     * @return
     */
    int tranferLogToHistory(int logOverDays);

    /**
     * 清除log表中超过指定天数的日志
     * 
     * @param logOverDays
     *            超过的天数
     * @return
     */
    int delLogOverDays(int logOverDays);

}
