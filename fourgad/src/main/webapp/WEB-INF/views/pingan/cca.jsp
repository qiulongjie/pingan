<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0,user-scalable=no">
<title>中国平安车险计算器</title>

<link rel="stylesheet" type="text/css" href="${ctx }/static/styles/main_cca.css">
<script type="text/javascript" src="http://rs.iadcn.com/pingan/static/js/jquery-1.7.1.min.js"></script>

<script type="text/javascript" src="${ctx}/static/js/cca.js"></script>

</head>

<body>
    <div class="banner">
		<img src="${ctx }/static/images/cca/BANNER2.jpg"  width="100%"/>
	</div>
<form action="${ctx}/cca/submitGet" name="form1" method="get">
	<div class="con">
		<div class="conbox">
			<h3 class="jisuan_tit">2015年车险计算器</h3>
			<div class="input1">
				<p>
					<span class="inp_tit">行驶地区：</span><span>
					<select id="pSelect" name="province">
							
					</select></span> <span>
					<select id="cSelect" name="city">
							
					</select></span>
				</p>
				<p>
					<span class="inp_tit">车牌号：</span><span>
					<select id="carTselect" name="carT">
						<option>京</option>
						<option>津</option>
						<option>冀</option>
						<option>晋</option>
						<option>蒙</option>
						<option>辽</option>
						<option>吉</option>
						<option>黑</option>
						<option>沪</option>
						<option>苏</option>
						<option>浙</option>
						<option>皖</option>
						<option>赣</option>
						<option>闽</option>
						<option>鲁</option>
						<option>豫</option>
						<option>鄂</option>
						<option>湘</option>
						<option>粤</option>
						<option>桂</option>
						<option>琼</option>
						<option>渝</option>
						<option>贵</option>
						<option>云</option>
						<option>藏</option>
						<option>陕</option>
						<option>甘</option>
						<option>宁</option>
						<option>青</option>
						<option>新</option>	
					</select>
					</span> 
					<span>
					 <input id="carNum" name="carNum" type="text" class="inp" style="width:40%" />
					</span>
				</p>
				<p style="padding-left: 70px; line-height: 20px;">
					<input id="newCar" name="newCar" type="checkbox" value="1" style="vertical-align: middle;" id="xx" />
					<label for="xx">新车未上牌</label>
				</p>
				<!-- <p>
					<span class="inp_tit">购车时间：</span><span> <select name="year">
							<option>2015年</option>
							<option>2014年</option>
					</select>
					</span>
				</p> -->
				<p>
					<span class="inp_tit">车价：</span><span> 
					  <input id="carprice" name="carprice" type="text" class="inp" style="width: 50%; margin-right: 10px;" />
					</span>万元
				</p>
				<p>
					<span class="inp_tit">车主姓名：</span>
					<span>
					 <input id="uname" name="uname" type="text" class="inp" />
					</span>
				</p>
				<p>
					<span class="inp_tit">生日：</span><span>
					<input id="birthday" name="birthday" type="text" class="inp" placeholder="不填或者例如：19820308"/>
					</span>
				</p>
				<p>
					<span class="inp_tit">手机号：</span><span>
					<input id="phone" name="phone" type="text" class="inp" />
					</span>
				</p>
				<p style="padding-left:70px;">
					<input name="ywx" checked type="checkbox" value="1" id="mf"/>
					<label for="mf">车车安用户免费领取最高25万元意外险</label>
				</p>
				
			</div>
			<div style="padding-top: 5px;">
				<!-- <a href="#" class="jisuan_btn">开始计算</a> -->
				<input name="a" type="hidden" value="${a }" />
				<input type="button" class="jisuan_btn" onclick="checkForm()"  value="开始计算" />
			</div>
		</div>

	</div>
</form>
</body>
</html>

