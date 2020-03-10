/**
 * 系统设置
 * @author czf
 */
var ImgLocation='http://101.37.69.236:8888/';
var defaultImg='adminimg.jpg';
$(function() {
	$("img[src]").each(function (i) {
		if($(this).attr("src").trim()==''||$(this).attr("src").trim()==ImgLocation
				||$(this).attr("src").trim()==ImgLocation+defaultImg){
			$(this).attr("src",ImgLocation+defaultImg);
			$(this).next('.bg-red-light').addClass("hidden");
		};
		$(this).attr("width","120px");
		$(this).attr("height","100px");
	})
});