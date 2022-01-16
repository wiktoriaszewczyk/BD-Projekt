-- Wszystkie dziedziny ze swoją nadrzędną dziedziną
SELECT d1.nazwa AS dziedzina_nad, d2.nazwa
FROM Dziedzina d1 RIGHT JOIN Dziedzina d2 
ON d1.idDziedzina = d2.dziedzina_idDziedzina;

-- Książka info i autorzy
SELECT k.idKsiazka, k.tytul, k.rok_wydania, k.isbn, string_agg((a.imie || ' ' || a.nazwisko), ', ')  AS autor
FROM Ksiazka k 
JOIN Ksiazka_Autor ka ON k.idKsiazka = ka.ksiazka_idKsiazka 
JOIN Autor a ON ka.Autor_idAutor = a.idAutor
GROUP BY k.idKsiazka;

-- Książka, dziedziny
SELECT k.idksiazka, (string_agg(d.dziedzina_nad, ', ') || ', ' || string_agg(d.nazwa, ', ')) as dziedziny 
FROM Ksiazka k 
JOIN Ksiazka_Dziedzina kd ON k.idKsiazka = kd.ksiazka_idKsiazka 
JOIN 
(SELECT d2.idDziedzina as idDziedzina, d1.nazwa AS dziedzina_nad, d2.nazwa
FROM Dziedzina d1 RIGHT JOIN Dziedzina d2 
ON d1.idDziedzina = d2.dziedzina_idDziedzina) d 
ON kd.dziedzina_idDziedzina = d.idDziedzina
GROUP BY k.idKsiazka;

-- Książka, autorzy, dziedziny



