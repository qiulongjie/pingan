<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<html>
<head>
	<title>任务管理</title>
</head>

<body>
	<c:if test="${not empty message}">
		<div id="message" class="alert alert-success"><button data-dismiss="alert" class="close">×</button>${message}</div>
	</c:if>
	<div class="row">
		<div class="span4 offset7">
			<form class="form-search" action="#">
				<label>名称：</label> <input type="text" name="search_LIKE_title" class="input-medium" value="${param.search_LIKE_title}"> 
				<button type="submit" class="btn" id="search_btn">Search</button>
		    </form>
	    </div>
	    <tags:sort/>
	</div>
	
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>任务</th>
				<th>日有效订单阀值</th>
				<th>日传输订单阀值</th>
				<th>状态</th>
				<th>管理</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${tasks.content}" var="task">
			<tr>
				<td><a href="${ctx}/task/update/${task.id}">${task.title}</a></td>
				<td>${task.dayMaxNum}</td>
				<td>${task.dayTransMax}</td>
				<td>
				 <c:if test="${task.status == 0}">暂停中</c:if>
				 <c:if test="${task.status == 1}">运行中</c:if>
				</td>
				<td>
				  <c:if test="${task.status == 0}"><a href="${ctx}/task/updateStatus/${task.id}/1" onClick="return confirm('确定运行该任务?');">运行</a></c:if>
				  <c:if test="${task.status == 1}"><a href="${ctx}/task/updateStatus/${task.id}/0" onClick="return confirm('确定暂停该任务?');">暂停</a></c:if>
				  &nbsp;&nbsp;&nbsp;
				  <a href="${ctx}/task/delete/${task.id}" onClick="return confirm('确定删除并停止该任务?');">删除</a>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	
	<tags:pagination page="${tasks}" paginationSize="5"/>

	<div>
	  <a class="btn" href="${ctx}/task/refureshTasks">刷新任务缓存</a>
	  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	 <a class="btn" href="${ctx}/task/create">创建任务</a>
	 </div>
</body>
</html>
