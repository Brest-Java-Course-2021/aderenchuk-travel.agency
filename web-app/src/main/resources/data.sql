INSERT INTO TOUR (TOUR_ID, DIRECTION, DATE_TOUR)
VALUES (106, 'BREST-KYPR', '2023-10-01'),
       (107, 'SARATOV-ASTANA', '2023-12-01'),
       (108, 'KYPR-BARCELONA', '2023-12-15'),
       (109, 'VENA-RIGA', '2024-05-02'),
       (110, 'AMSTERDAM-DUBAI', '2025-04-12');


INSERT INTO CLIENT (FIRSTNAME, LASTNAME, TOUR_ID)
VALUES ('Griha', 'Bogin', 106),
       ('Leha', 'Vlonskiy', 108),
       ('Sasha', 'Mir', 108),
       ('Kin', 'Vel', 109),
       ('Kun', 'Malonskaya', 109),
       ('Vera', 'Lodkina', 107),
       ('Minay', 'Imirova', 107),
       ('Valet', 'Moss', 107);

