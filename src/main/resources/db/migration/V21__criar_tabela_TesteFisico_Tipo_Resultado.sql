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

INSERT INTO `bpchoquenovo`.`provas` (`criadoem`, `criadopor`, `atualizadoem`, `atualizadopor`, `nome`, `descricao`, `tipo`, `refInicialMasc`, `refFinalMasc`, `intervaloRef`, `idadeInicial`, `idadeFinal`, `intervaloIdade`) VALUES ('2017-05-11 16:32:49', 'fireberg2500@hotmail.com', '2017-05-16 16:21:30', 'fireberg2500@hotmail.com', 'FLEXÃO BARRA', 'FDFGDFGDF', 'NOTA', '1.00', '12.00', '1.00', '20', '54', '5');
INSERT INTO `bpchoquenovo`.`provas` (`criadoem`, `criadopor`, `atualizadoem`, `atualizadopor`, `nome`, `descricao`, `tipo`, `refInicialMasc`, `refFinalMasc`, `intervaloRef`, `idadeInicial`, `idadeFinal`, `intervaloIdade`) VALUES ('2017-05-11 16:32:49', 'fireberg2500@hotmail.com', '2017-05-16 16:21:30', 'fireberg2500@hotmail.com', 'FLEXÃO SOLO', 'FDFGDFGDF', 'NOTA', '14.00', '45.00', '2.00', '20', '54', '5');
INSERT INTO `bpchoquenovo`.`provas` (`criadoem`, `criadopor`, `atualizadoem`, `atualizadopor`, `nome`, `descricao`, `tipo`, `refInicialMasc`, `refFinalMasc`, `intervaloRef`, `idadeInicial`, `idadeFinal`, `intervaloIdade`) VALUES ('2017-05-11 16:32:49', 'fireberg2500@hotmail.com', '2017-05-16 16:21:30', 'fireberg2500@hotmail.com', 'ABDOMINAL', 'FDFGDFGDF', 'NOTA', '18.00', '49.00', '2.00', '20', '54', '5');
INSERT INTO `bpchoquenovo`.`provas` (`criadoem`, `criadopor`, `atualizadoem`, `atualizadopor`, `nome`, `descricao`, `tipo`, `refInicialMasc`, `refFinalMasc`, `intervaloRef`, `idadeInicial`, `idadeFinal`, `intervaloIdade`) VALUES ('2017-05-11 16:32:49', 'fireberg2500@hotmail.com', '2017-05-16 16:21:30', 'fireberg2500@hotmail.com', 'CORRIDA 12MIN', 'FDFGDFGDF', 'NOTA', '1400.00', '2999.00', '100.00', '20', '54', '5');
INSERT INTO `bpchoquenovo`.`provas` (`criadoem`, `criadopor`, `atualizadoem`, `atualizadopor`, `nome`, `descricao`, `tipo`, `refInicialMasc`, `refFinalMasc`, `intervaloRef`, `idadeInicial`, `idadeFinal`, `intervaloIdade`) VALUES ('2017-05-11 16:32:49', 'fireberg2500@hotmail.com', '2017-05-16 16:21:30', 'fireberg2500@hotmail.com', 'CORRIDA 50M', 'FDFGDFGDF', 'TEMPO', '10.50', '6.75', '0.25', '20', '54', '5');
INSERT INTO `bpchoquenovo`.`provas` (`nome`, `descricao`, `tipo`) VALUES ('FLUTUAÇÃO', 'FDFGDFGDF', 'APTOINAPTO');
INSERT INTO `bpchoquenovo`.`provas` (`nome`, `descricao`, `tipo`) VALUES ('LANÇAR GRANADA', 'FDFGDFGDF', 'APTOINAPTO');



CREATE TABLE tipos_teste(
	id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	criadoem DATETIME DEFAULT CURRENT_TIMESTAMP,
    criadopor VARCHAR(50),
    atualizadoem DATETIME,
    atualizadopor VARCHAR(50),
	nome VARCHAR(40) NOT NULL,
	descricao VARCHAR(60) NOT NULL,
	qtdProvasMedia INT(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;






