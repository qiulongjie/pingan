<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0,user-scalable=no">
<title>中国平安大礼包，25万元意外险免费送，先到先得！</title>
<meta name="keywords" content="Array" >
<meta name="description" content="中国平安大礼包，25万元意外险免费送，先到先得！">

<%-- <link rel="stylesheet" type="text/css" href="${ctx }/static/styles/ping_ios.css"> --%>
<link rel="stylesheet" type="text/css" href="http://rs.iadcn.com/pingan/static/zhuanpan/styles/ping_ios.css">
<script type="text/javascript" src="http://rs.iadcn.com/pingan/static/js/jquery-1.7.1.min.js"></script>
<script type="text/javascript" src="http://rs.iadcn.com/pingan/static/js/jquery.cookie.js"></script>
<%-- <script type="text/javascript" src="${ctx}/static/js/form_code_cookie.js"></script> --%>
<script type="text/javascript" src="http://rs.iadcn.com/pingan/static/js/form_code_cookie.js"></script>
<script type="text/javascript" src="http://rs.iadcn.com/pingan/static/zhuanpan/js/baidu_count.js"></script>

</head>

<body>
<div style="height:20px;position:fixed;top:0;width:100%;z-index:9999999;display:block;background:#ffffff;"></div>
<div class="banner">
		<img src="http://rs.iadcn.com/pingan/static/zhuanpan/images/ping_ios/banner1.jpg" width="100%"/>
		<img src="http://rs.iadcn.com/pingan/static/zhuanpan/images/ping_ios/banner2.jpg" width="100%"/>
		<img src="http://rs.iadcn.com/pingan/static/zhuanpan/images/ping_ios/banner3.jpg" width="100%"/>
	</div>
	
  <div class="con3">
   	  <div class="conbox2">
       	<h3>填写信息领取大礼</h3>
		<div>
		<form action="${ctx}/ping/submitGet" name="form1" method="get">
		   <input name="code" type="hidden" value="${code }" />
		   <input name="a" type="hidden" value="${a }" />
		   <input name="failure_path" type="hidden" value="ping_ios" />
			<p class="red">请务必填写真实信息，以保障您领取大奖</p>
			<div class="info">
				<p><span>姓&nbsp;&nbsp;名：</span><input value="${uname }" id="txtName" name="uname" type="text" class="m" placeholder="请填写您的真实姓名"/></p>
				<p><span>生&nbsp;&nbsp;日：</span><input value="${birthday }" id="txtBirthday" name="birthday" type="text" class="m" placeholder="例如：19890308"/></p>
				<p><span>手&nbsp;&nbsp;机：</span><input value="${phone }" id="txtMobile" name="phone" type="text" class="m" placeholder="请填写您的真实手机号码"/></p>
				<p><span style="float:left">验证码：</span>
				<input id="vcode" name="vryCode" type="text" class="m" placeholder="请输入验证码" style="width:30%; float:left"/>
				<img id="code_img" src="${ctx}/ping/go/img" style="width:100px; height:38px; float:left; margin-left:5px;">
				<br class="clear"/>
				</p>
			</div>
			<div>
				<input type="button" class="lingqu_btn" value="立即领取" onclick="checkForm('s')" />
			</div>
			<p>*您的个人信息只供参与本次赠险调研及后续保险咨询服务<br /></p>
		  </form>
		</div>
		<br class="clear"/>
      </div>
	  

    </div>

</body>
<script>
$(function() {
	
	var vry = "${vry}";
	var a = "${a}";
	if(vry === 'failure_vry'){
		alert("验证码错误！");
		$('#vcode').css({ 'borderColor': 'red' });
        document.getElementById('vcode').value = '';
        $.cookie("cookie"+a,null);
	} 
	
	 $("#vcode").focus(function(){
		 $('#code_img').attr('src','${ctx}/ping/go/img?'+ Math.floor(Math.random() * 100)).fadeIn();
	 });

	$('#code_img').click(function() {//生成验证码  
		$(this).hide().attr('src','${ctx}/ping/go/img?'+ Math.floor(Math.random() * 100)).fadeIn();
	})
})
</script>
</html>

