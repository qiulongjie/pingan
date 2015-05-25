<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0,user-scalable=no">
<title>中国平安保险</title>
<link rel="stylesheet" type="text/css" href="${ctx}/static/css/main.css">
	<script type="text/javascript" src="${ctx}/static/js/jquery-1.7.1.min.js"></script>
    <script type="text/javascript" src="${ctx}/static/js/jquery.cookie.js"></script>
    <script type="text/javascript" src="${ctx}/static/js/form2.js"></script>
</head>

<body>
<div class="banner">
	<img src="${ctx}/static/images/banner.gif" width="100%"/>
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
		<!--<div class="renling">
	 		<div class="left"><img src="images/tit_left.gif" /></div>
			<div class="red_bg">认领一份免费平安保险来保障您的安全</div>
			<div><img src="images/tit_right.gif" /></div>
		</div>-->
		<div><img src="${ctx}/static/images/tit.gif" width="100%" /></div>
		
		<div class="tianxie">
			<h3 class="tianxie_icon">
				填写信息领取大礼
			</h3>
			<div class="info_write">
				<p>
					<span>姓名：</span>
					<div><input  id="txtName" name="uname" type="text" placeholder="请填写您的真实姓名"  class="inp_w"/></div>
				</p>
				<p>
					<span>性别：</span>
					<div><span style="padding-right:50px;">
					<input  type="radio" value="男"  name="ddlSex"  type="radio" />男</span>
					<span><input  type="radio" value="女" name="ddlSex" type="radio" />女</span>
					<br style="clear:both"/>
					</div>
				</p>
				<p>
					<span>生日：</span><div><input id="txtBirthday" name="birthday"  type="text" placeholder="例如：19820308"  class="inp_w"/></div>
				</p>
				<p>
					<span>手机：</span>
					<div><input id="txtMobile" name="phone" type="text" placeholder="请填写您的真实电话"  class="inp_w left" style="margin-right:10px;"/>
					<input name="code" type="hidden" value="${code }" />
					<input name="a" type="hidden" value="${a }" />
					</div>
				</p>
			</div>
			
			<div style="color:#f75248;  padding:10px 0 0 0;">温馨提示：平安人寿后续致电以确认免费保障生效事宜，请留意接听</div>
		</div>
		
		<div class="btnbox">
			<!--<input type="Button" class="sub_btn" value="立即领取"  />-->
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

