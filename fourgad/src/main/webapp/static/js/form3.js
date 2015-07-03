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

    if (!document.getElementById('whoisid').checked) {
        alert('请选择与谁同行');
            
        document.getElementById('whoisid').focus();
        return false;
    }else if (!document.getElementById('countryid').checked) {
        alert('请选择境内还是境外');
        
        document.getElementById('countryid').focus();
        return false;
    }else if (!document.getElementById('feeid').checked) {
        alert('请选择旅游预算');
        
        document.getElementById('feeid').focus();
        return false;
    }else if (!document.getElementById('isneedid').checked) {
        alert('请选择购买或免费领取一份交通意外保险');
        
        document.getElementById('isneedid').focus();
        return false;
    }else if (document.getElementById('txtName').value == '') {
        alert('姓名不能为空');
        document.getElementById('txtName').value = '';
        document.getElementById('txtName').style.borderColor = "red";
        document.getElementById('txtName').focus();
        return false;
    }
    else if (!isvalname(document.getElementById('txtName').value)) {
        alert("请输入你的中文姓名（2-6个汉字）!");
        document.getElementById('txtName').value = '';
        document.getElementById('txtName').style.borderColor = "red";
        document.getElementById('txtName').focus();
        return false;
    }
    else if (!isvalidname(document.getElementById('txtName').value)) {
        alert("您的名字不合法，请重新输入!");
        document.getElementById('txtName').value = '';
        document.getElementById('txtName').style.borderColor = "red";
        document.getElementById('txtName').focus();
        return false;
    }
    else if ($.trim($("#txtBirthday").val()) == "") {
        alert('请输入你的生日');
        $('#txtBirthday').css({ 'borderColor': 'red' });
        document.getElementById('txtBirthday').value = '';
        document.getElementById('txtBirthday').focus();
        return false;
    }
    else if (!IsValidBirthday($.trim(birthday))) {
        alert('您输入的生日格式不正确，请重新输入!');
        $('#txtBirthday').css({ 'borderColor': 'red' });
        document.getElementById('txtBirthday').value = '';
        document.getElementById('txtBirthday').focus();
        return false;
    }
    else if (jsGetAge($.trim($("#txtBirthday").val())) < 23) {
        alert('年龄须在23-50周岁之间！');
        $('#txtBirthday').css({ 'borderColor': 'red' });
        document.getElementById('txtBirthday').value = '';
        document.getElementById('txtBirthday').focus();
        return false;
    }
    else if (jsGetAge($.trim($("#txtBirthday").val())) > 50) {
        alert('年龄须在23-50周岁之间！');
        $('#txtBirthday').css({ 'borderColor': 'red' });
        document.getElementById('txtBirthday').value = '';
        document.getElementById('txtBirthday').focus();
        return false;
    }
    else if ($('input:radio[name=ddlSex]:checked').val() == undefined) {
        alert("请选择您的性别!");
        return false;
    }
    else if (!isNumber(document.getElementById('txtMobile').value)) {
        alert("您的电话号码格式不对！");
        document.getElementById('txtMobile').value = '';
        document.getElementById('txtMobile').style.borderColor = "red";
        document.getElementById('txtMobile').focus();
        return false;
    }
    else {
        if (o == "s") {
        	document.form1.submit();
            //document.getElementById("subGo").click();
        }
    }
    return true;
}
function isvalname(str) {
    reg = /^([\u4e00-\u9fa5]){2,6}$/;
    if (reg.test(str)) return true;
    else return false;

}
//---- 判断姓名合法 -----
var dx = ['赵','钱','孙','李','周','吴','郑','王','冯','陈','楮','卫','蒋','沈','韩','杨','朱','秦','尤','许',
  		'何','吕','施','张','孔','曹','严','华','金','魏','陶','姜','戚','谢','邹','喻','柏','水','窦','章',
  		'云','苏','潘','葛','奚','范','彭','郎','鲁','韦','昌','马','苗','凤','花','方','俞','任','袁','柳',
  		'酆','鲍','史','唐','费','廉','岑','薛','雷','贺','倪','汤','滕','殷','罗','毕','郝','邬','安','常',
  		'乐','于','时','傅','皮','卞','齐','康','伍','余','元','卜','顾','孟','平','黄','和','穆','萧',
  		'尹','姚','邵','湛','汪','祁','毛','禹','狄','米','贝','明','臧','计','伏','成','戴','谈','宋','茅',
  		'庞','熊','纪','舒','屈','项','祝','董','梁','杜','阮','蓝','闽','席','季','麻','强','贾','路',
  		'娄','危','江','童','颜','郭','梅','盛','林','刁','锺','徐','丘','骆',
  		'高','夏','蔡','田','樊','胡','凌','霍',
  		'虞','万','支','柯','昝','管','卢','莫',
  		'经','房','裘','缪','干','解','应','宗',
  		'丁','宣','贲','邓','郁','单','杭','洪',
  		'包','诸','左','石','崔','吉','钮','龚',
  		'程','嵇','邢','滑','裴','陆','荣','翁',
  		'荀','羊','於','惠','甄','麹','家','封',
  		'芮','羿','储','靳','汲','邴','糜','松',
  		'井','段','富','巫','乌','焦','巴','弓',
  		'牧','隗','山','谷','车','侯','宓','蓬',
  		'全','郗','班','仰','秋','仲','伊','宫',
  		'宁','仇','栾','暴','甘','斜','厉','戎',
  		'祖','武','符','刘','景','詹','束','龙',
  		'叶','幸','司','韶','郜','黎','蓟','薄',
  		'印','宿','白','怀','蒲','邰','从','鄂',
  		'索','咸','籍','赖','卓','蔺','屠','蒙',
  		'池','乔','阴','郁','胥','能','苍','双',
  		'闻','莘','党','翟','谭','贡','劳','逄',
  		'姬','申','扶','堵','冉','宰','郦','雍',
  		'郤','璩','桑','桂','濮','牛','寿','通',
  		'边','扈','燕','冀','郏','浦','尚','农',
  		'温','别','庄','晏','柴','瞿','阎','充',
  		'慕','连','茹','习','宦','艾','鱼','容',
  		'向','古','易','慎','戈','廖','庾','终',
  		'暨','居','衡','步','都','耿','满','弘',
  		'匡','国','文','寇','广','禄','阙','东',
  		'欧','殳','沃','利','蔚','越','夔','隆',
  		'师','巩','厍','聂','晁','勾','敖','融',
  		'冷','訾','辛','阚','那','简','饶','空',
  		'曾','毋','沙','乜','养','鞠','须','丰',
  		'巢','关','蒯','相','查','后','荆','红',
  		'游','竺','权','逑','盖','益','桓','公'];
  var fx = ['万俟','司马','上官','欧阳',
          '夏侯','诸葛','闻人','东方',
          '赫连','皇甫','尉迟','公羊',
          '澹台','公冶','宗政','濮阳',
          '淳于','单于','太叔','申屠',
          '公孙','仲孙','轩辕','令狐',
          '锺离','宇文','长孙','慕容',
          '鲜于','闾丘','司徒','司空',
          '丌官','司寇','仉督','子车',
          '颛孙','端木','巫马','公西',
          '漆雕','乐正','壤驷','公良',
          '拓拔','夹谷','宰父','谷梁',
          '晋楚','阎法','汝鄢','涂钦',
          '段干','百里','东郭','南门',
          '呼延','归海','羊舌','微生',
          '岳帅','缑亢','况后','有琴',
          '梁丘','左丘','东门','西门',
          '商牟','佘佴','伯赏','南宫',
          '墨哈','谯笪','年爱','阳佟'];
function isvalidname(str) {  
	var s = str.substring(0,1);
	if(isDx(s)){
		return true;
	}
	var s2 = str.substring(0,2);
	if(isFx(s2)){
		return true;
	}
    return false;    
}
function isDx(s){
	for(var i = 0 ; i < dx.length ; i++){
		if(s === dx[i]){
			return true;
		}
	}
	return false;
}
function isFx(s){
	for(var i = 0 ; i < fx.length ; i++){
		if(s === fx[i]){
			return true;
		}
	}
	return false;
}
//---- 判断姓名合法 -----
function jsGetAge(strBirthday) {
    var returnAge;
    var birthYear = strBirthday.substr(0, 4);
    var birthMonth = strBirthday.substr(4, 2);
    var birthDay = strBirthday.substr(6, 2);
    var d = new Date();
    var nowYear = d.getFullYear();

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
    var myreg = /^0?(13[0-9]|17[0-9]|15[012356789]|18[0123456789]|14[57])[0-9]{8}$/;
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
    	
    	 alert('年龄须在23-50周岁之间1111！');
        if (!checkForm('n')) return;
       
       
        return true;
    });
});
