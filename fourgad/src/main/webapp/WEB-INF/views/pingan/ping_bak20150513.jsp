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

<%-- <link rel="stylesheet" type="text/css" href="${ctx}/static/css/main.css"> --%>
<link rel="stylesheet" type="text/css" href="http://rs.iadcn.com/pingan/static/css/main.css">
	<script type="text/javascript" src="http://rs.iadcn.com/pingan/static/js/jquery-1.7.1.min.js"></script>
    <script type="text/javascript" src="http://rs.iadcn.com/pingan/static/js/jquery.cookie.js"></script>
<%-- <script type="text/javascript" src="${ctx}/static/js/form2.1.2.js"></script> --%>
<script type="text/javascript" src="http://rs.iadcn.com/pingan/static/js/form2.1.2.js"></script> 


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
	<img src="http://rs.iadcn.com/pingan/static/images/banner.jpg" width="100%"/>
</div>


<div class="con">
<form action="${ctx}/ping/submitGet" name="form1" method="get">
<div class="bg">
<!--第二部分-->	
		<div>
		<!--<div class="renling">
	 		<div class="left"><img src="images/tit_left.gif" /></div>
			<div class="red_bg">认领一份免费平安保险来保障您的安全</div>
			<div><img src="images/tit_right.gif" /></div>
		</div>-->
		<div><img src="http://rs.iadcn.com/pingan/static/images/tit.gif" width="100%" /></div>
		
		<div class="tianxie">
			<h3 class="tianxie_icon">
				填写信息领取大礼
			</h3>
			<div class="info_write">
				<p>
					<span>姓名：</span>
					<div><input  id="txtName" name="uname" type="text" placeholder="请填写您的真实姓名"  class="inp_w"/></div>
					<input  value="男"  name="ddlSex"  type="hidden" id="sex1">
				</p>
				<p>
					<span>生日：</span><div><input id="txtBirthday" name="birthday"  type="text" placeholder="例如：19820308"  class="inp_w"/></div>
				</p>
				<p>
					<span>手机：</span>
					<div><input id="txtMobile" name="phone" type="text" placeholder="接收电子保单号"  class="inp_w left" style="margin-right:10px;"/>
					<input name="code" type="hidden" value="${code }" />
					<input name="a" type="hidden" value="${a }" />
					</div>
						<br style="clear:both;"/>
				</p>
			</div>
			
			<div style="color:#f75248;  padding:10px 0 0 0;">温馨提示：平安人寿后续致电以确认免费保障生效事宜，请留意接听</div>
		</div>
		
		<div class="btnbox">
			<!--<input type="Button" class="sub_btn" value="立即领取"  />-->
			<input type="button" class="sub_btn" value="立即领取" onclick="checkForm('s')" />
		</div>
		
		<div class="shenming">
			*您的个人信息只供参与本次赠险调研及后续保险咨询服务产品
		</div>
		
	</div>
 </div>
</div>
</form>



</body>
</html>

