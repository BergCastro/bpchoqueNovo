CREATE TABLE testes_fisicos(
	id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	criadoem DATETIME DEFAULT CURRENT_TIMESTAMP,
    criadopor VARCHAR(50),
    atualizadoem DATETIME,
    atualizadopor VARCHAR(50),
	data DATE,
	objetivo VARCHAR(40) NOT NULL,
	notaAprovacao DECIMAL(10,2)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE tiposteste_provas(
	tipoTesteId BIGINT(20) NOT NULL,
	provaId BIGINT(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE testes_tipos(
	testeFisicoId BIGINT(20) NOT NULL,
	tipoTesteId BIGINT(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE testesFisicos_pessoas(
	testeFisicoId BIGINT(20) NOT NULL,
	pessoaId BIGINT(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE provas(
	id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	criadoem DATETIME DEFAULT CURRENT_TIMESTAMP,
    criadopor VARCHAR(50),
    atualizadoem DATETIME,
    atualizadopor VARCHAR(50),
	nome VARCHAR(40) NOT NULL,
	descricao VARCHAR(400) NOT NULL,
	tipo VARCHAR(40) NOT NULL,
	refInicialMasc DECIMAL(10,2),
	refFinalMasc DECIMAL(10,2),
	refInicialFem DECIMAL(10,2),
	refFinalFem DECIMAL(10,2),
	intervaloRef DECIMAL(10,2),
	idadeInicial INT(3),
	idadeFinal INT(3),
	intervaloIdade INT(3)	
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE tipos_teste(
	id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	criadoem DATETIME DEFAULT CURRENT_TIMESTAMP,
    criadopor VARCHAR(50),
    atualizadoem DATETIME,
    atualizadopor VARCHAR(50),
	nome VARCHAR(40) NOT NULL,
	descricao VARCHAR(60) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;




CREATE TABLE resultados_teste(
	id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	criadoem DATETIME DEFAULT CURRENT_TIMESTAMP,
    criadopor VARCHAR(50),
    atualizadoem DATETIME,
    atualizadopor VARCHAR(50),
	pessoa BIGINT(20) NOT NULL,
	teste BIGINT(20) NOT NULL,
	prova BIGINT(20) NOT NULL,
	tipoteste BIGINT(20) NOT NULL,
	valor DECIMAL(10,2) NOT NULL,
	FOREIGN KEY (pessoa) REFERENCES pessoasdef(id),
	FOREIGN KEY (teste) REFERENCES testes_fisicos(id),
	FOREIGN KEY (prova) REFERENCES provas(id),
	FOREIGN KEY (tipoTeste) REFERENCES tipos_teste(id)	
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


