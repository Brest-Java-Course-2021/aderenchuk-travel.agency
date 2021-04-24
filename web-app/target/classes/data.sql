INSERT INTO TOUR (TOUR_ID, DIRECTION, DATE_TOUR)
VALUES (101, 'BREST-MOSCOW', '2021-08-01'),
       (102, 'MINSK-ROME', '2021-09-01'),
       (103, 'MOSCOW-BARCELONA', '2021-06-15'),
       (104, 'MINSK-RIGA', '2021-07-02'),
       (105, 'MINSK-DUBAI', '2021-05-25');


INSERT INTO CLIENT (FIRSTNAME, LASTNAME, TOUR_ID)
VALUES ('Ihor', 'Dmitriev', 101),
       ('Alex', 'Volkanovski', 103),
       ('Irina', 'Sheyk', 103),
       ('Leonel', 'Messi', 104),
       ('Polina', 'Chistyakova', 104),
       ('Anna', 'Sedakova', 102),
       ('Gareth', 'Bale', 102),
       ('Toni', 'Kross', 102);

