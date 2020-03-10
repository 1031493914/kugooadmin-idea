/**
 * @author czf
 */
//活动图片flash上传
$(function(){
	
 	//商品详情页图片
	new FileUpload({
		button_placeholder_id : "pictureInfo",
		post_params: {},
		onStart: function(filename){
			
		},
		onSuccess: function(file,data){
			if(data.error != 0){
				
				alert(data.msg);
				return;
			}
			$('input[name="pictureInfoRomImg"][value=""]').each(function (i) {
				if(i==0){
					$(this).val(data.path);
					$(this).next("img").attr('src',imageServerUrl+data.path);
					$(this).prev().val(data.height);
					$(this).prev().prev().val(data.width);
					$(this).next().next().removeClass("hidden");
				}
			});
			
		}
	});
	$('[name="pictureInfoRomImg"]').parent().parent().find(".bg-red-light").each(function () {
		$(this).click(function () {
			$(this).prev("img").attr('src',imageServerUrl+defaultImage);
			$(this).prev("img").prev().val("");
			$(this).prev("img").prev().prev().val("");
			$(this).prev("img").prev().prev().prev().val("");
		});
	});
	//layer插件显示图片预览
	layer.ready(function(){
		$("[id^=pictureInfoImgDiv]").each(function () {
			layer.photos({
				 photos: $(this)
		 		,anim: 5 //0-6的选择，指定弹出图片动画类型，默认随机（请注意，3.0之前的版本用shift参数）
			}); 
			
		});
	});
});
//商品图片flash上传
$(function () {
	//商品轮播页图片
	new FileUpload({
		button_placeholder_id : "img",
		post_params: {width : 640,height :622},
		onStart: function(filename){
		},
		onSuccess: function(file,data){
			if(data.error != 0){
				alert(data.msg);
				return;
			}
			$('input[name="romImg"][value=""]').each(function (i) {
				if(i==0){
					$(this).val(data.path);
					$(this).next("img").attr('src',imageServerUrl+data.path);
					$(this).prev().val(data.height);
					$(this).prev().prev().val(data.width);
					$(this).next().next().removeClass("hidden");
				}
			});
		}
	});
	$('[name="romImg"]').parent().parent().find(".bg-red-light").each(function () {
		$(this).click(function () {
			$(this).prev("img").attr('src',imageServerUrl+defaultImage);
			$(this).prev("img").prev().val("");
			$(this).prev("img").prev().prev().val("");
			$(this).prev("img").prev().prev().prev().val("");
		});
	});
});


