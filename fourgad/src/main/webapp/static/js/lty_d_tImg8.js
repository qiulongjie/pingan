var winWidth = 0;
var scrollobjWidth = 350;
function findDimensions() //函数：获取尺寸 
{ 
//获取窗口宽度 
if (window.innerWidth){
	winWidth = window.innerWidth; 
} else if ((document.body) && (document.body.clientWidth)) {
	winWidth = document.body.clientWidth; 
}
//通过深入Document内部对body进行检测，获取窗口大小 
if (document.documentElement && document.documentElement.clientHeight && document.documentElement.clientWidth) 
{ 
winWidth = document.documentElement.clientWidth; 
} 
if(winWidth < 400){
	scrollobjWidth = winWidth-50;
}else{
	scrollobjWidth = 350;
}
$('#scrollobj').width(scrollobjWidth);
} 
findDimensions();
//调用函数，获取数值 
window.onresize=findDimensions; 

function scroll(obj) { 

	/*往左*/ 
	var tmp = (obj.scrollLeft)++; 
	//当滚动条到达右边顶端时 
	if (obj.scrollLeft == tmp) { 
		obj.innerHTML += obj.innerHTML; 
	} 
	//当滚动条滚动了初始内容的宽度时滚动条回到最左端 
	if (obj.scrollLeft >= obj.firstChild.offsetWidth) { 
		obj.scrollLeft = 0; 
	} 
} 

var _timer = setInterval("scroll(document.getElementById('scrollobj'))", 20); 

$(function() {
	function getQueryString(name) { 
		var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i"); 
		var r = window.location.search.substr(1).match(reg); 
		if (r != null) {
			return unescape(r[2]); 
		}
		return null; 
	} 
	var a = getQueryString('a');
	var z = getQueryString('p');
	var p = getQueryString('p2');
	var c = getQueryString('code');
	var web_path = getRootPath_web();
	var initing = true;
	var bannerLink = '';
	var ping_title = '';
	
	init();
	
	function init(){
		if($.cookie("cookie"+a)){
			showSorry();
		}else{
			if ($.cookie("ltyChoujiang"+a)) {
				showCompleteInfo();
			}
		}
		$.ajax({
			type : "POST",
			cache : false,
			async : false,
			data:{'a':a,'ltyType':'8'},
			url : web_path+"/lty/getLtyIniyInfo",
			dataType : "json",
			success : function(data) {
				if(data != null && data != '' && data != undefined){
					if(data.channel != 'xx'){
						$('#zhuanpanImg').attr('src',data.zhuanpan_link);
						
						$('#jiangping1_link').attr('src',data.jiangping1_link);
						$('#jiangping2_link').attr('src',data.jiangping2_link);
						$('#jiangping3_link').attr('src',data.jiangping3_link);
						$('#jiangping4_link').attr('src',data.jiangping4_link);
						$('#jiangping5_link').attr('src',data.jiangping5_link);
						
						$('#scrollobj').html(data.top_croll_text);
						
						$('#top_img_id').attr('src',data.top_img);
						
						bannerLink = data.banner_link;
						ping_title = data.ping_title;
					}
					
				}else{
				}
				initing = false;
			},
			error : function() {
			}
		});
	}

	var running = false;
	var rotateFunc = function(angle) {
		$('#zhuanpanImg').stopRotate();
		$("#zhuanpanImg").rotate({
			angle : 0,
			duration : 5000,
			animateTo : angle + 1440,
			callback : function() {
				if(!parseInt(p)){
					p='7';
				}
				$.cookie("ltyChoujiang"+a, "true", {
					expires : parseInt(p)
				});
				setTimeout(showPrize,500);
			}
		});
	};

	$("#lotteryBtn").rotate({
		bind : {
			click : function() {
				if (initing) {
					return;
				}
				if (running) {
					return;
				}
				if($.cookie("cookie"+a)){
					showSorry();
					return;
				}else{
					if ($.cookie("ltyChoujiang"+a)) {
						showCompleteInfo();
						return;
					}
				}
				getLottery();
			}
		}

	});

	function getLottery() {
		running = true;
		$.ajax({
			type : "POST",
			cache : false,
			async : false,
			data:{'a':a,'z':z,'p':p,'c':c},
			url : web_path+"/ltyAs/getLottery",
			dataType : "json",
			success : function(data) {
				if(data.error != '' && data.error != undefined){
					alert(data.error);
				}else{
					
					//$('.tanchuang_input2').html(data.title_info);
					if(data.channel != 'xx'){
						$('#save_1').attr('href',web_path+data.link_url+'?a='+a);
					}
				   /*  $.session.set('ok_title', data.ok_title);
					$.session.set('ok_info', data.ok_info);
					$.session.set('lty_angle', data.lty_angle);
					
					$.session.set('banner_link', bannerLink);
					$.session.set('ping_title', ping_title); */
					rotateFunc(parseFloat(data.lty_angle));
					
					$('#lotteryBtn').attr('src','http://192.168.0.53:8989/fourgad/static/images/lty_d8/qidong_btn3.png');
				}
			},
			error : function() {
				running = false;
			}
		});
	}
	function showPrize(){
		$('#prizeShow').show();
		running = false;
		setTimeout(gotoPrize,1000);
	}
	function gotoPrize(){
		var url = $('#save_1').attr('href');
		window.location.href=url;
	}
	function showSorry(){
		$('#denyShow').show();
	}
	function showCompleteInfo(){
		$('#completeInfoShow').show();
	}
	function showCompleteInfo1(){
		$('#completeInfoShow1').show();
	}
	
	$('#denyShow_btn').click(function(){
		$('#denyShow').hide();
	});
	
	//获取当前根目录
	function getRootPath_web() {
		//var p = 'http://192.168.0.53:8989/fourgad';//本地测试
		var p = 'http://m.iadcn.com';
		return p;
        /*var curWwwPath = window.document.location.href;
        var pathName = window.document.location.pathname;
        var pos = curWwwPath.indexOf(pathName);
        var localhostPaht = curWwwPath.substring(0, pos);
        var projectName = pathName.substring(0, pathName.substr(1).indexOf('/') + 1);
        return (localhostPaht + projectName);*///本地测试改为这个
        //return localhostPaht;
    }
	
})