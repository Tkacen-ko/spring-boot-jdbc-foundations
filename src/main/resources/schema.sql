DROP TABLE IF EXISTS pet, user;


CREATE TABLE pet
(
    id    BIGINT PRIMARY KEY AUTO_INCREMENT,
    breed VARCHAR(256)
);

CREATE TABLE user
(
    id     BIGINT PRIMARY KEY AUTO_INCREMENT,
    name   VARCHAR(256),
    age    INT,
    pet_id BIGINT references pet (id)
);


