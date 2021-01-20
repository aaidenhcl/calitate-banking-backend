--Remember to comment all queries with reason and your name :D 
--for testing purposes

--really dont need these but these skeletons may come in handy in the future
--user dump
insert into user (id, username, password, credit_score, email,region) values (1, 'bob', 'asd', 700, 'aaidenplays@yahoo.com','Texas');
insert into user(id, username, profession, region) values (2, 'spongebob', 'Fry Cook', 'Under Da Sea');

--More spends
--Jeremy
INSERT INTO User(ID,REGION) VALUES (3,'Texas');
INSERT INTO User(ID,REGION) VALUES (4, 'Michigan');
INSERT INTO User(ID,REGION) VALUES (5, 'Michigan');

--credit_card dump
insert into credit_card (id,user_id, spending_limit, credit_card_number, balance) values (1,1,15000.0,'12345678901456', 10000.0);
insert into credit_card (id,user_id, status, spending_limit) values (2,2, 'active', 600);
insert into credit_card (id,spending_limit, status, user_id) values (3, 5000.0, 'active', 2);
insert into credit_card (id,spending_limit, status, user_id) values (4, 15000.0, 'active', 2);
insert into credit_card (id,spending_limit, status, user_id) values (5, 15000.0, 'inactive', 2);
INSERT INTO CREDIT_CARD(ID,USER_ID) VALUES (6,3);
INSERT INTO CREDIT_CARD(ID,USER_ID) VALUES (7,4);
INSERT INTO CREDIT_CARD(ID,USER_ID) VALUES (8,5);

--Samiylo
insert into credit_card (id, user_id, spending_limit, credit_card_number, balance) values (9,1,15000.0,'1234567890123456', 10000.0);
insert into credit_card (id,user_id, spending_limit, credit_card_number, balance) values (10,1,15000.0,'12345678901234', 10000.0);
insert into credit_card (id, apr, balance, credit_card_number, cvv, expiration_date, spending_limit, status, type, user_id) values (11, 0.20, 154.00, 1234567891532458, 4567, '2021-02-15 11:00:09.472', 5000.00, 'active', 'silver', 1);


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
insert into spend(id, amount, category, item, user_note, credit_card_id, date_created) values (1, 300, 'Technology', 'iPad', true, 2, '20010101');
insert into spend(id, date_created, amount, category, item, credit_card_id) values(2, CURDATE(), 10, 'groceries', 'bananas',1);

insert into spend(id, amount, category, item, credit_card_id, date_created) values(3, 20, 'groceries', 'tatertots',1, '20010301');
insert into spend(id, amount, category, item, credit_card_id, date_created) values(4, 30, 'groceries', 'apples',1, '20010401');
insert into spend(id, amount, category, item, credit_card_id, date_created) values(5, 5, 'groceries', 'strawberries',1, '20010501');
insert into spend(id, amount, category, item, credit_card_id, date_created) values(6, 5, 'groceries', 'milk',1, '20010510');
insert into spend(id, amount, category, item, credit_card_id, date_created) values(7, 6, 'groceries', 'juice',1, '20010701');
insert into spend(id, amount, category, item, credit_card_id, date_created) values(8, 22, 'groceries', 'poptarts',1, '20010801');
insert into spend(id, amount, category, item, credit_card_id, date_created) values(9, 34, 'fitness', 'weights',1, '20010801');
insert into spend(id, amount, category, item, credit_card_id, date_created) values(10, 23, 'fitness', 'bench',1, '20010901');
insert into spend(id, amount, category, item, credit_card_id, date_created) values(11, 67, 'fitness', 'p90x',1, '20011001');
insert into spend(id, amount, category, item, credit_card_id, date_created) values(12, 23, 'fitness', 'protein',1, '20011101');
insert into spend(id, amount, category, item, credit_card_id, date_created) values(13, 56, 'fitness', 'suppliments',1, '20011201');
insert into spend(id, amount, category, item, credit_card_id, date_created) values(14, 12, 'fitness', 'creatine',1, '20020101');
insert into spend(id, amount, category, item, credit_card_id, date_created) values(15, 46, 'entertainment', 'movies',1, '20020201');
insert into spend(id, amount, category, item, credit_card_id, date_created) values(16, 24, 'entertainment', 'video games',1, '20020301');
insert into spend(id, amount, category, item, credit_card_id, date_created) values(17, 25, 'entertainment', 'music',1, '20020304');
insert into spend(id, amount, category, item, credit_card_id, date_created) values(18, 13, 'entertainment', 'ice skating',1, '20020401');
insert into spend(id, amount, category, item, credit_card_id, date_created) values(19, 10, 'entertainment', 'canoeing',1, '20020501');
INSERT INTO SPEND(id,amount,credit_card_id, date_created) VALUES (20,10.0,6, '20020601');
INSERT INTO SPEND(id,amount,credit_card_id, date_created) VALUES (21,15.0,7, '20020701');
INSERT INTO SPEND(id,amount,credit_card_id, date_created) VALUES (22,15.0,8, '20020801');
INSERT INTO SPEND(Id,Amount,Category,Item,Credit_Card_Id, date_created) VALUES(41,100.0,'groceries','food',11, '20020901');


