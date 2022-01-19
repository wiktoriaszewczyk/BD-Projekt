import java.math.BigDecimal;

public class Egzemplarz extends Ksiazka{
    private int idE;
    private boolean wypozyczona;
    private String data_wypozyczenia;
    private String data_planowanego_oddania;
    private String data_oddania;
    
    Egzemplarz(){}
    Egzemplarz(String data_w, String data_po, String data_o, int iE, boolean wyp, int id, String t, int rok, BigDecimal i, String a, String d){
        super(id, t, rok, i, a, d);
        idE = iE;
        wypozyczona = wyp;
        data_wypozyczenia = data_w;
        data_planowanego_oddania = data_po;
        data_oddania = data_o;
    }

    public int getIdE(){
        return idE;
    }
    public boolean getWypozyczona(){
        return wypozyczona;
    }
    public String getDataWypozyczenia(){
        return data_wypozyczenia;
    }
    public String getDataPlanowanegoOddania(){
        return data_planowanego_oddania;
    }
    public String getDataOddania(){
        return data_oddania;
    }


}
