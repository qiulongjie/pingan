$(function(){
	
	
	var provinceCityData = [{ "PId": 1, "PN": "北京" }, { "PId": 2, "PN": "天津" }, { "PId": 3, "PN": "河北" }, { "PId": 4, "PN": "山西" }, { "PId": 5, "PN": "内蒙古" }, { "PId": 6, "PN": "辽宁" }, { "PId": 7, "PN": "吉林" }, { "PId": 8, "PN": "黑龙江" }, { "PId": 9, "PN": "上海" }, { "PId": 10, "PN": "江苏" }, { "PId": 11, "PN": "浙江" }, { "PId": 12, "PN": "安徽" }, { "PId": 13, "PN": "福建" }, { "PId": 14, "PN": "江西" }, { "PId": 15, "PN": "山东" }, { "PId": 16, "PN": "河南" }, { "PId": 17, "PN": "湖北" }, { "PId": 18, "PN": "湖南" }, { "PId": 19, "PN": "广东" }, { "PId": 20, "PN": "广西" }, { "PId": 21, "PN": "海南" }, { "PId": 22, "PN": "重庆" }, { "PId": 23, "PN": "四川" }, { "PId": 24, "PN": "贵州" }, { "PId": 25, "PN": "云南" }, { "PId": 26, "PN": "西藏" }, { "PId": 27, "PN": "陕西" }, { "PId": 28, "PN": "甘肃" }, { "PId": 29, "PN": "青海" }, { "PId": 30, "PN": "宁夏" }, { "PId": 31, "PN": "新疆"}];
	
	var cityData = [{ "CId": 1, "CN": "北京", "PId": 1 }, { "CId": 2, "CN": "天津", "PId": 2 }, { "CId": 3, "CN": "石家庄", "PId": 3 }, { "CId": 4, "CN": "唐山", "PId": 3 }, { "CId": 5, "CN": "秦皇岛", "PId": 3 }, { "CId": 6, "CN": "邯郸", "PId": 3 }, { "CId": 7, "CN": "邢台", "PId": 3 }, { "CId": 8, "CN": "保定", "PId": 3 }, { "CId": 9, "CN": "张家口", "PId": 3 }, { "CId": 10, "CN": "承德", "PId": 3 }, { "CId": 11, "CN": "沧州", "PId": 3 }, { "CId": 12, "CN": "廊坊", "PId": 3 }, { "CId": 13, "CN": "衡水", "PId": 3 }, { "CId": 14, "CN": "太原", "PId": 4 }, { "CId": 15, "CN": "大同", "PId": 4 }, { "CId": 16, "CN": "阳泉", "PId": 4 }, { "CId": 17, "CN": "长治", "PId": 4 }, { "CId": 18, "CN": "晋城", "PId": 4 }, { "CId": 19, "CN": "朔州", "PId": 4 }, { "CId": 20, "CN": "晋中", "PId": 4 }, { "CId": 21, "CN": "运城", "PId": 4 }, { "CId": 22, "CN": "忻州", "PId": 4 }, { "CId": 23, "CN": "临汾", "PId": 4 }, { "CId": 24, "CN": "吕梁", "PId": 4 }, { "CId": 25, "CN": "呼和浩特", "PId": 5 }, { "CId": 26, "CN": "包头", "PId": 5 }, { "CId": 27, "CN": "乌海", "PId": 5 }, { "CId": 28, "CN": "赤峰", "PId": 5 }, { "CId": 29, "CN": "通辽", "PId": 5 }, { "CId": 30, "CN": "鄂尔多斯", "PId": 5 }, { "CId": 31, "CN": "呼伦贝尔", "PId": 5 }, { "CId": 32, "CN": "巴彦淖尔", "PId": 5 }, { "CId": 33, "CN": "乌兰察布", "PId": 5 }, { "CId": 34, "CN": "兴安", "PId": 5 }, { "CId": 35, "CN": "锡林郭勒", "PId": 5 }, { "CId": 36, "CN": "阿拉善", "PId": 5 }, { "CId": 37, "CN": "沈阳", "PId": 6 }, { "CId": 38, "CN": "大连", "PId": 6 }, { "CId": 39, "CN": "鞍山", "PId": 6 }, { "CId": 40, "CN": "抚顺", "PId": 6 }, { "CId": 41, "CN": "本溪", "PId": 6 }, { "CId": 42, "CN": "丹东", "PId": 6 }, { "CId": 43, "CN": "锦州", "PId": 6 }, { "CId": 44, "CN": "营口", "PId": 6 }, { "CId": 45, "CN": "阜新", "PId": 6 }, { "CId": 46, "CN": "辽阳", "PId": 6 }, { "CId": 47, "CN": "盘锦", "PId": 6 }, { "CId": 48, "CN": "铁岭", "PId": 6 }, { "CId": 49, "CN": "朝阳", "PId": 6 }, { "CId": 50, "CN": "葫芦岛", "PId": 6 }, { "CId": 51, "CN": "长春", "PId": 7 }, { "CId": 52, "CN": "吉林", "PId": 7 }, { "CId": 53, "CN": "四平", "PId": 7 }, { "CId": 54, "CN": "辽源", "PId": 7 }, { "CId": 55, "CN": "通化", "PId": 7 }, { "CId": 56, "CN": "白山", "PId": 7 }, { "CId": 57, "CN": "松原", "PId": 7 }, { "CId": 58, "CN": "白城", "PId": 7 }, { "CId": 59, "CN": "延边", "PId": 7 }, { "CId": 60, "CN": "哈尔滨", "PId": 8 }, { "CId": 61, "CN": "齐齐哈尔", "PId": 8 }, { "CId": 62, "CN": "鸡西", "PId": 8 }, { "CId": 63, "CN": "鹤岗", "PId": 8 }, { "CId": 64, "CN": "双鸭山", "PId": 8 }, { "CId": 65, "CN": "大庆", "PId": 8 }, { "CId": 66, "CN": "伊春", "PId": 8 }, { "CId": 67, "CN": "佳木斯", "PId": 8 }, { "CId": 68, "CN": "七台河", "PId": 8 }, { "CId": 69, "CN": "牡丹江", "PId": 8 }, { "CId": 70, "CN": "黑河", "PId": 8 }, { "CId": 71, "CN": "绥化", "PId": 8 }, { "CId": 72, "CN": "大兴安岭", "PId": 8 }, { "CId": 73, "CN": "上海", "PId": 9 }, { "CId": 74, "CN": "南京", "PId": 10 }, { "CId": 75, "CN": "无锡", "PId": 10 }, { "CId": 76, "CN": "徐州", "PId": 10 }, { "CId": 77, "CN": "常州", "PId": 10 }, { "CId": 78, "CN": "苏州", "PId": 10 }, { "CId": 79, "CN": "南通", "PId": 10 }, { "CId": 80, "CN": "连云港", "PId": 10 }, { "CId": 81, "CN": "淮安", "PId": 10 }, { "CId": 82, "CN": "盐城", "PId": 10 }, { "CId": 83, "CN": "扬州", "PId": 10 }, { "CId": 84, "CN": "镇江", "PId": 10 }, { "CId": 85, "CN": "泰州", "PId": 10 }, { "CId": 86, "CN": "宿迁", "PId": 10 }, { "CId": 87, "CN": "杭州", "PId": 11 }, { "CId": 88, "CN": "宁波", "PId": 11 }, { "CId": 89, "CN": "温州", "PId": 11 }, { "CId": 90, "CN": "嘉兴", "PId": 11 }, { "CId": 91, "CN": "湖州", "PId": 11 }, { "CId": 92, "CN": "绍兴", "PId": 11 }, { "CId": 93, "CN": "金华", "PId": 11 }, { "CId": 94, "CN": "衢州", "PId": 11 }, { "CId": 95, "CN": "舟山", "PId": 11 }, { "CId": 96, "CN": "台州", "PId": 11 }, { "CId": 97, "CN": "丽水", "PId": 11 }, { "CId": 98, "CN": "合肥", "PId": 12 }, { "CId": 99, "CN": "芜湖", "PId": 12 }, { "CId": 100, "CN": "蚌埠", "PId": 12 }, { "CId": 101, "CN": "淮南", "PId": 12 }, { "CId": 102, "CN": "马鞍山", "PId": 12 }, { "CId": 103, "CN": "淮北", "PId": 12 }, { "CId": 104, "CN": "铜陵", "PId": 12 }, { "CId": 105, "CN": "安庆", "PId": 12 }, { "CId": 106, "CN": "黄山", "PId": 12 }, { "CId": 107, "CN": "滁州", "PId": 12 }, { "CId": 108, "CN": "阜阳", "PId": 12 }, { "CId": 109, "CN": "宿州", "PId": 12 }, { "CId": 110, "CN": "六安", "PId": 12 }, { "CId": 111, "CN": "亳州", "PId": 12 }, { "CId": 112, "CN": "池州", "PId": 12 }, { "CId": 113, "CN": "宣城", "PId": 12 }, { "CId": 114, "CN": "福州", "PId": 13 }, { "CId": 115, "CN": "厦门", "PId": 13 }, { "CId": 116, "CN": "莆田", "PId": 13 }, { "CId": 117, "CN": "三明", "PId": 13 }, { "CId": 118, "CN": "泉州", "PId": 13 }, { "CId": 119, "CN": "漳州", "PId": 13 }, { "CId": 120, "CN": "南平", "PId": 13 }, { "CId": 121, "CN": "龙岩", "PId": 13 }, { "CId": 122, "CN": "宁德", "PId": 13 }, { "CId": 123, "CN": "南昌", "PId": 14 }, { "CId": 124, "CN": "景德镇", "PId": 14 }, { "CId": 125, "CN": "萍乡", "PId": 14 }, { "CId": 126, "CN": "九江", "PId": 14 }, { "CId": 127, "CN": "新余", "PId": 14 }, { "CId": 128, "CN": "鹰潭", "PId": 14 }, { "CId": 129, "CN": "赣州", "PId": 14 }, { "CId": 130, "CN": "吉安", "PId": 14 }, { "CId": 131, "CN": "宜春", "PId": 14 }, { "CId": 132, "CN": "抚州", "PId": 14 }, { "CId": 133, "CN": "上饶", "PId": 14 }, { "CId": 134, "CN": "济南", "PId": 15 }, { "CId": 135, "CN": "青岛", "PId": 15 }, { "CId": 136, "CN": "淄博", "PId": 15 }, { "CId": 137, "CN": "枣庄", "PId": 15 }, { "CId": 138, "CN": "东营", "PId": 15 }, { "CId": 139, "CN": "烟台", "PId": 15 }, { "CId": 140, "CN": "潍坊", "PId": 15 }, { "CId": 141, "CN": "济宁", "PId": 15 }, { "CId": 142, "CN": "泰安", "PId": 15 }, { "CId": 143, "CN": "威海", "PId": 15 }, { "CId": 144, "CN": "日照", "PId": 15 }, { "CId": 145, "CN": "莱芜", "PId": 15 }, { "CId": 146, "CN": "临沂", "PId": 15 }, { "CId": 147, "CN": "德州", "PId": 15 }, { "CId": 148, "CN": "聊城", "PId": 15 }, { "CId": 149, "CN": "滨州", "PId": 15 }, { "CId": 150, "CN": "菏泽", "PId": 15 }, { "CId": 151, "CN": "郑州", "PId": 16 }, { "CId": 152, "CN": "开封", "PId": 16 }, { "CId": 153, "CN": "洛阳", "PId": 16 }, { "CId": 154, "CN": "平顶山", "PId": 16 }, { "CId": 155, "CN": "安阳", "PId": 16 }, { "CId": 156, "CN": "鹤壁", "PId": 16 }, { "CId": 157, "CN": "新乡", "PId": 16 }, { "CId": 158, "CN": "焦作", "PId": 16 }, { "CId": 159, "CN": "濮阳", "PId": 16 }, { "CId": 160, "CN": "许昌", "PId": 16 }, { "CId": 161, "CN": "漯河", "PId": 16 }, { "CId": 162, "CN": "三门峡", "PId": 16 }, { "CId": 163, "CN": "南阳", "PId": 16 }, { "CId": 164, "CN": "商丘", "PId": 16 }, { "CId": 165, "CN": "信阳", "PId": 16 }, { "CId": 166, "CN": "周口", "PId": 16 }, { "CId": 167, "CN": "驻马店", "PId": 16 }, { "CId": 168, "CN": "济源", "PId": 16 }, { "CId": 169, "CN": "武汉", "PId": 17 }, { "CId": 170, "CN": "黄石", "PId": 17 }, { "CId": 171, "CN": "十堰", "PId": 17 }, { "CId": 172, "CN": "宜昌", "PId": 17 }, { "CId": 173, "CN": "襄阳", "PId": 17 }, { "CId": 174, "CN": "鄂州", "PId": 17 }, { "CId": 175, "CN": "荆门", "PId": 17 }, { "CId": 176, "CN": "孝感", "PId": 17 }, { "CId": 177, "CN": "荆州", "PId": 17 }, { "CId": 178, "CN": "黄冈", "PId": 17 }, { "CId": 179, "CN": "咸宁", "PId": 17 }, { "CId": 180, "CN": "随州", "PId": 17 }, { "CId": 181, "CN": "恩施", "PId": 17 }, { "CId": 182, "CN": "仙桃", "PId": 17 }, { "CId": 183, "CN": "潜江", "PId": 17 }, { "CId": 184, "CN": "天门", "PId": 17 }, { "CId": 185, "CN": "神农架", "PId": 17 }, { "CId": 186, "CN": "长沙", "PId": 18 }, { "CId": 187, "CN": "株洲", "PId": 18 }, { "CId": 188, "CN": "湘潭", "PId": 18 }, { "CId": 189, "CN": "衡阳", "PId": 18 }, { "CId": 190, "CN": "邵阳", "PId": 18 }, { "CId": 191, "CN": "岳阳", "PId": 18 }, { "CId": 192, "CN": "常德", "PId": 18 }, { "CId": 193, "CN": "张家界", "PId": 18 }, { "CId": 194, "CN": "益阳", "PId": 18 }, { "CId": 195, "CN": "郴州", "PId": 18 }, { "CId": 196, "CN": "永州", "PId": 18 }, { "CId": 197, "CN": "怀化", "PId": 18 }, { "CId": 198, "CN": "娄底", "PId": 18 }, { "CId": 199, "CN": "湘西", "PId": 18 }, { "CId": 200, "CN": "广州", "PId": 19 }, { "CId": 201, "CN": "韶关", "PId": 19 }, { "CId": 202, "CN": "深圳", "PId": 19 }, { "CId": 203, "CN": "珠海", "PId": 19 }, { "CId": 204, "CN": "汕头", "PId": 19 }, { "CId": 205, "CN": "佛山", "PId": 19 }, { "CId": 206, "CN": "江门", "PId": 19 }, { "CId": 207, "CN": "湛江", "PId": 19 }, { "CId": 208, "CN": "茂名", "PId": 19 }, { "CId": 209, "CN": "肇庆", "PId": 19 }, { "CId": 210, "CN": "惠州", "PId": 19 }, { "CId": 211, "CN": "梅州", "PId": 19 }, { "CId": 212, "CN": "汕尾", "PId": 19 }, { "CId": 213, "CN": "河源", "PId": 19 }, { "CId": 214, "CN": "阳江", "PId": 19 }, { "CId": 215, "CN": "清远", "PId": 19 }, { "CId": 216, "CN": "东莞", "PId": 19 }, { "CId": 217, "CN": "中山", "PId": 19 }, { "CId": 218, "CN": "潮州", "PId": 19 }, { "CId": 219, "CN": "揭阳", "PId": 19 }, { "CId": 220, "CN": "云浮", "PId": 19 }, { "CId": 221, "CN": "南宁", "PId": 20 }, { "CId": 222, "CN": "柳州", "PId": 20 }, { "CId": 223, "CN": "桂林", "PId": 20 }, { "CId": 224, "CN": "梧州", "PId": 20 }, { "CId": 225, "CN": "北海", "PId": 20 }, { "CId": 226, "CN": "防城港", "PId": 20 }, { "CId": 227, "CN": "钦州", "PId": 20 }, { "CId": 228, "CN": "贵港", "PId": 20 }, { "CId": 229, "CN": "玉林", "PId": 20 }, { "CId": 230, "CN": "百色", "PId": 20 }, { "CId": 231, "CN": "贺州", "PId": 20 }, { "CId": 232, "CN": "河池", "PId": 20 }, { "CId": 233, "CN": "来宾", "PId": 20 }, { "CId": 234, "CN": "崇左", "PId": 20 }, { "CId": 235, "CN": "海口", "PId": 21 }, { "CId": 236, "CN": "三亚", "PId": 21 }, { "CId": 237, "CN": "三沙", "PId": 21 }, { "CId": 238, "CN": "五指山", "PId": 21 }, { "CId": 239, "CN": "琼海", "PId": 21 }, { "CId": 240, "CN": "儋州", "PId": 21 }, { "CId": 241, "CN": "文昌", "PId": 21 }, { "CId": 242, "CN": "万宁", "PId": 21 }, { "CId": 243, "CN": "东方", "PId": 21 }, { "CId": 244, "CN": "定安", "PId": 21 }, { "CId": 245, "CN": "屯昌", "PId": 21 }, { "CId": 246, "CN": "澄迈", "PId": 21 }, { "CId": 247, "CN": "临高", "PId": 21 }, { "CId": 248, "CN": "白沙", "PId": 21 }, { "CId": 249, "CN": "昌江", "PId": 21 }, { "CId": 250, "CN": "乐东", "PId": 21 }, { "CId": 251, "CN": "陵水", "PId": 21 }, { "CId": 252, "CN": "保亭", "PId": 21 }, { "CId": 253, "CN": "琼中", "PId": 21 }, { "CId": 254, "CN": "重庆", "PId": 22 }, { "CId": 255, "CN": "成都", "PId": 23 }, { "CId": 256, "CN": "自贡", "PId": 23 }, { "CId": 257, "CN": "攀枝花", "PId": 23 }, { "CId": 258, "CN": "泸州", "PId": 23 }, { "CId": 259, "CN": "德阳", "PId": 23 }, { "CId": 260, "CN": "绵阳", "PId": 23 }, { "CId": 261, "CN": "广元", "PId": 23 }, { "CId": 262, "CN": "遂宁", "PId": 23 }, { "CId": 263, "CN": "内江", "PId": 23 }, { "CId": 264, "CN": "乐山", "PId": 23 }, { "CId": 265, "CN": "南充", "PId": 23 }, { "CId": 266, "CN": "眉山", "PId": 23 }, { "CId": 267, "CN": "宜宾", "PId": 23 }, { "CId": 268, "CN": "广安", "PId": 23 }, { "CId": 269, "CN": "达州", "PId": 23 }, { "CId": 270, "CN": "雅安", "PId": 23 }, { "CId": 271, "CN": "巴中", "PId": 23 }, { "CId": 272, "CN": "资阳", "PId": 23 }, { "CId": 273, "CN": "阿坝", "PId": 23 }, { "CId": 274, "CN": "甘孜", "PId": 23 }, { "CId": 275, "CN": "凉山", "PId": 23 }, { "CId": 276, "CN": "贵阳", "PId": 24 }, { "CId": 277, "CN": "六盘水", "PId": 24 }, { "CId": 278, "CN": "遵义", "PId": 24 }, { "CId": 279, "CN": "安顺", "PId": 24 }, { "CId": 280, "CN": "毕节", "PId": 24 }, { "CId": 281, "CN": "铜仁", "PId": 24 }, { "CId": 282, "CN": "黔西南", "PId": 24 }, { "CId": 283, "CN": "黔东南", "PId": 24 }, { "CId": 284, "CN": "黔南", "PId": 24 }, { "CId": 285, "CN": "昆明", "PId": 25 }, { "CId": 286, "CN": "曲靖", "PId": 25 }, { "CId": 287, "CN": "玉溪", "PId": 25 }, { "CId": 288, "CN": "保山", "PId": 25 }, { "CId": 289, "CN": "昭通", "PId": 25 }, { "CId": 290, "CN": "丽江", "PId": 25 }, { "CId": 291, "CN": "普洱", "PId": 25 }, { "CId": 292, "CN": "临沧", "PId": 25 }, { "CId": 293, "CN": "楚雄", "PId": 25 }, { "CId": 294, "CN": "红河", "PId": 25 }, { "CId": 295, "CN": "文山", "PId": 25 }, { "CId": 296, "CN": "西双版纳", "PId": 25 }, { "CId": 297, "CN": "大理", "PId": 25 }, { "CId": 298, "CN": "德宏", "PId": 25 }, { "CId": 299, "CN": "怒江", "PId": 25 }, { "CId": 300, "CN": "迪庆", "PId": 25 }, { "CId": 301, "CN": "拉萨", "PId": 26 }, { "CId": 302, "CN": "昌都", "PId": 26 }, { "CId": 303, "CN": "山南", "PId": 26 }, { "CId": 304, "CN": "日喀则", "PId": 26 }, { "CId": 305, "CN": "那曲", "PId": 26 }, { "CId": 306, "CN": "阿里", "PId": 26 }, { "CId": 307, "CN": "林芝", "PId": 26 }, { "CId": 308, "CN": "西安", "PId": 27 }, { "CId": 309, "CN": "铜川", "PId": 27 }, { "CId": 310, "CN": "宝鸡", "PId": 27 }, { "CId": 311, "CN": "咸阳", "PId": 27 }, { "CId": 312, "CN": "渭南", "PId": 27 }, { "CId": 313, "CN": "延安", "PId": 27 }, { "CId": 314, "CN": "汉中", "PId": 27 }, { "CId": 315, "CN": "榆林", "PId": 27 }, { "CId": 316, "CN": "安康", "PId": 27 }, { "CId": 317, "CN": "商洛", "PId": 27 }, { "CId": 318, "CN": "兰州", "PId": 28 }, { "CId": 319, "CN": "嘉峪关", "PId": 28 }, { "CId": 320, "CN": "金昌", "PId": 28 }, { "CId": 321, "CN": "白银", "PId": 28 }, { "CId": 322, "CN": "天水", "PId": 28 }, { "CId": 323, "CN": "武威", "PId": 28 }, { "CId": 324, "CN": "张掖", "PId": 28 }, { "CId": 325, "CN": "平凉", "PId": 28 }, { "CId": 326, "CN": "酒泉", "PId": 28 }, { "CId": 327, "CN": "庆阳", "PId": 28 }, { "CId": 328, "CN": "定西", "PId": 28 }, { "CId": 329, "CN": "陇南", "PId": 28 }, { "CId": 330, "CN": "临夏", "PId": 28 }, { "CId": 331, "CN": "甘南", "PId": 28 }, { "CId": 332, "CN": "西宁", "PId": 29 }, { "CId": 333, "CN": "海东", "PId": 29 }, { "CId": 334, "CN": "海北", "PId": 29 }, { "CId": 335, "CN": "黄南", "PId": 29 }, { "CId": 336, "CN": "海南", "PId": 29 }, { "CId": 337, "CN": "果洛", "PId": 29 }, { "CId": 338, "CN": "玉树", "PId": 29 }, { "CId": 339, "CN": "海西", "PId": 29 }, { "CId": 340, "CN": "银川", "PId": 30 }, { "CId": 341, "CN": "石嘴山", "PId": 30 }, { "CId": 342, "CN": "吴忠", "PId": 30 }, { "CId": 343, "CN": "固原", "PId": 30 }, { "CId": 344, "CN": "中卫", "PId": 30 }, { "CId": 345, "CN": "乌鲁木齐", "PId": 31 }, { "CId": 346, "CN": "克拉玛依", "PId": 31 }, { "CId": 347, "CN": "吐鲁番", "PId": 31 }, { "CId": 348, "CN": "哈密", "PId": 31 }, { "CId": 349, "CN": "昌吉", "PId": 31 }, { "CId": 350, "CN": "博尔塔拉", "PId": 31 }, { "CId": 351, "CN": "巴音郭楞", "PId": 31 }, { "CId": 352, "CN": "阿克苏", "PId": 31 }, { "CId": 353, "CN": "克孜勒苏", "PId": 31 }, { "CId": 354, "CN": "喀什", "PId": 31 }, { "CId": 355, "CN": "和田", "PId": 31 }, { "CId": 356, "CN": "伊犁", "PId": 31 }, { "CId": 357, "CN": "塔城", "PId": 31 }, { "CId": 358, "CN": "阿勒泰", "PId": 31 }, { "CId": 359, "CN": "石河子", "PId": 31 }, { "CId": 360, "CN": "阿拉尔", "PId": 31 }, { "CId": 361, "CN": "图木舒克", "PId": 31 }, { "CId": 362, "CN": "五家渠", "PId": 31 }, { "CId": 363, "CN": "北屯", "PId": 31}];
	
    var CPdate = [{ "CId": 352, "CP": "新N" }, { "CId": 358, "CP": "新H" }, { "CId": 316, "CP": "陕G" }, { "CId": 105, "CP": "皖H" }, { "CId": 279, "CP": "贵G" }, { "CId": 155, "CP": "豫E" }, { "CId": 39, "CP": "辽C" }, { "CId": 32, "CP": "蒙L" }, { "CId": 58, "CP": "吉G" }, { "CId": 56, "CP": "吉F" }, { "CId": 321, "CP": "甘D" }, { "CId": 230, "CP": "桂L" }, { "CId": 100, "CP": "皖C" }, { "CId": 26, "CP": "蒙B" }, { "CId": 310, "CP": "陕C" }, { "CId": 8, "CP": "冀F" }, { "CId": 288, "CP": "云M" }, { "CId": 225, "CP": "桂E" }, { "CId": 1, "CP": "京" }, { "CId": 41, "CP": "辽E" }, { "CId": 280, "CP": "贵F" }, { "CId": 149, "CP": "鲁M" }, { "CId": 111, "CP": "皖S" }, { "CId": 11, "CP": "冀S" }, { "CId": 349, "CP": "新B" }, { "CId": 51, "CP": "吉A" }, { "CId": 186, "CP": "湘A" }, { "CId": 17, "CP": "晋D" }, { "CId": 192, "CP": "湘J" }, { "CId": 77, "CP": "苏D" }, { "CId": 49, "CP": "辽N" }, { "CId": 218, "CP": "粤U" }, { "CId": 195, "CP": "湘L" }, { "CId": 255, "CP": "川A" }, { "CId": 10, "CP": "冀H" }, { "CId": 112, "CP": "皖R" }, { "CId": 28, "CP": "蒙D" }, { "CId": 107, "CP": "皖M" }, { "CId": 293, "CP": "云E" }, { "CId": 269, "CP": "川S" }, { "CId": 297, "CP": "云L" }, { "CId": 38, "CP": "辽B" }, { "CId": 65, "CP": "黑E" }, { "CId": 15, "CP": "晋B" }, { "CId": 42, "CP": "辽F" }, { "CId": 259, "CP": "川F" }, { "CId": 147, "CP": "鲁N" }, { "CId": 328, "CP": "甘J" }, { "CId": 216, "CP": "粤S" }, { "CId": 138, "CP": "鲁E" }, { "CId": 30, "CP": "蒙K" }, { "CId": 174, "CP": "鄂G" }, { "CId": 181, "CP": "鄂Q" }, { "CId": 226, "CP": "桂P" }, { "CId": 205, "CP": "粤E" }, { "CId": 114, "CP": "闽A" }, { "CId": 40, "CP": "辽D" }, { "CId": 132, "CP": "赣F" }, { "CId": 45, "CP": "辽J" }, { "CId": 108, "CP": "皖K" }, { "CId": 129, "CP": "赣B" }, { "CId": 343, "CP": "宁D" }, { "CId": 268, "CP": "川X" }, { "CId": 261, "CP": "川H" }, { "CId": 200, "CP": "粤A" }, { "CId": 228, "CP": "桂R" }, { "CId": 276, "CP": "贵A" }, { "CId": 223, "CP": "桂C" }, { "CId": 60, "CP": "黑A" }, { "CId": 348, "CP": "新L" }, { "CId": 235, "CP": "琼A" }, { "CId": 6, "CP": "冀D" }, { "CId": 314, "CP": "陕F" }, { "CId": 87, "CP": "浙A" }, { "CId": 98, "CP": "皖A" }, { "CId": 232, "CP": "桂M" }, { "CId": 213, "CP": "粤P" }, { "CId": 150, "CP": "鲁R" }, { "CId": 231, "CP": "桂J" }, { "CId": 156, "CP": "豫F" }, { "CId": 63, "CP": "黑H" }, { "CId": 70, "CP": "黑N" }, { "CId": 13, "CP": "冀T" }, { "CId": 189, "CP": "湘D" }, { "CId": 25, "CP": "蒙A" }, { "CId": 31, "CP": "蒙H" }, { "CId": 91, "CP": "浙E" }, { "CId": 50, "CP": "辽P" }, { "CId": 197, "CP": "湘N" }, { "CId": 81, "CP": "苏H" }, { "CId": 103, "CP": "皖F" }, { "CId": 101, "CP": "皖D" }, { "CId": 178, "CP": "鄂J" }, { "CId": 106, "CP": "皖J" }, { "CId": 170, "CP": "鄂B" }, { "CId": 210, "CP": "粤L" }, { "CId": 62, "CP": "黑G" }, { "CId": 130, "CP": "赣D" }, { "CId": 52, "CP": "吉B" }, { "CId": 134, "CP": "鲁A" }, { "CId": 141, "CP": "鲁H" }, { "CId": 168, "CP": "豫U" }, { "CId": 67, "CP": "黑D" }, { "CId": 90, "CP": "浙F" }, { "CId": 319, "CP": "甘B" }, { "CId": 206, "CP": "粤J" }, { "CId": 158, "CP": "豫H" }, { "CId": 219, "CP": "粤V" }, { "CId": 320, "CP": "甘C" }, { "CId": 93, "CP": "浙G" }, { "CId": 43, "CP": "辽G" }, { "CId": 18, "CP": "晋E" }, { "CId": 20, "CP": "晋K" }, { "CId": 175, "CP": "鄂H" }, { "CId": 177, "CP": "鄂D" }, { "CId": 124, "CP": "赣H" }, { "CId": 126, "CP": "赣G" }, { "CId": 326, "CP": "甘F" }, { "CId": 354, "CP": "新Q" }, { "CId": 152, "CP": "豫B" }, { "CId": 346, "CP": "新J" }, { "CId": 285, "CP": "云A" }, { "CId": 145, "CP": "鲁S" }, { "CId": 318, "CP": "甘A" }, { "CId": 12, "CP": "冀R" }, { "CId": 264, "CP": "川L" }, { "CId": 290, "CP": "云P" }, { "CId": 97, "CP": "浙K" }, { "CId": 80, "CP": "苏G" }, { "CId": 46, "CP": "辽K" }, { "CId": 54, "CP": "吉D" }, { "CId": 148, "CP": "鲁P" }, { "CId": 292, "CP": "云S" }, { "CId": 23, "CP": "晋L" }, { "CId": 146, "CP": "鲁Q" }, { "CId": 222, "CP": "桂B" }, { "CId": 110, "CP": "皖N" }, { "CId": 277, "CP": "贵B" }, { "CId": 121, "CP": "闽F" }, { "CId": 329, "CP": "甘K" }, { "CId": 198, "CP": "湘K" }, { "CId": 24, "CP": "晋J" }, { "CId": 153, "CP": "豫C" }, { "CId": 161, "CP": "豫L" }, { "CId": 102, "CP": "皖E" }, { "CId": 208, "CP": "粤K" }, { "CId": 266, "CP": "川Z" }, { "CId": 211, "CP": "粤M" }, { "CId": 260, "CP": "川B" }, { "CId": 263, "CP": "川K" }, { "CId": 123, "CP": "赣A" }, { "CId": 265, "CP": "川R" }, { "CId": 74, "CP": "苏A" }, { "CId": 221, "CP": "桂A" }, { "CId": 120, "CP": "闽H" }, { "CId": 79, "CP": "苏F" }, { "CId": 163, "CP": "豫R" }, { "CId": 88, "CP": "浙B" }, { "CId": 122, "CP": "闽J" }, { "CId": 257, "CP": "川D" }, { "CId": 47, "CP": "辽L" }, { "CId": 154, "CP": "豫D" }, { "CId": 325, "CP": "甘L" }, { "CId": 125, "CP": "赣J" }, { "CId": 116, "CP": "闽B" }, { "CId": 159, "CP": "豫J" }, { "CId": 291, "CP": "云J" }, { "CId": 61, "CP": "黑B" }, { "CId": 183, "CP": "鄂N" }, { "CId": 227, "CP": "桂N" }, { "CId": 5, "CP": "冀C" }, { "CId": 135, "CP": "鲁B" }, { "CId": 215, "CP": "粤R" }, { "CId": 327, "CP": "甘M" }, { "CId": 286, "CP": "云D" }, { "CId": 94, "CP": "浙H" }, { "CId": 118, "CP": "闽C" }, { "CId": 144, "CP": "鲁L" }, { "CId": 162, "CP": "豫M" }, { "CId": 117, "CP": "闽G" }, { "CId": 236, "CP": "琼B" }, { "CId": 204, "CP": "粤D" }, { "CId": 212, "CP": "粤N" }, { "CId": 317, "CP": "陕H" }, { "CId": 164, "CP": "豫N" }, { "CId": 73, "CP": "沪" }, { "CId": 133, "CP": "赣E" }, { "CId": 201, "CP": "粤F" }, { "CId": 190, "CP": "湘E" }, { "CId": 92, "CP": "浙D" }, { "CId": 202, "CP": "粤B" }, { "CId": 37, "CP": "辽A" }, { "CId": 171, "CP": "鄂C" }, { "CId": 359, "CP": "新C" }, { "CId": 3, "CP": "冀A" }, { "CId": 341, "CP": "宁B" }, { "CId": 64, "CP": "黑J" }, { "CId": 19, "CP": "晋F" }, { "CId": 53, "CP": "吉C" }, { "CId": 57, "CP": "吉J" }, { "CId": 78, "CP": "苏E" }, { "CId": 86, "CP": "苏N" }, { "CId": 109, "CP": "皖L" }, { "CId": 71, "CP": "黑M" }, { "CId": 180, "CP": "鄂S" }, { "CId": 262, "CP": "川J" }, { "CId": 357, "CP": "新G" }, { "CId": 96, "CP": "浙J" }, { "CId": 14, "CP": "晋A" }, { "CId": 142, "CP": "鲁J" }, { "CId": 85, "CP": "苏M" }, { "CId": 4, "CP": "冀B" }, { "CId": 2, "CP": "津" }, { "CId": 322, "CP": "甘E" }, { "CId": 48, "CP": "辽M" }, { "CId": 55, "CP": "吉E" }, { "CId": 29, "CP": "蒙G" }, { "CId": 309, "CP": "陕B" }, { "CId": 104, "CP": "皖G" }, { "CId": 281, "CP": "贵D" }, { "CId": 347, "CP": "新K" }, { "CId": 143, "CP": "鲁K" }, { "CId": 140, "CP": "鲁G" }, { "CId": 312, "CP": "陕E" }, { "CId": 89, "CP": "浙C" }, { "CId": 27, "CP": "蒙C" }, { "CId": 33, "CP": "蒙J" }, { "CId": 345, "CP": "新A" }, { "CId": 75, "CP": "苏B" }, { "CId": 342, "CP": "宁C" }, { "CId": 99, "CP": "皖B" }, { "CId": 224, "CP": "桂D" }, { "CId": 169, "CP": "鄂A" }, { "CId": 323, "CP": "甘H" }, { "CId": 308, "CP": "陕A" }, { "CId": 332, "CP": "青A" }, { "CId": 115, "CP": "闽D" }, { "CId": 182, "CP": "鄂M" }, { "CId": 179, "CP": "鄂L" }, { "CId": 311, "CP": "陕D" }, { "CId": 188, "CP": "湘C" }, { "CId": 176, "CP": "鄂K" }, { "CId": 22, "CP": "晋H" }, { "CId": 157, "CP": "豫G" }, { "CId": 127, "CP": "赣K" }, { "CId": 165, "CP": "豫S" }, { "CId": 7, "CP": "冀E" }, { "CId": 76, "CP": "苏C" }, { "CId": 160, "CP": "豫K" }, { "CId": 113, "CP": "皖P" }, { "CId": 270, "CP": "川T" }, { "CId": 139, "CP": "鲁F" }, { "CId": 313, "CP": "陕J" }, { "CId": 82, "CP": "苏J" }, { "CId": 83, "CP": "苏K" }, { "CId": 214, "CP": "粤Q" }, { "CId": 16, "CP": "晋C" }, { "CId": 66, "CP": "黑F" }, { "CId": 267, "CP": "川Q" }, { "CId": 172, "CP": "鄂E" }, { "CId": 131, "CP": "赣C" }, { "CId": 194, "CP": "湘H" }, { "CId": 340, "CP": "宁A" }, { "CId": 128, "CP": "赣L" }, { "CId": 44, "CP": "辽H" }, { "CId": 196, "CP": "湘M" }, { "CId": 315, "CP": "陕K" }, { "CId": 229, "CP": "桂K" }, { "CId": 287, "CP": "云F" }, { "CId": 191, "CP": "湘F" }, { "CId": 220, "CP": "粤W" }, { "CId": 21, "CP": "晋M" }, { "CId": 137, "CP": "鲁D" }, { "CId": 207, "CP": "粤G" }, { "CId": 193, "CP": "湘G" }, { "CId": 9, "CP": "冀G" }, { "CId": 324, "CP": "甘G" }, { "CId": 119, "CP": "闽E" }, { "CId": 289, "CP": "云C" }, { "CId": 209, "CP": "粤H" }, { "CId": 84, "CP": "苏L" }, { "CId": 151, "CP": "豫A" }, { "CId": 217, "CP": "粤T" }, { "CId": 344, "CP": "宁E" }, { "CId": 254, "CP": "渝" }, { "CId": 95, "CP": "浙L" }, { "CId": 166, "CP": "豫P" }, { "CId": 187, "CP": "湘B" }, { "CId": 203, "CP": "粤C" }, { "CId": 167, "CP": "豫Q" }, { "CId": 272, "CP": "川M" }, { "CId": 136, "CP": "鲁C" }, { "CId": 256, "CP": "川C" }, { "CId": 278, "CP": "贵C"}];
    
    $("#pSelect").empty();
    
    // 循环为省份下拉框赋值
    $.each(provinceCityData,function(i,item){
    	$("#pSelect").append("<option pid='"+item.PId+"'>"+item.PN+"</option>");
    });
    
    // 为城市下拉框赋值
    changeCity($("#pSelect").find("option:selected").attr('pid'));
    
    // 设置车牌前置信息
    changeCarT($("#cSelect").find("option:selected").attr('cid'));
    
    //省份下拉框改变事件
    $("#pSelect").change(function(){
    	var pn = $("#pSelect").find("option:selected").text();
    	var pid = $("#pSelect").find("option:selected").attr('pid');
    	changeCity(pid);
    	changeCarT($("#cSelect").find("option:selected").attr('cid'));
    });
    
    // 根据省份变换城市
    function changeCity(pid){
    	$("#cSelect").empty();
    	$.each(cityData,function(i,item){
    		if(pid === item.PId+''){
    			$("#cSelect").append("<option cid='"+item.CId+"'>"+item.CN+"</option>");
    		}
        });
    }
    // 城市下拉框改变事件
    $("#cSelect").change(function(){
    	var pn = $("#cSelect").find("option:selected").text();
    	var cid = $("#cSelect").find("option:selected").attr('cid');
    	changeCarT(cid);
    });
    
    // 根据城市变换车牌
    function changeCarT(cid){
    	$.each(CPdate,function(i,item){
    		if(cid === item.CId+''){
    			 $("#carTselect option").each(function(){
    				 if($(this).text() === item.CP.substring(0,1)){
    					 $(this).attr('selected','');
    					 $('#carNum').val(item.CP.substring(1,2));
    				 }
    			 });
    		}
        });
    }
    
    $('#newCar').change(function(){
    	if(($(this).attr('checked'))){
    		$('#carNum').val($('#carNum').val().substring(0,1)+'*');
    		$('#carNum').attr('disabled','');
    	}else{
    		$('#carNum').removeAttr('disabled');
    		$('#carNum').val($('#carNum').val().substring(0,$('#carNum').val().length-1));
    	}
    });
    
   
});

