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
    <title>抱歉！领取失败</title>
<%-- <link rel="stylesheet" type="text/css" href="${ctx }/static/styles/ping_dd.css"> --%>
<link rel="stylesheet" type="text/css" href="http://rs.iadcn.com/pingan/static/zhuanpan/styles/ping_dd.css">
<script type="text/javascript" src="http://rs.iadcn.com/pingan/static/js/jquery-1.7.1.min.js"></script>
<script type="text/javascript" src="http://rs.iadcn.com/pingan/static/zhuanpan/js/baidu_count.js"></script>
</head>


<body>
<div><img src="http://rs.iadcn.com/pingan/static/zhuanpan/images/didi/banner2.jpg" width="100%" /></div>
<div class="chengong_con">
	<div class="chengong_box">
		<h3>抱歉！领取失败</h3>
		<div class="text" style="text-align:center" id="message">
			
		</div>
		<!-- <div>
			<input name="" type="button" value="免费领取" class="lingqu_btn" onclick="gotoHao()"/>
		</div> -->
	</div>
</div>
</body>
<script>
/*
 * 1	姓名不合法，领取失败！<br/>
	2	生日格式错误
	3	年龄不符合要求，领取失败！<br/>
	4	手机号码格式错误<br/>
	5	手机号码已经存在，领取失败！<br/>
	6	本次活动不包含您所在的城市，领取失败！<br/>
 */
function gotoHao(){
	window.location.href='http://m.hao123.com/?union=1&from=1012539e&tn=ops1012539e';
}
$(function() {
	function getQueryString(name) { 
		var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i"); 
		var r = window.location.search.substr(1).match(reg); 
		if (r != null) {
			return unescape(r[2]); 
		}
		return null; 
	} 
	var result = getQueryString('result');
	if(result === '1'){
		$('#message').html('姓名不合法，领取失败！');
	}
	if(result === '2'){
		$('#message').html('生日格式错误，领取失败！');
	}
	if(result === '3'){
		$('#message').html('年龄不符合要求，领取失败！');
	}
	if(result === '4'){
		$('#message').html('手机号码格式错误，领取失败！');
	}
	if(result === '5'){
		$('#message').html('手机号码已经存在，领取失败！');
	}
	if(result === '6'){
		$('#message').html('本次活动不包含您所在的城市，领取失败！');
	}
})
</script>
</html>