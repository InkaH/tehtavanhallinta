$(document).ready(function() {
	$(window).resize(function() {
		$("#add").animate({
			height : $("#add").get(0).scrollHeight
		}, 500);
	});
});

$(function() {
    $( "#datepicker" ).datepicker();
  });