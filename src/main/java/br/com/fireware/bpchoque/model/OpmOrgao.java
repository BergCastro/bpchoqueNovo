//CIA(COD_CIA, NOME_CIA)
package br.com.fireware.bpchoque.model;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import lombok.Data;


@Data
@Entity
@Table(name="opms_orgaos")
public class OpmOrgao{
	
	/**
	 * 
	 */
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name="codigo")
	private Long id;
	@NotEmpty(message="O campo não pode estar vazio!")
	@Length(max=40, message="O campo tem que ter no máximo {max} caracteres")
	@Column(name="nome", nullable=false, length=40, unique=true)
	private String nome;
	
	@Column(name="localizacao", nullable=true, length=60)
	private String localizacao;
	@NotNull(message="Escolha um tipo!")
	@Enumerated(EnumType.STRING)
	private TipoOpm tipo;
	
	
	public enum TipoOpm {
		CIVIL("Civil"),
		MILITAR("Militar");
		
		private String descricao;
		
		TipoOpm(String descricao) {
			this.descricao = descricao;
		}
		
		public String getDescricao() {
			return descricao;
		}
	}
	
	
	
	
}
