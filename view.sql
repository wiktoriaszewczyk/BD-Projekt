CREATE VIEW dziedziny_naddziedziny AS
    SELECT d2.idDziedzina as idDziedzina, d2.dziedzina_idDziedzina AS idDziedzina_nad, d1.nazwa AS dziedzina_nad, d2.nazwa
    FROM Dziedzina d1 RIGHT JOIN Dziedzina d2 
    ON d1.idDziedzina = d2.dziedzina_idDziedzina;

CREATE VIEW dziedziny_ksiazki AS
    SELECT k.idksiazka , (string_agg(d.dziedzina_nad, ', ') || ', ' || string_agg(d.nazwa, ', ')) as dziedziny 
    FROM Ksiazka k 
    JOIN Ksiazka_Dziedzina kd ON k.idKsiazka = kd.ksiazka_idKsiazka 
    JOIN 
    (SELECT d2.idDziedzina as idDziedzina, d1.nazwa AS dziedzina_nad, d2.nazwa
    FROM Dziedzina d1 RIGHT JOIN Dziedzina d2 
    ON d1.idDziedzina = d2.dziedzina_idDziedzina) d 
    ON kd.dziedzina_idDziedzina = d.idDziedzina
    GROUP BY k.idKsiazka;

CREATE VIEW ksiazka_info AS
    SELECT k.idKsiazka, k.tytul, k.rok_wydania, k.isbn, string_agg((a.imie || ' ' || a.nazwisko), ', ')  AS autorzy, d.dziedziny, k.wydawnictwo_idwydawnictwo AS idwydawnictwo, w.nazwa AS wydawnictwo
    FROM Ksiazka k 
    JOIN Wydawnictwo w
    ON k.wydawnictwo_idwydawnictwo = w.idwydawnictwo
    JOIN Ksiazka_Autor ka ON k.idKsiazka = ka.ksiazka_idKsiazka 
    JOIN Autor a ON ka.Autor_idAutor = a.idAutor
    JOIN dziedziny_ksiazki d ON k.idKsiazka = d.idKsiazka
    GROUP BY k.idKsiazka, d.dziedziny, w.nazwa;
    -- SELECT k.idKsiazka, k.tytul, k.rok_wydania, k.isbn, string_agg((a.imie || ' ' || a.nazwisko), ', ')  AS autorzy, d.dziedziny
    -- FROM Ksiazka k 
    -- JOIN Ksiazka_Autor ka ON k.idKsiazka = ka.ksiazka_idKsiazka 
    -- JOIN Autor a ON ka.Autor_idAutor = a.idAutor
    -- JOIN dziedziny_ksiazki d ON k.idKsiazka = d.idKsiazka
    -- GROUP BY k.idKsiazka, d.dziedziny;


CREATE VIEW egzemplarz_info AS
    SELECT e.idegzemplarz, k.idksiazka, e.wypozyczona, k.tytul, k.rok_wydania, k.isbn, k.autorzy, k.dziedziny, k.idwydawnictwo, k.wydawnictwo
    FROM ksiazka_info k
    JOIN egzemplarz e
    ON k.idksiazka = e.ksiazka_idKsiazka;

CREATE VIEW wypozyczone_egzemplarze_nieoddane AS
    SELECT w.czytelnik_idczytelnik, TO_CHAR(w.data_wypozyczenia, 'DD MM YYYY') as data_wypozyczenia, TO_CHAR(w.data_planowanego_oddania, 'DD MM YYYY') as data_planowanego_oddania, e.idegzemplarz, e.idksiazka, e.wypozyczona, e.tytul, e.rok_wydania, e.isbn, e.autorzy, e.dziedziny, e.idwydawnictwo, e.wydawnictwo
    FROM wypozyczenie w
    JOIN egzemplarz_info e
    ON w.egzemplarz_idEgzemplarz = e.idEgzemplarz
    WHERE w.data_oddania IS NULL;

CREATE VIEW wypozyczone_egzemplarze_oddane AS
    SELECT w.czytelnik_idczytelnik, TO_CHAR(w.data_wypozyczenia, 'DD MM YYYY') as data_wypozyczenia, TO_CHAR(w.data_planowanego_oddania, 'DD MM YYYY') as data_planowanego_oddania, TO_CHAR(w.data_oddania, 'DD MM YYYY') as data_oddania, e.idegzemplarz, e.idksiazka, e.wypozyczona, e.tytul, e.rok_wydania, e.isbn, e.autorzy, e.dziedziny, e.idwydawnictwo, e.wydawnictwo
    FROM wypozyczenie w
    JOIN egzemplarz_info e
    ON w.egzemplarz_idEgzemplarz = e.idEgzemplarz
    WHERE w.data_oddania IS NOT NULL;

CREATE VIEW zarezerwowane_ksiazki AS 
    SELECT r.czytelnik_idczytelnik, k.idksiazka, k.tytul, k.rok_wydania, k.isbn, k.autorzy, k.dziedziny, k.idwydawnictwo, k.wydawnictwo
    FROM rezerwacja r
    JOIN ksiazka_info k
    ON r.ksiazka_idksiazka = k.idksiazka;

