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
<%-- <link rel="stylesheet" type="text/css" href="${ctx }/static/styles/main_lty_d8.css"> --%>
<link rel="stylesheet" type="text/css" href="http://rs.iadcn.com/pingan/static/zhuanpan/styles/main_lty_d8.css">
<script type="text/javascript" src="http://rs.iadcn.com/pingan/static/zhuanpan/js/jquery-1.7.1.min.js"></script>
<script type="text/javascript" src="http://rs.iadcn.com/pingan/static/zhuanpan/js/jquery.cookie.js"></script>
<script type="text/javascript" src="http://rs.iadcn.com/pingan/static/zhuanpan/js/jQueryRotate.2.2.js"></script>
<script type="text/javascript" src="http://rs.iadcn.com/pingan/static/zhuanpan/js/jquerysession.js"></script>
<script type="text/javascript" src="http://rs.iadcn.com/pingan/static/zhuanpan/js/baidu_count.js"></script>

</head>

<body style="max-width:400px; margin:0 auto;">
<!-- 弹窗 start -->
<div  id="denyShow" style="display:none;">
	<div class="blackbg"></div>
	<div class="tanchuang">
		<div>
		<a id="denyShow_btn" class="sub_btn" href="#" style="bottom:0px;">确&nbsp;&nbsp;&nbsp;定</a>
		</div>
		<div><img src="http://rs.iadcn.com/pingan/static/zhuanpan/images/pupo/sorry_box.png"  width="300" /></div>
	</div>
</div>

<div  id="prizeShow" style="display:none;">
	<div class="blackbg"></div>
	<div class="tanchuang"><img src="http://rs.iadcn.com/pingan/static/zhuanpan/images/pupo/winning_box.png" width="300" /></div>
</div>

<div  id="completeInfoShow" style="display:none;">
	<div class="blackbg"></div>
	<div class="tanchuang">
		<div>
		<a id="completeInfoShow_btn" class="sub_btn" href="${ctx }/pa/pingNew2?a=${a }" style="bottom:-100px;">完善信息</a>
		</div>
		<div><img src="http://rs.iadcn.com/pingan/static/zhuanpan/images/pupo/repeat_box.png"  width="300" /></div>
	</div>
</div>

<div  id="completeInfoShow1" style="display:none;">
	<div class="blackbg"></div>
	<div class="tanchuang">
		<div>
		<a id="completeInfoShow_btn" class="sub_btn" href="${ctx }/pa/pingNew2?a=${a }" style="bottom:-100px;">完善信息</a>
		</div>
		<div><img src="http://rs.iadcn.com/pingan/static/zhuanpan/images/pupo/accident_box.png"  width="300" /></div>
	</div>
