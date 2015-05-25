(function (i, s, o, g, r, a, m) {
    i['GoogleAnalyticsObject'] = r; i[r] = i[r] || function () {
        (i[r].q = i[r].q || []).push(arguments)
    }, i[r].l = 1 * new Date(); a = s.createElement(o),
m = s.getElementsByTagName(o)[0]; a.async = 1; a.src = g; m.parentNode.insertBefore(a, m)

})(window, document, 'script', '//www.google-analytics.com/analytics.js', 'ga');

ga('create', 'UA-54132960-1', 'auto');
ga('send', 'pageview');

function checkForm(o) {
    var birthday = $("#txtBirthday").val();
    var reg = /^(19|20)\d{2}(1[0-2]|0?[1-9])(0?[1-9]|[1-2][0-9]|3[0-1])$/;       

    flag = true;


   if (document.getElementById('txtName').value == '') {
        alertText('姓名不能为空');
        document.getElementById('txtName').value = '';
        document.getElementById('txtName').style.borderColor = "red";
        document.getElementById('txtName').focus();
		flag = false;
        return false;
    }
    if (!isvalname(document.getElementById('txtName').value)) {
        alertText("请输入你的中文姓名（2-6个汉字）!");
        document.getElementById('txtName').value = '';
        document.getElementById('txtName').style.borderColor = "red";
        document.getElementById('txtName').focus();
		flag = false;
        return false;
    }
    if (!isvalidname(document.getElementById('txtName').value)) {
        alertText("您的名字不合法，请重新输入!");
        document.getElementById('txtName').value = '';
        document.getElementById('txtName').style.borderColor = "red";
        document.getElementById('txtName').focus();
		flag = false;
        return false;
    }
    if ($.trim($("#txtBirthday").val()) == "") {
        alertText('请输入你的生日');
        $('#txtBirthday').css({ 'borderColor': 'red' });
        document.getElementById('txtBirthday').value = '';
        document.getElementById('txtBirthday').focus();
		flag = false;
        return false;
    }
    if (!IsValidBirthday($.trim(birthday))) {
        alertText('您输入的生日格式不正确，请重新输入!');
        $('#txtBirthday').css({ 'borderColor': 'red' });
        document.getElementById('txtBirthday').value = '';
        document.getElementById('txtBirthday').focus();
		flag = false;
        return false;
    }
    if (jsGetAge($.trim($("#txtBirthday").val())) < 23) {
        alertText('年龄须在23-50周岁之间！');
        $('#txtBirthday').css({ 'borderColor': 'red' });
        document.getElementById('txtBirthday').value = '';
        document.getElementById('txtBirthday').focus();
		flag = false;
        return false;
    }
    if (jsGetAge($.trim($("#txtBirthday").val())) > 50) {
        alertText('年龄须在23-50周岁之间！');
        $('#txtBirthday').css({ 'borderColor': 'red' });
        document.getElementById('txtBirthday').value = '';
        document.getElementById('txtBirthday').focus();
		flag = false;
        return false;
    }

    if (!isNumber(document.getElementById('txtMobile').value)) {
        alertText("您的电话号码格式不对！");
        document.getElementById('txtMobile').value = '';
        document.getElementById('txtMobile').style.borderColor = "red";
        document.getElementById('txtMobile').focus();
		flag = false;
        return false;
    }
    if (flag) 
		{
        	document.form1.submit();
        }
    return true;
}

function alertText(txt){
	//$('#alert_text').text(txt);
	//$('#submitFormDiv').show();
	alert(txt);
}


function isvalname(str) {
    reg = /^([\u4e00-\u9fa5]){2,6}$/;
    if (reg.test(str)) return true;
    else return false;

}
function isvalidname(str) {  
        return true;    
}
function jsGetAge(strBirthday) {
    var returnAge;
    var birthYear = strBirthday.substr(0, 4);
    var birthMonth = strBirthday.substr(4, 2);
    var birthDay = strBirthday.substr(6, 2);
    d = new Date();
    var nowYear = 2014;

    var nowMonth = d.getMonth() + 1;

    var nowDay = d.getDate();

    if (nowYear == birthYear) {
        returnAge = 0; //同年 则为0岁
    }
    else {
        var ageDiff = nowYear - birthYear; //年之差
        if (ageDiff > 0) {
            if (nowMonth == birthMonth) {
                var dayDiff = nowDay - birthDay; //日之差
                if (dayDiff < 0) {
                    returnAge = ageDiff - 1;
                }
                else {
                    returnAge = ageDiff;
                }
            }
            else {
                var monthDiff = nowMonth - birthMonth; //月之差
                if (monthDiff < 0) {
                    returnAge = ageDiff - 1;
                }
                else {
                    returnAge = ageDiff;
                }
            }
        }
        else {
            returnAge = -1; //返回-1 表示出生日期输入错误 晚于今天
        }
    }

    return returnAge; //返回周岁年龄

}
function isNumber(String) {
    var Letters = "1234567890-";
    var i;
    var c;
    if (String.length != 11) {
        return false;
    }
    if (String.charAt(0) == '-')
        return false;
    if (String.charAt(String.length - 1) == '-')
        return false;
    for (i = 0; i < String.length; i++) {
        c = String.charAt(i);
        if (Letters.indexOf(c) < 0)
            return false;
    }
    var myreg = /^0?(13[0-9]|15[012356789]|17[012356789]|18[0123456789]|14[57])[0-9]{8}$/;
    if (myreg.test(String)) return true;
    else return false;

    return true;

}
function IsValidBirthday(birthday) {
    var reg = /^(?:(?!0000)[0-9]{4}(?:(?:0[1-9]|1[0-2])(?:0[1-9]|1[0-9]|2[0-8])|(?:0[13-9]|1[0-2])(?:29|30)|(?:0[13578]|1[02])31)|(?:[0-9]{2}(?:0[48]|[2468][048]|[13579][26])|(?:0[48]|[2468][048]|[13579][26])00)0229)$/;
    if (reg.test(birthday)) {
        return true;
    }
    else {
        return false;
    }
}

$(function () {
    $("#spanVerfiryCode").click(function () {
    	
    	 alertText('年龄须在23-50周岁之间1111！');
        if (!checkForm('n')) return;
       
       
        return true;
    });
    
    $('#queding_btn_xiaoshi').click(function(e){
    	$('#submitFormDiv').hide();
    });
});
