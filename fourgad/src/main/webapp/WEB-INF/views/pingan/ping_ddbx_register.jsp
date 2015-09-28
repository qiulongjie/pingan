<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, minimum-scale=0.5, maximum-scale=2.0, user-scalable=yes" />
<title>填写信息免费领取公共交通意外险</title>

<meta name="keywords" content="车险" >
<link rel="stylesheet" type="text/css" href="http://rs.iadcn.com/pingan/static/zhuanpan/styles/ddbx_register.css">
<link rel="stylesheet" type="text/css" href="http://rs.iadcn.com/pingan/static/zhuanpan/styles/tanchuang.css">

<script type="text/javascript" src="http://rs.iadcn.com/pingan/static/js/jquery-1.7.1.min.js"></script>
<script type="text/javascript" src="http://rs.iadcn.com/pingan/static/js/jquery.cookie.js"></script>
<script type="text/javascript" src="http://rs.iadcn.com/pingan/static/zhuanpan/js/jquerysession.js"></script>
<script type="text/javascript" src="http://rs.iadcn.com/pingan/static/zhuanpan/js/baidu_count.js"></script>
<script type="text/javascript" src="http://rs.iadcn.com/pingan/static/js/form_code_cookie.js"></script>

</head>
<body>

	<div id="submitFormDiv" style="display: none;">
		<div class="blackbg"></div>
		<div class="tanchuang">
			<div id="alert_text" class="tanchuang_input" style="color: #666;">
			</div>
			<div class="queding_btn">
				<a id="queding_btn_xiaoshi" href="#">确&nbsp;&nbsp;&nbsp;定</a>
			</div>

		</div>
	</div>
	<div id="submitFormDiv2" style="display: none;">
		<div class="blackbg"></div>
		<div class="tanchuang">
			<div class="tanchuang_input2" style="color: #666;">
				您的奖已经领取成功,下次再来吧!</div>
		</div>
	</div>

	<div class="list">
		<div class="tit">填写信息免费领取公共交通意外险</div>
		<div class="g">
			<form action="${ctx}/ping/submitGet" name="form1" method="get">
				<input name="code" type="hidden" value="${code }" /> 
				<input name="a" type="hidden" value="${a }" /> 
				<input name="failure_path" type="hidden" value="ping_ddbx_register" /> 
				<input name="ok_p" type="hidden" value="ping_ddbx_ok" />
				<div>
					<div class="left">
						<span>姓&nbsp;名：</span> <input name="uname" id="txtName"
							value="${uname }" type="text" class="inp" placeholder="请填写您的真实姓名" style="width: 200px;" />
					</div>
					<div class="left">
						<input name="ddlSex" type="radio" value="男" id="x" checked /><label for="x">男</label> 
						<input name="ddlSex" type="radio" value="女" id="c" /><label for="c">女</label>
					</div>
					<br class="clear" />
				</div>

				<div>
					<span>生&nbsp;日：</span> <input value="${birthday }" id="txtBirthday"
						name="birthday" type="text" placeholder="例如：19890825" class="inp" />
				</div>

				<div>
					<span>手&nbsp;机：</span> <input value="${phone }" id="txtMobile"
						name="phone" type="text" placeholder="免费接受电子保单" class="inp" />
				</div>

				<div>
					<span style="float: left;">验证码：</span> <input id="vcode"
						name="vryCode" type="text" class="inp"
						style="width: 200px; float: left;" />
					<div style="width: 100px; height: 36px; background: #000000; float: left; margin-top: 7px; margin-left: 10px;">
						<img src="${ctx}/ping/go/img" width="100%" height="100%" id="code_img" />
					</div>
					<br class="clear" />
				</div>
			</form>
			<div>
				<input type="button" value="&nbsp;领取赠险" class="lingqu_btn"
					onclick="checkForm('s')" />
			</div>
		</div>
	</div>
	<div style="">
		<div style="margin-top: 60px;">
			<img src="http://rs.iadcn.com/pingan/static/zhuanpan/images/ddbx/register_1.jpg" width="100%" />
		</div>
		<div>
			<img src="http://rs.iadcn.com/pingan/static/zhuanpan/images/ddbx/register_2.jpg" width="100%" />
		</div>
		<div>
			<img src="http://rs.iadcn.com/pingan/static/zhuanpan/images/ddbx/register_3.jpg" width="100%" />
		</div>
		<div>
			<img src="http://rs.iadcn.com/pingan/static/zhuanpan/images/ddbx/register_4.jpg" width="100%" />
		</div>
		<div>
			<img src="http://rs.iadcn.com/pingan/static/zhuanpan/images/ddbx/register_5.jpg" width="100%" />
		</div>
		<div>
			<img src="http://rs.iadcn.com/pingan/static/zhuanpan/images/ddbx/register_6.jpg" width="100%" />
		</div>
		<div>
			<img src="http://rs.iadcn.com/pingan/static/zhuanpan/images/ddbx/register_7.jpg" width="100%" />
		</div>
		<div>
			<img src="http://rs.iadcn.com/pingan/static/zhuanpan/images/ddbx/register_8.jpg" width="100%" />
		</div>
		<div>
			<img src="http://rs.iadcn.com/pingan/static/zhuanpan/images/ddbx/register_9.jpg" width="100%" />
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
	var a = "${a}";
	if(vry === 'failure_vry'){
		alert("验证码错误！");
		$('#vcode').css({ 'borderColor': 'red' });
        document.getElementById('vcode').value = '';
	}else{
		if($.cookie("cookie"+a)){
			if($.cookie("cookie_cnt"+a)){
				var cnt = parseInt($.cookie("cookie_cnt"+a));
				if(cnt<=1){
					$('#submitFormDiv2').show();
				}
			}else{
				$('#submitFormDiv2').show();
			}
		}
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