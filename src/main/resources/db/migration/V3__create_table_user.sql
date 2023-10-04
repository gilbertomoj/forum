create table user(
    id bigint not null auto_increment,
    name varchar(50),
    email varchar(50),
    primary key (id)
);

insert into user values(1, 'josé', 'jose@gmail.com');