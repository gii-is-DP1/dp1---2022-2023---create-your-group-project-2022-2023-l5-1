-- One admin user, named admin1 with passwor 4dm1n and authority admin
INSERT INTO users(username,password,name,enabled) VALUES ('admin1','4dm1n','Admin',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (1,'admin1','admin');

INSERT INTO users(username,password,name,enabled) VALUES ('player1','player1','Pepe',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (2,'player1','player');


INSERT INTO users(username,password,name,enabled) VALUES ('player2','player2','Paco',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (3,'player2','player');
INSERT INTO users(username,password,name,enabled) VALUES ('player3','player3','Curro',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (4,'player3','player');
