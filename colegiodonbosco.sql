create database colegiodonbosco;

use colegiodonbosco;

/* ---------------------------------------------- 
	CREACION DE TABLAS
---------------------------------------------- */

/*
tabla: rol

Esta tabla tendrá por el momento unicamente 3 roles: ADMINISTRADOR, PROFESOR y ALUMNO.
*/

create table rol(
	id_rol int primary key auto_increment,
    nombre varchar(100) not null
);

/*
tabla: usuario

Esta tabla tendrá informacion del usuario. Recordemos que un usuario
puede tener rol ADMINISTRADOR, PROFESOR o ALUMNO.
*/

create table usuario (
	id_usuario int primary key auto_increment,
    nombre varchar(100) not null,
    carnet varchar(100) not null,
    password varchar(100) not null,
    id_rol int not null,
    constraint fk_usu_rol foreign key(id_rol) references rol(id_rol)
);

/* 
tabla: libro

Esta tabla servirá para almacenar informacion de un libro
*/

create table libro (
	codigo char(8) primary key, -- Ejemplo: LIB00001 
    titulo varchar(100) not null,
    autor varchar(100) not null,
    num_pag int not null,
    editorial varchar(100) not null,
    isbn varchar(100) not null,
    anio_pub int not null,
    unidades_disp int not null, 
    ubicacion varchar(100) not null
);

/* 
tabla: revista

Esta tabla servirá para almacenar informacion de una revista 
*/

create table revista (
	codigo char(8) primary key, -- Ejemplo: REV00001 
    titulo varchar(100) not null,
    editorial varchar(100) not null,
    periocidad varchar(100) not null, -- Ejemplo: diaria, semanal, quincenal, mensual
    fecha_pub datetime not null,
    unidades_disp int not null,
    ubicacion varchar(100) not null
);

/* 
tabla: cd

Esta tabla servirá para almacenar informacion de un cd 
*/

create table cd (
	codigo char(8) primary key, -- Ejemplo: CDA00001 
    titulo varchar(100) not null,
    artista varchar(100) not null,
    genero varchar(100) not null, 
    duracion varchar(10) not null, --  Ejemplo: 01:30 (1 hora y 30 min)
    num_canciones int not null,
    unidades_disp int not null,
    ubicacion varchar(100) not null
);

/* 
tabla: dvd

Esta tabla servirá para almacenar informacion de un dvd
*/

create table dvd (
	codigo char(8) primary key, -- Ejemplo: DVD00001 
    titulo varchar(100) not null,
    director varchar(100) not null,
    duracion varchar(10) not null, --  Ejemplo: 01:30 (1 hora y 30 min)
    genero varchar(100) not null, 
    unidades_disp int not null,
    ubicacion varchar(100) not null
);

create table obra (
	codigo char(8) primary key, -- Ejemplo: OBR00001 
    titulo varchar(100) not null,
    autor varchar(100) not null,
    editorial varchar(100) not null,
    genero varchar(100) not null, 
    unidades_disp int not null,
    ubicacion varchar(100) not null
);

create table tesis (
	codigo char(8) primary key, -- Ejemplo: TES00001 
    titulo varchar(100) not null,
    autor varchar(100) not null,
    editorial varchar(100) not null,
    facultad varchar(100) not null,
    carrera varchar(100) not null,
    unidades_disp int not null,
    ubicacion varchar(100) not null
);

create table documento (
	codigo char(8) primary key, -- Ejemplo: DOC00001 
    titulo varchar(100) not null,
    autor varchar(100) not null,
    editorial varchar(100) not null,
    tematica varchar(100) not null, 
    unidades_disp int not null,
    ubicacion varchar(100) not null
);

/*
tabla: material

Esta tabla es la que tendrá todos los materiales 
disponibles (Libros, Revistas, CDs y DVDs). 

Como sabremos que tipo de material es?
R/ Por el codigo de 8 digitos: LIB00001, REV00001, etc.

NOTA: Todos estos materiales seran agregados, actualizados o eliminados 
unicamente por el usuario con rol: ADMIN
*/

create table material (
	id_material int primary key auto_increment,
    codigo char(8) not null,
    fecha_creacion datetime not null default now(),
    id_usuario int, 
    constraint fk_mat_usu foreign key(id_usuario) references usuario(id_usuario)
);

/*
tabla: prestamo

Esta tabla es la que tendrá todos prestamos de material que
van realizando.

NOTA: Todos estos materiales seran prestados 
unicamente por el usuario con rol: ADMIN
*/
create table prestamo (
	id_prestamo int primary key auto_increment,
    id_material int not null,
    id_usuario int not null,
    fecha_prestamo datetime not null,
    fecha_devolucion datetime not null,
    constraint fk_pre_mat foreign key(id_material) references material(id_material),
    constraint fk_pre_usu foreign key(id_usuario) references usuario(id_usuario)
);

/* 
tabla: parametros_sistema

En esta tabla se almacenarán todos los parametros que sirven para hacer 
calculos dentro de nuestro sistema.
Ejemplo: el precio de mora diaria por atraso
*/
create table parametro_sistema (
	id_parametro_sistema int primary key auto_increment,
    nombre varchar(100) not null,
    descripcion varchar(100) not null,
    valor varchar(100) not null
);

/* ---------------------------------------------- 
	INSERTS INICIALES
---------------------------------------------- */

insert into rol (nombre) 
values ('ADMINISTRADOR');
insert into rol (nombre) 
values ('PROFESOR');
insert into rol (nombre) 
values ('ALUMNO');

