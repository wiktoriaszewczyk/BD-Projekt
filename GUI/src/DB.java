import java.sql.*;

public class DB {
    Connection c = null; 

    DB(){
        try{
            String dbaseURL = "jdbc:postgresql://tyke.db.elephantsql.com:5432/ifxtftxk";
            String username  = "ifxtftxk";
            String password  = "0g7OqkBeJSAZuc67LqNSPiuns4STF5ZP"; 
            c = DriverManager.getConnection(dbaseURL, username, password);
        }
            catch(SQLException se){
            System.out.println("Brak polaczenia z baza danych, wydruk logu sledzenia i koniec.");
            se.printStackTrace();
            System.exit(1);
        }
        System.out.println("Połączenie!");
    }

    boolean logowanie(boolean kto, String l, String p){
        // kto false - czytelnik, true pracownik 
        boolean toReturn = false;
        try{       

            CallableStatement cst;
            if(kto)
                cst = c.prepareCall( "{call logowanie_pracownik(?,?)}" );
            else
                cst = c.prepareCall( "{call logowanie_czytelnik(?,?)}" );
            cst.setString(1,l);
            cst.setString(2,p);
 
            ResultSet rs;
            rs = cst.executeQuery();
            while (rs.next())  {
                int result = rs.getInt(1);
                toReturn = (result != 0);
            }

            rs.close();      
            cst.close(); 
        }
        catch(SQLException e){
            System.out.println("Blad podczas przetwarzania danych:"+e);
        }
        return toReturn;
    }

    boolean rejestracjaCzytelnik(String imie, String nazwisko, String email, String telefon, String login, String haslo1){
        return true;
    }

}
