import javax.swing.*;
import java.sql.*;

public class App{
    public App(){
        JFrame frame = new JFrame();
        frame.setSize(300,300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("AAAA");
        frame.setVisible(true);
    }

    public static void main(String[] args){
        new App();
        Connection c = null; 
        try{
          c = DriverManager.getConnection("jdbc:postgresql://tyke.db.elephantsql.com:5432/ifxtftxk",
                                          "ifxtftxk", "0g7OqkBeJSAZuc67LqNSPiuns4STF5ZP");
        }
        catch(SQLException se){
          System.out.println("Brak polaczenia z baza danych, wydruk logu sledzenia i koniec.");
          se.printStackTrace();
          System.exit(1);
        }
    }
}