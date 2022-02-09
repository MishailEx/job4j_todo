CREATE TABLE IF NOT EXISTS item (
                         id int GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
                         description VARCHAR NOT NULL,
                         created VARCHAR NOT NULL,
                         done boolean NOT NULL,
                         user_id int REFERENCES users(id)
);

CREATE TABLE IF NOT EXISTS users (
                         id int GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
                         name VARCHAR NOT NULL UNIQUE,
                         password VARCHAR NOT NULL
);