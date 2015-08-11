<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0,user-scalable=no">
<title>大都会人寿，50万元意外险免费送，先到先得！</title>
<meta name="keywords" content="Array" >
<meta name="description" content="大都会人寿，50万元意外险免费送，先到先得！">

<%-- <link rel="stylesheet" type="text/css" href="${ctx }/static/styles/main2.css"> --%>
<link rel="stylesheet" type="text/css" href="http://rs.iadcn.com/pingan/static/zhuanpan/styles/main2.css">
<script type="text/javascript" src="http://rs.iadcn.com/pingan/static/js/jquery-1.7.1.min.js"></script>
<script type="text/javascript" src="http://rs.iadcn.com/pingan/static/js/jquery.cookie.js"></script>
<script type="text/javascript" src="http://rs.iadcn.com/pingan/static/zhuanpan/js/jquerysession.js"></script>
<!-- <script type="text/javascript" src="${ctx}/static/js/form_ddh.js"></script> -->
<!-- <script type="text/javascript" src="http://rs.iadcn.com/pingan/static/js/form2.1.2.js"></script> -->
<script type="text/javascript" src="http://rs.iadcn.com/pingan/static/js/form_ddh.js"></script>
<script type="text/javascript" src="http://rs.iadcn.com/pingan/static/zhuanpan/js/baidu_count.js"></script>
<style type="text/css">
table{ width:100%; margin:0; padding:0;}
.tl{ border-left:1px solid #ddd;border-top:1px solid #ddd; margin-bottom:10px;}
.tl td,.tl th{ border-right:1px solid #ddd; border-bottom:1px solid #ddd; }
.tl thead{ background:#e8e8e8;}
.tl tfoot{}
</style>
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
		<img src="http://rs.iadcn.com/pingan/static/zhuanpan/images/daduhui/banner.jpg" width="100%"/>
	</div>
	
  <div class="con2">
   	  <div class="conbox2">
       	<h3>填写信息领取大礼</h3>
		<div>
		<form action="${ctx}/ping/ddhSumbit" name="form1" method="get">
		   <input id="code" name="code" type="hidden" value="${code }" />
		   <input name="a" type="hidden" value="${a }" />
			<p class="red" id="ping_title">请务必填写真实信息，以保障您领取大奖</p>
			
			<div class="info">
				<p><span>姓名：</span><input value="${uname }" id="txtName" name="uname" type="text" class="m" placeholder="请填写您的真实姓名"/></p>
				<p>
					<span>性别：</span>
					<span>
					<input type="radio" value="Male"  name="ddlSex" type="radio" checked/>男
					</span>
					<span>
					<input type="radio" value="Female" name="ddlSex" type="radio" />女
					</span>
				</p>
				<p><span>生日：</span><input value="${birthday }" id="txtBirthday" name="birthday" type="text" class="m" placeholder="例如：19820308"/></p>
				<p><span>手机：</span><input value="${phone }" id="txtMobile" name="phone" type="text" class="m" placeholder="接受保单号"/></p>
				<p><span>验证：</span><input id="vcode" name="vryCode" type="text" class="m" placeholder="请输入验证码" style="width:60%"/>
 				 <img src="${ctx}/ping/go/img" width="20%" height="30px" id="code_img" style="margin-button:0px"/>
 				</p>
				<p style="font-size:12px;" id="check_p">
					<label for="checkBaoxian">
                    <input name="checkBaoxian" type="checkbox" value="1" id="checkBaoxian" checked/>
					本人同意投保上述保险产品，并理解且同意<a href="http://rs.iadcn.com/pingan/static/pdf/APA202ddh.pdf">【保险条款】</a>，特别是免责条款的内容；同意<a href="javascript:showTip()">【个人信息授权条款】</a>的内容 
					</label>
				</p>
			</div>
			<div>
				<!-- <a href="#" class="lingqu_btn" onclick="checkForm('s')">立即领取</a> -->
				<input type="button" class="lingqu_btn" value="立即领取" onclick="checkForm('s')" />
			</div>
		</form>
		</div>
		
		<div style="margin-top:20px;">
	  	 <h3 style="background:none; padding-left:0;">保障介绍</h3>
	  	<table class="tl" cellpadding="0" cellspacing="0">
				<thead>
					<tr><th>可获保障利益</th><th>保障额度</th></tr>
				</thead>
				<tbody>
					<tr>
						<td>水陆公共交通意外伤害保险金</td>
						<td>最高5万元</td>
					</tr>
					<tr>
						<td>航空意外伤害保险</td>
						<td>最高50万元</td>
					</tr>
				</tbody>
				<tfoot>
					<tr><td>保障期限：90天</td><td>&nbsp;</td></tr>
				</tfoot>
			</table>

		该保障计划由《航空意外伤害保险》和《水陆公共交通意外伤害保险》构成，具体保障内容以保险合同为准。
	  </div>
	  
	  <div style="margin-top:20px;">
	  	 <h3 style="background:none; padding-left:0;">活动介绍</h3>
	  	①每个身份证号只能领取一次<br/>
②保单生效后，大都会人寿会给您发送短信通知<br/>
③此活动目前仅适用于以下地区：北京市、上海市、重庆市、辽宁省、江苏省、浙江省、福建省（除厦门）、广东省、四川省、湖北省、天津市
④保单生效的用户，可以到大都会人寿官网进行保单下载
		
	  </div>
	  
		<br class="clear"/>
      </div>
	  
	  

    </div>


</body>
<script>
function showTip(){
	alert("本人（即投保人）在此授权贵公司，贵公司继承人、受让人、中国境内外关联方以及贵公司授权的第三方服务者（以下统称：贵公司及相关方）在适用法律、法规允许或要求的范围内，在中国境内外，从各种渠道收集与本人向贵公司提交的保险申请和与可能订立及履行相关保险合同有关的任何本人的个人信息，并已知晓并同意贵公司及相关方将在下列情况下收集、使用、存储、披露、传送或以其他的方式处理任何本人的个人信息：（1）审核本人提交的投保申请，签订或履行可能订立的相关保险合同，并/或提供相关售后服务时；（2）遵守适用的法律法规或贵公司的合规制度或计划时；（3）拟向本人提供其他金融产品和/或服务时；（4）进行直接行销、或数据处理等。同时本人在此确认在向贵司提交投保申请时已经获得被保险人和受益人的同意，为上述目的向贵公司及相关方提供和授权使用他们的个人信息资料。");
}
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
	/// 复选框
	var isCheck = "${isCheck}";
	if(isCheck === '0'){
		$('#checkBaoxian').removeAttr("checked");
	}else{
		$('#checkBaoxian').attr("checked",'true');
	}
	
})
</script>
</html>

