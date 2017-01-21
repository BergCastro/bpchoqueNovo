package com.algaworks.brewer.repository;


import org.springframework.data.jpa.repository.JpaRepository;


import com.algaworks.brewer.model.Cargo;

public interface CargoRepository extends JpaRepository<Cargo, Long>{

	public Cargo findByNome(String nome);
	
}
