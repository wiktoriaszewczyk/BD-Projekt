public class Wydawnictwo {
    private int id;
    private String nazwa;

    Wydawnictwo(){}
    Wydawnictwo(int i, String n){
        id = i;
        nazwa = n;
    }

    public String toString(){
        return nazwa;
    }

    public int getId(){
        return id;
    }
    public String getNazwa(){
        return nazwa;
    }
}
