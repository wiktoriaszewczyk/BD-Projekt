import java.math.BigDecimal;

public class Ksiazka {
    private int idksiazka;
    private String tytul;
    private int rok_wydania;
    private BigDecimal isbn;
    private String autorzy;
    private String dziedziny;

    Ksiazka(){}

    Ksiazka(int id, String t, int rok, BigDecimal i, String a, String d){
        idksiazka = id;
        tytul = t;
        rok_wydania = rok;
        isbn = i;
        autorzy = a;
        dziedziny = d;
    }

    int getIdksiazka(){
        return idksiazka;
    }
    String getTytul(){
        return tytul;
    }
    int getRokwydania(){
        return rok_wydania;
    }
    BigDecimal getIsbn(){
        return isbn;
    }
    String getAutorzy(){
        return autorzy;
    }
    String getDziedziny(){
        return dziedziny;
    }

    public String toString(){
        //return idksiazka + ", " + tytul + ", " + rok_wydania + ", " + isbn + ", " + autorzy + ", " + dziedziny;
        return tytul + ", " + autorzy;
    }

}
