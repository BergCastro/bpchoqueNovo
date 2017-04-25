package br.com.fireware.bpchoque.model.def;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.fireware.bpchoque.model.def.Doacao.DoacaoTipo;
import lombok.Data;

@Data
@Entity
@Table(name = "doacoes_detalhes")
public class DoacaoDetalhe {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	// @NotNull(message="O campo não pode estar vazio!")
	@Enumerated(EnumType.STRING)
	private DoacaoTipo tipo;

	private String descricao;
	
	private BigDecimal quantidade;
	
	@ManyToOne
	private Doacao doacao;

}
