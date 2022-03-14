DROP TABLE IF EXISTS user, pet;


CREATE TABLE user(
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(256),
    age INT,
    pet_id BIGINT
);

CREATE TABLE pet(
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    breed VARCHAR(256)
);

ALTER TABLE user add foreign key (pet_id) references pet(id);