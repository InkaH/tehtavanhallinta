$(document).ready(function() {
	
	$(window).scroll(function (event) {
	    var scroll = $(window).scrollTop();
	    // Do something
	});
	
	$(window).resize(function() {
		$("#add").removeClass("collapse in").addClass("collapse");
	});

	$("#datepicker").datepicker();
	
	$("#timepicker").timepicker();

	$('#uusiTehtava input[type=text]').on('change invalid', function() {
		var textfield = $(this).get(0);
		textfield.setCustomValidity('');
		if (!textfield.validity.valid) {
			textfield.setCustomValidity('Aihe on pakollinen tieto');
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
