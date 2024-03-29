USE chess;

CREATE TABLE chess_room (
    id BIGINT NOT NULL AUTO_INCREMENT,
    turn VARCHAR(10) NOT NULL,

    PRIMARY KEY (id)
);

INSERT INTO chess_room (turn) VALUES ("WHITE");
