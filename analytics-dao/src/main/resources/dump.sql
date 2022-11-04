
CREATE EXTENSION IF NOT EXISTS pldbgapi;
CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

-- auth
create schema auth;

CREATE TABLE auth.user (
    id bigserial NOT NULL primary key,
    name varchar(100) NOT NULL,
    surname varchar(100) NOT NULL,
    email varchar(100) NOT NULL unique,
    password varchar(100) NOT NULL,
    salt varchar(100) not null,
    register_date timestamp NOT NULL
);

alter table auth.user
    add column base64 varchar(100);
create index user_base64_index
    on auth."user" (base64);

alter table auth.user
    drop column base64;

CREATE TABLE auth.session (
    id bigserial NOT NULL primary key,
    session_key varchar(110) DEFAULT NULL,
    user_id bigint NOT NULL references auth.user(id),
    create_time timestamp NOT NULL,
    update_time timestamp NOT NULL
);
create unique index session_session_key_uindex
    on auth.session (session_key);

create table auth.restore_password_link (
    id bigserial not null primary key,
    user_id bigint NOT NULL references auth.user(id),
    create_time timestamp NOT NULL,
    update_time timestamp NOT NULL,
    change_password_key varchar(72) not null
);
create unique index restore_password__change_password_key_uindex
    on auth.restore_password_link (change_password_key);

CREATE TYPE auth.user_info_type AS (id int, name varchar(50), surname varchar(50), email varchar(50), session_key varchar(50));

create table auth.user_action (
    id bigserial not null primary key,
    create_time timestamp not null default now(),
    user_id bigint not null
      references auth.user(id),
    field varchar(255) not null,
    title varchar(255),
    value text null
);


CREATE OR REPLACE FUNCTION auth.create_new_user(name varchar, surname varchar, email varchar, password varchar, salt varchar) RETURNS auth.user_info_type AS $$
declare
    object auth.user_info_type;
    user_id bigint;
    session_key varchar;
BEGIN

    insert into auth.user (name, surname, email, password, salt, register_date)
    values (name, surname, email, password, salt, now());

    user_id = currval('auth.user_id_seq');
    session_key = uuid_generate_v4();

    insert into auth.session (session_key, user_id, create_time, update_time)
    values (session_key, user_id, now(), now());

    object.name = name;
    object.id = user_id;
    object.surname = surname;
    object.email = email;
    object.session_key = session_key;
    RETURN object;
END
$$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION auth.create_session(user_id_p bigint) RETURNS varchar AS $$
declare
    session_key varchar;
BEGIN
    session_key = uuid_generate_v4();
    delete from auth.session s where s.user_id = user_id_p;

    insert into auth.session (session_key, user_id, create_time, update_time)
    values (session_key, user_id_p, now(), now());

    RETURN session_key;
END
$$ LANGUAGE plpgsql;

/**
    abilities
 */

create schema abilities;

CREATE TABLE abilities.role (
    id bigserial NOT NULL primary key,
    name character varying(255) NOT NULL,
    title character varying(255) NOT NULL,
    description text
);

CREATE TABLE abilities.permission (
    id bigserial NOT NULL primary key,
    name character varying(255) NOT NULL,
    title character varying(255) NOT NULL,
    type character varying(255) NOT NULL,
    description text
);


create table abilities.user_to_permission (
     user_id bigint NOT NULL
         references auth.user(id),
     permission_id bigint not null
         references abilities.permission(id),
     PRIMARY KEY (user_id, permission_id)
);

create table abilities.user_to_role (
     user_id bigint NOT NULL
         references auth.user(id),
     role_id bigint not null
         references abilities.role(id),
     PRIMARY KEY (user_id, role_id)
);

create table abilities.permission_to_role (
     permission_id bigint NOT NULL
         references abilities.permission(id),
     role_id bigint not null
         references abilities.role(id),
     PRIMARY KEY (permission_id, role_id)
);



/**
  parser - Таблицы для хранения данных из парсеров.
 */
create schema parser;

create table parser.parse_task (
    id bigserial not null primary key,
    create_time timestamp not null default now(),
    external_id text not null,
    type varchar(255) not null,
    url text not null,
    title text,
    status varchar(255)
);
create unique index parse_task_type_external_id_uindex
    on parser.parse_task (type, external_id);


