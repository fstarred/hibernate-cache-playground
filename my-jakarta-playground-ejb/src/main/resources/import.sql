-- CUSTOMER
Insert into CUSTOMER (ID,NAME,SURNAME,BIRTH,LAST_UPDATE) values (1,'Mario','Rossi', to_date('03-07-1986','DD-MM-RRRR'), null);
Insert into CUSTOMER (ID,NAME,SURNAME,BIRTH,LAST_UPDATE) values (2,'Fabrizio','Stellato', to_date('23-01-1979','DD-MM-RRRR'), null);
Insert into CUSTOMER (ID,NAME,SURNAME,BIRTH,LAST_UPDATE) values (3,'Daniele','Mazzafiocca', to_date('12-02-1976','DD-MM-RRRR'), null);
Insert into CUSTOMER (ID,NAME,SURNAME,BIRTH,LAST_UPDATE) values (4,'Mario','Brega', to_date('03-03-1982','DD-MM-RRRR'), null);
Insert into CUSTOMER (ID,NAME,SURNAME,BIRTH,LAST_UPDATE) values (5,'Carla','Fracconi', to_date('11-10-1956','DD-MM-RRRR'), null);
Insert into CUSTOMER (ID,NAME,SURNAME,BIRTH,LAST_UPDATE) values (6,'Jesus','Saves', to_date('30-09-1988','DD-MM-RRRR'), null);
Insert into CUSTOMER (ID,NAME,SURNAME,BIRTH,LAST_UPDATE) values (7,'Giuseppe','Italo', to_date('23-11-1968','DD-MM-RRRR'), null);
Insert into CUSTOMER (ID,NAME,SURNAME,BIRTH,LAST_UPDATE) values (8,'Gino','Loggio', to_date('25-04-2010','DD-MM-RRRR'), null);
Insert into CUSTOMER (ID,NAME,SURNAME,BIRTH,LAST_UPDATE) values (9,'Giovanni','Battesta', to_date('04-01-1963','DD-MM-RRRR'), null);
Insert into CUSTOMER (ID,NAME,SURNAME,BIRTH,LAST_UPDATE) values (10,'Alex','Di Carlo', to_date('12-12-1996','DD-MM-RRRR'), null);
Insert into CUSTOMER (ID,NAME,SURNAME,BIRTH,LAST_UPDATE) values (11,'Elena','Jacovolli', to_date('14-07-1981','DD-MM-RRRR'), null);

-- ARTICLE
Insert into ARTICLE (NAME,TYPE,DESCRIPTION) values ('PEC-BRONZE','EMAIL','PEC BRONZE BUDGET VERSION');
Insert into ARTICLE (NAME,TYPE,DESCRIPTION) values ('PEC-GOLD','EMAIL','PEC GOLD ELITE VERSION');

-- PROPERTY
Insert into PROPERTY (NAME, ARTICLE_NAME, DESCRIPTION) values ('SPACE', 'PEC-BRONZE','SPACE IN GB')
Insert into PROPERTY (NAME, ARTICLE_NAME, DESCRIPTION) values ('ARCHIVE', 'PEC-BRONZE','ARCHIVE IN GB')
Insert into PROPERTY (NAME, ARTICLE_NAME, DESCRIPTION) values ('SPACE', 'PEC-GOLD','SPACE IN GB')
