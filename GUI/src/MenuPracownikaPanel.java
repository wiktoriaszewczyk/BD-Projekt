import javax.swing.*;
import java.awt.Font;
public class MenuPracownikaPanel extends JPanel{
    private JLabel header;
    private JButton wylogujButton;
    private JButton pracownikButton;
    private JButton autorButton;
    private JButton wydawnictwoButton;
    private JButton dziedzinaButton;
    private JButton ksiazkaButton;
    private JButton egzemplarzButton;

    MenuPracownikaPanel(){
        setLayout(null);
        setBounds(0, 0, 1200, 700);

        header = new JLabel("Menu Pracownika",SwingConstants.CENTER);
        wylogujButton = new JButton("Wyloguj");
        pracownikButton = new JButton("Pracownicy");
        autorButton = new JButton("Autor");
        wydawnictwoButton = new JButton("Wydawnictwo");
        dziedzinaButton = new JButton("Dziedzina");
        ksiazkaButton = new JButton("Książka");
        egzemplarzButton = new JButton("Egzemplarz");

        header.setBounds(0,20,1200,50);
        header.setFont(new Font("TimesRoman",Font.BOLD,40));

        pracownikButton.setBounds(30,100,150,40);
        pracownikButton.setFocusable(false);

        autorButton.setBounds(30,150,150,40);
        autorButton.setFocusable(false);

        wydawnictwoButton.setBounds(30,200,150,40);
        wydawnictwoButton.setFocusable(false);

        dziedzinaButton.setBounds(30,250,150,40);
        dziedzinaButton.setFocusable(false);

        ksiazkaButton.setBounds(30,300,150,40);
        ksiazkaButton.setFocusable(false);

        egzemplarzButton.setBounds(30,350,150,40);
        egzemplarzButton.setFocusable(false);

        wylogujButton.setBounds(30, 550, 150, 40);
        wylogujButton.setFocusable(false);

        add(header);
        add(pracownikButton);
        add(autorButton);
        add(wydawnictwoButton);
        add(dziedzinaButton);
        add(ksiazkaButton);
        add(egzemplarzButton);

        add(wylogujButton);

        setVisible(false);
    }

    public void restart(){
        removeAll();
        revalidate();
        repaint();

        add(header);
        add(pracownikButton);
        add(autorButton);
        add(wydawnictwoButton);
        add(dziedzinaButton);
        add(ksiazkaButton);
        add(egzemplarzButton);

        add(wylogujButton);

        setVisible(false);
    }

    public JLabel getHeader(){
        return header;
    }
    public JButton getWylogujButton(){
        return wylogujButton;
    } 
    public JButton getPracownikButton(){
        return pracownikButton;
    }
    public JButton getAutorButton(){
        return autorButton;
    }
    public JButton getWydawnictwoButton(){
        return wydawnictwoButton;
    }
    public JButton getDziedzinaButton(){
        return dziedzinaButton;
    }
    public JButton getKsiazkaButton(){
        return ksiazkaButton;
    }
    public JButton getEgzemplarzButton(){
        return egzemplarzButton;
    }
}