</div>
<div id="saveurl" style="display:none;">
<a id="save_1" href="${ctx }/pa/pingNew2?a=${a }" ></a>
</div>
<!-- 弹窗 end -->

	<div>
		<img id="top_img_id" src="http://rs.iadcn.com/pingan/static/zhuanpan/images/lty_d8/banner.jpg"  width="100%" />
	</div>
	<div class="tongzhi">
			<div class="tongzhi_text">
				<div class="left"><img src="http://rs.iadcn.com/pingan/static/zhuanpan/images/lty_d8/horn_black.gif"/></div>
				<div class="wenzi">
					<div id="scrollobj" style="white-space:nowrap;overflow:hidden; width:350px;">
						周*(139****4582)获iphone6&nbsp;&nbsp;&nbsp;&nbsp;肖*(189****2159）获得永久自行车&nbsp;&nbsp;&nbsp;&nbsp;陈**(135****2193)获得交通意外险&nbsp;&nbsp;&nbsp;&nbsp;黄*(135****2422)获得智能扫地机器人吸尘器&nbsp;&nbsp;&nbsp;&nbsp;葛**(139****9703)获得双磨全钢多功能豆浆机&nbsp;&nbsp;&nbsp;&nbsp;吴**(159****0124)超能陆战队大白玩偶&nbsp;&nbsp;&nbsp;&nbsp;蔡*(158****0707)获得遥控驱蚊冷风/空调扇&nbsp;&nbsp;&nbsp;&nbsp;韩**(159****5764)交通意外保险&nbsp;&nbsp;&nbsp;&nbsp;陈*(159****6745)永久自行车&nbsp;&nbsp;&nbsp;&nbsp;李*(135****5122)iphone6&nbsp;&nbsp;&nbsp;&nbsp;程*(158****4670)智能扫地机器人吸尘器&nbsp;&nbsp;&nbsp;&nbsp;汤*(135****9308)全钢多功能豆浆机&nbsp;&nbsp;&nbsp;&nbsp;章**(159****6975)超能陆战队大白玩偶&nbsp;&nbsp;&nbsp;&nbsp;赵**(139****1684)永久自行车&nbsp;&nbsp;&nbsp;&nbsp;黄*(139****5122)交通意外保险&nbsp;&nbsp;&nbsp;&nbsp;陈**(139****6408)双磨全钢多功能豆浆机&nbsp;&nbsp;&nbsp;&nbsp;袁*(139****0065)iphone6&nbsp;&nbsp;&nbsp;&nbsp;高**(159****7453)获得永久自行车
					</div>
				</div>
			</div>
	</div>
	<div>
		<div class="zpbox">
			
			<div class="zhuanpan">
				<div class="zhuanpan_pic"><img id="zhuanpanImg" src="http://rs.iadcn.com/pingan/static/zhuanpan/images/lty_d8/zhuanpan.png" width="100%" /></div>
				<div class="qidong_box">
					<div><img id="lotteryBtn" src="http://rs.iadcn.com/pingan/static/zhuanpan/images/lty_d8/qidong_btn3.gif" width="100%" /></div>
				</div>
			</div>
			
			<div>
				<img src="http://rs.iadcn.com/pingan/static/zhuanpan/images/lty_d8/bg1.jpg" width="100%" />
				<img src="http://rs.iadcn.com/pingan/static/zhuanpan/images/lty_d8/bg2.jpg" width="100%"/>
				<img src="http://rs.iadcn.com/pingan/static/zhuanpan/images/lty_d8/bg3.jpg" width="100%"/>
			</div>
		</div>
	</div>
	
	
	<div>
		<img src="http://rs.iadcn.com/pingan/static/zhuanpan/images/lty_d8/cjbg0.jpg" width="100%"/>
	</div>
	
	
	<div>
		<div>
			<img id="jiangping1_link" src="http://rs.iadcn.com/pingan/static/zhuanpan/images/lty_d8/cjbg1.jpg" width="100%"/>
			<img id="jiangping2_link" src="http://rs.iadcn.com/pingan/static/zhuanpan/images/lty_d8/cjbg2.jpg" width="100%"/>
			<img id="jiangping3_link" src="http://rs.iadcn.com/pingan/static/zhuanpan/images/lty_d8/cjbg3.jpg" width="100%"/>
			<img id="jiangping4_link" src="http://rs.iadcn.com/pingan/static/zhuanpan/images/lty_d8/cjbg4.jpg" width="100%"/>
			<img id="jiangping5_link" src="http://rs.iadcn.com/pingan/static/zhuanpan/images/lty_d8/cjbg5.jpg" width="100%"/>
		</div>
	
	</div>


<div style="background:#0188cc; padding:10px 0;">
	<div style="color:#fff; text-align:center; line-height:30px; font-size:20px;">【抽奖说明】</div>
	<div style="width: 85%;color:#744f00; background:#fedc01; margin:0px auto; padding:10px; border-radius:6px;">     
	<p style="margin-bottom:8px;">(1)请点击大转盘“开始抽奖”按钮进行抽奖</p>
      <p style="margin-bottom:8px;">(2)中奖后请填写准确个人资料，方便领取奖品时与您联
系，如信息填写错误或不完整，则视为主动放弃。</p>
      <p style="margin-bottom:8px;">(3)请中奖用户正确填写资料并核对中奖信息，以便工作
人员给您核发奖品。</p>
      <p style="margin-bottom:8px;">(4)本次活动为答谢新老用户特设，所有奖品均为赠送，
因此不会向用户收取任何费用（包括邮费） </p>
      <p style="">（5）此页面由第三方掌众信息提供，活动最终解释权归掌众信息，如有疑问请致电客服热线：0755-3300507 </p>
</div>

</body>
<script type="text/javascript" src="http://rs.iadcn.com/pingan/static/zhuanpan/js/lty_d_tImg8.js"></script>
</html>
