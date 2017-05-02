package br.com.fireware.bpchoque.repository.def;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fireware.bpchoque.model.def.Doacao;
import br.com.fireware.bpchoque.model.def.DoacaoDetalhe;
import br.com.fireware.bpchoque.model.def.Prova;


public interface ProvaRepository extends JpaRepository<Prova, Long> {
	
	
	

}
