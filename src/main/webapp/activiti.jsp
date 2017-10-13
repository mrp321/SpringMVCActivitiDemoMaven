<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>activiti测试</title>
</head>
<body>
	<div id="page">
		<p>xxxxx报销申请录入xxxxx</p>
		<div id="applyPage">
			用户id:<input type="text" id="userId"><br> 报销金额:<input
				type="text" id="money"><br> <input type="button"
				value="报销" @click="apply()">
		</div>
		<p>xxxxx用户任务列表xxxxx</p>
		<ul v-for="(task, index) in taskList">
			<li>{{task.taskId}}:{{task.taskName}}<input type="button"
				value="办理任务" @click="completeTask(curUserId, task.taskId)"></li>
		</ul>
		<p>xxxxx审批1：张三任务列表xxxxx</p>
		<ul v-for="(task, index) in taskListOfZhangsan">
			<li>{{task.taskId}}:{{task.taskName}}<input type="button"
				value="办理任务" @click="completeTask('zhangsan', task.taskId)"></li>
		</ul>
		<p>xxxxx审批2：李四任务列表xxxxx</p>
		<ul v-for="(task, index) in taskListOfLisi">
			<li>{{task.taskId}}:{{task.taskName}}<input type="button" value="办理任务"
				@click="completeTask('lisi', task.taskId)"></li>
		</ul>
	</div>
	<script type="text/javascript" src="js/vue.js"></script>
	<script type="text/javascript" src="js/jquery-1.10.1.min.js"></script>
	<script type="text/javascript" src="js/activiti.js"></script>
</body>
</html>