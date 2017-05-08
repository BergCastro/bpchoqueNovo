var Brewer = Brewer || {};

Brewer.ResultadoCadastro = (function() {

	function ResultadoCadastro() {
		this.modal = $('#modalAdicionaResultado');
		this.botaoSalvar = this.modal
				.find('.js-modal-cadastro-resultado-salvar-btn');
		this.form = this.modal.find('form');
		this.url = this.form.attr('action');
		this.inputParticipante = $('#participante');
		this.inputQtdProvas = $('#qtdProvas');

		this.containerMensagemErro = $('.js-mensagem-cadastro-detalhe');
	}

	ResultadoCadastro.prototype.iniciar = function() {
		this.form.on('submit', function(event) {
			event.preventDefault()
		});
		this.modal.on('shown.bs.modal', onModalShow.bind(this));
		this.modal.on('hide.bs.modal', onModalClose.bind(this))
		this.botaoSalvar.on('click', onBotaoSalvarClick.bind(this));
	}

	function onModalShow() {
		this.inputParticipante.focus();
	}

	function onModalClose() {
		this.inputParticipante.val('');
		
		this.containerMensagemErro.addClass('hidden');
		this.form.find('.form-group').removeClass('has-error');
	}

	function onBotaoSalvarClick() {
		var participante = this.inputParticipante.val().trim();

		var qtdProvas = this.inputQtdProvas.val();

		

		var inputValorProvas = [];
		var inputIdProvas = [];
		
		
		$('.input-prova').each(function() {
			inputValorProvas.push($(this).val());
		});
		$('.input-id-prova').each(function() {
			inputIdProvas.push($(this).val());
		});
		

		$.ajax({
			url : this.url,
			method : 'POST',
			contentType : 'application/json',
			data : JSON.stringify({
				pessoa : participante,
				provas : inputIdProvas,
				valores : inputValorProvas
			}),

			error : onErroSalvandoEstilo.bind(this),
			success : onEstiloSalvo.bind(this)
		});
	}

	function onErroSalvandoEstilo(obj) {
		var mensagemErro = obj.responseText;
		this.containerMensagemErro.removeClass('hidden');
		this.containerMensagemErro.html('<span>' + mensagemErro + '</span>');
		this.form.find('.form-group').addClass('has-error');
	}

	function onEstiloSalvo(estilo) {
		/*var comboEstilo = $('#listaDetalhe');
		comboEstilo.append('<tr style="background:#ffb3b3;">' + '<td>'
				+ estilo.tipo + '</td>' + '<td>' + estilo.quantidade
				+ '.00</td>' + '<td style="text-transform: uppercase;">'
				+ estilo.descricao + '</td>' + '<td class="text-center">'
				+ '<i class="glyphicon glyphicon-floppy-remove"></i>' +

				'</td>' + '</tr>');
		comboEstilo.val(estilo.id);*/
		this.modal.modal('hide');
	}

	return ResultadoCadastro;

}());

$(function() {
	var resultadoCadastro = new Brewer.ResultadoCadastro();
	resultadoCadastro.iniciar();
});
