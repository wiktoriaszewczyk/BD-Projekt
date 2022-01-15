-- Nowe Wypozyczenie -> zmiana w egzemplarz
CREATE OR REPLACE FUNCTION nowe_wypozyczenie()
RETURNS TRIGGER AS
$$
DECLARE 
    czy_wypozyczona RECORD;   -- wypozyczona
    k RECORD;   -- kara
    id_ksiazki RECORD;   -- rezerwacja
    czy_rezerwowana RECORD; -- rezerwacja
BEGIN

    -- czy książka jest dostępna
    SELECT wypozyczona INTO czy_wypozyczona FROM Egzemplarz WHERE idEgzemplarz = NEW.Egzemplarz_idEgzemplarz;
    IF czy_wypozyczona.wypozyczona THEN
        RAISE NOTICE 'Książka niedostępna';
        RETURN NULL;
    END IF;

    -- czy czytelnik nie ma za wysokiej kary
    SELECT kara INTO k FROM Czytelnik WHERE idCzytelnik = NEW.Czytelnik_idCzytelnik;
    IF k.kara > 10 THEN
        RAISE NOTICE 'Nie możesz wypożyczyć książki. Zapłać karę';
        RETURN NULL;
    END IF;

    -- czy byla wczesniej rezerwowana
    -- jesli tak to usuwam rezerwacje
    SELECT ksiazka_idksiazka INTO id_ksiazki FROM egzemplarz WHERE idegzemplarz = NEW.Egzemplarz_idEgzemplarz;
    SELECT COUNT(*) AS r INTO czy_rezerwowana FROM rezerwacja WHERE czytelnik_idczytelnik = NEW.czytelnik_idCzytelnik AND ksiazka_idksiazka = id_ksiazki.ksiazka_idksiazka;
    IF czy_rezerwowana.r > 0 THEN 
        DELETE FROM rezerwacja  WHERE czytelnik_idczytelnik = NEW.czytelnik_idCzytelnik AND ksiazka_idksiazka = id_ksiazki.ksiazka_idksiazka;
    END IF;

    -- zmiana pola wypozyczona w tabeli Egzemplarz na true
    UPDATE Egzemplarz SET wypozyczona = true WHERE idEgzemplarz = NEW.Egzemplarz_idEgzemplarz;

    RETURN NEW;
END;
$$ LANGUAGE plpgsql;  

CREATE TRIGGER wypozyczenie_insert BEFORE INSERT ON Wypozyczenie
FOR EACH ROW EXECUTE PROCEDURE nowe_wypozyczenie();

---------------------------------------------------------------------------


-- Oddanie książki
CREATE OR REPLACE FUNCTION oddanie_ksiazki()
RETURNS TRIGGER AS
$$
BEGIN
    -- IF NEW.data_oddania IS NOT NULL THEN

    -- END IF;

    -- IF NEW.data_planowanego_oddania IS NOT NULL THEN
    --     RAISE NOTICE 'AAa'; 
    --     -- działa mimo że nie zmieniamy daty_oddania
    -- END IF;
    
    UPDATE Egzemplarz SET wypozyczona = false WHERE idEgzemplarz = NEW.Egzemplarz_idEgzemplarz;

    RETURN NEW;
END;
$$ LANGUAGE plpgsql;  

CREATE TRIGGER wypozyczenie_update_oddanie AFTER UPDATE ON Wypozyczenie
FOR EACH ROW EXECUTE PROCEDURE oddanie_ksiazki();


-- Prolongata 
-- $$
-- DECLARE 
--     k RECORD;
--     r RECORD;
--     e RECORD;
-- BEGIN
--     IF NEW.data_planowanego_oddania = 
--         -- czy ksiązka jest oddana
--         IF NEW.data_oddania IS NOT NULL THEN
--             RAISE NOTICE 'Książka już oddana.';
--             RETURN NULL;
--         END IF;

--         -- czy nie za długo przetrzymana 4 miesiące
--         IF NEW.data_planowanego_oddania - NEW.data_wypozyczenia  > 4*30 THEN
--             RAISE NOTICE 'Przetrzymana za długo.';
--             RETURN NULL;
--         END IF;

--         -- czy ksiązka jest zarezerwowana
--         SELECT Ksiazka_idKsiazka as id INTO k FROM Egzemplarz WHERE idEgzemplarz = NEW.Egzemplarz_idEgzemplarz;
        
--         SELECT COUNT(*) AS n INTO r FROM Rezerwacja WHERE ksiazka_idksiazka = k.id;

--         IF r.n > 0 THEN
--             RAISE NOTICE 'Nie można przedłużyć terminu oddania';
--             RETURN NULL;
--         END IF;

--         RETURN NEW;

