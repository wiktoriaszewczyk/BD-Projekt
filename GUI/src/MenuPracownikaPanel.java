import javax.swing.*;
import java.awt.Font;
public class MenuPracownikaPanel extends JPanel{
    private JLabel header;
    private JButton wylogujButton;
    private JButton PracownikButton;
    private JButton AutorButton;
    private JButton dodajWydawnictwoButton;
    private JButton dodajDziedzineButton;
    private JButton dodajKsiazkeButton;
    private JButton dodajEgzemplarzButton;

    MenuPracownikaPanel(){
        setLayout(null);
        setBounds(0, 0, 1200, 700);

        header = new JLabel("Menu Pracownika",SwingConstants.CENTER);
        wylogujButton = new JButton("Wyloguj");
        PracownikButton = new JButton("Pracownicy");
        AutorButton = new JButton("Autor");
        dodajWydawnictwoButton = new JButton("Dodaj Wydawnictwo");
        dodajDziedzineButton = new JButton("Dodaj Dziedzinę");
        dodajKsiazkeButton = new JButton("Dodaj Książkę");
        dodajEgzemplarzButton = new JButton("Dodaj Egzemplarz");

        header.setBounds(0,20,1200,50);
        header.setFont(new Font("TimesRoman",Font.BOLD,40));

        PracownikButton.setBounds(30,100,150,40);
        PracownikButton.setFocusable(false);

        AutorButton.setBounds(30,150,150,40);
        AutorButton.setFocusable(false);

        dodajWydawnictwoButton.setBounds(30,200,150,40);
        dodajWydawnictwoButton.setFocusable(false);

        dodajDziedzineButton.setBounds(30,250,150,40);
        dodajDziedzineButton.setFocusable(false);

        dodajKsiazkeButton.setBounds(30,300,150,40);
        dodajKsiazkeButton.setFocusable(false);

        dodajEgzemplarzButton.setBounds(30,350,150,40);
        dodajEgzemplarzButton.setFocusable(false);

        wylogujButton.setBounds(30, 550, 150, 40);
        wylogujButton.setFocusable(false);

        add(header);
        add(PracownikButton);
        add(AutorButton);
        add(dodajWydawnictwoButton);
        add(dodajDziedzineButton);
        add(dodajKsiazkeButton);
        add(dodajEgzemplarzButton);

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
        return PracownikButton;
    }
    public JButton getAutorButton(){
        return AutorButton;
    }
}
