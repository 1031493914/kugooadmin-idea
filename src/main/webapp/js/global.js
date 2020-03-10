/**
 * 全局js设置
 * @author czf
 */
var imageServerUrl='http://47.75.168.210:8888/';
var defaultImage='adminimg.jpg';
$(function() {
	$("img[src]").each(function (i) {
		if($(this).attr("src").trim()==''||$(this).attr("src").trim()==imageServerUrl
				||$(this).attr("src").trim()==imageServerUrl+defaultImage){
			$(this).attr("src",imageServerUrl+defaultImage);
			$(this).next('.bg-red-light').addClass("hidden");
		};
		$(this).attr("width","120px");
		$(this).attr("height","100px");
	})
});