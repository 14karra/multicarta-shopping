CREATE TABLE PURCHASE
(
    id            uuid           NOT NULL,
    count         int            NOT NULL,
    amount        numeric(19, 2) NOT NULL,
    purchase_date date           NOT NULL,
    item_id       bigint         NOT NULL
        CONSTRAINT purchase_item_id_fk REFERENCES ITEM (id),
    customer_id   uuid           NOT NULL
        CONSTRAINT purchase_customer_id_fk REFERENCES CUSTOMER (id),

    CONSTRAINT positive_amount_cc CHECK (amount >= 0::int),
    CONSTRAINT purchase_pk PRIMARY KEY (id)
);

CREATE UNIQUE INDEX purchase_id_uidx ON PURCHASE (id);
CREATE INDEX purchase_item_i ON PURCHASE (item_id);
CREATE INDEX purchase_customer_i ON PURCHASE (customer_id);