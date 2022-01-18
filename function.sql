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
-- 1 - udane logowanie
-- 0 - nieudane logowanie
CREATE OR REPLACE FUNCTION logowanie_czytelnik(l text, p text)
RETURNS int AS
$$
DECLARE
    rec RECORD;
BEGIN
    SELECT COUNT(*) AS n INTO rec FROM czytelnik WHERE login = l AND haslo = p;
    IF rec.n = 1 THEN
        RETURN 1;
    END IF;
    RETURN 0;
END;
$$
LANGUAGE plpgsql;


-------------------------------------------------------------------------------------

-- czy zarezerwowana ksiazka jest juz dostepna

CREATE OR REPLACE FUNCTION rezerwacja_dostepnosc(idK int)
RETURNS int AS
$$
DECLARE
    rec RECORD;
BEGIN
    SELECT COUNT(*) AS n INTO rec FROM egzemplarz WHERE ksiazka_idksiazka = idK AND wypozyczona = false;
    IF rec.n > 1 THEN
        SELECT idegzemplarz AS n INTO rec FROM egzemplarz WHERE ksiazka_idksiazka = idK AND wypozyczona = false;
        RETURN rec.n;
    END IF;
    RETURN 0;
END;
$$
LANGUAGE plpgsql;

-------------------------------------------------------------------------------------

-- szukanie autorów po tekscie
