CREATE TABLE cargos (
    codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(50) NOT NULL,
    abreviacao VARCHAR(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



INSERT INTO cargos(nome, abreviacao) VALUES ('SOLDADO PM', 'SD PM');
INSERT INTO cargos(nome, abreviacao) VALUES ('CABO PM', 'CB PM');
INSERT INTO cargos(nome, abreviacao) VALUES ('3° SARGENTO PM', '3° SGT PM');
INSERT INTO cargos(nome, abreviacao) VALUES ('2° SARGENTO PM', '2° SGT PM');
INSERT INTO cargos(nome, abreviacao) VALUES ('1° SARGENTO PM', '1° SGT PM');
INSERT INTO cargos(nome, abreviacao) VALUES ('SUBTENENTE PM', 'ST PM');
INSERT INTO cargos(nome, abreviacao) VALUES ('2° TENENTE PM', '2° TEN PM');
INSERT INTO cargos(nome, abreviacao) VALUES ('1° TENENTE PM', '1° TEN PM');
INSERT INTO cargos(nome, abreviacao) VALUES ('CAPITÃO PM', 'CAP PM');
INSERT INTO cargos(nome, abreviacao) VALUES ('MAJOR PM', 'MJ PM');
INSERT INTO cargos(nome, abreviacao) VALUES ('TENENTE CORONEL PM', 'TEN CEL PM');
INSERT INTO cargos(nome, abreviacao) VALUES ('CORONEL PM', 'CEL PM');



