<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0,user-scalable=no">
<title>25万元意外险免费送，先到先得！</title>
<meta name="keywords" content="Array" >
<meta name="description" content="">

<%-- <link rel="stylesheet" type="text/css" href="${ctx }/static/styles/ping_ios2.css"> --%>
<link rel="stylesheet" type="text/css" href="http://rs.iadcn.com/pingan/static/zhuanpan/styles/ping_ios2.css">
<script type="text/javascript" src="http://rs.iadcn.com/pingan/static/js/jquery-1.7.1.min.js"></script>
<script type="text/javascript" src="http://rs.iadcn.com/pingan/static/js/jquery.cookie.js"></script>
<%-- <script type="text/javascript" src="${ctx}/static/js/form_code_cookie.js"></script> --%>
<script type="text/javascript" src="http://rs.iadcn.com/pingan/static/js/form_code_cookie.js"></script>
<script type="text/javascript" src="http://rs.iadcn.com/pingan/static/zhuanpan/js/baidu_count.js"></script>

</head>

<body style="max-width:400px; margin:0 auto;">
<div style="height:20px;position:fixed;top:0;width:100%;z-index:9999999;display:block;background:#ffffff;"></div>
	<div style="margin-top: 20px;">
	<div><img src="http://rs.iadcn.com/pingan/static/zhuanpan/images/ping_ios/rebanner.jpg" width="100%" /> </div>
    <div class="inputcon">
    	<form action="${ctx}/ping/submitGet" name="form1" method="get" onsubmit="return checkForm('a');">
		   <input id="code" name="code" type="hidden" value="${code }" />
		   <input name="a" type="hidden" value="${a }" />
		   <input name="failure_path" type="hidden" value="ping_ios" />
		   <input name="ok_p" type="hidden" value="ping_ios_ok" />
        	<div class="inputbox">
                  <div class="inp"><input value="${uname }" id="txtName" name="uname" type="text" class="input1" placeholder="请填写您的真实姓名"/></div>
          		  <div class="inpbg"><img src="http://rs.iadcn.com/pingan/static/zhuanpan/images/ping_ios/input1.jpg" width="100%" /></div>
            </div>
            
            <div class="inputbox">
                  <div class="inp"><input value="${birthday }" id="txtBirthday" name="birthday" type="text" class="input1" placeholder="例如：19890308"/></div>
          		  <div class="inpbg"><img src="http://rs.iadcn.com/pingan/static/zhuanpan/images/ping_ios/input3.jpg" width="100%" /></div>
            </div>
            
             <div class="inputbox">
                  <div class="inp"><input value="${phone }" id="txtMobile" name="phone" type="text" class="input1" placeholder="请填写您的手机号码"/></div>
          		  <div class="inpbg"><img src="http://rs.iadcn.com/pingan/static/zhuanpan/images/ping_ios/input2.jpg" width="100%" /></div>
            </div>
            
             <div class="inputbox">
                  <div class="inp"><input id="vcode" name="vryCode" type="text" class="input1" style="width:42%;" placeholder="在此输入"/></div>
               <div class="yanzheng"><img id="code_img" src="${ctx}/ping/go/img" width="100%" height="100%"/></div>
          		  <div class="inpbg"><img src="http://rs.iadcn.com/pingan/static/zhuanpan/images/ping_ios/input4.jpg" width="100%" /></div>
            </div>
            
      <!-- <div class="yuedu">
                <img name="checkImg" id="checkImg" src="http://rs.iadcn.com/pingan/static/zhuanpan/images/ping_ios/fuxuan.jpg" width="8%" />
<label for="checkImg">我已阅读相关<a href="#">保险说明</a>,同意免费申请、领取交通意外险</label>
      </div> -->
            
            <div><input class="lingqu_btn" name="" type="image" src="http://rs.iadcn.com/pingan/static/zhuanpan/images/ping_ios/lingqu.jpg"  width="100%" /></div>
       </form> 
    </div>
    
    <div><img src="http://rs.iadcn.com/pingan/static/zhuanpan/images/ping_ios/bottompic.jpg" width="100%" /></div>
    <div><img src="http://rs.iadcn.com/pingan/static/zhuanpan/images/ping_ios/friendlink.jpg" width="100%" /></div>
  </div> 
</body>

<script>

$(function() {
	
	var vry = "${vry}";
	var a = "${a}";
	if(vry === 'failure_vry'){
		alert("验证码错误！");
		$('#vcode').css({ 'borderColor': 'red' });
        document.getElementById('vcode').value = '';
        $.cookie("cookie"+a,null);
	} 
	
	 $("#vcode").focus(function(){
		 $('#code_img').attr('src','${ctx}/ping/go/img?'+ Math.floor(Math.random() * 100)).fadeIn();
	 });

	$('#code_img').click(function() {//生成验证码  
		$(this).hide().attr('src','${ctx}/ping/go/img?'+ Math.floor(Math.random() * 100)).fadeIn();
	})
	/* var code = "${code}";
	if(code === '0'){
		$("#checkImg").attr('src','http://rs.iadcn.com/pingan/static/zhuanpan/images/ping_ios/fuxuan-nomal.jpg');
	}else{
		$("#checkImg").attr('src','http://rs.iadcn.com/pingan/static/zhuanpan/images/ping_ios/fuxuan.jpg');
		code = '1';
	}
	$("#code").val(code);
	$('.yuedu').click(function() {
		if(code === '1'){
			$("#checkImg").attr('src','http://rs.iadcn.com/pingan/static/zhuanpan/images/ping_ios/fuxuan-nomal.jpg');
			code='0';
		}else if(code === '0'){
			$("#checkImg").attr('src','http://rs.iadcn.com/pingan/static/zhuanpan/images/ping_ios/fuxuan.jpg');
			code = '1';
		}
		$("#code").val(code);
	}) */
})
</script>
</html>

