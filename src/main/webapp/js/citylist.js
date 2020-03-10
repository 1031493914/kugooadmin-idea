/**
 * 省份-城市下拉列表
 * @author hasee
 */
//var city_list = { 
//	1: { "n": "北京", 1: "东城", 2: "西城", 3: "崇文", 4: "宣武", 5: "朝阳"}, 
//	2: { "n": "天津", 1: "和平", 2: "河东", 3: "河西", 4: "南开", 5: "河北"}
//};
var city_list={
		1:{'p':"北京",1:"北京"},
		2:{'p':"上海",1:"上海"},
		3:{'p':"天津",1:"天津"},
		4:{'p':"重庆",1:"重庆"},
		5:{'p':"黑龙江",1:"哈尔滨"},
		6:{'p':"吉林",1:"长春"},
		7:{'p':"辽宁",1:"沈阳",2:"大连"},
		8:{'p':"陕西",1:"西安"},
		9:{'p':"江西",1:"南昌"},
		10:{'p':"山东",1:"济南",2:"青岛"},
		11:{'p':"山西",1:"太原"},
		12:{'p':"河北",1:"石家庄"},
		13:{'p':"河南",1:"郑州"},
		14:{'p':"安徽",1:"合肥"},
		15:{'p':"江苏",1:"南京",2:"苏州",3:"南通"},
		16:{'p':"浙江",1:"杭州",2:"金华"},
		17:{'p':"湖北",1:"武汉"},
		18:{'p':"湖南",1:"长沙"},
		19:{'p':"四川",1:"成都"},
		20:{'p':"福建",1:"福州市",2:"厦门"},
		21:{'p':"广东",1:"广州",2:"深圳"},
		22:{'p':"贵州",1:"贵阳"},
		23:{'p':"云南",1:"昆明"},
		24:{'p':"海南",1:"海口"}
};

//setProvCitySel();		/*<button onclick="addStorage();setProvCitySel();">添加仓库</button>*/

function setProvCitySel() { 
	var ps = FgetProvince(); 
	var px = $('#province'); 
	var cx = $('#city');
	px[0].options.length = 1;
	for(var k in ps) { 
		if(k == 0 || k == 99) { 
			continue; 
		} 
//		var option = new Option(ps[k], k);
		var option = new Option(ps[k], ps[k]);
		option.setAttribute('data-id' , k);
		px[0].options.add(option); 
	} 
//	setCitySel();		/*<select id="province" onclick="setCitySel();">*/
}

function FgetProvince() { 
	var l = {}; 
	for(var k in city_list) { 
		if(k == 0) 
			continue;
		l[k] = city_list[k]['p'] 
	} 
	return l;
}

function setCitySel() { 
	var px = $('#province'); 
	var cx = $('#city');
//	var c = FgetCity(px[0].value);
	var c = FgetCity(px.find("option:selected").data('id'));
	cx[0].options.length = 1; 
	for(var d in c) { 
//		cx[0].options.add(new Option(c[d], d)); 
		cx[0].options.add(new Option(c[d], c[d])); 
	} 
}

function FgetCity(pid) { 
	if(pid <= 0 || !(pid in city_list)) 
		return null; 
	var l = {}; 
	for(var k in city_list[pid]) { 
		if(k == 0 || k == 'p') 
			continue;
		l[pid * 100 + (k - 0)] = city_list[pid][k] 
	} 
	return l;
}

