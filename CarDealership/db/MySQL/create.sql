drop database if exists CarDealershipSQL;

create database CarDealershipSQL;

create table Cliente(id bigint not null auto_increment ,
e-mail varchar(50) not null,
senha varchar(50) not null, 
cpf integer not null,
nome varchar(50) not null, 
telefone integer not null, 
sexo char, 
dataDeNascimento date not null, primary key (id));

create table lojas (
id_loja int not null auto_increment,
nome varchar(50) not null,
email varchar(50) not null,
senha varchar(50) not null,
descricao varchar(60) not null,
cnpj int(14) not null,
primary key(id_loja)
)