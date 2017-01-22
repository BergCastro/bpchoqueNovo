package br.com.fireware.bpchoque.repository.def;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import br.com.fireware.bpchoque.model.def.PessoaDef;

public interface PessoaRepository extends JpaRepository<PessoaDef, Long>{

	List<PessoaDef> findAll();
	List<PessoaDef> findByNome(String nome);
	@Query("select p from PessoaDef p where p.id=?1")
	PessoaDef buscaById(Long id);
	
}
