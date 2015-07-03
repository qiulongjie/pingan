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

<%-- <link rel="stylesheet" type="text/css" href="${ctx }/static/styles/main2.css"> --%>
<link rel="stylesheet" type="text/css" href="http://rs.iadcn.com/pingan/static/zhuanpan/styles/main2.css">
<script type="text/javascript" src="http://rs.iadcn.com/pingan/static/js/jquery-1.7.1.min.js"></script>
<script type="text/javascript" src="http://rs.iadcn.com/pingan/static/js/jquery.cookie.js"></script>
<script type="text/javascript" src="http://rs.iadcn.com/pingan/static/zhuanpan/js/jquerysession.js"></script>
<%-- <script type="text/javascript" src="${ctx}/static/js/form_code_cookie.js"></script> --%>
<!-- <script type="text/javascript" src="http://rs.iadcn.com/pingan/static/js/form2.1.2.js"></script> -->
<script type="text/javascript" src="http://rs.iadcn.com/pingan/static/js/form_code_cookie.js"></script>
<script type="text/javascript" src="http://rs.iadcn.com/pingan/static/zhuanpan/js/baidu_count.js"></script>

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


<!--***********************************************************-->

<div class="banner">
<%-- 		<img src="${ctx }/static/images/lottery_new/banner.jpg" width="100%"/> --%>
		<img src="http://rs.iadcn.com/pingan/static/zhuanpan/images/lottery_new/banner.jpg" width="100%"/>
	</div>
	
  <div class="con2">
   	  <div class="conbox2">
       	<h3>填写信息领取大礼</h3>
		<div>
		<form action="${ctx}/ping/submitGet" name="form1" method="get">
		   <input name="code" type="hidden" value="${code }" />
		   <input name="a" type="hidden" value="${a }" />
			<p class="red" id="ping_title">请务必填写真实信息，以保障您领取大奖</p>
			
			<div class="info">
				<p><span>姓名：</span><input value="${uname }" id="txtName" name="uname" type="text" class="m" placeholder="请填写您的真实姓名"/></p>
				<p><span>生日：</span><input value="${birthday }" id="txtBirthday" name="birthday" type="text" class="m" placeholder="例如：19820308"/></p>
				<p><span>手机：</span><input value="${phone }" id="txtMobile" name="phone" type="text" class="m" placeholder="请填写您的真实手机号码"/></p>
				<p><span>验证：</span><input id="vcode" name="vryCode" type="text" class="m" placeholder="请输入验证码" style="width:60%"/>
 				 <img src="${ctx}/ping/go/img" width="20%" height="30px" id="code_img" style="margin-button:0px"/>
 				</p>
			</div>
			<div>
				<!-- <a href="#" class="lingqu_btn" onclick="checkForm('s')">立即领取</a> -->
				<input type="button" class="lingqu_btn" value="立即领取" onclick="checkForm('s')" />
			</div>
			<p>*温馨提示：后续将致电以确认免费保障生效事宜，请留意接听</p>
		</form>
		</div>
		<br class="clear"/>
      </div>
	  
	  
	  <div class="hezuo">
	  		<div class="tit"><h3>合作伙伴</h3></div>
			<div>
				<ul style="width:50%;margin:0 auto;">
					<li style="float: left;height: 40px;width: 45%;margin: 0 1%;"><a href="#"><img src="http://rs.iadcn.com/pingan/static/zhuanpan/images/lottery_new/pingan.png" /></a></li>
					<li style="float: left;height: 40px;width: 45%;margin: 0 1%;"><a href="#"><img src="http://192.168.0.53:8989/fourgad/static/images/lottery_new/logo5.jpg" /></a></li>
				</ul>
			</div>
		 <br class="clear"/>
	  </div>

    </div>


</body>
<script>
$(function() {
	
	var ping_title = $.session.get('ping_title');
	if(ping_title){
		if(ping_title != '' && ping_title != 'null'){
		   $('#ping_title').html(ping_title);
		}
	}
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