--payment dump
--for user story 43 need payments to calculate spend and payment differences
--Aaiden
insert into account (id, checking_balance, account_number, user_id) values (1, 1000, '989451001', 1);
insert into payment(id, amount, account_id, credit_card_id, date_created) values (1, 50, 1, 1,'20010102');
insert into payment(id, amount, account_id, credit_card_id, date_created) values (2, 10, 1, 1,'20010202');
insert into payment(id, amount, account_id, credit_card_id, date_created) values (3, 20, 1, 1,'20010302');
insert into payment(id, amount, account_id, credit_card_id, date_created) values (4, 30, 1, 1,'20010402');
insert into payment(id, amount, account_id, credit_card_id, date_created) values (5, 40, 1, 1,'20010502');
insert into payment(id, amount, account_id, credit_card_id, date_created) values (6, 45, 1, 1,'20010602');
insert into payment(id, amount, account_id, credit_card_id, date_created) values (7, 24, 1, 1,'20010702');
insert into payment(id, amount, account_id, credit_card_id, date_created) values (8, 24, 1, 1,'20010802');
insert into payment(id, amount, account_id, credit_card_id, date_created) values (9, 36, 1, 1,'20010902');
insert into payment(id, amount, account_id, credit_card_id, date_created) values (10, 46, 1, 1,'20011002');
insert into payment(id, amount, account_id, credit_card_id, date_created) values (11, 64, 1, 1,'20011102');
insert into payment(id, amount, account_id, credit_card_id, date_created) values (12, 86, 1, 1,'20011202');
insert into payment(id, amount, account_id, credit_card_id, date_created) values (13, 23, 1, 1,'20020102');
insert into payment(id, amount, account_id, credit_card_id, date_created) values (14, 75, 1, 1,'20020103');
insert into payment(id, amount, account_id, credit_card_id, date_created) values (15, 36, 1, 1,'20020302');
insert into payment(id, amount, account_id, credit_card_id, date_created) values (16, 50, 2, 10,'20020402');
insert into payment(id, amount, account_id, credit_card_id, date_created) values (17, 10, 2, 10,'20020403');
insert into payment(id, amount, account_id, credit_card_id, date_created) values (18, 20, 2, 10,'20020602');
insert into payment(id, amount, account_id, credit_card_id, date_created) values (19, 30, 2, 10,'20020702');
insert into payment(id, amount, account_id, credit_card_id, date_created) values (20, 40, 2, 10,'20020802');
insert into payment(id, amount, account_id, credit_card_id, date_created) values (21, 45, 2, 10,'20020902');
insert into payment(id, amount, account_id, credit_card_id, date_created) values (22, 24, 2, 10,'20021002');
insert into payment(id, amount, account_id, credit_card_id, date_created) values (23, 24, 2, 10,'20021102');
insert into payment(id, amount, account_id, credit_card_id, date_created) values (24, 36, 2, 10,'20021202');
insert into payment(id, amount, account_id, credit_card_id, date_created) values (25, 46, 2, 10,'20030102');
insert into payment(id, amount, account_id, credit_card_id, date_created) values (26, 64, 2, 10,'20030202');
insert into payment(id, amount, account_id, credit_card_id, date_created) values (27, 86, 2, 10,'20030302');
insert into payment(id, amount, account_id, credit_card_id, date_created) values (28, 23, 2, 10,'20030402');
insert into payment(id, amount, account_id, credit_card_id, date_created) values (29, 75, 2, 10,'20030502');
insert into payment(id, amount, account_id, credit_card_id, date_created) values (30, 36, 2, 10,'20030602');

