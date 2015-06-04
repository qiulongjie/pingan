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
<%-- <link rel="stylesheet" type="text/css" href="${ctx }/static/styles/main_as.css"> --%>
<link rel="stylesheet" type="text/css" href="http://rs.iadcn.com/pingan/static/zhuanpan/styles/main_as.css">
<script type="text/javascript" src="http://rs.iadcn.com/pingan/static/zhuanpan/js/jquery-1.7.1.min.js"></script>
<script type="text/javascript" src="http://rs.iadcn.com/pingan/static/zhuanpan/js/jquery.cookie.js"></script>
<script type="text/javascript" src="http://rs.iadcn.com/pingan/static/zhuanpan/js/jQueryRotate.2.2.js"></script>
<%-- <script type="text/javascript" src="${ctx }/static/js/jquerysession.js"></script> --%>
<script type="text/javascript" src="http://rs.iadcn.com/pingan/static/zhuanpan/js/jquerysession.js"></script>

</head>

<body>

<div  id="submitFormDiv" style="display:none;">
	<div class="blackbg"></div>
	<div class="tanchuang" >
		<div class="tanchuang_input2" style="color:#666;">
			
		</div>
		<div class="queding_btn">
 			<a id="queding_btn" href="#">确&nbsp;&nbsp;&nbsp;定</a> 
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
        <div class="zhuanpan"><%-- 
        	<div class="zhuan_pic"> <img src="${ctx }/static/images/ltyas/zhuanpan2.png" width="100%" id="zhuanpanImg"/> </div>
            <div class="zhuan_qidong"><img src="${ctx }/static/images/ltyas/qidong.png"  id="lotteryBtn"/></div> --%>
            <div class="zhuan_pic"> <img src="http://rs.iadcn.com/pingan/static/zhuanpan/images/ltyas/zhuanpan2.png" width="100%" id="zhuanpanImg"/> </div>
            <div class="zhuan_qidong"><img src="http://rs.iadcn.com/pingan/static/zhuanpan/images/ltyas/qidong.png"  id="lotteryBtn"/></div>
        </div>
        </div>
        <%-- <div class="y_bg"><img src="${ctx }/static/images/ltyas/bg.png"  width="100%" /></div> --%>
        <div class="y_bg"><img src="http://rs.iadcn.com/pingan/static/zhuanpan/images/ltyas/bg.png"  width="100%" /></div>
    </div>
    
    <div class="con">
   	  <div class="conbox">
       	<h3>奖项设置</h3>
        <div class="jiangpin_list">
        	<ul>
			<%-- <li><img src="${ctx }/static/images/ltyas/jiangpin1.jpg" width="100px" /></li>
            <li><img src="${ctx }/static/images/ltyas/jiangpin2.jpg" width="100px" /></li>
            <li><img src="${ctx }/static/images/ltyas/jiangpin3.jpg" width="100px" /></li>
			<li><img src="${ctx }/static/images/ltyas/jiangpin4.jpg" width="100px" /></li>
            <li><img src="${ctx }/static/images/ltyas/jiangpin5.jpg" width="100px" /></li>
            <li><img src="${ctx }/static/images/ltyas/jiangpin6.jpg" width="100px" /></li> --%>
			<li><img src="http://rs.iadcn.com/pingan/static/zhuanpan/images/ltyas/jiangpin1.jpg" width="100px" /></li>
            <li><img src="http://rs.iadcn.com/pingan/static/zhuanpan/images/ltyas/jiangpin2.jpg" width="100px" /></li>
            <li><img src="http://rs.iadcn.com/pingan/static/zhuanpan/images/ltyas/jiangpin3.jpg" width="100px" /></li>
			<li><img src="http://rs.iadcn.com/pingan/static/zhuanpan/images/ltyas/jiangpin4.jpg" width="100px" /></li>
            <li><img src="http://rs.iadcn.com/pingan/static/zhuanpan/images/ltyas/jiangpin5.jpg" width="100px" /></li>
            <li><img src="http://rs.iadcn.com/pingan/static/zhuanpan/images/ltyas/jiangpin6.jpg" width="100px" /></li>
			</ul>
            <br style="clear:both"/>
        </div>
      </div>
         
      <div class="conbox">
       	<h3>注意事项</h3>
			<div class="text">请点击大转盘“启动抽奖”按钮进行抽奖<br/>
				中奖后请填写准确个人资料，方便领取奖品时与您联系，如填写的手机号码无法联系到本人，则视为主动放弃
			</div>
         </div>
    </div>

</body>
<script>
	$(function() {
		
		var a = '${a }';
		var z = '${p }';
		var p = '${p2 }';
		var c = '${code }';

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
					$.cookie("ltyAsChoujiang"+a, "true", {
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
					if ($.cookie("ltyAsChoujiang"+a)) {
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
				data:{'a':a,'z':z,'p':p,'c':c},
				url : "${ctx}/ltyAs/getLottery",
				dataType : "json",
				success : function(data) {
					if(data.error != '' && data.error != undefined){
						alert(data.error);
					}else{
						
						$('.tanchuang_input2').html(data.title_info);
						$('#queding_btn').attr('href','${ctx}'+data.link_url+'?a='+a);
					    $.session.set('ok_title', data.ok_title);
						$.session.set('ok_info', data.ok_info);
						$.session.set('lty_angle', data.lty_angle);
						rotateFunc(parseFloat(data.lty_angle));
					}
				},
				error : function() {
					running = false;
				}
			});
		}
	})
</script>
</html>
