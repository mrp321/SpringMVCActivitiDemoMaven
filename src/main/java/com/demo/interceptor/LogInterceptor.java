package com.demo.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.demo.entity.User;
import com.demo.service.UserService;

/**
 * 日志拦截器
 * 
 * @date 2017-10-10
 * @author qchen
 * 
 * 
 */
public class LogInterceptor implements HandlerInterceptor {

	@Autowired
	private UserService userService;

	@Override
	public void afterCompletion(HttpServletRequest arg0,
			HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {

	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2, ModelAndView arg3) throws Exception {

	}

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object obj) throws Exception {
		// 定义用户id
		String userId = null;
		// 请求地址
		String url = request.getRequestURI();
		// session中的loginUser
		Object loginUser = request.getSession().getAttribute("loginUser");
		// 取出userId
		if (loginUser != null) {
			userId = ((User) loginUser).getUserId();
		}
		// 日志记录
		this.userService.addLog(url, userId);
		return true;
	}

}
