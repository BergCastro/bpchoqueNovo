

CREATE TABLE pessoasdef (
    codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
    
    nome VARCHAR(80) NOT NULL,
    sexo VARCHAR(20) NOT NULL,
    telefone1 VARCHAR(20),
    telefone2 VARCHAR(20),
    datanasc DATE NOT NULL,
    email VARCHAR(60),
    tipo VARCHAR(20) NOT NULL,
    matricula VARCHAR(15) NOT NULL,
    numero_pm VARCHAR(6),
    nome_guerra VARCHAR(20),
    cargo BIGINT(20) NOT NULL,
    opm_orgao BIGINT(20) NOT NULL,
    FOREIGN KEY (cargo) REFERENCES cargos(codigo),
    FOREIGN KEY (opm_orgao) REFERENCES opms_orgaos(codigo)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

