
/* Drop Tables */

DROP TABLE IF EXISTS COMMENT;
DROP TABLE IF EXISTS POST;
DROP TABLE IF EXISTS POST_TAG;
DROP TABLE IF EXISTS TAG;
DROP TABLE IF EXISTS USER;




/* Create Tables */

CREATE TABLE COMMENT
(
	comment_id int NOT NULL,
	post_id int,
	user_id int,
	content text,
	create_at timestamp,
	update_at timestamp,
	PRIMARY KEY (comment_id)
) WITHOUT OIDS;


CREATE TABLE POST
(
	post_id int NOT NULL,
	post_name varchar,
	content text,
	user_id int,
	create_at timestamp,
	update_at timestamp,
	PRIMARY KEY (post_id)
) WITHOUT OIDS;


CREATE TABLE POST_TAG
(
	post_tag_id int NOT NULL,
	post_id int,
	tag_id int,
	PRIMARY KEY (post_tag_id)
) WITHOUT OIDS;


CREATE TABLE TAG
(
	tag_id int NOT NULL,
	tag_name varchar,
	PRIMARY KEY (tag_id)
) WITHOUT OIDS;


CREATE TABLE USER
(
	user_id int NOT NULL,
	user_name varchar(25),
	email varchar(30),
	password varchar(32),
	is_active boolean DEFAULT '0',
	is_role boolean DEFAULT '0',
	create_at timestamp,
	update_at timestamp,
	PRIMARY KEY (user_id)
) WITHOUT OIDS;



