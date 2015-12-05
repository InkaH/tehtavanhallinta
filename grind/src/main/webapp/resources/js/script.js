$(document).ready(function() {
	
	/* Keep scroll position over page refresh */
	(function(b){window.onbeforeunload=function(a){window.name+=" ["+b(window).scrollTop().toString()+"["+b(window).scrollLeft().toString()};b.maintainscroll=function(){if(0<window.name.indexOf("[")){var a=window.name.split("[");window.name=b.trim(a[0]);window.scrollTo(parseInt(a[a.length-1]),parseInt(a[a.length-2]))}};b.maintainscroll()})(jQuery);
	
	/*
	$(window).resize(function() {
		$("#add").removeClass("collapse in").addClass("collapse");
	});
	*/

	$("#datepicker").datepicker();
	
	$("#timepicker").timepicker();

	$('#newTask textarea').on('change invalid', function() {
		var textfield = $(this).get(0);
		textfield.setCustomValidity('');
		if (!textfield.validity.valid) {
			textfield.setCustomValidity('Pakollinen tieto puuttuu');
		}
	});
	
	var $counterText = $('<div class="character-counter" />');
    $('#tiedot').after($counterText).bind('keyup blur', function () {
        $counterText.text($(this).val().length + ' / 1000');
    }).keyup();
    
    $('.tiedot').click(function (e) {
    	$(this).next().toggle();
    });
    
});
