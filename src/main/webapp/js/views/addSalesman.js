/**
 * @author czf
 */
$('input').each(function () {
	$(this).focus(function () {
		$("span.error-title").html('');
	})
})
function error(th,formid){
	var bool=true;
	var name=$.trim($('[name=name]').val());
	var mobile=$.trim($('[name=mobile]').val());
	var companyid=$.trim($('[name=companyid]').val());
	if (name == "") {
		$('[name=name]').parent().next('.text-danger').find('.error-title').html('请输入销售人员姓名');
		bool=false;
	}
	if (mobile == "") {
		$('[name=mobile]').parent().next('.text-danger').find('.error-title').html('请输入销售人员手机号');
		bool=false;
	}
	if(mobile!="" && !(/^1(3|4|5|7|8)\d{9}$/.test(mobile))){ 
		$('[name=mobile]').parent().next('.text-danger').find('.error-title').html("手机号码有误，请重填");  
		 bool=false;
    } 
	if (companyid == "") {
		$('[name=companyid]').parent().next('.text-danger').find('.error-title').html('请输入销售人员所属分公司');
		bool=false;
	}
	if(bool){
		th.attr('disabled','disabled');
		$("#"+formid).submit();
		return false;
	}
	return false;
}
