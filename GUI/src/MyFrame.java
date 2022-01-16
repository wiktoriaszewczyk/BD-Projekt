import javax.swing.*;

import java.awt.Color;
import java.awt.Font;

public class MyFrame extends JFrame{

    private static DB db = new DB(); 

    private static boolean zalogowany_czytelnik = false;
    private static boolean zalogowany_pracownik = false;


    private static JPanel startPanel;
    private static JTextField userCzytelnik;
    private static JPasswordField passwordCzytelnik;
    private static JLabel bladLogowaniaCztyelnik;

    private static JPanel loginPracownikPanel;
    private static JTextField userPracownik;
    private static JPasswordField passwordPracownik;
    private static JLabel bladLogowaniaPracownik;

    private static JPanel zarejestrujCzytelnikPanel;
    private static JTextField imieRejestracja;
    private static JTextField nazwiskoRejestracja;
    private static JTextField emailRejestracja;
    private static JTextField telefonRejestracja;
    private static JTextField loginRejestracja;
    private static JPasswordField haslo1Rejestracja;
    private static JPasswordField haslo2Rejestracja;
    private static JLabel rejestracjaInfo; 

    private static JPanel katalogKsiazekPanel;

    MyFrame(){
        this.setTitle("Biblioteka - Projekt BD Wiktoria Szewczyk");
        this.setSize(1200,700);
        this.setLocationRelativeTo(null);  // center frame
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);

        startPanelInit();
        loginPracownikPanelInit();
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
        header.setBounds(0,20,1200,50);
        // header.setBorder(BorderFactory.createLineBorder(Color.black));
        header.setFont(new Font("TimesRoman",Font.BOLD,40));

        JLabel userCzytelnikLabel = new JLabel("Login: ");
        userCzytelnikLabel.setBounds(465, 200, 80, 25);
        userCzytelnik = new JTextField();
        userCzytelnik.setBounds(565, 200, 170, 25);

        JLabel passwordCzytelnikLabel = new JLabel("Hasło: ");
        passwordCzytelnikLabel.setBounds(465, 240, 80, 25);
        passwordCzytelnik = new JPasswordField();
        passwordCzytelnik.setBounds(565, 240, 170, 25);

        JButton buttonLoginCzytelnik = new JButton("Zaloguj się");
        buttonLoginCzytelnik.setBounds(465, 280 ,130, 30);
        buttonLoginCzytelnik.setFocusable(false);
        buttonLoginCzytelnik.addActionListener(e -> actionZalogujCzytelnik());

        JButton buttonZarejestrujCzytelnik = new JButton("Zarejestruj się");
        buttonZarejestrujCzytelnik.setBounds(605, 280, 130, 30);
        buttonZarejestrujCzytelnik.setFocusable(false);
        buttonZarejestrujCzytelnik.addActionListener(e -> {startPanel.setVisible(false); zarejestrujCzytelnikPanel.setVisible(true);});

        bladLogowaniaCztyelnik = new JLabel("");
        bladLogowaniaCztyelnik.setBounds(465, 320, 270, 30);

        JButton ksiegozbiorButton = new JButton("Katalog ksiażek");
        ksiegozbiorButton.setBounds(465, 450, 270, 30);
        ksiegozbiorButton.setFocusable(false);
        ksiegozbiorButton.addActionListener(e -> {startPanel.setVisible(false); katalogKsiazekPanel.setVisible(true);});

        JButton buttonPracownik = new JButton("Dla pracowników");
        buttonPracownik.setBounds(465, 500, 270, 30);
        buttonPracownik.setFocusable(false);
        buttonPracownik.addActionListener(e -> {startPanel.setVisible(false); loginPracownikPanel.setVisible(true);});

