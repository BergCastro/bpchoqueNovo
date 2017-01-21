package br.com.fireware.bpchoque.repository.helper.cerveja;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.fireware.bpchoque.dto.CervejaDTO;
import br.com.fireware.bpchoque.dto.ValorItensEstoque;
import br.com.fireware.bpchoque.model.Cerveja;
import br.com.fireware.bpchoque.repository.filter.CervejaFilter;

public interface CervejasQueries {

	public Page<Cerveja> filtrar(CervejaFilter filtro, Pageable pageable);
	
	public List<CervejaDTO> porSkuOuNome(String skuOuNome);
	
	public ValorItensEstoque valorItensEstoque();
	
}
