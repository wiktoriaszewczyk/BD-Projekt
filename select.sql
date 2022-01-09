-- Wszystkie dziedziny ze swoją nadrzędną dziedziną
SELECT d1.nazwa AS "dziedzina_nad", d2.nazwa
FROM Dziedzina d1 RIGHT JOIN Dziedzina d2 
ON d1.idDziedzina = d2.dziedzina_idDziedzina;

-- Książka i autorzy
SELECT * 
FROM Ksiazka k 
JOIN Ksiazka_Autor ka ON k.idKsiazka = ka.ksiazka_idKsiazka 
JOIN Autor a ON ka.Autor_idAutor = a.idAutor;

-- Książka, dziedzina i dziedzina nadrzedna
SELECT * 
FROM Ksiazka k 
JOIN Ksiazka_Dziedzina kd ON k.idKsiazka = kd.ksiazka_idKsiazka 
JOIN 
(SELECT d2.idDziedzina as idDziedzina, d1.nazwa AS "dziedzina_nad", d2.nazwa
FROM Dziedzina d1 RIGHT JOIN Dziedzina d2 
ON d1.idDziedzina = d2.dziedzina_idDziedzina) d 
ON kd.dziedzina_idDziedzina = d.idDziedzina;