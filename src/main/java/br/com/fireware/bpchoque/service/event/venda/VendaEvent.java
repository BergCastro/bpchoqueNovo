package br.com.fireware.bpchoque.service.event.venda;

import br.com.fireware.bpchoque.model.Venda;

public class VendaEvent {

	private Venda venda;

	public VendaEvent(Venda venda) {
		this.venda = venda;
	}

	public Venda getVenda() {
		return venda;
	}

}
