Spring Batch Database Converter

This Project uses Spring Data JPA for reading and writing data from old to new postgres database.

----------------------------------------

SQL to create table and insert data for old database: <br/> <br/>
CREATE TABLE player (<br/>
	id BIGINT PRIMARY KEY,<br/>
	description TEXT,<br/>
	birthdate TEXT<br/>
);

INSERT INTO player (id, description, birthdate) VALUES <br/>
(1, 'Lebron James', '1984-12-30'),<br/>
(2, 'James Harden', '1989-08-26'),<br/>
(3, 'Trae Young', '1998-09-19'),<br/>
(4, 'Stephen Curry', '1988-03-14'),<br/>
(5, 'Ja Morant', '1999-08-10'),<br/>
(6, 'Giannis Antetokounmpo', '1994-12-06'),<br/>
(7, 'Damian Lillard', '1990-07-15');<br/>

----------------------------------------

SQL to create table for new database:<br/> <br/>
CREATE TABLE player (<br/>
	id BIGINT PRIMARY KEY,<br/>
	name VARCHAR(255),<br/>
	birthdate DATE<br/>
);
