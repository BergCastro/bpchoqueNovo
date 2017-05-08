CREATE TABLE resultados_teste(
	id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	criadoem DATETIME DEFAULT CURRENT_TIMESTAMP,
    criadopor VARCHAR(50),
    atualizadoem DATETIME,
    atualizadopor VARCHAR(50),
	pessoa BIGINT(20) NOT NULL,
	teste BIGINT(20) NOT NULL,
	tipoteste BIGINT(20) NOT NULL,
	valorProva1 DECIMAL(10,2),
	pontuacaoProva1 DECIMAL(10,2),
	valorProva2 DECIMAL(10,2),
	pontuacaoProva2 DECIMAL(10,2),
	valorProva3 DECIMAL(10,2),
	pontuacaoProva3 DECIMAL(10,2),
	valorProva4 DECIMAL(10,2),
	pontuacaoProva4 DECIMAL(10,2),
	valorProva5 DECIMAL(10,2),
	pontuacaoProva5 DECIMAL(10,2),
	FOREIGN KEY (pessoa) REFERENCES pessoasdef(id),
	FOREIGN KEY (teste) REFERENCES testes_fisicos(id),
	FOREIGN KEY (tipoTeste) REFERENCES tipos_teste(id)	
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


