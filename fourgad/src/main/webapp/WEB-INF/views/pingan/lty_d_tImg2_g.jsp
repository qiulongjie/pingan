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
<%-- <link rel="stylesheet" type="text/css" href="${ctx }/static/styles/main_lty_d2.css"> --%>
<link rel="stylesheet" type="text/css" href="http://rs.iadcn.com/pingan/static/zhuanpan/styles/main_lty_d2.css">
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
			恭喜您！中了三等奖，获得最高保额25万大礼包，领取查看详情
		</div>
		<div class="queding_btn">
<!--  			<a id="queding_btn" href="#">确&nbsp;&nbsp;&nbsp;定</a>  -->
 			<a id="a_goto_bz" href="#">确&nbsp;&nbsp;&nbsp;定</a>
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

	<img id="top_img_id" src="http://rs.iadcn.com/pingan/static/zhuanpan/images/lty_d2/banner_xindian.jpg"  width="100%" />
	<div class="big_box3">
    	<div class="big_box_con3">
		<div class="tongzhi">
			<div id="scrollobj" style="white-space:nowrap;overflow:hidden; line-height:32px; color:#e74d2b;">周*(139****4582)六等奖，获得50M流量&nbsp;&nbsp;&nbsp;&nbsp;肖*(189****2159)二等奖，获得ipad Air2 9.7英寸平板电脑&nbsp;&nbsp;&nbsp;&nbsp;陈**(135****2193)三等奖，获得价值25万元的交通意外保险&nbsp;&nbsp;&nbsp;&nbsp;黄*(135****2422)四等奖，获得20000毫安移动电源&nbsp;&nbsp;&nbsp;&nbsp;葛**(139****9703)五等奖，获得50元微信红包&nbsp;&nbsp;&nbsp;&nbsp;吴**(159****0124)六等奖，获得50M流量&nbsp;&nbsp;&nbsp;&nbsp;蔡*(158****0707)六等奖，获得50M流量&nbsp;&nbsp;&nbsp;&nbsp;韩**(159****5764)三等奖，获得价值25万元的交通意外保险&nbsp;&nbsp;&nbsp;&nbsp;陈*(159****6745)五等奖，获得50元微信红包&nbsp;&nbsp;&nbsp;&nbsp;李*(135****5122)六等奖，获得50M流量&nbsp;&nbsp;&nbsp;&nbsp;程*(158****4670)五等奖，获得50元微信红包&nbsp;&nbsp;&nbsp;&nbsp;汤*(135****9308)四等奖，获得2000毫安移动电源&nbsp;&nbsp;&nbsp;&nbsp;章**(159****6975)二等奖，获得ipad Air2 9.7英寸平板电脑&nbsp;&nbsp;&nbsp;&nbsp;赵**(139****1684)五等奖，获得50元微信红包&nbsp;&nbsp;&nbsp;&nbsp;黄*(139****5122)三等奖，获得价值25万元的交通意外保险&nbsp;&nbsp;&nbsp;&nbsp;陈**(139****6408)四等奖，获得2000毫安移动电源&nbsp;&nbsp;&nbsp;&nbsp;袁*(139****0065)六等奖，获得50M流量&nbsp;&nbsp;&nbsp;&nbsp;高**(159****7453)五等奖，获得50元微信红包</div>
		</div>
		
		
        <div class="zhuanpan">
        	<div class="zhuan_pic4"> <img src="http://rs.iadcn.com/pingan/static/zhuanpan/images/lty_d2/zhuanpan1_6plus.png" width="100%" id="zhuanpanImg"/> </div>
            <div id="zhan_qidong1" class="zhuan_qidong3"><img src="http://rs.iadcn.com/pingan/static/zhuanpan/images/lty_d/qidong_btn3.gif" id="lotteryBtn" /></div>
        </div>
        </div>
        <div class="y_bg3"><img src="http://rs.iadcn.com/pingan/static/zhuanpan/images/lty_d/bg3.png"  width="100%" /></div>
    </div>
    
    <div class="con33">
		  	  			<div style="background:url(http://rs.iadcn.com/pingan/static/zhuanpan/images/lty_d/bg_bolang.png) 0 0 repeat-x; width:100%; height:12px; display:block; margin:0; position:relative;z-index:99999;bottom:-8px;"></div>
   	  <div class="conbox3">

       <div class="conbox3_nr">
	   	<h3>奖项设置</h3>
        <div class="jiangpin_list2">
        	<ul>
				<li style="float:left"><img id="jiangping1_link" src="http://rs.iadcn.com/pingan/static/zhuanpan/images/lty_d2/1.png" width="" /></li>
	            <li style="float:right"><img id="jiangping2_link" src="http://rs.iadcn.com/pingan/static/zhuanpan/images/lty_d2/2.png" width="" /></li></ul>
	            <ul>
	            <li style="float:left"><img id="jiangping3_link" src="http://rs.iadcn.com/pingan/static/zhuanpan/images/lty_d2/3.png" width="" /></li>
				<li style="float:right"><img id="jiangping4_link" src="http://rs.iadcn.com/pingan/static/zhuanpan/images/lty_d2/4.png" width="" /></li></ul>
				<ul>
	            <li style="float:left"><img id="jiangping5_link" src="http://rs.iadcn.com/pingan/static/zhuanpan/images/lty_d2/5.png" width="" /></li>
	            <li style="float:right"><img id="jiangping6_link" src="http://rs.iadcn.com/pingan/static/zhuanpan/images/lty_d2/6.png" width="" /></li>
			</ul>
            <br style="clear:both"/>
        </div>
		</div>
         <div class="conbox3_nr">
       	<h3>抽奖说明</h3>
			<div class="text">1.请点击大转盘“开始抽奖”按钮进行抽奖<br/>
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
		var ping_title = '';
		
		var a = '${a }';
		var z = '${p }';
		var p = '${p2 }';
		var c = '${code }';
		
		init();
		
		function init(){
			if(z){
				
			}else{
				z = '1';
			}
			initing = false;
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
					if ($.cookie("ltyChoujiang"+a)) {
						if ($.cookie("ltyChoujiang_cnt"+a)){
							var cnt = parseInt($.cookie("ltyChoujiang_cnt"+a));
							cnt = cnt - 1;
							$.cookie("ltyChoujiang_cnt"+a, cnt+"", {
								expires : parseInt(z)
							});
						}
					}else{
						$.cookie("ltyChoujiang"+a, "true", {
							expires : parseInt(z)
						});
						var cnt = 1;
						if(parseInt(p)){
						  cnt=parseInt(p);
						}
						$.cookie("ltyChoujiang_cnt"+a, cnt+"", {
							expires : parseInt(z)
						});
					}
					running = false;
					$('#a_goto_bz').attr('href','${ctx }/pa1/ping_bz_g?code=ping_bz_ok_g2&p='+z+'&p2='+p+'&a='+a);
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
						if ($.cookie("ltyChoujiang_cnt"+a)){
							var cnt = parseInt($.cookie("ltyChoujiang_cnt"+a));
							if(cnt<=1){
								$('#submitFormDiv2').show();
								return;
							}
						}
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
				url : "${ctx}/pingLottery/getLottery2",
				dataType : "json",
				success : function(data) {
					rotateFunc(parseFloat(data)-18);
					$('#lotteryBtn').attr('src','http://rs.iadcn.com/pingan/static/zhuanpan/images/lty_d/qidong_btn3.png');
				},
				error : function() {
					running = false;
				}
			});
		}
	})
</script>
</html>
