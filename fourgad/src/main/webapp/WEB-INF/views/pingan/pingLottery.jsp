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
<link rel="stylesheet" type="text/css" href="${ctx }/static/styles/main2.css">
<script type="text/javascript" src="http://rs.iadcn.com/pingan/static/zhuanpan/js/jquery-1.7.1.min.js"></script>
<script type="text/javascript" src="http://rs.iadcn.com/pingan/static/zhuanpan/js/jquery.cookie.js"></script>
<script type="text/javascript" src="http://rs.iadcn.com/pingan/static/zhuanpan/js/jQueryRotate.2.2.js"></script>
</head>

<body>

<div  id="submitFormDiv" style="display:none;">
	<div class="blackbg"></div>
	<div class="tanchuang" >
		<div class="tanchuang_input2" style="color:#666;">
			恭喜您！中了三等奖，获得最高保额50万大礼包，领取查看详情
		</div>
		<div class="queding_btn">
 			<a href="${ctx }/pa/ping?a=${a }">确&nbsp;&nbsp;&nbsp;定</a> 
<%-- 			<a href="http://m.iadcn.com/pa/ping?a=${a }">确&nbsp;&nbsp;&nbsp;定</a> --%>
		</div>
		
	</div>
</div>

<div  id="submitFormDiv2" style="display:none;">
	<div class="blackbg"></div>
	<div class="tanchuang" >
		<div class="tanchuang_input2" style="color:#666;">
			Oh~No~您已经抽了1 次奖,不能再抽了,下次再来吧!
		</div>
		<div class="queding_btn">
			<a id="queding_btn_xiaoshi" href="#">确&nbsp;&nbsp;&nbsp;定</a> 
		</div>
	</div>
</div>


	<div class="big_box">
		<div class="big_box_con">
			<div class="zhuanpan">
				<div class="zhuan_pic">
					<img src="${ctx }/static/images/lottery_new/zhuanpan.png" width="100%" id="zhuanpanImg" />
				</div>
				<div class="zhuan_qidong">
					<img src="${ctx }/static/images/lottery_new/qidong.png" id="lotteryBtn"/>
				</div>
			</div>
		</div>
		<div class="y_bg">
			<img src="${ctx }/static/images/lottery_new/bg.png" width="100%" />
		</div>
	</div>

	<div class="con">
		<div class="conbox">
			<h3>奖项设置</h3>
			<div class="jiangpin_list">
				<ul>
					<li><img src="${ctx }/static/images/lottery_new/1.png" width="100px" /></li>
					<li><img src="${ctx }/static/images/lottery_new/2.png" width="100px" /></li>
					<li><img src="${ctx }/static/images/lottery_new/3.png" width="100px" /></li>
				</ul>
				<br style="clear: both" />
			</div>
		</div>

		<div class="conbox">
			<h3>奖项设置</h3>
			<div class="text">
				请点击大转盘“开始抽奖”按钮进行抽奖<br />
				中奖后请填写准确个人资料，方便领取奖品时与您联系，如无法通过填写手机号联系本人，则视为主动放弃
			</div>
		</div>
	</div>

</body>
<script>
	$(function() {

		$('#queding_btn_xiaoshi').click(function(e) {
			$('#submitFormDiv2').hide();
		});

		var running = false;
		var rotateFunc = function(angle) {
			$('#zhuanpanImg').stopRotate();
			$("#zhuanpanImg").rotate({
				angle : 0,
				duration : 5000,
				animateTo : angle + 1440,
				callback : function() {
					$.cookie("pinganChoujiang", "true", {
						expires : 1
					});
					running = false;
					$('#submitFormDiv').show();
				}
			});
		};

		$("#lotteryBtn").rotate({
			bind : {
				click : function() {
					if (running) {
						return;
					}
					if ($.cookie("pinganChoujiang")) {
						$('#submitFormDiv2').show();
						return;
					}
					getLottery();
				}
			}

		});

		function getLottery() {
			running = true;
			$.ajax({
				type : "POST",
				cache : false,
				async : false,
				url : "${ctx}/pingLottery/getLottery2?time="
						+ new Date().getTime(),
				dataType : "text",
				success : function(data, textStatus) {
					rotateFunc(parseInt(data));
				},
				error : function() {
					running = false;
				}
			});
		}
	})
</script>
</html>
