delete from account_transaction;
delete  from account;
delete  from user;
delete  from currency;
delete  from account_type;
delete  from transaction_type;


INSERT INTO currency (id, type) VALUES (1,'AUD');
INSERT INTO currency (id, type) VALUES (2,'USD');
INSERT INTO currency (id, type) VALUES (3, 'SGD');

INSERT INTO account_type (id, type) VALUES (1, 'CURRENT');
INSERT INTO account_type (id, type) VALUES (2, 'SAVINGS');

INSERT INTO transaction_type (id, type) VALUES (1, 'CREDIT');
INSERT INTO transaction_type (id, type) VALUES (2, 'DEBIT');

INSERT INTO user (id, first_name, last_name) VALUES (1, 'Tayla', 'Smith');

INSERT INTO account (id, account_name, account_number, account_type_id, currency_id, user_id, balance_date, available_balance) VALUES (1, 'SGSavings726', '3421123332', 2, 3, 1, '2021-07-22', 2345.45);
INSERT INTO account (id, account_name, account_number, account_type_id, currency_id, user_id, balance_date, available_balance ) VALUES (2, 'AUSavings933', '3421123233', 2, 1, 1, '2021-07-20', 234.45);
INSERT INTO account (id, account_name, account_number, account_type_id, currency_id, user_id, balance_date, available_balance ) VALUES (3, 'SGCurrent166', '3421123345', 1, 3, 1, '2021-07-19', 23423.45);
INSERT INTO account (id, account_name, account_number, account_type_id, currency_id, user_id , balance_date, available_balance) VALUES (4, 'USSavings234', '3421123753', 2, 2, 1, '2021-07-22', 2378.45);


INSERT INTO account_transaction (id, amount, transaction_date, account_id, transaction_type_id, narration ) VALUES (1, 9540.48, '2021-07-10', 1, 1, 'Deposit from salary');
INSERT INTO account_transaction (id, amount, transaction_date, account_id, transaction_type_id, narration ) VALUES (2, 100.00, '2021-07-11', 1, 2, 'Lunch');
INSERT INTO account_transaction (id, amount, transaction_date, account_id, transaction_type_id, narration ) VALUES (3, 200.00, '2021-07-13', 2, 2, 'Petrol');
INSERT INTO account_transaction (id, amount, transaction_date, account_id, transaction_type_id, narration ) VALUES (4, 50.00, '2021-07-14', 2, 2, 'Coffee');
INSERT INTO account_transaction (id, amount, transaction_date, account_id, transaction_type_id, narration ) VALUES (5, 40.48, '2021-07-15', 3, 1, 'Deposit from Rent');
INSERT INTO account_transaction (id, amount, transaction_date, account_id, transaction_type_id, narration ) VALUES (6, 230.00, '2021-07-17', 3, 2, 'Dinner');
INSERT INTO account_transaction (id, amount, transaction_date, account_id, transaction_type_id, narration ) VALUES (7, 260.00, '2021-07-19', 4, 2, 'Travel');
INSERT INTO account_transaction (id, amount, transaction_date, account_id, transaction_type_id, narration ) VALUES (8, 80.00, '2021-07-20', 4, 2, 'Shopping');
INSERT INTO account_transaction (id, amount, transaction_date, account_id, transaction_type_id, narration ) VALUES (9, 250.00, '2021-07-21', 2, 2, 'Grocery');
INSERT INTO account_transaction (id, amount, transaction_date, account_id, transaction_type_id, narration ) VALUES (10, 450.00, '2021-07-22', 4, 2, 'Furniture');


INSERT INTO user (id, first_name, last_name ) VALUES (2, 'John', 'Smith');

INSERT INTO account (id, account_name, account_number, account_type_id, currency_id, user_id, balance_date, available_balance ) VALUES (5, 'JS_SGSavings726', '7821123332', 2, 3, 2, '2021-07-22', 2345.45);
INSERT INTO account (id, account_name, account_number, account_type_id, currency_id, user_id, balance_date, available_balance ) VALUES (6, 'JS_AUSavings933', '7821123233', 2, 1, 2, '2021-07-20', 234.45);
INSERT INTO account (id, account_name, account_number, account_type_id, currency_id, user_id, balance_date, available_balance ) VALUES (7, 'JS_SGCurrent166', '7821123345', 1, 3, 2, '2021-07-19', 23423.45);
INSERT INTO account (id, account_name, account_number, account_type_id, currency_id, user_id, balance_date, available_balance ) VALUES (8, 'JS_USSavings234', '7821123753', 2, 2, 2, '2021-07-22', 2378.45);


INSERT INTO account_transaction (id, amount, transaction_date, account_id, transaction_type_id, narration ) VALUES (11, 9540.48, '2021-07-10', 5, 1, 'Deposit from salary');
INSERT INTO account_transaction (id, amount, transaction_date, account_id, transaction_type_id, narration ) VALUES (12, 100.00, '2021-07-12', 5, 2, 'Lunch');
INSERT INTO account_transaction (id, amount, transaction_date, account_id, transaction_type_id, narration ) VALUES (13, 200.00, '2021-07-13', 6, 2, 'Petrol');
INSERT INTO account_transaction (id, amount, transaction_date, account_id, transaction_type_id, narration ) VALUES (14, 50.00, '2021-07-14', 7, 2, 'Coffee');
INSERT INTO account_transaction (id, amount, transaction_date, account_id, transaction_type_id, narration ) VALUES (15, 40.48, '2021-07-15', 7, 1, 'Deposit from Rent');
INSERT INTO account_transaction (id, amount, transaction_date, account_id, transaction_type_id, narration ) VALUES (16, 230.00, '2021-07-16', 5, 2, 'Dinner');
INSERT INTO account_transaction (id, amount, transaction_date, account_id, transaction_type_id, narration ) VALUES (17, 260.00, '2021-07-18', 6, 2, 'Travel');
INSERT INTO account_transaction (id, amount, transaction_date, account_id, transaction_type_id, narration ) VALUES (18, 80.00, '2021-07-20', 8, 2, 'Shopping');
INSERT INTO account_transaction (id, amount, transaction_date, account_id, transaction_type_id, narration ) VALUES (19, 250.00, '2021-07-21', 8, 2, 'Grocery');
INSERT INTO account_transaction (id, amount, transaction_date, account_id, transaction_type_id, narration ) VALUES (20, 450.00, '2021-07-23', 7, 2, 'Furniture');