//  验证表单 
function checkForm(){
	if ($("#pSelect").val() == "" || $("#cSelect").val() == "") {
		alert("请选择车辆行驶区域");
		return false;
	}
	var license = $("#carNum").val();
	var reg = /^[a-zA-Z][a-z0-9A-Z]{5,6}$/;
	var reg2 = /^[a-zA-Z]{0,1}[*]$/;
	if (!$('#newCar').is(":checked")) {
		if (license.length != 6 || !reg.test(license)) {
			alert("请按照格式“NW8178”填写车牌号码");
			return false;
		}
	} 
	/*var tstr = $("#purchasetime").val();
	var regtime = /^[0-9]{4}[年][0-9]{2}[月]$/;

	if (tstr == "请选择购车时间") {
		$("#l_msg1").show();
		$("#l_msg2").html("请选择购车时间");
		return false;
	}
	if (!regtime.test(tstr)) {
		$("#l_msg1").show();
		$("#l_msg2").html("您输入的购车时间不正确");
		return false;
	}*/

	var priceReg = /^(\d{1,3})(\.\d{1,4})?$/;
	if (!priceReg.test($("#carprice").val())) {
		alert("您输入的车价不正确");
		return false;
	}

	if ($("#uname").val() == "" || $("#uname").val() == "车主姓名") {
		alert("请输入车主姓名");
		return false;
	}
	var linkNameReg = /^[\u4e00-\u9fa5]{2,6}$/;
	if (!linkNameReg.test($("#uname").val())) {
		alert("姓名只能输入2-6个中文,请您重新检查.");
		return false;
	}
	if ($("#birthday").val() != "") {
		if(!IsValidBirthday($("#birthday").val())){
			alert("生日格式不正确");
			return false;
		}
	}
	if ($("#phone").val() == "" || $("#phone").val() == "手机") {
		alert("请输入手机号码");
		return false;
	}
	
	var MobileReg = /^((13|15|18|17|14)\d{9,10})?$/;
	if (!MobileReg.test($("#phone").val())) {
		alert("请输入正确的手机号码");
		return false;
	}
	document.form1.submit();
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