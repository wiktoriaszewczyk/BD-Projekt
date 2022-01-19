import javax.swing.*;
import java.awt.Font;
public class MenuCzytelnikaPanel extends JPanel{
    private JLabel header;
    private JButton wylogujButton;
    private JButton daneButton;
    private JButton wypozyczeniaButton;
    private JButton rezerwacjeButton;
    private JButton historiaButton;
    private JButton katalogButton;

    MenuCzytelnikaPanel(){
        setLayout(null);
        setBounds(0, 0, 1200, 700);

        header = new JLabel("Menu Czytelnika",SwingConstants.CENTER);
        wylogujButton = new JButton("Wyloguj");
        daneButton = new JButton("Moje dane");
        wypozyczeniaButton = new JButton("Wypożyczone");
        rezerwacjeButton = new JButton("Zarezerwowane");
        historiaButton = new JButton("Historia wypożyczeń");
        katalogButton = new JButton("Katalog książek");

        header.setBounds(0,20,1200,50);
        header.setFont(new Font("TimesRoman",Font.BOLD,40));

        daneButton.setBounds(30,100,150,40);
        daneButton.setFocusable(false);

        wypozyczeniaButton.setBounds(30,150,150,40);
        wypozyczeniaButton.setFocusable(false);

        rezerwacjeButton.setBounds(30,200,150,40);
        rezerwacjeButton.setFocusable(false);

        historiaButton.setBounds(30,250,150,40);
        historiaButton.setFocusable(false);

        katalogButton.setBounds(30,300,150,40);
        katalogButton.setFocusable(false);

        wylogujButton.setBounds(30, 550, 150, 40);
        wylogujButton.setFocusable(false);

        add(header);
        add(daneButton);
        add(wypozyczeniaButton);
        add(rezerwacjeButton);
        add(historiaButton);
        add(katalogButton);

        add(wylogujButton);

        setVisible(false);
    }

    public void restart(){
        removeAll();
        revalidate();
        repaint();

        add(header);
        add(daneButton);
        add(wypozyczeniaButton);
        add(rezerwacjeButton);
        add(historiaButton);
        add(katalogButton);

        add(wylogujButton);

        setVisible(false);
    }

    public JLabel getHeader(){
        return header;
    }
    public JButton getWylogujButton(){
        return wylogujButton;
    } 
    public JButton getDaneButton(){
        return daneButton;
    }
    public JButton getWypozyczeniaButton(){
        return wypozyczeniaButton;
    }
    public JButton getRezerwacjeButton(){
        return rezerwacjeButton;
    }
    public JButton getHistoriaButton(){
        return historiaButton;
    }
    public JButton getKatalogButton(){
        return katalogButton;
    }
}
