<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0,user-scalable=no">
<title>免费交通意外险大礼包，先到先得！</title>
<meta name="keywords" content="意外险" >
<meta name="description" content="免费交通意外险大礼包，先到先得！">

<script type="text/javascript" src="http://rs.iadcn.com/pingan/static/js/jquery-1.7.1.min.js"></script>
<script type="text/javascript" src="http://rs.iadcn.com/pingan/static/js/jquery.cookie.js"></script>
<script type="text/javascript" src="http://rs.iadcn.com/pingan/static/zhuanpan/js/jquerysession.js"></script>
<script type="text/javascript" src="http://rs.iadcn.com/pingan/static/js/form_code_cookie.js"></script>
<script type="text/javascript" src="http://rs.iadcn.com/pingan/static/zhuanpan/js/baidu_count.js"></script>
<style>
body{margin:0;padding:0; margin:0 auto;line-height:22px;font-family:"微软雅黑";background:#fff;-webkit-text-size-adjust:none;color:#666666; width:100%;font-size: 14px;}
form,ul,li,p,h1,h2,h3,h4,h5,h6,dl,dt,dd{margin:0;padding:0; font-weight:normal;}
input,select{font-size:12px; color:#666; padding:4 0 0 5px; line-height:18px;}
img{border:0;}
.left{float:left;}
.right{float:right;}
ul,li{list-style-type:none;}
.clear{ clear:both; height:0; overflow:hidden;}
html,body{ height:100%;}
a{ text-decoration:none;}
ul,li{ list-style-type:none; margin:0; padding:0;}
.inputcon{ padding:0 20px;}
.info p{ margin:10px 0;}
.info p input.m{ height:34px; width:82%; border-radius:5px; border:1px solid #b7b7b7; background:#f7f8f8; outline:none;}
/* 我的加的  begin */
/* 弹出框 */
.tanchuang_input{padding:10px; width:90%; margin:0 auto;}
.tanchuang_input div{ margin:5px 0;}
.tanchuang_input div span{ width:20%;}

.blackbg{width: 100%; height: 100%; background-color: #000; opacity: 0.6; position: fixed; top: 0; left: 0; z-index:9999;}
.tanchuang{width:80%; left: 50%; margin-left: -40%; top: 50%; margin-top: -100px; position: absolute; z-index: 3; background-color: #fff; z-index:99999; border-radius:8px;}
.tanchuang_tit{background:#cf0000; text-align:center; color:#fff; height:40px; line-height:40px; font-weight:bold; border-radius:8px 8px 0 0; font-size:18px;}
.sub_btn{width:90%; height:40px; line-height:40px; color:#fff; background:#ff6400; border-radius:8px; display:block; margin:0 auto; text-align:center; outline:none;}
a.sub_btn:hover{ background:#e95b00;}

.queding_btn{text-align:center; line-height:42px; height:42px; border-top:1px solid #ddd; background:#f3f3f3; border-radius:0 0 8px 8px; font-weight:bold; }
.queding_btn a{ color:#666;font-size:16px; display:block; width:100%; border-radius:0 0 8px 8px;}
.queding_btn a:hover{background:#dddddd; border-radius:0 0 8px 8px;}
/* 我的加的  end  */
</style>

</head>

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


<body style="max-width:380px; margin:0 auto;">
	<div>
		<img src="http://rs.iadcn.com/pingan/static/zhuanpan/images/pingNew3/1.jpg" width="100%" />
		<img src="http://rs.iadcn.com/pingan/static/zhuanpan/images/pingNew3/2.jpg" width="100%" />
		<img src="http://rs.iadcn.com/pingan/static/zhuanpan/images/pingNew3/3.jpg" width="100%" />
		<img src="http://rs.iadcn.com/pingan/static/zhuanpan/images/pingNew3/4.jpg" width="100%" />
	</div>
	
 <form action="${ctx}/ping/submitGet" name="form1" method="get" onsubmit="return checkForm('a');">
	 <div class="inputcon">
		   <input name="code" type="hidden" value="${code }" />
		   <input name="a" type="hidden" value="${a }" />
		   <input name="failure_path" type="hidden" value="pingNew3" />
		   <input name="ok_p" type="hidden" value="pingNew3_ok" />
			<div class="info">
				<p>
				<span>姓&nbsp;&nbsp;名：</span>
				<input value="${uname }" id="txtName" name="uname" type="text" class="m" placeholder="请填写您的真实姓名"/></p>
				<p>
				<span>生&nbsp;&nbsp;日：</span>
				<input value="${birthday }" id="txtBirthday" name="birthday" type="text" class="m" placeholder="例：19890825"/></p>
				<p>
				<span>手&nbsp;&nbsp;机：</span>
				<input value="${phone }" id="txtMobile" name="phone" type="text" class="m" placeholder="请填写接收奖品的手机号码"/></p>
				<p>
				<span style="float:left">验证码：</span>
				<input id="vcode" name="vryCode" type="text" class="m" style="width:40%; float:left"/>
				<img id="code_img" src="${ctx}/ping/go/img" style="width:100px; height:38px; float:left; margin-left:5px;">
				<br class="clear"/>
				</p>
			</div>
		
	 </div>		
	
	
	<div>
		<img src="http://rs.iadcn.com/pingan/static/zhuanpan/images/pingNew3/5.jpg" width="100%" />
		<input name="" type="image" src="http://rs.iadcn.com/pingan/static/zhuanpan/images/pingNew3/6.jpg" width="100%" />
		<img src="http://rs.iadcn.com/pingan/static/zhuanpan/images/pingNew3/7.jpg" width="100%" />
		
	</div>
</form>
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

