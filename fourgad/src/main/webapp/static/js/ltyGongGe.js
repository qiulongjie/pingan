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
function getQueryString(name) { 
	var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i"); 
	var r = window.location.search.substr(1).match(reg); 
	if (r != null) {
		return unescape(r[2]); 
	}
	return null; 
} 
var aa = getQueryString('a');
if(aa==='A8781097'){
	
}else{
	var _timer = setInterval("scroll(document.getElementById('scrollobj'))", 20); 
}

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
	var bannerLink;
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
		$.ajax({
			type : "POST",
			cache : false,
			async : false,
			data:{'a':a,'ltyType':'1'},
			url : web_path+"/lty/getLtyInitInfoNew",
			dataType : "json",
			success : function(data) {
				if(data != null && data != '' && data != undefined){
					
					$('#banner_link').attr('src',data.banner_link);
					
					$('#zp1_link').attr('src',data.zp1_link);
					$('#zp2_link').attr('src',data.zp2_link);
					$('#zp3_link').attr('src',data.zp3_link);
					$('#zp4_link').attr('src',data.zp4_link);
					$('#zp5_link').attr('src',data.zp5_link);
					$('#zp6_link').attr('src',data.zp6_link);
					$('#zp7_link').attr('src',data.zp7_link);
					$('#zp8_link').attr('src',data.zp8_link);
					
					$('#jiangping1_link').attr('src',data.jiangping1_link);
					$('#jiangping2_link').attr('src',data.jiangping2_link);
					$('#jiangping3_link').attr('src',data.jiangping3_link);
					$('#jiangping4_link').attr('src',data.jiangping4_link);
					$('#jiangping5_link').attr('src',data.jiangping5_link);
					$('#jiangping6_link').attr('src',data.jiangping6_link);
					$('#jiangping7_link').attr('src',data.jiangping7_link);
					$('#jiangping8_link').attr('src',data.jiangping8_link);
					
					$('#scrollobj').html(data.top_croll_text);
					$('#f_alert_txt').html(data.f_alert_txt);
					
					bannerLink = data.banner_link;
					
				}else{
				}
				initing = false;
			},
			error : function() {
			}
		});
	}
	
var lottery={
		index:-1,	//当前转动到哪个位置，起点位置
		count:0,	//总共有多少个位置
		timer:0,	//setTimeout的ID，用clearTimeout清除
		speed:100,	//初始转动速度
		times:0,	//转动次数
		cycle:50,	//转动基本次数：即至少需要转动多少次再进入抽奖环节
		prize:1,	//中奖位置
		init:function(id){
			if ($("#"+id).find(".lottery-unit").length>0) {
				$lottery = $("#"+id);
				$units = $lottery.find(".lottery-unit");
				this.obj = $lottery;
				this.count = $units.length;
				$lottery.find(".lottery-unit-"+this.index).addClass("black");
			};
		},
		roll:function(){
			var index = this.index;
			var count = this.count;
			var lottery = this.obj;
			$(lottery).find(".lottery-unit-"+index).removeClass("black");
			index += 1;
			if (index>count-1) {
				index = 0;
			};
			$(lottery).find(".lottery-unit-"+index).addClass("black");
			this.index=index;
			return false;
		},
		stop:function(index){
			this.prize=index;
			return false;
		}
	};

	function roll(){
		lottery.times += 1;
		lottery.roll();
		if (lottery.times > lottery.cycle+10 && lottery.prize==lottery.index) {
			lotteryFinish();
			clearTimeout(lottery.timer);
			lottery.prize=-1;
			lottery.times=0;
		}else{
			if (lottery.times<lottery.cycle) {
				lottery.speed -= 10;
			}else if(lottery.times==lottery.cycle) {
				//var index = Math.random()*(lottery.count)|0;
				//lottery.prize = index;		
			}else{
				if (lottery.times > lottery.cycle+10 && ((lottery.prize==0 && lottery.index==7) || lottery.prize==lottery.index+1)) {
					lottery.speed += 110;
				}else{
					lottery.speed += 20;
				}
			}
			if (lottery.speed<40) {
				lottery.speed=40;
			};
			//console.log(lottery.times+'^^^^^^'+lottery.speed+'^^^^^^^'+lottery.prize);
			lottery.timer = setTimeout(roll,lottery.speed);
		}
		return false;
	}

	var click=false;
	
	lottery.init('lottery');
	$("#lottery_start").click(function(){
		if(initing){
			return false;
		}else{
			if (click) {
				return false;
			}else{
				if($.cookie("cookie"+a)){
					showSorry();
					return false;
				}else{
					if ($.cookie("ltyChoujiang"+a)) {
						showCompleteInfo();
						return false;
					}
				}
				click=true;
				getLottery();
				return false;
			}
		}
	});
	
	var angleTemp;
	function getLottery(){
		$.ajax({
			type : "POST",
			cache : false,
			async : false,
			data:{'a':a},
			url : web_path+"/lty/getLotteryNew",
			dataType : "json",
			success : function(data) {
				if(data.error != '' && data.error != undefined){
				}else{
					/*if(data.is_outlink === 1 || data.is_outlink === '1'){
						$('#save_1').attr('href',data.link_url);
					}else{
						$('#save_1').attr('href',web_path+data.link_url+'?a='+a);
					}*/
					$('#save_1').attr('href',web_path+data.link_url+'?a='+a);
					angleTemp=data.lty_prize;
					
				    $.session.set('ok_title', data.ok_title);
					$.session.set('ok_info', data.ok_info);
					$.session.set('banner_link', bannerLink);
					$.session.set('lty_angle', data.lty_prize);
					
					lottery.prize=parseInt(data.lty_prize);
					lottery.speed=100;
					roll();
				}
			},
			error : function() {
			}
		});
	}// end getLottery
	
	function lotteryFinish(){
		if(!parseInt(p)){
		  p='7';
		}
		var url = $('#save_1').attr('href');
		$.cookie("ltyChoujiang"+a, url, {
			expires : parseInt(p)
		});
		setTimeout(showPrize,500);
	}
	
	function showPrize(){
		if(angleTemp != 0){
			$('#prizeShow2').show();
		}else{
			$('#prizeShow').show();
		}
		click=false;
		setTimeout(gotoPrize,1000);
	}
	function gotoPrize(){
		var url = $('#save_1').attr('href');
		window.location.href=url;
	}
	function showSorry(){
		$('#denyShow').show();
		click=false;
	}
	function showCompleteInfo(){
		var url = $.cookie("ltyChoujiang"+a);
		$('#completeInfoShow_btn').attr('href',url);
		$('#completeInfoShow').show();
		click=false;
	}
	function showCompleteInfo1(){
		$('#completeInfoShow1').show();
		click=false;
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
        return (localhostPaht + projectName);//本地测试改为这个
        // return localhostPaht;
    }
})