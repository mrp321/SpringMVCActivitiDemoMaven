package com.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.demo.entity.MyProc;
import com.demo.entity.MyTask;
import com.demo.service.ActivitiService;
import com.xiaoleilu.hutool.util.StrUtil;

@Controller
@RequestMapping("activiti")
public class ActivitiController extends CommController {
	private static final Logger lg = Logger.getLogger(ActivitiController.class);
	@Autowired
	private ActivitiService activitiService;

	/**
	 * 开启工作流
	 * 
	 * @param userId
	 *            用户id
	 * @return
	 */
	@RequestMapping("startWorkFlow")
	@ResponseBody
	public Map<String, Object> startWorkFlow(String userId, String money) {
		lg.info("开启工作流开始");
		Map<String, Object> map = new HashMap<String, Object>();
		if (StrUtil.isNotEmpty(userId)) {
			try {
				MyProc proc = this.activitiService.startWorkFlow(userId, money);
				map = this.getReturnMap(true, proc, "开启工作流成功");
			} catch (Exception e) {
				map = this.getFailureMap("开启工作流失败，信息：" + e.getMessage());
			}
		} else {
			map = this.getEmptyParamsMap();
		}
		lg.info("开启工作流结束");
		return map;
	}

	/**
	 * 查询任务
	 * 
	 * @param userId
	 *            用户id
	 * @return
	 */
	@RequestMapping("queryTask")
	@ResponseBody
	public Map<String, Object> queryTask(String userId) {
		lg.info("查询任务开始");
		Map<String, Object> map = new HashMap<String, Object>();
		if (StrUtil.isNotEmpty(userId)) {
			try {
				List<MyTask> taskList = this.activitiService.queryTask(userId);
				map = this.getReturnMap(true, taskList, "查询任务成功");
			} catch (Exception e) {
				map = this.getFailureMap("查询任务失败，信息：" + e.getMessage());
			}
		} else {
			map = this.getEmptyParamsMap();
		}
		lg.info("查询任务结束");
		return map;
	}

	/**
	 * 办理任务
	 * 
	 * @param userId
	 *            用户id
	 * @return
	 */
	@RequestMapping("completeTask")
	@ResponseBody
	public Map<String, Object> completeTask(String userId, String taskId) {
		lg.info("办理任务开始");
		Map<String, Object> map = new HashMap<String, Object>();
		if (StrUtil.isNotEmpty(userId) && StrUtil.isNotEmpty(taskId)) {
			try {
				this.activitiService.completeTask(userId, taskId);
				map = this.getSuccessMap("办理任务成功");
			} catch (Exception e) {
				map = getFailureData("办理任务失败,信息：" + e.getMessage());
			}
		} else {
			map = this.getEmptyParamsMap();
		}
		return map;
	}
}