create table parser.notice (
    id bigserial not null primary key,
    create_time timestamp not null default now(),
    parse_task_id bigint not null
       references parser.parse_task(id),
    title varchar(255) not null,
    sum double precision not null,
    address text,
    description text
);

alter table parser.notice
    add column close_date timestamp;

alter table parser.notice
    add column status varchar(255) not null default 'ACTIVE';

create index notice_status_index
	on parser.notice (status);

create table parser.direction (
    id bigserial not null primary key,
    create_time timestamp not null default now(),
    name varchar(255) not null unique,
    title varchar(255) not null
);

create table parser.feature (
    id bigserial not null primary key,
    create_time timestamp not null default now(),
    name varchar(255) not null unique,
    exact_name varchar(255) null unique
);

create table parser.feature_to_direction (
    id bigserial not null primary key,
    create_time timestamp not null default now(),
    feature_id bigint not null
        references parser.feature(id),
    direction_id bigint not null
        references parser.direction(id)
);

create table parser.feature_to_notice (
    id bigserial not null primary key,
    create_time timestamp not null default now(),
    feature_id bigint not null
        references parser.feature(id),
    notice_id bigint not null
        references parser.notice(id),
    value text not null,
    units varchar(255) null
);

create table parser.notice_action (
    id bigserial not null primary key,
    create_time timestamp not null default now(),
    notice_id bigint not null,
    field varchar(255) not null,
    title varchar(255),
    value text null
);


create table parser.notice_feature_action (
    id bigserial not null primary key,
    create_time timestamp not null default now(),
    notice_id bigint not null,
    field varchar(255) not null,
    title varchar(255),
    value text null
);


insert into parser.direction (name, title) values ('REALTY_SALE', 'Недвижимость, продажа');

