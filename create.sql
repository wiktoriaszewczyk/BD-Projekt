CREATE TABLE Pracownik (
  login VARCHAR(45)   NOT NULL UNIQUE,
  imie VARCHAR(255)   NOT NULL ,
  nazwisko VARCHAR(255)   NOT NULL ,
  email VARCHAR(45)   NOT NULL UNIQUE,
  haslo VARCHAR(45)   NOT NULL   ,
  CHECK (email LIKE '%_@_%._%'), 
PRIMARY KEY(login));

-- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- 

CREATE TABLE Wydawnictwo (
  idWydawnictwo SERIAL  NOT NULL ,
  nazwa VARCHAR(255)   NOT NULL,
PRIMARY KEY(idWydawnictwo));

-- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- 

CREATE TABLE Dziedzina (
  idDziedzina SERIAL  NOT NULL ,
  Dziedzina_idDziedzina INTEGER    ,
  nazwa VARCHAR(255)   NOT NULL UNIQUE  ,
PRIMARY KEY(idDziedzina)  ,
  FOREIGN KEY(Dziedzina_idDziedzina)
    REFERENCES Dziedzina(idDziedzina));

CREATE INDEX Dziedzina_FKIndex1 ON Dziedzina (Dziedzina_idDziedzina);
CREATE INDEX IFK_Rel_13 ON Dziedzina (Dziedzina_idDziedzina);

-- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- 

CREATE TABLE Autor (
  idAutor SERIAL  NOT NULL ,
  imie VARCHAR(255)   NOT NULL ,
  nazwisko VARCHAR(255)   NOT NULL   ,
PRIMARY KEY(idAutor));

-- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- 

CREATE TABLE Czytelnik (
  idCzytelnik SERIAL  NOT NULL ,
  imie VARCHAR(255)   NOT NULL ,
  nazwisko VARCHAR(255)   NOT NULL ,
  email VARCHAR(45)   NOT NULL UNIQUE,
  telefon INTEGER DEFAULT NULL ,
  kara FLOAT NOT NULL DEFAULT 0.0,
  login VARCHAR(45)   NOT NULL UNIQUE,
  haslo VARCHAR(45)   NOT NULL   ,
CHECK (((telefon < 1000000000 AND telefon >= 100000000) OR telefon IS NULL) AND email LIKE '%_@_%._%'), 
PRIMARY KEY(idCzytelnik));

-- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- 

CREATE TABLE Ksiazka (
  idKsiazka SERIAL  NOT NULL ,
  Wydawnictwo_idWydawnictwo INTEGER   NOT NULL ,
  tytul VARCHAR(255)   NOT NULL ,
  rok_wydania INTEGER   NOT NULL ,
  ISBN BIGINT UNIQUE,
CHECK(rok_wydania > 1800 AND rok_wydania <= EXTRACT(YEAR FROM CURRENT_DATE) AND ISBN >= 1000000000000 AND ISBN < 10000000000000),
PRIMARY KEY(idKsiazka)  ,
  FOREIGN KEY(Wydawnictwo_idWydawnictwo)
    REFERENCES Wydawnictwo(idWydawnictwo));


CREATE INDEX Ksiazka_FKIndex1 ON Ksiazka (Wydawnictwo_idWydawnictwo);

CREATE INDEX IFK_Rel_06 ON Ksiazka (Wydawnictwo_idWydawnictwo);

-- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- 

CREATE TABLE Rezerwacja (
  Czytelnik_idCzytelnik INTEGER   NOT NULL ,
  Ksiazka_idKsiazka INTEGER   NOT NULL ,
  data_rezerwacji timestamp   NOT NULL DEFAULT now(),
CHECK(data_rezerwacji <= now()),
PRIMARY KEY(Czytelnik_idCzytelnik, Ksiazka_idKsiazka)    ,
  FOREIGN KEY(Czytelnik_idCzytelnik)
    REFERENCES Czytelnik(idCzytelnik),
  FOREIGN KEY(Ksiazka_idKsiazka)
    REFERENCES Ksiazka(idKsiazka));


CREATE INDEX Egzemplarz_has_Czytelnik_FKIndex2 ON Rezerwacja (Czytelnik_idCzytelnik);
CREATE INDEX Rezerwacja_FKIndex2 ON Rezerwacja (Ksiazka_idKsiazka);

CREATE INDEX IFK_Rel_14 ON Rezerwacja (Czytelnik_idCzytelnik);
CREATE INDEX IFK_Rel_12 ON Rezerwacja (Ksiazka_idKsiazka);

-- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- 

CREATE TABLE Ksiazka_Autor (
  Ksiazka_idKsiazka INTEGER   NOT NULL ,
  Autor_idAutor INTEGER   NOT NULL   ,
PRIMARY KEY(Ksiazka_idKsiazka, Autor_idAutor)    ,
  FOREIGN KEY(Ksiazka_idKsiazka)
    REFERENCES Ksiazka(idKsiazka),
  FOREIGN KEY(Autor_idAutor)
    REFERENCES Autor(idAutor));

CREATE INDEX Ksiazka_has_Autor_FKIndex1 ON Ksiazka_Autor (Ksiazka_idKsiazka);
CREATE INDEX Ksiazka_has_Autor_FKIndex2 ON Ksiazka_Autor (Autor_idAutor);

CREATE INDEX IFK_Rel_01 ON Ksiazka_Autor (Ksiazka_idKsiazka);
CREATE INDEX IFK_Rel_02 ON Ksiazka_Autor (Autor_idAutor);

-- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- 

CREATE TABLE Ksiazka_Dziedzina (
  Ksiazka_idKsiazka INTEGER   NOT NULL ,
  Dziedzina_idDziedzina INTEGER   NOT NULL   ,
PRIMARY KEY(Ksiazka_idKsiazka, Dziedzina_idDziedzina)    ,
  FOREIGN KEY(Ksiazka_idKsiazka)
    REFERENCES Ksiazka(idKsiazka),
  FOREIGN KEY(Dziedzina_idDziedzina)
    REFERENCES Dziedzina(idDziedzina));

CREATE INDEX Ksiazka_has_Dziedzina_FKIndex1 ON Ksiazka_Dziedzina (Ksiazka_idKsiazka);
CREATE INDEX Ksiazka_has_Dziedzina_FKIndex2 ON Ksiazka_Dziedzina (Dziedzina_idDziedzina);

CREATE INDEX IFK_Rel_07 ON Ksiazka_Dziedzina (Ksiazka_idKsiazka);
CREATE INDEX IFK_Rel_08 ON Ksiazka_Dziedzina (Dziedzina_idDziedzina);

-- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- 

CREATE TABLE Egzemplarz (
  idEgzemplarz SERIAL  NOT NULL ,
  Ksiazka_idKsiazka INTEGER   NOT NULL ,
  wypozyczona BOOL  DEFAULT false NOT NULL   ,
PRIMARY KEY(idEgzemplarz)  ,
  FOREIGN KEY(Ksiazka_idKsiazka)
    REFERENCES Ksiazka(idKsiazka));


CREATE INDEX Egzemplarz_FKIndex1 ON Egzemplarz (Ksiazka_idKsiazka);

CREATE INDEX IFK_Rel_09 ON Egzemplarz (Ksiazka_idKsiazka);

-- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- 

CREATE TABLE Wypozyczenie (
  Czytelnik_idCzytelnik INTEGER   NOT NULL ,
  Egzemplarz_idEgzemplarz INTEGER   NOT NULL ,
  data_wypozyczenia timestamp   NOT NULL DEFAULT NOW(),
  data_planowanego_oddania timestamp   NOT NULL DEFAULT NOW() + INTERVAL '1 MONTH',
  data_oddania timestamp  DEFAULT NULL    ,
CHECK(data_wypozyczenia < data_planowanego_oddania AND data_wypozyczenia <= data_oddania),
PRIMARY KEY(Czytelnik_idCzytelnik, Egzemplarz_idEgzemplarz, data_wypozyczenia) ,
  FOREIGN KEY(Czytelnik_idCzytelnik)
    REFERENCES Czytelnik(idCzytelnik),
  FOREIGN KEY(Egzemplarz_idEgzemplarz)
    REFERENCES Egzemplarz(idEgzemplarz));

CREATE INDEX Czytelnik_has_Egzemplarz_FKIndex1 ON Wypozyczenie (Czytelnik_idCzytelnik);
CREATE INDEX Czytelnik_has_Egzemplarz_FKIndex2 ON Wypozyczenie (Egzemplarz_idEgzemplarz);

CREATE INDEX IFK_Rel_10 ON Wypozyczenie (Czytelnik_idCzytelnik);
CREATE INDEX IFK_Rel_11 ON Wypozyczenie (Egzemplarz_idEgzemplarz);