--Jeremy User
INSERT INTO User (Id, Dob, Address, Credit_Score, Email,First_Name, Last_Name, Password, Profession, Region, Username)
VALUES(7,  '1990-12-13','Wixom,MI,48393', 700, 'jeremy.mathew@hcl.com', 'Jeremy', 'Mathew', 'password', 'engineer', 'Michigan', 'jmathewhcl');

--Jeremy Account
INSERT INTO Account(Id, Account_Number, Checking_Balance, Checking_Interest_Rate, User_Id)
VALUES(4,	123451001, 2000.00, .01, 7);

--Jeremy Credit cards
INSERT INTO Credit_Card(Id, Apr, Balance, Credit_Card_Number, Cvv, Spending_Limit, Status, Type, User_Id) 
VALUES(15, 0.2, 1500.00, '9876543219876543', 333, 2000.00, 'active', 'bronze', 7); 
INSERT INTO Credit_Card(Id, Apr, Balance, Credit_Card_Number, Cvv, Spending_Limit, Status, Type, User_Id) 
VALUES(16, 0.2, 1500.00, '9876987698769876', 444, 2000.00, 'active', 'bronze', 7); 
INSERT INTO Credit_Card(Id, Apr, Balance, Credit_Card_Number, Cvv, Spending_Limit, Status, Type, User_Id) 
VALUES(17, 0.2, 2000.00, '4321432143214321', 555, 3000.00, 'active', 'silver', 7); 
INSERT INTO Credit_Card(Id, Apr, Balance, Credit_Card_Number, Cvv, Spending_Limit, Status, Type, User_Id) 
VALUES(18, 0.2, 3000.00, '7654765476547654', 777, 5000.00, 'inactive', 'gold', 7); 

--Jeremy spends
INSERT INTO SPEND(id, amount, category, item, credit_card_id) VALUES(33, 250, 'sports', 'soccer',15);
INSERT INTO SPEND(id, amount, category, item, credit_card_id) VALUES(34, 150, 'sports', 'basketball',15);
INSERT INTO SPEND(id, amount, category, item, credit_card_id) VALUES(35, 300, 'sports', 'football',16);
INSERT INTO SPEND(id, amount, category, item, credit_card_id) VALUES(36, 100, 'sports', 'hockey',16);
INSERT INTO SPEND(id, amount, category, item, credit_card_id) VALUES(37, 201, 'groceries', 'meat',16);
INSERT INTO SPEND(id, amount, category, item, credit_card_id) VALUES(38, 199, 'groceries', 'eggs',16);
INSERT INTO SPEND(id, amount, category, item, credit_card_id) VALUES(39, 205, 'groceries', 'drinks',17);
INSERT INTO SPEND(id, amount, category, item, credit_card_id) VALUES(40, 195, 'groceries', 'ice cream',17);

--Jeremy payment
INSERT INTO PAYMENT(id, amount, account_id, credit_card_id) VALUES (41, 300, 4, 15);
INSERT INTO PAYMENT(id, amount, account_id, credit_card_id) VALUES (42, 300, 4, 15);
INSERT INTO PAYMENT(id, amount, account_id, credit_card_id) VALUES (43, 300, 4, 16);
INSERT INTO PAYMENT(id, amount, account_id, credit_card_id) VALUES (44, 300, 4, 16);
INSERT INTO PAYMENT(id, amount, account_id, credit_card_id) VALUES (45, 300, 4, 17);