INSERT INTO parser.feature (id, create_time, name, exact_name) VALUES (6, '2022-08-11 01:40:05.106089', 'Тип комнат', null);
INSERT INTO parser.feature (id, create_time, name, exact_name) VALUES (7, '2022-08-11 01:40:05.106089', 'Санузел', null);
INSERT INTO parser.feature (id, create_time, name, exact_name) VALUES (8, '2022-08-11 01:40:05.106089', 'Окна', null);
INSERT INTO parser.feature (id, create_time, name, exact_name) VALUES (9, '2022-08-11 01:40:05.106089', 'Ремонт', null);
INSERT INTO parser.feature (id, create_time, name, exact_name) VALUES (10, '2022-08-11 01:40:05.106089', 'Мебель', null);
INSERT INTO parser.feature (id, create_time, name, exact_name) VALUES (11, '2022-08-11 01:40:05.106089', 'Способ продажи', null);
INSERT INTO parser.feature (id, create_time, name, exact_name) VALUES (12, '2022-08-11 01:40:05.106089', 'Вид сделки', null);
INSERT INTO parser.feature (id, create_time, name, exact_name) VALUES (16, '2022-08-16 00:30:06.170741', 'В доме', null);
INSERT INTO parser.feature (id, create_time, name, exact_name) VALUES (17, '2022-08-16 00:30:06.170741', 'Двор', null);
INSERT INTO parser.feature (id, create_time, name, exact_name) VALUES (18, '2022-08-16 00:30:06.170741', 'Парковка', null);
INSERT INTO parser.feature (id, create_time, name, exact_name) VALUES (20, '2022-08-16 00:30:18.114564', 'Высота потолков', null);
INSERT INTO parser.feature (id, create_time, name, exact_name) VALUES (21, '2022-08-16 00:30:18.114564', 'Пассажирский лифт', null);
INSERT INTO parser.feature (id, create_time, name, exact_name) VALUES (22, '2022-08-16 00:30:18.114564', 'Грузовой лифт', null);
INSERT INTO parser.feature (id, create_time, name, exact_name) VALUES (23, '2022-08-16 00:30:23.565413', 'Техника', null);
INSERT INTO parser.feature (id, create_time, name, exact_name) VALUES (24, '2022-08-16 00:36:07.933926', 'Отделка', null);
INSERT INTO parser.feature (id, create_time, name, exact_name) VALUES (25, '2022-08-16 00:36:07.933926', 'Название новостройки', null);
INSERT INTO parser.feature (id, create_time, name, exact_name) VALUES (26, '2022-08-16 00:36:07.933926', 'Официальный застройщик', null);
INSERT INTO parser.feature (id, create_time, name, exact_name) VALUES (27, '2022-08-16 00:36:07.933926', 'Тип участия', null);
INSERT INTO parser.feature (id, create_time, name, exact_name) VALUES (28, '2022-08-16 00:36:07.933926', 'Срок сдачи', null);
INSERT INTO parser.feature (id, create_time, name, exact_name) VALUES (29, '2022-08-16 00:36:18.699195', 'Корпус, строение', null);
INSERT INTO parser.feature (id, create_time, name, exact_name) VALUES (30, '2022-08-16 00:37:28.962628', 'Тёплый пол', null);
INSERT INTO parser.feature (id, create_time, name, exact_name) VALUES (31, '2022-08-16 01:02:44.611307', 'Запланирован снос', null);
INSERT INTO parser.feature (id, create_time, name, exact_name) VALUES (14, '2022-08-16 00:30:06.170741', 'Год постройки', 'HOUSE_YEAR');
INSERT INTO parser.feature (id, create_time, name, exact_name) VALUES (2, '2022-08-11 01:40:05.106089', 'Общая площадь', 'TOTAL_AREA');
INSERT INTO parser.feature (id, create_time, name, exact_name) VALUES (15, '2022-08-16 00:30:06.170741', 'Этажей в доме', 'HOUSE_FLOORS');
INSERT INTO parser.feature (id, create_time, name, exact_name) VALUES (4, '2022-08-11 01:40:05.106089', 'Этаж', 'FLOOR');
INSERT INTO parser.feature (id, create_time, name, exact_name) VALUES (5, '2022-08-11 01:40:05.106089', 'Балкон или лоджия', 'BALCON_LOGGIA');
INSERT INTO parser.feature (id, create_time, name, exact_name) VALUES (13, '2022-08-16 00:30:06.170741', 'Тип дома', 'HOUSE_TYPE');
INSERT INTO parser.feature (id, create_time, name, exact_name) VALUES (1, '2022-08-11 01:40:05.106089', 'Количество комнат', 'ROOM_COUNT');
INSERT INTO parser.feature (id, create_time, name, exact_name) VALUES (3, '2022-08-11 01:40:05.106089', 'Площадь кухни', 'KITCHEN_AREA');
INSERT INTO parser.feature (id, create_time, name, exact_name) VALUES (19, '2022-08-16 00:30:18.114564', 'Жилая площадь', 'LIVING_AREA');


/**
  realty - Таблицы для аналитики по недвижимости.
 */
create schema realty;

create table realty.city (
    id bigserial not null primary key,
    create_time timestamp not null default now(),
    name varchar(255) not null,
    coords jsonb not null
);

create table realty.district (
    id bigserial not null primary key,
    create_time timestamp not null default now(),
    name varchar(255) not null,
    coords jsonb not null,
    city_id bigint not null
        references realty.city(id),
    parent_district_id bigint null
        references realty.district(id),
    parents bigint[]
);

alter table realty.district
    add column level int;

create table realty.house (
    id bigserial not null primary key,
    create_time timestamp not null default now(),
    street varchar(500) not null,
    house_num varchar(255) not null,
    district_id bigint not null
        references realty.district(id),
    city_id bigint not null
        references realty.city(id),
    coords jsonb not null
);

/*create table realty.house_to_notice (
    id bigserial not null primary key,
    create_time timestamp not null default now(),
    house_id bigint not null
        references realty.house(id),
    notice_id bigint not null
        references parser.notice(id)
);

create unique index house_to_notice_notice_id_house_id_uindex
    on realty.house_to_notice (notice_id, house_id);

create unique index house_street_house_num_city_id_uindex
    on realty.house (street, house_num, city_id);*/

alter table parser.notice
    add column house_id bigint null
        references realty.house(id);

alter table parser.notice
    add column update_time timestamp null;

create table parser.direction_url (
    id bigserial not null primary key,
    create_time timestamp not null default now(),
    type varchar(255) not null,
    direction_id bigint not null,
    url text not null,
    city_id bigint not null
        references realty.city(id)
);

