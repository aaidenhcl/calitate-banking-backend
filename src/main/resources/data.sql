--Remember to comment all queries with reason and your name :D 
--for testing purposes
--really dont need these but these skeletons may come in handy in the future 

--user dump
insert into user (id, username, password, credit_score, email,region) values (1, 'bob', 'asd', 700, 'aaidenplays@yahoo.com','Texas');
insert into user(id, username, profession, region) values (2, 'spongebob', 'Fry Cook', 'Under Da Sea'); 





--More spends
--Jeremy
INSERT INTO USER(ID,REGION) VALUES (3,'Texas');
INSERT INTO USER(ID,REGION) VALUES (4, 'Michigan');
INSERT INTO USER(ID,REGION) VALUES (5, 'Michigan');

--credit_card dump
insert into credit_card (id,user_id) values (1,1);
insert into credit_card (id,user_id) values (2,2);
insert into credit_card (id,spending_limit, status, user_id) values (3, 5000.0, 'active', 2);
insert into credit_card (id,spending_limit, status, user_id) values (4, 15000.0, 'active', 2);
insert into credit_card (id,spending_limit, status, user_id) values (5, 15000.0, 'inactive', 2);
insert into credit_card (id,user_id, spending_limit, credit_card_number, balance) values (10,1,15000.0,'1234567890123456', 10000.0);


INSERT INTO CREDIT_CARD(ID,USER_ID) VALUES (6,3);
INSERT INTO CREDIT_CARD(ID,USER_ID) VALUES (7,4);
INSERT INTO CREDIT_CARD(ID,USER_ID) VALUES (8,5);
insert into credit_card (id, user_id) values (9,1);

--account dump
insert into account (id, checking_balance, account_number, user_id) values (2, 1000, '989451001', 1);

--credit_card_request dump
--user story 36
--for No of credit card rejected along with reasons
--Aaiden
insert into credit_card_request (id, status, user_id) values (1, 'approved', 1 );
insert into credit_card_request (id, status, card_type, user_id) values (2, 'approved', 'gold', 1);
insert into credit_card_request (id, status, reason, user_id) values (3, 'rejected', 'Bad payment history', 2);
insert into credit_card_request (id, status, reason, user_id) values (4, 'rejected', 'Bad payment history', 2);
insert into credit_card_request (id, status, reason, user_id) values (5, 'rejected', 'Too many requests pending', 1);
insert into credit_card_request (id, status, reason, user_id) values (6, 'rejected', 'Too many requests pending', 1);
insert into credit_card_request (id, status, reason, user_id) values (7, 'rejected', 'Too many requests pending', 2);
insert into credit_card_request (id, status, reason, user_id) values (8, 'rejected', 'Overdrafted account(s)', 1);
insert into credit_card_request (id, status, reason, user_id) values (9, 'rejected', 'Overdrafted account(s)', 2);
insert into credit_card_request (id, status, card_type, user_id, request_time) values (10, 'approved', 'gold', 1, '20201220');
--really dont need these but these skeletons may come in handy in the future 
--(aaiden)
insert into credit_card_request (id, status, user_id) values (11, 'approved', 1 );

