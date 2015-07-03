<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<html>
<head>
	<title>注册渠道信息</title>
	
	<script>
		$(document).ready(function() {
			//聚焦第一个输入框
			$("#channel").focus();
			$('.input-datepicker').datepicker({
				language: 'cn',
				autoclose: true, 
			    format: 'yyyy-mm-dd',
			    todayHighlight:true
			});
			
			$("#inputForm").validate({
			});
		});
	</script>
</head>

<body>
    <c:if test="${not empty message}">
		<div id="message" class="alert alert-warn"><button data-dismiss="alert" class="close">×</button>${message}</div>
	</c:if>
	<form id="inputForm" action="${ctx}/count/addChannel" method="post" class="form-horizontal">
		<fieldset>
			<legend><small>注册渠道</small></legend>
			<div class="control-group">
				<label for="channel" class="control-label">渠道:</label>
				<div class="controls">
					<input type="text" id="channel" name="channel" value="${channel }" class="input-large required" minlength="1"/>
				</div>
			</div>
			<div class="control-group">
				<label for="busy_name" class="control-label">商务:</label>
				<div class="controls">
					<input type="text" id="busy_name" name="busy_name" value="${busy_name }" class="input-large required"/>
				</div>
			</div>
			<div class="control-group">
				<label for="customer" class="control-label">客户:</label>
				<div class="controls">
					<input type="text" id="customer" name="customer" value="${customer }" class="input-large required"/>
				</div>
			</div>
			<div class="control-group">
				<label for="start_time" class="control-label">生效时间:</label>
				<div class="controls">
					<input id="start_time" name="start_time"  value="${start_time }" type="text" class="input-medium input-datepicker input-large required"/>
				</div>
			</div>
			<div class="control-group">
				<label for="end_time" class="control-label">终止时间:</label>
				<div class="controls">
					<input id="end_time" name="end_time"  value="${end_time }" type="text" class="input-medium input-datepicker input-large"/>
				</div>
			</div>
			
			<div class="form-actions">
				<input id="submit_btn" class="btn btn-primary" type="submit" value="提交"/>&nbsp;	
				<input id="cancel_btn" class="btn" type="button" value="返回" onclick="history.back()"/>
			</div>
		</fieldset>
	</form>
</body>
</html>
