create table Cliente(id bigint not null auto_increment ,
e-mail varchar(50) not null,
senha varchar(50) not null, 
cpf integer not null,
nome varchar(50) not null, 
telefone integer not null, 
sexo char, 
dataDeNascimento date not null, primary key (id));