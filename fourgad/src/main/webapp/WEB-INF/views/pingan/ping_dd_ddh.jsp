<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0,user-scalable=no">
<title>意外险免费送，先到先得！</title>
<meta name="keywords" content="意外险" >
<meta name="description" content="25万元意外险免费送，先到先得！">

<!-- <link rel="stylesheet" type="text/css" href="${ctx }/static/styles/ping_dd.css">-->
<link rel="stylesheet" type="text/css" href="http://rs.iadcn.com/pingan/static/zhuanpan/styles/ping_dd.css"> 
<link rel="stylesheet" type="text/css" href="http://rs.iadcn.com/pingan/static/zhuanpan/styles/tanchuang.css">
<script type="text/javascript" src="http://rs.iadcn.com/pingan/static/js/jquery-1.7.1.min.js"></script>
<script type="text/javascript" src="http://rs.iadcn.com/pingan/static/js/jquery.cookie.js"></script>
<script type="text/javascript" src="http://rs.iadcn.com/pingan/static/zhuanpan/js/jquerysession.js"></script>
<%-- <script type="text/javascript" src="${ctx}/static/js/form_code_cookie.js"></script> --%>
<!-- <script type="text/javascript" src="http://rs.iadcn.com/pingan/static/js/form2.1.2.js"></script> -->
<script type="text/javascript" src="http://rs.iadcn.com/pingan/static/js/form_code_cookie.js"></script>
<script type="text/javascript" src="http://rs.iadcn.com/pingan/static/zhuanpan/js/baidu_count.js"></script>
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
<div  id="submitFormDiv2" style="display:none;">
	<div class="blackbg"></div>
	<div class="tanchuang" >
		<div class="tanchuang_input2" style="color:#666;">
			您的奖已经领取成功,下次再来吧!
		</div>
	</div>
</div>
<div class="banner">
	<img src="http://rs.iadcn.com/pingan/static/zhuanpan/images/didi/banner.jpg" width="100%"/>
</div>

<div class="con">
<div class="bg">
	<div class="wenjuan">
			<div style="margin-bottom:10px;"><img src="http://rs.iadcn.com/pingan/static/zhuanpan/images/didi/tit2.gif" width="100%" /></div>

		<h3 class="wenjuan_icon">调研问卷</h3>
		<dl>
			<dt>
				(1)您上班选择哪种交通方式?
			</dt>
			<dd>
				<span>
					<input type="radio" value="1" id="x" name="tx"  type="radio" />
					<label for="x">自驾  </label>
				</span>
				<span>
					<input type="radio" value="2" id="c" name="tx"  type="radio" checked/>
					<label for="c">公共交通</label>
				</span>
				<span>
					<input type="radio" value="3" id="a" name="tx"  type="radio"/>
					<label for="a">步行</label>
				</span>
			</dd>
			<br style="clear:both"/>
		</dl>
		
		
		<dl>
			<dt>
				（2）您觉得家人是否需要一份意外险？
			</dt>
			<dd>
				<span>
					<input  name="xz"  type="radio" type="radio" value="1" id="s" checked/>
					<label for="s">很有必要，保障很重要  </label>
				</span>
				<span>
					<input  name="xz"  type="radio" type="radio" value="2" id="w"/>
					<label for="w">有合适的会考虑购买</label>
				</span>
				<span>
					<input  name="xz"  type="radio" type="radio" value="3" id="w"/>
					<label for="w">没有必要</label>
				</span>
				
			</dd>
			<br style="clear:both"/>
		</dl>
	</div>
	
