// 开启工作流
var applyUrl = "/activiti/startWorkFlow.do";
// 查询任务
var queryTaskUrl = "/activiti/queryTask.do";
// 办理任务
var completeTaskUrl = "/activiti/completeTask.do";
var page = new Vue({
	el : "#page",
	data : {
		taskListOfZhangsan : [],
		taskListOfLisi : [],
		taskList : [],
		curUserId : ""
	},
	mounted : function() {
		this.$nextTick(function() {
			page.queryTask("zhangsan");
			page.queryTask("lisi");
		})
	},
	methods : {
		// 开启工作流
		apply : function() {
			var userId = $("#userId").val();
			var money = $("#money").val();
			if (userId == "" || money == "") {
				alert("输入项不能为空");
				return;
			}
			$.ajax({
				type : "post",
				url : applyUrl,
				data : {
					userId : userId,
					money : money
				},
				success : function(data) {
					if (data.success) {
						page.queryTask(userId);
						page.curUserId = userId;
					}
				}
			})
		},
		// 办理任务
		completeTask : function(userId, taskId) {
			$.ajax({
				type : "post",
				url : completeTaskUrl,
				data : {
					userId : userId,
					taskId : taskId,
				},
				success : function(data) {
						page.queryTask("zhangsan");
						page.queryTask("lisi");
						page.queryTask(page.curUserId);
				}
			})
		},
		// 查询任务
		queryTask : function(userId) {
			$.ajax({
				type : "get",
				url : queryTaskUrl,
				data : {
					userId : userId,
				},
				success : function(data) {
					if (data.success) {
						switch (userId) {
						case "zhangsan":
							page.taskListOfZhangsan = data.data;
							break;
						case "lisi":
							page.taskListOfLisi = data.data;
							break;
						default:
							page.taskList = data.data;
							break;
						}
					}
				}
			})
		}
	},

})