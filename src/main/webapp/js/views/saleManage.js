/**
 * @author czf
 */
$(function() {
	// 全选/全不选
	$("#allSeleted").toggle(function() {
		$("input").attr("checked", "checked");
	}, function() {
		$("input").removeAttr("checked");
	});
})
// 后台下架处理
function down() {
	// 获取checkbox的值
	var sel = document.getElementsByName("id");
	var str = "";
	for (var i = 0; i < sel.length; i++){
		if (sel[i].checked == true) {
			str += sel[i].value + ",";
		}
	}
	if (str == "" || str == null) {
		alert("请至少选择一条记录");
		return false;
	}
	if (window.confirm("确定删除吗？")) {
		$.get("downSalesman", {
			'ids' : str
		}, function(data) {
			if (data) {
				alert('删除成功');
				window.location.href = "saleManage";
				return;
			}
		});
	}
}