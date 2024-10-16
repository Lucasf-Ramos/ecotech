USE MASTER IF EXISTS(
 select * from SYS.databases where name ='bd_EcoTech')
 drop database bd_EcoTech
 go 

-- CRIAR UM BANCO DE DADOS
CREATE DATABASE bd_EcoTech
GO
-- ACESSAR O BANCO DE DADOS
USE bd_EcoTech
GO

CREATE TABLE Admnistrador
( 
   id            INT				IDENTITY,
   nome          VARCHAR(100)		NOT NULL,
   email         VARCHAR(100)		NOT NULL,
   senha         VARCHAR(100)		NOT NULL,
   nivelAcesso   VARCHAR(10)    	NULL, -- ADMIN ou USER
   dataCadastro	 SMALLDATETIME		NOT NULL,
   statusUsuario bit			NOT NULL, -- ATIVO ou INATIVO
   PRIMARY KEY (id)
)
GO
INSERT Admnistrador (nome, email, senha, nivelAcesso, dataCadastro, statusUsuario)
VALUES ('Ana Silva', 'ana.silva@example.com', 'senhaAna1', 'ADMIN', GETDATE(), 1)
INSERT Admnistrador (nome, email, senha, nivelAcesso, dataCadastro, statusUsuario)
VALUES ('Lucas Fantinati', 'lucasframos15@gmail.com', 'senha', 'ADMIN', GETDATE(), 1)
INSERT Admnistrador (nome, email, senha, nivelAcesso, dataCadastro, statusUsuario)
VALUES ('Pedro Santos', 'pedro.santos@example.com', 'senhaPedro2', 'ADMIN', GETDATE(), 1)
INSERT Admnistrador (nome, email, senha, nivelAcesso, dataCadastro, statusUsuario)
VALUES ('Mariana Oliveira', 'mariana.oliveira@example.com', 'senhaMariana3', 'ADMIN', GETDATE(), 1)
INSERT Admnistrador (nome, email, senha, nivelAcesso, dataCadastro, statusUsuario)
VALUES ('Lucas Souza', 'lucas.souza@example.com', 'senhaLucas4', 'ADMIN', GETDATE(), 1)
INSERT Admnistrador (nome, email, senha, nivelAcesso, dataCadastro, statusUsuario)
VALUES ('Carolina Costa', 'carolina.costa@example.com', 'senhaCarolina5', 'ADMIN', GETDATE(), 1)
INSERT Admnistrador (nome, email, senha, nivelAcesso, dataCadastro, statusUsuario)
VALUES ('Rafael Pereira', 'rafael.pereira@example.com', 'senhaRafael6', 'ADMIN', GETDATE(), 1)
INSERT Admnistrador (nome, email, senha, nivelAcesso, dataCadastro, statusUsuario)
VALUES ('Gustavo Lima', 'gustavo.lima@example.com', 'senhaGustavo8', 'ADMIN', GETDATE(), 1)
INSERT Admnistrador (nome, email, senha, nivelAcesso, dataCadastro, statusUsuario)
VALUES ('Fernanda Almeida', 'fernanda.almeida@example.com', 'senhaFernanda7', 'ADMIN', GETDATE(), 1)
INSERT Admnistrador (nome, email, senha, nivelAcesso, dataCadastro, statusUsuario)
VALUES ('Isabela Martins', 'isabela.martins@example.com', 'senhaIsabela9', 'ADMIN', GETDATE(), 1)
INSERT Admnistrador (nome, email, senha, nivelAcesso, dataCadastro, statusUsuario)
VALUES ('Henrique Barbosa', 'henrique.barbosa@example.com', 'senhaHenrique10', 'ADMIN', GETDATE(), 1)
GO

