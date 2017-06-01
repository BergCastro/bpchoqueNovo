CREATE TABLE testes_fisicos(
	id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	criadoem DATETIME,
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


CREATE TABLE provas(
	id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	criadoem DATETIME,
    criadopor VARCHAR(50),
    atualizadoem DATETIME,
    atualizadopor VARCHAR(50),
	nome VARCHAR(40) NOT NULL,
	descricao VARCHAR(400) NOT NULL,
	tipo VARCHAR(40) NOT NULL,
	refInicialMasc VARCHAR(20) DEFAULT '0',
	refFinalMasc VARCHAR(20) DEFAULT '0',
	refInicialFem VARCHAR(20) DEFAULT '0',
	refFinalFem VARCHAR(20) DEFAULT '0',
	intervaloRef VARCHAR(20) DEFAULT '0',
	idadeInicial INT(3),
	idadeFinal INT(3),
	intervaloIdade INT(3)	
) ENGINE=InnoDB DEFAULT CHARSET=utf8;





CREATE TABLE tipos_teste(
	id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	criadoem DATETIME,
    criadopor VARCHAR(50),
    atualizadoem DATETIME,
    atualizadopor VARCHAR(50),
	nome VARCHAR(40) NOT NULL,
	descricao VARCHAR(60) NOT NULL,
	qtdProvasMedia INT(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;






