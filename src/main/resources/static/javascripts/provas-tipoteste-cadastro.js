var Brewer = Brewer || {};

Brewer.ProvaTipoCadastro = (function() {
	
	function ProvaTipoCadastro() {
		this.modal = $('#modalCadastroProvaTipo');
		this.botaoSalvar = this.modal.find('.js-modal-cadastro-prova-salvar-btn');
		this.form = this.modal.find('form');
		this.url = this.form.attr('action');
		this.inputProva = $('#prova');
		
		
		this.containerMensagemErro = $('.js-mensagem-cadastro-prova');
	}
	
	ProvaTipoCadastro.prototype.iniciar = function() {
		this.form.on('submit', function(event) { event.preventDefault() });
		this.modal.on('shown.bs.modal', onModalShow.bind(this));
		this.modal.on('hide.bs.modal', onModalClose.bind(this))
		this.botaoSalvar.on('click', onBotaoSalvarClick.bind(this));
	}
	
	function onModalShow() {
		this.inputProva.focus();
	}
	
	function onModalClose() {
		//this.inputTipo.val('');
		
		
		this.containerMensagemErro.addClass('hidden');
		this.form.find('.form-group').removeClass('has-error');
	}
	
	function onBotaoSalvarClick() {
		var id = this.inputProva.val().trim();
		
		$.ajax({
			url: this.url,
			method: 'POST',
			contentType: 'application/json',
			data: JSON.stringify({id: id}),
			
			error: onErroSalvandoEstilo.bind(this),
			success: onEstiloSalvo.bind(this)
		});
	}
	
	function onErroSalvandoEstilo(obj) {
		var mensagemErro = obj.responseText;
		this.containerMensagemErro.removeClass('hidden');
		this.containerMensagemErro.html('<span>' + mensagemErro + '</span>');
		this.form.find('.form-group').addClass('has-error');
	}
	
	function onEstiloSalvo(estilo) {
		this.modal.modal('hide');
		var corpoTabela = $('#tabelaProvasBlock');
		$("#tabelaProvasBlock").load('/tiposTeste/atualizaProvas');
		//$.getScript('/layout/javascripts/algaworks.min.js');
		
		
		/*var comboEstilo = $('#listaDetalhe');
		comboEstilo.append(
				           '<tr style="background:#ffb3b3;">'+
								'<td>'+estilo.nome+'</td>'+
								'<td>'+estilo.descricao+'.00</td>'+
								'<td style="text-transform: uppercase;">'+estilo.descricao+'</td>'+
								'<td class="text-center">'+
									 '<i class="glyphicon glyphicon-floppy-remove"></i>'+
									 
									
								'</td>'+
								'</tr>');
		comboEstilo.val(estilo.id);*/
		
		//location.reload();
	}
	
	return ProvaTipoCadastro;
	
}());




$(function() {
	var detalheCadastro = new Brewer.ProvaTipoCadastro();
	detalheCadastro.iniciar();
});

(function($) {
	  remove = function(item) {
	    var tr = $(item).closest('tr');

	    tr.fadeOut(400, function() {
	      tr.remove();  
	    });

	    return false;
	  }
	})(jQuery);
