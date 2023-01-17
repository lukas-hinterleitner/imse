create table book
(
    id           varchar(255) not null,
    amount_pages integer      not null,
    name         varchar(255) not null,
    primary key (id)
) engine=InnoDB;
create table borrows
(
    id         varchar(255) not null,
    end_date   date         not null,
    start_date date         not null,
    book_id    varchar(255) not null,
    room_id    varchar(255) not null,
    library_id varchar(255) not null,
    user_id    varchar(255) not null,
    primary key (id)
) engine=InnoDB;
create table contains
(
    id            varchar(255) not null,
    delivery_date date,
    quantity      integer,
    book_id       varchar(255) not null,
    room_id       varchar(255) not null,
    library_id    varchar(255) not null,
    primary key (id)
) engine=InnoDB;
create table customer
(
    id                varchar(255) not null,
    email             varchar(255) not null,
    name              varchar(255) not null,
    phone_number      varchar(255) not null,
    registration_date date         not null,
    primary key (id)
) engine=InnoDB;
create table employee
(
    id            varchar(255)     not null,
    email         varchar(255)     not null,
    name          varchar(255)     not null,
    hiring_date   date             not null,
    salary        double precision not null,
    library_id    varchar(255)     not null,
    supervisor_id varchar(255),
    primary key (id)
) engine=InnoDB;
create table library
(
    id             varchar(255) not null,
    address_city   varchar(255) not null,
    address_street varchar(255) not null,
    name           varchar(255) not null,
    primary key (id)
) engine=InnoDB;
create table room
(
    library_id varchar(255) not null,
    room_id    varchar(255) not null,
    capacity   integer      not null,
    name       varchar(255) not null,
    primary key (library_id, room_id)
) engine=InnoDB;
alter table customer
    add constraint UK_dwk6cx0afu8bs9o4t536v1j5v unique (email);
alter table employee
    add constraint UK_fopic1oh5oln2khj8eat6ino0 unique (email);
alter table borrows
    add constraint FK25io8sagpwn2dgeh1a3ud0vrx foreign key (book_id) references book (id);
alter table borrows
    add constraint FK18dy6iair3l0blrrvuik5xjjb foreign key (room_id, library_id) references room (library_id, room_id);
alter table contains
    add constraint FKtkfr4pstqp33wsxy0rny35i7g foreign key (book_id) references book (id);
alter table contains
    add constraint FK6fl7ecckap19pyhifh15a6cr1 foreign key (room_id, library_id) references room (library_id, room_id);
alter table employee
    add constraint FKftpc8cwbpe3es2nlw4esduj08 foreign key (library_id) references library (id);
alter table employee
    add constraint FKc30m2tyq9as0ht5xctaypfdqp foreign key (supervisor_id) references employee (id);
alter table room
    add constraint FKt0w33wlpmltupcgni8c25qiw1 foreign key (library_id) references library (id);