--spend dump
--user story 38
--for Credit card usage/spend pattern –E.g., Grocery/ Fitness/ Entertainment etc.  
--(Aaiden)
insert into spend(id, amount, category, item, user_note, credit_card_id) values (1, 300, 'Technology', 'iPad', true, 2);
insert into spend(id, date_created, amount, category, item, credit_card_id) values(2, CURDATE(), 10, 'groceries', 'bananas',1);
insert into spend(id, amount, category, item, credit_card_id) values(3, 20, 'groceries', 'tatertots',1);
insert into spend(id, amount, category, item, credit_card_id) values(4, 30, 'groceries', 'apples',1);
insert into spend(id, amount, category, item, credit_card_id) values(5, 5, 'groceries', 'strawberries',1);
insert into spend(id, amount, category, item, credit_card_id) values(6, 5, 'groceries', 'milk',1);
insert into spend(id, amount, category, item, credit_card_id) values(7, 6, 'groceries', 'juice',1);
insert into spend(id, amount, category, item, credit_card_id) values(8, 22, 'groceries', 'poptarts',1);
insert into spend(id, amount, category, item, credit_card_id) values(9, 34, 'fitness', 'weights',1);
insert into spend(id, amount, category, item, credit_card_id) values(10, 23, 'fitness', 'bench',1);
insert into spend(id, amount, category, item, credit_card_id) values(11, 67, 'fitness', 'p90x',1);
insert into spend(id, amount, category, item, credit_card_id) values(12, 23, 'fitness', 'protein',1);
insert into spend(id, amount, category, item, credit_card_id) values(13, 56, 'fitness', 'suppliments',1);
insert into spend(id, amount, category, item, credit_card_id) values(14, 12, 'fitness', 'creatine',1);
insert into spend(id, amount, category, item, credit_card_id) values(15, 46, 'entertainment', 'movies',1);
insert into spend(id, amount, category, item, credit_card_id) values(16, 24, 'entertainment', 'video games',1);
insert into spend(id, amount, category, item, credit_card_id) values(17, 25, 'entertainment', 'music',1);
insert into spend(id, amount, category, item, credit_card_id) values(18, 13, 'entertainment', 'ice skating',1);
insert into spend(id, amount, category, item, credit_card_id) values(19, 10, 'entertainment', 'canoeing',1);
INSERT INTO SPEND(id,amount,credit_card_id) VALUES (20,10.0,6);
INSERT INTO SPEND(id,amount,credit_card_id) VALUES (21,15.0,7);
INSERT INTO SPEND(id,amount,credit_card_id) VALUES (22,15.0,8);

--payment dump
--for user story 43 need payments to calculate spend and payment differences
--Aaiden
insert into account (id, checking_balance, account_number, user_id) values (1, 1000, '989451001', 1);
insert into payment(id, amount, account_id, credit_card_id) values (1, 50, 1, 1);
insert into payment(id, amount, account_id, credit_card_id) values (2, 10, 1, 1);
insert into payment(id, amount, account_id, credit_card_id) values (3, 20, 1, 1);
insert into payment(id, amount, account_id, credit_card_id) values (4, 30, 1, 1);
insert into payment(id, amount, account_id, credit_card_id) values (5, 40, 1, 1);
insert into payment(id, amount, account_id, credit_card_id) values (6, 45, 1, 1);
insert into payment(id, amount, account_id, credit_card_id) values (7, 24, 1, 1);
insert into payment(id, amount, account_id, credit_card_id) values (8, 24, 1, 1);
insert into payment(id, amount, account_id, credit_card_id) values (9, 36, 1, 1);
insert into payment(id, amount, account_id, credit_card_id) values (10, 46, 1, 1);
insert into payment(id, amount, account_id, credit_card_id) values (11, 64, 1, 1);
insert into payment(id, amount, account_id, credit_card_id) values (12, 86, 1, 1);
insert into payment(id, amount, account_id, credit_card_id) values (13, 23, 1, 1);
insert into payment(id, amount, account_id, credit_card_id) values (14, 75, 1, 1);
insert into payment(id, amount, account_id, credit_card_id) values (15, 36, 1, 1); 
insert into payment(id, amount, account_id, credit_card_id) values (16, 50, 2, 10);
insert into payment(id, amount, account_id, credit_card_id) values (17, 10, 2, 10);
insert into payment(id, amount, account_id, credit_card_id) values (18, 20, 2, 10);
insert into payment(id, amount, account_id, credit_card_id) values (19, 30, 2, 10);
insert into payment(id, amount, account_id, credit_card_id) values (20, 40, 2, 10);
insert into payment(id, amount, account_id, credit_card_id) values (21, 45, 2, 10);
insert into payment(id, amount, account_id, credit_card_id) values (22, 24, 2, 10);
insert into payment(id, amount, account_id, credit_card_id) values (23, 24, 2, 10);
insert into payment(id, amount, account_id, credit_card_id) values (24, 36, 2, 10);
insert into payment(id, amount, account_id, credit_card_id) values (25, 46, 2, 10);
insert into payment(id, amount, account_id, credit_card_id) values (26, 64, 2, 10);
insert into payment(id, amount, account_id, credit_card_id) values (27, 86, 2, 10);
insert into payment(id, amount, account_id, credit_card_id) values (28, 23, 2, 10);
insert into payment(id, amount, account_id, credit_card_id) values (29, 75, 2, 10);
insert into payment(id, amount, account_id, credit_card_id) values (30, 36, 2, 10); 
