--Remember to comment all queries with reason and your name :D 
--for testing purposes
insert into credit_card (id) values (1);


--really dont need these but these skeletons may come in handy in the future 
--(aaiden)
insert into user (id, username, password, credit_score) values (1, 'bob', 'asd', 700);
insert into credit_card_request (id, status, user_id) values (1, 'approved', 1 );

--Add spends
--Samiylo
insert into spend(id, amount, category, item, user_note, credit_card_id) values (1, 300, 'Technology', 'iPad', true, 1);

--Matthew
insert into user(id, profession, region) values (2, 'Fry Cook', 'Texas'); 

insert into credit_card_request (id, status, card_type, user_id) values (2, 'approved', 'gold', 1);

--user story 36
--for No of credit card rejected along with reasons
--Aaiden
insert into credit_card_request (id, status, reason, user_id) values (3, 'rejected', 'Bad payment history', 2);
insert into credit_card_request (id, status, reason, user_id) values (4, 'rejected', 'Bad payment history', 2);
insert into credit_card_request (id, status, reason, user_id) values (5, 'rejected', 'Too many requests pending', 1);
insert into credit_card_request (id, status, reason, user_id) values (6, 'rejected', 'Too many requests pending', 1);
insert into credit_card_request (id, status, reason, user_id) values (7, 'rejected', 'Too many requests pending', 2);
insert into credit_card_request (id, status, reason, user_id) values (8, 'rejected', 'Overdrafted account(s)', 1);
insert into credit_card_request (id, status, reason, user_id) values (9, 'rejected', 'Overdrafted account(s)', 2);

--user story 38
--for Credit card usage/spend pattern –E.g., Grocery/ Fitness/ Entertainment etc.  
--(Aaiden)
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



insert into credit_card_request (id, status, card_type, user_id, request_time) values (10, 'Approved', 'gold', 1, '20201220');


