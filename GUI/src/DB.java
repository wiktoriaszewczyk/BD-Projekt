import java.math.BigDecimal;
import java.sql.*;
import java.util.Vector;
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

    int logowanie(boolean kto, String l, String p){
        // kto false - czytelnik, true pracownik 
        int toReturn = 0;
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
                toReturn = result;
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

    void infoAutor(DefaultListModel<Autor> autorzy){
        autorzy.clear();
        try{       
            PreparedStatement pst = c.prepareCall( "SELECT idautor, imie, nazwisko dziedziny FROM autor ORDER BY nazwisko;" );
 
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

    void infoAutor(Vector<Autor> autorzy){
        autorzy.clear();
        try{       
            PreparedStatement pst = c.prepareCall( "SELECT idautor, imie, nazwisko dziedziny FROM autor ORDER BY nazwisko;" );
 
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
                PreparedStatement pst = c.prepareCall( "SELECT idautor, imie, nazwisko dziedziny FROM autor WHERE imie LIKE ? OR nazwisko LIKE ? ORDER BY nazwisko;" );
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

    boolean aktualizujAutor(int id, String imie, String nazwisko){
        boolean toReturn = false;
        try{       
            PreparedStatement pst = c.prepareCall( "UPDATE autor SET imie = ?, nazwisko = ? WHERE idautor = ?;" );
            pst.setString(1,imie);
            pst.setString(2,nazwisko);
            pst.setInt(3,id);
 
            toReturn = (pst.executeUpdate() != 0);

            pst.close(); 
        }
        catch(SQLException e){
            System.out.println("Blad podczas przetwarzania danych:"+e);
        }
        return toReturn;
    }

    void infoWydawnictwo(DefaultListModel<Wydawnictwo> wydawnictwa){
        wydawnictwa.clear();
        try{       
            PreparedStatement pst = c.prepareCall( "SELECT idwydawnictwo, nazwa FROM wydawnictwo ORDER BY nazwa;" );
 
            ResultSet rs;
            rs = pst.executeQuery();
            while (rs.next())  {
                int id = rs.getInt(1);
                String nazwa = rs.getString(2);
                wydawnictwa.addElement(new Wydawnictwo(id, nazwa));

            }
            rs.close();      
            pst.close(); 
        }
        catch(SQLException e){
            System.out.println("Blad podczas przetwarzania danych:"+e);
        }
    }

    void infoWydawnictwo(Vector<Wydawnictwo> wydawnictwa){
        wydawnictwa.clear();
        wydawnictwa.add(new Wydawnictwo(0,""));
        try{       
            PreparedStatement pst = c.prepareCall( "SELECT idwydawnictwo, nazwa FROM wydawnictwo ORDER BY nazwa;" );
 
            ResultSet rs;
            rs = pst.executeQuery();
            while (rs.next())  {
                int id = rs.getInt(1);
                String nazwa = rs.getString(2);
                wydawnictwa.addElement(new Wydawnictwo(id, nazwa));

            }
            rs.close();      
            pst.close(); 
        }
        catch(SQLException e){
            System.out.println("Blad podczas przetwarzania danych:"+e);
        }
    }

    void infoWydawnictwo(DefaultListModel<Wydawnictwo> wydawnictwo, String tekst){
        if(tekst.equals(""))
            infoWydawnictwo(wydawnictwo);
            else{
            wydawnictwo.clear();
            try{       
                tekst = "%"+tekst+"%";
                PreparedStatement pst = c.prepareCall( "SELECT idwydawnictwo, nazwa FROM wydawnictwo WHERE nazwa LIKE ? ORDER BY nazwa;" );
                pst.setString(1,tekst);

                ResultSet rs;
                rs = pst.executeQuery();
                while (rs.next())  {
                    int id = rs.getInt(1);
                    String nazwa = rs.getString(2);
                    wydawnictwo.addElement(new Wydawnictwo(id, nazwa));

                }
                rs.close();      
                pst.close(); 
            }
            catch(SQLException e){
                System.out.println("Blad podczas przetwarzania danych:"+e);
            }
        }
    }

    boolean dodajWydawnictwo(String nazwa){
        boolean toReturn = false;
        try{       
            PreparedStatement pst = c.prepareCall( "INSERT INTO Wydawnictwo (nazwa) VALUES  (?)" );
            pst.setString(1,nazwa);
 
            toReturn = (pst.executeUpdate() != 0);

            pst.close(); 
        }
        catch(SQLException e){
            System.out.println("Blad podczas przetwarzania danych:"+e);
        }
        return toReturn;
    }

    boolean usunWydawnictwo(int id){
        boolean toReturn = false;
        try{       
            PreparedStatement pst = c.prepareCall( "DELETE FROM wydawnictwo WHERE idwydawnictwo = ?;" );
            pst.setInt(1,id);
 
            toReturn = (pst.executeUpdate() != 0);

            pst.close(); 
        }
        catch(SQLException e){
            System.out.println("Blad podczas przetwarzania danych:"+e);
        }
        return toReturn;
    }

    boolean aktualizujWydawnictwo(int id, String nazwa){
        boolean toReturn = false;
        try{       
            PreparedStatement pst = c.prepareCall( "UPDATE wydawnictwo SET nazwa = ? WHERE idwydawnictwo = ?;" );
            pst.setString(1,nazwa);
            pst.setInt(2,id);
 
            toReturn = (pst.executeUpdate() != 0);

            pst.close(); 
        }
        catch(SQLException e){
            System.out.println("Blad podczas przetwarzania danych:"+e);
        }
        return toReturn;
    }

    void infoDziedzina(Vector<Dziedzina> dziedziny, DefaultListModel<Dziedzina> dziedziny2){
        dziedziny.clear();
        dziedziny2.clear();
        dziedziny.addElement(new Dziedzina(0,0,"","----------"));
        try{       
            PreparedStatement pst = c.prepareCall( "SELECT * FROM dziedziny_naddziedziny;" );
 
            ResultSet rs;
            rs = pst.executeQuery();
            while (rs.next())  {
                int id = rs.getInt(1);
                int idNad = rs.getInt(2);
                String nazwaNad = rs.getString(3);
                String nazwa = rs.getString(4);
                dziedziny.addElement(new Dziedzina(id, idNad, nazwa, nazwaNad));
                dziedziny2.addElement(new Dziedzina(id, idNad, nazwa, nazwaNad));
            }
            rs.close();      
            pst.close(); 
        }
        catch(SQLException e){
            System.out.println("Blad podczas przetwarzania danych:"+e);
        }
    }
    
    void infoDziedzina(Vector<Dziedzina> dziedziny, DefaultListModel<Dziedzina> dziedziny2, String tekst){
        if(tekst == "")
        infoDziedzina(dziedziny, dziedziny2);
        else{
            dziedziny.clear();
            dziedziny2.clear();
            dziedziny.addElement(new Dziedzina(0,0,"","----------"));
            try{       
                tekst = "%"+tekst+"%";
                PreparedStatement pst = c.prepareCall( "SELECT * FROM dziedziny_naddziedziny WHERE nazwa LIKE ?;" );
                pst.setString(1,tekst);

                ResultSet rs;
                rs = pst.executeQuery();
                while (rs.next())  {
                    int id = rs.getInt(1);
                    int idNad = rs.getInt(2);
                    String nazwaNad = rs.getString(3);
                    String nazwa = rs.getString(4);
                    dziedziny.addElement(new Dziedzina(id, idNad, nazwa, nazwaNad));
                    dziedziny2.addElement(new Dziedzina(id, idNad, nazwa, nazwaNad));

                }
                rs.close();      
                pst.close(); 
            }
            catch(SQLException e){
                System.out.println("Blad podczas przetwarzania danych:"+e);
            }
        }
    }

    boolean dodajDziedzina(String nazwa, int id){
        boolean toReturn = false;
        try{       
            PreparedStatement pst = c.prepareCall( "INSERT INTO Dziedzina (dziedzina_iddziedzina, nazwa) VALUES (?,?)" );
            if(id == 0)
                pst.setNull(1,Types.INTEGER);
            else
                pst.setInt(1,id);
            pst.setString(2,nazwa);
 
            toReturn = (pst.executeUpdate() != 0);

            pst.close(); 
        }
        catch(SQLException e){
            System.out.println("Blad podczas przetwarzania danych:"+e);
        }
        return toReturn;
    }

    boolean usunDziedzina(int id){
        boolean toReturn = false;
        try{       
            PreparedStatement pst = c.prepareCall( "DELETE FROM dziedzina WHERE iddziedzina = ?;" );
            pst.setInt(1,id);
 
            toReturn = (pst.executeUpdate() != 0);

            pst.close(); 
        }
        catch(SQLException e){
            System.out.println("Blad podczas przetwarzania danych:"+e);
        }
        return toReturn;
    }

    boolean aktualizujDziedzina(int id, String nazwa, int idNad){
        boolean toReturn = false;
        try{       
            PreparedStatement pst = c.prepareCall( "UPDATE Dziedzina SET dziedzina_iddziedzina = ?, nazwa = ? WHERE iddziedzina = ?;" );
            pst.setInt(1,idNad);
            pst.setString(2,nazwa);
            pst.setInt(3,id);
 
            toReturn = (pst.executeUpdate() != 0);

            pst.close(); 
        }
        catch(SQLException e){
            System.out.println("Blad podczas przetwarzania danych:"+e);
        }
        return toReturn;
    }

    void infoKsiazka(DefaultListModel<Ksiazka> ksiazki){
        ksiazki.clear();
        ksiazki.addElement(null);
        try{       
            PreparedStatement pst = c.prepareCall( "SELECT idksiazka, tytul, rok_wydania, isbn, autorzy, dziedziny, idwydawnictwo, wydawnictwo FROM ksiazka_info;" );
 
            ResultSet rs;
            rs = pst.executeQuery();
            while (rs.next())  {
                int idksiazka = rs.getInt(1);
                String tytul = rs.getString(2);
                int rok_wydania = rs.getInt(3);
                BigDecimal isbn = rs.getBigDecimal(4);
                String autorzy = rs.getString(5);
                String dziedziny = rs.getString(6);
                Wydawnictwo wydawnictwo = new Wydawnictwo(rs.getInt(7),rs.getString(8));
                ksiazki.addElement(new Ksiazka(idksiazka, tytul, rok_wydania, isbn, wydawnictwo, autorzy, dziedziny));

            }
            rs.close();      
            pst.close(); 
        }
        catch(SQLException e){
            System.out.println("Blad podczas przetwarzania danych:"+e);
        }

    }

    void infoKsiazka(DefaultListModel<Ksiazka> ksiazki, String tekst){
        if(tekst == ""){
            infoKsiazka(ksiazki);
        }
        else{
            ksiazki.clear();
            ksiazki.addElement(null);
            try{       
                tekst = "%" + tekst + "%";
                PreparedStatement pst = c.prepareCall( "SELECT idksiazka, tytul, rok_wydania, isbn, autorzy, dziedziny, idwydawnictwo, wydawnictwo FROM ksiazka_info WHERE tytul LIKE ? OR autorzy LIKE ?;" );
                pst.setString(1, tekst);
                pst.setString(2, tekst);

                ResultSet rs;
                rs = pst.executeQuery();
                while (rs.next())  {
                    int idksiazka = rs.getInt(1);
                    String tytul = rs.getString(2);
                    int rok_wydania = rs.getInt(3);
                    BigDecimal isbn = rs.getBigDecimal(4);
                    String autorzy = rs.getString(5);
                    String dziedziny = rs.getString(6);
                    Wydawnictwo wydawnictwo = new Wydawnictwo(rs.getInt(7),rs.getString(8));
                    ksiazki.addElement(new Ksiazka(idksiazka, tytul, rok_wydania, isbn, wydawnictwo, autorzy, dziedziny));

                }
                rs.close();      
                pst.close(); 
            }
            catch(SQLException e){
                System.out.println("Blad podczas przetwarzania danych:"+e);
            }
        }

    }

    void infoKsiazka(DefaultListModel<Ksiazka> ksiazki, String tekst, Dziedzina dziedzina){
        if(tekst == ""){
            infoKsiazka(ksiazki);
        }
        else{
            ksiazki.clear();
            ksiazki.addElement(null);
            try{       
                tekst = "%" + tekst + "%";
                String dziedzina2 = "%" + dziedzina.getNazwa() + "%";
                PreparedStatement pst;
                if(dziedzina.getId() == 0){
                    pst = c.prepareCall( "SELECT idksiazka, tytul, rok_wydania, isbn, autorzy, dziedziny, idwydawnictwo, wydawnictwo FROM ksiazka_info WHERE tytul LIKE ? OR autorzy LIKE ?;" );
                    pst.setString(1, tekst);
                    pst.setString(2, tekst);
                }
                else{
                    pst = c.prepareCall( "SELECT idksiazka, tytul, rok_wydania, isbn, autorzy, dziedziny, idwydawnictwo, wydawnictwo FROM ksiazka_info WHERE (tytul LIKE ? OR autorzy LIKE ?) AND dziedziny LIKE ? ;" );
                    pst.setString(1, tekst);
                    pst.setString(2, tekst);
                    pst.setString(3, dziedzina2);
                }

                ResultSet rs;
                rs = pst.executeQuery();
                while (rs.next())  {
                    int idksiazka = rs.getInt(1);
                    String tytul = rs.getString(2);
                    int rok_wydania = rs.getInt(3);
                    BigDecimal isbn = rs.getBigDecimal(4);
                    String autorzy = rs.getString(5);
                    String dziedziny = rs.getString(6);
                    Wydawnictwo wydawnictwo = new Wydawnictwo(rs.getInt(7),rs.getString(8));
                    ksiazki.addElement(new Ksiazka(idksiazka, tytul, rok_wydania, isbn, wydawnictwo, autorzy, dziedziny));

                }
                rs.close();      
                pst.close(); 
            }
            catch(SQLException e){
                System.out.println("Blad podczas przetwarzania danych:"+e);
            }
        }

    }

    boolean usunKsiazka(int idKsiazka){
        boolean toReturn = false;
        try{       
            PreparedStatement pst = c.prepareCall( "DELETE FROM ksiazka WHERE idksiazka = ?;" );
            pst.setInt(1,idKsiazka);
 
            toReturn = (pst.executeUpdate() != 0);

            pst.close(); 
        }
        catch(SQLException e){
            System.out.println("Blad podczas przetwarzania danych:"+e);
        }
        return toReturn;
    }

    int dodajKsiazka(String nazwa, int rok, BigDecimal isbn, int idWydawnictwo){
        // zwraca id ksiazki
        int toReturn = 0;
        try{       
            PreparedStatement pst = c.prepareCall( "INSERT INTO Ksiazka (Wydawnictwo_idWydawnictwo, tytul, rok_wydania, ISBN) VALUES (?, ?, ?, ?);" );
            pst.setInt(1,idWydawnictwo);
            pst.setString(2,nazwa);
            pst.setInt(3,rok);
            pst.setBigDecimal(4,isbn);
 

            if(pst.executeUpdate() != 0){
                try{       
                    PreparedStatement pst2 = c.prepareCall( "SELECT idksiazka FROM Ksiazka ORDER BY idksiazka DESC LIMIT 1;" );
    
                    ResultSet rs;
                    rs = pst2.executeQuery();
                    while (rs.next()){
                        toReturn = rs.getInt(1);
                    }
                    rs.close();      
                    pst2.close(); 
                }
                catch(SQLException e){
                    System.out.println("Blad podczas przetwarzania danych:"+e);
                }
            }

            pst.close(); 
        }
        catch(SQLException e){
            System.out.println("Blad podczas przetwarzania danych:"+e);
        }
        return toReturn;
    }

    int autualizujKsiazka(int idKsiazka, String nazwa, int rok, BigDecimal isbn, int idWydawnictwo){
        // zwraca id ksiazki
        int toReturn = 0;
        try{       
            PreparedStatement pst = c.prepareCall( "UPDATE Ksiazka SET Wydawnictwo_idWydawnictwo = ?, tytul = ?, rok_wydania = ?, ISBN = ? WHERE idksiazka = ?;" );
            pst.setInt(1,idWydawnictwo);
            pst.setString(2,nazwa);
            pst.setInt(3,rok);
            pst.setBigDecimal(4,isbn);
            pst.setInt(5,idKsiazka);

            if(pst.executeUpdate() != 0){
                toReturn = idKsiazka;
            }

            pst.close(); 
        }
        catch(SQLException e){
            System.out.println("Blad podczas przetwarzania danych:"+e);
        }
        return toReturn;
    }

    void infoAutorKsiazka(DefaultListModel<Autor> autorzy, int idKsiazka){
        autorzy.clear();
        try{       
            PreparedStatement pst = c.prepareCall( "SELECT idautor, imie, nazwisko FROM ksiazka_autor ka JOIN autor a ON ka.autor_idautor = a.idautor WHERE ka.ksiazka_idksiazka = ? ORDER BY nazwisko;" );
            pst.setInt(1, idKsiazka);

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

    boolean usunAutorKsiazka(int idKsiazka, int idAutor){
        boolean toReturn = false;
        try{       
            PreparedStatement pst = c.prepareCall( "DELETE FROM ksiazka_autor WHERE ksiazka_idksiazka = ? AND autor_idautor = ?;" );
            pst.setInt(1,idKsiazka);
            pst.setInt(2, idAutor);
 
            toReturn = (pst.executeUpdate() != 0);

            pst.close(); 
        }
        catch(SQLException e){
            System.out.println("Blad podczas przetwarzania danych:"+e);
        }
        return toReturn;
    }

    boolean dodajAutorKsiazka(int idKsiazka, int idAutor){
        boolean toReturn = false;
        try{       
            PreparedStatement pst = c.prepareCall( "INSERT INTO Ksiazka_Autor (Ksiazka_idKsiazka, Autor_idAutor) VALUES (?, ?);" );
            pst.setInt(1,idKsiazka);
            pst.setInt(2,idAutor);
 
            toReturn = (pst.executeUpdate() != 0);

            pst.close(); 
        }
        catch(SQLException e){
            System.out.println("Blad podczas przetwarzania danych:"+e);
        }
        return toReturn;
    }

    void infoDziedzinaKsiazka(DefaultListModel<Dziedzina> dziedziny, int idKsiazka){
        dziedziny.clear();
        try{       
            PreparedStatement pst = c.prepareCall( "SELECT iddziedzina, iddziedzina_nad, dziedzina_nad, nazwa FROM ksiazka_dziedzina kd JOIN dziedziny_naddziedziny dn ON kd.dziedzina_iddziedzina = dn.iddziedzina WHERE ksiazka_idksiazka = ?;" );
            pst.setInt(1, idKsiazka);

            ResultSet rs;
            rs = pst.executeQuery();
            while (rs.next())  {
                int id = rs.getInt(1);
                int idNad = rs.getInt(2);
                String nazwaNad = rs.getString(3);
                String nazwa = rs.getString(4);
                dziedziny.addElement(new Dziedzina(id, idNad, nazwa, nazwaNad));

            }
            rs.close();      
            pst.close(); 
        }
        catch(SQLException e){
            System.out.println("Blad podczas przetwarzania danych:"+e);
        }
    }

    boolean usunDziedzinaKsiazka(int idKsiazka, int idAutor){
        boolean toReturn = false;
        try{       
            PreparedStatement pst = c.prepareCall( "DELETE FROM ksiazka_dziedzina WHERE ksiazka_idksiazka = ? AND dziedzina_iddziedzina = ?;" );
            pst.setInt(1,idKsiazka);
            pst.setInt(2, idAutor);
 
            toReturn = (pst.executeUpdate() != 0);

            pst.close(); 
        }
        catch(SQLException e){
            System.out.println("Blad podczas przetwarzania danych:"+e);
        }
        return toReturn;
    }

    boolean dodajDziedzinaKsiazka(int idKsiazka, int idDziedzina){
        boolean toReturn = false;
        try{       
            PreparedStatement pst = c.prepareCall( "INSERT INTO Ksiazka_Dziedzina (Ksiazka_idKsiazka, Dziedzina_idDziedzina) VALUES (?, ?);" );
            pst.setInt(1,idKsiazka);
            pst.setInt(2,idDziedzina);
 
            toReturn = (pst.executeUpdate() != 0);

            pst.close(); 
        }
        catch(SQLException e){
            System.out.println("Blad podczas przetwarzania danych:"+e);
        }
        return toReturn;
    }

    void infoEgzemplarze(DefaultListModel<Egzemplarz> list, int idKsiazka){
        list.clear();
        try{       
            PreparedStatement pst = c.prepareCall( "SELECT * FROM egzemplarz_info WHERE idksiazka = ? ORDER BY idegzemplarz;" );

            pst.setInt(1,idKsiazka);
            ResultSet rs;
            rs = pst.executeQuery();
            while (rs.next())  {
                int idegzemplarz = rs.getInt(1);
                int idksiazka = rs.getInt(2);
                boolean wypozyczona = rs.getBoolean(3);
                String tytul = rs.getString(4);
                int rok_wydania = rs.getInt(5);
                BigDecimal isbn = rs.getBigDecimal(6);
                String autorzy = rs.getString(7);
                String dziedziny = rs.getString(8);
                Wydawnictwo wydawnictwo = new Wydawnictwo(rs.getInt(9),rs.getString(10));
                list.addElement(new Egzemplarz("","","",idegzemplarz, wypozyczona, idksiazka, tytul, rok_wydania, isbn, wydawnictwo, autorzy, dziedziny));

            }
            rs.close();      
            pst.close(); 
        }
        catch(SQLException e){
            System.out.println("Blad podczas przetwarzania danych:"+e);
        }
    }

    boolean dodajEgzemplarz(int idKsiazka){
        boolean toReturn = false;
        try{       
            PreparedStatement pst = c.prepareCall( "INSERT INTO Egzemplarz (Ksiazka_idKsiazka) VALUES (?);" );
            pst.setInt(1,idKsiazka);
 
            toReturn = (pst.executeUpdate() != 0);
            pst.close(); 
        }
        catch(SQLException e){
            System.out.println("Blad podczas przetwarzania danych:"+e);
        }
        return toReturn;
    }

    boolean usunEgzemplarz(int idEgzemplarz){
        boolean toReturn = false;
        try{       
            PreparedStatement pst = c.prepareCall( "DELETE FROM Egzemplarz WHERE idEgzemplarz = ?;" );
            pst.setInt(1,idEgzemplarz);
 
            toReturn = (pst.executeUpdate() != 0);

            pst.close(); 
        }
        catch(SQLException e){
            System.out.println("Blad podczas przetwarzania danych:"+e);
        }
        return toReturn;
    }




    void infoCzytelnik(Czytelnik czytelnik){
        try{       
            PreparedStatement pst = c.prepareCall( "SELECT * FROM czytelnik WHERE idczytelnik = ?;" );
            pst.setInt(1, czytelnik.getId());

            ResultSet rs;
            rs = pst.executeQuery();
            while (rs.next())  {
                String imie = rs.getString(2);
                String nazwisko = rs.getString(3);
                String email = rs.getString(4);
                Integer telefon = rs.getInt(5); // ? null
                float kara = rs.getFloat(6);
                String login = rs.getString(7);
                String haslo = rs.getString(8);
                czytelnik.setAll(imie, nazwisko, email, telefon, kara, login, haslo);
            }
            rs.close();      
            pst.close(); 
        }
        catch(SQLException e){
            System.out.println("Blad podczas przetwarzania danych:"+e);
        }
    }

    boolean aktualizujKaraCzytelnik(Czytelnik czytelnik){
        boolean toReturn = false;
        try{       
            PreparedStatement pst = c.prepareCall( "UPDATE Czytelnik SET kara = ? WHERE idczytelnik = ?;" );
            pst.setFloat(1,0);
            pst.setInt(2,czytelnik.getId());
 
            toReturn = (pst.executeUpdate() != 0);
            if(toReturn){
                czytelnik.zaplacKara();
            }
            pst.close(); 
        }
        catch(SQLException e){
            System.out.println("Blad podczas przetwarzania danych:"+e);
        }
        return toReturn;
    }

    void infoWypozyczone(DefaultListModel<Egzemplarz> list, int idCzytelnik){
        list.clear();
        list.addElement(null);
        try{       
            PreparedStatement pst = c.prepareCall( "SELECT * FROM wypozyczone_egzemplarze_nieoddane WHERE czytelnik_idczytelnik = ? ORDER BY data_wypozyczenia DESC;" );

            pst.setInt(1,idCzytelnik);
            ResultSet rs;
            rs = pst.executeQuery();
            while (rs.next())  {
                String data_wypozyczenia = rs.getString(2);
                String data_planowanego_oddania = rs.getString(3);
                String data_oddania = "";
                int idegzemplarz = rs.getInt(4);
                int idksiazka = rs.getInt(5);
                boolean wypozyczona = rs.getBoolean(6);
                String tytul = rs.getString(7);
                int rok_wydania = rs.getInt(8);
                BigDecimal isbn = rs.getBigDecimal(9);
                String autorzy = rs.getString(10);
                String dziedziny = rs.getString(11);
                Wydawnictwo wydawnictwo = new Wydawnictwo(rs.getInt(12),rs.getString(13));
                list.addElement(new Egzemplarz(data_wypozyczenia, data_planowanego_oddania, data_oddania, idegzemplarz, wypozyczona, idksiazka, tytul, rok_wydania, isbn, wydawnictwo, autorzy, dziedziny));

            }
            rs.close();      
            pst.close(); 
        }
        catch(SQLException e){
            System.out.println("Blad podczas przetwarzania danych:"+e);
        }
    }

    boolean oddajEgzemplarz(int idCzytelnik, int idEgzemplarz){
        boolean toReturn = false;
        try{       
            PreparedStatement pst = c.prepareCall( "UPDATE Wypozyczenie SET data_oddania = NOW() WHERE Egzemplarz_idEgzemplarz = ? AND czytelnik_idCzytelnik = ?;" );
            pst.setInt(1,idEgzemplarz);
            pst.setInt(2,idCzytelnik);
 
            toReturn = (pst.executeUpdate() != 0);
            pst.close(); 
        }
        catch(SQLException e){
            System.out.println("Blad podczas przetwarzania danych:"+e);
        }
        return toReturn;
    }

    void infoZarezerwowane(DefaultListModel<Ksiazka> list, int idCzytelnik){
        list.clear();
        list.addElement(null);
        try{       
            PreparedStatement pst = c.prepareCall( "SELECT * FROM zarezerwowane_ksiazki WHERE czytelnik_idczytelnik = ?;" );
            pst.setInt(1,idCzytelnik);
            ResultSet rs;
            rs = pst.executeQuery();
            while (rs.next())  {
                int idksiazka = rs.getInt(2);
                String tytul = rs.getString(3);
                int rok_wydania = rs.getInt(4);
                BigDecimal isbn = rs.getBigDecimal(5);
                String autorzy = rs.getString(6);
                String dziedziny = rs.getString(7);
                Wydawnictwo wydawnictwo = new Wydawnictwo(rs.getInt(8),rs.getString(9));
                list.addElement(new Ksiazka(idksiazka, tytul, rok_wydania, isbn, wydawnictwo, autorzy, dziedziny));

            }
            rs.close();      
            pst.close(); 
        }
        catch(SQLException e){
            System.out.println("Blad podczas przetwarzania danych:"+e);
        }
    }

    int dostepnoscZarezerwowanejKsiazki(int idKsiazka, int idCzytelnik){
        int toReturn = 0;
        try{       
            CallableStatement cst = c.prepareCall( "{call rezerwacja_dostepnosc(?,?)}" );
            cst.setInt(1,idKsiazka);
            cst.setInt(2,idCzytelnik);
 
            ResultSet rs;
            rs = cst.executeQuery();
            while (rs.next())  {
                toReturn = rs.getInt(1);
            }

            rs.close();      
            cst.close(); 
        }
        catch(SQLException e){
            System.out.println("Blad podczas przetwarzania danych:"+e);
        }
        return toReturn;
    }

    void infoWypozyczoneOddane(DefaultListModel<String> list, int idCzytelnik){
        list.clear();
        try{       
            PreparedStatement pst = c.prepareCall( "SELECT * FROM wypozyczone_egzemplarze_oddane WHERE czytelnik_idczytelnik = ? ORDER BY data_wypozyczenia DESC;" );

            pst.setInt(1,idCzytelnik);
            ResultSet rs;
            rs = pst.executeQuery();
            while (rs.next())  {
                String data_wypozyczenia = rs.getString(2);
                // String data_planowanego_oddania = rs.getString(3);
                // String data_oddania = rs.getString(4);
                // int idegzemplarz = rs.getInt(5);
                // int idksiazka = rs.getInt(6);
                // boolean wypozyczona = rs.getBoolean(7);
                String tytul = rs.getString(8);
                // int rok_wydania = rs.getInt(9);
                // BigDecimal isbn = rs.getBigDecimal(10);
                String autorzy = rs.getString(11);
                // String dziedziny = rs.getString(12);
                list.addElement(tytul + ", " + autorzy + ". Data wypożyczenia: " +data_wypozyczenia);
                
            }
            rs.close();      
            pst.close(); 
        }
        catch(SQLException e){
            System.out.println("Blad podczas przetwarzania danych:"+e);
        }
    }

    int dostepnoscKsiazki(int idKsiazka){
        int toReturn = 0;
        try{       
            CallableStatement cst = c.prepareCall( "{call dostepnosc_ksiazki(?)}" );
            cst.setInt(1,idKsiazka);
 
            ResultSet rs;
            rs = cst.executeQuery();
            while (rs.next())  {
                toReturn = rs.getInt(1);
            }

            rs.close();      
            cst.close(); 
        }
        catch(SQLException e){
            System.out.println("Blad podczas przetwarzania danych:"+e);
        }
        return toReturn;
    }

    boolean wypozyczEgzemplarz(int idCzytelnik, int idEgzemplarz){
        boolean toReturn = false;
        try{       
            PreparedStatement pst = c.prepareCall( "INSERT INTO Wypozyczenie (Czytelnik_idCzytelnik, Egzemplarz_idEgzemplarz) VALUES (?,?);" );
            pst.setInt(1,idCzytelnik);
            pst.setInt(2,idEgzemplarz);
            
            toReturn = (pst.executeUpdate() != 0);
            pst.close(); 
        }
        catch(SQLException e){
            System.out.println("Blad podczas przetwarzania danych:"+e);
        }
        return toReturn;
    }

    boolean rezerwujEgzemplarz(int idCzytelnik, int idKsiazka){
        boolean toReturn = false;
        try{       
            PreparedStatement pst = c.prepareCall( "INSERT INTO Rezerwacja (Czytelnik_idCzytelnik, Ksiazka_idKsiazka) VALUES (?,?);" );
            pst.setInt(1,idCzytelnik);
            pst.setInt(2,idKsiazka);
            
            toReturn = (pst.executeUpdate() != 0);
            pst.close(); 
        }
        catch(SQLException e){
            System.out.println("Blad podczas przetwarzania danych:"+e);
        }
        return toReturn;
    }

    boolean usunRezerwacje(int idCzytelnik, int idKsiazka){
        boolean toReturn = false;
        try{       
            PreparedStatement pst = c.prepareCall( "DELETE FROM rezerwacja WHERE czytelnik_idczytelnik = ? AND ksiazka_idksiazka = ?;" );
            pst.setInt(1,idCzytelnik);
            pst.setInt(2,idKsiazka);
 
            toReturn = (pst.executeUpdate() != 0);

            pst.close(); 
        }
        catch(SQLException e){
            System.out.println("Blad podczas przetwarzania danych:"+e);
        }
        return toReturn;
    }


}
