<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>SpringMVC 测试</title>
<style type="text/css">
span {
	color: red;
}
</style>
</head>
<body>
	<div id="page">

		<!-- 登录页 -->
		<p>xxxxxxxxxx用户登录页xxxxxxxxxx</p>
		<div id="loginPage">
			<form action="" method="post">
				用户id:<input type="text" id="userIdLogin"><br> 密码：<input
					type="text" id="passwordLogin"><br> <input
					type="button" value="登录" @click="login()"> <input
					type="button" value="退出" @click="logout()">
			</form>
			<span>{{ loginMsg }}</span>
		</div>

		<!-- 添加用户页 -->
		<p>xxxxxxxxxx用户添加页xxxxxxxxxx</p>
		<div id="addUserPage" v-if="loginOkFlag">

			用户id:<input type="text" id="userIdAdd"><br> 密码：<input
				type="text" id="passwordAdd"><br> <input type="button"
				value="添加" @click="addUser()">
		</div>
		<span>{{ addUserMsg }}</span>
		<!-- 用户列表展示页 -->
		<p>xxxxxxxxxx用户展示页xxxxxxxxxx</p>
		<div id="userPage" v-if="loginOkFlag">
			<table border="1" cellpadding="0">
				<tr>
					<th>用户id</th>
					<th>密码</th>
					<th>操作</th>
				</tr>
				<tr v-for="(user,index) in userList">
					<td><input type="text" :value="user.userId"
						readonly="readonly" id="userIdShow"></td>
					<td><input type="text" :value="user.password"
						:id="'passwordShow' + index"></td>
					<td><input type="button" value="修改"
						@click="modiUser(user, index)"><input type="button"
						value="删除" @click="delUser(user)"></td>
				</tr>
			</table>
			<span>{{ modiOrDelUserMsg }}</span>
		</div>
	</div>
</body>
<script type="text/javascript" src="js/jquery-1.10.1.min.js"></script>
<script type="text/javascript" src="js/vue.js"></script>
<script type="text/javascript" src="js/user.js"></script>
</html>