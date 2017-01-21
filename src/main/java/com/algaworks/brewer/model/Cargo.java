//CARGO(COD_CARGO, NOME_CARGO, ABREVIACAO_CARGO)

package com.algaworks.brewer.model;



import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;



import lombok.Data;
@Data
@Entity
@Table(name="cargos")
public class Cargo implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name="COD_CARGO")
	private Long id;
	@NotEmpty(message="O campo não pode estar vazio")
	@Length(max=15, message="A patente tem que ter no máximo {max} caracteres")
	@Column(name="NOME_CARGO", nullable= false, length=15, unique=true)
	private String nome;
	@NotEmpty(message="O campo não pode estar vazio")
	//@Length(max=10, message="A patente tem que ter no máximo {max} caracteres")
	/*@Digits(integer = 8, fraction = 0, message = "O formato da data está errado ex.: 12/12/2012")
	@Min(value = 8, message = "O formato da data está errado ex.: 12/12/2012")
    @Max(value = 8, message = "O formato da data está errado ex.: 12/12/2012")*/
	@Column(name="ABREVIACAO_CARGO", nullable= false, length=10, unique=true)
	private String abreviacao;
	
	
		

	
	
	
	
	
	
	
	
}