alter table parser.parse_task
    add column direction_url_id bigint references parser.direction_url(id) not null default 1;

alter table parser.direction_url
    add column title varchar(255);

create table realty.notice_category (
    id bigserial not null primary key,
    create_time timestamp not null default now(),
    notice_id bigint not null unique
        references parser.notice(id),
    canon_type_number int not null,
    rooms_count varchar(255),
    floor varchar(255),
    house_floor varchar(255),
    house_type varchar(255),
    house_build_year varchar(255),
    balcon varchar(255),
    square_value double precision not null
);

create table realty.notice_average_price (
    id bigserial not null primary key,
    create_time timestamp not null default now(),
    canon_type_number int not null,
    realty_config_type varchar(255) not null,
    sum double precision not null
);

create unique index notice_avg_price_canon_type_num_real_conf_type_uindex
    on realty.notice_average_price (canon_type_number, realty_config_type);


create table realty.house_add_info (
    id bigserial not null primary key,
    create_time timestamp not null default now(),
    house_id bigint not null unique
       references realty.house(id),
    build_year int not null,
    floors_count int not null,
    deterioration int,
    house_type varchar(255) not null
);

create or replace view realty.v_notice_info_with_avg_price as(
    select
        n.id,
        n.title,
        n.sum,
        pt.url,
        h.street,
        h.house_num,
        h.coords,
        d.name,
        nc.square_value,
        nap.sum as average_sum,
        nc.rooms_count,
        nc.floor,
        nc.house_floor,
        nc.house_type,
        nc.house_build_year,
        nc.balcon,
        nc.classifier_category,
        nc.realty_segment,
        nc.repair_type,
        nc.simple_house_type,
        nc.total_square,
        nc.kitchen_square,
        nc.metro_distance,
        d.id as district_id
    from parser.notice n
        join parser.parse_task pt on n.parse_task_id = pt.id
        join realty.house h on n.house_id = h.id
        left join realty.notice_category nc on n.id = nc.notice_id
        left join realty.notice_average_price nap on nc.canon_type_number = nap.canon_type_number and nap.realty_config_type = 'COMPLEX'
        left join realty.district d on h.district_id = d.id
    where
        n.status = 'ACTIVE'
    order by h.district_id
);

create table realty.fias_addr(
    id bigserial PRIMARY KEY NOT NULL,
    fias_uuid uuid not null,
    parent_uuid uuid,
    formal_name varchar(255) not null,
    off_name varchar(255) not null,
    region_code int not null,
    shortname varchar(255) not null,
    aolevel int not null,
    street_code int
);

alter table realty.fias_addr
    add column city_id bigint references realty.fias_addr(id);

alter table realty.city
    add column fias_id bigint references realty.fias_addr(id);

create unique index fias_addr_fias_uuid_uindex
	on realty.fias_addr (fias_uuid);
alter table realty.fias_addr
    add constraint fias_addr_fias_addr_fias_uuid_fk
        foreign key (parent_uuid) references realty.fias_addr (fias_uuid);


-- Этот запрос может падать...
alter table realty.city
    alter column fias_id set not null;

alter table realty.city
    add column lat double precision not null default 0,
    add column lng double precision not null default 0;

alter table realty.house
    add column fias_street bigint;

create table realty.import_realty_request(
    id bigserial not null primary key,
    create_time timestamp not null default now(),
    user_id bigint not null
        references auth.user(id),
    objects_count int not null
);

create table realty.import_realty_object(
    id bigserial not null primary key,
    create_time timestamp not null default now(),
    import_excel_request_id bigint not null
        references realty.import_realty_request(id),
    address text not null,
    rooms_count varchar(255) not null,
    realty_segment varchar(255) not null,
    house_floors_count int not null,
    wall_material varchar(255) not null,
    floor int not null,
    total_area double precision not null,
    kitchen_area double precision not null,
    balcon varchar(255) not null,
    metro_distance int not null,
    repair_type varchar(255) not null
);

drop view realty.v_notice_info_with_avg_price;

alter table realty.notice_category
    add column classifier_category varchar(255) not null,
    add column realty_segment varchar(255),
    add column repair_type varchar(255),
    add column simple_house_type varchar(255),
    add column total_square varchar(255),
    add column kitchen_square varchar(255),
    add column metro_distance varchar(255);