insert into usuario (nombre, carnet, password, id_rol) 
values ('Admin Admin', 'admin', 'admin', 1);
insert into usuario (nombre, carnet, password, id_rol) 
values ('Rafael Torres', 'profesor', 'profesor', 2);
insert into usuario (nombre, carnet, password, id_rol) 
values ('Tatiana Valencia', 'alumno', 'alumno', 3);

insert into libro(codigo, titulo, autor, num_pag, editorial, isbn, anio_pub, unidades_disp, ubicacion)
values ('LIB00001', 'Libro1', 'Autor1', 111, 'Editorial1', '111111', '2001', 2, 'Ubicacion1');
insert into libro(codigo, titulo, autor, num_pag, editorial, isbn, anio_pub, unidades_disp, ubicacion)
values ('LIB00002', 'Libro2', 'Autor2', 222, 'Editorial2', '222222', '2002', 2, 'Ubicacion2');

insert into material(codigo, fecha_creacion, id_usuario)
values('LIB00001', now(), 1);
insert into material(codigo, fecha_creacion, id_usuario)
values('LIB00002', now(), 1);

insert into revista(codigo, titulo, editorial, periocidad, fecha_pub, unidades_disp, ubicacion)
values ('REV00001', 'Revista1', 'Editorial1', 'Semanal', date_sub(now(), interval 20 year), 2, 'Ubicacion1');
insert into revista(codigo, titulo, editorial, periocidad, fecha_pub, unidades_disp, ubicacion)
values ('REV00002', 'Revista2', 'Editorial2', 'Semanal', date_sub(now(), interval 22 year), 2, 'Ubicacion2');

insert into material(codigo, fecha_creacion, id_usuario)
values('REV00001', now(), 1);
insert into material(codigo, fecha_creacion, id_usuario)
values('REV00002', now(), 1);

insert into cd(codigo, titulo, artista, genero, duracion, num_canciones, unidades_disp, ubicacion)
values ('CD000001', 'Cd1', 'Artista1', 'Genero1', '01:30', 11, 2, 'Ubicacion1');
insert into cd(codigo, titulo, artista, genero, duracion, num_canciones, unidades_disp, ubicacion)
values ('CD000002', 'Cd2', 'Artista2', 'Genero2', '01:30', 22, 2, 'Ubicacion2');

insert into material(codigo, fecha_creacion, id_usuario)
values('CD000001', now(), 1);
insert into material(codigo, fecha_creacion, id_usuario)
values('CD000002', now(), 1);

insert into dvd(codigo, titulo, director, duracion, genero, unidades_disp, ubicacion)
values ('DVD00001', 'Dvd1', 'Director1', '01:30', 'Genero1', 2, 'Ubicacion1');
insert into dvd(codigo, titulo, director, duracion, genero, unidades_disp, ubicacion)
values ('DVD00002', 'Dvd2', 'Director2', '01:30', 'Genero2', 2, 'Ubicacion2');

insert into material(codigo, fecha_creacion, id_usuario)
values('DVD00001', now(), 1);
insert into material(codigo, fecha_creacion, id_usuario)
values('DVD00002', now(), 1);

insert into obra(codigo, titulo, autor, editorial, genero, unidades_disp, ubicacion)
values ('OBR00001', 'Obra1', 'Autor1', 'Editorial1', 'Genero1', 2, 'Ubicacion1');
insert into obra(codigo, titulo, autor, editorial, genero, unidades_disp, ubicacion)
values ('OBR00002', 'Obra2', 'Autor2', 'Editorial2', 'Genero2', 2, 'Ubicacion2');

insert into material(codigo, fecha_creacion, id_usuario)
values('OBR00001', now(), 1);
insert into material(codigo, fecha_creacion, id_usuario)
values('OBR00002', now(), 1);

insert into tesis(codigo, titulo, autor, editorial, facultad, carrera, unidades_disp, ubicacion)
values ('TES00001', 'Tesis1', 'Autor1', 'Editorial1', 'Facultad1', 'Carrera1', 2, 'Ubicacion1');
insert into tesis(codigo, titulo, autor, editorial, facultad, carrera, unidades_disp, ubicacion)
values ('TES00002', 'Tesis2', 'Autor2', 'Editorial2', 'Facultad2', 'Carrera2', 2, 'Ubicacion2');

insert into material(codigo, fecha_creacion, id_usuario)
values('TES00001', now(), 1);
insert into material(codigo, fecha_creacion, id_usuario)
values('TES00002', now(), 1);

insert into documento(codigo, titulo, autor, editorial, tematica, unidades_disp, ubicacion)
values ('DOC00001', 'Documento1', 'Autor1', 'Editorial1', 'Tematica1', 2, 'Ubicacion1');
insert into documento(codigo, titulo, autor, editorial, tematica, unidades_disp, ubicacion)
values ('DOC00002', 'Documento2', 'Autor2', 'Editorial2', 'Tematica2', 2, 'Ubicacion2');

insert into material(codigo, fecha_creacion, id_usuario)
values('DOC00001', now(), 1);
insert into material(codigo, fecha_creacion, id_usuario)
values('DOC00002', now(), 1);

insert into parametro_sistema(nombre, descripcion, valor)
values('MORA', 'Mora diaria', '0.25');

insert into prestamo(id_material, id_usuario, fecha_prestamo, fecha_devolucion)
values(1, 3, now(), date_add(now(), INTERVAL 5 DAY));
insert into prestamo(id_material, id_usuario, fecha_prestamo, fecha_devolucion)
values(2, 3, date_sub(now(), interval 5 day), date_sub(now(), interval 3 day));

select * from revista;