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
<!-- <link rel="stylesheet" type="text/css" href="${ctx }/static/styles/main_lty_d6.css"> -->
<link rel="stylesheet" type="text/css" href="http://rs.iadcn.com/pingan/static/zhuanpan/styles/main_lty_d6.css">
<script type="text/javascript" src="http://rs.iadcn.com/pingan/static/zhuanpan/js/jquery-1.7.1.min.js"></script>
<script type="text/javascript" src="http://rs.iadcn.com/pingan/static/zhuanpan/js/jquery.cookie.js"></script>
<script type="text/javascript" src="http://rs.iadcn.com/pingan/static/zhuanpan/js/jQueryRotate.2.2.js"></script>
<script type="text/javascript" src="http://rs.iadcn.com/pingan/static/zhuanpan/js/jquerysession.js"></script>
<script type="text/javascript" src="http://rs.iadcn.com/pingan/static/zhuanpan/js/baidu_count.js"></script>

</head>

<body style=" max-width:400px; margin:0 auto;">

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
<div  id="submitFormDiv3" style="display:none;">
	<div class="blackbg"></div>
	<div class="tanchuang" >
	<div id="close_cjg_id" style="position:absolute;right:5px;top:3px;"><img src="http://rs.iadcn.com/pingan/static/zhuanpan/images/lty_d/iconfont-xalt.png"  width="20px" height="20px"/></div>
		<div class="tanchuang_input2" style="color:#666;">
			您已经抽过奖，需要完善信息？
		</div>
		<div class="queding_btn">
<%-- 			<a id="queding_btn_xiaoshi" href="http://m.iadcn.com/pa/ping_hiwifi?a=${a }">完善信息</a>  --%>
			<a id="queding_btn_xiaoshi" href="${ctx }/ping?a=${a }">完善信息</a> 
		</div>
	</div>
</div>
	<img id="top_img_id" src=""  width="100%" />
	<div class="big_box3">
    	<div class="big_box_con5">
		<div class="tongzhi3">
			<div id="scrollobj" style="white-space:nowrap;overflow:hidden; line-height:32px; color:#e74d2b;"></div>
		</div>
		
		
        <div class="zhuanpan">
        	<div class="zhuan_pic4"> <img src="" width="100%" id="zhuanpanImg"/> </div>
            <div id="zhan_qidong1" class="zhuan_qidong3"><img src="http://rs.iadcn.com/pingan/static/zhuanpan/images/lty_d6/qidong_btn.gif" id="lotteryBtn" /></div>
            <!-- <div id="zhan_qidong2" class="zhuan_qidong3" style="display:none"><img src="http://192.168.0.53:8989/fourgad/static/images/lty_d/qidong_btn3.png" id="lotteryBtn2" /></div> -->

        </div>
        </div>
        <div class="y_bg3"><img src="http://rs.iadcn.com/pingan/static/zhuanpan/images/lty_d6/bg3.png"  width="100%" /></div>
    </div>
    
    <div class="con333 bgz">
		  	  			<div style="background:url(http://rs.iadcn.com/pingan/static/zhuanpan/images/lty_d6/bg_bolang.png) 0 0 repeat-x; width:100%; height:12px; display:block; margin:0; position:relative;z-index:99999;bottom:-8px;"></div>
   	 <div class="conbox3" style="background:#f669f5;">
       <div class="conbox3_nr"  style="border-right:3px solid #731864; border-left:3px solid #731864;">
	   	<h3 class="title2">奖项设置</h3>
        <div class="jiangpin_list2">
        	<ul>
				<li style="float:left"><img id="jiangping1_link" src="" width="" /></li>
	            <li style="float:right"><img id="jiangping2_link" src="" width="" /></li></ul>
	            <ul>
	            <li style="float:left"><img id="jiangping3_link" src="" width="" /></li>
				<li style="float:right"><img id="jiangping4_link" src="" width="" /></li></ul>
				<ul>
	            <li style="float:left"><img id="jiangping5_link" src="" width="" /></li>
	            <li style="float:right"><img id="jiangping6_link" src="" width="" /></li>
			</ul>
            <br style="clear:both"/>
        </div>
		</div>
         <div class="conbox3_nr"  style="border-right:3px solid #731864; border-left:3px solid #731864;">
       	<h3 class="title2">奖项说明</h3>
			<div class="text" style="color:#ffffff;">1.请点击大转盘“开始抽奖”按钮进行抽奖<br/>
				2.中奖后请填写准确个人资料，方便领取奖品时与您联系，如信息填写错误或不完整，则视为主动放弃
			</div>
         </div>
    </div>
		
      </div>
         