<!--第二部分-->	
		<div>
		<!--<div class="renling">
	 		<div class="left"><img src="images/tit_left.gif" /></div>
			<div class="red_bg">认领一份免费平安保险来保障您的安全</div>
			<div><img src="images/tit_right.gif" /></div>
		</div>-->
		<div class="tianxie">
		<form action="${ctx}/ping/ddhSumbit" name="form1" method="get">
		   <input name="code" type="hidden" value="${code }" />
		   <input name="a" type="hidden" value="${a }" />
		    <input name="failure_path" type="hidden" value="ping_dd_ddh" />
		    <input name="failure_path2" type="hidden" value="ping_dd_ddh_f" />
		    <input name="ok_p" type="hidden" value="ping_dd_ok" />
			<h3 class="tianxie_icon">
				填写信息领取大礼
			</h3>
			<div class="info_write">
				<p>
					<span>姓&nbsp;&nbsp;&nbsp;名：</span>
					<div><input value="${uname }" id="txtName" name="uname"  type="text" placeholder="请填写您的真实姓名"  class="inp_w"/></div>
				</p>
				<p>
					<span>性&nbsp;&nbsp;&nbsp;别：</span>
					<div><span style="padding-right:50px;"><input  type="radio" value="Male" name="ddlSex"  type="radio" checked/>男</span>
					<span><input  type="radio" value="Female" name="ddlSex" type="radio"/>女</span>
					<br style="clear:both"/>
					</div>
				</p>
				<p>
					<span>生&nbsp;&nbsp;&nbsp;日：</span><div><input value="${birthday }" id="txtBirthday" name="birthday" type="text" placeholder="例如：19820308"  class="inp_w"/></div>
				</p>
				<p>
					<span>手&nbsp;&nbsp;&nbsp;机：</span>
					<div><input value="${phone }" id="txtMobile" name="phone" type="text" placeholder="免费接收电子保单"  class="inp_w" />
					<!-- <input name="" type="button" value="发送验证码" class="yanzheng_btn" /> -->
					</div>
				</p>
				<p>
					<span>验证码：</span>
					<div><input id="vcode" name="vryCode"  type="text" class="inp_w left" style="margin-right:10px; width:50%;"/>
					<div style="height:42px; float:left;padding:0;">
					 <img src="${ctx}/ping/go/img" width="100%" height="100%" id="code_img"/>
                    </div>
					</div>
					<br class="clear">
				</p>
			</div>
			</form>
			<div style="color:#f75248;  padding:10px 0 0 0;">温馨提示：本保险保费已支付，无需用户额外支付！</div>
		</div>
		
		<div class="btnbox">
			<!--<input type="Button" class="sub_btn" value="立即领取"  />-->
			<input type="button" class="sub_btn" value="立即领取" onclick="checkForm('s')" />
		</div>
		
		
		
	</div>
 </div>
</div>


<div class="friendlink">
	<div class="hezuo"><h3>合作伙伴</h3></div>
	<ul >
		<li style="float: left;margin: 0 1%;"><img src="http://rs.iadcn.com/pingan/static/zhuanpan/images/ping_logo/1.png" /></li>
		<li style="float: left;margin: 0 1%;"><img src="http://rs.iadcn.com/pingan/static/zhuanpan/images/ping_logo/2.png" /></li>
		<li style="float: left;margin: 0 1%;"><img src="http://rs.iadcn.com/pingan/static/zhuanpan/images/ping_logo/3.png" /></li>
		<li style="float: left;margin: 0 1%;"><img src="http://rs.iadcn.com/pingan/static/zhuanpan/images/ping_logo/4.png" /></li>
		<li style="float: left;margin: 0 1%;"><img src="http://rs.iadcn.com/pingan/static/zhuanpan/images/ping_logo/5.png" /></li>
		<li style="float: left;margin: 0 1%;"><img src="http://rs.iadcn.com/pingan/static/zhuanpan/images/ping_logo/6.png" /></li>
		<li style="float: left;margin: 0 1%;"><img src="http://rs.iadcn.com/pingan/static/zhuanpan/images/ping_logo/7.png" /></li>
	</ul>
</div>


</body>
<script>
$(function() {
	
	var ping_title = $.session.get('ping_title');
	if(ping_title){
		if(ping_title != '' && ping_title != 'null'){
		   $('#ping_title').html(ping_title);
		}
	}
	var vry = "${vry}";
	var a = "${a}";
	if(vry === 'failure_vry'){
		alert("验证码错误！");
		$('#vcode').css({ 'borderColor': 'red' });
        document.getElementById('vcode').value = '';
	}else{
		if($.cookie("cookie"+a)){
			if($.cookie("cookie_cnt"+a)){
				var cnt = parseInt($.cookie("cookie_cnt"+a));
				if(cnt<=1){
					$('#submitFormDiv2').show();
				}
			}else{
				$('#submitFormDiv2').show();
			}
		}
	} 
	
	 $("#vcode").focus(function(){
		 $('#code_img').attr('src','${ctx}/ping/go/img?'+ Math.floor(Math.random() * 100)).fadeIn();
	 });

		$('#code_img').click(function() {//生成验证码  
			$(this).hide().attr('src','${ctx}/ping/go/img?'+ Math.floor(Math.random() * 100)).fadeIn();
		})
})
</script>
</html>

