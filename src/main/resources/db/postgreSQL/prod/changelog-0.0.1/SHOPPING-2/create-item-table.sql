CREATE TABLE ITEM
(
    id             bigint                   NOT NULL,
    name           varchar                  NOT NULL,
    description    varchar(100),
    amount         numeric(19, 2)           NOT NULL,
    quantity       int                      NOT NULL,
    insertion_date timestamp with time zone NOT NULL,

    CONSTRAINT positive_amount_cc CHECK (amount >= 0::int),
    CONSTRAINT item_pk PRIMARY KEY (id)
);
