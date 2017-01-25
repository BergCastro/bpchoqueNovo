package br.com.fireware.bpchoque.repository.def;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


import br.com.fireware.bpchoque.model.def.Avaliador;


public interface AvaliadorRepository extends JpaRepository<Avaliador, Long> {
	
	List<Avaliador> findAll();
	

}
