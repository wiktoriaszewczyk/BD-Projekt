-- Funkcja logowanie jako pracownik
-- Zwraca
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
-- Zwraca
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

-- Funkcja sprawdza czy ksiazka jest dostepna do wypożyczenia
-- Zwraca 
-- 0 - jeśli nie 
-- idegzemplarz dostępnego egzemplarza - jeśli tak 
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

-- Funkcja sprawdza czy zarezerwowana ksiazka jest juz dostepna
-- Zwraca 
-- 0 - jeśli nie 
-- idegzemplarz dostępnego egzemplarza - jeśli tak 
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


------------------------------------------------------------------------------------------------------------

-- Przy oddaniu książki jeśli została przetrzymana nalicza się kara (0.1 zł na dzień)   -- nienajlepsze rozwiązanie :c 
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

