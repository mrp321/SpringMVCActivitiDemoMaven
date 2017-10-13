package com.demo.service;

import java.util.List;

import com.demo.entity.MyProc;
import com.demo.entity.MyTask;

public interface ActivitiService {

	/**
	 * 开启工作流
	 * 
	 * @param userId
	 *            用户id
	 * @param money
	 *            报销金额
	 * @return
	 */
	MyProc startWorkFlow(String userId, String money);

	/**
	 * 查询任务
	 * 
	 * @param userId
	 *            用户id
	 * @return
	 */
	List<MyTask> queryTask(String userId);

	/**
	 * 办理任务
	 * 
	 * @param userId
	 *            用户id
	 * @param taskId
	 *            任务id
	 */
	void completeTask(String userId, String taskId);

}