CREATE TABLE GrupoResiduo
(
	id				INT		  IDENTITY,
	nome			VARCHAR(100) NOT NULL,  -- PEQUENO PORTE, MEDIO PORTE E GRANDE PORTE
	descricao		VARCHAR(300) NOT NULL,

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


CREATE TABLE EcoPonto
(
	id				INT		IDENTITY,
	nome			    	VARCHAR(100)	NOT NULL,
	descricao		    	VARCHAR(400)	NULL,
	logradouro 	        	VARCHAR(100)    NOT NULL, -- nome da rua, avenida e etc
	cep				CHAR(8)         NOT NULL,
    numResid		    	VARCHAR(10)     NOT NULL,
	bairro  			VARCHAR(200)	NOT NULL,
    cidade 				VARCHAR(200)	NOT NULL,
    uf 				CHAR(2)		NOT NULL,
	complemento 			VARCHAR(200)	NULL,
	pontoRef 			VARCHAR(200)	NULL,
	telefone			VARCHAR(20)	NULL,
	horarioFunc			VARCHAR(200)	NULL,
	statusPonto			bit		NOT NULL, -- ATIVO(1) ou INATIVO(0) 
    gruporesiduo_id			INT		NOT NULL,
	email			   	VARCHAR(100)    NOT NULL,
	cnpj			    	CHAR(14)	NOT NULL,
	senha				VARCHAR(100)	NOT NULL,
	foto				varbinary(max)	NULL,

   


	PRIMARY KEY (id),
	FOREIGN KEY (gruporesiduo_id) REFERENCES GrupoResiduo(id),
	
	
	
)
GO 

INSERT EcoPonto (nome, descricao, logradouro, cep, numResid, bairro, cidade, uf, complemento, pontoRef, telefone, horarioFunc, statusPonto, gruporesiduo_id, email, cnpj, senha, foto)
VALUES ('EcoPonto Verde', 'Coleta seletiva de resíduos eletrônicos', 'Rua das Árvores, 123', '06410000', '123', 'Alphaville', 'Barueri', 'SP', NULL, 'Próximo ao shopping', '(11) 1234-5678', 'Seg-Sex: 08:00-18:00', 1, 1, 'contato@ecopontoalphaville.com', '1234567890001', '1234', NULL)
INSERT EcoPonto (nome, descricao, logradouro, cep, numResid, bairro, cidade, uf, complemento, pontoRef, telefone, horarioFunc, statusPonto, gruporesiduo_id, email, cnpj, senha, foto)
VALUES ('EcoPonto do Zé', 'Coleta seletiva de resíduos eletrônicos', 'Avenida dos Rios, 456', '06345000', '456', 'Vila Cretti', 'Carapicuíba', 'SP', NULL, 'Próximo ao centro comercial', '(11) 2345-6789', 'Seg-Sex: 09:00-19:00', 1, 6, 'contato@ecopontovilacretti.com', '2345678900001', '5678', NULL)
INSERT EcoPonto (nome, descricao, logradouro, cep, numResid, bairro, cidade, uf, complemento, pontoRef, telefone, horarioFunc, statusPonto, gruporesiduo_id, email, cnpj, senha, foto)
VALUES ('EcoPonto Futuro', 'Coleta seletiva de resíduos eletrônicos', 'Rua das Flores, 789', '06677000', '789', 'Amador Bueno', 'Itapevi', 'SP', NULL, 'Próximo à estação de trem', '(11) 3456-7890', 'Seg-Sex: 07:00-17:00', 1, 2, 'contato@ecopontoamadorbueno.com', '34567890000123', 'EcopontoAmadorBueno_7890', NULL)
INSERT EcoPonto (nome, descricao, logradouro, cep, numResid, bairro, cidade, uf, complemento, pontoRef, telefone, horarioFunc, statusPonto, gruporesiduo_id, email, cnpj, senha, foto)
VALUES ('EcoPonto da Dona Maria', 'Coleta seletiva de resíduos eletrônicos', 'Avenida das Aves, 1011', '06600000', '1011', 'Jardim Alvorada', 'Jandira', 'SP', NULL, 'Próximo ao centro de saúde', '(11) 4567-8901', 'Seg-Sex: 10:00-20:00', 1, 4, 'contato@ecopontojardimalvorada.com', '45678901230001', 'EcopontoJardimAlvorada_8901', NULL)
INSERT EcoPonto (nome, descricao, logradouro, cep, numResid, bairro, cidade, uf, complemento, pontoRef, telefone, horarioFunc, statusPonto, gruporesiduo_id, email, cnpj, senha, foto)
VALUES ('EcoPonto Vida', 'Coleta seletiva de resíduos eletrônicos', 'Rua dos Lagos, 1213', '06000000', '1213', 'Jardim das Flores', 'Osasco', 'SP', NULL, 'Próximo ao parque', '(11) 3456-7890', 'Seg-Sex: 07:00-17:00', 1, 4, 'contato@ecopontojardimdasflores.com', '56789012300001', 'EcopontoJardimDasFlores_5678', NULL)
INSERT EcoPonto (nome, descricao, logradouro, cep, numResid, bairro, cidade, uf, complemento, pontoRef, telefone, horarioFunc, statusPonto, gruporesiduo_id, email, cnpj, senha, foto)
VALUES ('EcoPonto do Seu João', 'Coleta seletiva de resíduos eletrônicos', 'Rua das Pedras, 1415', '06550000', '1415', 'Centro', 'Pirapora do Bom Jesus', 'SP', NULL, 'Próximo à prefeitura', '(11) 3456-7890', 'Seg-Sex: 07:00-17:00', 1, 1, 'contato@ecopontocentro.com', '67890123400001', 'EcopontoCentro_1234', NULL)
INSERT EcoPonto (nome, descricao, logradouro, cep, numResid, bairro, cidade, uf, complemento, pontoRef, telefone, horarioFunc, statusPonto, gruporesiduo_id, email, cnpj, senha, foto)
VALUES ('EcoPonto Limpo', 'Coleta seletiva de resíduos eletrônicos', 'Avenida das Águas, 1617', '06500000', '1617', 'Tamboré', 'Santana de Parnaíba', 'SP', NULL, 'Próximo ao colégio', '(11) 3456-7890', 'Seg-Sex: 07:00-17:00', 1, 5, 'contato@ecopontotambore.com', '78901234500001', 'EcopontoTambore_7890', NULL)
INSERT EcoPonto (nome, descricao, logradouro, cep, numResid, bairro, cidade, uf, complemento, pontoRef, telefone, horarioFunc, statusPonto, gruporesiduo_id, email, cnpj, senha, foto)
VALUES ('EcoPonto do Seu Chico', 'Coleta seletiva de resíduos eletrônicos', 'Rua dos Pinheiros, 1819', '06420000', '1819', 'Aldeia da Serra', 'Barueri', 'SP', NULL, 'Próximo ao campo de futebol', '(11) 3456-7890', 'Seg-Sex: 07:00-17:00', 1, 6, 'contato@ecopontoaldeiadaserra.com', '89012345000001', 'EcopontoAldeiaDaSerra_1234', NULL)
INSERT EcoPonto (nome, descricao, logradouro, cep, numResid, bairro, cidade, uf, complemento, pontoRef, telefone, horarioFunc, statusPonto, gruporesiduo_id, email, cnpj, senha, foto)
VALUES ('EcoPonto Brisa', 'Coleta seletiva de resíduos eletrônicos', 'Avenida das Rosas, 1920', '06360000', '1920', 'Jardim Maria Beatriz', 'Carapicuíba', 'SP', NULL, 'Próximo ao parque de diversões', '(11) 3456-7890', 'Seg-Sex: 07:00-17:00', 1, 2, 'contato@ecopontojardimmariabeatriz.com', '90123456000001', 'EcopontoJardimMariaBeatriz_5678', NULL)
INSERT EcoPonto (nome, descricao, logradouro, cep, numResid, bairro, cidade, uf, complemento, pontoRef, telefone, horarioFunc, statusPonto, gruporesiduo_id, email, cnpj, senha, foto)
VALUES ('EcoPonto da Vó Ana', 'Coleta seletiva de resíduos eletrônicos', 'Rua dos Girassóis, 2021', '06688000', '2021', 'Vila Nova', 'Itapevi', 'SP', NULL, 'Próximo ao posto de gasolina', '(11) 3456-7890', 'Seg-Sex: 07:00-17:00', 1, 1, 'contato@ecopontovilanova.com', '12345678900002', 'EcopontoVilaNova_7890', NULL)

GO


CREATE TABLE UsuarioApp
(
	id				    INT				IDENTITY,
	nome			    VARCHAR(100)	NOT NULL,
	email				VARCHAR(100)	UNIQUE NOT NULL,
	avaliacao			FLOAT				NULL,


	PRIMARY KEY (id),
	
)


SELECT * FROM Admnistrador
SELECT * FROM GrupoResiduo
SELECT * FROM EcoPonto
SELECT * FROM UsuarioApp

select email, senha from EcoPonto


