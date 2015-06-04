<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0,user-scalable=no">
<title>填写信息领取大礼</title>
<meta name="keywords" content="Array" >
<meta name="description" content="中国平安大礼包，25万元意外险免费送，先到先得！">

<%-- <link rel="stylesheet" type="text/css" href="${ctx }/static/styles/main_as.css"> --%>
<link rel="stylesheet" type="text/css" href="http://rs.iadcn.com/pingan/static/zhuanpan/styles/main_as.css">
<script type="text/javascript" src="http://rs.iadcn.com/pingan/static/js/jquery-1.7.1.min.js"></script>
<%-- <script type="text/javascript" src="${ctx}/static/js/form_as.js"></script> --%>
<script type="text/javascript" src="http://rs.iadcn.com/pingan/static/zhuanpan/js/form_as.js"></script>
<%-- <script type="text/javascript" src="${ctx }/static/js/jquerysession.js"></script> --%>
<script type="text/javascript" src="http://rs.iadcn.com/pingan/static/zhuanpan/js/jquerysession.js"></script>

</head>

<body>

<div id="submitFormDiv" style="display:none;">
		<div class="blackbg"></div>
		<div class="tanchuang">
			<div id="alert_text" class="tanchuang_input" style="color:#666;">
				</div>
			<div class="queding_btn">
				<a id="queding_btn_xiaoshi" href="#">确&nbsp;&nbsp;&nbsp;定</a>
			</div>

		</div>
</div>

<div class="banner">
		<%-- <img src="${ctx }/static/images/ltyox/banner.jpg" width="100%"/> --%>
		<img src="" width="100%" id="banner_link"/>
	</div>
	
  <div class="con3">
   	  <div class="conbox2">
       	<h3>填写信息领取大礼</h3>
		<div>
		<form action="${ctx}/lty/addRewardInfo" name="form1" method="get">
		
		   <input name="a" type="hidden" value="${a }" />
		  <input id="lty_angle" name="lty_angle" type="hidden" value="" />
		  <input id="ok_title" name="ok_title" type="hidden" value="" />
		  <input id="path" name="path" type="hidden" value=ltyzjok />
		   
			<p class="red">请务必填写真实信息，以保障您领取大奖</p>
			<div class="info">
				<p><span>姓名：</span><input id="txtName" name="uname" type="text" class="m" placeholder="请填写您的真实姓名"/></p>
				<p><span>手机：</span><input id="txtMobile" name="phone" type="text" class="m" placeholder="请填写接收奖品的手机号码"/></p>
				<p><span>地址：</span><input id="address" name="address" type="text" class="m" placeholder="请填写您的真实地址"/></p>
			</div>
			<div>
				<!-- <a href="#" class="lingqu_btn">立即领取</a> -->
				<input type="button" class="lingqu_btn" value="立即领取" onclick="checkForm('s')" />
			</div>
			<p>*温馨提示：请提供真实详细的邮寄地址和手机号码，在您中奖后我司会在2-3个工作日和您联系。<br />
			</p>
		    <p></p>
		 </form>
		</div>
		<br class="clear"/>
      </div>
	  

    </div>


</body>
<script>
$(function() {
	
	var lty_angle = $.session.get('lty_angle');
	var ok_title = $.session.get('ok_title');
	
	var banner_link = $.session.get('banner_link');
	$('#banner_link').attr('src',banner_link);
	
	$('#lty_angle').val(lty_angle);
	$('#ok_title').val(ok_title); 
})
</script>
</html>

