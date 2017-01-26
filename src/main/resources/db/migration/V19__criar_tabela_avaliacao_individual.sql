CREATE TABLE avaliadores(
	id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	criadoem DATETIME DEFAULT CURRENT_TIMESTAMP,
    criadopor VARCHAR(50),
    atualizadoem DATETIME,
    atualizadopor VARCHAR(50),
	pessoadef BIGINT(20) NOT NULL,
	cref VARCHAR(15),
	escolaridade VARCHAR(20),
	FOREIGN KEY (pessoadef) REFERENCES pessoasdef(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO avaliadores (criadopor, pessoadef, cref, escolaridade) VALUES ('fireberg2500@hotmail.com',18, '123123', 2);


CREATE TABLE avaliacoes_individuais (
    id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
    criadoem DATETIME DEFAULT CURRENT_TIMESTAMP,
    criadopor VARCHAR(50),
    atualizadoem DATETIME,
    atualizadopor VARCHAR(50),    
    dataAvaliacao DATE,
    praticaTipoAtividade TINYINT(1) NOT NULL,
    praticaTipoAtividadeQual VARCHAR(40),
    frequencia VARCHAR(40) NOT NULL,
    duracao VARCHAR(40) NOT NULL,
    objetivo VARCHAR(30) NOT NULL,
    objetivoOutro VARCHAR(30),
    restricao TINYINT(1) NOT NULL,
    restricaoQual VARCHAR(30),
    fuma TINYINT(1) NOT NULL,
    bebidaAlcoolica TINYINT(1) NOT NULL,
    medicamento TINYINT(1) NOT NULL,
    medicamentoQual VARCHAR(30),
    medicamentoMotivo VARCHAR(30),
    medicamentoDosagem VARCHAR(30),
    problemaOutro VARCHAR(30),
    problemaArticulacaoQual VARCHAR(30),
    pessoadef BIGINT(20) NOT NULL,
    avaliador BIGINT(20) NOT NULL,
    FOREIGN KEY (pessoadef) REFERENCES pessoasdef(id),
    FOREIGN KEY (avaliador) REFERENCES avaliadores(id)
    
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE avaliacao_problemas(
	id BIGINT(20) NOT NULL,
	problemas VARCHAR(40),
	FOREIGN KEY (id) REFERENCES avaliacoes_individuais(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
