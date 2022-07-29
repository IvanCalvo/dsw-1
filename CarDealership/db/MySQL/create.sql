drop database if exists CarDealershipSQL;

create database CarDealershipSQL;

use CarDealershipSQL;

create table Usuario(
	id bigint not null auto_increment,
	email varchar(50) not null unique, 
	senha varchar(50) not null, 
	papel varchar(20),
	primary key (id)
);

create table Cliente(
	id bigint not null,
	cpf integer not null,
	nome varchar(50) not null, 
	telefone integer not null, 
	sexo char, 
	dataDeNascimento date not null, 
	primary key (id),
	foreign key (id) references Usuario(id)
);

create table lojas (
	id bigint not null,
	nome varchar(50) not null,
	descricao varchar(60) not null,
	cnpj int(14) not null,
	primary key(id),
	foreign key (id) references Usuario(id)
);

create table carro(
	id int not null auto_increment,
	cnpj_loja int not null,
	id_loja bigint not null,
	placa varchar(7) not null,
	modelo varchar(30) not null,
	chassi varchar(30) not null,
	ano int(4) not null,
	quilometragem int(10) not null,
	descricao varchar(120) not null,
	valor float(11,2) not null,
	fotos varchar(10) not null,
	primary key(id),
  	foreign key(id_loja) references lojas(id)
);

create table Proposta(
	id int not null auto_increment,
	cliente_id bigint not null,
	carro_id int not null,
	valor decimal not null,
	condPagamento varchar(50) not null,
	dataAtual date not null,
	statusCompra varchar(50) not null,
	foreign key (cliente_id) references Cliente(id),
	foreign key (carro_id) references carro(id),
	primary key(id)
);