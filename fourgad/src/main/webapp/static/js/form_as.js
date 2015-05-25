(function (i, s, o, g, r, a, m) {
    i['GoogleAnalyticsObject'] = r; i[r] = i[r] || function () {
        (i[r].q = i[r].q || []).push(arguments)
    }, i[r].l = 1 * new Date(); a = s.createElement(o),
m = s.getElementsByTagName(o)[0]; a.async = 1; a.src = g; m.parentNode.insertBefore(a, m)

})(window, document, 'script', '//www.google-analytics.com/analytics.js', 'ga');

ga('create', 'UA-54132960-1', 'auto');
ga('send', 'pageview');

function checkForm(o) {
	
   var flag = true;

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

    if (!isNumber(document.getElementById('txtMobile').value)) {
        alertText("您的电话号码格式不对！");
        document.getElementById('txtMobile').value = '';
        document.getElementById('txtMobile').style.borderColor = "red";
        document.getElementById('txtMobile').focus();
		flag = false;
        return false;
    }
    
    if (document.getElementById('address').value == '') {
        alertText('地址不能为空');
        document.getElementById('address').value = '';
        document.getElementById('address').style.borderColor = "red";
        document.getElementById('address').focus();
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

$(function () {
    
    $('#queding_btn_xiaoshi').click(function(e){
    	$('#submitFormDiv').hide();
    });
});
