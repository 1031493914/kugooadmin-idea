/**
 * jedate日期插件
 * 时间范围
 */
var start = {
	format : 'YYYY-MM-DD hh:mm:ss',
	minDate : $.nowDate(0), //设定最小日期为当前日期
	festival : true,
	//isinitVal:true,
	//maxDate : $.nowDate(0), //最大日期
	choosefun : function(elem, datas) {
		end.minDate = datas; //开始日选好后，重置结束日的最小日期
	}
};
var end = {
	format : 'YYYY-MM-DD hh:mm:ss',
	minDate : $.nowDate(0), //设定最小日期为当前日期
	festival : true,
	//isinitVal:true,
	//maxDate : $.nowDate(0), //最大日期
	choosefun : function(elem, datas) {
		start.maxDate = datas; //将结束日的初始值设定为开始日的最大日期
		orderend.maxDate = datas;
	}
};
var orderend = {
		format : 'YYYY-MM-DD hh:mm:ss',
		minDate : $.nowDate(0), //设定最小日期为当前日期
		festival : true,
		//isinitVal:true,
		//maxDate : $.nowDate(0), //最大日期
		choosefun : function(elem, datas) {
			//start.maxDate = datas; //将结束日的初始值设定为开始日的最大日期
		}
	};
$("#startdate").jeDate(start);
$("#enddate").jeDate(end);
$("#orderendtime").jeDate(orderend);
$('#startdate').click(function() {
	if ($(this).val().trim() == '') {
			$('#jedatebox').find('.jedatehms li:eq(0)').find('input:eq(0)').val('00');
			$('#jedatebox').find('.jedatehms li:eq(1)').find('input:eq(0)').val('00');
			$('#jedatebox').find('.jedatehms li:eq(2)').find('input:eq(0)').val('00');
	}
});
$('#enddate').click(function() {
	if ($(this).val().trim() == '') {
			$('#jedatebox').find('.jedatehms li:eq(0)').find('input:eq(0)').val('23');
			$('#jedatebox').find('.jedatehms li:eq(1)').find('input:eq(0)').val('59');
			$('#jedatebox').find('.jedatehms li:eq(2)').find('input:eq(0)').val('59');
	}
});
$('#orderendtime').click(function() {
	if ($(this).val().trim() == '') {
			$('#jedatebox').find('.jedatehms li:eq(0)').find('input:eq(0)').val('23');
			$('#jedatebox').find('.jedatehms li:eq(1)').find('input:eq(0)').val('59');
			$('#jedatebox').find('.jedatehms li:eq(2)').find('input:eq(0)').val('59');
	}
});
$(function() {
	
	new FileUpload({
		button_placeholder_id : "imgInput",
		post_params : {},
		
		onSuccess : function(file, data) {
			if (data.error != 0) {
				
				alert(data.msg);
				return;
			}
			$('input[name="bigPictureimg"]').val(data.path);
			$('#imgss0').attr('src', ImgLocation + data.path);
			$('#imgss0').next('.bg-red-light').removeClass("hidden");
		}
	});
	$('[name="bigPictureimg"]').parent().find(".bg-red-light").each(function () {
		$(this).click(function () {
			$(this).prev("img").attr('src',ImgLocation+defaultImg);
			$(this).prev("img").prev().val("");
		});
	});
	$(".bg-red-light").each(function () {
		$(this).click(function () {
			$(this).addClass("hidden");
		});
	});
	//点击input 清空所有错误提示
	$('input').each(function() {
		$(this).focus(function() {
			$("span[id^=error-]").html('');
		})
	})
});
function getOn(){
	var bool=true;
	if($('[name="name1"]').val()==""){
		bool=false;
		$("#error-name1").css({
			"color":"red"
		})
		$("#error-name1").html("请输入活动标题");
	}
	if($('[name="bigPictureimg"]').val()==""){
		bool=false;
		$("#error-bigPictureimg").css({
			"color":"red"
		})
		$("#error-bigPictureimg").html("请上传活动大图");
	}
	if($('[name="startdate"]').val()==""){
		bool=false;
		$("#error-startdate").css({
			"color":"red"
		})
		$("#error-startdate").html("请输入开始时间和结束时间");
	}
	if($('[name="enddate"]').val()==""){
		bool=false;
		$("#error-startdate").css({
			"color":"red"
		})
		$("#error-startdate").html("请输入开始时间和结束时间");
	}
	if($('[name="orderendtime"]').val()==""){
		bool=false;
		$("#error-orderendtime").css({
			"color":"red"
		})
		$("#error-orderendtime").html("请输入预定截止时间");
	}
	if($('[name="orderday"]').val()==""){
		bool=false;
		$("#error-orderday").css({
			"color":"red"
		})
		$("#error-orderday").html("请输入客户需提前几天预定");
	}
	if(bool){
		$("#form1").submit();
	}
}