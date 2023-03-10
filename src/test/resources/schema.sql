CREATE TABLE PRICES (
	ID INTEGER NOT NULL PRIMARY KEY,
	BRAND_ID INTEGER NOT NULL,
	START_DATE TIMESTAMP WITH TIME ZONE NOT NULL,
	END_DATE TIMESTAMP WITH TIME ZONE NOT NULL,
	PRICE_LIST INTEGER NOT NULL,
	PRODUCT_ID INTEGER NOT NULL,
	PRIORITY SMALLINT NOT NULL,
	PRICE NUMERIC(10, 2) NOT NULL,
	CURR CHAR(3) NOT NULL
);

INSERT INTO PRICES (ID, BRAND_ID, START_DATE, END_DATE, PRICE_LIST, PRODUCT_ID, PRIORITY, PRICE, CURR) VALUES (1, 1, {ts '2020-06-14 00:00:00.00'}, {ts '2020-12-31 23:59:59.00'}, 1, 35455, 0, 35.50, 'EUR');
INSERT INTO PRICES (ID, BRAND_ID, START_DATE, END_DATE, PRICE_LIST, PRODUCT_ID, PRIORITY, PRICE, CURR) VALUES (2, 1, {ts '2020-06-14 15:00:00.00'}, {ts '2020-06-14 18:30:00.00'}, 2, 35455, 1, 25.45, 'EUR');
INSERT INTO PRICES (ID, BRAND_ID, START_DATE, END_DATE, PRICE_LIST, PRODUCT_ID, PRIORITY, PRICE, CURR) VALUES (3, 1, {ts '2020-06-15 00:00:00.00'}, {ts '2020-06-15 11:00:00.00'}, 3, 35455, 1, 30.50, 'EUR');
INSERT INTO PRICES (ID, BRAND_ID, START_DATE, END_DATE, PRICE_LIST, PRODUCT_ID, PRIORITY, PRICE, CURR) VALUES (4, 1, {ts '2020-06-15 16:00:00.00'}, {ts '2020-12-31 23:59:59.00'}, 4, 35455, 1, 38.95, 'EUR'); 

COMMIT;