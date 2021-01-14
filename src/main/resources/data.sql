--Remember to comment all queries with reason and your name :D 
--for testing purposes
insert into credit_card (id) values (1);


--really dont need these but these skeletons may come in handy in the future 
--(aaiden)
--insert into user (id, username, password, credit_score) values (1, 'bob', 'asd', 700);
--insert into credit_card_request (id, status, user_id) values (11, 'approved', 1 );

--Add spends
--Samiylo
insert into spend(id) values (1);

--More spends
--Jeremy
INSERT INTO USER(ID,REGION) VALUES (4,'Texas');
INSERT INTO USER(ID,REGION) VALUES (5, 'Michigan');
INSERT INTO USER(ID,REGION) VALUES (6, 'Michigan');

INSERT INTO CREDIT_CARD(ID,USER_ID) VALUES (2,4);
INSERT INTO CREDIT_CARD(ID,USER_ID) VALUES (3,5);
INSERT INTO CREDIT_CARD(ID,USER_ID) VALUES (4,5);

INSERT INTO SPEND(id,amount,credit_card_id) VALUES (2,10.0,2);
INSERT INTO SPEND(id,amount,credit_card_id) VALUES (3,15.0,3);
INSERT INTO SPEND(id,amount,credit_card_id) VALUES (4,15.0,4);