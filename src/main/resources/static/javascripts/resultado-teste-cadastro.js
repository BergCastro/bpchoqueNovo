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
			var input = $(this).val().replace('"', '.');
			inputValorProvas.push(input);
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
		var corpoTabela = $('#resultsBlock');
		/*corpoTabela.html('<div  id="1" class="tab-pane fade in active">'+

							'<div class="table-responsive">'+
								'<table'+
									'class="table  table-hover table-condensed table-striped table-resultados">'+
									'<thead>'+
										'<tr>'+
											'<th>MATRÍCULA</th>
											<th>NOME</th>
											<th th:each="prova, iterProvas : ${tipo.provas}"><span
												th:utext="${prova.nome}"></span></th>
											<th>NOTA</th>
											<th></th>
										</tr>
									</thead>

									<tbody id="listaDetalhe">

										<tr th:each="resultado : ${resultados}">
											
											<td th:text="${resultado.pessoa.matricula}" th:if="${resultado.tipoTeste.id}==${tipo.id}">Matricula</td>
											<td th:text="${resultado.pessoa.nome}" th:if="${resultado.tipoTeste.id}==${tipo.id}">Nome</td>
											
											<td th:text="${resultado.valorProva1}"
												th:if="${tipo.qtdProvas}>=1 and ${resultado.tipoTeste.id}==${tipo.id} and ${resultado.tipoPontuacaoProva1}=='NOTA'">Nome</td>
											<td th:text="(${resultado.valorProva1}==1 ? APTO : INAPTO)"
												th:if="${tipo.qtdProvas}>=1 and ${resultado.tipoTeste.id}==${tipo.id} and ${resultado.tipoPontuacaoProva1}=='APTOINAPTO'">Nome</td>
											<td th:text="${#strings.replace(resultado.valorProva1, '.', '&#8221')}"
												th:if="${tipo.qtdProvas}>=1 and ${resultado.tipoTeste.id}==${tipo.id} and ${resultado.tipoPontuacaoProva1}=='TEMPO'">Nome</td>
											
											<td th:text="${resultado.valorProva2}"
												th:if="${tipo.qtdProvas}>=2 and ${resultado.tipoTeste.id}==${tipo.id} and ${resultado.tipoPontuacaoProva2}=='NOTA'"  >Nome</td>
											<td th:text="(${resultado.valorProva2}==1 ? APTO : INAPTO)"
												th:if="${tipo.qtdProvas}>=1 and ${resultado.tipoTeste.id}==${tipo.id} and ${resultado.tipoPontuacaoProva2}=='APTOINAPTO'">Nome</td>
											<td th:text="${#strings.replace(resultado.valorProva2, '.', '&#8221')}"
												th:if="${tipo.qtdProvas}>=1 and ${resultado.tipoTeste.id}==${tipo.id} and ${resultado.tipoPontuacaoProva2}=='TEMPO'">Nome</td>
											
											<td th:text="${resultado.valorProva3}"
												th:if="${tipo.qtdProvas}>=3 and ${resultado.tipoTeste.id}==${tipo.id} and ${resultado.tipoPontuacaoProva3}=='NOTA'">Nome</td>
											<td th:text="(${resultado.valorProva3}==1 ? APTO : INAPTO)"
												th:if="${tipo.qtdProvas}>=1 and ${resultado.tipoTeste.id}==${tipo.id} and ${resultado.tipoPontuacaoProva3}=='APTOINAPTO'">Nome</td>
											<td th:text="${#strings.replace(resultado.valorProva3, '.', '&#8221')}"
												th:if="${tipo.qtdProvas}>=1 and ${resultado.tipoTeste.id}==${tipo.id} and ${resultado.tipoPontuacaoProva3}=='TEMPO'">Nome</td>
											
											<td th:text="${resultado.valorProva4}"
												th:if="${tipo.qtdProvas}>=4 and ${resultado.tipoTeste.id}==${tipo.id} and ${resultado.tipoPontuacaoProva4}=='NOTA'">Nome</td>
											<td th:text="(${resultado.valorProva4}==1 ? APTO : INAPTO)"
												th:if="${tipo.qtdProvas}>=1 and ${resultado.tipoTeste.id}==${tipo.id} and ${resultado.tipoPontuacaoProva4}=='APTOINAPTO'">Nome</td>	
											<td th:text="${#strings.replace(resultado.valorProva4, '.', '&#8221')}"
												th:if="${tipo.qtdProvas}>=1 and ${resultado.tipoTeste.id}==${tipo.id} and ${resultado.tipoPontuacaoProva4}=='TEMPO'">Nome</td>
											
												
											<td th:text="${resultado.valorProva5}"
												th:if="${tipo.qtdProvas}>=5 and ${resultado.tipoTeste.id}==${tipo.id} and ${resultado.tipoPontuacaoProva5}=='NOTA'">Nome</td>
											<td th:text="(${resultado.valorProva5}==1 ? APTO : INAPTO)"
												th:if="${tipo.qtdProvas}>=1 and ${resultado.tipoTeste.id}==${tipo.id} and ${resultado.tipoPontuacaoProva5}=='APTOINAPTO'">Nome</td>
											<td th:text="${#strings.replace(resultado.valorProva5, '.', '&#8221')}"
												th:if="${tipo.qtdProvas}>=1 and ${resultado.tipoTeste.id}==${tipo.id} and ${resultado.tipoPontuacaoProva5}=='TEMPO'">Nome</td>
											

											<td th:if="${resultado.tipoTeste.id}==${tipo.id}">Nota</td>
											<td class="text-center" th:if="${resultado.tipoTeste.id}==${tipo.id}"><a class="btn btn-link btn-xs"
												data-toggle="modal" data-target="#myModalExclusaoResultado"
												th:attr="data-id=${resultado.id}, data-nome=${resultado.pessoa.nome}"
												title="Excluir" rel="tooltip" data-placement="top"> <span
													class="glyphicon glyphicon-remove"></span>
											</a></td>
										</tr>

									</tbody>
								</table>
								<div class="bw-field-action__icon">
									<a href="#" data-toggle="modal"
										data-target="#modalAdicionaResultado"
										th:attr="data-tipo=${tipo.id}"> <i
										class="glyphicon  glyphicon-plus-sign"></i> <span
										class="hidden-xs  hidden-sm">Adicionar Resultado</span>
									</a>
								</div>
								<br>
							</div>
						</div>');
*/		
		this.modal.modal('hide');
		$("#resultsBlock").load('/testesFisicos/resultados');
		
		console.log();
	//	location.reload();
	}

	return ResultadoCadastro;

}());

$(function() {
	var resultadoCadastro = new Brewer.ResultadoCadastro();
	resultadoCadastro.iniciar();
});
