//TESTE_FISICO(COD_TF, DATA_TF, COD_TTF)
package br.com.fireware.bpchoque.model.def;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonValue;


import br.com.fireware.bpchoque.model.def.Doacao.DoacaoTipo;
import br.com.fireware.bpchoque.security.UsuarioSistema;
import lombok.Data;

@Data
@Entity
@Table(name = "provas_teste")
public class Prova {

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
	
	@NotNull(message="Um tipo deve ser selecionado!")
	@Enumerated(EnumType.STRING)
	private CampoTipo tipo;
	
	//private BigDecimal valorProva;
	
	private BigDecimal refInicialMasc;
	
	private BigDecimal refFinalMasc;
	
	private BigDecimal refInicialFem;
	
	private BigDecimal refFinalFem;
	
	private BigDecimal intervaloRef;
	
	private Integer idadeInicial;
	
	private Integer idadeFinal;
	
	private Integer intervaloIdade;
	
	
	

	//@OneToMany(orphanRemoval=true, mappedBy="doacao")
	//private List<DoacaoDetalhe> detalhes;

	//private double valor;
	
	@PrePersist
	@PreUpdate
	private void prePersistUpdate() {
		
		
	}
	
	public enum CampoTipo {
		NOTA("Nota"),
		APTOINAPTO("Apto / Inapto");
		
		
		
		private String descricao;
		
		CampoTipo(String descricao) {
			this.descricao = descricao;
		}
		
		public String getDescricao() {
			return descricao;
		}
		
			}

}
