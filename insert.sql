-- Pracownik
INSERT INTO Pracownik (login, imie, nazwisko, email, haslo)
VALUES ('admin', 'Admin', 'Admin', 'admin@admin.com', 'admin');
INSERT INTO Pracownik (login, imie, nazwisko, email, haslo)
VALUES ('aborkowski', 'Artur', 'Borkowski', 'aborkowski@gmail.com', 'has1o');


-- Wydawnictwo
INSERT INTO Wydawnictwo (nazwa) VALUES ('Prószyński i S-ka'); -- 1
INSERT INTO Wydawnictwo (nazwa) VALUES ('Rebis'); -- 2
INSERT INTO Wydawnictwo (nazwa) VALUES ('Media Rodzina'); -- 3
INSERT INTO Wydawnictwo (nazwa) VALUES ('W.A.B.'); -- 4
INSERT INTO Wydawnictwo (nazwa) VALUES ('Wydawnictwo Literackie'); -- 5
INSERT INTO Wydawnictwo (nazwa) VALUES ('Wydawnictwo Iskry'); -- 6


-- Dziedzina
INSERT INTO Dziedzina ( Dziedzina_idDziedzina, nazwa)
VALUES (Null,'Beletrystyka'); -- 1
INSERT INTO Dziedzina (Dziedzina_idDziedzina, nazwa)
VALUES (Null,'Literatura faktu'); -- 2
INSERT INTO Dziedzina (Dziedzina_idDziedzina, nazwa)
VALUES (Null,'Literatura popularnonaukowa'); -- 3
INSERT INTO Dziedzina (Dziedzina_idDziedzina, nazwa)
VALUES (Null,'Literatura dziecięca'); -- 4
INSERT INTO Dziedzina (Dziedzina_idDziedzina, nazwa)
VALUES (Null,'Komiksy'); -- 5
INSERT INTO Dziedzina (Dziedzina_idDziedzina, nazwa)
VALUES (Null,'Poezja, dramat, satyra'); -- 6
INSERT INTO Dziedzina (Dziedzina_idDziedzina, nazwa)
VALUES (Null,'Inne'); -- 7

INSERT INTO Dziedzina (Dziedzina_idDziedzina, nazwa)
VALUES (1,'fantasy, science fiction'); -- 8
INSERT INTO Dziedzina (Dziedzina_idDziedzina, nazwa)
VALUES (1,'klasyka'); -- 9
INSERT INTO Dziedzina (Dziedzina_idDziedzina, nazwa)
VALUES (1,'kryminał, sensacja, thriller'); -- 10
INSERT INTO Dziedzina (Dziedzina_idDziedzina, nazwa)
VALUES (1,'literatura piękna'); -- 11
INSERT INTO Dziedzina (Dziedzina_idDziedzina, nazwa)
VALUES (1,'literatura młodzieżowa'); -- 12

INSERT INTO Dziedzina (Dziedzina_idDziedzina, nazwa)
VALUES (2,'biografia, autobiografia, pamiętnik'); -- 13
INSERT INTO Dziedzina (Dziedzina_idDziedzina, nazwa)
VALUES (2,'reportaż'); -- 14

INSERT INTO Dziedzina (Dziedzina_idDziedzina, nazwa)
VALUES (3,'astronomia, astrofizyka'); -- 15
INSERT INTO Dziedzina (Dziedzina_idDziedzina, nazwa)
VALUES (3,'encyklopedie, słowniki'); -- 16
INSERT INTO Dziedzina (Dziedzina_idDziedzina, nazwa)
VALUES (3,'historia'); -- 17
INSERT INTO Dziedzina (Dziedzina_idDziedzina, nazwa)
VALUES (3,'informatyka, matematyka');  -- 18
INSERT INTO Dziedzina (Dziedzina_idDziedzina, nazwa)
VALUES (3,'popularnonaukowa'); -- 19

