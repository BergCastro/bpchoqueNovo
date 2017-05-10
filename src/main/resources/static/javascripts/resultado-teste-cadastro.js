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

	function onEstiloSalvo() {
		var corpoTabela = $('#listaDetalhe');
		/*corpoTabela.html('<tr th:each="resultado : ${resultados}">'+
											
											'<td th:text="${resultado.pessoa.matricula}" th:if="${resultado.tipoTeste.id}==${tipo.id}">Matricula</td>'+
											'<td th:text="${resultado.pessoa.nome}" th:if="${resultado.tipoTeste.id}==${tipo.id}">Nome</td>'+
											'<td th:text="${resultado.valorProva1}"'+
											'	th:if="${tipo.qtdProvas}>=1 and ${resultado.tipoTeste.id}==${tipo.id}">Nome</td>'+
											'<td th:text="${resultado.valorProva2}"'+
											'	th:if="${tipo.qtdProvas}>=2 and ${resultado.tipoTeste.id}==${tipo.id}"  >Nome</td>'+
											'<td th:text="${resultado.valorProva3}"'+
											'	th:if="${tipo.qtdProvas}>=3 and ${resultado.tipoTeste.id}==${tipo.id}">Nome</td>'+
											'<td th:text="${resultado.valorProva4}"'+
											'	th:if="${tipo.qtdProvas}>=4 and ${resultado.tipoTeste.id}==${tipo.id}">Nome</td>'+
											'<td th:text="${resultado.valorProva5}"'+
											'	th:if="${tipo.qtdProvas}>=5 and ${resultado.tipoTeste.id}==${tipo.id}">Nome</td>'+
											

											'<td th:if="${resultado.tipoTeste.id}==${tipo.id}">Nota</td>'+
											'<td class="text-center" th:if="${resultado.tipoTeste.id}==${tipo.id}"><a class="btn btn-link btn-xs"'+
											'	data-toggle="modal" data-target="#myModalExclusaoResultado"'+
											'	th:attr="data-id=${resultado.id}, data-nome=${resultado.pessoa.nome}"'+
											'	title="Excluir" rel="tooltip" data-placement="top"> <span'+
											'		class="glyphicon glyphicon-remove"></span>'+
											'</a></td>'+
										'</tr>');*/
		
		this.modal.modal('hide');
		location.reload();
	}

	return ResultadoCadastro;

}());

$(function() {
	var resultadoCadastro = new Brewer.ResultadoCadastro();
	resultadoCadastro.iniciar();
});
