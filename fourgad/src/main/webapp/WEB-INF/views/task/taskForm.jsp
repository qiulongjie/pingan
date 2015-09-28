<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
	<title>任务管理</title>
</head>
<body>
	<form id="inputForm" action="${ctx}/task/${action}" method="post" class="form-horizontal">
		<input type="hidden" name="id" value="${task.id}"/>
		<input type="hidden" name="status" value="${task.status}"/>
		<fieldset>
			<legend><small>管理任务</small></legend>
			<div class="control-group">
				<label for="task_title" class="control-label">任务名称:</label>
				<div class="controls">
					<input type="text" id="task_title" name="title"  value="${task.title}" class="input-large required" minlength="3"/>
				</div>
			</div>	
			<div class="control-group">
				<label for="description" class="control-label">任务描述:</label>
				<div class="controls">
					<textarea id="description" name="description" class="input-large">${task.description}</textarea>
				</div>
			</div>
			<div class="control-group">
				<label for="task_title" class="control-label">任务KEY:</label>
				<div class="controls">
					<input type="text" id="task_key" name="taskKey"  value="${task.taskKey}" class="input-large required" minlength="3"/>
				</div>
			</div>	
			<div class="control-group">
				<label for="task_title" class="control-label">日传输订单阀值:</label>
				<div class="controls">
					<input type="text" id="day_trans_max" name="dayTransMax"  value="${task.dayTransMax}" class="input-large"/>
				</div>
			</div>	
			<div class="control-group">
				<label for="task_title" class="control-label">日有效订单阀值:</label>
				<div class="controls">
					<input type="text" id="day_max_num" name="dayMaxNum"  value="${task.dayMaxNum}" class="input-large"/>
				</div>
			</div>	
			<div class="description">
				<label for="task_title" class="control-label">阀值获取sql:</label>
				<div class="controls">
					<textarea id="ord_sql" name="ordSql" class="input-large">${task.ordSql}</textarea>
				</div>
			</div>	
			<div class="form-actions">
				<input id="submit_btn" class="btn btn-primary" type="submit" value="提交"/>&nbsp;	
				<input id="cancel_btn" class="btn" type="button" value="返回" onclick="history.back()"/>
			</div>
		</fieldset>	
	</form>
	<script>
		$(document).ready(function() {
			//聚焦第一个输入框
			$("#task_title").focus();
			//为inputForm注册validate函数
			$("#inputForm").validate();
		});
	</script>
</body>
</html>
