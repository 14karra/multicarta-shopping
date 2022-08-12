CREATE TABLE users
(
    username varchar(20)  NOT NULL,
    password varchar(100) NOT NULL,
    role     varchar(5)   NOT NULL,
    CONSTRAINT users_pk PRIMARY KEY (username)
)