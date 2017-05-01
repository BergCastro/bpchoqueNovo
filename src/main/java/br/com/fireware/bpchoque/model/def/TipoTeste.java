//TESTE_FISICO(COD_TF, DATA_TF, COD_TTF)
package br.com.fireware.bpchoque.model.def;


import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;

import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import javax.persistence.JoinColumn;

import org.springframework.format.annotation.DateTimeFormat;


import lombok.Data;

@Data
@Entity
@Table(name = "tipos_teste")
public class TipoTeste {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss")
	@Column(name = "criadoem")
	private LocalDateTime criadoem;
	
	@Column(name = "criadopor")
	private String criadopor;
	
	
	@DateTimeFormat(pattern = "dd/MM/yyyy  HH:mm:ss")
	@Column(name = "atualizadoem")
	private LocalDateTime atualizadoem;
	
	@Column(name = "atualizadopor")
	private String atualizadopor;
	
	private String nome;
	
	@ManyToMany
	@JoinTable ( name ="tiposteste_provas",
	joinColumns = @JoinColumn ( name ="tipoTesteId"),
	inverseJoinColumns = @JoinColumn ( name ="provaId"))
	private List<Prova> provas;
	
	@PrePersist
	@PreUpdate
	private void prePersistUpdate() {
	
		
	}
	
	

}