</body>

<script>
function scroll(obj) { 

	/*往左*/ 
	var tmp = (obj.scrollLeft)++; 
	//当滚动条到达右边顶端时 
	if (obj.scrollLeft == tmp) { 
		obj.innerHTML += obj.innerHTML; 
	} 
	//当滚动条滚动了初始内容的宽度时滚动条回到最左端 
	if (obj.scrollLeft >= obj.firstChild.offsetWidth) { 
		obj.scrollLeft = 0; 
	} 
} 

var _timer = setInterval("scroll(document.getElementById('scrollobj'))", 20); 

	$(function() {
		
		var initing = true;
		var bannerLink = '';
		
		var a = '${a }';
		var z = '${p }';
		var p = '${p2 }';
		var c = '${code }';
		
		init();
		
		function init(){
			$.ajax({
				type : "POST",
				cache : false,
				async : false,
				data:{'a':a,'ltyType':'6'},
				url : "${ctx}/lty/getLtyIniyInfo",
				dataType : "json",
				success : function(data) {
					if(data != null && data != '' && data != undefined){
						$('#zhuanpanImg').attr('src',data.zhuanpan_link);
						
						$('#jiangping1_link').attr('src',data.jiangping1_link);
						$('#jiangping2_link').attr('src',data.jiangping2_link);
						$('#jiangping3_link').attr('src',data.jiangping3_link);
						$('#jiangping4_link').attr('src',data.jiangping4_link);
						$('#jiangping5_link').attr('src',data.jiangping5_link);
						$('#jiangping6_link').attr('src',data.jiangping6_link);
						
						$('#scrollobj').html(data.top_croll_text);
						
						$('#top_img_id').attr('src',data.top_img);
						
						bannerLink = data.banner_link;
						
						initing = false;
					}else{
						alert('初始化错误,请刷新重试');
					}
				},
				error : function() {
					alert('网络有错误');
				}
			});
		}

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
					$.cookie("ltyChoujiang"+a, "true", {
						expires : 7
					});
					running = false;
					$('#submitFormDiv').show();
				}
			});
		};

		$("#lotteryBtn").rotate({
			bind : {
				click : function() {
					if (initing) {
						return;
					}
					if (running) {
						return;
					}
					if ($.cookie("ltyChoujiang"+a)) {
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
						//http://192.168.0.53:8989/fourgad/pa/www.meixx.cn/frontshop/befRegister?m=3000&cp=1
						$('#queding_btn').attr('href','${ctx}'+data.link_url+'?a='+a);
						if(data.lty_angle === '135'){
						   $('#queding_btn').attr('href','http://www.meixx.cn/frontshop/befRegister?m=3000&cp=2');
						}
						if(data.lty_angle === '90'){
						   $('#queding_btn').attr('href','http://www.meixx.cn/frontshop/befRegister?m=3000&cp=1');
						}
						$('.tanchuang_input2').html(data.title_info);
						
					    $.session.set('ok_title', data.ok_title);
						$.session.set('ok_info', data.ok_info);
						$.session.set('lty_angle', data.lty_angle);
						
						$.session.set('banner_link', bannerLink);
						rotateFunc(parseFloat(data.lty_angle));
						
						$('#lotteryBtn').attr('src','http://rs.iadcn.com/pingan/static/zhuanpan/images/lty_d6/qidong_btn.png');
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
