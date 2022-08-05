CREATE TABLE users
(
    username varchar(20)  NOT NULL,
    password varchar(100) NOT NULL,
    birthday date         NOT NULL,
    CONSTRAINT users_pk PRIMARY KEY (username)
)