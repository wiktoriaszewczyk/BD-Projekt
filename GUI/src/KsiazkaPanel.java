import javax.swing.*;
import java.awt.Font;

public class KsiazkaPanel extends JPanel{
    private JLabel tytulLabel;
    private JLabel autorLabel;
    private JLabel rokWydaniaLabel;
    private JLabel isbnLabel;
    private JLabel dziedzinyLabel;
    private JButton powrotButton ;
    /*
        label ile egzemplarzy 
        label ile dostepnych
        button wypozycz
        button zarezerwuj
    */

    KsiazkaPanel(){
        setLayout(null);
        setBounds(0, 0, 1200, 700);

        tytulLabel = new JLabel("tytuł",SwingConstants.CENTER);
        autorLabel = new JLabel("autor");
        rokWydaniaLabel = new JLabel("rok_wydania");
        isbnLabel = new JLabel("isbn");
        dziedzinyLabel = new JLabel("dziedziny");
        powrotButton = new JButton("Powrót do katalogu");

        tytulLabel.setBounds(0,20,1200,50);
        tytulLabel.setFont(new Font("TimesRoman",Font.BOLD,40));

        autorLabel.setBounds(0,60,1200,30);
        dziedzinyLabel.setBounds(0,100,1200,30);
        rokWydaniaLabel.setBounds(0,140,1200,30);
        isbnLabel.setBounds(0,180,1200,30);

        powrotButton.setBounds(465, 500, 270, 30);
        powrotButton.setFocusable(false);

        add(tytulLabel);
        add(autorLabel);
        add(rokWydaniaLabel);
        add(isbnLabel);
        add(dziedzinyLabel);
        add(powrotButton);

        setVisible(false);
    }

    public JLabel getTytul(){
        return tytulLabel;
    }
    public JLabel getAutor(){
        return autorLabel;
    }
    public JLabel getRokWydania(){
        return rokWydaniaLabel;
    }
    public JLabel getIsbn(){
        return isbnLabel;
    }
    public JLabel getDziedziny(){
        return dziedzinyLabel;
    }
    public JButton getPowrotButton(){
        return powrotButton;
    }

}
