drop table if exists place;
drop table if exists weather;


create table place
(
    place_id uuid not null
        constraint place_pkey
            primary key,
    address varchar(255),
    category_id uuid,
    latitude double precision,
    longitude double precision,
    phone_number varchar(255),
    place_description varchar(255),
    place_name varchar(255),
    place_photo varchar(255)
);

create table weatherscore
(
    weatherscore_id uuid not null
        constraint weatherscore_pkey
            primary key,
    place_id uuid,
    place_name varchar(255),
    latitude double precision,
    longitude double precision,
    score varchar(10)
);