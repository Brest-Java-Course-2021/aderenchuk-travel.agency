INSERT INTO TOUR (TOUR_ID, DIRECTION, DATE_TOUR)
VALUES (101, 'BREST-MOSCOW', '2021-08-01'),
       (102, 'MINSK-ROME', '2022-09-02'),
       (103, 'FRANKFURT-BARCELONA', '2020-06-15'),
       (104, 'PORTY-RIGA', '2019-07-02'),
       (105, 'VASHINGTON-DUBAI', '2023-05-25');


INSERT INTO CLIENT (FIRSTNAME, LASTNAME, TOUR_ID)
VALUES ('Ihor', 'Dmitriev', 101),
       ('Alex', 'Volkanovski', 103),
       ('Irina', 'Sheyk', 103),
       ('Leonel', 'Messi', 104),
       ('Polina', 'Chistyakova', 104),
       ('Anna', 'Sedakova', 102),
       ('Gareth', 'Bale', 102),
       ('Toni', 'Kross', 102);