package com.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.demo.dao.UserDao;
import com.demo.entity.User;
import com.demo.service.UserService;
import com.demo.util.UserUtil;

/**
 * 用户服务实现层
 * 
 * @date 2017-10-10
 * @author qchen
 * 
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {
    /** 用户dao层 */
    @Autowired
    private UserDao userDao;

    /**
     * 登录
     * 
     * @param userId
     *            用户id
     * @param password
     *            密码
     * @return
     */
    @Override
    public User login(String userId, String password) {
        return this.userDao.login(userId, password);
    }

    /**
     * 查询用户
     * 
     * @return
     */
    @Override
    public List<User> queryUser() {
        return this.userDao.queryUser();
    }

    /**
     * 添加用户
     * 
     * @param userId
     *            用户id
     * @param password
     *            密码
     * @return
     */
    @Override
    public int addUser(String userId, String password) {
        // 根据用户id查询用户
        User userByUserId = this.userDao.queryUserByUserId(userId);
        // 如果用户不存在，则添加用户
        if (userByUserId != null) {
            return UserUtil.USER_ALREADY_EXISTS;
        } else {
            User user = new User(userId, password);
            return this.userDao.addUser(user);
        }
    }

    /**
     * 修改用户
     * 
     * @param userId
     *            用户id
     * @param password
     *            密码
     * @return
     */
    @Override
    public int modiUser(String userId, String password) {
        User user = new User(userId, password);
        return this.userDao.modiUser(user);
    }

    /**
     * 删除用户
     * 
     * @param userId
     *            用户id
     * @return
     */
    @Override
    public int delUser(String userId) {
        return this.userDao.delUser(userId);
    }

    /**
     * 日志记录
     * 
     * @param url
     *            请求地址
     * @param userId
     *            用户id
     */
    @Override
    public int addLog(String url, String userId) {
        return this.userDao.addLog(url, userId);
    }

    /**
     * 将超过指定天数的日志转移到日志记录历史表中
     * 
     * @param logOverDays
     *            超过的天数
     * @return
     */
    @Override
    public int tranferLogToHistory(int logOverDays) {

        return this.userDao.tranferLogToHistory(logOverDays);
    }

    /**
     * 清除log表中超过指定天数的日志
     * 
     * @param logOverDays
     *            超过的天数
     * @return
     */
    @Override
    public int delLogOverDays(int logOverDays) {
        return this.userDao.delLogOverDays(logOverDays);
    }

}
