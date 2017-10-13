package com.demo.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.DeploymentBuilder;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.repository.ProcessDefinitionQuery;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.entity.MyProc;
import com.demo.entity.MyTask;
import com.demo.service.ActivitiService;

@Service
public class ActivitiServiceImpl implements ActivitiService {

	private String procDefKey = "bxlc";
	@Autowired
	private RepositoryService repositoryService;
	@Autowired
	private RuntimeService runtimeService;
	@Autowired
	private TaskService taskService;

	@Override
	public MyProc startWorkFlow(String userId, String money) {
		// 查询流程定义列表
		List<ProcessDefinition> procDefList = this.queryProcDef();
		// 如果流程定义列表为空，就重新部署流程定义
		if (procDefList.isEmpty()) {
			this.deployProcDef();
		}
		// 设置流程变量
		Map<String, Object> variables = this.setVariables(userId, money);
		// 启动流程实例
		ProcessInstance procInst = this.startProcInst(variables);
		String procInstId = procInst.getId();
		String procDefId = procInst.getProcessDefinitionId();
		MyProc proc = new MyProc();
		proc.setProcDefId(procDefId);
		proc.setProcInstId(procInstId);
		return proc;
	}

	/**
	 * 部署流程定义
	 */
	public Deployment deployProcDef() {
		DeploymentBuilder builder = this.repositoryService.createDeployment();
		builder.addClasspathResource("/com/demo/bpmn/Activiti.bpmn");
		builder.addClasspathResource("/com/demo/bpmn/Activiti.png");
		Deployment deployment = builder.deploy();
		return deployment;
	}

	/**
	 * 查询流程定义
	 */
	public List<ProcessDefinition> queryProcDef() {
		ProcessDefinitionQuery query = this.repositoryService
				.createProcessDefinitionQuery();
		query.processDefinitionKey(procDefKey);
		List<ProcessDefinition> procDefList = query.list();
		return procDefList;
	}

	/**
	 * 启动流程实例
	 */
	public ProcessInstance startProcInst(Map<String, Object> variables) {
		ProcessInstance procInst = this.runtimeService
				.startProcessInstanceByKey(procDefKey, variables);
		return procInst;
	}

	/**
	 * 查询任务
	 * 
	 * @param userId
	 *            用户id
	 * @return
	 */
	@Override
	public List<MyTask> queryTask(String userId) {
		TaskQuery query = this.taskService.createTaskQuery();
		// 设置办理人
		query.taskAssignee(userId);
		List<Task> taskList = query.list();
		List<MyTask> myTaskList = new ArrayList<MyTask>();
		for (Task task : taskList) {
			String taskId = task.getId();
			String taskName = task.getName();
			MyTask myTask = new MyTask();
			myTask.setTaskId(taskId);
			myTask.setTaskName(taskName);
			myTaskList.add(myTask);
		}
		return myTaskList;
	}

	/**
	 * 办理任务
	 * 
	 * @param userId
	 *            用户id
	 * @param taskId
	 *            任务id
	 */
	@Override
	public void completeTask(String userId, String taskId) {
		this.taskService.complete(taskId);

	}

	/**
	 * 设置流程变量
	 * 
	 * @param userId
	 *            用户id
	 * @param money
	 *            报销金额
	 * @return
	 */
	private Map<String, Object> setVariables(String userId, String money) {
		Map<String, Object> variablesMap = new HashMap<String, Object>();
		variablesMap.put("userId", userId);
		variablesMap.put("money", money);
		return variablesMap;
	}

}
