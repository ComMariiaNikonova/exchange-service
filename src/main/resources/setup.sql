CREATE DATABASE data_exch;

USE data_exch;

DROP TABLE IF EXISTS commission
CREATE TABLE commission
(
commission DECIMAL (10, 2),
  currency_from     VARCHAR(255),
  currency_to       VARCHAR(255),
  PRIMARY KEY (currency_id_from, currency_id_to)
);

DROP TABLE IF EXISTS rate
CREATE TABLE rate
(
rate DECIMAL (10, 2),
  currency_from     VARCHAR(255),
  currency_to       VARCHAR(255),
  PRIMARY KEY (currency_id_from, currency_id_to)
);

