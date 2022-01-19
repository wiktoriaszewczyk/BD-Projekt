-- Funkcja logowanie jako pracownik
-- 1 - udane logowanie
-- 0 - nieudane logowanie
CREATE OR REPLACE FUNCTION logowanie_pracownik(l text, p text)
RETURNS int AS
$$
DECLARE
    rec RECORD;
BEGIN
    SELECT COUNT(*) AS n INTO rec FROM pracownik WHERE login = l AND haslo = p;
    IF rec.n = 1 THEN
        RETURN 1;
    END IF;
    RETURN 0;
END;
$$
LANGUAGE plpgsql;

-------------------------------------------------------------------------------------


-- Funkcja logowanie jako czytelnik
-- id - udane logowanie
-- 0 - nieudane logowanie
CREATE OR REPLACE FUNCTION logowanie_czytelnik(l text, p text)
RETURNS int AS
$$
DECLARE
    rec RECORD;
BEGIN
    SELECT COUNT(*) AS n, idczytelnik INTO rec FROM czytelnik WHERE login = l AND haslo = p GROUP BY idczytelnik;
    IF rec.n = 1 THEN
        RETURN rec.idczytelnik;
    END IF;
    RETURN 0;
END;
$$
LANGUAGE plpgsql;

-------------------------------------------------------------------------------------

-- czy ksiazka jest juz dostepna do wypożyczenia
-- zwraca 
-- 0 jeśli nie 
-- idegzemplarz dostępnego egzemplarza jeśli tak 

CREATE OR REPLACE FUNCTION dostepnosc_ksiazki(idK int)
RETURNS int AS
$$
DECLARE
    rec RECORD;
BEGIN
    -- Sprawdzam czy istnieje dostępny egzemplarz 
    SELECT COUNT(*) AS n INTO rec FROM egzemplarz WHERE ksiazka_idksiazka = idK AND wypozyczona = false;

    IF rec.n > 0 THEN
        SELECT idegzemplarz AS n INTO rec FROM egzemplarz WHERE ksiazka_idksiazka = idK AND wypozyczona = false;
        RETURN rec.n;
    END IF;
    RETURN 0;
END;
$$
LANGUAGE plpgsql;


-------------------------------------------------------------------------------------

-- czy zarezerwowana ksiazka jest juz dostepna
-- zwraca 
-- 0 jeśli nie 
-- idegzemplarz dostępnego egzemplarza jeśli tak 

CREATE OR REPLACE FUNCTION rezerwacja_dostepnosc(idK int, idC int)
RETURNS int AS
$$
DECLARE
    recPierwszenstwo RECORD;
    rec RECORD;
BEGIN
    -- Sprawdzam czy czytelnik jest pierwszy w kolejce do książki
    SELECT czytelnik_idczytelnik as id FROM rezerwacja INTO recPierwszenstwo WHERE ksiazka_idksiazka = 1 ORDER BY data_rezerwacji LIMIT 1;

    IF recPierwszenstwo.id = idC THEN
        -- Sprawdzam czy istnieje dostępny egzemplarz 
        SELECT COUNT(*) AS n INTO rec FROM egzemplarz WHERE ksiazka_idksiazka = idK AND wypozyczona = false;

        IF rec.n > 0 THEN
            SELECT idegzemplarz AS n INTO rec FROM egzemplarz WHERE ksiazka_idksiazka = idK AND wypozyczona = false;
            RETURN rec.n;
        END IF;
    END IF;
    RETURN 0;
END;
$$
LANGUAGE plpgsql;

-------------------------------------------------------------------------------------

-- usuwanie ksiazki
-- zwraca 
-- 0 - nie udało się usunąć (są egzemplarze)
-- 1 - udało się usunąć
CREATE OR REPLACE FUNCTION usuwanie_ksiazki(idK int)
RETURNS int AS
$$
DECLARE
    recEgzemplarz RECORD;
    recAutor RECORD;
    recDziedzina RECORD;
    recRezerwacja RECORD;
BEGIN
    -- Usuwam informacje z tabel ksiazka_dziedzina, ksiazka_autor i rezerwacja tylko jesli nie ma egzemplarzy
    -- Jeśli chcemy usunąć książke trzeba najpierw usunąć wszystkie jej egzemplarze, nie usuwam kaskadowo wszystkich informacji o książce  
    SELECT COUNT(*) AS n INTO recEgzemplarz FROM egzemplarz WHERE ksiazka_idksiazka = idK;
    SELECT COUNT(*) AS n INTO recAutor FROM ksiazka_autor WHERE ksiazka_idksiazka = idK;
    SELECT COUNT(*) AS n INTO recDziedzina FROM ksiazka_dziedzina WHERE ksiazka_idksiazka = idK;
    SELECT COUNT(*) AS n INTO recRezerwacja FROM rezerwacja WHERE ksiazka_idksiazka = idK;
    
    IF recEgzemplarz.n > 0 THEN
        RETURN 0;
    END IF;

    IF recAutor.n > 0 THEN
        DELETE FROM ksiazka_autor WHERE ksiazka_idksiazka = idK;        
    END IF;

    IF recDziedzina.n > 0 THEN
        DELETE FROM ksiazka_dziedzina WHERE ksiazka_idksiazka = idK;        
    END IF;

    IF recRezerwacja.n > 0 THEN
        DELETE FROM rezerwacja WHERE ksiazka_idksiazka = idK;        
    END IF;

    DELETE FROM ksiazka WHERE idksiazka = idK;    

    RETURN 1;
END;
$$
LANGUAGE plpgsql;

------------------------------------------------------------------------------------------------------------

-- Przy oddaniu książki jeśli została przetrzymana nalicza się kara // nienajlepsze rozwiązanie :c

CREATE OR REPLACE FUNCTION dodaj_kara(idC int, idE int)
RETURNS VOID AS
$$
DECLARE
    rec RECORD;
    k0 RECORD;
    k int;
BEGIN
    SELECT CAST(DATE_PART('day', data_oddania - data_planowanego_oddania) AS int) AS n INTO rec FROM wypozyczenie WHERE czytelnik_idczytelnik=idC AND egzemplarz_idEgzemplarz=idE;
    IF rec.n > 0 THEN
        SELECT kara INTO k0 FROM czytelnik WHERE idczytelnik = idC;
        k := k0.kara + rec.n*0.1;
        UPDATE Czytelnik SET kara = k WHERE idCzytelnik = idC;
    END IF;
END;
$$
LANGUAGE plpgsql;



-- -- wypożyczanie książki przez czytelnika

-- CREATE OR REPLACE FUNCTION wypozyczanie_egzemplarza(idC int, idE int)
-- RETURNS VOID AS
-- $$
-- DECLARE
-- BEGIN
--     INSERT INTO Wypozyczenie (Czytelnik_idCzytelnik, Egzemplarz_idEgzemplarz) VALUES (idC,idE);
--     -- RETURN 1;
-- END;
-- $$
-- LANGUAGE plpgsql;

-- ------------------------------------------------------------------------------------------------------------

-- -- oddawanie książki przez czytelnika

-- CREATE OR REPLACE FUNCTION oddawanie_egzemplarza(idC int, idE int)
-- RETURNS int AS
-- $$
-- DECLARE
-- BEGIN
--     UPDATE Wypozyczenie SET data_oddania = NOW() WHERE Egzemplarz_idEgzemplarz = idE AND czytelnik_idCzytelnik = idC;
--     RETURN 1;
-- END;
-- $$
-- LANGUAGE plpgsql;