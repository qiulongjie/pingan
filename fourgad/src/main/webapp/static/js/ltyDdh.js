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
	scrollobjWidth = winWidth-60;
}else{
	scrollobjWidth = 330;
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
	var p = getQueryString('p');
	var p2 = getQueryString('p2');
	var c = getQueryString('code');
	var initing = true;
	var bannerLink = '';
	var ping_title = '';
	var web_path = getRootPath_web();
	init();
	
	function init(){
		if($.cookie("cookie"+a)){
			showSorry();
		}else{
			if ($.cookie("ltyChoujiang"+a)) {
				showCompleteInfo();
			}
		}
		initing = false;
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
				showPrize();
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
			data:{'a':a},
			url : web_path+"/pingLottery/getLottery2",
			dataType : "text",
			success : function(data) {
				rotateFunc(parseFloat(data)-18);
				$('#lotteryBtn').attr('src','http://rs.iadcn.com/pingan/static/zhuanpan/images/daduhui/59.jpg');
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
		running = false;
	}
	function showCompleteInfo(){
		$('#completeInfoShow').show();
		running = false;
	}
	
	$('#denyShow_btn').click(function(){
		$('#denyShow').hide();
	});
	
	//获取当前根目录
	function getRootPath_web() {
        var curWwwPath = window.document.location.href;
        var pathName = window.document.location.pathname;
        var pos = curWwwPath.indexOf(pathName);
        var localhostPaht = curWwwPath.substring(0, pos);
        var projectName = pathName.substring(0, pathName.substr(1).indexOf('/') + 1);
        //return (localhostPaht + projectName);//本地测试改为这个
        return localhostPaht;
    }
})