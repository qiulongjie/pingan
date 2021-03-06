<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<div id="header">
	<div id="title">
	    <h1><a href="${ctx}">平安接口数据统计</a><small></small>
	    <shiro:user>
			<div class="btn-group pull-right">
				<a class="btn dropdown-toggle" data-toggle="dropdown" href="#">
					<i class="icon-user"></i> <shiro:principal property="name"/>
					<span class="caret"></span>
				</a>
			
				<ul class="dropdown-menu">
					<shiro:hasRole name="admin">
						<li><a href="${ctx}/admin/user">Admin Users</a></li>
						<li class="divider"></li>
					</shiro:hasRole>
<%-- 					<li><a href="${ctx}/api">APIs</a></li> --%>
					<li><a href="${ctx}/count/otherOrdList">其他数据统计</a></li>	
                    <li><a href="${ctx}/count/reqOrdList">访问数据</a></li>
                    <li><a href="${ctx}/count/ordList">访问数据-按天</a></li>
                    <li><a href="${ctx}/count/zjList">中奖结果</a></li>
                    <li><a href="${ctx}/count/antiCheatingList">作弊分析数据</a></li>
                    <li><a href="${ctx}/count/channelList">渠道信息</a></li>
                    <li><a href="${ctx}/count/addChannel">渠道注册</a></li>
                    <li><a href="${ctx}/count/channelFeeList">渠道结算数据</a></li>
                    <li><a href="${ctx}/count/ddhDataList">大都会数据</a></li>
                    <li><a href="${ctx}/count/ordDetailList">订单详情</a></li>
                    <li><a href="${ctx}/count/okClickCountList">红包点击统计</a></li>
<%--                     <li><a href="${ctx}/count/execCommand">执行命令</a></li> --%>
                    <li><a href="${ctx}/task">任务管理</a></li>
					<li><a href="${ctx}/profile">修改密码</a></li>
					<li><a href="${ctx}/logout">退出</a></li>
				</ul>
			</div>
		</shiro:user>
		</h1>
	</div>
</div>