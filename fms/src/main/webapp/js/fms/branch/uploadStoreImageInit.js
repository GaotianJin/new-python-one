var operate = null;
var imgUrl = "";
jQuery(function($) {
	/*$(document).ready(function(){
		$('.gallery_demo_unstyled').addClass('gallery_demo'); // adds new class name to maintain degradability
		$('ul.gallery_demo').galleria({
			history   : true, // activates the history object for bookmarking, back-button etc.
			clickNext : true, // helper for making the image clickable
			insert    : '#main_image', // the containing selector for our main image
			onImage   : function(image,caption,thumb) { // let's add some image effects for demonstration purposes
				// fade in the image & caption
				image.css('display','none').fadeIn(1000);
				caption.css('display','none').fadeIn(1000);
				// fetch the thumbnail container
				var _li = thumb.parents('li');
				// fade out inactive thumbnail
				_li.siblings().children('img.selected').fadeTo(500,0.3);
				// fade in active thumbnail
				thumb.fadeTo('fast',1).addClass('selected');
				// add a title for the clickable image
				image.attr('title','Next image >>');
			},
			onThumb : function(thumb) { // thumbnail effects goes here
				// fetch the thumbnail container
				var _li = thumb.parents('li');
				// if thumbnail is active, fade all the way.
				var _fadeTo = _li.is('.active') ? '1' : '0.3';
				// fade in the thumbnail when finnished loading
				thumb.css({display:'none',opacity:_fadeTo}).fadeIn(1500);
				// hover effects
				thumb.hover(
					function() { thumb.fadeTo('fast',1); },
					function() { _li.not('.active').children('img').fadeTo('fast',0.3); } // don't fade out if the parent is active
				)
			}
		});
	});*/
	
	
	operate = $("#uploadImage_operate").val();
	//if(operate=="updStore"){
		getAllStoreImage();
	//}
	if(operate=="detailStore"){
		$("#uploadImage_ImageTableDiv").css("display","none");
	}
	
});


function liOnClick(obj){
	//console.info(obj);
	obj = $(obj) ;
	//console.info(obj);
	//console.info(obj.find("img").attr("src"));
	imgUrl = obj.find("img").attr("src");
	$("#main_image_img").attr('src',imgUrl);
}


function upoloadStoreImage(){
	var storeId = $("#uploadImage_storeId").val();
	var storeImage = $("#uploadFile_storeImage").val();
	if(storeImage==null||storeImage==""||storeImage==undefined){
		$.messager.alert('提示', "请选择需要上传的照片", 'info');
		return;
	}
	//判断此文件是否已经上传
	var param = {};
	param.storeId = storeId;
	param.fileType = "04";
	//param.operate = operate;
	$.ajaxFileUpload({
		type:'post',
		url:contextPath+"/branch/uploadStoreImage?param="+$.toJSON(param),
		fileElementId:'uploadFile_storeImage', 
		dataType:'json',
		success:function(reData,status){
			reData = $.parseJSON(reData)
			try {
				if(reData.success){
					if(reData.obj!=null&&reData.obj!=""&&reData.obj!=undefined){
						imgUrl = reData.obj;
						$("#main_image_img").attr('src',reData.obj);
						$("#allImagelist").append("<li onclick='liOnClick(this)'><img src='"+reData.obj+"'></li>");
					}
					$.messager.alert('提示', "上传成功！");
				}else{
					$.messager.alert('提示', reData.msg);
				}
			} catch (e) {
				$.messager.alert('提示', e);
			}
		}
	});
	
}


function getAllStoreImage(){
	var storeId = $("#uploadImage_storeId").val();
	var param = {};
	param.storeId = storeId;
	param.fileType = "04";
	$.ajax({
		type:'post',
		url:contextPath+"/branch/getAllStoreImage",
		data:'param='+encodeURI($.toJSON(param)),
		cache:false,
		success:function(reData){
			try {
				if(reData.success){
					var obj = reData.obj;
					if(obj!=null&&obj.length>0){
						for(var i=0;i<obj.length;i++){
							if(i==0){
								imgUrl = obj[i].storePhoto;
								$("#main_image_img").attr('src',obj[i].storePhoto);
							}
							$("#allImagelist").append("<li onclick='liOnClick(this)'><img src='"+obj[i].storePhoto+"'></li>");
						}
					}else{
						imgUrl = null;
					}
				}
			} catch (e) {
				$.messager.alert('提示', e);
			}
		}
	});
}

function deleteStoreImage(){
	var storeId = $("#uploadImage_storeId").val();
	var param = {};
	param.storeId = storeId;
	param.imgUrl = imgUrl;
	param.fileType = "04";
	$.ajax({
		type:'post',
		url:contextPath+"/branch/deleteStoreImage",
		data:'param='+encodeURI($.toJSON(param)),
		cache:false,
		success:function(reData){
			try {
				if(reData.success){
					$.messager.alert('提示', reData.msg);
					$("#allImagelist").find("li").remove();
					$("#main_image_img").attr('src',contextPath+"/img/unUploadStoreImage.jpg");
					getAllStoreImage();
				}
			} catch (e) {
				$.messager.alert('提示', e);
			}
		}
	});
}

function deleteLI(){
	//console.info("0000000000000000");
	//console.info($("#allImagelist"));
	//$("#allImagelist").find("li").remove();
	//console.info($("#allImagelist"));
}
