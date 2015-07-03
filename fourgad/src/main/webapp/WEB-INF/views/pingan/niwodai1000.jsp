<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>存一元，尽享万元投资收益</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0,user-scalable=no">
<meta name="keywords" content="Array">
<meta name="description" itemprop="description" content="存一元，尽享万元投资收益">
<meta itemprop="name" content="存一元，尽享万元投资收益">
<meta itemprop="image" content="http://rs.iadcn.com/pingan/static/zhuanpan/images/niwodai/1.jpg">

<%-- <link rel="stylesheet" type="text/css" href="${ctx }/static/styles/niwodai1000.css">--%>
<link rel="stylesheet" type="text/css" href="http://rs.iadcn.com/pingan/static/styles/niwodai1000.css">
<script type="text/javascript" src="http://rs.iadcn.com/pingan/static/js/jquery-1.7.1.min.js"></script>
<%-- <script type="text/javascript" src="${ctx }/static/js/niwodai1000.js"></script> --%>
<script type="text/javascript" src="http://rs.iadcn.com/pingan/static/js/niwodai1000.js"></script>
<script type="text/javascript" src="http://rs.iadcn.com/pingan/static/zhuanpan/js/baidu_count.js"></script>
<style type="text/css">
.titpic{margin-left: 16px;margin-top: 10px;}
.tittext{font-family: "微软雅黑";font-size: 20px;font-weight: bold;margin-bottom: 10px;}
strong{color:#ef3e37;}
</style>
</head>

<body>
	<div>
		<img src="http://rs.iadcn.com/pingan/static/zhuanpan/images/niwodai/1.jpg" width="100%" />
		<img src="http://rs.iadcn.com/pingan/static/zhuanpan/images/niwodai/2.jpg" width="100%" />
		<div class="biaodan">
			<div class="inputbox">
				<p style="margin-bottom:6px;">
				<input id="txtMobile" name="phone"  type="text"  placeholder="输入真实手机号，领取一万元体验金" class="inp"/>
				</p>
				<p>
				<input  id="txtName" name="uname" type="text"  placeholder="输入真实姓名" class="inp"/>
				</p>
				<p style=" margin:10px 0;" onclick="checkclick()">
				<img name="checkImg" id="checkImg" width="18px;" align="top" src="http://rs.iadcn.com/pingan/static/zhuanpan/images/niwodai/moren.jpg" />
				<label for="checkImg">领取最高保额<strong>25万元</strong>的出行意外险一份即送<strong>100元</strong>红包大礼<strong style="font-weight:normal;">(限量500份，先到先得)</strong>！</label>
				</p>
			</div>
			<div class="inputbg"><img src="http://rs.iadcn.com/pingan/static/zhuanpan/images/niwodai/3.jpg"  width="100%"/></div>
		</div>
<input name="" type="image" src="http://rs.iadcn.com/pingan/static/zhuanpan/images/niwodai/4.jpg"  width="100%" onclick="checkForm()"/>	
</div>
	
	
<div class="jieshaobox">
	<div class="title2">
		<h3><img src="http://rs.iadcn.com/pingan/static/zhuanpan/images/niwodai/xiangqingtit.jpg" /></h3>
		<div class="miaoshu">
			<h4>产品描述</h4>
			<div>
				<p style="margin-bottom:5px;">在你我贷官网、手机APP、微信、wap等平台未成功投资过的新用户，购买“万元户”仅需预存1元，即可享受10000元新手体验金，产品到期前享受10000倍收益！</p>
				<p class="border">新手体验金仅限于作为计算收益之基数，不视为任何形式的现金及等价物，亦不得提现。</p>
			</div>
		</div>
	</div>
</div>

<div class="jieshaobox">
	<div class="title2">
		<div class="miaoshu">
			<h4>收益怎么计算</h4>
			<div>
				<p style="margin-bottom:5px;">万元户的10000元新手体验金，将在5天后给您收益：10000*9.9%*5/365=13.56元</p>
			</div>
		</div>
	</div>
</div>

<div class="jieshaobox">
	<div class="title2">
		<div class="miaoshu">
			<h4>如何申购</h4>
			<div>
				<p style="margin-bottom:5px;">你我贷未成功投资过的新用户，只要预存1元，即可获得10000元新手体验金，享5天年化9.9%的收益，购买当天起息。</p>
			</div>
		</div>
	</div>
</div>

<div class="jieshaobox">
	<div class="title2">
		<div class="miaoshu">
			<h4>怎么赎回</h4>
			<div>
				<p style="margin-bottom:5px;">到期后，1元本金加13.56元收益共14.56元发放至您的你我贷账户余额，可直接提现。
为保障您的资金安全，使用快捷支付的用户需遵循同卡进出规则，即购买与提现使用的银行卡必须一致。</p>
			</div>
		</div>
	</div>
</div>


<div class="jieshaobox">
	<div class="title2">
		<div class="miaoshu">
			<h4>适用人群</h4>
			<div>
				<p style="margin-bottom:5px;">仅限未成功投资过的新用户，仅可投资一次 。
</p>
			</div>
		</div>
	</div>
</div>

<div class="bottom" style="width:300px;">
			<p class="zhanghu">账户资金安全由银行和保险公司共同保障</p>
		</div>

</body>
</html>

