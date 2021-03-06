DROP TABLE FRIEND_BLOCK IF EXISTS; 
DROP TABLE FRIEND_RELATION IF EXISTS; 
DROP TABLE FRIEND_SUBSCRIBE IF EXISTS; 
DROP SEQUENCE USER_SEQ IF EXISTS;
DROP TABLE USER IF EXISTS; 
commit;
-------------------------------------------------------------------------------------

CREATE TABLE USER( 
	ID BIGINT PRIMARY KEY AUTO_INCREMENT NOT NULL,
	EMAIL VARCHAR(255) UNIQUE NOT NULL,
	USERNAME VARCHAR(255) UNIQUE NOT NULL
);

CREATE SEQUENCE USER_SEQ 
	MINVALUE 1
    MAXVALUE 9999999999
    START WITH 1
    INCREMENT BY 1
    CACHE 20; 
    
commit;    
-------------------------------------------------------------------------------------

CREATE TABLE FRIEND_RELATION( 
	USER1ID BIGINT NOT NULL,
	USER2ID BIGINT NOT NULL,
	STATUS VARCHAR(255) NOT NULL
);

ALTER TABLE FRIEND_RELATION
  ADD (
    CONSTRAINT FRIEND_PK PRIMARY KEY (USER1ID, USER2ID)
  );
commit;
-------------------------------------------------------------------------------------

CREATE TABLE FRIEND_SUBSCRIBE( 
	REQUESTOR VARCHAR(255) NOT NULL,
	TARGET VARCHAR(255) NOT NULL
);

ALTER TABLE FRIEND_SUBSCRIBE
  ADD (
    CONSTRAINT SUBSCRIBE_PK PRIMARY KEY (REQUESTOR, TARGET)
  );

commit;

-------------------------------------------------------------------------------------
CREATE TABLE FRIEND_BLOCK( 
	REQUESTOR VARCHAR(255) NOT NULL,
	TARGET VARCHAR(255) NOT NULL
);
ALTER TABLE FRIEND_BLOCK
  ADD (
    CONSTRAINT BLOCK_PK PRIMARY KEY (REQUESTOR, TARGET)
  );
commit;