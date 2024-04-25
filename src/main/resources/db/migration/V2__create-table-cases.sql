CREATE TABLE cases(

    id SERIAL PRIMARY KEY,
    total BIGINT NOT NULL,
    new BIGINT NOT NULL,
    date VARCHAR(10) NOT NULL,
    country_id INTEGER NOT NULL,

    FOREIGN KEY (country_id) REFERENCES countries (id)
    ON DELETE CASCADE
    ON UPDATE CASCADE

);

