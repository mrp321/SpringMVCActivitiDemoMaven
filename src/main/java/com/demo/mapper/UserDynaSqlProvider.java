package com.demo.mapper;

import org.apache.ibatis.jdbc.SQL;

import com.demo.entity.User;
import com.xiaoleilu.hutool.util.StrUtil;

/**
 * 用户相关动态sql查询
 * 
 * @date 2017-10-10
 * @author qchen
 * 
 * 
 */
public class UserDynaSqlProvider {

    /**
     * 查询用户
     * 
     * @return
     */
    public String queryUser() {
        return new SQL().SELECT("user_id, password").FROM("user")
                .WHERE("del_flag = 0").toString();
    }

    /**
     * 添加用户
     * 
     * @param user
     *            用户实体
     * 
     * @return
     */
    public String addUser() {
        return new SQL() {
            {
                INSERT_INTO("user");
                VALUES("user_id", "#{userId}");
                VALUES("password", "#{password}");
                VALUES("del_flag", "0");
                // 或者下面这种写法
                // VALUES("user_id, password, del_flag",
                // "#{userId}, #{password}, 0");
            }
        }.toString();
    }

    /**
     * 修改用户
     * 
     * @param user
     *            用户实体
     * @return
     */
    public String modiUser(final User user) {
        return new SQL() {
            {
                UPDATE("user");
                if (StrUtil.isNotEmpty(user.getPassword())) {
                    SET("password = #{password}");
                }
                SET("update_time = NOW()");
                WHERE("user_id = #{userId}");
                AND();
                WHERE("del_flag = 0");
            }
        }.toString();
    }

    /**
     * 删除用户
     * 
     * @param userId
     *            用户id
     * @return
     */
    public String delUser() {
        return new SQL() {
            {
                DELETE_FROM("user");
                WHERE("user_id = #{userId}");
                AND();
                WHERE("del_flag = 0");
            }
        }.toString();
    }

}
