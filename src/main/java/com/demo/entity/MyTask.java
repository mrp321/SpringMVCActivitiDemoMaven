package com.demo.entity;

public class MyTask {
	private String taskId;
	private String taskName;

	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	@Override
	public String toString() {
		return "MyTask [taskId=" + taskId + ", taskName=" + taskName + "]";
	}

}
