USE MASTER IF EXISTS(
 select * from SYS.databases where name ='bd_EcoTech')
 drop database bd_EcoTech
 go 

-- CRIAR UM BANCO DE DADOS a
CREATE DATABASE bd_EcoTech
GO
-- ACESSAR O BANCO DE DADOS
USE bd_EcoTech
GO

CREATE TABLE Usuario
( 
   id            INT			IDENTITY,
   nome          VARCHAR(100)	NOT NULL,
   email         VARCHAR(100)	UNIQUE NOT NULL,
   senha         VARCHAR(100)	NOT NULL,
   nivelAcesso   VARCHAR(10)    NULL, -- ADMIN ou USER
   foto			 VARBINARY(MAX) NULL,
   dataCadastro	 SMALLDATETIME	NOT NULL,
   statusUsuario bit   NOT NULL, -- ATIVO ou INATIVO ou TROCAR_SENHA

   PRIMARY KEY (id)
)
GO
INSERT Usuario (nome, email, senha, nivelAcesso, foto, dataCadastro, statusUsuario)
VALUES ('Ana Silva', 'ana.silva@example.com', 'senhaAna1', 'ADMIN', NULL, GETDATE(), 1)
INSERT Usuario (nome, email, senha, nivelAcesso, foto, dataCadastro, statusUsuario)
VALUES ('Pedro Santos', 'pedro.santos@example.com', 'senhaPedro2', 'ADMIN', NULL, GETDATE(), 1)
INSERT Usuario (nome, email, senha, nivelAcesso, foto, dataCadastro, statusUsuario)
VALUES ('Mariana Oliveira', 'mariana.oliveira@example.com', 'senhaMariana3', 'ADMIN', NULL, GETDATE(), 1)
INSERT Usuario (nome, email, senha, nivelAcesso, foto, dataCadastro, statusUsuario)
VALUES ('Lucas Souza', 'lucas.souza@example.com', 'senhaLucas4', 'ADMIN', NULL, GETDATE(), 1)
INSERT Usuario (nome, email, senha, nivelAcesso, foto, dataCadastro, statusUsuario)
VALUES ('Carolina Costa', 'carolina.costa@example.com', 'senhaCarolina5', 'ADMIN', NULL, GETDATE(), 1)
INSERT Usuario (nome, email, senha, nivelAcesso, foto, dataCadastro, statusUsuario)
VALUES ('Rafael Pereira', 'rafael.pereira@example.com', 'senhaRafael6', 'ADMIN', NULL, GETDATE(), 1)
INSERT Usuario (nome, email, senha, nivelAcesso, foto, dataCadastro, statusUsuario)
VALUES ('Gustavo Lima', 'gustavo.lima@example.com', 'senhaGustavo8', 'ADMIN', NULL, GETDATE(), 1)
INSERT Usuario (nome, email, senha, nivelAcesso, foto, dataCadastro, statusUsuario)
VALUES ('Fernanda Almeida', 'fernanda.almeida@example.com', 'senhaFernanda7', 'ADMIN', NULL, GETDATE(), 1)
INSERT Usuario (nome, email, senha, nivelAcesso, foto, dataCadastro, statusUsuario)
VALUES ('Isabela Martins', 'isabela.martins@example.com', 'senhaIsabela9', 'ADMIN', NULL, GETDATE(), 1)
INSERT Usuario (nome, email, senha, nivelAcesso, foto, dataCadastro, statusUsuario)
VALUES ('Henrique Barbosa', 'henrique.barbosa@example.com', 'senhaHenrique10', 'ADMIN', NULL, GETDATE(), 1)
GO

