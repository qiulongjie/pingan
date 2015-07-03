<%@ page contentType="text/html;charset=UTF-8"%><%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%><c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
 <meta>
    <meta content="telephone=no" name="format-detection" />
    <meta content="email=no" name="format-detection" />
    <meta name="viewport" content="initial-scale=1.0, maximum-scale=1.0, user-scalable=no,width=device-width" />
    <title>中国平安保险</title>
<%--     <link rel="stylesheet" href="${ctx}/static/css/index3.css" /> --%>
    <link rel="stylesheet" type="text/css" href="http://rs.iadcn.com/pingan/static/zhuanpan/styles/main2.css">
    <script type="text/javascript" src="http://rs.iadcn.com/pingan/static/zhuanpan/js/baidu_count.js"></script>
</head>


<body style="height:100%;">
	<div class="banner">
		<img src="http://rs.iadcn.com/pingan/static/zhuanpan/images/lottery_new/banner.jpg" width="100%"/>
	</div>
	
  <div class="con2">
   		<div class="gongxi">
			<h2 class="scu">恭喜您！</h2>
			<p>获得平安免费意外险</p>
			
			<div class="scu_text">
				感谢您申请中国平安免费意外险，为了保证您的成功领取，请勿重复申请，如需更改赠险信息，请在客服电话确认时按提示操作
			</div>
			<div style="margin: 0px auto; width:85%; padding-bottom:12px;">
			  <img onclick="gotoHao()" src="http://rs.iadcn.com/pingan/static/zhuanpan/images/ok/red.png" width="100%">
			</div>
		</div>
		
		
    </div>
</body>
<script>
function gotoHao(){
	window.location.href='http://m.hao123.com/?union=1&from=1012539e&tn=ops1012539e';
}
</script>
</html>