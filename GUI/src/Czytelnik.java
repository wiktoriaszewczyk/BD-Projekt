public class Czytelnik {
    private int id = 0;
    private String imie;
    private String nazwisko;
    private String email;
    private int telefon;
    private float kara;
    private String login;
    private String haslo;

    void setAll(String i, String n, String e, int t, float k, String l, String h){
        imie = i;
        nazwisko = n;
        email = e;
        telefon = t;
        kara = k;
        login = l;
        haslo = h;
    }

    void setId(int i){
        id = i;
        if(i == 0){
            imie = "";
            nazwisko = "";
            email = "";
            telefon = 0;
            kara = 0;
            login = "";
            haslo = "";
        }
    }

    void zaplacKara(){
        kara = 0;
    }

    int getId(){
        return id;
    }
    String getImie(){
        return imie;
    }
    String getNazwisko(){
        return nazwisko;
    }
    String getEmail(){
        return email;
    }
    int getTelefon(){
        return telefon;
    }
    float getKara(){
        return kara;
    }
    String getLogin(){
        return login;
    }
    String getHaslo(){
        return haslo;
    }
    
}
