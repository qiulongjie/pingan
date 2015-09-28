<%@ page language="java" contentType="image/jpeg"
	import="java.util.*,com.zzcm.fourgad.util.RandImgCreater"
	pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>code</title>
	<%
response.setHeader("Pragma","No-cache");
response.setHeader("Cache-Control","no-cache");
response.setDateHeader("Expires", 0);
out.clear();
out = pageContext.pushBody();
RandImgCreater rc = new RandImgCreater(response);
rc.setBgColor(255, 255, 255);
String rand = rc.createRandImage();
session.setAttribute("verifyCode",rand);
%>

  </head>
  
  <body>
  

  </body>
</html>
