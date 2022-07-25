use CarDealershipSQL;

insert into Usuario(nome, email, senha, papel) values ('Administrador', 'admin', 'admin', 'ADMIN');
insert into Usuario(nome, email, senha, papel) values ('Usuario', 'user', 'user', 'USER');

insert into lojas(nome, email, descricao, cnpj) values ('Loja 1', 'loja1@email.com', 'Loja de Veículos número 1', 123);
insert into lojas(nome, email, descricao, cnpj) values ('Loja 2', 'loja2@email.com', 'Loja de Veículos número 2', 456);
insert into lojas(nome, email, descricao, cnpj) values ('Loja 3', 'loja3@email.com', 'Loja de Veículos número 3', 789);

insert into carro(cnpj_loja, id_loja, placa, modelo, chassi, ano, quilometragem, descricao, valor, fotos) values(
    123,
    1,
    'ABC1234',
    'Carro1',
    'CodChassi',
    '2020',
    20000,
    'carro0',
    30000,
    'pathFotos'
);

insert into carro(cnpj_loja, id_loja, placa, modelo, chassi, ano, quilometragem, descricao, valor, fotos) values(
    123,
    1,
    'ABC1010',
    'Carro2',
    'CodChassi2',
    '2018',
    60000,
    'carro1',
    25000,
    'pathFotos'
);

insert into carro(cnpj_loja, id_loja, placa, modelo, chassi, ano, quilometragem, descricao, valor, fotos) values(
    123,
    1,
    'ABC2000',
    'Carro3',
    'CodChassi3',
    '2005',
    100000,
    'carro2',
    20000,
    'pathFotos'
);


insert into carro(cnpj_loja, id_loja, placa, modelo, chassi, ano, quilometragem, descricao, valor, fotos) values(
    456,
    2,
    'ABC0000',
    'Carro1',
    'CodChassi',
    '2006',
    35000,
    'carro3',
    40000,
    'pathFotos'
);

insert into carro(cnpj_loja, id_loja, placa, modelo, chassi, ano, quilometragem, descricao, valor, fotos) values(
    456,
    2,
    'ABC6515',
    'Carro2',
    'CodChassi2',
    '2018',
    60000,
    'carro4',
    25000,
    'pathFotos'
);

insert into carro(cnpj_loja, id_loja, placa, modelo, chassi, ano, quilometragem, descricao, valor, fotos) values(
    456,
    2,
    'ABC0909',
    'Carro3',
    'CodChassi3',
    '2021',
    100000,
    'carro5',
    32100,
    'pathFotos'
);

insert into carro(cnpj_loja, id_loja, placa, modelo, chassi, ano, quilometragem, descricao, valor, fotos) values(
    789,
    3,
    'ABC6541',
    'Carro1',
    'CodChassi',
    '2006',
    35000,
    'carro6',
    40000,
    'pathFotos'
);

insert into carro(cnpj_loja, id_loja, placa, modelo, chassi, ano, quilometragem, descricao, valor, fotos) values(
    789,
    3,
    'ABC9842',
    'Carro2',
    'CodChassi2',
    '2018',
    60000,
    'carro7',
    25000,
    'pathFotos'
);

insert into carro(cnpj_loja, id_loja, placa, modelo, chassi, ano, quilometragem, descricao, valor, fotos) values(
    789,
    3,
    'ABC1545',
    'Carro3',
    'CodChassi3',
    '2021',
    100000,
    'carro8',
    32100,
    'pathFotos'
);