--Jeremy Credit card requests
INSERT INTO Credit_Card_Request(Id,Card_Type, Offered_APR, Offered_Limit, Reason, Status, User_Id)
VALUES (18, 'Bronze', 0.2, 1000.00, 'Approved', 'approved', 7);
INSERT INTO Credit_Card_Request(Id,Card_Type, Offered_APR, Offered_Limit, Reason, Status, User_Id)
VALUES (19, 'Bronze', 0.2, 1000.00, 'Approved', 'approved', 7);
INSERT INTO Credit_Card_Request(Id,Card_Type, Offered_APR, Offered_Limit, Reason, Status, User_Id)
VALUES (20, 'Gold', 0.25, 5000.00, 'low credit score', 'rejected', 7);

--Jeremy Loan Request
INSERT INTO Loan_Request(id, status, reason, offered_amount, offered_apr, term_in_months, minimum_monthly_payment, user_id)
VALUES (9, 'approved', 'approved', 2000, .01, 12, 80, 7);
INSERT INTO Loan_Request(id, status, reason, offered_amount, offered_apr, term_in_months, minimum_monthly_payment, user_id)
VALUES (10, 'declined', 'not enough information provided', 12000, .01, 24, 500, 7);
INSERT INTO Loan_Request(id, status, reason, offered_amount, offered_apr, term_in_months, minimum_monthly_payment, user_id)
VALUES (11, 'rejected', 'Too many requests', 30000, .03, 15, 2000, 7);

--Jeremy Loan
insert into loan(id, principle, apr, term_in_months, minimum_monthly_payment, user_id)
values(2, 1200, .1, 10, 120, 7);

--Aaiden's User:
insert into user(id, username, email, DOB, first_name, last_name, address, region, credit_score, profession, date_created)
values(6, 'aaiden', 'aaidenw.dev@gmail.com', '19961107', 'aaiden', 'witten', '12345 street lane', 'texas', 800, 'developer', CURDATE());

insert into credit_card(id, spending_limit, apr, balance, expiration_date, credit_card_number, cvv, type, status, date_created, user_id)
values(12, 60000, .1, 500, '20210211', '1515121213131414', 355, 'platinum', 'actve', '20170101', 6);

insert into credit_card(id, spending_limit, apr, balance, expiration_date, credit_card_number, cvv, type, status, date_created, user_id)
values(13, 60000, .1, 500, '20210211', '1515121213131413', 356, 'platinum', 'actve', '20170101', 6);

insert into credit_card(id, spending_limit, apr, balance, expiration_date, credit_card_number, cvv, type, status, date_created, user_id)
values(14, 60000, .1, 500, '20210211', '1515121213131412', 357, 'gold', 'inactve', '20170101', 6);

insert into account (id, checking_balance, account_number, user_id) values (3, 1000, '989451002', 6);

insert into spend(id, amount, category, item, credit_card_id, date_created) values(23, 360, 'sports', 'soccer',12,'20170101');
insert into spend(id, amount, category, item, credit_card_id, date_created) values(24, 360, 'sports', 'snowboard',12,'20170202');
insert into spend(id, amount, category, item, credit_card_id, date_created) values(25, 360, 'sports', 'helmet',12,'20170302');
insert into spend(id, amount, category, item, credit_card_id, date_created) values(26, 360, 'groceries', 'steak',12,'20170402');
insert into spend(id, amount, category, item, credit_card_id, date_created) values(27, 360, 'groceries', 'meat',12,'20170502');
insert into spend(id, amount, category, item, credit_card_id, date_created) values(28, 360, 'groceries', 'eggs',12,'20170602');
insert into spend(id, amount, category, item, credit_card_id, date_created) values(29, 300, 'entertainment', 'ice skating',12,'20170702');
insert into spend(id, amount, category, item, credit_card_id, date_created) values(30, 350, 'entertainment', 'movies',12,'20170802');
insert into spend(id, amount, category, item, credit_card_id, date_created) values(31, 360, 'entertainment', 'movies',12,'20170902');
insert into spend(id, amount, category, item, credit_card_id, date_created) values(32, 360, 'sports', 'basketball',12,'20171002');

