package br.com.fireware.bpchoque.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fireware.bpchoque.model.Venda;
import br.com.fireware.bpchoque.repository.helper.venda.VendasQueries;

public interface Vendas extends JpaRepository<Venda, Long>, VendasQueries {

}
