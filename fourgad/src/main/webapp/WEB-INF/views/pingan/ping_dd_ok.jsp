<%@ page contentType="text/html;charset=UTF-8"%><%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%><c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
 <meta>
    <meta content="telephone=no" name="format-detection" />
    <meta content="email=no" name="format-detection" />
    <meta name="viewport" content="initial-scale=1.0, maximum-scale=1.0, user-scalable=no,width=device-width" />
    <title>交通意外险</title>
<%-- <link rel="stylesheet" type="text/css" href="${ctx }/static/styles/ping_dd.css"> --%>
<link rel="stylesheet" type="text/css" href="http://rs.iadcn.com/pingan/static/zhuanpan/styles/ping_dd.css">
<script type="text/javascript" src="http://rs.iadcn.com/pingan/static/zhuanpan/js/baidu_count.js"></script>
</head>


<body>
<div><img src="http://rs.iadcn.com/pingan/static/zhuanpan/images/didi/banner2.jpg" width="100%" /></div>
<div class="chengong_con">
	<div class="chengong_box">
		<h3>感谢您申请领取了免费交通意外险</h3>
		<div class="text">
			我们会在一个月内将保单的相关信息用短信或者电话通知您。
		</div>
		<div>
			<input name="" type="button" value="免费领取" class="lingqu_btn" onclick="gotoHao()"/>
		</div>
		
		<div style="text-align:center; color:#d7513f;">点击领取滴滴红包，让您的出行更任性</div>
	</div>
</div>
</body>
<script>
function gotoHao(){
	window.location.href='http://gsactivity.diditaxi.com.cn/gulfstream/activity/v2/giftpackage/index?channel=f7f6ffe39931e139fde3e91f382799a4';
}
</script>
</html>