import javax.swing.*;
import java.awt.Font;

public class KsiazkaPanel extends JPanel{
    private JLabel tytulLabel;
    private JLabel autorLabel;
    private JLabel rokWydaniaLabel;
    private JLabel isbnLabel;
    private JLabel wydawnictwoLabel;
    private JLabel dziedzinyLabel;
    private JButton powrotButton ;

    KsiazkaPanel(){
        setLayout(null);
        setBounds(0, 0, 1200, 700);

        tytulLabel = new JLabel("Tytuł: ",SwingConstants.CENTER);
        autorLabel = new JLabel("Autor: ",SwingConstants.CENTER);
        rokWydaniaLabel = new JLabel("Rok wydania: ",SwingConstants.CENTER);
        isbnLabel = new JLabel("isbn",SwingConstants.CENTER);
        wydawnictwoLabel = new JLabel("Wydawnictwo: ",SwingConstants.CENTER);
        dziedzinyLabel = new JLabel("dziedziny",SwingConstants.CENTER);
        powrotButton = new JButton("Powrót do katalogu");

        tytulLabel.setBounds(0,20,1200,50);
        tytulLabel.setFont(new Font("TimesRoman",Font.BOLD,40));

        autorLabel.setBounds(0,140,1200,30);
        dziedzinyLabel.setBounds(0,180,1200,30);
        rokWydaniaLabel.setBounds(0,220,1200,30);
        isbnLabel.setBounds(0,260,1200,30);
        wydawnictwoLabel.setBounds(0,300,1200,30);

        powrotButton.setBounds(465, 500, 270, 30);
        powrotButton.setFocusable(false);

        add(tytulLabel);
        add(autorLabel);
        add(rokWydaniaLabel);
        add(isbnLabel);
        add(wydawnictwoLabel);
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
    public JLabel getWydawnictwo(){
        return wydawnictwoLabel;
    }
    public JLabel getDziedziny(){
        return dziedzinyLabel;
    }
    public JButton getPowrotButton(){
        return powrotButton;
    }

}
