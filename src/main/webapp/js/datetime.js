/**
 * jedate日期插件
 * 时间范围
 */
var start = {
	format : 'YYYY-MM-DD hh:mm:ss',
	minDate : '2014-06-16 00:00:00', //设定最小日期为当前日期
	festival : true,
	//isinitVal:true,
	maxDate : $.nowDate(0), //最大日期
	choosefun : function(elem, datas) {
		end.minDate = datas; //开始日选好后，重置结束日的最小日期
	}
};
var end = {
	format : 'YYYY-MM-DD hh:mm:ss',
	minDate : '2014-06-16 23:59:59', //设定最小日期为当前日期
	festival : true,
	//isinitVal:true,
	maxDate : $.nowDate(0), //最大日期
	choosefun : function(elem, datas) {
		start.maxDate = datas; //将结束日的初始值设定为开始日的最大日期
	}
};
$("#createtime1").jeDate(start);
$("#createtime2").jeDate(end);

$('#createtime1').click(function() {
	if ($(this).val().trim() == '') {
			$('#jedatebox').find('.jedatehms li:eq(0)').find('input:eq(0)').val('00');
			$('#jedatebox').find('.jedatehms li:eq(1)').find('input:eq(0)').val('00');
			$('#jedatebox').find('.jedatehms li:eq(2)').find('input:eq(0)').val('00');
	}
});
$('#createtime2').click(function() {
	if ($(this).val().trim() == '') {
			$('#jedatebox').find('.jedatehms li:eq(0)').find('input:eq(0)').val('23');
			$('#jedatebox').find('.jedatehms li:eq(1)').find('input:eq(0)').val('59');
			$('#jedatebox').find('.jedatehms li:eq(2)').find('input:eq(0)').val('59');
	}
});