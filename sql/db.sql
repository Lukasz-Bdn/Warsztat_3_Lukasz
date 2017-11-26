CREATE TABLE  user_group(
	id INT AUTO_INCREMENT,
	name VARCHAR(255),
	PRIMARY KEY(id)
);

CREATE TABLE users(
	id BIGINT(20) AUTO_INCREMENT,
	username VARCHAR(255),
	email VARCHAR(255) UNIQUE,
	password VARCHAR(245),
	user_group_id INT,
	PRIMARY KEY(id),
	FOREIGN KEY(user_group_id) REFERENCES user_group(id)
);

CREATE TABLE exercise(
	id INT(11) AUTO_INCREMENT,
	title VARCHAR(255),
	description TEXT,
	PRIMARY KEY(id)
);

CREATE TABLE solution(
	id INT(11) AUTO_INCREMENT,
	created DATETIME,
	updated DATETIME,
	description TEXT,
	exercise_id INT(11),
	users_id BIGINT(20),
	PRIMARY KEY(id),
	FOREIGN KEY(exercise_id) REFERENCES exercise(id),
	FOREIGN KEY(users_id) REFERENCES users(id)
);