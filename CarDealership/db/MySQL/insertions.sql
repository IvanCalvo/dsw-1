use CarDealershipSQL;

insert into Usuario(email, senha, papel) values ('admin', 'admin', 'ADMIN');
insert into Usuario(email, senha, papel) values ('user', 'user', 'USER');

insert into Usuario(email, senha, papel) values ('loja1', 'loja1', 'LOJA');
insert into Usuario(email, senha, papel) values ('loja2', 'loja2', 'LOJA');
insert into Usuario(email, senha, papel) values ('loja3', 'loja3', 'LOJA');
insert into Usuario(email, senha, papel) values ('loja4', 'loja4', 'LOJA');

insert into lojas(nome, id, descricao, cnpj) values ('Loja 1', 3, 'Loja de Veículos número 1', 123);
insert into lojas(nome, id, descricao, cnpj) values ('Loja 2', 4, 'Loja de Veículos número 2', 456);
insert into lojas(nome, id, descricao, cnpj) values ('Loja 3', 5, 'Loja de Veículos número 3', 789);
insert into lojas(nome, id, descricao, cnpj) values ('Loja 4', 6, 'Loja de Veículos número 4', 1231231231);


insert into carro(cnpj_loja, id_loja, placa, modelo, chassi, ano, quilometragem, descricao, valor, fotos) values(
    123,
    3,
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
    3,
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
    3,
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
    4,
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
    4,
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
    4,
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
    5,
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
    5,
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
    5,
    'ABC1545',
    'Carro3',
    'CodChassi3',
    '2021',
    100000,
    'carro8',
    32100,
    'pathFotos'
);
