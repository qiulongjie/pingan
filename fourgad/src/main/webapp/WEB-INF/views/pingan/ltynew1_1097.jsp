<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta>
<meta content="telephone=no" name="format-detection" />
<meta content="email=no" name="format-detection" />
<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=0.5, maximum-scale=2.0, user-scalable=yes" /> 
	
<title>欢乐转盘抽大奖，送大礼！</title>
<%-- <link rel="stylesheet" type="text/css" href="${ctx }/static/styles/ltyNew1.css"> --%>
<link rel="stylesheet" type="text/css" href="http://rs.iadcn.com/pingan/static/zhuanpan/styles/ltyNew1.css">
<script type="text/javascript" src="http://rs.iadcn.com/pingan/static/zhuanpan/js/jquery-1.7.1.min.js"></script>
<script type="text/javascript" src="http://rs.iadcn.com/pingan/static/zhuanpan/js/jquery.cookie.js"></script>
<script type="text/javascript" src="http://rs.iadcn.com/pingan/static/zhuanpan/js/jquerysession.js"></script>
<script type="text/javascript" src="http://rs.iadcn.com/pingan/static/zhuanpan/js/baidu_count.js"></script>
<script type="text/javascript" src="http://rs.iadcn.com/pingan/static/zhuanpan/js/ltyGongGe.js"></script>
<!-- <script type="text/javascript" src="${ctx }/static/js/ltyGongGe.js"></script> -->
</head>

<body>

<body style="max-width:400px; margin:0 auto;">
<!-- 弹窗 start -->
<div  id="denyShow" style="display:none;">
	<div class="blackbg"></div>
	<div class="tanchuang">
		<div>
		<a id="denyShow_btn" class="sub_btn" href="#" style="bottom:0px;">确&nbsp;&nbsp;&nbsp;定</a>
		</div>
		<div><img src="http://rs.iadcn.com/pingan/static/zhuanpan/images/pupo/sorry_box.gif"  width="300" /></div>
	</div>
</div>

<div  id="prizeShow" style="display:none;">
	<div class="blackbg"></div>
	<div class="tanchuang"><img src="http://rs.iadcn.com/pingan/static/zhuanpan/images/pupo/winning_box.gif" width="300" /></div>
</div>

<div  id="completeInfoShow" style="display:none;">
	<div class="blackbg"></div>
	<div class="tanchuang">
		<div>
		<a id="completeInfoShow_btn" class="sub_btn" href="${ctx }/pa/pingNew1?a=${a }" style="bottom:-100px;">完善信息</a>
		</div>
		<div><img src="http://rs.iadcn.com/pingan/static/zhuanpan/images/pupo/repeat_box.gif"  width="300" /></div>
	</div>
</div>

<div id="saveurl" style="display:none;">
<a id="save_1" href="#" ></a>
</div>
<!-- 弹窗 end -->
	<div>
		<img id="banner_link" src="http://rs.iadcn.com/pingan/static/zhuanpan/images/ltyNew1/banner1.jpg" width="100%" />
	</div>
	
	<div class="cjbox" style="padding-top:10px;">
		<div class="choujiang">
			<div id="lottery" class="jiugongge">
				<ul>
					<li class="lottery-unit lottery-unit-0"><img id="zp1_link" src="http://rs.iadcn.com/pingan/static/zhuanpan/images/ltyNew1/1.png" /></li>
					<li class="lottery-unit lottery-unit-1"><img id="zp2_link" src="http://rs.iadcn.com/pingan/static/zhuanpan/images/ltyNew1/2.png" /></li>
					<li class="lottery-unit lottery-unit-2"><img id="zp3_link" src="http://rs.iadcn.com/pingan/static/zhuanpan/images/ltyNew1/3.png" /></li>
					<li class="lottery-unit lottery-unit-7"><img id="zp8_link" src="http://rs.iadcn.com/pingan/static/zhuanpan/images/ltyNew1/8.png" /></li>
					
					<li id="lottery_start"><img src="http://rs.iadcn.com/pingan/static/zhuanpan/images/ltyNew1/9.png" /></li>
					
					<li class="lottery-unit lottery-unit-3"><img id="zp4_link" src="http://rs.iadcn.com/pingan/static/zhuanpan/images/ltyNew1/4.png" /></li>
					<li class="lottery-unit lottery-unit-6"><img id="zp7_link" src="http://rs.iadcn.com/pingan/static/zhuanpan/images/ltyNew1/7.png" /></li>
					<li class="lottery-unit lottery-unit-5 black"><img id="zp6_link" src="http://rs.iadcn.com/pingan/static/zhuanpan/images/ltyNew1/6.png" /></li>
					<li class="lottery-unit lottery-unit-4 "><img id="zp5_link" src="http://rs.iadcn.com/pingan/static/zhuanpan/images/ltyNew1/5.png" /></li>
				<br class="clear"/>
				<ul>
				
			</div>
		
		</div>
	</div>
	
	<div class="bottom">
		<h3 style="text-align:center;font-weight:bold;">活动说明</h3>
		<div style="padding:10px 20px 10px 20px;">
		<p>
			01.请点击大转盘“开始抽奖”按钮进行抽奖<br/>
02.中奖后请填写准确个人资料，方便领取奖品时与您联
系，如信息填写错误或不完整，则视为主动放弃。<br/>
03.请中奖用户正确填写资料并核对中奖信息，以便工作
人员给您核发奖品。<br/>
04.本次活动为答谢新老用户特设，所有奖品均为赠送，
因此不会向用户收取任何费用（包括邮费） 。
		</p>
		</div>
	</div>
</body>

</html>
