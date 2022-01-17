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

    boolean rejestracjaCzytelnik(String imie, String nazwisko, String email, int telefon, String login, String haslo){
        boolean toReturn = false;
        try{       
            PreparedStatement pst = c.prepareCall( "INSERT INTO Czytelnik (imie, nazwisko, email, telefon, login, haslo) VALUES (?,?,?,?,?,?)" );
            pst.setString(1,imie);
            pst.setString(2,nazwisko);
            pst.setString(3,email);
            if(telefon!=0)
                pst.setInt(4,telefon);
            else
                pst.setNull(4,Types.INTEGER);
            pst.setString(5,login);
            pst.setString(6,haslo);
 
            toReturn = (pst.executeUpdate() != 0);
            
            pst.close(); 

        }
        catch(SQLException e){
            System.out.println("Blad podczas przetwarzania danych:"+e);
        }
        return toReturn;
    }

    boolean rejestracjaPracownik(String imie, String nazwisko, String email, String login, String haslo){
        boolean toReturn = false;
        try{       
            PreparedStatement pst = c.prepareCall( "INSERT INTO Pracownik (login, imie, nazwisko, email, haslo) VALUES  (?,?,?,?,?)" );
            pst.setString(1,login);
            pst.setString(1,imie);
            pst.setString(3,nazwisko);
            pst.setString(4,email);
            pst.setString(5,haslo);
 
            toReturn = (pst.executeUpdate() != 0);

            pst.close(); 
        }
        catch(SQLException e){
            System.out.println("Blad podczas przetwarzania danych:"+e);
        }
        return toReturn;
    }

}
