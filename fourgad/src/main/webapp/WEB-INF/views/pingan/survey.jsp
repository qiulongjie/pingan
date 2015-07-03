<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta>
<meta content="telephone=no" name="format-detection" />
<meta content="email=no" name="format-detection" />
<meta name="viewport"
	content="initial-scale=1.0, maximum-scale=1.0, user-scalable=no,width=device-width" />
	
<title>微调研</title>

<%-- <link rel="stylesheet" type="text/css" href="${ctx}/static/styles/main.css"> --%>
<script type="text/javascript" src="http://rs.iadcn.com/pingan/static/zhuanpan/js/jquery-1.7.1.min.js"></script>
<%-- <script type="text/javascript" src="${ctx}/static/js/jquery-1.7.1.min.js"></script> --%>
<script type="text/javascript" src="http://rs.iadcn.com/pingan/static/zhuanpan/js/baidu_count.js"></script>
<style type="text/css">
body{margin:0;padding:0; margin:0 auto;line-height:22px;font-family:"微软雅黑";background:#fff;-webkit-text-size-adjust:none;color:#7e8c8d; width:100%;font-size: 14px;}
form,ul,li,p,h1,h2,h3,h4,h5,h6,dl,dt,dd{margin:0;padding:0; font-weight:normal;}

img{border:0;}
.left{float:left;}
.right{float:right;}
ul,li{list-style-type:none;}
.clear{ clear:both;}
.rel{margin: 0px auto;max-width: 320px; z-index:0;background:url(../images/lottery/bg.png) 0 0 repeat; z-index:2; position:relative;}
html,body{ height:100%;}
a{ text-decoration:none;}

.top{ position:relative; z-index:999; /*height:239px; max-width:320px; width:320px;*/display:block;background:url(../images/lottery/bg.png) 0 0 repeat;width:100%; min-height:312px; padding-bottom:12px;}
.zhuan_box{}
.rel img,.con img{border: 0px currentColor;border-image: none;max-width: 100%;}
.arrow{margin: 0px auto;width:100px;cursor: pointer; max-width: 100px; left:109px;top:59px; position:absolute; z-index:9999;}
.zhuan_bg{top:0px;width:85%;position: absolute; z-index:99; left:7%;}
.red_bg{ position: absolute; z-index:1; width:100%; display:block; }


.con{ background:url(../images/lottery/bg.png) 0 0 repeat;width:100%;padding-top:20px;/* width:320px; max-width:320px;*/}
.conbox{ background:#fbf9f5; border-radius:8px; border:3px solid #dab37c; width:94%; margin:0 auto;position:relative; z-index:99;}
.conbox .tit1{position:absolute;left:-3px; top:5px;}

.nr{ font-size:14px; color:#907e6b; line-height:20px; padding:10px; margin-top:14px;}

.tanchuang_input{padding:10px; width:90%; margin:0 auto;}
.tanchuang_input2{padding:15px; width:90%; margin:0 auto; font-size:14px;}
.tanchuang_input div{ margin:5px 0;}
.tanchuang_input div span{ width:20%;}
.tanchuang_input .input1{ width:80%; border:1px solid #d6bea4; border-radius:2px; height:30px; line-height:30px;}

.blackbg{width: 100%; height: 100%; background-color: #000; opacity: 0.6; position: fixed; top: 0; left: 0; z-index:9999;}
.tanchuang{width:80%; left: 50%; margin-left: -40%; top: 50%; margin-top: -100px; position: absolute; z-index: 3; background-color: #fff; z-index:99999; border-radius:8px;}
.tanchuang_tit{background:#cf0000; text-align:center; color:#fff; height:40px; line-height:40px; font-weight:bold; border-radius:8px 8px 0 0; font-size:18px;}
.sub_btn{width:90%; height:40px; line-height:40px; color:#fff; background:#ff6400; border-radius:8px; display:block; margin:0 auto; text-align:center; outline:none;}
a.sub_btn:hover{ background:#e95b00;}


.queding_btn{text-align:center; line-height:42px; height:42px; border-top:1px solid #ddd; background:#f3f3f3; border-radius:0 0 8px 8px; font-weight:bold; }
.queding_btn a{ color:#666;font-size:16px; display:block; width:100%; border-radius:0 0 8px 8px;}
.queding_btn a:hover{background:#dddddd; border-radius:0 0 8px 8px;}

/*调查*/
.diaocha_tit {
	width: 100%;
	text-align: center;
	color: #fff;
	background: #e75504;
	height: 46px;
	line-height: 46px;
	font-size: 18px;
	font-weight: bold;
	margin-bottom: 10px;
}

.diaocha_nr {
	width: 96%;
	margin: 0 auto;
}

.diaocha_nr div dl dt {
	font-weight: bold;
	line-height: 46px;
	font-size: 18px;
	font-weight: bold;
	color: #666;
}

.diaocha_nr div dl dd {
	width: 100%;
	line-height: 34px;
	font-size: 16px;
}

.diaocha_nr dl dd span {
	width: 50%;
	display: block;
	float: left;
}

.diaocha_nr dl dd span label {
	
}

.ganxie p {
	margin: 10px 0;
	text-align: center;
}

.ganxietext {
	padding: 10px;
	background: #f3f3f3;
	color: red;
	margin-top: 20px;
}

.choujiangbtn a {
	width: 90%;
	height: 46px;
	line-height: 46px;
	text-align: center;
	color: #fff;
	background: #e75504;
	margin: 0 auto;
	border-radius: 5px;
	display: block;
	font-size: 20px;
}
.choujiangbtn .inputsumbit {
	width: 90%;
	height: 46px;
	line-height: 46px;
	text-align: center;
	color: #fff;
	background: #e75504;
	margin: 0 auto;
	border-radius: 5px;
	display: block;
	font-size: 20px;
	border:none;
	-webkit-appearance:none; /*去除input默认样式*/}

}




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

	<div class="diaocha_tit">
		参加微信调研，即可抽奖，赢取百元话费！
	</div>
	<div class="diaocha_nr">
	<form action="${ctx}/pa/submit/survey" id="form1" name="form1" method="get" onsubmit="return checkInput()">
	  <input name="code" type="hidden" value="${code }" />
	  <input name="a" type="hidden" value="${a }" />
	  <input name="p" type="hidden" value="${p }" />
	  <input name="p2" type="hidden" value="${p2 }" />
		<div>
			<dl>
				<dt>1.	您的微龄是？</dt>
				<dd class="radio_show_group">
					<label for="a"><span><input type="radio" value="1" id="a" name="aRadio">A.6个月</span></label>
					<label for="b"><span><input type="radio" value="2" id="b" name="aRadio">B.1年</span></label>
					<label for="c"><span><input type="radio" value="3" id="c" name="aRadio">C.2年</span></label>
					<label for="d"><span><input type="radio" value="4" id="d" name="aRadio">D.3年</span></label>
				</dd>
			</dl>
			
			<dl>
				<dt>2.	您最喜欢的微信服务是？</dt>
				<dd class="radio_show_group">
					<label for="e"><span><input type="radio" value="1" id="e" name="bRadio">A.朋友圈</span></label>
					<label for="f"><span><input type="radio" value="2" id="f" name="bRadio">B.游戏</span></label>
					<label for="g"><span><input type="radio" value="3" id="g" name="bRadio">C.微商</span></label>
					<label for="h"><span><input type="radio" value="4" id="h" name="bRadio">D.红包</span></label>
				</dd>
			</dl>
			
			<dl>
				<dt>3.	您一天使用微信的时间是？</dt>
				<dd class="radio_show_group">
					<label for="aa"><span><input type="radio" value="1" id="aa" name="cRadio">A.不使用</span></label>
					<label for="bb"><span><input type="radio" value="2" id="bb" name="cRadio">B.手机开机就上着</span></label>
					<label for="cc"><span><input type="radio" value="3" id="cc" name="cRadio">C.有消息才上</span></label>
					<label for="dd"><span><input type="radio" value="4" id="dd" name="cRadio">D.1小时以内</span></label>
				</dd>
			</dl>
			
			<dl>
				<dt>4.	您对朋友圈广告的接受度是？</dt>
				<dd class="radio_show_group">
					<label for="ee"><span><input type="radio" value="1" id="ee" name="dRadio">A.喜欢</span></label>
					<label for="ff"><span><input type="radio" value="2" id="ff" name="dRadio">B.一般接受</span></label>
					<label for="gg"><span><input type="radio" value="3" id="gg" name="dRadio">C.无所谓</span></label>
					<label for="hh"><span><input type="radio" value="4" id="hh" name="dRadio">D.讨厌</span></label>
				</dd>
			</dl>
		</div>
		<br class="clear"/>
		
		
	</div>
	
	
	<div class="ganxie" style="padding-bottom:10px;">
		<!-- <p class="ganxietext">感谢您参加微信的调研活动，可参加一次抽奖</p> -->
		<p class="choujiangbtn">
			<!-- <a href="#" id="surveyChoujiang">点击提交</a> -->
			<input class="inputsumbit" type="submit" value="点击提交" /> 
		</p>
	</div>
	</form>
</body>


<script>
function checkInput(){
	var countCheck = 0;
	var count = 0;
	$('.diaocha_nr input:radio:checked').each(function(i,item){
		countCheck+=1;
	});
	$('.diaocha_nr .radio_show_group').each(function(i,item){
		count+=1;
	});
	var check = count-countCheck;
	if(check != 0 ){
		alertText('您还有'+check+'个选项未选择！请先完成，谢谢！');
		return false;
	}
	return true;
}

function alertText(txt){
	$('#alert_text').text(txt);
	$('#submitFormDiv').show();
}

$(function(){
	$('#queding_btn_xiaoshi').click(function(e){
    	$('#submitFormDiv').hide();
    });
	
	/* $('#surveyChoujiang').click(function(e){
		if(checkInput()){
			//document.form1.submit();
			$('#form1').submit();
		}
	}) */
	/* function checkInput(){
		alert(12);
		var countCheck = 0;
		var count = 0;
		$('.diaocha_nr input:radio:checked').each(function(i,item){
			countCheck+=1;
		});
		$('.diaocha_nr .radio_show_group').each(function(i,item){
			count+=1;
		});
		var check = count-countCheck;
		if(check != 0 ){
			alert('您还有'+check+'个选项未选择！请先完成，谢谢！');
			return false;
		}
		return true;
	} */
})
</script>
</html>