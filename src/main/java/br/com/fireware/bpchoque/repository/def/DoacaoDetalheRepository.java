package br.com.fireware.bpchoque.repository.def;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;



import br.com.fireware.bpchoque.model.def.DoacaoDetalhe;


public interface DoacaoDetalheRepository extends JpaRepository<DoacaoDetalhe, Long> {
	
	List<DoacaoDetalhe> findAll();
	

}
