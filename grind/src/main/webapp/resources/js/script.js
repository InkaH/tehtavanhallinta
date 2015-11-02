$(document).ready(function(){
	$("#add").click(function(){
		$("#add").animate({height:$("#add").get(0).scrollHeight},500);
	});
	/*$(window).resize(function(){$("#add").animate({height:$("#add").get(0).scrollHeight},500);});*/
});