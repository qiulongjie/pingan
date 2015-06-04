<%@ page contentType="text/html;charset=UTF-8"%><%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%><c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
 <meta>
    <meta content="telephone=no" name="format-detection" />
    <meta content="email=no" name="format-detection" />
    <meta name="viewport" content="initial-scale=1.0, maximum-scale=1.0, user-scalable=no,width=device-width" />
    <title>领奖成功</title>
<%--     <link rel="stylesheet" type="text/css" href="${ctx }/static/styles/main2.css"> --%>
    <link rel="stylesheet" type="text/css" href="http://rs.iadcn.com/pingan/static/zhuanpan/styles/main2.css">
    <script type="text/javascript" src="http://rs.iadcn.com/pingan/static/zhuanpan/js/jquery-1.7.1.min.js"></script>
    <script type="text/javascript" src="http://rs.iadcn.com/pingan/static/zhuanpan/js/jquerysession.js"></script>
</head>


<body style="height:100%;">
	<div class="banner">
<%-- 		<img src="${ctx }/static/images/ltyox/banner.jpg" width="100%"/> --%>
		<img src="http://rs.iadcn.com/pingan/static/zhuanpan/images/ltyox/banner.jpg" width="100%"/>
	</div>
	
  <div class="con2">
   		<div class="gongxi">
			<h2 class="scu">恭喜您！</h2>
			<p id="ok_title">${ok_title }</p>
			
			<div id="ok_info" class="scu_text">
				${ok_info }
			</div>
		
		</div>
    </div>
</body>

<script>
$(function() {
	
	var ok_title = $.session.get('ok_title');
	var ok_info = $.session.get('ok_info');
	
	$('#ok_title').html(ok_title);
	$('#ok_info').html(ok_info);
})
</script>
</html>