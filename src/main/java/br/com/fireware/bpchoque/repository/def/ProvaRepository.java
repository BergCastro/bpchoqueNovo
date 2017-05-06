package br.com.fireware.bpchoque.repository.def;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


import br.com.fireware.bpchoque.model.def.Prova;
import br.com.fireware.bpchoque.model.def.TesteFisico;
import br.com.fireware.bpchoque.model.def.TipoTeste;


public interface ProvaRepository extends JpaRepository<Prova, Long> {
	
	

}
