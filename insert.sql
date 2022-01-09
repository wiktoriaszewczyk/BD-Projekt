-- Pracownik
INSERT INTO Pracownik (login, imie, nazwisko, email, haslo)
VALUES ('admin', 'Admin', 'Admin', 'admin@admin.com', 'admin');
INSERT INTO Pracownik (login, imie, nazwisko, email, haslo)
VALUES ('aborkowski', 'Artur', 'Borkowski', 'aborkowski@gmail.com', 'has1o');


-- Wydawnictwo
INSERT INTO Wydawnictwo (nazwa) VALUES ('Prószyński i S-ka');
INSERT INTO Wydawnictwo (nazwa) VALUES ('Rebis');
INSERT INTO Wydawnictwo (nazwa) VALUES ('Media Rodzina');
INSERT INTO Wydawnictwo (nazwa) VALUES ('W.A.B.');
INSERT INTO Wydawnictwo (nazwa) VALUES ('Wydawnictwo Literackie');
INSERT INTO Wydawnictwo (nazwa) VALUES ('Wydawnictwo Iskry');


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
VALUES ('Jane','Austen');
INSERT INTO Autor (imie, nazwisko)
VALUES ('J.R.R.','Tolkien');
INSERT INTO Autor (imie, nazwisko)
VALUES ('Witold','Gombrowicz');
INSERT INTO Autor (imie, nazwisko)
VALUES ('George','Orwell');
INSERT INTO Autor (imie, nazwisko)
VALUES ('Fiodor','Dostojewski');
INSERT INTO Autor (imie, nazwisko)
VALUES ('J.K.','Rowling');
INSERT INTO Autor (imie, nazwisko)
VALUES ('Harper','Lee');
INSERT INTO Autor (imie, nazwisko)
VALUES ('Terry','Pratchett');
INSERT INTO Autor (imie, nazwisko)
VALUES ('Neil','Gaiman');   -- 2 autor Dobry omen


-- Czytelnik
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

-- Ksiazka_Dziedzina
INSERT INTO Ksiazka_Dziedzina (Ksiazka_idKsiazka, Dziedzina_idDziedzina)
VALUES (1,8);
INSERT INTO Ksiazka_Dziedzina (Ksiazka_idKsiazka, Dziedzina_idDziedzina)
VALUES (2,8);
INSERT INTO Ksiazka_Dziedzina (Ksiazka_idKsiazka, Dziedzina_idDziedzina)
VALUES (3,11);

-- Egzemplarz
INSERT INTO Egzemplarz (Ksiazka_idKsiazka)
VALUES (1); -- 1
INSERT INTO Egzemplarz (Ksiazka_idKsiazka)
VALUES (1); -- 2
INSERT INTO Egzemplarz (Ksiazka_idKsiazka)
VALUES (2); -- 3
INSERT INTO Egzemplarz (Ksiazka_idKsiazka)
VALUES (3); -- 4
INSERT INTO Egzemplarz (Ksiazka_idKsiazka)
VALUES (3); -- 5

-- Wypozyczenie
INSERT INTO Wypozyczenie (Czytelnik_idCzytelnik, Egzemplarz_idEgzemplarz) --, data_wypozyczenia, data_planowanego_oddania, data_oddania
VALUES (3,1);
UPDATE Egzemplarz SET wypozyczona = true WHERE idEgzemplarz = 1;


-- INSERT INTO x ()
-- VALUES ();
