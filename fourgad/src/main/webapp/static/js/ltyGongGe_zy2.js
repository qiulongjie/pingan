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
	var bannerLink;
	var web_path = getRootPath_web();
	var ltyPrize = 3;//四等奖
	var duihuan_code = '';
	var isNoYuebing = false;
	init();
	
	function init(){
		
		/*if($.cookie("ltyChoujiang"+a) === '3'){
			showSorry();
		}*/
		initing = false;
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
				/*if($.cookie("ltyChoujiang"+a) === '3'){
					showSorry();
					return false;
				}*/
				click=true;
				if($.cookie("ltyChoujiangToday")){
					showSorry();
					return false;
				}
				if($.cookie("ltyChoujiangA2246208")){//用户在pa/lty_d_tImg7?a=A2246208已经抽过奖
					
					if($.cookie("ltyChoujiang"+a)){
						if($.cookie("ltyChoujiang"+a) == '1'){//用户在这里已经抽过奖
							//hongbao --> thanks
							toRoll(7);
							return false;
						}
						/*if($.cookie("ltyChoujiang"+a) == '2'){
							//thanks
							toRoll(7);
							return false;
						}*/
					}else{
						if(!parseInt(p)){
						   p='1000';
						}
						$.cookie("ltyChoujiang"+a, "1", {
							expires : parseInt(p)
						});
						//hongbao --> thanks
						toRoll(7);
						return false;
					}
				}else{
					if($.cookie("ltyChoujiang"+a) == '1'){//用户在这里已经抽过奖
						//hongbao --> thanks
						toRoll(7);
						return false;
					}
					if($.cookie("ltyChoujiang"+a) == '2'){
						//thanks
						toRoll(7);
						return false;
					}
					gotoBx();
					return false;
				}
			}
		}
	});
	
	function toRoll(ltyPrize){
		lottery.prize=ltyPrize;
		lottery.speed=100;
		roll();
	}
	
	function gotoBx(){
		toRoll(0);
		setBxUrl();
		return false;
	}
	function setBxUrl(){
		$.ajax({
			type : "POST",
			cache : false,
			async : true,
			data:{'a':a},
			url : web_path+"/lty/getPingUrlDdh",
			dataType : "text",
			success : function(data) {
				if(data){
				   $('#save_1').attr('href',web_path+data+'?a='+a);
				}
			}
		});
	}
	function lotteryFinish(){
		var cookieTime = new Date();
		var hours = cookieTime.getHours();
		var minutes = cookieTime.getMinutes();
		var seconds = cookieTime.getSeconds();
		var theTime = ((23-hours)*60*60+(59-minutes)*60+(59-seconds)+1)*1000;
		cookieTime.setTime(cookieTime.getTime() + theTime);//次日凌晨0点时间
		$.cookie("ltyChoujiangToday", "1", {
			expires : cookieTime
		});
		if(!parseInt(p)){
		  p='1000';
		}
		if($.cookie("ltyChoujiang"+a)){
			var cnt = parseInt($.cookie("ltyChoujiang"+a));
			cnt = cnt + 1;
			if(cnt === 2){// 谢谢参与
				setTimeout(showThanks,500);
			}else{
				$.cookie("ltyChoujiang"+a, cnt+"", {
					expires : parseInt(p)
				});
			}
			/*if(cnt === 2){
				// zhonghongbao
				setTimeout(showHongbao,500);
			}*/
		}else{
			$.cookie("ltyChoujiang"+a, "1", {
				expires : parseInt(p)
			});
			setTimeout(showPrize,500);
		}
	}
	
	function showPrize(){
		$('#prizeShow').show();
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
	function showYueBing(){
		$('#yuebingShow').show();
		click=false;
		setTimeout(gotoYueBingPrize,1000);
	}
	function gotoYueBingPrize(){
		var url = $('#save_2').attr('href');
		window.location.href=url;
	}
	function showHongbao(){
		$('#hongbaoShow').show();
		click=false;
	}
	function showThanks(){
		$('#thanksShow').show();
		click=false;
		setTimeout(hideThanks,3000);
	}
	function hideThanks(){
		$('#thanksShow').hide();
	}
	
	$('#denyShow_btn').click(function(){
		$('#denyShow').hide();
	});
	
	$('#hb_img').click(function(){
		window.location.href='http://m.koudailc.com/page/register?isShare=1&source_tag=zhangyue4';
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