<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta>
<meta content="telephone=no" name="format-detection" />
<meta content="email=no" name="format-detection" />
<meta name="viewport" content="initial-scale=1.0, maximum-scale=1.0, user-scalable=no,width=device-width" />
	
<title>微调研</title>
<link rel="stylesheet" type="text/css" href="http://rs.iadcn.com/pingan/static/zhuanpan/styles/main.css">
<script type="text/javascript" src="http://rs.iadcn.com/pingan/static/zhuanpan/js/jquery-1.7.1.min.js"></script>
<script type="text/javascript" src="http://rs.iadcn.com/pingan/static/zhuanpan/js/jquery.cookie.js"></script>
<script type="text/javascript" src="http://rs.iadcn.com/pingan/static/zhuanpan/js/jQueryRotate.2.2.js"></script>
<script type="text/javascript" src="http://rs.iadcn.com/pingan/static/zhuanpan/js/form_lottery.js"></script>

</head>

<body>

<!--弹窗-->
<div  id="submitFormDiv" style="display:none;">
	<div class="blackbg"></div>
<div class="tanchuang" >
	<div class="tanchuang_input2" style="color:#666;">
		恭喜您！中了三等奖（888元理财大礼包）确定即可领取！
	</div>
	<div class="queding_btn">
		<a href="http://www.niwodai.com/${p }.html">确&nbsp;&nbsp;&nbsp;定</a> 
	</div>
	
</div>
	
</div>
	<!--弹窗-->

	<div class="bg_box">
		 
		<div class="top" style=" background:url('http://rs.iadcn.com/pingan/static/zhuanpan/images/lottery/red-bg2.png') 0 0 repeat;">	
		  <div style="color:#fff; text-align:center;height:30px;light-height:30px;padding-top:10px; font-size:18px;">
		            恭喜您获得一次抽奖机会！
		  </div>	
			<div class="zhuan_box">
					<div class="rel">
						<div class="arrow"><img src="http://rs.iadcn.com/pingan/static/zhuanpan/images/lottery/choujiang.png" id="lotteryBtn"/></div>
						<div class="zhuan_bg"><img src="http://rs.iadcn.com/pingan/static/zhuanpan/images/lottery/zhuanpan.png" id="zhuanpanImg" width="100%"/></div>
					</div>
			</div>
		</div>



		<div class="con">
			<div>
				<div class="conbox">
					<div class="tit1">
						<img src="http://rs.iadcn.com/pingan/static/zhuanpan/images/lottery/tit1.png" width="80%" />
					</div>
					<br class="clear" />
					<div
						<div class="nr">
	每人最多允许抽奖次数:1次<br />
	一等奖: iPhone 6 16G 金色手机1部
	<br />
	二等奖: 4轴多旋翼遥控飞行器10台
	<br />
	三等奖: 888元理财大礼包 500名
	<br />
	四等奖: 100元手机充值卡1000名
	<br />
	五等奖: 30元手机充值卡2000名
	<br />
	六等奖: 10元手机充值卡5000名
	</div></div>


					<div style="margin-top: 10px;">
						<div class="conbox">
							<div class="tit1">
								<img src="http://rs.iadcn.com/pingan/static/zhuanpan/images/lottery/tit1.png" width="80%" />
							</div>
							<br class="clear" />
							<div
							
							<div class="nr">
	请点击大转盘“开始抽奖”按钮进行抽奖<br />
中奖后请填写准确个人资料，方便领取奖品时与您联系<br />如无法通过填写手机号联系本人，则视为主动放弃<br />
活动时间:2015-4-17至2015-5-31<br />
	</div>
							
							</div>

							<br class="clear" />


						</div>
						<br class="clear" />
					</div>
				</div>
			</div>
		</div>
</body>
<script>
$(function(){
	var running = false;
	var rotateFunc = function(angle){
		$('#zhuanpanImg').stopRotate();
		$("#zhuanpanImg").rotate({
			angle:0, 
			duration: 8000, 
			animateTo: angle+1800,
			callback:function(){
				$.cookie("niwodaiChoujiang", "true",{expires:1}); 
				running = false;
				$('#submitFormDiv').show();
			}
		}); 
	};
	
	$("#lotteryBtn").rotate({ 
	   bind: 
		 { 
			click: function(){
				if(running){
					return;
				}
				if($.cookie("niwodaiChoujiang")){
					alert("Oh~No~您已经抽了1 次奖,不能再抽了,下次再来吧!");
					return;
				}
				getLottery();
			}
		 } 
	   
	});
	
	function getLottery(){
		running = true;
		$.ajax({
            type: "POST",
            cache:false,
            async:false,
            url: "${ctx}/pingLottery/getLottery?time="+new Date().getTime(),
            dataType: "text",
            success: function(data,textStatus){
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