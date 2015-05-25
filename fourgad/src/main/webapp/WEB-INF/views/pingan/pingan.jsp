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

    <style type="text/css">
        BODY {
            MARGIN: 0px;
        }

        H1 {
            MARGIN: 0px;
        }

        H2 {
            MARGIN: 0px;
        }

        H3 {
            MARGIN: 0px;
        }

        H4 {
            MARGIN: 0px;
        }

        H5 {
            MARGIN: 0px;
        }

        H6 {
            MARGIN: 0px;
        }

        P {
            MARGIN: 0px;
        }

        FORM {
            MARGIN: 0px;
        }

        main {
            MARGIN: 0px;
        }

        OL {
            PADDING-BOTTOM: 0px;
            MARGIN: 0px;
            PADDING-LEFT: 0px;
            PADDING-RIGHT: 0px;
            PADDING-TOP: 0px;
        }

        UL {
            PADDING-BOTTOM: 0px;
            MARGIN: 0px;
            PADDING-LEFT: 0px;
            PADDING-RIGHT: 0px;
            PADDING-TOP: 0px;
        }

        INPUT[type=checkbox] {
            PADDING-BOTTOM: 0px;
            MARGIN: 0px;
            PADDING-LEFT: 0px;
            PADDING-RIGHT: 0px;
            PADDING-TOP: 0px;
        }

        HTML {
            -webkit-tap-highlight-color: rgba(0, 0, 0, 0);
        }

        A {
            COLOR: #357ecb;
        }
    </style>
    <script type="text/javascript" src="${ctx}/static/js/jquery-1.7.1.min.js"></script>
    <script type="text/javascript" src="${ctx}/static/js/jquery.cookie.js"></script>
    <script type="text/javascript" src="${ctx}/static/js/form.js"></script>
    <link rel="stylesheet" href="${ctx}/static/css/index3.css" />
</head>
   
<body>
    <div class="content" runat="server" id="divFirst" visible="true">
        <div class="header">
                <img src="${ctx}/static/images/top.png" class="navLogThree">
        </div>
        <!--注册宜人贷-->
        <form runat="server" class="formBlock" action="${ctx}/pingan" name="form1" method="POST">
            <section class="sectionPart2">
                <p id="error_trips"></p>
                <ul>
                    <li class="formLi">
                        <span class="divLeft" data-role="content">姓名：</span>
                        <span class="divRight" data-role="content">
                            <input type="text" placeholder="请输入您真实的姓名" id="txtName" name="uname" />
                        </span>
                    </li>
                    <li class="formLi">
                        <span class="divLeft" data-role="content">生日：</span>
                        <span class="divRight" data-role="content">
                            <input type="text" placeholder="格式例：19820907" id="txtBirthday" name="birthday" />
                        </span>
                    </li>
                    <li class="formLi">
                        <div class="divLeft" data-role="content">性别</div>
                        <div class="divRight" data-role="content">
                            <input name="ddlSex" type="radio" value="男" style="display: inline;" /><div class="labtrips xx" style="margin-right: 9px; display: inline;">男</div>
                            <input name="ddlSex" type="radio" value="女" style="display: inline;" /><div class="labtrips xx" style="margin-right: 9px; display: inline;">女</div>
                        </div>
                    </li>
                    <li class="formLi">
                        <span class="divLeft" data-role="content">手机：</span>
                        <span class="divRight" data-role="content">
                            <input type="text" placeholder="请输入您真实的手机号码" id="txtMobile" name="phone" />
                        </span>
                    </li>
                    
                </ul>
                <br />
                <span data-value="buttonDown" class="buttons" onclick="checkForm('s')">提交</span>                
            </section>
            
        </form>
        <div class="header">
            <img src="${ctx}/static/images/foot.png" class="navLogThree">
        </div>
    </div>   
</body>
</html>