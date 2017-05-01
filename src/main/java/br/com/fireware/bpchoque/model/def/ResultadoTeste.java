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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "resultados_teste")
public class ResultadoTeste {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="pessoa")
	private PessoaDef pessoa;
	
	@ManyToOne
	@JoinColumn(name="teste")
	private TesteFisico teste;
	
	@ManyToOne
	@JoinColumn(name="prova")
	private Prova prova;
	
	@ManyToOne
	@JoinColumn(name="tipoTeste")
	private TipoTeste tipoTeste;
	
	private BigDecimal valor;
	
	
	

	
	@PrePersist
	@PreUpdate
	private void prePersistUpdate() {
		
		
	}
	
	

}