CREATE TABLE GrupoResiduo
(
	id			INT		  IDENTITY,
	nome		VARCHAR(100) NOT NULL,  -- PEQUENO PORTE, MEDIO PORTE E GRANDE PORTE
	descricao	VARCHAR(300) NOT NULL,

	PRIMARY KEY(id)
)
GO
INSERT GrupoResiduo (nome, descricao) VALUES ('PEQUENO PORTE','Smartphones, fones de ouvido, smartwatches e cameras digitais.')
INSERT GrupoResiduo (nome, descricao) VALUES ('MÉDIO PORTE', 'Notebooks, tablets (de 7 a 10 polegadas) e cameras DSLR.')
INSERT GrupoResiduo (nome, descricao) VALUES ('GRANDE PORTE', 'Desktops e Sistemas de Home Theater.')
INSERT GrupoResiduo (nome, descricao) VALUES ('PEQUENO/MÉDIO PORTE', 'Smartphones, fones de ouvido, smartwatches, cameras digitais, notebooks, tablets (de 7 a 10 polegadas) e cameras DSLR.')
INSERT GrupoResiduo (nome, descricao) VALUES ('PEQUENO/MÉDIO/GRANDE PORTE', 'Smartphones, fones de ouvido, smartwatches, cameras digitais, notebooks, tablets (de 7 a 10 polegadas), 
cameras DSLR, desktops e Sistemas de Home Theater.')
INSERT GrupoResiduo (nome, descricao) VALUES ('MÉDIO/GRANDE PORTE', 'Notebooks, tablets (de 7 a 10 polegadas), cameras DSLR, desktops e Sistemas de Home Theater.')
GO

CREATE TABLE Regiao
(
	id			 INT		    IDENTITY,
	nome	     VARCHAR(100)	NOT NULL,
	

	PRIMARY KEY (id),
	
)
GO
INSERT Regiao (nome) VALUES ('BARUERI')
INSERT Regiao (nome) VALUES ('CARAPICUÍBA')
INSERT Regiao (nome) VALUES ('ITAPEVI')
INSERT Regiao (nome) VALUES ('JANDIRA')
INSERT Regiao (nome) VALUES ('OSASCO')
INSERT Regiao (nome) VALUES ('PIRAPORA DO BOM JESUS')
INSERT Regiao (nome) VALUES ('SANTANA DE PARNAIBA')
GO

CREATE TABLE EcoPonto
(
	id				    INT		    IDENTITY,
	nome			    VARCHAR(100)	NOT NULL,

	email			    VARCHAR(100)    UNIQUE Not NULL,
	cnpj			    char(14)	UNIQUE Not NULL,
	senha				VARCHAR(100)	NOT NULL,

	descricao		    VARCHAR(400)	  NULL,
	website			    VARCHAR(200)	NULL,
	logradouro 	        VARCHAR(100)    NOT NULL, -- nome da rua, avenida e etc
	cep				    CHAR(8)         NOT NULL,
    numResid		    VARCHAR(10)     NOT NULL,
	bairro  			VARCHAR(200)	NOT NULL,
    cidade 				VARCHAR(200)	NOT NULL,
    uf 				    CHAR(2)			NOT NULL,
	complemento 		VARCHAR(200)	NULL,
	pontoRef 			VARCHAR(200)	NULL,
    telefone			VARCHAR(20)		NULL,
	horarioFunc			VARCHAR(200)	NULL,
	statusPonto			bit		NOT NULL, -- ATIVO(1) ou INATIVO(0) 

    gruporesiduo_id		INT				NOT NULL,
    regiao_id			INT				NOT NULL,


	PRIMARY KEY (id),
	FOREIGN KEY (gruporesiduo_id) REFERENCES GrupoResiduo(id),
	FOREIGN KEY (regiao_id) REFERENCES Regiao(id)
	
	
)
GO --adicionar senha a todos os ecopontos
INSERT EcoPonto (nome, descricao, website, logradouro, cep, numResid, bairro, cidade, uf, complemento, pontoRef, telefone, horarioFunc, statusPonto, gruporesiduo_id, regiao_id, email, cnpj) 
VALUES ('Ecoponto Alphaville', 'Coleta seletiva de resíduos eletrônicos', NULL, 'Rua das Árvores, 123', '06410000', '123', 'Alphaville', 'Barueri', 'SP', NULL, 'Próximo ao shopping', '(11) 1234-5678', 'Seg-Sex: 08:00-18:00', 1, 1, 1, 'contato@ecopontoalphaville.com', '1234567890001')
INSERT EcoPonto (nome, descricao, website, logradouro, cep, numResid, bairro, cidade, uf, complemento, pontoRef, telefone, horarioFunc, statusPonto, gruporesiduo_id, regiao_id, email, cnpj)
VALUES ('Ecoponto Vila Cretti', 'Coleta seletiva de resíduos eletrônicos', NULL, 'Avenida dos Rios, 456', '06345000', '456', 'Vila Cretti', 'Carapicuíba', 'SP', NULL, 'Próximo ao centro comercial', '(11) 2345-6789', 'Seg-Sex: 09:00-19:00', 1, 2, 1, 'contato@ecopontovilacretti.com', '2345678900001')
INSERT EcoPonto (nome, descricao, website, logradouro, cep, numResid, bairro, cidade, uf, complemento, pontoRef, telefone, horarioFunc, statusPonto, gruporesiduo_id, regiao_id, email, cnpj)
VALUES ('Ecoponto Amador Bueno', 'Coleta seletiva de resíduos eletrônicos', NULL, 'Rua das Flores, 789', '06677000', '789', 'Amador Bueno', 'Itapevi', 'SP', NULL, 'Próximo à estação de trem', '(11) 3456-7890', 'Seg-Sex: 07:00-17:00', 1, 3, 1, 'contato@ecopontoamadorbueno.com', '34567890000123')
INSERT EcoPonto (nome, descricao, website, logradouro, cep, numResid, bairro, cidade, uf, complemento, pontoRef, telefone, horarioFunc, statusPonto, gruporesiduo_id, regiao_id, email, cnpj)
VALUES ('Ecoponto Jardim Alvorada', 'Coleta seletiva de resíduos eletrônicos', NULL, 'Avenida das Aves, 1011', '06600000', '1011', 'Jardim Alvorada', 'Jandira', 'SP', NULL, 'Próximo ao centro de saúde', '(11) 4567-8901', 'Seg-Sex: 10:00-20:00', 1, 4, 1, 'contato@ecopontojardimalvorada.com', '45678901230001')
INSERT EcoPonto (nome, descricao, website, logradouro, cep, numResid, bairro, cidade, uf, complemento, pontoRef, telefone, horarioFunc, statusPonto, gruporesiduo_id, regiao_id, email, cnpj)
VALUES ('Ecoponto Jardim das Flores', 'Coleta seletiva de resíduos eletrônicos', NULL, 'Rua dos Lagos, 1213', '06000000', '1213', 'Jardim das Flores', 'Osasco', 'SP', NULL, 'Próximo ao parque', '(11) 3456-7890', 'Seg-Sex: 07:00-17:00', 1, 5, 1, 'contato@ecopontojardimdasflores.com', '56789012300001')
INSERT EcoPonto (nome, descricao, website, logradouro, cep, numResid, bairro, cidade, uf, complemento, pontoRef, telefone, horarioFunc, statusPonto, gruporesiduo_id, regiao_id, email, cnpj)
VALUES ('Ecoponto Centro', 'Coleta seletiva de resíduos eletrônicos', NULL, 'Rua das Pedras, 1415', '06550000', '1415', 'Centro', 'Pirapora do Bom Jesus', 'SP', NULL, 'Próximo à prefeitura', '(11) 3456-7890', 'Seg-Sex: 07:00-17:00', 1, 6, 1, 'contato@ecopontocentro.com', '67890123400001')
INSERT EcoPonto (nome, descricao, website, logradouro, cep, numResid, bairro, cidade, uf, complemento, pontoRef, telefone, horarioFunc, statusPonto, gruporesiduo_id, regiao_id, email, cnpj)
VALUES ('Ecoponto Tamboré', 'Coleta seletiva de resíduos eletrônicos', NULL, 'Avenida das Águas, 1617', '06500000', '1617', 'Tamboré', 'Santana de Parnaíba', 'SP', NULL, 'Próximo ao colégio', '(11) 3456-7890', 'Seg-Sex: 07:00-17:00', 1, 1, 1, 'contato@ecopontotambore.com', '78901234500001')
INSERT EcoPonto (nome, descricao, website, logradouro, cep, numResid, bairro, cidade, uf, complemento, pontoRef, telefone, horarioFunc, statusPonto, gruporesiduo_id, regiao_id, email, cnpj)
VALUES ('Ecoponto Aldeia da Serra', 'Coleta seletiva de resíduos eletrônicos', NULL, 'Rua dos Pinheiros, 1819', '06420000', '1819', 'Aldeia da Serra', 'Barueri', 'SP', NULL, 'Próximo ao campo de futebol', '(11) 3456-7890', 'Seg-Sex: 07:00-17:00', 1, 2, 1, 'contato@ecopontoaldeiadaserra.com', '89012345000001')
INSERT EcoPonto (nome, descricao, website, logradouro, cep, numResid, bairro, cidade, uf, complemento, pontoRef, telefone, horarioFunc, statusPonto, gruporesiduo_id, regiao_id, email, cnpj)
VALUES ('Ecoponto Jardim Maria Beatriz', 'Coleta seletiva de resíduos eletrônicos', NULL, 'Avenida das Rosas, 1920', '06360000', '1920', 'Jardim Maria Beatriz', 'Carapicuíba', 'SP', NULL, 'Próximo ao parque de diversões', '(11) 3456-7890', 'Seg-Sex: 07:00-17:00', 1, 3, 1, 'contato@ecopontojardimmariabeatriz.com', '90123456000001')
INSERT EcoPonto (nome, descricao, website, logradouro, cep, numResid, bairro, cidade, uf, complemento, pontoRef, telefone, horarioFunc, statusPonto, gruporesiduo_id, regiao_id, email, cnpj)
VALUES ('Ecoponto Vila Nova', 'Coleta seletiva de resíduos eletrônicos', NULL, 'Rua dos Girassóis, 2021', '06688000', '2021', 'Vila Nova', 'Itapevi', 'SP', NULL, 'Próximo à escola municipal', '(11) 3456-7890', 'Seg-Sex: 07:00-17:00', 1, 4, 1, 'contato@ecopontovilanova.com', '01234567000001')
GO

/*
CREATE TABLE Ocorrencia
(
	id						INT				IDENTITY,
	descricao				VARCHAR(400)	NOT NULL,
	dataOcorrencia			SMALLDATETIME	NOT NULL,
    ecoPonto_id				INT				NOT NULL,
    usuario_id				INT				NOT NULL,
	statusOcorrencia 		VARCHAR(10)		NOT NULL,  -- ATIVO ou INATIVO 

	PRIMARY KEY (id),
	FOREIGN KEY (ecoPonto_id) REFERENCES EcoPonto(id),
	FOREIGN KEY (usuario_id) REFERENCES Usuario(id)
)

GO

INSERT Ocorrencia (descricao, dataOcorrencia, ecoPonto_id, usuario_id, statusOcorrencia) 
VALUES ('Entulho deixado próximo ao ecoponto', '03/01/2024 10:30:00', 1, 1, 'ATIVO')
INSERT Ocorrencia (descricao, dataOcorrencia, ecoPonto_id, usuario_id, statusOcorrencia)
VALUES ('Vandalismo nas instalações do ecoponto', '05/03/2024 14:45:00', 2, 2, 'ATIVO')
INSERT Ocorrencia (descricao, dataOcorrencia, ecoPonto_id, usuario_id, statusOcorrencia)
VALUES ('Falta de manutenção nos recipientes de coleta', '05/10/2024 08:20:00', 3, 3, 'ATIVO')
INSERT Ocorrencia (descricao, dataOcorrencia, ecoPonto_id, usuario_id, statusOcorrencia)
VALUES ('Presença de ratos nas proximidades do ecoponto', '15/03/2024 11:10:00', 4, 4, 'ATIVO')
INSERT Ocorrencia (descricao, dataOcorrencia, ecoPonto_id, usuario_id, statusOcorrencia)
VALUES ('Acúmulo de lixo ao redor do ecoponto', '20/05/2024 13:55:00', 5, 5, 'ATIVO')

GO
*/

SELECT * FROM Usuario
SELECT * FROM GrupoResiduo
SELECT * FROM Regiao
SELECT * FROM EcoPonto



