// springmvc测试用js

var loginUrl = "/user/login.do";
var logoutUrl = "/user/logout.do";
var queryUserUrl = "/user/queryUser.do";
var addUserUrl = "/user/addUser.do";
var modiUserUrl = "/user/modiUser.do";
var delUserUrl = "/user/delUser.do";

var page = new Vue({
	el : "#page",
	data : {
		userList : [],
		loginMsg : "",
		loginOkFlag : false,
		addUserMsg : "",
		modiOrDelUserMsg : ""
	},
	mounted : function() {
		this.$nextTick(function() {

		})
	},
	methods : {
		login : function() {
			var userId = $("#userIdLogin").val();
			var password = $("#passwordLogin").val();
			console.log("userId:" + userId + "\npassword:" + password);
			$.ajax({
				type : "post",
				url : loginUrl,
				data : {
					userId : userId,
					password : password,
				},
				success : function(data) {
					page.loginMsg = data.msg;
					page.addUserMsg = "";
					page.modiOrDelUserMsg = "";
					if (data.success) {
						page.loginOkFlag = true;
						page.queryUser();
					} else {
						page.loginOkFlag = false;
					}
				}
			});
		},
		logout : function() {
			$.ajax({
				type : "post",
				url : logoutUrl,
				data : {},
				success : function(data) {
					page.loginMsg = data.msg;
					page.addUserMsg = "";
					page.modiOrDelUserMsg = "";
					page.loginOkFlag = false;
					$("#userIdLogin").val("");
					$("#passwordLogin").val("");
				}

			});
		},
		addUser : function() {
			var userId = $("#userIdAdd").val();
			var password = $("#passwordAdd").val();
			$.ajax({
				type : "post",
				url : addUserUrl,
				data : {
					userId : userId,
					password : password,
				},
				success : function(data) {
					page.addUserMsg = data.msg;
					if (data.success) {
						page.queryUser();

					}
					page.modiOrDelUserMsg = "";

				}
			})
		},
		modiUser : function(user, index) {
			var password = $("#passwordShow" + index).val();
			alert(password);
			$.ajax({
				type : "post",
				url : modiUserUrl,
				data : {
					userId : user.userId,
					password : password,
				},
				success : function(data) {
					page.modiOrDelUserMsg = data.msg;
					if (data.success) {
						page.queryUser();
					}

				}
			})
		},
		delUser : function(user) {
			$.ajax({
				type : "post",
				url : delUserUrl,
				data : {
					userId : user.userId,
				},
				success : function(data) {
					page.modiOrDelUserMsg = data.msg;
					if (data.success) {
						page.queryUser();
					}

				}
			})
		},
		queryUser : function() {
			$.ajax({
				type : "get",
				url : queryUserUrl,
				data : {},
				success : function(data) {

					if (data.success) {
						page.userList = data.data;
					}
				}
			})
		},
	}
})