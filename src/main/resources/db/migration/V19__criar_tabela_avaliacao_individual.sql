CREATE TABLE avaliadores(
	id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	criadoem DATETIME DEFAULT CURRENT_TIMESTAMP,
    criadopor VARCHAR(50),
    atualizadoem DATETIME,
    atualizadopor VARCHAR(50),
	pessoadef BIGINT(20) NOT NULL,
	FOREIGN KEY (pessoadef) REFERENCES pessoasdef(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE avaliacoes_individuais (
    id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
    criadoem DATETIME DEFAULT CURRENT_TIMESTAMP,
    criadopor VARCHAR(50),
    atualizadoem DATETIME,
    atualizadopor VARCHAR(50),
    nome VARCHAR(80) NOT NULL,
    dataAvaliacao DATE,
    sexo VARCHAR(20) NOT NULL,
    praticaTipoAtividade TINYINT(1) NOT NULL,
    praticaTipoAtividadeQual VARCHAR(40) NOT NULL,
    frequencia VARCHAR(40) NOT NULL,
    duracao VARCHAR(40) NOT NULL,
    objetivo VARCHAR(30) NOT NULL,
    objetivoOutro VARCHAR(30) NOT NULL,
    restricao TINYINT(1) NOT NULL,
    restricaoQual VARCHAR(30) NOT NULL,
    fuma TINYINT(1) NOT NULL,
    bebidaAlcoolica TINYINT(1) NOT NULL,
    medicamento TINYINT(1) NOT NULL,
    medicamentoQual VARCHAR(30) NOT NULL,
    medicamentoMotivo VARCHAR(30) NOT NULL,
    problemaOutro VARCHAR(30) NOT NULL,
    problemaArticulacaoQual VARCHAR(30) NOT NULL,
    pessoadef BIGINT(20) NOT NULL,
    avaliador BIGINT(20) NOT NULL,
    FOREIGN KEY (pessoadef) REFERENCES pessoasdef(id),
    FOREIGN KEY (avaliador) REFERENCES avaliadores(id)
    
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE avaliacao_problemas(
	id BIGINT(20) NOT NULL,
	problemas VARCHAR(40)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
