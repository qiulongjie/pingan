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
<%-- <link rel="stylesheet" type="text/css" href="${ctx }/static/styles/ltyDdh.css"> --%>
<link rel="stylesheet" type="text/css" href="http://rs.iadcn.com/pingan/static/zhuanpan/styles/ltyDdh.css">
<script type="text/javascript" src="http://rs.iadcn.com/pingan/static/zhuanpan/js/jquery-1.7.1.min.js"></script>
<script type="text/javascript" src="http://rs.iadcn.com/pingan/static/zhuanpan/js/jquery.cookie.js"></script>
<script type="text/javascript" src="http://rs.iadcn.com/pingan/static/zhuanpan/js/jQueryRotate.2.2.js"></script>
<script type="text/javascript" src="http://rs.iadcn.com/pingan/static/zhuanpan/js/jquerysession.js"></script>
<script type="text/javascript" src="http://rs.iadcn.com/pingan/static/zhuanpan/js/baidu_count.js"></script>
<!-- <script type="text/javascript" src="http://rs.iadcn.com/pingan/static/zhuanpan/js/ltyGongGe.js"></script> -->
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
		<a id="completeInfoShow_btn" class="sub_btn" href="${ctx }/pa/pingDdh?a=${a }" style="bottom:-100px;">完善信息</a>
		</div>
		<div><img src="http://rs.iadcn.com/pingan/static/zhuanpan/images/pupo/repeat_box.png"  width="300" /></div>
	</div>
</div>

<div id="saveurl" style="display:none;">
<a id="save_1" href="${ctx }/pa/pingDdh?a=${a }" ></a>
</div>
<!-- 弹窗 end -->

	<div class="tongzhi">
		<div class="tongzhi_text">
					<div class="left"><img src="http://rs.iadcn.com/pingan/static/zhuanpan/images/daduhui/horn.gif"/></div>
					<div class="wenzi">
						<div id="scrollobj" style="white-space:nowrap;overflow:hidden;">周*(139****4582)六等奖，获得30元话费&nbsp;&nbsp;&nbsp;&nbsp;肖*(189****2159)二等奖，获得ipad mini3&nbsp;&nbsp;&nbsp;&nbsp;陈**(135****2193)三等奖，获得价值50万元的大都会人寿交通意外险&nbsp;&nbsp;&nbsp;&nbsp;黄*(135****2422)四等奖，获得移动电源&nbsp;&nbsp;&nbsp;&nbsp;葛**(139****9703)五等奖，获得50元话费&nbsp;&nbsp;&nbsp;&nbsp;吴**(159****0124)六等奖，获得30元话费&nbsp;&nbsp;&nbsp;&nbsp;韩**(159****5764)三等奖，获得价值50万元的大都会人寿交通意外险&nbsp;&nbsp;&nbsp;&nbsp;陈*(159****6745)六等奖，获得30元话费&nbsp;&nbsp;&nbsp;&nbsp;李*(135****5122)五等奖，获得50元话费&nbsp;&nbsp;&nbsp;&nbsp;程*(158****4670)四等奖，获得移动电源&nbsp;&nbsp;&nbsp;&nbsp;汤*(135****9308)五等奖，获得50元话费&nbsp;&nbsp;&nbsp;&nbsp;章**(159****6975)二等奖，获得ipad mini3&nbsp;&nbsp;&nbsp;&nbsp;赵**(139****1684)六等奖，获得30元话费&nbsp;&nbsp;&nbsp;&nbsp;黄*(139****5122)三等奖，获得价值50万元的大都会人寿交通意外险&nbsp;&nbsp;&nbsp;&nbsp;陈**(139****6408)四等奖，获得移动电源&nbsp;&nbsp;&nbsp;&nbsp;袁*(139****0065)五等奖，获得50元话费&nbsp;&nbsp;&nbsp;&nbsp;高**(159****7453)四等奖，获得移动电源</div>
					</div>
				</div>
		<div>	
			<img src="http://rs.iadcn.com/pingan/static/zhuanpan/images/daduhui/tzbg.jpg"  width="100%"/>
			<img src="http://rs.iadcn.com/pingan/static/zhuanpan/images/daduhui/tzbg2.jpg"  width="100%"/>		</div>
	</div>
	
		<div class="zpbox">
			<div class="zhuanpan">
				<div class="zhuanpan_pic">
				<img id="zhuanpanImg" src="http://rs.iadcn.com/pingan/static/zhuanpan/images/daduhui/zhuanpan.png" width="100%" />
				</div>
				<div class="qidong_box">
					<div><img id="lotteryBtn" src="http://rs.iadcn.com/pingan/static/zhuanpan/images/daduhui/59.gif" width="100%" /></div>
				</div>
			</div>
			<div>
				<img src="http://rs.iadcn.com/pingan/static/zhuanpan/images/daduhui/bg1.jpg" width="100%" />
				<img src="http://rs.iadcn.com/pingan/static/zhuanpan/images/daduhui/bg2.jpg" width="100%"/>
				<img src="http://rs.iadcn.com/pingan/static/zhuanpan/images/daduhui/bg3.jpg" width="100%"/>
			</div>
		</div>
		
		
		<div><img src="http://rs.iadcn.com/pingan/static/zhuanpan/images/daduhui/bg4.jpg" width="100%"/></div>
		
	<div class="jiangpinlist">
		<div class="jiangxiang">
			<ul>
				<li>
					<img src="http://rs.iadcn.com/pingan/static/zhuanpan/images/daduhui/jiangxiang1.jpg" />
				</li>
				<li>
					<img src="http://rs.iadcn.com/pingan/static/zhuanpan/images/daduhui/jiangxiang2.jpg" />
				</li>
				<li>
					<img src="http://rs.iadcn.com/pingan/static/zhuanpan/images/daduhui/jiangxiang3.jpg" />
				</li>
				<li>
					<img src="http://rs.iadcn.com/pingan/static/zhuanpan/images/daduhui/jiangxiang4.jpg" />
				</li>
				<li>
					<img src="http://rs.iadcn.com/pingan/static/zhuanpan/images/daduhui/jiangxiang5.jpg" />
				</li>
				<li>
					<img src="http://rs.iadcn.com/pingan/static/zhuanpan/images/daduhui/jiangxiang6.jpg" />
				</li>
			
				<br class="clear"/>
			</ul>
	  </div>
	
		<div class="shuoming">
			<div>
				<img src="http://rs.iadcn.com/pingan/static/zhuanpan/images/daduhui/shuomingtit.png" width="100%" />
			</div>
			<div style="margin-top:10px;">
				01.请点击大转盘“开始抽奖”按钮进行抽奖<br/>
				02.中奖后请填写准确个人资料，方便领取奖品时与您联
				系，如信息填写错误或不完整，则视为主动放弃。
			</div>
			
		</div>
	
		</div>

</body>
<!-- <script type="text/javascript" src="${ctx }/static/js/ltyDdh.js"></script> -->
<script type="text/javascript" src="http://rs.iadcn.com/pingan/static/zhuanpan/js/ltyDdh.js"></script>
</html>
