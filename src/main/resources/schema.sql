create table book (
                      id int(10) not null,
                      title varchar(150) not null,
                      author varchar(150) not null,
                      description varchar(150),
                      constraint book_pk primary key (id)
);