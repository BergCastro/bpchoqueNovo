$(function () {
    var inputs  = $('.referencia');
    var labelRef = $('.labelref');
    var inputsIdades  = $('.idade');
    var tipo = $('#tipo');
    var valorInputs = [];
    var refInicial = $('#refInicialMasc');
    
    if(tipo.val() == 'APTOINAPTO'){
    	inputs.addClass('hide');
    	labelRef.addClass('hide');
    	inputsIdades.addClass('hide');
    }
    if(tipo.val() == 'TEMPO_MIN'){
    	inputs.addClass('js-time-min');
   	 	$.getScript('/javascripts/formata-campo-tempo.js');
    }
    if(tipo.val() == 'TEMPO'){
    	inputs.addClass('js-time');
   	 	$.getScript('/javascripts/formata-campo-tempo.js');
    }
    
   /* $("form").submit(function(){
    
    	
    	
    		$('.referencia').each(function() {
    			
    			var input = $(this).val().replace(/\D/g, '');
    			$(this).val(input);
    		});	
        
    });*/
    
    $('#tipo').on('change', function (event) {
        
        if(tipo.val() == 'TEMPO'){
        	inputs.removeClass('hide');
        	labelRef.removeClass('hide');
        	inputsIdades.removeClass('hide');
        	inputs.removeClass('js-time-min');
        	inputs.removeClass('js-plain');
        	inputs.addClass('js-time');
        	
        }
        if(tipo.val() == 'TEMPO_MIN'){
        	inputs.removeClass('hide');
        	labelRef.removeClass('hide');
        	inputsIdades.removeClass('hide');
        	inputs.removeClass('js-time');
        	inputs.removeClass('js-plain');
        	inputs.addClass('js-time-min');
        	 $.getScript('/javascripts/formata-campo-tempo.js');
        	
        }
        if(tipo.val() == 'INTEIRO'){
        	inputs.removeClass('hide');
        	labelRef.removeClass('hide');
        	inputsIdades.removeClass('hide');
        	inputs.removeClass('js-time-seg');
        	inputs.removeClass('js-time');
        	inputs.addClass('js-plain');
        	$.getScript('/javascripts/brewer.js');
        	
        }
        if(tipo.val() == 'APTOINAPTO'){
        	inputs.removeClass('js-time-seg');
        	inputs.removeClass('js-time');
        	inputs.removeClass('js-plain');
        	inputs.addClass('hide');
        	labelRef.addClass('hide');
        	inputsIdades.addClass('hide');
        	
        }
        
        inputs.val('');
        $.getScript('/javascripts/formata-campo-tempo.js');
        $.getScript('/javascripts/brewer.js');
        console.log(tipo.val())
    });

});