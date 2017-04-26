var Brewer = Brewer || {};

Brewer.DetalheCadastro = (function() {
	
	function DetalheCadastro() {
		this.modal = $('#modalCadastroItemDoacao');
		this.botaoSalvar = this.modal.find('.js-modal-cadastro-detalhe-salvar-btn');
		this.form = this.modal.find('form');
		this.url = this.form.attr('action');
		this.inputTipo = $('#tipo');
		this.inputQuantidade = $('#quantidade');
		this.inputDescricao = $('#descricao');
		this.containerMensagemErro = $('.js-mensagem-cadastro-detalhe');
	}
	
	DetalheCadastro.prototype.iniciar = function() {
		this.form.on('submit', function(event) { event.preventDefault() });
		this.modal.on('shown.bs.modal', onModalShow.bind(this));
		this.modal.on('hide.bs.modal', onModalClose.bind(this))
		this.botaoSalvar.on('click', onBotaoSalvarClick.bind(this));
	}
	
	function onModalShow() {
		this.inputTipo.focus();
	}
	
	function onModalClose() {
		this.inputTipo.val('');
		this.inputQuantidade.val('');
		this.inputDescricao.val('');
		this.containerMensagemErro.addClass('hidden');
		this.form.find('.form-group').removeClass('has-error');
	}
	
	function onBotaoSalvarClick() {
		var tipo = this.inputTipo.val().trim();
		var quantidade = this.inputQuantidade.val();
		var descricao = this.inputDescricao.val();
		$.ajax({
			url: this.url,
			method: 'POST',
			contentType: 'application/json',
			data: JSON.stringify({ 
				tipo: tipo,
				quantidade: quantidade,
				descricao: descricao
				}
				}),
			}
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
		var comboEstilo = $('#estilo');
		comboEstilo.append('<option value=' + estilo.id + '>' + estilo.nome + '</option>');
		comboEstilo.val(estilo.id);
		this.modal.modal('hide');
	}
	
	return DetalheCadastro;
	
}());

$(function() {
	var estiloCadastroRapido = new Brewer.DetalheCadastro();
	estiloCadastroRapido.iniciar();
});
