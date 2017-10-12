package com.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.demo.entity.User;
import com.demo.service.UserService;
import com.demo.util.UserUtil;
import com.xiaoleilu.hutool.util.StrUtil;

/**
 * 用户控制层
 * 
 * @date 2017-10-10
 * @author qchen
 * 
 */
@Controller
@RequestMapping("/user/")
public class UserController extends CommController {
    /** 日志 */
    private static final Logger lg = Logger.getLogger(UserController.class);
    /** 用户服务层 */
    @Autowired
    private UserService userService;

    /**
     * 登录 http://localhost:8080/user/login.do?userId=admin&password=admin
     * 
     * @param session
     * @param userId
     *            用户id
     * @param password
     *            密码
     * @return
     */
    @RequestMapping("login")
    @ResponseBody
    public Map<String, Object> login(HttpSession session, String userId,
            String password) {
        lg.info("登录开始");
        Map<String, Object> map = new HashMap<String, Object>();
        if (StrUtil.isNotEmpty(userId) && StrUtil.isNotEmpty(password)) {
            try {
                User user = this.userService.login(userId, password);
                if (user != null) {
                    map = getSuccessMap("登录成功");
                    session.setAttribute("loginUser", user);
                } else {
                    map = getFailureMap("该用户不存在");
                }
            } catch (Exception e) {
                map = this.getFailureMap("登录失败");
                lg.error("登录失败，原因：" + e.getMessage());
            }
        } else {
            map = this.getEmptyParamsMap();
        }
        lg.info("登录结束");
        return map;
    }

    /**
     * 退出
     * 
     * @param session
     * @param userId
     *            用户id
     * @return
     */
    @RequestMapping("logout")
    @ResponseBody
    public Map<String, Object> logout(HttpSession session) {
        lg.info("退出开始");
        Map<String, Object> map = new HashMap<String, Object>();

        try {
            session.invalidate();
            map = getSuccessMap("退出成功");
        } catch (Exception e) {
            map = getFailureMap("退出失败");
            lg.error("退出失败, 原因：" + e.getMessage());
        }
        lg.info("退出结束");
        return map;
    }

    /**
     * 查询用户
     * 
     * @param session
     * @param userId
     *            用户id
     * @return
     */
    @RequestMapping("queryUser")
    @ResponseBody
    public Map<String, Object> queryUser(HttpSession session) {
        lg.info("查询用户开始");
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            boolean isLogin = this.isLogin(session);
            if (!isLogin) {
                return this.getInvalidSessionMap();
            }
            List<User> userList = this.userService.queryUser();
            map = getReturnMap(true, userList, "查询用户成功");
        } catch (Exception e) {
            map = getFailureMap("查询用户失败");
            lg.error("查询用户失败，失败信息：" + e.getMessage());
        }
        lg.info("查询用户结束");
        return map;
    }

    /**
     * 添加用户
     * 
     * @param session
     * @param userId
     *            用户id
     * @param password
     *            密码
     * @return
     */
    @RequestMapping("addUser")
    @ResponseBody
    public Map<String, Object> addUser(HttpSession session, String userId,
            String password) {
        lg.info("添加用户开始");
        Map<String, Object> map = new HashMap<String, Object>();
        if (StrUtil.isNotEmpty(userId) && StrUtil.isNotEmpty(password)) {
            try {
                boolean isLogin = this.isLogin(session);
                if (!isLogin) {
                    return this.getInvalidSessionMap();
                }
                int flag = this.userService.addUser(userId, password);
                if (flag > 0) {
                    map = this.getSuccessMap("添加用户成功");
                } else if (flag == UserUtil.USER_ALREADY_EXISTS) {
                    map = this.getFailureData("该用户已存在");
                } else {
                    map = this.getFailureMap("添加用户失败");
                }
            } catch (Exception e) {
                map = this.getFailureData("添加用户失败");
                lg.error("添加用户失败，原因：" + e.getMessage());
            }
        } else {
            map = this.getEmptyParamsMap();
        }
        lg.info("添加用户结束");
        return map;
    }

    /**
     * 修改用户
     * 
     * @param session
     * @param userId
     *            用户id
     * @param password
     *            密码
     * @return
     */
    @RequestMapping("modiUser")
    @ResponseBody
    public Map<String, Object> modiUser(HttpSession session, String userId,
            String password) {
        lg.info("修改用户开始");
        Map<String, Object> map = new HashMap<String, Object>();
        if (StrUtil.isNotEmpty(userId)) {
            try {
                boolean isLogin = this.isLogin(session);
                if (!isLogin) {
                    return this.getInvalidSessionMap();
                }
                int flag = this.userService.modiUser(userId, password);
                if (flag > 0) {
                    map = this.getSuccessMap("修改用户成功");
                } else {
                    map = this.getFailureMap("修改用户失败");
                }
            } catch (Exception e) {
                map = getFailureMap("修改用户失败");
                lg.error("修改用户失败， 原因：" + e.getMessage());
            }
        } else {
            map = this.getEmptyParamsMap();
        }
        lg.info("修改用户结束");
        return map;
    }

    /**
     * 删除用户
     * 
     * @param session
     * @param userId
     *            用户id
     * @return
     */
    @RequestMapping("delUser")
    @ResponseBody
    public Map<String, Object> delUser(HttpSession session, String userId) {
        lg.info("删除用户开始");
        Map<String, Object> map = new HashMap<String, Object>();
        if (StrUtil.isNotEmpty(userId)) {
            try {
                boolean isLogin = this.isLogin(session);
                if (!isLogin) {
                    return this.getInvalidSessionMap();
                }
                int flag = this.userService.delUser(userId);
                if (flag > 0) {
                    map = this.getSuccessMap("删除用户成功");
                } else {
                    map = this.getFailureMap("删除用户失败");
                }
            } catch (Exception e) {
                map = getFailureMap("删除用户失败");
                lg.error("删除用户失败， 原因：" + e.getMessage());
            }
        } else {
            map = this.getEmptyParamsMap();
        }
        lg.info("删除用户结束");
        return map;
    }
}
