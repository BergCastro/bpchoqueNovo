$('#myModalExclusaoResultado').on('show.bs.modal', function(event) {
	
	var button = $(event.relatedTarget);
	
	var detalheId = button.data('id');
	var detalheNome = button.data('nome');
	
	var modal = $(this);
	var form = modal.find('form');
	var action = form.data('url-base');
	var tipo = form.data('tipo');
	if (!action.endsWith('/')) {
		action += '/';
	}
	form.attr('action', action + detalheId);
	
	modal.find('.modal-body span').html('Tem certeza que deseja excluir '+tipo+'<strong>' + detalheNome + '</strong>?');
//location.reload();
});