package com.demo.dao;

import java.util.List;

import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;
import org.springframework.stereotype.Repository;

import com.demo.entity.User;
import com.demo.mapper.UserDynaSqlProvider;

/**
 * 用户dao层
 * 
 * @date 2017-10-10
 * @author qchen
 * 
 * 
 */
@Repository
public interface UserDao {
    /**
     * 登录
     * 
     * @param userId
     *            用户id
     * @param password
     *            密码
     * @return
     */
    @Select("SELECT user_id, password FROM user WHERE user_id = #{userId} AND password = #{password} AND del_flag = 0")
    @Results({ @Result(property = "userId", column = "user_id"),
            @Result(property = "password", column = "password") })
    User login(@Param("userId") String userId,
            @Param("password") String password);

    /**
     * 查询用户
     * 
     * @return
     */
    @SelectProvider(type = UserDynaSqlProvider.class, method = "queryUser")
    @Results({ @Result(property = "userId", column = "user_id"),
            @Result(property = "password", column = "password") })
    List<User> queryUser();

    /**
     * 添加用户
     * 
     * @param user
     *            用户实体
     * 
     * @return
     */
    @InsertProvider(type = UserDynaSqlProvider.class, method = "addUser")
    int addUser(User user);

    /**
     * 修改用户
     * 
     * @param userId
     *            用户id
     * @param password
     *            密码
     * @return
     */
    @UpdateProvider(type = UserDynaSqlProvider.class, method = "modiUser")
    int modiUser(User user);

    /**
     * 删除用户
     * 
     * @param userId
     *            用户id
     * @return
     */
    @DeleteProvider(type = UserDynaSqlProvider.class, method = "delUser")
    int delUser(String userId);

    /**
     * 根据userId查询用户
     * 
     * @param userId
     *            用户id
     * @return
     */
    User queryUserByUserId(String userId);

    /**
     * 日志记录
     * 
     * @param url
     *            请求地址
     * @param userId
     *            用户id
     */
    @Insert("INSERT INTO log(url, user_id, del_flag) VALUES(#{url}, #{userId}, 0)")
    int addLog(@Param("url") String url, @Param("userId") String userId);

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
