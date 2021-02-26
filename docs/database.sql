create table TBL_PROFESSORES
(
    ID INTEGER not null
        constraint TBL_PROFESSORES_pk
            primary key autoincrement,
    NOME TEXT,
    ENDERECO TEXT,
    IDADE INTEGER,
    MATERIA TEXT,
    VOTO_BOM INTEGER,
    VOTO_RUIM INTEGER
);

create unique index TBL_PROFESSORES_ID_uindex
	on TBL_PROFESSORES (ID);