INSERT INTO Dziedzina (Dziedzina_idDziedzina, nazwa)
VALUES (4,'bajki'); -- 20
INSERT INTO Dziedzina (Dziedzina_idDziedzina, nazwa)
VALUES (4,'baśnie, legendy, podania'); -- 21
INSERT INTO Dziedzina (Dziedzina_idDziedzina, nazwa)
VALUES (4,'popularnonaukowa dziecięca'); -- 22

INSERT INTO Dziedzina (Dziedzina_idDziedzina, nazwa)
VALUES (6,'poezja'); -- 23
INSERT INTO Dziedzina (Dziedzina_idDziedzina, nazwa)
VALUES (6,'satyra'); -- 24
INSERT INTO Dziedzina (Dziedzina_idDziedzina, nazwa)
VALUES (6,'utwór dramatyczny (dramat, komedia, tragedia)'); -- 25

INSERT INTO Dziedzina (Dziedzina_idDziedzina, nazwa)
VALUES (7,'poradniki'); -- 26
INSERT INTO Dziedzina (Dziedzina_idDziedzina, nazwa)
VALUES (7,'religia'); -- 27
INSERT INTO Dziedzina (Dziedzina_idDziedzina, nazwa)
VALUES (7,'turystyka, mapy, atlasy'); -- 28
INSERT INTO Dziedzina (Dziedzina_idDziedzina, nazwa)
VALUES (7,'kulinaria, przepisy kulinarne'); -- 29
INSERT INTO Dziedzina (Dziedzina_idDziedzina, nazwa)
VALUES (7,'sport'); -- 30


-- Autor
INSERT INTO Autor (imie, nazwisko)
VALUES ('Jane','Austen'); -- 1
INSERT INTO Autor (imie, nazwisko)
VALUES ('J.R.R.','Tolkien'); -- 2
INSERT INTO Autor (imie, nazwisko)
VALUES ('Witold','Gombrowicz'); -- 3
INSERT INTO Autor (imie, nazwisko)
VALUES ('George','Orwell'); -- 4
INSERT INTO Autor (imie, nazwisko)
VALUES ('Fiodor','Dostojewski'); --5
INSERT INTO Autor (imie, nazwisko)
VALUES ('J.K.','Rowling'); -- 6
INSERT INTO Autor (imie, nazwisko)
VALUES ('Harper','Lee'); -- 7
INSERT INTO Autor (imie, nazwisko)
VALUES ('Terry','Pratchett'); -- 8
INSERT INTO Autor (imie, nazwisko)
VALUES ('Neil','Gaiman'); -- 9

-- Czytelnik
INSERT INTO Czytelnik (imie, nazwisko, email, telefon, login, haslo)
VALUES ('Test', 'Testowy', 'test@test.com', '451886435', 'test', 'test');
INSERT INTO Czytelnik (imie, nazwisko, email, telefon, login, haslo)
VALUES ('Marcin', 'Wiśniewski', 'mwisniewski@wp.pl', '434754654', 'mwisniewski', 'marcin123');
INSERT INTO Czytelnik (imie, nazwisko, email, telefon, login, haslo)
VALUES ('Edyta', 'Laskowska', 'elaskowska@gmail.pl', '932847284', 'elaskowska', 'edyta123');
INSERT INTO Czytelnik (imie, nazwisko, email, telefon, login, haslo)
VALUES ('Aniela', 'Rutkowska', 'arutkowska@wp.pl', '212134445', 'arutkowska', 'aniela123');


-- Ksiazka
INSERT INTO Ksiazka (Wydawnictwo_idWydawnictwo, tytul, rok_wydania, ISBN)
VALUES (1, 'Kolor magii', 1994, 9788374690973); -- 1
INSERT INTO Ksiazka (Wydawnictwo_idWydawnictwo, tytul, rok_wydania, ISBN)
VALUES (1, 'Dobry omen', 2019, 9788381690867); -- 2
INSERT INTO Ksiazka (Wydawnictwo_idWydawnictwo, tytul, rok_wydania, ISBN)
VALUES (2, 'Zabić drozda', 2015, 9788378187929); -- 3

