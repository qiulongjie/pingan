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
 <title>中国平安保险</title>
 <script type="text/javascript" src="http://rs.iadcn.com/pingan/static/js/jquery-1.7.1.min.js"></script>
 <script type="text/javascript" src="http://rs.iadcn.com/pingan/static/zhuanpan/js/baidu_count.js"></script>
 <style type="text/css">
 body{margin:0;padding:0; margin:0 auto;line-height:22px;font-family:"微软雅黑";background:#fff;-webkit-text-size-adjust:none;color:#666666; width:100%;font-size: 14px;}
form,ul,li,p,h1,h2,h3,h4,h5,h6,dl,dt,dd{margin:0;padding:0; font-weight:normal;}
input,select{font-size:12px; color:#666; padding:4 0 0 5px; line-height:18px;}
img{border:0;}
.left{float:left;}
.right{float:right;}
ul,li{list-style-type:none;}
.clear{ clear:both; height:0; overflow:hidden;}
html,body{ height:100%;}
a{ text-decoration:none;}
ul,li{ list-style-type:none; margin:0; padding:0;}
.con2{ width:100%; background:#ecf1f0; padding:10px 0;}
.gongxi{ width:80%; margin:10px auto 90px auto; background:#fff0db;border:5px solid #fff; border-radius:5px; padding-bottom:18px;}
.gongxi p{ text-align:center; font-size:18px; color:#b36f06;}
.con2 h2.scu{  padding-left:33px; color:#b82f1d; font-size:26px; line-height:35px; text-align:center; width:40%; margin:10px auto;}
.scu_text{ background:#fff; border-radius:3px; padding:10px; height:23px; width:85%; margin:10px auto; color:#b36f06; text-align:center;}
 </style>
</head>
<body style="max-width:380px; margin:0 auto;">
			<div><img src="http://rs.iadcn.com/pingan/static/zhuanpan/images/pingNew3/banner_kdlc.jpg" width="100%" /></div>

	<div class="con2">
   		<div class="gongxi">
			<h2 class="scu">感谢您！</h2>
			<p style="margin-bottom:10px;">参与此次活动</p>
			
			<p align="center">
			 <a id="downloadClick" href="http://m.koudailc.com/page/register?isShare=1&source_tag=zhangyue">
			   <img src="http://rs.iadcn.com/pingan/static/zhuanpan/images/pingNew3/btn.png" width="90%" />
			 </a>
			</p>
			
			<!-- <p align="center">
				<a id="downloadClick" href="http://rs.iadcn.com/pingan/static/apk/koudailicai_zhangyue.apk" >
				  <img src="http://rs.iadcn.com/pingan/static/zhuanpan/images/pingNew3/download_kdlc.png" width="90%" />
				</a>
			</p> -->
		
		</div>
		</div>

</body>
<script>
$(function() {
	var a = '${a}';
	$('#downloadClick').click(function(){
		$.ajax({
			type : "POST",
			cache : false,
			data:{'a':a,'clickType':1},
			url : "${ctx}/ping/addOKClickLog",
			dataType : "text",
			success : function(data) {
			},
			error : function() {
			}
		});
	});
})
</script>
</html>