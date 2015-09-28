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

<%-- <div  id="completeInfoShow1" style="display:none;">
	<div class="blackbg"></div>
	<div class="tanchuang">
		<div>
		<a id="completeInfoShow_btn2" class="sub_btn" href="${ctx }/pa/pingNew1?a=${a }" style="bottom:-100px;">完善信息</a>
		</div>
		<div><img src="http://rs.iadcn.com/pingan/static/zhuanpan/images/pupo/accident_box.png"  width="300" /></div>
	</div>
</div> --%>
<div id="saveurl" style="display:none;">
<a id="save_1" href="#" ></a>
</div>
<!-- 弹窗 end -->
 
	<div>
		<div class="tongzhi">
			<div class="tongzhi_text">
				<div class="left"><img src="http://rs.iadcn.com/pingan/static/zhuanpan/images/ltyNew1/horn.gif"/></div>
				<div class="wenzi">
					<div id="scrollobj" style="white-space:nowrap;overflow:hidden;">
						周*(139****4582)获iphone6&nbsp;&nbsp;&nbsp;&nbsp;肖*(189****2159）获得永久自行车&nbsp;&nbsp;&nbsp;&nbsp;陈**(135****2193)获得交通意外险&nbsp;&nbsp;&nbsp;&nbsp;黄*(135****2422)获得智能扫地机器人吸尘器&nbsp;&nbsp;&nbsp;&nbsp;葛**(139****9703)获得双磨全钢多功能豆浆机&nbsp;&nbsp;&nbsp;&nbsp;吴**(159****0124)超能陆战队大白玩偶&nbsp;&nbsp;&nbsp;&nbsp;蔡*(158****0707)获得遥控驱蚊冷风/空调扇&nbsp;&nbsp;&nbsp;&nbsp;韩**(159****5764)交通意外保险&nbsp;&nbsp;&nbsp;&nbsp;陈*(159****6745)永久自行车&nbsp;&nbsp;&nbsp;&nbsp;李*(135****5122)iphone6&nbsp;&nbsp;&nbsp;&nbsp;程*(158****4670)智能扫地机器人吸尘器&nbsp;&nbsp;&nbsp;&nbsp;汤*(135****9308)全钢多功能豆浆机&nbsp;&nbsp;&nbsp;&nbsp;章**(159****6975)超能陆战队大白玩偶&nbsp;&nbsp;&nbsp;&nbsp;赵**(139****1684)永久自行车&nbsp;&nbsp;&nbsp;&nbsp;黄*(139****5122)交通意外保险&nbsp;&nbsp;&nbsp;&nbsp;陈**(139****6408)双磨全钢多功能豆浆机&nbsp;&nbsp;&nbsp;&nbsp;袁*(139****0065)iphone6&nbsp;&nbsp;&nbsp;&nbsp;高**(159****7453)获得永久自行车
					</div>
				</div>
			</div>
			<div><img src="http://rs.iadcn.com/pingan/static/zhuanpan/images/ltyNew1/tongzhi.jpg" width="100%" /></div>
		</div>
	</div>
	
	<div>
		<img id="banner_link" src="http://rs.iadcn.com/pingan/static/zhuanpan/images/ltyNew1/banner1.jpg" width="100%" />
	</div>
	
	
	<div><img src="http://rs.iadcn.com/pingan/static/zhuanpan/images/ltyNew1/bannerbg.jpg" width="100%"/></div>
	
	<div class="cjbox">
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
	
	<div>
		<img src="http://rs.iadcn.com/pingan/static/zhuanpan/images/ltyNew1/bg.jpg" width="100%"/>
	</div>
	
	<div>
		<div>
			<img src="http://rs.iadcn.com/pingan/static/zhuanpan/images/ltyNew1/jiangxiang.jpg" width="100%"/>
		</div>
		
		<div class="jiangxiang">
			<ul>
				<li>
					<img id="jiangping1_link" src="http://rs.iadcn.com/pingan/static/zhuanpan/images/ltyNew1/jiangxiang1.png" />
				</li>
				<li>
					<img id="jiangping2_link" src="http://rs.iadcn.com/pingan/static/zhuanpan/images/ltyNew1/jiangxiang2.png" />
				</li>
				<li>
					<img id="jiangping3_link" src="http://rs.iadcn.com/pingan/static/zhuanpan/images/ltyNew1/jiangxiang3.png" />
				</li>
				<li>
					<img id="jiangping4_link" src="http://rs.iadcn.com/pingan/static/zhuanpan/images/ltyNew1/jiangxiang4.png" />
				</li>
				<li>
					<img id="jiangping5_link" src="http://rs.iadcn.com/pingan/static/zhuanpan/images/ltyNew1/jiangxiang5.png" />
				</li>
				<li>
					<img id="jiangping6_link" src="http://rs.iadcn.com/pingan/static/zhuanpan/images/ltyNew1/jiangxiang6.png" />
				</li>
				<li>
					<img id="jiangping7_link" src="http://rs.iadcn.com/pingan/static/zhuanpan/images/ltyNew1/jiangxiang7.png" />
				</li>
				<li>
					<img id="jiangping8_link" src="http://rs.iadcn.com/pingan/static/zhuanpan/images/ltyNew1/jiangxiang8.png" />
				</li>
				<br class="clear"/>
			</ul>
	  </div>
	</div>
	
	<div class="bottom">
		<img src="http://rs.iadcn.com/pingan/static/zhuanpan/images/ltyNew1/shuoming.jpg" width="100%" />
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
		<img src="http://rs.iadcn.com/pingan/static/zhuanpan/images/ltyNew1/indexbottom.jpg" width="100%" />
	</div>
</body>

</html>