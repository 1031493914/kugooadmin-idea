/**
 * 分页POST方式提交
 * @author czf
 * @since 2017.03.10
 */
$(".pagelist").find("a[href!='javascript:;']").each(function(i) {
	$(this).click(function () {
		$("#myform").attr("action",$("#myform").attr("action")
				+"?cp="+$(this).attr("href").split("cp=")[1]);
		$("#myform").submit();
		return false;
	});
});