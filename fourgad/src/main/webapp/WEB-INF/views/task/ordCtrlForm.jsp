<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
	<title>订单生成开关管理</title>
</head>
<body>
	<form id="inputForm" action="${ctx}/ordCtrl/${action}" method="post" class="form-horizontal">
		<input type="hidden" name="id" value="${ordCtrl.id}"/>
		<input type="hidden" name="id" value="${ordCtrl.crtOrdStatus}"/>
		<fieldset>
			<legend><small>管理任务</small></legend>
			<div class="control-group">
				<label for="task_title" class="control-label">订单类型:</label>
				<div class="controls">
					<input type="text" id="ord_title" name="ordTitle"  value="${ordCtrl.ordTitle}" class="input-large required"/>
				</div>
			</div>	
			<div class="control-group">
				<label for="task_title" class="control-label">订单KEY:</label>
				<div class="controls">
					<input type="text" id="ord_key" name="ordKey"  value="${ordCtrl.ordKey}" class="input-large required"/>
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
