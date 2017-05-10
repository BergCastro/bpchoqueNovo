//TESTE_FISICO(COD_TF, DATA_TF, COD_TTF)
package br.com.fireware.bpchoque.model.def;


import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Transient;
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
	
	private String descricao;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable ( name ="tiposteste_provas",
	joinColumns = @JoinColumn ( name ="tipoTesteId"),
	inverseJoinColumns = @JoinColumn ( name ="provaId"))
	private List<Prova> provas;
	
	/*@OneToMany(mappedBy="tipoTeste", fetch = FetchType.EAGER)
	private List<ResultadoTeste> resultados;*/
	
	@Transient
	private Integer qtdProvas;
	
	public Integer getQtdProvas(){
		return provas.size();
	}
	
	@PrePersist
	@PreUpdate
	private void prePersistUpdate() {
		nome = nome.toUpperCase();
		descricao = descricao.toUpperCase();
		
	}
	
	

}
