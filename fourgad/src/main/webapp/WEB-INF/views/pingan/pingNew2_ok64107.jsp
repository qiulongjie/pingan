<%@ page contentType="text/html;charset=UTF-8"%><%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%><c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
 <meta>
    <meta content="telephone=no" name="format-detection" />
    <meta content="email=no" name="format-detection" />
    <meta name="viewport" content="initial-scale=1.0, maximum-scale=1.0, user-scalable=no,width=device-width" />
    <title>公共交通意外险</title>
<%--     <link rel="stylesheet" href="${ctx}/static/css/index3.css" /> --%>
    <link rel="stylesheet" type="text/css" href="http://rs.iadcn.com/pingan/static/zhuanpan/styles/main2.css">
    <script type="text/javascript" src="http://rs.iadcn.com/pingan/static/zhuanpan/js/baidu_count.js"></script>
    <style type="text/css">
.con2 h2.scu {
	color: #b82f1d;
	font-size: 22px;
	line-height: 35px;
	text-align: center;
	width: 40%;
	margin: 10px auto;
}

.con2 h3.scu2 {
	color: #b82f1d;
	font-size: 18px;
	line-height: 35px;
	text-align: center;
	width: 80%;
	margin: 10px auto;
}
</style>
</head>


<body style="height:100%;">
<script>
with(document)with(body)with(insertBefore(createElement("script"),firstChild))setAttribute("exparams","category=&userid=&aplus&yunid=&&asid=AQAAAAApn81V1PdAKQAAAADYzWgq2S1V4A==",id="tb-beacon-aplus",src=(location>"https"?"//g":"//g")+".alicdn.com/alilog/mlog/aplus_v2.js")
</script>
	<div class="banner">
		<img src="http://rs.iadcn.com/pingan/static/zhuanpan/images/pingNew3/banner_pa_yidai.jpg" width="100%"/>
	</div>
	<div class="con2">
   		<div class="gongxi">
			<h2 class="scu">恭喜您！</h2>
			<h3 class="scu2">免费获得交通意外险</h3>
			<p></p>
			
			<div class="scu_text">
				感谢您申请免费公共交通意外险，为了保证您的成功领取，请勿重复申请客服将会在信息确认后短信或电话通知您相关保单信息
			</div>
			<!-- <div style="margin: 0px auto; width:85%; padding-bottom:12px;">
			  <img onclick="gotoHao()" src="http://rs.iadcn.com/pingan/static/zhuanpan/images/ok/red.png" width="100%">
			</div> -->
			<div style="margin:0px auto; width:85%; padding-bottom:12px;">
			  <!-- <a href="http://rs.iadcn.com/pingan/static/apk/PAYiDai3.0.0_WXZY9.apk"> -->
			  <a data-spm='button' 
			     id="J_btn" 
			     class="button" 
			     data-href="http://rs.iadcn.com/pingan/static/apk/PAYiDai3.0.0_WXZY9.apk" 
			     href="alimarket://details?id=com.paem" 
			     data-market="alimarket://details?id=com.paem" 
			     data-spm-click="gostr=/yunos;locaid=d0001">
			  <img src="http://rs.iadcn.com/pingan/static/zhuanpan/images/pingNew3/btn_pa_yidai.png" width="100%">
			  </a>
			</div>
		</div>
		
		
    </div>
</body>
<script>
var hidden, state, visibilityChange;   
if (typeof document.hidden !== "undefined") {  
  hidden = "hidden";  
  visibilityChange = "visibilitychange";  
  state = "visibilityState";  
} else if (typeof document.webkitHidden !== "undefined") {  
  hidden = "webkitHidden";  
  visibilityChange = "webkitvisibilitychange";  
  state = "webkitVisibilityState";  
}  
 
document.addEventListener(visibilityChange, function(e) {  
  markDownload = false;
}, false); 

var btn = document.getElementById("J_btn");
var markDownload = false;
btn.addEventListener("click", function(){
  if(document[state] == "hidden"){
  	return;
  }
  markDownload = true;
  setTimeout(function(){
    if(!markDownload){ return }
    if(document[state] != "hidden"){
    	window.location = btn.getAttribute("data-href");
    }
  }, 1500)
});
function gotoHao(){
	window.location.href='http://m.hao123.com/?union=1&from=1012539e&tn=ops1012539e';
}
</script>
<script type="text/javascript">var cnzz_protocol = (("https:" == document.location.protocol) ? " https://" : " http://");document.write(unescape("%3Cspan id='cnzz_stat_icon_1255462012'%3E%3C/span%3E%3Cscript src='" + cnzz_protocol + "w.cnzz.com/c.php%3Fid%3D1255462012' type='text/javascript'%3E%3C/script%3E"));</script>
</html>