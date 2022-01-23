--  Usuwanie wszystkich widoków
DROP VIEW dziedziny_naddziedziny CASCADE;
DROP VIEW dziedziny_ksiazki CASCADE;

-- Usuwanie 
DROP FUNCTION logowanie_pracownik(text, text);

-- Usuwanie tabel
DROP TABLE Wypozyczenie CASCADE;
DROP TABLE Egzemplarz CASCADE;
DROP TABLE Ksiazka_Dziedzina CASCADE;
DROP TABLE Ksiazka_Autor CASCADE;
DROP TABLE Rezerwacja CASCADE;
DROP TABLE Ksiazka CASCADE;
DROP TABLE Czytelnik CASCADE;
DROP TABLE Autor CASCADE;
DROP TABLE Dziedzina CASCADE;
DROP TABLE Wydawnictwo CASCADE;
DROP TABLE Pracownik CASCADE;

