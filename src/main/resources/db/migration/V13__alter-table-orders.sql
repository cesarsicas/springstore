ALTER TABLE orders
ADD COLUMN customer_address_id BIGINT,
ADD COLUMN customer_credit_card_id BIGINT,
ADD CONSTRAINT fk_orders_customer_address FOREIGN KEY (customer_address_id) REFERENCES customer_addresses(id),
ADD CONSTRAINT fk_orders_credit_cards FOREIGN KEY (customer_credit_card_id) REFERENCES customer_credit_cards(id);
