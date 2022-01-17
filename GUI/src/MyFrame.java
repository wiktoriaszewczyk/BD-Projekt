import javax.swing.*;
import java.awt.Font;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MyFrame extends JFrame{

    private static DB db = new DB(); 

    private static boolean zalogowany_czytelnik = false;
    private static boolean zalogowany_pracownik = false;

    private static JPanel startPanel;
    private static JPanel loginPracownikPanel;
    private static JPanel zarejestrujCzytelnikPanel;
    private static JPanel zarejestrujPracownikPanel;
    private static JPanel katalogKsiazekPanel;

    MyFrame(){
        this.setTitle("Biblioteka - Projekt BD Wiktoria Szewczyk");
        this.setSize(1200,700);
        this.setLocationRelativeTo(null);  // center frame
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);

        startPanelInit();

        loginPracownikPanelInit();
        zarejestrujPracownikPanelInit();

        zarejestrujCzytelnikPanelInit();

        katalogKsiazekPanelInit();

        this.add(startPanel);
        this.add(loginPracownikPanel);
        this.add(zarejestrujCzytelnikPanel);
        this.add(katalogKsiazekPanel);


        // this.setLayout(null);

        // button = new JButton("Klik");
        // button.setBounds(200,100,100,50);
        // // button.addActionListener(this);
        // button.addActionListener(e -> buttonAction());
        // button.setFocusable(false);
        // button.setBackground(Color.CYAN);
        // button.setBorder(BorderFactory.createEtchedBorder());
        // //button.setEnabled(false); // dissable a button

        this.setVisible(true);
    }

    private static void startPanelInit(){
        startPanel = new JPanel();
        startPanel.setBounds(0, 0, 1200, 700);
        startPanel.setLayout(null);
        // startPanel.setBackground(new Color(0x031926));

        JLabel header = new JLabel("BIBLIOTEKA", SwingConstants.CENTER);
        JLabel loginLabel = new JLabel("Login: ");
        JTextField login = new JTextField();
        JLabel hasloLabel = new JLabel("Hasło: ");
        JPasswordField haslo = new JPasswordField();
        JButton buttonLogin = new JButton("Zaloguj się");
        JButton buttonZarejestruj = new JButton("Zarejestruj się");
        JLabel bladLogowania = new JLabel("");
        JButton ksiegozbiorButton = new JButton("Katalog ksiażek");
        JButton buttonPracownik = new JButton("Dla pracowników");

        header.setBounds(0,20,1200,50);
        // header.setBorder(BorderFactory.createLineBorder(Color.black));
        header.setFont(new Font("TimesRoman",Font.BOLD,40));

        loginLabel.setBounds(465, 200, 80, 25);
        login.setBounds(565, 200, 170, 25);

        hasloLabel.setBounds(465, 240, 80, 25);
        haslo.setBounds(565, 240, 170, 25);

        buttonLogin.setBounds(465, 280 ,130, 30);
        buttonLogin.setFocusable(false);
        buttonLogin.addActionListener(e -> bladLogowania.setText(actionZaloguj(false,login.getText(), String.valueOf(haslo.getPassword()))));

        buttonZarejestruj.setBounds(605, 280, 130, 30);
        buttonZarejestruj.setFocusable(false);
        buttonZarejestruj.addActionListener(e -> {startPanel.setVisible(false); zarejestrujCzytelnikPanel.setVisible(true);});

        bladLogowania.setBounds(465, 320, 270, 30);

        ksiegozbiorButton.setBounds(465, 450, 270, 30);
        ksiegozbiorButton.setFocusable(false);
        ksiegozbiorButton.addActionListener(e -> {startPanel.setVisible(false); katalogKsiazekPanel.setVisible(true);});

        buttonPracownik.setBounds(465, 500, 270, 30);
        buttonPracownik.setFocusable(false);
        buttonPracownik.addActionListener(e -> {startPanel.setVisible(false); loginPracownikPanel.setVisible(true);});

        startPanel.add(header);
        startPanel.add(loginLabel);
        startPanel.add(login);
        startPanel.add(hasloLabel);
        startPanel.add(haslo);
        startPanel.add(buttonLogin);
        startPanel.add(buttonZarejestruj);
        startPanel.add(bladLogowania);
        startPanel.add(ksiegozbiorButton);
        startPanel.add(buttonPracownik);
    }

    private static void loginPracownikPanelInit(){
        loginPracownikPanel = new JPanel();
        loginPracownikPanel.setBounds(0, 0, 1200, 700);
        loginPracownikPanel.setLayout(null);
        // startPanel.setBackground(new Color(0x031926));

        JLabel header = new JLabel("Zaloguj się jako pracownik",SwingConstants.CENTER);
        JLabel loginLabel = new JLabel("Login: ");
        JTextField login = new JTextField();
        JLabel hasloLabel = new JLabel("Hasło: ");
        JPasswordField haslo = new JPasswordField();
        JButton buttonLogin = new JButton("Zaloguj się");
        JLabel bladLogowania = new JLabel("");
        JButton powrotButton = new JButton("Powrót do startu");

        header.setBounds(0,20,1200,50);
        header.setFont(new Font("TimesRoman",Font.BOLD,40));

        loginLabel.setBounds(465, 200, 80, 25);
        login.setBounds(565, 200, 170, 25);

        hasloLabel.setBounds(465, 240, 80, 25);
        haslo.setBounds(565, 240, 170, 25);

        buttonLogin.setBounds(465, 280 ,270, 30);
        buttonLogin.setFocusable(false);
        buttonLogin.addActionListener(e -> bladLogowania.setText(actionZaloguj(true,login.getText(), String.valueOf(haslo.getPassword()))));

        bladLogowania.setBounds(465, 320, 270, 30);

        powrotButton.setBounds(465, 500, 270, 30);
        powrotButton.setFocusable(false);
        powrotButton.addActionListener(e -> {startPanel.setVisible(true); loginPracownikPanel.setVisible(false);});


        loginPracownikPanel.add(header);
        loginPracownikPanel.add(loginLabel);
        loginPracownikPanel.add(login);
        loginPracownikPanel.add(hasloLabel);
        loginPracownikPanel.add(haslo);
        loginPracownikPanel.add(buttonLogin);
        loginPracownikPanel.add(bladLogowania);
        loginPracownikPanel.add(powrotButton);

        loginPracownikPanel.setVisible(false);
    }

    private static void zarejestrujCzytelnikPanelInit(){
        zarejestrujCzytelnikPanel = new JPanel();
        zarejestrujCzytelnikPanel.setLayout(null);
        zarejestrujCzytelnikPanel.setBounds(0, 0, 1200, 700);

        JLabel header = new JLabel("Zarejestruj się",SwingConstants.CENTER);
        JLabel imieLabel = new JLabel("Imie* ");
        JTextField imie = new JTextField();
        JLabel nazwiskoLabel = new JLabel("Nazwisko* ");
        JTextField nazwisko = new JTextField();
        JLabel emailLabel = new JLabel("Email* ");
        JTextField email = new JTextField();
        JLabel telefonLabel = new JLabel("Telefon ");
        JTextField telefon= new JTextField();
        JLabel loginLabel = new JLabel("Login* ");
        JTextField login= new JTextField();
        JLabel haslo1Label = new JLabel("Hasło* ");
        JPasswordField haslo1 = new JPasswordField();
        JLabel haslo2Label = new JLabel("Powtórz hasło* ");
        JPasswordField haslo2 = new JPasswordField();
        JButton buttonRejestracja = new JButton("Zarejestruj się");
        JLabel infoLabel = new JLabel("* pole wymagane");
        JButton powrotButton = new JButton("Powrót do startu");

        header.setBounds(0,20,1200,50);
        header.setFont(new Font("TimesRoman",Font.BOLD,40));

        imieLabel.setBounds(465, 150, 80, 25);
        imie.setBounds(565, 150, 170, 25);

        nazwiskoLabel.setBounds(465, 190, 80, 25);
        nazwisko.setBounds(565, 190, 170, 25);

        emailLabel.setBounds(465, 230, 80, 25);
        email.setBounds(565, 230, 170, 25);

        telefonLabel.setBounds(465, 270, 80, 25);
        telefon.setBounds(565, 270, 170, 25);

        loginLabel.setBounds(465, 310, 80, 25);
        login.setBounds(565, 310, 170, 25);

        haslo1Label.setBounds(465, 350, 80, 25);
        haslo1.setBounds(565, 350, 170, 25);

        haslo2Label.setBounds(465, 390, 90, 25);
        haslo2.setBounds(565, 390, 170, 25);

        buttonRejestracja.setBounds(465, 430 ,270, 30);
        buttonRejestracja.setFocusable(false);
        buttonRejestracja.addActionListener(e -> infoLabel.setText(actionRejestracjaCzytelnik(imie.getText(), nazwisko.getText(), email.getText(), telefon.getText(), login.getText(), String.valueOf(haslo1.getPassword()), String.valueOf(haslo2.getPassword()))));

        infoLabel.setBounds(465, 470, 270, 30);

        powrotButton.setBounds(465, 600, 270, 30);
        powrotButton.setFocusable(false);
        powrotButton.addActionListener(e -> {startPanel.setVisible(true); zarejestrujCzytelnikPanel.setVisible(false);});

        zarejestrujCzytelnikPanel.add(header);
        zarejestrujCzytelnikPanel.add(imieLabel);
        zarejestrujCzytelnikPanel.add(imie);
        zarejestrujCzytelnikPanel.add(nazwiskoLabel);
        zarejestrujCzytelnikPanel.add(nazwisko);
        zarejestrujCzytelnikPanel.add(emailLabel);
        zarejestrujCzytelnikPanel.add(email);
        zarejestrujCzytelnikPanel.add(telefonLabel);
        zarejestrujCzytelnikPanel.add(telefon);
        zarejestrujCzytelnikPanel.add(loginLabel);
        zarejestrujCzytelnikPanel.add(login);
        zarejestrujCzytelnikPanel.add(haslo1Label);
        zarejestrujCzytelnikPanel.add(haslo1);
        zarejestrujCzytelnikPanel.add(haslo2Label);
        zarejestrujCzytelnikPanel.add(haslo2);
        zarejestrujCzytelnikPanel.add(buttonRejestracja);
        zarejestrujCzytelnikPanel.add(infoLabel);
        zarejestrujCzytelnikPanel.add(powrotButton);

        zarejestrujCzytelnikPanel.setVisible(false);
    }

    private static void zarejestrujPracownikPanelInit(){
        zarejestrujPracownikPanel = new JPanel();
        zarejestrujPracownikPanel.setLayout(null);
        zarejestrujPracownikPanel.setBounds(0, 0, 1200, 700);

        JLabel header = new JLabel("Zarejestruj się",SwingConstants.CENTER);
        JLabel imieLabel = new JLabel("Imie* ");
        JTextField imie = new JTextField();
        JLabel nazwiskoLabel = new JLabel("Nazwisko* ");
        JTextField nazwisko = new JTextField();
        JLabel emailLabel = new JLabel("Email* ");
        JTextField email = new JTextField();
        JLabel loginLabel = new JLabel("Login* ");
        JTextField login= new JTextField();
        JLabel haslo1Label = new JLabel("Hasło* ");
        JPasswordField haslo1 = new JPasswordField();
        JLabel haslo2Label = new JLabel("Powtórz hasło* ");
        JPasswordField haslo2 = new JPasswordField();
        JButton buttonRejestracja = new JButton("Zarejestruj się");
        JLabel infoLabel = new JLabel("* pole wymagane");
        JButton powrotButton = new JButton("Powrót do startu");

        header.setBounds(0,20,1200,50);
        header.setFont(new Font("TimesRoman",Font.BOLD,40));

        imieLabel.setBounds(465, 150, 80, 25);
        imie.setBounds(565, 150, 170, 25);

        nazwiskoLabel.setBounds(465, 190, 80, 25);
        nazwisko.setBounds(565, 190, 170, 25);

        emailLabel.setBounds(465, 230, 80, 25);
        email.setBounds(565, 230, 170, 25);

        loginLabel.setBounds(465, 270, 80, 25);
        login.setBounds(565, 270, 170, 25);

        haslo1Label.setBounds(465, 310, 80, 25);
        haslo1.setBounds(565, 310, 170, 25);

        haslo2Label.setBounds(465, 350, 90, 25);
        haslo2.setBounds(565, 350, 170, 25);

        buttonRejestracja.setBounds(465, 390 ,270, 30);
        buttonRejestracja.setFocusable(false);
        buttonRejestracja.addActionListener(e -> infoLabel.setText(actionRejestracjaPracownik(imie.getText(), nazwisko.getText(), email.getText(), login.getText(), String.valueOf(haslo1.getPassword()), String.valueOf(haslo2.getPassword()))));

        infoLabel.setBounds(465, 430, 270, 30);

        powrotButton.setBounds(465, 600, 270, 30);
        powrotButton.setFocusable(false);
        powrotButton.addActionListener(e -> {startPanel.setVisible(true); zarejestrujPracownikPanel.setVisible(false);});

        zarejestrujPracownikPanel.add(header);
        zarejestrujPracownikPanel.add(imieLabel);
        zarejestrujPracownikPanel.add(imie);
        zarejestrujPracownikPanel.add(nazwiskoLabel);
        zarejestrujPracownikPanel.add(nazwisko);
        zarejestrujPracownikPanel.add(emailLabel);
        zarejestrujPracownikPanel.add(email);
        zarejestrujPracownikPanel.add(loginLabel);
        zarejestrujPracownikPanel.add(login);
        zarejestrujPracownikPanel.add(haslo1Label);
        zarejestrujPracownikPanel.add(haslo1);
        zarejestrujPracownikPanel.add(haslo2Label);
        zarejestrujPracownikPanel.add(haslo2);
        zarejestrujPracownikPanel.add(buttonRejestracja);
        zarejestrujPracownikPanel.add(infoLabel);
        zarejestrujPracownikPanel.add(powrotButton);

        zarejestrujPracownikPanel.setVisible(false);
    }

    private static void katalogKsiazekPanelInit(){
        katalogKsiazekPanel = new JPanel();
        katalogKsiazekPanel.setLayout(null);
        katalogKsiazekPanel.setBounds(0, 0, 1200, 700);

        JLabel header = new JLabel("Katalog ksiażek",SwingConstants.CENTER);
        header.setBounds(0,20,1200,50);
        header.setFont(new Font("TimesRoman",Font.BOLD,40));

        JButton powrotButton = new JButton("Powrót do startu");
        powrotButton.setBounds(465, 500, 270, 30);
        powrotButton.setFocusable(false);
        powrotButton.addActionListener(e -> {startPanel.setVisible(true); katalogKsiazekPanel.setVisible(false);});

        katalogKsiazekPanel.add(header);
        katalogKsiazekPanel.add(powrotButton);

        katalogKsiazekPanel.setVisible(false);
    }

    // --------------------------------------------------------------------------------
    // Funkcje dla przycisków

    private static String actionZaloguj(boolean kto, String user, String password){
        // kto: false - czytelnik, true pracownik 
        String toReturn = "Logowanie nie powiodło się!";
        if(db.logowanie(kto, user, password)){
            // bladLogowaniaCztyelnik.setText("Logowanie powiodło się!");
            toReturn = "Logowanie powiodło się!";
            if(kto)
                zalogowany_pracownik = true;
            else
                zalogowany_czytelnik = true;
        }
        return toReturn;
    }

    private static String actionRejestracjaCzytelnik(String imie, String nazwisko, String email, String telefon, String login, String haslo1, String haslo2){
        // imieRejestracja.getText(), nazwiskoRejestracja.getText(), emailRejestracja.getText(), telefonRejestracja.getText(), loginRejestracja.getText(), String.valueOf(haslo1Rejestracja.getPassword()), String.valueOf(haslo2Rejestracja.getPassword());
        String toReturn;
        String blad = "";

        boolean valid = true;

        Pattern patternEmail = Pattern.compile("^.+@.+\\..+$");
        Matcher matcherEmail = patternEmail.matcher(email);
        Pattern patternTelefon = Pattern.compile("^\\d{9}$");
        Matcher matcherTelefon = patternTelefon.matcher(telefon);
        
        if(imie.equals("") || nazwisko.equals("") || email.equals("") || login.equals("") || haslo1.equals("") || haslo2.equals("")){
            valid = false;
            blad += "Wypełnij wszystkie wymagane pola. ";
        }
        else if(!haslo1.equals(haslo2)){
            valid = false;
            blad += "Różne hasła. ";
        }
        else if(!matcherEmail.matches()){
            valid = false;
            blad += "Błędny adres email. ";
        }
        else if(!matcherTelefon.matches() && !telefon.equals("")){
            valid = false;
            blad += "Błędny numer telefonu. ";
        }

        if(valid){
            int tel = 0;
            if(!telefon.equals(""))
                tel = Integer.valueOf(telefon);
            if(db.rejestracjaCzytelnik(imie, nazwisko, email, tel, login, haslo1)){
                toReturn = "Rejstracja powiodła się.";
            }
            else{
                toReturn = "Podany login lub email zajęty." ;
            }
        }
        else{
            toReturn = blad;
        }
        return toReturn;
    }

    private static String actionRejestracjaPracownik(String imie, String nazwisko, String email, String login, String haslo1, String haslo2){
        // imieRejestracja.getText(), nazwiskoRejestracja.getText(), emailRejestracja.getText(), telefonRejestracja.getText(), loginRejestracja.getText(), String.valueOf(haslo1Rejestracja.getPassword()), String.valueOf(haslo2Rejestracja.getPassword());
        String toReturn;
        String blad = "";

        boolean valid = true;

        Pattern patternEmail = Pattern.compile("^.+@.+\\..+$");
        Matcher matcherEmail = patternEmail.matcher(email);
        
        if(imie.equals("") || nazwisko.equals("") || email.equals("") || login.equals("") || haslo1.equals("") || haslo2.equals("")){
            valid = false;
            blad += "Wypełnij wszystkie wymagane pola. ";
        }
        else if(!haslo1.equals(haslo2)){
            valid = false;
            blad += "Różne hasła. ";
        }
        else if(!matcherEmail.matches()){
            valid = false;
            blad += "Błędny adres email. ";
        }

        if(valid){
            if(db.rejestracjaPracownik(imie, nazwisko, email, login, haslo1)){
                toReturn = "Rejstracja powiodła się.";
            }
            else{
                toReturn = "Podany login lub email zajęty." ;
            }
        }
        else{
            toReturn = blad;
        }
        return toReturn;
    }

    public static void main(String[] args){
        new MyFrame();
    }
}
