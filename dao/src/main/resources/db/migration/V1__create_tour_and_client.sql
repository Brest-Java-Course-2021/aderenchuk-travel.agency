CREATE SCHEMA IF NOT EXISTS testdb;
DROP TABLE IF EXISTS testdb.CLIENT;
DROP TABLE IF EXISTS testdb.TOUR;

CREATE TABLE testdb.TOUR
(
    TOUR_ID   INT         NOT NULL AUTO_INCREMENT,
    DIRECTION VARCHAR(50) NOT NULL UNIQUE,
    DATE_TOUR DATE        NOT NULL,
    PRIMARY KEY (TOUR_ID)
);

CREATE TABLE testdb.CLIENT
(
    CLIENT_ID INT         NOT NULL AUTO_INCREMENT,
    FIRSTNAME VARCHAR(20) NOT NULL,
    LASTNAME VARCHAR(30)  NOT NULL,
    TOUR_ID INT           NOT NULL,
    PRIMARY KEY (CLIENT_ID),
    FOREIGN KEY (TOUR_ID)
    REFERENCES TOUR (TOUR_ID) ON DELETE CASCADE
);