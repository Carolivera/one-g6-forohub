-- Crear tabla usuarios
create table usuarios (
    id bigint not null auto_increment,
    nombre varchar(100) not null,
    correo_electronico varchar(300) not null,
    contrasena varchar(100) not null,
    perfiles varchar(100) not null,
    primary key(id)
);

-- Crear tabla cursos
create table cursos (
    id bigint not null auto_increment,
    nombre varchar(300) not null,
    categoria varchar(100) not null,
    primary key(id)
);

-- Crear tabla topicos
create table topicos (
    id bigint not null auto_increment,
    titulo varchar(100) not null,
    mensaje varchar(400) not null,
    fecha_creacion date not null,
    status tinyint default 1,
    usuario_id bigint not null,
    cursos_id bigint not null,
    primary key(id),
    constraint fk_usuarios_id foreign key(usuario_id) references usuarios(id),
    constraint fk_cursos_id foreign key(cursos_id) references cursos(id)
);

-- Crear tabla respuestas
create table respuestas (
    id bigint not null auto_increment,
    mensaje varchar(200) not null,
    fecha_creacion date not null,
    solucion varchar(400) not null,
    topico_id bigint not null,
    primary key(id),
    constraint fk_topicos_id foreign key(topico_id) references topicos(id)
);
