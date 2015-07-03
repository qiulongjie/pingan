var isCheck = 0;
function checkclick(){
	if(isCheck === 1){
		document.getElementById('checkImg').src='http://rs.iadcn.com/pingan/static/zhuanpan/images/niwodai/moren.jpg';
		isCheck = 0;
	}else{
		document.getElementById('checkImg').src='http://rs.iadcn.com/pingan/static/zhuanpan/images/niwodai/xuanzhong.jpg';
		isCheck = 1;
	}
}
function checkForm() {

   var flag = true;

   if (document.getElementById('txtMobile').value === '') {
       alertText("请输入您的手机号！");
       document.getElementById('txtMobile').value = '';
       document.getElementById('txtMobile').style.borderColor = "red";
       document.getElementById('txtMobile').focus();
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
   if (document.getElementById('txtName').value == '') {
        alertText('请输入真实姓名！');
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

    if (flag) 
	{
    	$('.lingqu_btn').attr('disabled','');
    	var a = getQueryString('a');
    	var uname = document.getElementById('txtName').value;
    	var phone = document.getElementById('txtMobile').value;
    	var channel;
    	$.ajax({
			type : "POST",
			cache : false,
			async : false,
			data:{'a':a,'uname':uname,'phone':phone,'isCheck':isCheck},
			url : "http://m.iadcn.com/nwd/niwodai",
			dataType : "text",
			success : function(data) {
				channel = data;
			},
			error : function() {
			}
		});
    	if(!channel){
    		channel = '5ac59ad35d78d718307a2a28e7c0ed90';
    	}
    	window.location.href='http://m.niwodai.com/index.do?method=zhangyueLogin&mobile='+phone+'&userName='+encodeURI(encodeURI(uname))+'&extsid='+channel+'&source_id='
    }
    return true;
}
function getQueryString(name) { 
	var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i"); 
	var r = window.location.search.substr(1).match(reg); 
	if (r != null) {
		return unescape(r[2]); 
	}
	return null; 
} 
function alertText(txt){
	alert(txt);
}
function isvalname(str) {
    var reg = /^([\u4e00-\u9fa5]){2,6}$/;
    if (reg.test(str)) return true;
    else return false;

}
// ---- 判断姓名合法 start -----
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
	/*var s = str.substring(0,1);
	if(isDx(s)){
		return true;
	}
	var s2 = str.substring(0,2);
	if(isFx(s2)){
		return true;
	}
    return false;*/
	return true;   
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
//---- 判断姓名合法  END -----

//---- 判断手机号码是否正确 start
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
