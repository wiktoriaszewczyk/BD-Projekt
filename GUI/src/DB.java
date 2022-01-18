import java.math.BigDecimal;
import java.sql.*;
// import java.util.ArrayList;

import javax.swing.DefaultListModel;

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
            pst.setString(2,imie);
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

    void infoKsiazka(DefaultListModel<Ksiazka> ksiazki){
        // cos co czysci ksiazki - tak żeby wywolywać te funkcje kilka razy, po zmianach w tabeli
        ksiazki.clear();
        try{       
            PreparedStatement pst = c.prepareCall( "SELECT idksiazka, tytul, rok_wydania, isbn, autorzy, dziedziny FROM ksiazka_info;" );
 
            ResultSet rs;
            rs = pst.executeQuery();
            while (rs.next())  {
                int idksiazka = rs.getInt(1);
                String tytul = rs.getString(2);
                int rok_wydania = rs.getInt(3);
                BigDecimal isbn = rs.getBigDecimal(4);
                String autorzy = rs.getString(5);
                String dziedziny = rs.getString(6);
                // int ilosc_egzemplarzy = 
                // System.out.println(idksiazka + ", " + tytul + ", " + rok_wydania + ", " + isbn + ", " + autorzy + ", " + dziedziny);
                //ksiazki.add(new Ksiazka(idksiazka, tytul, rok_wydania, isbn, autorzy, dziedziny));
                ksiazki.addElement(new Ksiazka(idksiazka, tytul, rok_wydania, isbn, autorzy, dziedziny));

            }
            rs.close();      
            pst.close(); 
        }
        catch(SQLException e){
            System.out.println("Blad podczas przetwarzania danych:"+e);
        }

    }

    void infoAutor(DefaultListModel<Autor> autorzy){
        autorzy.clear();
        try{       
            PreparedStatement pst = c.prepareCall( "SELECT idautor, imie, nazwisko dziedziny FROM autor;" );
 
            ResultSet rs;
            rs = pst.executeQuery();
            while (rs.next())  {
                int idautor = rs.getInt(1);
                String imie = rs.getString(2);
                String nazwisko = rs.getString(3);
                autorzy.addElement(new Autor(idautor, imie, nazwisko));

            }
            rs.close();      
            pst.close(); 
        }
        catch(SQLException e){
            System.out.println("Blad podczas przetwarzania danych:"+e);
        }
    }

    void infoAutor(DefaultListModel<Autor> autorzy, String tekst){
        // popbiera autorów których imie lub nazwisko zawiera tekst
        if(tekst.equals(""))
            infoAutor(autorzy);
            else{
            autorzy.clear();
            try{       
                tekst = "%"+tekst+"%";
                PreparedStatement pst = c.prepareCall( "SELECT idautor, imie, nazwisko dziedziny FROM autor WHERE imie LIKE ? OR nazwisko LIKE ?;" );
                pst.setString(1,tekst);
                pst.setString(2,tekst);

                ResultSet rs;
                rs = pst.executeQuery();
                while (rs.next())  {
                    int idautor = rs.getInt(1);
                    String imie = rs.getString(2);
                    String nazwisko = rs.getString(3);
                    autorzy.addElement(new Autor(idautor, imie, nazwisko));

                }
                rs.close();      
                pst.close(); 
            }
            catch(SQLException e){
                System.out.println("Blad podczas przetwarzania danych:"+e);
            }
        }
    }

    boolean dodajAutor(String imie, String nazwisko){
        boolean toReturn = false;
        try{       
            PreparedStatement pst = c.prepareCall( "INSERT INTO Autor (imie, nazwisko) VALUES  (?,?)" );
            pst.setString(1,imie);
            pst.setString(2,nazwisko);
 
            toReturn = (pst.executeUpdate() != 0);

            pst.close(); 
        }
        catch(SQLException e){
            System.out.println("Blad podczas przetwarzania danych:"+e);
        }
        return toReturn;
    }

    boolean usunAutor(int id){
        boolean toReturn = false;
        try{       
            PreparedStatement pst = c.prepareCall( "DELETE FROM autor WHERE idautor = ?;" );
            pst.setInt(1,id);
 
            toReturn = (pst.executeUpdate() != 0);

            pst.close(); 
        }
        catch(SQLException e){
            System.out.println("Blad podczas przetwarzania danych:"+e);
        }
        return toReturn;
    }

}