insert into payment(id, amount, account_id, credit_card_id, date_created) values (31, 300, 3, 12,'20170102');
insert into payment(id, amount, account_id, credit_card_id, date_created) values (32, 350, 3, 12,'20170202');
insert into payment(id, amount, account_id, credit_card_id, date_created) values (33, 300, 3, 12,'20170203');
insert into payment(id, amount, account_id, credit_card_id, date_created) values (34, 300, 3, 12,'20170402');
insert into payment(id, amount, account_id, credit_card_id, date_created) values (35, 360, 3, 12,'20170502');
insert into payment(id, amount, account_id, credit_card_id, date_created) values (36, 360, 3, 12,'20170602');
insert into payment(id, amount, account_id, credit_card_id, date_created) values (37, 360, 3, 12,'20170702');
insert into payment(id, amount, account_id, credit_card_id, date_created) values (38, 360, 3, 12,'20170802');
insert into payment(id, amount, account_id, credit_card_id, date_created) values (39, 360, 3, 12,'20170902');
insert into payment(id, amount, account_id, credit_card_id, date_created) values (40, 360, 3, 12,'20171002');

insert into credit_card_request (id, status, offered_limit, offered_apr, card_type, reason, user_id, request_time, last_updated)
values (12, 'approved', 30000, .1, 'platinum', null, 6, CURDATE(), CURDATE()+1);

insert into credit_card_request (id, status, offered_limit, offered_apr, card_type, reason, user_id, request_time, last_updated)
values (13, 'rejected', 30000, .1, 'platinum', 'Too many requests pending', 6, CURDATE(), CURDATE()+1);

insert into credit_card_request (id, status, offered_limit, offered_apr, card_type, reason, user_id, request_time, last_updated)
values (14, 'accepted', 30000, .1, 'gold', null, 6, CURDATE(), CURDATE()+1);

insert into credit_card_request (id, status, offered_limit, offered_apr, card_type, reason, user_id, request_time, last_updated)
values (15, 'accepted', 30000, .1, 'platinum', null, 6, CURDATE(), CURDATE()+1);

insert into credit_card_request (id, status, offered_limit, offered_apr, card_type, reason, user_id, request_time, last_updated)
values (16, 'accepted', 30000, .1, 'platinum', null, 6, CURDATE(), CURDATE()+1);

insert into credit_card_request (id, status, offered_limit, offered_apr, card_type, reason, user_id, request_time, last_updated)
values (17, 'declined', 30000, .1, 'silver', null, 6, CURDATE(), CURDATE()+1);

insert into loan_request(id, status, reason, offered_amount, offered_apr, term_in_months, minimum_monthly_payment, user_id)
values (1, 'rejected', 'Too many requests', 30000, .15, 48, 625, 6);

insert into loan_request(id, status, reason, offered_amount, offered_apr, term_in_months, minimum_monthly_payment, user_id)
values (2, 'rejected', 'Too many requests', 30000, .15, 48, 625, 6);

insert into loan_request(id, status, reason, offered_amount, offered_apr, term_in_months, minimum_monthly_payment, user_id)
values (3, 'rejected', 'Bad credit history', 30000, .15, 48, 625, 6);

insert into loan_request(id, status, reason, offered_amount, offered_apr, term_in_months, minimum_monthly_payment, user_id)
values (4, 'rejected', 'Bad credit history', 30000, .15, 48, 625, 6);

insert into loan_request(id, status, reason, offered_amount, offered_apr, term_in_months, minimum_monthly_payment, user_id)
values (5, 'rejected', 'Bad credit history', 30000, .15, 48, 625, 6);

insert into loan_request(id, status, reason, offered_amount, offered_apr, term_in_months, minimum_monthly_payment, user_id)
values (6, 'approved', null, 30000, .15, 48, 625, 6);

insert into loan_request(id, status, reason, offered_amount, offered_apr, term_in_months, minimum_monthly_payment, user_id)
values (7, 'accepted', null, 30000, .15, 48, 625, 6);

insert into loan_request(id, status, reason, offered_amount, offered_apr, term_in_months, minimum_monthly_payment, user_id)
values (8, 'declined', null, 30000, .15, 48, 625, 6);

insert into loan(id, principle, apr, term_in_months, minimum_monthly_payment, user_id)
values(1, 30000, .15, 48, 625, 6);