INSERT INTO Ksiazka (Wydawnictwo_idWydawnictwo, tytul, rok_wydania, ISBN)
VALUES (3, 'Harry Potter i Kamień Filozoficzny', 2016, 9788380082113); -- 4
INSERT INTO Ksiazka (Wydawnictwo_idWydawnictwo, tytul, rok_wydania, ISBN)
VALUES (3, 'Harry Potter i Komnata Tajemnic', 2016, 9788380082137); -- 5
INSERT INTO Ksiazka (Wydawnictwo_idWydawnictwo, tytul, rok_wydania, ISBN)
VALUES (4, 'Folwark zwierzęcy', 2021, 9788328072596); -- 6
INSERT INTO Ksiazka (Wydawnictwo_idWydawnictwo, tytul, rok_wydania, ISBN)
VALUES (4, 'Zbrodnia i kara', 2018, 9788328055971); -- 7
INSERT INTO Ksiazka (Wydawnictwo_idWydawnictwo, tytul, rok_wydania, ISBN)
VALUES (4, '1984', 2021, 9788328072466); -- 8
INSERT INTO Ksiazka (Wydawnictwo_idWydawnictwo, tytul, rok_wydania, ISBN)
VALUES (5, 'Ferdydurke', 2012, 9788308049211); -- 9



-- Rezerwacja
INSERT INTO Rezerwacja (Czytelnik_idCzytelnik, Ksiazka_idKsiazka, data_rezerwacji)
VALUES (1,1,'2022-01-07 00:44:56.943037');
INSERT INTO Rezerwacja (Czytelnik_idCzytelnik, Ksiazka_idKsiazka)
VALUES (2,1);

-- Ksiazka_Autor
INSERT INTO Ksiazka_Autor (Ksiazka_idKsiazka, Autor_idAutor)
VALUES (1,8);
INSERT INTO Ksiazka_Autor (Ksiazka_idKsiazka, Autor_idAutor)
VALUES (2,8);
INSERT INTO Ksiazka_Autor (Ksiazka_idKsiazka, Autor_idAutor)
VALUES (2,9);
INSERT INTO Ksiazka_Autor (Ksiazka_idKsiazka, Autor_idAutor)
VALUES (3,7);

INSERT INTO Ksiazka_Autor (Ksiazka_idKsiazka, Autor_idAutor)
VALUES (4,6);
INSERT INTO Ksiazka_Autor (Ksiazka_idKsiazka, Autor_idAutor)
VALUES (5,6);
INSERT INTO Ksiazka_Autor (Ksiazka_idKsiazka, Autor_idAutor)
VALUES (6,4);
INSERT INTO Ksiazka_Autor (Ksiazka_idKsiazka, Autor_idAutor)
VALUES (7,5);
INSERT INTO Ksiazka_Autor (Ksiazka_idKsiazka, Autor_idAutor)
VALUES (8,4);
INSERT INTO Ksiazka_Autor (Ksiazka_idKsiazka, Autor_idAutor)
VALUES (9,3);

-- Ksiazka_Dziedzina
INSERT INTO Ksiazka_Dziedzina (Ksiazka_idKsiazka, Dziedzina_idDziedzina)
VALUES (1,8);
INSERT INTO Ksiazka_Dziedzina (Ksiazka_idKsiazka, Dziedzina_idDziedzina)
VALUES (2,8);
INSERT INTO Ksiazka_Dziedzina (Ksiazka_idKsiazka, Dziedzina_idDziedzina)
VALUES (3,11);

INSERT INTO Ksiazka_Dziedzina (Ksiazka_idKsiazka, Dziedzina_idDziedzina)
VALUES (4,12);
INSERT INTO Ksiazka_Dziedzina (Ksiazka_idKsiazka, Dziedzina_idDziedzina)
VALUES (5,12);
INSERT INTO Ksiazka_Dziedzina (Ksiazka_idKsiazka, Dziedzina_idDziedzina)
VALUES (6,9);
INSERT INTO Ksiazka_Dziedzina (Ksiazka_idKsiazka, Dziedzina_idDziedzina)
VALUES (6,11);
INSERT INTO Ksiazka_Dziedzina (Ksiazka_idKsiazka, Dziedzina_idDziedzina)
VALUES (7,11);
INSERT INTO Ksiazka_Dziedzina (Ksiazka_idKsiazka, Dziedzina_idDziedzina)
VALUES (8,11);
INSERT INTO Ksiazka_Dziedzina (Ksiazka_idKsiazka, Dziedzina_idDziedzina)
VALUES (9,9);


