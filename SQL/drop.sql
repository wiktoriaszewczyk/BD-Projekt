-- Usuwanie tabel i wszystkiego z nimi związanego
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

-- Restart bazy:
-- Wywołanie kolejno plików -> drop, create, view, function, trigger, insert