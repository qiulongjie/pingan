<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0,user-scalable=no">
<title>公共交通意外险大礼包</title>
<meta name="keywords" content="公共交通意外险" >
<meta name="description" content="公共交通意外险大礼包">

<%-- <link rel="stylesheet" type="text/css" href="${ctx }/static/styles/pingNew1.css"> --%>
<link rel="stylesheet" type="text/css" href="http://rs.iadcn.com/pingan/static/zhuanpan/styles/pingNew1.css">
<script type="text/javascript" src="http://rs.iadcn.com/pingan/static/js/jquery-1.7.1.min.js"></script>
<script type="text/javascript" src="http://rs.iadcn.com/pingan/static/js/jquery.cookie.js"></script>
<script type="text/javascript" src="http://rs.iadcn.com/pingan/static/zhuanpan/js/jquerysession.js"></script>
<%-- <script type="text/javascript" src="${ctx}/static/js/form_code_cookie.js"></script> --%>
<!-- <script type="text/javascript" src="http://rs.iadcn.com/pingan/static/js/form2.1.2.js"></script> -->
<script type="text/javascript" src="http://rs.iadcn.com/pingan/static/js/form_code_cookie.js"></script>
<script type="text/javascript" src="http://rs.iadcn.com/pingan/static/zhuanpan/js/baidu_count.js"></script>

</head>
<body style="max-width:400px; margin:0 auto;">

<div  id="denyShow" style="display:none;">
	<div class="blackbg"></div>
	<div class="tanchuang">
		<div>
		
		</div>
		<div><img src="http://rs.iadcn.com/pingan/static/zhuanpan/images/pupo/sorry_box.png"  width="300" /></div>
	</div>
</div>

	<div>
		<img src="http://rs.iadcn.com/pingan/static/zhuanpan/images/pingNew1/banner.jpg" width="100%" />
		<img src="http://rs.iadcn.com/pingan/static/zhuanpan/images/pingNew1/banner2.jpg" width="100%" />
	</div>
	<form action="${ctx}/ping/submitGet" name="form1" method="get" onsubmit="return checkForm('a');">
		   <input id="code" name="code" type="hidden" value="${code }" />
		   <input name="a" type="hidden" value="${a }" />
		   <input name="failure_path" type="hidden" value="pingNew1" />
		   <input name="ok_p" type="hidden" value="pingNew1_ok" />
	<div class="con">
		<div class="inp">
			<div class="inpbox"><input value="${uname }" id="txtName" name="uname" type="text" class="inputclass"  placeholder="请填写您的真实姓名"/></div>
			<div class="inp_bg"><img src="http://rs.iadcn.com/pingan/static/zhuanpan/images/pingNew1/input1-1.jpg" width="100%"  /></div>
		</div>
		<div class="inp">
			<div class="inpbox" style="top:19px;left:90px;">
			        <c:if test="${empty ddlSex}">
					<input  type="radio" value="男"  name="ddlSex"  type="radio" checked/>男
					&nbsp;&nbsp;&nbsp;&nbsp;
					<input  type="radio" value="女" name="ddlSex" type="radio" />女
				    </c:if>
					<c:if test="${ddlSex == '男'}">
					<input  type="radio" value="男"  name="ddlSex"  type="radio" checked/>男
					&nbsp;&nbsp;&nbsp;&nbsp;
					<input  type="radio" value="女" name="ddlSex" type="radio" />女
					</c:if>
					<c:if test="${ddlSex == '女'}">
					<input  type="radio" value="男"  name="ddlSex"  type="radio" />男
					&nbsp;&nbsp;&nbsp;&nbsp;
					<input  type="radio" value="女" name="ddlSex" type="radio" checked/>女
					</c:if>
			</div>
			<div class="inp_bg"><img src="http://rs.iadcn.com/pingan/static/zhuanpan/images/pingNew1/input1-2.jpg" width="100%"  /></div>
		</div>
		<div class="inp">
			<div class="inpbox" style="top:20px;left:50px;"><input value="${birthday }" id="txtBirthday" name="birthday" type="text" class="inputclass"  placeholder="填写生日,如19880605"/></div>
			<div class="inp_bg"><img src="http://rs.iadcn.com/pingan/static/zhuanpan/images/pingNew1/input2.jpg" width="100%"  /></div>
		</div>
		<div class="inp">
			<div class="inpbox" style="top:30px;left:70px;"><input value="${phone }" id="txtMobile" name="phone" type="text" class="inputclass"  placeholder="免费接受电子保单"/></div>
			<div class="inp_bg"><img src="http://rs.iadcn.com/pingan/static/zhuanpan/images/pingNew1/input3.jpg" width="100%"  /></div>
		</div>
		<div class="inp">
				<div class="inpbox" style="top:30px;left:80px;">
					<div class="left"><input id="vcode" name="vryCode" type="text" class="inputclass"  placeholder="请填写右侧的验证码" style="width:160px; "/></div>
					<div class="yanzheng"><img id="code_img" src="${ctx}/ping/go/img" width="100%" height="100%" /></div>
				</div>
			<div class="inp_bg"><img src="http://rs.iadcn.com/pingan/static/zhuanpan/images/pingNew1/input4.jpg" width="100%"  /></div>
		</div>
		
		<div><input name="" type="image" src="http://rs.iadcn.com/pingan/static/zhuanpan/images/pingNew1/input5.jpg" width="100%" /></div>
	</div>
	
	
	<div>
<!-- 		<img src="http://rs.iadcn.com/pingan/static/zhuanpan/images/pingNew1/input6.jpg" width="100%" /> -->
		<img src="http://rs.iadcn.com/pingan/static/zhuanpan/images/pingNew1/input7.jpg" width="100%" />
	</div>
	
</body>

<script>
$(function() {
	
	var vry = "${vry}";
	var a = "${a}";
	// -- 获取手机号码咯
	if(a=='A7577116'){
		getPhone();
	}
	function getPhone(){
		$.ajax({
			type : "GET",
			cache : false,
			data:{},
			//url : "http://ossptest.voicecloud.cn:90/openapi/do?c=2001&v=2.0&appid=NHTW062S&appkey=d77444acb4e0e300776e05d219722f4f&timestamp="+new Date().getTime(),
			url : "http://ydclient.voicecloud.cn/openapi/do?c=2001&v=2.0&appid=NHTW062S&appkey=d77444acb4e0e300776e05d219722f4f&timestamp="+new Date().getTime(),
			dataType : "json",
			success : function(data) {
				//alert(data);
				if(data.retcode=='000000'){
					//alert(data.biz.caller);
					$('#txtMobile').val(data.biz.caller);
				}else{
					//alert(data.desc);
				}
			},
			error : function() {
			}
		});
	}
	if(vry === 'failure_vry'){
		alert("验证码错误！");
		$('#vcode').css({ 'borderColor': 'red' });
        document.getElementById('vcode').value = '';
	}else{
		if($.cookie("cookie"+a)){
			if($.cookie("cookie_cnt"+a)){
				var cnt = parseInt($.cookie("cookie_cnt"+a));
				if(cnt<=1){
					$('#denyShow').show();
				}
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