-- Dwie różne naddziedziny
-- INSERT INTO Ksiazka_Dziedzina (Ksiazka_idKsiazka, Dziedzina_idDziedzina)
-- VALUES (4,20);


-- Egzemplarz
INSERT INTO Egzemplarz (Ksiazka_idKsiazka) VALUES (1); -- 1
INSERT INTO Egzemplarz (Ksiazka_idKsiazka) VALUES (1); -- 2
INSERT INTO Egzemplarz (Ksiazka_idKsiazka) VALUES (2); -- 3
INSERT INTO Egzemplarz (Ksiazka_idKsiazka) VALUES (3); -- 4
INSERT INTO Egzemplarz (Ksiazka_idKsiazka) VALUES (3); -- 5
INSERT INTO Egzemplarz (Ksiazka_idKsiazka) VALUES (4); -- 6
INSERT INTO Egzemplarz (Ksiazka_idKsiazka) VALUES (5); -- 7
INSERT INTO Egzemplarz (Ksiazka_idKsiazka) VALUES (5); -- 8
INSERT INTO Egzemplarz (Ksiazka_idKsiazka) VALUES (5);
INSERT INTO Egzemplarz (Ksiazka_idKsiazka) VALUES (6); -- 10
INSERT INTO Egzemplarz (Ksiazka_idKsiazka) VALUES (6);
INSERT INTO Egzemplarz (Ksiazka_idKsiazka) VALUES (7); -- 12
INSERT INTO Egzemplarz (Ksiazka_idKsiazka) VALUES (7);
INSERT INTO Egzemplarz (Ksiazka_idKsiazka) VALUES (8); -- 14
INSERT INTO Egzemplarz (Ksiazka_idKsiazka) VALUES (8);
INSERT INTO Egzemplarz (Ksiazka_idKsiazka) VALUES (9); -- 16
INSERT INTO Egzemplarz (Ksiazka_idKsiazka) VALUES (9);
INSERT INTO Egzemplarz (Ksiazka_idKsiazka) VALUES (9); -- 18

-- Wypozyczenie
INSERT INTO Wypozyczenie (Czytelnik_idCzytelnik, Egzemplarz_idEgzemplarz, data_wypozyczenia, data_planowanego_oddania)
VALUES (1,4,'2021-09-19 11:37:44.361709','2021-10-19 11:37:44.361709');
    UPDATE Wypozyczenie SET data_oddania = '2021-11-19 11:37:44.361709' WHERE Egzemplarz_idEgzemplarz = 4 AND czytelnik_idCzytelnik = 1;
    -- Zadziała trigger z karą, ponieważ książka jest przetrzymana
INSERT INTO Wypozyczenie (Czytelnik_idCzytelnik, Egzemplarz_idEgzemplarz)
VALUES (3,1);

INSERT INTO Wypozyczenie (Czytelnik_idCzytelnik, Egzemplarz_idEgzemplarz, data_wypozyczenia, data_planowanego_oddania, data_oddania)
VALUES (2,5,'2021-10-19 11:37:44.361709','2021-11-19 11:37:44.361709',NULL);
    UPDATE Wypozyczenie SET data_oddania = '2022-01-19 11:37:44.361709' WHERE Egzemplarz_idEgzemplarz = 5 AND czytelnik_idCzytelnik = 2;
    -- Zadziała trigger z karą, ponieważ książka jest przetrzymana

-- INSERT INTO x ()
-- VALUES ();

-- UPDATE Czytelnik SET kara = 10.1 WHERE idCzytelnik = 2;