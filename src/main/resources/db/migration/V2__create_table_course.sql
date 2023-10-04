create table course(
    id bigint not null auto_increment,
    name varchar(50),
    category varchar(50),
    primary key(id)
);

insert into course values (1, 'javascript', 'web')