<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0,user-scalable=no">
<title>公共交通意外险大礼包</title>
<meta name="keywords" content="公共交通,意外险,大礼包" >
<meta name="description" content="公共交通意外险大礼包">

<%-- <link rel="stylesheet" type="text/css" href="${ctx }/static/styles/ping_pc.css"> --%>
<link rel="stylesheet" type="text/css" href="http://rs.iadcn.com/pingan/static/zhuanpan/styles/ping_pc.css">
<script type="text/javascript" src="http://rs.iadcn.com/pingan/static/js/jquery-1.7.1.min.js"></script>
<script type="text/javascript" src="http://rs.iadcn.com/pingan/static/js/jquery.cookie.js"></script>
<script type="text/javascript" src="http://rs.iadcn.com/pingan/static/zhuanpan/js/jquerysession.js"></script>
<%-- <script type="text/javascript" src="${ctx}/static/js/form_code_cookie.js"></script> --%>
<!-- <script type="text/javascript" src="http://rs.iadcn.com/pingan/static/js/form2.1.2.js"></script> -->
<script type="text/javascript" src="http://rs.iadcn.com/pingan/static/js/form_code_cookie.js"></script>
<script type="text/javascript" src="http://rs.iadcn.com/pingan/static/zhuanpan/js/baidu_count.js"></script>

</head>


<body>
	<div class="con">
		<div class="conbox">
		<form action="${ctx}/ping/submitGet" name="form1" method="post">
		   <input name="code" type="hidden" value="${code }" />
		   <input name="a" type="hidden" value="${a }" />
		   <input name="failure_path" type="hidden" value="ping_pc" />
		   <input name="ok_p" type="hidden" value="ping_pc_ok" />
					<div class="left"><img src="http://rs.iadcn.com/pingan/static/zhuanpan/images/ping_pc/banner.jpg" /></div>
						<div class="right-inp">
							<div class="title"><img src="http://rs.iadcn.com/pingan/static/zhuanpan/images/ping_pc/title1.png" /></div>
						<div class="inp_box">
							<div>
								<span class="name">姓名：</span>
								<input value="${uname }" id="txtName" name="uname" type="text" class="inp" placeholder="请填写您的真实姓名"/>
							</div>
							
							<div>
								<span class="name">生日：</span>
								<input value="${birthday }" id="txtBirthday" name="birthday" type="text"  class="inp" placeholder="例如：19820308"/>
							</div>
							
							<div>
								<span class="name">手机号：</span>
								<input value="${phone }" id="txtMobile" name="phone" type="text"  class="inp" placeholder="请填写您的真实手机号码"/>
							</div>
							
							<div>
								<span class="name">验证码：</span>
								<input id="vcode" name="vryCode" type="text" class="inp" style="width:100px; float:left;" />
								<div class="yanzheng" style="margin:0 0 0 10px; padding:0;">
								<img src="${ctx}/ping/go/img" width="100%" height="100%" id="code_img" style="margin-button:0px"/>
								</div>
							</div>
							
							<div>
							   <input name="" type="button"  class="btn lingqu_btn" value="免费领取" onclick="checkForm('s')"/>		
					        </div>
						</div>
					</div>
		<br class="clear"/>
		</div>
		
	</div>
	
	<div class="frlendlink">
		<ul>
			<li><img src="http://rs.iadcn.com/pingan/static/zhuanpan/images/ping_pc/1.jpg" /></li>
			<li><img src="http://rs.iadcn.com/pingan/static/zhuanpan/images/ping_pc/2.jpg" /></li>
			<li><img src="http://rs.iadcn.com/pingan/static/zhuanpan/images/ping_pc/3.jpg" /></li>
			<li><img src="http://rs.iadcn.com/pingan/static/zhuanpan/images/ping_pc/4.jpg" /></li>
<!-- 			<li><img src="http://rs.iadcn.com/pingan/static/zhuanpan/images/ping_pc/5.jpg" /></li> -->
			<li><img src="http://rs.iadcn.com/pingan/static/zhuanpan/images/ping_pc/6.jpg" /></li>
			<li><img src="http://rs.iadcn.com/pingan/static/zhuanpan/images/ping_pc/7.jpg" /></li>
			<li><img src="http://rs.iadcn.com/pingan/static/zhuanpan/images/ping_pc/8.jpg" /></li>
			
		</ul>
	</div>
</body>



<script>
$(function() {
	
	var vry = "${vry}";
	if(vry === 'failure_vry'){
		alert("验证码错误！");
		$('#vcode').css({ 'borderColor': 'red' });
        document.getElementById('vcode').value = '';
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

