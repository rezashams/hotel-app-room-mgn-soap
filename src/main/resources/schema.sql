    DROP TABLE IF EXISTS room;
    DROP TABLE IF EXISTS book_room;

    CREATE TABLE room (
    id BIGINT  PRIMARY KEY,
    room_name VARCHAR(50) NOT NULL,
    room_price INT(8) NOT NULL,
    room_description VARCHAR(255)
    );

     CREATE TABLE book_room (
        id BIGINT  PRIMARY KEY,
        date_from DATE NOT NULL,
        date_to DATE NOT NULL,
        room_id BIGINT NOT NULL,
         foreign key (room_id) references room(id)
        );
