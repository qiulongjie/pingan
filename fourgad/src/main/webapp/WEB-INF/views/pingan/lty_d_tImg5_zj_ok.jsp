<%@ page contentType="text/html;charset=UTF-8"%><%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%><c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
 <meta>
    <meta content="telephone=no" name="format-detection" />
    <meta content="email=no" name="format-detection" />
    <meta name="viewport" content="initial-scale=1.0, maximum-scale=1.0, user-scalable=no,width=device-width" />
    <title>领奖成功</title>
<%--     <link rel="stylesheet" type="text/css" href="${ctx }/static/styles/main2.css"> --%>
    <link rel="stylesheet" type="text/css" href="http://rs.iadcn.com/pingan/static/zhuanpan/styles/main2.css">
    <link rel="stylesheet" type="text/css" href="http://rs.iadcn.com/pingan/static/zhuanpan/styles/main_lty_d5_ok.css">
    <script type="text/javascript" src="http://rs.iadcn.com/pingan/static/zhuanpan/js/jquery-1.7.1.min.js"></script>
    <script type="text/javascript" src="http://rs.iadcn.com/pingan/static/zhuanpan/js/jquerysession.js"></script>
    <script type="text/javascript" src="http://rs.iadcn.com/pingan/static/zhuanpan/js/baidu_count.js"></script>
</head>

<body style="height:100%;">

<div class="popupBoxBg slidePopupBox" style="display: none;">
        <div class="popupBoxShow">
            <p id="ok_title">
             
            </p>
        </div>
        <div class="fade"></div>
    </div>
    
    
	<div class="banner">
		<img src="http://rs.iadcn.com/pingan/static/zhuanpan/images/lty_d5/banner_aqy.jpg" width="100%"/>
	</div>
	
  <div class="con2">
   		<div class="gongxi2">
			<h2 class="scu2">恭喜您！</h2>
<!--			<p>获得平安免费意外险</p>
-->			

	<div class="jihuo">
		<p>激活码：<strong><apan id="id_jihuoma"></sapan></strong></p>
		<p>有效期：<strong><apan id="id_youxiaoqi">2015-12-31</sapan></strong></p>
		<p style="font-size:14px">(建议截图保存，激活码将不再另行通知)</p>
	</div>

			<div class="scu_text2">
				
温馨提示：<br/>
1.  请在有效期内前往爱奇艺官方网站激活，否则将视为无效。<br/>
2.  此激活码不能兑换现金； 请用户妥善保管激活码，如发生盗用，泄漏，遗失等问题不予调换<br/>
3.  此激活码最终解释权归爱奇艺公司所有，若用户领取会员体验出现失败请致电爱奇艺客服热线:400-800-7171
        
			</div>
		
		</div>
	  


    </div>
</body>

<script>
$(function() {
	
	var jihuoma = $.session.get('jihuoma');
	var ok_title = $.session.get('ok_title');
	if(jihuoma === 'x'){
		jihuoma = '抱歉！激活码已经被领取完了！';
		$('#id_youxiaoqi').html('');
	}else{
		$('.popupBoxBg').show();
	}
	$('#id_jihuoma').html(jihuoma);
	$('#ok_title').html(ok_title);
	
	setTimeout('$(".slidePopupBox").hide()', 3000);
})
</script>
</html>