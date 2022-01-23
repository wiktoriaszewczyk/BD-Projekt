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
    IF NEW.data_oddania IS NOT NULL THEN
        -- Zmiana flagi wypozyczenia na false
        UPDATE Egzemplarz SET wypozyczona = false WHERE idEgzemplarz = NEW.Egzemplarz_idEgzemplarz;
        -- Dodawanie kary za przetrzymanie
        PERFORM dodaj_kara(NEW.Czytelnik_idCzytelnik, NEW.Egzemplarz_idEgzemplarz);
    END IF;

    RETURN NEW;
END;
$$ LANGUAGE plpgsql;  

CREATE TRIGGER wypozyczenie_update_oddanie AFTER UPDATE ON Wypozyczenie
FOR EACH ROW EXECUTE PROCEDURE oddanie_ksiazki();


---------------------------------------------------------------------------
-- Nowa rezerwacja -> niemożliwa jeśli już wypożyczamy książkę

CREATE OR REPLACE FUNCTION nowa_rezerwacja()
RETURNS TRIGGER AS
$$
DECLARE 
    czy_wypozyczona RECORD;   -- wypozyczona
BEGIN

    -- Czy czytelnik nie wypożycza już tej książki
    SELECT COUNT(*) AS n INTO czy_wypozyczona FROM wypozyczone_egzemplarze_nieoddane WHERE Czytelnik_idCzytelnik = NEW.Czytelnik_idCzytelnik AND idKsiazka = NEW.Ksiazka_idKsiazka;
    IF czy_wypozyczona.n > 0 THEN
        RAISE NOTICE 'Nie możesz zarezerwowac ksiazki, ktora wypozyczasz';
        RETURN NULL;
    END IF;


    RETURN NEW;
END;
$$ LANGUAGE plpgsql;  

CREATE TRIGGER rezerwacja_insert BEFORE INSERT ON Rezerwacja
FOR EACH ROW EXECUTE PROCEDURE nowa_rezerwacja();
