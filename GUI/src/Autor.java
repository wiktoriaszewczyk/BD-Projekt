public class Autor {
    private int id;
    private String imie;
    private String nazwisko;
    
    Autor(){}
    Autor(int i, String im, String na){
        id = i;
        imie = im;
        nazwisko = na;
    }

    public String toString(){
        return imie + " " + nazwisko;
    }

    public int getId(){
        return id;
    }
    public String getImie(){
        return imie;
    }
    public String getNazwisko(){
        return nazwisko;
    }

}