        startPanel.add(header);
        startPanel.add(userCzytelnikLabel);
        startPanel.add(userCzytelnik);
        startPanel.add(passwordCzytelnikLabel);
        startPanel.add(passwordCzytelnik);
        startPanel.add(buttonLoginCzytelnik);
        startPanel.add(buttonZarejestrujCzytelnik);
        startPanel.add(bladLogowaniaCztyelnik);
        startPanel.add(ksiegozbiorButton);
        startPanel.add(buttonPracownik);
    }

    private static void loginPracownikPanelInit(){
        loginPracownikPanel = new JPanel();
        loginPracownikPanel.setLayout(null);
        loginPracownikPanel.setBounds(0, 0, 1200, 700);

        JLabel header = new JLabel("Zaloguj się jako pracownik",SwingConstants.CENTER);
        header.setBounds(0,20,1200,50);
        header.setFont(new Font("TimesRoman",Font.BOLD,40));

        JLabel userPracownikLabel = new JLabel("Login: ");
        userPracownikLabel.setBounds(465, 200, 80, 25);
        userPracownik = new JTextField();
        userPracownik.setBounds(565, 200, 170, 25);

        JLabel passwordPracownikLabel = new JLabel("Hasło: ");
        passwordPracownikLabel.setBounds(465, 240, 80, 25);
        passwordPracownik = new JPasswordField();
        passwordPracownik.setBounds(565, 240, 170, 25);

        JButton buttonLoginPracownik = new JButton("Zaloguj się");
        buttonLoginPracownik.setBounds(465, 280 ,270, 30);
        buttonLoginPracownik.setFocusable(false);
        buttonLoginPracownik.addActionListener(e -> actionZalogujPracownik());

        bladLogowaniaPracownik = new JLabel("");
        bladLogowaniaPracownik.setBounds(465, 320, 270, 30);

        JButton powrotButton = new JButton("Powrót do startu");
        powrotButton.setBounds(465, 500, 270, 30);
        powrotButton.setFocusable(false);
        powrotButton.addActionListener(e -> {startPanel.setVisible(true); loginPracownikPanel.setVisible(false);});

        loginPracownikPanel.add(header);
        loginPracownikPanel.add(userPracownikLabel);
        loginPracownikPanel.add(userPracownik);
        loginPracownikPanel.add(passwordPracownikLabel);
        loginPracownikPanel.add(passwordPracownik);
        loginPracownikPanel.add(buttonLoginPracownik);
        loginPracownikPanel.add(bladLogowaniaPracownik);
        loginPracownikPanel.add(powrotButton);

        loginPracownikPanel.setVisible(false);
    }

    private static void zarejestrujCzytelnikPanelInit(){
        zarejestrujCzytelnikPanel = new JPanel();
        zarejestrujCzytelnikPanel.setLayout(null);
        zarejestrujCzytelnikPanel.setBounds(0, 0, 1200, 700);

        JLabel header = new JLabel("Zarejestruj się",SwingConstants.CENTER);
        header.setBounds(0,20,1200,50);
        header.setFont(new Font("TimesRoman",Font.BOLD,40));

        JLabel imieRejestracjaLabel = new JLabel("Imie: ");
        imieRejestracjaLabel.setBounds(465, 150, 80, 25);
        imieRejestracja = new JTextField();
        imieRejestracja.setBounds(565, 150, 170, 25);

        JLabel nazwiskoRejestracjaLabel = new JLabel("Nazwisko: ");
        nazwiskoRejestracjaLabel.setBounds(465, 190, 80, 25);
        nazwiskoRejestracja = new JPasswordField();
        nazwiskoRejestracja.setBounds(565, 190, 170, 25);

        JLabel emailRejestracjaLabel = new JLabel("Email: ");
        emailRejestracjaLabel.setBounds(465, 230, 80, 25);
        emailRejestracja = new JPasswordField();
        emailRejestracja.setBounds(565, 230, 170, 25);

        JLabel telefonRejestracjaLabel = new JLabel("Telefon: ");
        telefonRejestracjaLabel.setBounds(465, 270, 80, 25);
        telefonRejestracja = new JPasswordField();
        telefonRejestracja.setBounds(565, 270, 170, 25);

        JLabel loginRejestracjaLabel = new JLabel("Login: ");
        loginRejestracjaLabel.setBounds(465, 310, 80, 25);
        loginRejestracja = new JPasswordField();
        loginRejestracja.setBounds(565, 310, 170, 25);

        JLabel haslo1RejestracjaLabel = new JLabel("Hasło: ");
        haslo1RejestracjaLabel.setBounds(465, 350, 80, 25);
        haslo1Rejestracja = new JPasswordField();
        haslo1Rejestracja.setBounds(565, 350, 170, 25);

        JLabel haslo2RejestracjaLabel = new JLabel("Powtórz hasło: ");
        haslo2RejestracjaLabel.setBounds(465, 390, 90, 25);
        haslo2Rejestracja = new JPasswordField();
        haslo2Rejestracja.setBounds(565, 390, 170, 25);

        JButton buttonRejestracja = new JButton("Zarejestruj się");
        buttonRejestracja.setBounds(465, 430 ,270, 30);
        buttonRejestracja.setFocusable(false);
        buttonRejestracja.addActionListener(e -> actionRejestracjaCzytelnik());

        rejestracjaInfo = new JLabel("");
        rejestracjaInfo.setBounds(465, 470, 270, 30);

        JButton powrotButton = new JButton("Powrót do startu");
        powrotButton.setBounds(465, 600, 270, 30);
        powrotButton.setFocusable(false);
        powrotButton.addActionListener(e -> {startPanel.setVisible(true); zarejestrujCzytelnikPanel.setVisible(false);});

        zarejestrujCzytelnikPanel.add(header);
        zarejestrujCzytelnikPanel.add(imieRejestracjaLabel);
        zarejestrujCzytelnikPanel.add(imieRejestracja);
        zarejestrujCzytelnikPanel.add(nazwiskoRejestracjaLabel);
        zarejestrujCzytelnikPanel.add(nazwiskoRejestracja);
        zarejestrujCzytelnikPanel.add(emailRejestracjaLabel);
        zarejestrujCzytelnikPanel.add(emailRejestracja);
        zarejestrujCzytelnikPanel.add(telefonRejestracjaLabel);
        zarejestrujCzytelnikPanel.add(telefonRejestracja);
        zarejestrujCzytelnikPanel.add(loginRejestracjaLabel);
        zarejestrujCzytelnikPanel.add(loginRejestracja);
        zarejestrujCzytelnikPanel.add(haslo1RejestracjaLabel);
        zarejestrujCzytelnikPanel.add(haslo1Rejestracja);
        zarejestrujCzytelnikPanel.add(haslo2RejestracjaLabel);
        zarejestrujCzytelnikPanel.add(haslo2Rejestracja);
        zarejestrujCzytelnikPanel.add(buttonRejestracja);
        zarejestrujCzytelnikPanel.add(rejestracjaInfo);
        zarejestrujCzytelnikPanel.add(powrotButton);

        zarejestrujCzytelnikPanel.setVisible(false);
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

    private static void actionZalogujCzytelnik(){
        String user = userCzytelnik.getText();
        String password = passwordCzytelnik.getText();
        if(db.logowanie(false, user, password)){
            bladLogowaniaCztyelnik.setText("Logowanie powiodło się!");
            zalogowany_czytelnik = true;
        }
        else{
            bladLogowaniaCztyelnik.setText("Logowanie nie powiodło się!");
        }
    }
    private static void actionZalogujPracownik(){
        String user = userPracownik.getText();
        String password = passwordPracownik.getText();
        if(db.logowanie(true, user, password)){
            bladLogowaniaPracownik.setText("Logowanie powiodło się!");
            zalogowany_pracownik = true;
        }
        else{
            bladLogowaniaPracownik.setText("Logowanie nie powiodło się!");
        }
    }

    private static void actionRejestracjaCzytelnik(){
        String imie = imieRejestracja.getText();
        String nazwisko = nazwiskoRejestracja.getText();
        String email = emailRejestracja.getText();
        String telefon = telefonRejestracja.getText();
        String login = loginRejestracja.getText();
        String haslo1 = haslo1Rejestracja.getText();
        String haslo2 = haslo2Rejestracja.getText();
        String blad = "";

        boolean valid = true;

        if(haslo1.equals(haslo2)){
            valid = false;
            blad += "Różne hasła. ";
        }

        if(valid){
            if(db.rejestracjaCzytelnik(imie, nazwisko, email, telefon, login, haslo1)){
                rejestracjaInfo.setText("git");
            }
            else{
                rejestracjaInfo.setText("nie git");
            }
        }
        else{
            rejestracjaInfo.setText(blad);

        }

    }

    public static void main(String[] args){
        new MyFrame();
    }
}
