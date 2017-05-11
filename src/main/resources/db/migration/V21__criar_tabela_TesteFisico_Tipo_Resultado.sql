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

INSERT INTO `bpchoquenovo`.`provas` (`nome`, `descricao`, `tipo`) VALUES ('FLEXÃO BARRA', 'FDFGDFGDF', 'NOTA');
INSERT INTO `bpchoquenovo`.`provas` (`nome`, `descricao`, `tipo`) VALUES ('FLEXÃO SOLO', 'FDFGDFGDF', 'NOTA');
INSERT INTO `bpchoquenovo`.`provas` (`nome`, `descricao`, `tipo`) VALUES ('ABDOMINAL', 'FDFGDFGDF', 'NOTA');
INSERT INTO `bpchoquenovo`.`provas` (`nome`, `descricao`, `tipo`) VALUES ('CORRIDA 12MIN', 'FDFGDFGDF', 'NOTA');
INSERT INTO `bpchoquenovo`.`provas` (`nome`, `descricao`, `tipo`) VALUES ('CORRIDA 50M', 'FDFGDFGDF', 'NOTA');
INSERT INTO `bpchoquenovo`.`provas` (`nome`, `descricao`, `tipo`) VALUES ('FLUTUAÇÃO', 'FDFGDFGDF', 'APTOINAPTO');
INSERT INTO `bpchoquenovo`.`provas` (`nome`, `descricao`, `tipo`) VALUES ('LANÇAR GRANADA', 'FDFGDFGDF', 'APTOINAPTO');

CREATE TABLE tipos_teste(
	id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	criadoem DATETIME DEFAULT CURRENT_TIMESTAMP,
    criadopor VARCHAR(50),
    atualizadoem DATETIME,
    atualizadopor VARCHAR(50),
	nome VARCHAR(40) NOT NULL,
	descricao VARCHAR(60) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;






