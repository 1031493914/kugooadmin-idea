/**
 * @author czf
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
var start1 = {
	    format : 'YYYY-MM-DD hh:mm:ss',
	    minDate : '2017-05-20 00:00:00', //设定最小日期为当前日期
	    festival : true,
	    //isinitVal:true,
	    //maxDate : $.nowDate(0), //最大日期
	    choosefun : function(elem, datas) {
	        end1.minDate = datas; //开始日选好后，重置结束日的最小日期
	    }
};
var end1 = {
	    format : 'YYYY-MM-DD hh:mm:ss',
	    minDate : '2017-05-20 23:59:59', //设定最小日期为当前日期
	    festival : true,
	    //isinitVal:true,
	    //maxDate : $.nowDate(0), //最大日期
	    choosefun : function(elem, datas) {
	        start1.maxDate = datas; //将结束日的初始值设定为开始日的最大日期
	    }
};
$("#createtime1").jeDate(start);
$("#createtime2").jeDate(end);
$("#createtime3").jeDate(start1);
$("#createtime4").jeDate(end1);

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
$('#createtime3').click(function() {
	if ($(this).val().trim() == '') {
			$('#jedatebox').find('.jedatehms li:eq(0)').find('input:eq(0)').val('00');
			$('#jedatebox').find('.jedatehms li:eq(1)').find('input:eq(0)').val('00');
			$('#jedatebox').find('.jedatehms li:eq(2)').find('input:eq(0)').val('00');
	}
});
$('#createtime4').click(function() {
	if ($(this).val().trim() == '') {
			$('#jedatebox').find('.jedatehms li:eq(0)').find('input:eq(0)').val('23');
			$('#jedatebox').find('.jedatehms li:eq(1)').find('input:eq(0)').val('59');
			$('#jedatebox').find('.jedatehms li:eq(2)').find('input:eq(0)').val('59');
	}
});