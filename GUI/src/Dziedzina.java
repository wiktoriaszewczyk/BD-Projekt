public class Dziedzina {
    int id;
    int idNad;
    String nazwa;
    String nazwaNad;

    Dziedzina(){}
    Dziedzina(int  i1, int i2, String n1, String n2){
        id = i1;
        idNad = i2;
        nazwa = n1;
        nazwaNad = n2;
    }

    public String toString(){
        return nazwa;
    }

    public int getId(){
        return id;
    }
    public int getIdNad(){
        return idNad;
    }
    public String getNazwa(){
        return nazwa;
    }
    public String getNazwaNad(){
        return nazwaNad;
    }

}
