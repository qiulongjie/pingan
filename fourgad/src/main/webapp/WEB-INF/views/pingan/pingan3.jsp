<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0,user-scalable=no">
<title>中国平安保险</title>
<style type="text/css">
body{margin:0;padding:0; margin:0 auto;line-height:22px;font-family:"微软雅黑";background:#fff;-webkit-text-size-adjust:none;color:#7e8c8d; width:720px;}
form,ul,li,p,h1,h2,h3,h4,h5,h6,dl,dt,dd{margin:0;padding:0; font-weight:normal;}
input,select{font-size:12px; color:#666; padding:4 0 0 5px; line-height:18px;}
img{border:0;}
.left{float:left;}
.right{float:right;}
ul,li{list-style-type:none;}
.con{ background:#ecf1f0; width:720px; position:relative;}
.con .bg{ background:#fff; border:1px solid #e0e5e4; width:663px; margin:0 auto;}
.con h3{  font-size:36px; color:#599c66;line-height:36px;}
.con .wenjuan{ padding:20px;}
.con h3.wenjuan_icon{ background: url(images/icon.gif) 0 0 no-repeat; height:36px; padding-left:36px; }
.con .red_bg{ float:left; height:79px; background:#ed6f67; width:554px; line-height:79px; font-size:32px; color:#fff; text-align:center;}
.con .wenjuan dl{ margin:20px 0; font-size:24px;}
.con .wenjuan dl dt{ line-height:40px;}
.con .wenjuan dl dd{ line-height:40px;}
.con .wenjuan dl dd span{width: 310px;display: block;float: left;}
.renling{ position:absolute;left:0px; width:800px; height:96px;}
.tianxie{ padding:106px 20px 10px 30px;}
.tianxie_icon{background: url(images/icon.gif) 0 -40px no-repeat; height:36px; padding-left:42px; padding-bottom:20px;margin: 20px 0 0 0;}
.info_write{}
.info_write p{ padding:5px 0;}
.info_write span{float:left;line-height:50px; font-size:24px;}
.info_write div{padding-left:50px; line-height:50px;}
.info_write div input.inp_w{ width:334px; height:44px; background:#eeeeee; border:2px solid #bfbfbf; border-radius:2px;}
.btnbox{ margin:20px auto; width:598px;}
.sub_btn{ border:none; border-radius:3px; background:#ed6f67; color:#fff; width:598px; height:87px; line-height:87px; font-size:40px; cursor:pointer;}
.shenming{font-size:24px; color:#7e8c8d; line-height:30px; padding:0px 20px 30px 30px;}
.info_write div input.yanzheng_btn{ background:#8acd97; width:164px; height:50px; border-radius:3px; border:none; font-size:24px; line-height:50px; color:#fff; cursor:pointer;}




</style>
    <script type="text/javascript" src="${ctx}/static/js/jquery-1.7.1.min.js"></script>
    <script type="text/javascript" src="${ctx}/static/js/jquery.cookie.js"></script>
    <script type="text/javascript" src="${ctx}/static/js/form2.js"></script>
    
</head>

<body>
<div class="banner">
	<img src="${ctx}/static/images/banner.gif"/>
</div>

<div class="con">
 <form action="${ctx}/pingan2" name="form1" method="post">
<div class="bg">
	<div class="wenjuan">
		<h3 class="wenjuan_icon">调研问卷</h3>
		<dl>
			<dt>
				(1)您喜欢与谁同行？
			</dt>
			<dd>
				<span>
					<input type="radio" value="1" id="whoisid" name="whois"   />
					<label for="">闺蜜or好基友</label>
				</span>
				<span>
					<input type="radio" value="2" id="whoisid" name="whois"   />
					<label for="">家人</label>
				</span>
				<span>
					<input type="radio" value="3" id="whoisid" name="whois"   />
					<label for="">自己</label>
				</span>
			</dd>
			<br style="clear:both"/>
		</dl>
		
		
		<dl>
			<dt>
				(2)您会选择境内还是境外游？
			</dt>
			<dd>
				<span>
					<input  name="country"  type="radio"  value="1" id="countryid"/>
					<label for="">外面太危险，境内 </label>
				</span>
				<span>
					<input  name="country"  type="radio"  value="2" id="countryid"/>
					<label for="">有钱就任性，境外</label>
				</span>
				
			</dd>
			<br style="clear:both"/>
		</dl>
		
		
		<dl>
			<dt>
				(3)您今年的旅游预算是？ 
			</dt>
			<dd>
				<span>
					<input  name="fee"  type="radio"  value="1" id="feeid"/>
					<label for="">1000元以内</label>
				</span>
				<span>
					<input  name="fee"  type="radio"  value="2" id="feeid"/>
					<label for=""> 1000－5000元</label>
				</span>
				<span>
					<input name="fee"  type="radio"  value="3" id="feeid"/>
					<label for="">5000－10000元</label>
				</span>
				<span>
					<input name="fee"  type="radio"  value="4" id="fee"/>
					<label for="">10000元以上</label>
				</span>
			</dd>
			<br style="clear:both"/>
		</dl>
		
		
		<dl>
			<dt>
				(4)在旅游途中，您是否愿意为自己及家人购买或免费领取一份交通意外保险？？
			</dt>
			<dd>
				<span>
					<input name="isneed"  type="radio" type="radio" value="1" id="isneedid"/>
					<label for="">非常有必要</label>
				</span>
				<span>
					<input name="isneed"  type="radio" type="radio" value="2" id="isneedid"/>
					<label for=""> 在条件允许下有必要   </label>
				</span>
				<span>
					<input name="isneed"  type="radio" type="radio" value="3" id="isneedid"/>
					<label for="">没必要</label>
				</span>
			</dd>
			<br style="clear:both"/>
		</dl>
		
	</div>
	
	
	
	
	
<!--第二部分-->	
		<div>
		<div class="renling">
	 		<div class="left"><img src="${ctx}/static/images/tit_left.gif" /></div>
			<div class="red_bg">认领一份免费平安保险来保障您的安全</div>
			<div><img src="${ctx}/static/images/tit_right.gif" /></div>
		</div>
		
		<div class="tianxie">
			<h3 class="tianxie_icon">
				填写信息领取大礼
			</h3>
			<div class="info_write">
				<p>
					<span>姓名：</span>
					<div><input id="txtName" name="uname" type="text" placeholder="请填写您的真实姓名"  class="inp_w"/></div>
				</p>
				<p>
					<span>性别：</span>
					<div><span style="padding-right:50px;">
					<input  type="radio" value="男"  name="ddlSex"  type="radio" />男</span>
					<span><input  type="radio" value="女" name="ddlSex" type="radio"/>女</span>
					<br style="clear:both"/>
					</div>
				</p>
				<p>
					<span>生日：</span><div>
					<input id="txtBirthday" name="birthday" type="text" placeholder="例如：19820308"  class="inp_w"/></div>
				</p>
				<p>
					<span>手机：</span>
					<div>
					<input id="txtMobile" name="phone" type="text" placeholder="请填写您的真实电话"  class="inp_w left" style="margin-right:10px;"/>
					
					</div>
				</p>
			</div>
			
			<div style="color:#f75248; font-size:20px; padding:20px 0 0 0;">温馨提示：平安人寿后续致电以确认免费保障生效事宜，请留意接听</div>
		</div>
		
		<div class="btnbox">
			<input type="button" class="sub_btn" value="立即领取" onclick="checkForm('s')" />
		</div>
		
		<div class="shenming">
			*您的个人信息只供参与本次赠险调研及后续保险咨询服务产品
		</div>
		
	</div>
 </div>
</div>
</form>




</body>
</html>
