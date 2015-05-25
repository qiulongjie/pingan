<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0,user-scalable=no">
<title>中国平安车险计算结果</title>

<link rel="stylesheet" type="text/css" href="${ctx }/static/styles/main_cca.css">
<script type="text/javascript" src="http://rs.iadcn.com/pingan/static/js/jquery-1.7.1.min.js"></script>

</head>

<body>
   <div class="banner">
		<img src="${ctx }/static/images/cca/BANNER2.jpg"  width="100%"/>
	</div>
	
	<div class="con" >
		<div class="conbox">
			<h3 class="jisuan_tit">2015年车险计算器</h3>
			<div>
				<div class="jieguo">
					<p>根据您的车辆信息，您的车险报价为：</p>
					<strong class="jieguotext red">￥${d }起</strong>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<a href="http://www.4008000000.com/cpchexian/media/mttf.shtml?WT.mc_id=c03-SUYI-s10056">获取精准报价</a>
				</div>
				<div class="gongxi_box" style="display:none">
					<h4 class="red">恭喜您，领取成功！</h4>
					<p>您成功领取驾驶员意外险一份，中国平安客服将在三个工作日内与您确认信息，并发送电子保单号。</p>
				</div>
			</div>
		</div>
	</div>
</body>
<script>
$(function(){
	
	var x = '${x }';
	if(x === '1'){
	   $('.gongxi_box').show('fast');
	}
});
</script>
</html>

