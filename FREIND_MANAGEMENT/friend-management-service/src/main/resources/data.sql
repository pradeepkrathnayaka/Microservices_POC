INSERT INTO USER(ID, EMAIL, USERNAME) VALUES(USER_SEQ.nextval, 'pradeep@gmail.com', 'Pradeep'); --1
INSERT INTO USER(ID, EMAIL, USERNAME) VALUES(USER_SEQ.nextval,'kumarasiri@gmail.com', 'Kumarasiri');--2
INSERT INTO USER(ID, EMAIL, USERNAME) VALUES(USER_SEQ.nextval,'rathnayaka@gmail.com', 'Rathnayaka');--3

-------------------------------------------------------------------------------------

INSERT INTO FRIEND_RELATION (USER1ID, USER2ID, STATUS) VALUES (1, 2, 'Pending');
INSERT INTO FRIEND_RELATION (USER1ID, USER2ID, STATUS) VALUES (1, 3, 'Blocked');
INSERT INTO FRIEND_RELATION (USER1ID, USER2ID, STATUS) VALUES (2, 1, 'Accepted');
INSERT INTO FRIEND_RELATION (USER1ID, USER2ID, STATUS) VALUES (2, 3, 'Declined');
INSERT INTO FRIEND_RELATION (USER1ID, USER2ID, STATUS) VALUES (3, 1, 'Accepted');
INSERT INTO FRIEND_RELATION (USER1ID, USER2ID, STATUS) VALUES (3, 2, 'Declined');


-------------------------------------------------------------------------------------
INSERT INTO FRIEND_SUBSCRIBE (REQUESTOR, TARGET) VALUES ('pradeep@gmail.com', 'kumarasiri@gmail.com');
INSERT INTO FRIEND_SUBSCRIBE (REQUESTOR, TARGET) VALUES ('pradeep@gmail.com', 'rathnayaka@gmail.com');
INSERT INTO FRIEND_SUBSCRIBE (REQUESTOR, TARGET) VALUES ('kumarasiri@gmail.com', 'pradeep@gmail.com');
INSERT INTO FRIEND_SUBSCRIBE (REQUESTOR, TARGET) VALUES ('kumarasiri@gmail.com', 'rathnayaka@gmail.com');
INSERT INTO FRIEND_SUBSCRIBE (REQUESTOR, TARGET) VALUES ('rathnayaka@gmail.com', 'pradeep@gmail.com');
INSERT INTO FRIEND_SUBSCRIBE (REQUESTOR, TARGET) VALUES ('rathnayaka@gmail.com', 'kumarasiri@gmail.com');

-------------------------------------------------------------------------------------
INSERT INTO FRIEND_BLOCK (REQUESTOR, TARGET) VALUES ('pradeep@gmail.com', 'rathnayaka@gmail.com');
-------------------------------------------------------------------------------------
commit;