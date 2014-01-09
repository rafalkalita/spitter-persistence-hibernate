drop table if exists spittle;
drop table if exists spitter;

CREATE TABLE spitter (
    id integer NOT NULL AUTO_INCREMENT,
    username varchar(50) NOT NULL,
    password varchar(25) NOT NULL,
    fullname varchar(100) NOT NULL,
    PRIMARY KEY (id)
);

create table spittle (
    id integer NOT NULL AUTO_INCREMENT,
    spitter_id integer NOT NULL,
    message varchar(2000) NOT NULL,
    whencreated date NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (spitter_id) references spitter(id)
);