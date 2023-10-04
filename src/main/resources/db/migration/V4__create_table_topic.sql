create table topic(
    id bigint not null auto_increment,
    title varchar(50),
    message varchar(50),
    created_at datetime,
    status varchar(20) not null,
    course_id bigint not null,
    author_id bigint not null,
    primary key (id),
    foreign key (course_id) references course(id),
    foreign key (author_id) references user(id)
)