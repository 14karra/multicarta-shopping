CREATE TABLE CUSTOMER
(
    id        uuid        NOT NULL,
    name      varchar     NOT NULL,
    last_name varchar     NOT NULL,
    birthday  date        NOT NULL,
    username  varchar(20) NOT NULL,
    CONSTRAINT username_unique UNIQUE (username),
    CONSTRAINT customer_pk PRIMARY KEY (id)
);

CREATE UNIQUE INDEX customer_id_uidx ON CUSTOMER (id);