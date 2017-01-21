package com.algaworks.brewer.model;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;
@Data
@Entity
@Table(name = "usuario_grupo")
public class UsuarioGrupo {

	@EmbeddedId
	private UsuarioGrupoId id;

	

	

}
