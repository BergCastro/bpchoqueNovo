//TESTE_FISICO(COD_TF, DATA_TF, COD_TTF)
package br.com.fireware.bpchoque.model.def;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;

import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
@Entity
@Table(name = "doacoes")
public class Doacao {

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
	
	// @NotNull(message="O campo não pode estar vazio!")
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Column(columnDefinition = "DATE")
	private LocalDate dataDoacao;

	private String doador;

	//@OneToMany(orphanRemoval=true, mappedBy="doacao")
	//private List<DoacaoDetalhe> detalhes;

	//private double valor;
	
	
	
	public enum DoacaoTipo {
		OBJETO("Objeto"),
		VALOR("Valor");
		
		private String descricao;
		
		DoacaoTipo(String descricao) {
			this.descricao = descricao;
		}
		
		public String getDescricao() {
			return descricao;
		}
	}

}
