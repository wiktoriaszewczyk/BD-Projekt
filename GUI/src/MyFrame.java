import javax.naming.AuthenticationNotSupportedException;
import javax.swing.*;
import java.awt.Font;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MyFrame extends JFrame{

    private static DB db = new DB(); 

    // private static int zalogowany_czytelnik = 0;
    private static Czytelnik zalogowanyCzytelnik = new Czytelnik();
    private static boolean zalogowany_pracownik = false;

    private static JPanel startPanel;
    private static JPanel loginPracownikPanel;
    private static MenuPracownikaPanel menuPracownikaPanel;
    private static MenuPracownikaPanel pracownikPanel;  // dodawanie usuwanie pracownika
    private static MenuPracownikaPanel autorPanel;  // dodawanie usuwanie autora
    private static MenuPracownikaPanel wydawnictwoPanel;
    private static MenuPracownikaPanel dziedzinaPanel;
    private static MenuPracownikaPanel ksiazkaPanel;
    private static MenuPracownikaPanel egzemplarzPanel;

    private static JPanel zarejestrujCzytelnikPanel;
    private static MenuCzytelnikaPanel menuCzytelnikaPanel;
    private static MenuCzytelnikaPanel wypozyczonePanel;
    private static MenuCzytelnikaPanel egzemplarzInfoWypozyczonePanel;
    private static MenuCzytelnikaPanel historiaPanel;
    private static MenuCzytelnikaPanel zarezerwowanePanel;
    private static MenuCzytelnikaPanel ksiazkaInfoZarezerwowanaPanel;
    private static MenuCzytelnikaPanel katalogPanel;    // dla czytelnika
    private static MenuCzytelnikaPanel ksiazkaInfoKatalogPanel;

    private static JPanel katalogKsiazekPanel;  // nie dla czytelnika
    private static KsiazkaPanel ksiazkaInfoPanel;

    MyFrame(){
        this.setTitle("Biblioteka - Projekt BD Wiktoria Szewczyk");
        this.setSize(1200,700);
        this.setLocationRelativeTo(null);  // center frame
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);

        startPanelInit();

        loginPracownikPanelInit();
        zarejestrujCzytelnikPanelInit();

        menuPracownikaPanelInit();
        pracownikPanelInit();
        autorPanelInit();
        wydawnictwoPanelInit();
        dziedzinaPanelInit();
        ksiazkaPanelInit();
        egzemplarzPanelInit();

        menuCzytelnikaPanel = new MenuCzytelnikaPanel();    // init po poprawnym zalogowaniu
        wypozyczonePanel = new MenuCzytelnikaPanel();
        egzemplarzInfoWypozyczonePanel = new MenuCzytelnikaPanel();
        zarezerwowanePanel = new MenuCzytelnikaPanel();
        ksiazkaInfoZarezerwowanaPanel = new MenuCzytelnikaPanel();
        historiaPanel = new MenuCzytelnikaPanel();
        katalogPanel = new MenuCzytelnikaPanel();
        ksiazkaInfoKatalogPanel = new MenuCzytelnikaPanel();

        katalogKsiazekPanelInit();
        ksiazkaInfoPanelInit();

        this.add(startPanel);
        this.add(zarejestrujCzytelnikPanel);
        this.add(loginPracownikPanel);

        this.add(menuPracownikaPanel);
        this.add(pracownikPanel);
        this.add(autorPanel);
        this.add(wydawnictwoPanel);
        this.add(dziedzinaPanel);
        this.add(ksiazkaPanel);
        this.add(egzemplarzPanel);

        this.add(menuCzytelnikaPanel);
        this.add(wypozyczonePanel);
        this.add(egzemplarzInfoWypozyczonePanel);
        this.add(zarezerwowanePanel);
        this.add(ksiazkaInfoZarezerwowanaPanel);
        this.add(historiaPanel);
        this.add(katalogPanel);
        this.add(ksiazkaInfoKatalogPanel);

        this.add(katalogKsiazekPanel);
        this.add(ksiazkaInfoPanel);

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

        // JButton test = new JButton("test");
        // test.setBounds(0,0,100,30);
        // test.addActionListener(e -> db.infoKsiazki());

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
        
        // startPanel.add(test);
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

    private static void menuPracownikaButtons(MenuPracownikaPanel panel){
        panel.getWylogujButton().addActionListener(e -> {
            zalogowany_pracownik = false; 
            panel.setVisible(false);
            startPanel.setVisible(true);
        });

        panel.getPracownikButton().addActionListener(e -> {
            panel.setVisible(false);
            pracownikPanel.setVisible(true);
        });

        panel.getAutorButton().addActionListener(e ->{
            panel.setVisible(false);
            autorPanel.setVisible(true);
        });

        panel.getWydawnictwoButton().addActionListener(e ->{
            panel.setVisible(false);
            wydawnictwoPanel.setVisible(true);
        });

        panel.getDziedzinaButton().addActionListener(e ->{
            panel.setVisible(false);
            dziedzinaPanel.setVisible(true);
        });

        panel.getKsiazkaButton().addActionListener(e ->{
            panel.setVisible(false);
            ksiazkaPanel.setVisible(true);
        });

        panel.getEgzemplarzButton().addActionListener(e ->{
            panel.setVisible(false);
            egzemplarzPanel.setVisible(true);
        });
    }

    private static void menuPracownikaPanelInit(){
        menuPracownikaPanel = new MenuPracownikaPanel();
        menuPracownikaButtons(menuPracownikaPanel);

    }

    private static void pracownikPanelInit(){
        pracownikPanel = new MenuPracownikaPanel();
        pracownikPanel.setLayout(null);
        pracownikPanel.setBounds(0, 0, 1200, 700);

        menuPracownikaButtons(pracownikPanel);
        pracownikPanel.getHeader().setText("Dodaj Pracownika");

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

        pracownikPanel.add(imieLabel);
        pracownikPanel.add(imie);
        pracownikPanel.add(nazwiskoLabel);
        pracownikPanel.add(nazwisko);
        pracownikPanel.add(emailLabel);
        pracownikPanel.add(email);
        pracownikPanel.add(loginLabel);
        pracownikPanel.add(login);
        pracownikPanel.add(haslo1Label);
        pracownikPanel.add(haslo1);
        pracownikPanel.add(haslo2Label);
        pracownikPanel.add(haslo2);
        pracownikPanel.add(buttonRejestracja);
        pracownikPanel.add(infoLabel);

        pracownikPanel.setVisible(false);
    }

    private static void autorPanelInit(){
        autorPanel = new MenuPracownikaPanel();
        autorPanel.setLayout(null);
        autorPanel.setBounds(0, 0, 1200, 700);

        menuPracownikaButtons(autorPanel);

        autorPanel.getHeader().setText("Autorzy");

        JLabel dodajAutoraLabel = new JLabel("Dodaj autora",SwingConstants.CENTER);
        JLabel imieLabel = new JLabel("Imie* ");
        JTextField imie = new JTextField();
        JLabel nazwiskoLabel = new JLabel("Nazwisko* ");
        JTextField nazwisko = new JTextField();
        JButton buttonDodaj = new JButton("Dodaj");
        JLabel infoDodajLabel = new JLabel("* pole wymagane");

        JLabel zmienAutoraLabel = new JLabel("Aktualizuj autora",SwingConstants.CENTER);
        JLabel imieLabel2 = new JLabel("Imie* ");
        JTextField imie2 = new JTextField();
        JLabel nazwiskoLabel2 = new JLabel("Nazwisko* ");
        JTextField nazwisko2 = new JTextField();
        JButton buttonZmien = new JButton("Aktualizuj");
        JLabel infoZmienLabel = new JLabel("Najpierw wybierz autora z listy.");

        JLabel usunAutoraLabel = new JLabel("Usuń autora",SwingConstants.CENTER);
        JTextField szukaj = new JTextField();
        JButton buttonSzukaj = new JButton("Szukaj");
        DefaultListModel<Autor> autorzy = new DefaultListModel<>();
        db.infoAutor(autorzy);
        JList<Autor> listaAutor = new JList<>(autorzy); 
        JScrollPane scrollPane = new JScrollPane();

        JButton buttonUsun = new JButton("Usuń");
        JLabel infoUsunLabel = new JLabel("");

        dodajAutoraLabel.setFont(new Font("TimesRoman",Font.BOLD,20));
        dodajAutoraLabel.setBounds(300, 150, 270, 25);
        imieLabel.setBounds(300, 200, 80, 25);
        imie.setBounds(400, 200, 170, 25);

        nazwiskoLabel.setBounds(300, 240, 80, 25);
        nazwisko.setBounds(400, 240, 170, 25);

        buttonDodaj.setBounds(300, 290 ,270, 30);
        buttonDodaj.setFocusable(false);
        buttonDodaj.addActionListener(e -> {
            infoDodajLabel.setText(actionDodajAutor(imie.getText(), nazwisko.getText())); 
            db.infoAutor(autorzy);
        });

        infoDodajLabel.setBounds(300, 330, 270, 30);

        zmienAutoraLabel.setFont(new Font("TimesRoman",Font.BOLD,20));
        zmienAutoraLabel.setBounds(300, 380, 270, 25);
        imieLabel2.setBounds(300, 430, 80, 25);
        imie2.setBounds(400, 430, 170, 25);

        nazwiskoLabel2.setBounds(300, 470, 80, 25);
        nazwisko2.setBounds(400, 470, 170, 25);

        buttonZmien.setBounds(300, 520 ,270, 30);
        buttonZmien.setFocusable(false);
        buttonZmien.addActionListener(e -> {
            infoZmienLabel.setText(actionZmienAutor(listaAutor.getSelectedValue(), imie2.getText(), nazwisko2.getText())); 
            db.infoAutor(autorzy);
        });

        infoZmienLabel.setBounds(300, 560, 270, 30);

        usunAutoraLabel.setFont(new Font("TimesRoman",Font.BOLD,20));
        usunAutoraLabel.setBounds(700, 150, 270, 25);

        szukaj.setBounds(700,200,170,25);
        buttonSzukaj.setBounds(890,200,80,25);
        buttonSzukaj.addActionListener(e -> {db.infoAutor(autorzy,szukaj.getText());});

        listaAutor.setFixedCellHeight(40);
        listaAutor.addListSelectionListener(e -> {
            if(listaAutor.getSelectedValue() != null){
            imie2.setText(listaAutor.getSelectedValue().getImie());
            nazwisko2.setText(listaAutor.getSelectedValue().getNazwisko());
            }
        });
        listaAutor.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        scrollPane.setViewportView(listaAutor);
        scrollPane.setBounds(700,240,270,250);

        buttonUsun.setBounds(700, 510 ,270, 30);
        buttonUsun.setFocusable(false);
        buttonUsun.addActionListener(e -> {
            infoUsunLabel.setText(actionUsunAutor(listaAutor.getSelectedValue()));
            db.infoAutor(autorzy);
        });

        infoUsunLabel.setBounds(700, 550, 270, 50);


        autorPanel.add(dodajAutoraLabel);
        autorPanel.add(imieLabel);
        autorPanel.add(imie);
        autorPanel.add(nazwiskoLabel);
        autorPanel.add(nazwisko);
        autorPanel.add(buttonDodaj);
        autorPanel.add(infoDodajLabel);
        
        autorPanel.add(zmienAutoraLabel);
        autorPanel.add(imieLabel2);
        autorPanel.add(imie2);
        autorPanel.add(nazwiskoLabel2);
        autorPanel.add(nazwisko2);
        autorPanel.add(buttonZmien);
        autorPanel.add(infoZmienLabel);

        autorPanel.add(usunAutoraLabel);
        autorPanel.add(szukaj);
        autorPanel.add(buttonSzukaj);
        autorPanel.add(scrollPane);
        autorPanel.add(buttonUsun);
        autorPanel.add(infoUsunLabel);

        autorPanel.setVisible(false);
    }

    private static void wydawnictwoPanelInit(){
        wydawnictwoPanel = new MenuPracownikaPanel();
        wydawnictwoPanel.setLayout(null);
        wydawnictwoPanel.setBounds(0, 0, 1200, 700);

        menuPracownikaButtons(wydawnictwoPanel);

        wydawnictwoPanel.getHeader().setText("Wydawnictwa");

        JLabel dodajWydawnictwoLabel = new JLabel("Dodaj wydawnictwo",SwingConstants.CENTER);
        JLabel nazwaLabel = new JLabel("Nazwa* ");
        JTextField nazwa = new JTextField();
        JButton buttonDodaj = new JButton("Dodaj");
        JLabel infoDodajLabel = new JLabel("* pole wymagane");

        JLabel zmienWydawnictwoLabel = new JLabel("Aktualizuj wydawnictwo",SwingConstants.CENTER);
        JLabel nazwaLabel2 = new JLabel("Nazwa* ");
        JTextField nazwa2 = new JTextField();
        JButton buttonZmien = new JButton("Aktualizuj");
        JLabel infoZmienLabel = new JLabel("Najpierw wybierz wydawnictwo z listy.");

        JLabel usunWydawnictwoLabel = new JLabel("Usuń wydawnictwo",SwingConstants.CENTER);
        JTextField szukaj = new JTextField();
        JButton buttonSzukaj = new JButton("Szukaj");
        DefaultListModel<Wydawnictwo> wydawnictwa = new DefaultListModel<>();
        db.infoWydawnictwo(wydawnictwa);
        JList<Wydawnictwo> listaWydawnictwo = new JList<>(wydawnictwa); 
        JScrollPane scrollPane = new JScrollPane();

        JButton buttonUsun = new JButton("Usuń");
        JLabel infoUsunLabel = new JLabel("");

        dodajWydawnictwoLabel.setFont(new Font("TimesRoman",Font.BOLD,20));
        dodajWydawnictwoLabel.setBounds(300, 150, 270, 25);

        nazwaLabel.setBounds(300, 200, 80, 25);
        nazwa.setBounds(400, 200, 170, 25);

        buttonDodaj.setBounds(300, 250 ,270, 30);
        buttonDodaj.setFocusable(false);
        buttonDodaj.addActionListener(e -> {
            infoDodajLabel.setText(actionDodajWydawnictwo(nazwa.getText())); 
            db.infoWydawnictwo(wydawnictwa);
        });

        infoDodajLabel.setBounds(300, 290, 270, 30);

        zmienWydawnictwoLabel.setFont(new Font("TimesRoman",Font.BOLD,20));
        zmienWydawnictwoLabel.setBounds(300, 380, 270, 25);
        nazwaLabel2.setBounds(300, 430, 80, 25);
        nazwa2.setBounds(400, 430, 170, 25);

        buttonZmien.setBounds(300, 480 ,270, 30);
        buttonZmien.setFocusable(false);
        buttonZmien.addActionListener(e -> {
            infoZmienLabel.setText(actionZmienWydawnictwo(listaWydawnictwo.getSelectedValue(), nazwa2.getText())); 
            db.infoWydawnictwo(wydawnictwa);
        });

        infoZmienLabel.setBounds(300, 520, 270, 30);
        
        usunWydawnictwoLabel.setFont(new Font("TimesRoman",Font.BOLD,20));
        usunWydawnictwoLabel.setBounds(700, 150, 270, 25);

        szukaj.setBounds(700,200,170,25);
        buttonSzukaj.setBounds(890,200,80,25);
        buttonSzukaj.addActionListener(e -> {db.infoWydawnictwo(wydawnictwa,szukaj.getText());});

        listaWydawnictwo.setFixedCellHeight(40);
        listaWydawnictwo.addListSelectionListener(e -> {
            if(listaWydawnictwo.getSelectedValue() != null){
                nazwa2.setText(listaWydawnictwo.getSelectedValue().getNazwa());
            }
        });
        listaWydawnictwo.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        scrollPane.setViewportView(listaWydawnictwo);
        scrollPane.setBounds(700,240,270,250);

        buttonUsun.setBounds(700, 510 ,270, 30);
        buttonUsun.setFocusable(false);
        buttonUsun.addActionListener(e -> {
            infoUsunLabel.setText(actionUsunWydawnictwo(listaWydawnictwo.getSelectedValue()));
            db.infoWydawnictwo(wydawnictwa);
        });

        infoUsunLabel.setBounds(700, 550, 270, 50);


        wydawnictwoPanel.add(dodajWydawnictwoLabel);
        wydawnictwoPanel.add(nazwaLabel);
        wydawnictwoPanel.add(nazwa);
        wydawnictwoPanel.add(buttonDodaj);
        wydawnictwoPanel.add(infoDodajLabel);

        wydawnictwoPanel.add(zmienWydawnictwoLabel);
        wydawnictwoPanel.add(nazwaLabel2);
        wydawnictwoPanel.add(nazwa2);
        wydawnictwoPanel.add(buttonZmien);
        wydawnictwoPanel.add(infoZmienLabel);

        wydawnictwoPanel.add(usunWydawnictwoLabel);
        wydawnictwoPanel.add(szukaj);
        wydawnictwoPanel.add(buttonSzukaj);
        wydawnictwoPanel.add(scrollPane);
        wydawnictwoPanel.add(buttonUsun);
        wydawnictwoPanel.add(infoUsunLabel);

        wydawnictwoPanel.setVisible(false);


    }

    private static void dziedzinaPanelInit(){
        dziedzinaPanel = new MenuPracownikaPanel();
        dziedzinaPanel.setLayout(null);
        dziedzinaPanel.setBounds(0, 0, 1200, 700);

        menuPracownikaButtons(dziedzinaPanel);

        dziedzinaPanel.getHeader().setText("Dziedziny");

        JLabel dodajLabel = new JLabel("Dodaj dziedzinę",SwingConstants.CENTER);
        JLabel nazwaLabel = new JLabel("Nazwa* ");
        JTextField nazwa = new JTextField();
        JLabel nadNazwaLabel = new JLabel("<html>Dziedzina<br />nadrzędna <html>");
        Vector<Dziedzina> dziedziny = new Vector<>();
        JComboBox<Dziedzina> nadNazwa = new JComboBox<>(dziedziny);
        JButton buttonDodaj = new JButton("Dodaj");
        JLabel infoDodajLabel = new JLabel("* pole wymagane");

        JLabel zmienLabel = new JLabel("Aktualizuj dziedzinę",SwingConstants.CENTER);
        JLabel nazwaLabel2 = new JLabel("Nazwa* ");
        JTextField nazwa2 = new JTextField();
        JLabel nadNazwaLabel2 = new JLabel("<html>Dziedzina<br />nadrzędna <html>");
        JComboBox<Dziedzina> nadNazwa2 = new JComboBox<>(dziedziny);
        JButton buttonZmien = new JButton("Aktualizuj");
        JLabel infoZmienLabel = new JLabel("Najpierw wybierz autora z listy.");

        JLabel usunLabel = new JLabel("Usuń dziedzinę",SwingConstants.CENTER);
        JTextField szukaj = new JTextField();
        JButton buttonSzukaj = new JButton("Szukaj");
        DefaultListModel<Dziedzina> dziedziny2 = new DefaultListModel<>();
        db.infoDziedzina(dziedziny, dziedziny2);
        JList<Dziedzina> listaDziedzina = new JList<>(dziedziny2); 
        JScrollPane scrollPane = new JScrollPane();

        JButton buttonUsun = new JButton("Usuń");
        JLabel infoUsunLabel = new JLabel("");

        dodajLabel.setFont(new Font("TimesRoman",Font.BOLD,20));
        dodajLabel.setBounds(300, 150, 270, 25);
        nazwaLabel.setBounds(300, 200, 80, 25);
        nazwa.setBounds(400, 200, 170, 25);

        nadNazwaLabel.setBounds(300, 240, 80, 25);
        nadNazwa.setBounds(400, 240, 170, 25);

        buttonDodaj.setBounds(300, 290 ,270, 30);
        buttonDodaj.setFocusable(false);
        buttonDodaj.addActionListener(e -> {
            infoDodajLabel.setText(actionDodajDziedzine(nazwa.getText(), (Dziedzina)nadNazwa.getSelectedItem())); 
            db.infoDziedzina(dziedziny, dziedziny2);
        });

        infoDodajLabel.setBounds(300, 330, 270, 30);

        zmienLabel.setFont(new Font("TimesRoman",Font.BOLD,20));
        zmienLabel.setBounds(300, 380, 270, 25);
        nazwaLabel2.setBounds(300, 430, 80, 25);
        nazwa2.setBounds(400, 430, 170, 25);

        nadNazwaLabel2.setBounds(300, 470, 80, 25);
        nadNazwa2.setBounds(400, 470, 170, 25);

        buttonZmien.setBounds(300, 520 ,270, 30);
        buttonZmien.setFocusable(false);
        buttonZmien.addActionListener(e -> {
            infoZmienLabel.setText(actionZmienDziedzina(listaDziedzina.getSelectedValue(), nazwa2.getText(), (Dziedzina)nadNazwa2.getSelectedItem())); 
            db.infoDziedzina(dziedziny, dziedziny2);
        });

        infoZmienLabel.setBounds(300, 560, 270, 30);

        usunLabel.setFont(new Font("TimesRoman",Font.BOLD,20));
        usunLabel.setBounds(700, 150, 270, 25);

        szukaj.setBounds(700,200,170,25);
        buttonSzukaj.setBounds(890,200,80,25);
        buttonSzukaj.addActionListener(e -> {db.infoDziedzina(dziedziny, dziedziny2, szukaj.getText());});

        listaDziedzina.setFixedCellHeight(40);
        listaDziedzina.addListSelectionListener(e -> {
            if(listaDziedzina.getSelectedValue() != null){
                nazwa2.setText(listaDziedzina.getSelectedValue().getNazwa());
                nadNazwa2.setSelectedIndex(listaDziedzina.getSelectedValue().getIdNad());
            }
        });
        listaDziedzina.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        scrollPane.setViewportView(listaDziedzina);
        scrollPane.setBounds(700,240,300,250);

        buttonUsun.setBounds(700, 510 ,270, 30);
        buttonUsun.setFocusable(false);
        buttonUsun.addActionListener(e -> {
            infoUsunLabel.setText(actionUsunDziedzina(listaDziedzina.getSelectedValue()));
            db.infoDziedzina(dziedziny, dziedziny2);
        });

        infoUsunLabel.setBounds(700, 550, 270, 50);


        dziedzinaPanel.add(dodajLabel);
        dziedzinaPanel.add(nazwaLabel);
        dziedzinaPanel.add(nazwa);
        dziedzinaPanel.add(nadNazwaLabel);
        dziedzinaPanel.add(nadNazwa);
        dziedzinaPanel.add(buttonDodaj);
        dziedzinaPanel.add(infoDodajLabel);
        
        dziedzinaPanel.add(zmienLabel);
        dziedzinaPanel.add(nazwaLabel2);
        dziedzinaPanel.add(nazwa2);
        dziedzinaPanel.add(nadNazwaLabel2);
        dziedzinaPanel.add(nadNazwa2);
        dziedzinaPanel.add(buttonZmien);
        dziedzinaPanel.add(infoZmienLabel);

        dziedzinaPanel.add(usunLabel);
        dziedzinaPanel.add(szukaj);
        dziedzinaPanel.add(buttonSzukaj);
        dziedzinaPanel.add(scrollPane);
        dziedzinaPanel.add(buttonUsun);
        dziedzinaPanel.add(infoUsunLabel);

        dziedzinaPanel.setVisible(false);
    }

    private static void ksiazkaPanelInit(){
        ksiazkaPanel = new MenuPracownikaPanel();
        ksiazkaPanel.setLayout(null);
        ksiazkaPanel.setBounds(0, 0, 1200, 700);

        menuPracownikaButtons(ksiazkaPanel);

        ksiazkaPanel.getHeader().setText("Książki");


        JLabel usunLabel = new JLabel("Usuń ksiażkę",SwingConstants.CENTER);
        JTextField szukaj = new JTextField();
        JButton buttonSzukaj = new JButton("Szukaj");
        DefaultListModel<Ksiazka> ksiazki = new DefaultListModel<>();
        db.infoKsiazka(ksiazki);
        JList<Ksiazka> listaKsiazka = new JList<>(ksiazki); 
        JScrollPane scrollPane = new JScrollPane();

        JButton buttonUsun = new JButton("Usuń");
        JLabel infoUsunLabel = new JLabel("");

        usunLabel.setFont(new Font("TimesRoman",Font.BOLD,20));
        usunLabel.setBounds(700, 150, 270, 25);

        szukaj.setBounds(700,200,170,25);
        buttonSzukaj.setBounds(890,200,80,25);
        buttonSzukaj.addActionListener(e -> {db.infoKsiazka(ksiazki, szukaj.getText());});

        listaKsiazka.setFixedCellHeight(40);
        listaKsiazka.addListSelectionListener(e -> {
            if(listaKsiazka.getSelectedValue() != null){
                // nazwa2.setText(listaKsiazka.getSelectedValue().getNazwa());
                // nadNazwa2.setSelectedIndex(listaKsiazka.getSelectedValue().getIdNad());
            }
        });
        listaKsiazka.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        scrollPane.setViewportView(listaKsiazka);
        scrollPane.setBounds(700,240,300,250);

        buttonUsun.setBounds(700, 510 ,270, 30);
        buttonUsun.setFocusable(false);
        buttonUsun.addActionListener(e -> {
            infoUsunLabel.setText(actionUsunKsiazka(listaKsiazka.getSelectedValue()));
            db.infoKsiazka(ksiazki);
        });

        infoUsunLabel.setBounds(700, 550, 270, 50);

        ksiazkaPanel.add(usunLabel);
        ksiazkaPanel.add(szukaj);
        ksiazkaPanel.add(buttonSzukaj);
        ksiazkaPanel.add(scrollPane);
        ksiazkaPanel.add(buttonUsun);
        ksiazkaPanel.add(infoUsunLabel);

        ksiazkaPanel.setVisible(false);
    }

    private static void egzemplarzPanelInit(){
        egzemplarzPanel = new MenuPracownikaPanel();
        egzemplarzPanel.setLayout(null);
        egzemplarzPanel.setBounds(0, 0, 1200, 700);

        menuPracownikaButtons(egzemplarzPanel);

        egzemplarzPanel.getHeader().setText("Egzemplarze");


        // JLabel usunLabel = new JLabel("Usuń egzemplarz",SwingConstants.CENTER);
        // JTextField szukaj = new JTextField();
        // JButton buttonSzukaj = new JButton("Szukaj");
        DefaultListModel<Ksiazka> ksiazki = new DefaultListModel<>();
        db.infoKsiazka(ksiazki);
        JList<Ksiazka> listaKsiazka = new JList<>(ksiazki); 
        JScrollPane scrollPane = new JScrollPane();

        // JButton buttonUsun = new JButton("Usuń");
        // JLabel infoUsunLabel = new JLabel("");

        // usunLabel.setFont(new Font("TimesRoman",Font.BOLD,20));
        // usunLabel.setBounds(700, 150, 270, 25);

        // szukaj.setBounds(700,200,170,25);
        // buttonSzukaj.setBounds(890,200,80,25);
        // buttonSzukaj.addActionListener(e -> {db.infoKsiazka(ksiazki, szukaj.getText());});

        listaKsiazka.setFixedCellHeight(40);
        listaKsiazka.addListSelectionListener(e -> {
            if(listaKsiazka.getSelectedValue() != null){
                // nazwa2.setText(listaKsiazka.getSelectedValue().getNazwa());
                // nadNazwa2.setSelectedIndex(listaKsiazka.getSelectedValue().getIdNad());
            }
        });
        listaKsiazka.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        scrollPane.setViewportView(listaKsiazka);
        scrollPane.setBounds(700,240,300,250);

        // buttonUsun.setBounds(700, 510 ,270, 30);
        // buttonUsun.setFocusable(false);
        // buttonUsun.addActionListener(e -> {
        //     infoUsunLabel.setText(actionUsunKsiazka(listaKsiazka.getSelectedValue()));
        //     db.infoKsiazka(ksiazki);
        // });

        // infoUsunLabel.setBounds(700, 550, 270, 50);

        // egzemplarzPanel.add(usunLabel);
        // egzemplarzPanel.add(szukaj);
        // egzemplarzPanel.add(buttonSzukaj);
        egzemplarzPanel.add(scrollPane);
        // egzemplarzPanel.add(buttonUsun);
        // egzemplarzPanel.add(infoUsunLabel);

        egzemplarzPanel.setVisible(false);
    }


    private static void menuCzytelnikaButtons(MenuCzytelnikaPanel panel){
        panel.getWylogujButton().addActionListener(e -> {
            zalogowanyCzytelnik.setId(0); 
            panel.setVisible(false);
            startPanel.setVisible(true);
        });

        panel.getDaneButton().addActionListener(e -> {
            panel.setVisible(false);
            menuCzytelnikaPanel.setVisible(true);
        });

        panel.getWypozyczeniaButton().addActionListener(e -> {
            // wypozyczonePanelInit();
            panel.setVisible(false);
            wypozyczonePanel.setVisible(true);
        });

        panel.getRezerwacjeButton().addActionListener(e -> {
            // zarezerwowanePanelInit();
            panel.setVisible(false);
            zarezerwowanePanel.setVisible(true);

        });

        panel.getHistoriaButton().addActionListener(e -> {
            // historiaPanelInit();
            panel.setVisible(false);
            historiaPanel.setVisible(true);
        });

        panel.getKatalogButton().addActionListener(e -> {
            // katalogPanelInit();
            panel.setVisible(false);
            katalogPanel.setVisible(true);
        });

    }
    
    private static void menuCzytelnikaPanelInit(){
        menuCzytelnikaPanel.restart();
        menuCzytelnikaButtons(menuCzytelnikaPanel);

        JLabel imieLabel = new JLabel("Imie ");
        JTextField imie = new JTextField();
        JLabel nazwiskoLabel = new JLabel("Nazwisko ");
        JTextField nazwisko = new JTextField();
        JLabel emailLabel = new JLabel("Email ");
        JTextField email = new JTextField();
        JLabel telefonLabel = new JLabel("Telefon ");
        JTextField telefon= new JTextField();
        JLabel loginLabel = new JLabel("Login ");
        JLabel login= new JLabel();
        JLabel karaLabel = new JLabel("<html>Kara<br />do zapłaty: </html>");
        JLabel kara = new JLabel();
        // JLabel haslo2Label = new JLabel("Hasło ");
        // JPasswordField haslo2 = new JPasswordField();
        JButton buttonZaplac = new JButton("Opłać karę");
        
        // Vector<JTextField> pola = new Vector<JTextField>();
        // pola.add(imie);
        // pola.add(nazwisko);
        // pola.add()

        imieLabel.setBounds(465, 150, 80, 25);
        imie.setBounds(565, 150, 170, 25);
        imie.setText(zalogowanyCzytelnik.getImie());

        nazwiskoLabel.setBounds(465, 190, 80, 25);
        nazwisko.setBounds(565, 190, 170, 25);
        nazwisko.setText(zalogowanyCzytelnik.getNazwisko());
        
        emailLabel.setBounds(465, 230, 80, 25);
        email.setBounds(565, 230, 170, 25);
        email.setText(zalogowanyCzytelnik.getEmail());

        telefonLabel.setBounds(465, 270, 80, 25);
        telefon.setBounds(565, 270, 170, 25);
        String telefonString = "";
        if(zalogowanyCzytelnik.getTelefon() != 0)
            telefonString += zalogowanyCzytelnik.getTelefon();
        telefon.setText(telefonString);

        loginLabel.setBounds(465, 310, 80, 25);
        login.setBounds(565, 310, 170, 25);
        login.setText(zalogowanyCzytelnik.getLogin());

        karaLabel.setBounds(465, 350, 80, 30);
        kara.setBounds(565, 350, 35, 25);
        kara.setText("" + zalogowanyCzytelnik.getKara());

        // haslo2Label.setBounds(465, 390, 90, 25);
        // haslo2.setBounds(565, 390, 170, 25);

        buttonZaplac.setBounds(600, 350 ,135, 30);
        buttonZaplac.setFocusable(false);
        buttonZaplac.addActionListener(e -> {
            if(db.aktualizujKaraCzytelnik(zalogowanyCzytelnik))
                kara.setText(""+ zalogowanyCzytelnik.getKara());
        });

        // infoZaplacLabel.setBounds(465, 470, 270, 30);

        // buttonZaplac.setBounds(465, 430 ,270, 30);
        // buttonZaplac.setFocusable(false);
        // buttonZaplac.addActionListener(e -> infoZaplacLabel.setText(actionRejestracjaCzytelnik(imie.getText(), nazwisko.getText(), email.getText(), telefon.getText(), login.getText(), String.valueOf(haslo1.getPassword()), String.valueOf(haslo2.getPassword()))));

        // infoZaplacLabel.setBounds(465, 470, 270, 30);

        menuCzytelnikaPanel.add(imieLabel);
        menuCzytelnikaPanel.add(imie);
        menuCzytelnikaPanel.add(nazwiskoLabel);
        menuCzytelnikaPanel.add(nazwisko);
        menuCzytelnikaPanel.add(emailLabel);
        menuCzytelnikaPanel.add(email);
        menuCzytelnikaPanel.add(telefonLabel);
        menuCzytelnikaPanel.add(telefon);
        menuCzytelnikaPanel.add(loginLabel);
        menuCzytelnikaPanel.add(login);
        menuCzytelnikaPanel.add(karaLabel);
        menuCzytelnikaPanel.add(kara);
        // menuCzytelnikaPanel.add(haslo2Label);
        // menuCzytelnikaPanel.add(haslo2);
        menuCzytelnikaPanel.add(buttonZaplac);
        // menuCzytelnikaPanel.add(infoLabel);

        menuCzytelnikaPanel.setVisible(false);
    }

    private static void wypozyczonePanelInit(){
        wypozyczonePanel.restart();

        menuCzytelnikaButtons(wypozyczonePanel);

        wypozyczonePanel.getHeader().setText("Wypożyczone książki");

        JLabel info = new JLabel("Kliknij na książkę po więcej informacji.",SwingConstants.CENTER);
        DefaultListModel<Egzemplarz> ksiazki = new DefaultListModel<>();
        JList<Egzemplarz> listaKsiazek = new JList<>(ksiazki); 
        JScrollPane scrollPane = new JScrollPane();

        db.infoWypozyczone(ksiazki, zalogowanyCzytelnik.getId());    // pobranie danych
        
        if(ksiazki.size()==0)
            info.setText("Brak wypożyczonych książek.");

        info.setBounds(300,110,750,30);

        listaKsiazek.setFixedCellHeight(40);
        listaKsiazek.addListSelectionListener(e -> egzemplarzWyporzyczonyInfo(listaKsiazek.getSelectedValue(), wypozyczonePanel/*, listaKsiazek*/));
        listaKsiazek.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listaKsiazek.clearSelection();

        DefaultListCellRenderer renderer = (DefaultListCellRenderer)listaKsiazek.getCellRenderer();
        renderer.setHorizontalAlignment(JLabel.CENTER);
        
        scrollPane.setViewportView(listaKsiazek);
        scrollPane.setBounds(300,150,750,400);

        wypozyczonePanel.add(info);
        wypozyczonePanel.add(scrollPane);

        wypozyczonePanel.setVisible(false);
    }

    private static void zarezerwowanePanelInit(){
        zarezerwowanePanel.restart();

        menuCzytelnikaButtons(zarezerwowanePanel);

        zarezerwowanePanel.getHeader().setText("Zarezerwowane książki");

        JLabel info = new JLabel("Kliknij na książkę po więcej informacji.",SwingConstants.CENTER);
        DefaultListModel<Ksiazka> ksiazki = new DefaultListModel<>();
        JList<Ksiazka> listaKsiazek = new JList<>(ksiazki); 
        JScrollPane scrollPane = new JScrollPane();

        db.infoZarezerwowane(ksiazki, zalogowanyCzytelnik.getId());    // pobranie danych
        
        if(ksiazki.size()==0)
            info.setText("Brak wypożyczonych książek.");

        info.setBounds(300,110,750,30);

        listaKsiazek.setFixedCellHeight(40);
        listaKsiazek.addListSelectionListener(e -> ksiazkaZarezerwowanaInfo(listaKsiazek.getSelectedValue(), zarezerwowanePanel));
        listaKsiazek.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listaKsiazek.clearSelection();

        DefaultListCellRenderer renderer = (DefaultListCellRenderer)listaKsiazek.getCellRenderer();
        renderer.setHorizontalAlignment(JLabel.CENTER);
        
        scrollPane.setViewportView(listaKsiazek);
        scrollPane.setBounds(300,150,750,400);

        zarezerwowanePanel.add(info);
        zarezerwowanePanel.add(scrollPane);

        zarezerwowanePanel.setVisible(false);
    }

    private static void historiaPanelInit(){
        historiaPanel.restart();

        menuCzytelnikaButtons(historiaPanel);

        historiaPanel.getHeader().setText("Historia wypożyczeń");

        JLabel info = new JLabel("",SwingConstants.CENTER);
        DefaultListModel<String> ksiazki = new DefaultListModel<>();
        JList<String> listaKsiazek = new JList<>(ksiazki); 
        JScrollPane scrollPane = new JScrollPane();

        db.infoWypozyczoneOddane(ksiazki, zalogowanyCzytelnik.getId());    // pobranie danych
        
        if(ksiazki.size()==0)
            info.setText("Historia wypożyczeń jest pusta.");

        info.setBounds(300,110,750,30);

        listaKsiazek.setFixedCellHeight(40);
        // listaKsiazek.addListSelectionListener(e -> egzemplarzWyporzyczonyInfo(listaKsiazek.getSelectedValue(), wypozyczonePanel));
        listaKsiazek.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listaKsiazek.clearSelection();

        DefaultListCellRenderer renderer = (DefaultListCellRenderer)listaKsiazek.getCellRenderer();
        renderer.setHorizontalAlignment(JLabel.CENTER);
        
        scrollPane.setViewportView(listaKsiazek);
        scrollPane.setBounds(300,150,750,400);

        historiaPanel.add(info);
        historiaPanel.add(scrollPane);

        historiaPanel.setVisible(false);
    }

    private static void katalogPanelInit(){
        katalogPanel.restart();

        menuCzytelnikaButtons(katalogPanel);

        katalogPanel.getHeader().setText("Książki");

        //Wyszukiwanie

        DefaultListModel<Ksiazka> ksiazki = new DefaultListModel<>();
        JList<Ksiazka> listaKsiazek = new JList<>(ksiazki); 
        JScrollPane scrollPane = new JScrollPane();
        JLabel info = new JLabel("Kliknij na książkę po więcej informacji.",SwingConstants.CENTER);

        db.infoKsiazka(ksiazki);    // pobranie danych
    
        // Wyszukiwanie

        listaKsiazek.setFixedCellHeight(40);
        listaKsiazek.addListSelectionListener(e -> ksiazkaKatalogInfo(listaKsiazek.getSelectedValue(), katalogPanel));
        listaKsiazek.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listaKsiazek.clearSelection();

        DefaultListCellRenderer renderer = (DefaultListCellRenderer)listaKsiazek.getCellRenderer();
        renderer.setHorizontalAlignment(JLabel.CENTER);
        
        scrollPane.setViewportView(listaKsiazek);
        scrollPane.setBounds(300,150,750,400);

        if(ksiazki.size()==0)
            info.setText("Brak książek :o");
        info.setBounds(300,560,750,30);


        // Wyszukiwanie
        katalogPanel.add(scrollPane);
        katalogPanel.add(info);

        katalogPanel.setVisible(false);
    }


    private static void katalogKsiazekPanelInit(){
        katalogKsiazekPanel = new JPanel();
        katalogKsiazekPanel.setLayout(null);
        katalogKsiazekPanel.setBounds(0, 0, 1200, 700);

        JLabel header = new JLabel("Katalog ksiażek",SwingConstants.CENTER);
        JButton powrotButton = new JButton("Powrót do startu");
        DefaultListModel<Ksiazka> ksiazki = new DefaultListModel<>();
        db.infoKsiazka(ksiazki);    // pobranie danych
        JList<Ksiazka> listaKsiazek = new JList<>(ksiazki); 
        JScrollPane scrollPane = new JScrollPane();

        header.setBounds(0,20,1200,50);
        header.setFont(new Font("TimesRoman",Font.BOLD,40));
        
        listaKsiazek.setFixedCellHeight(40);
        listaKsiazek.addListSelectionListener(e -> ksiazkaInfo(listaKsiazek.getSelectedValue()));
        listaKsiazek.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        DefaultListCellRenderer renderer = (DefaultListCellRenderer)listaKsiazek.getCellRenderer();
        renderer.setHorizontalAlignment(JLabel.CENTER);
        
        scrollPane.setViewportView(listaKsiazek);
        scrollPane.setBounds(200,150,800,300);

        powrotButton.setBounds(465, 500, 270, 30);
        powrotButton.setFocusable(false);
        powrotButton.addActionListener(e -> {startPanel.setVisible(true); katalogKsiazekPanel.setVisible(false);});

        katalogKsiazekPanel.add(header);
        katalogKsiazekPanel.add(powrotButton);
        katalogKsiazekPanel.add(scrollPane);

        katalogKsiazekPanel.setVisible(false);
    }

    private static void ksiazkaInfoPanelInit(){
        ksiazkaInfoPanel = new KsiazkaPanel();
        ksiazkaInfoPanel.getPowrotButton().addActionListener(e -> {
            katalogKsiazekPanel.setVisible(true);
            ksiazkaInfoPanel.setVisible(false);
        });
    }

    

    // --------------------------------------------------------------------------------
    // Funkcje dla przycisków

    private static String actionZaloguj(boolean kto, String user, String password){
        // kto: false - czytelnik, true pracownik 
        String toReturn = "Logowanie nie powiodło się!";
        int id = db.logowanie(kto, user, password);
        if(id != 0){
            // bladLogowaniaCztyelnik.setText("Logowanie powiodło się!");
            toReturn = "";
            if(kto){
                // dla pracownika id to 1, bo klucz główny to login
                zalogowany_pracownik = true;
                loginPracownikPanel.setVisible(false);
                menuPracownikaPanel.setVisible(true);
            }
            else{
                // dla czytelnika potrzebuje zapisanego id do wypożyczeń, rezerwacji itd.
                zalogowanyCzytelnik.setId(id);
                db.infoCzytelnik(zalogowanyCzytelnik);

                menuCzytelnikaPanelInit();
                wypozyczonePanelInit();
                zarezerwowanePanelInit();
                historiaPanelInit();
                katalogPanelInit();

                startPanel.setVisible(false);
                menuCzytelnikaPanel.setVisible(true);
            }
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
    // Menu pracownika
    private static String actionRejestracjaPracownik(String imie, String nazwisko, String email, String login, String haslo1, String haslo2){
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

    private static String actionDodajAutor(String imie, String nazwisko){
        String toReturn;
        String blad = "";
        boolean valid = true;

        if(imie.equals("") || nazwisko.equals("")){
            valid = false;
            blad += "Wypełnij wszystkie wymagane pola. ";
        }

        if(valid){
            if(db.dodajAutor(imie, nazwisko)){
                toReturn = "Dodano autora.";
            }
            else{
                toReturn = "Dodanie autora nie powiodło się." ;
            }
        }
        else{
            toReturn = blad;
        }
        return toReturn;
    }

    private static String actionUsunAutor(Autor autor){
        String toReturn = "<html>Nie udało się usunąć autora.<br />Usuń najpierw jego ksiażki.<html>";
        if(autor == null){
            toReturn = "Wybierz autora."; 
        }
        else if(db.usunAutor(autor.getId()))
            toReturn = "Autor usunięty.";
    
        return toReturn;
    }

    private static String actionZmienAutor(Autor autor, String imie, String nazwisko){
        String toReturn = "Nie udało się zaktualizować danych autora.";
        if(autor == null){
            toReturn = "Najpierw wybierz autora."; 
        }
        else if(db.aktualizujAutor(autor.getId(), imie, nazwisko))
            toReturn = "Autor zaktualizowany.";
    
        return toReturn;
    }

    private static String actionDodajWydawnictwo(String nazwa){
        String toReturn;
        String blad = "";
        boolean valid = true;

        if(nazwa.equals("")){
            valid = false;
            blad += "Wypełnij wszystkie wymagane pola. ";
        }

        if(valid){
            if(db.dodajWydawnictwo(nazwa)){
                toReturn = "Dodano wydawnictwo.";
            }
            else{
                toReturn = "Dodanie wydawnictwa nie powiodło się." ;
            }
        }
        else{
            toReturn = blad;
        }
        return toReturn;
    }

    private static String actionUsunWydawnictwo(Wydawnictwo wydawnictwo){
        String toReturn = "<html>Nie udało się usunąć wydawnictwa.<br />Usuń najpierw jego ksiażki.<html>";
        if(wydawnictwo == null){
            toReturn = "Wybierz wydawnictwo."; 
        }
        else if(db.usunWydawnictwo(wydawnictwo.getId()))
            toReturn = "Wydawnictwo usunięte.";
    
        return toReturn;
    }

    private static String actionZmienWydawnictwo(Wydawnictwo wydawnictwo, String nazwa){
        String toReturn = "Nie udało się zaktualizować danych wydawnictwa.";
        if(wydawnictwo == null){
            toReturn = "Najpierw wybierz wydawnictwo."; 
        }
        else if(db.aktualizujWydawnictwo(wydawnictwo.getId(), nazwa))
            toReturn = "Wydawnictwo zaktualizowane.";
    
        return toReturn;
    }
    
    private static String actionDodajDziedzine(String nazwa, Dziedzina nadDziedzina){
        String toReturn;
        String blad = "";
        boolean valid = true;

        if(nazwa.equals("")){
            valid = false;
            blad += "Wypełnij wszystkie wymagane pola. ";
        }

        if(valid){
            if(db.dodajDziedzina(nazwa, nadDziedzina.getId())){
                toReturn = "Dodano dziedzinę.";
            }
            else{
                toReturn = "Dodanie dziedziny nie powiodło się." ;
            }
        }
        else{
            toReturn = blad;
        }
        return toReturn;
    }

    private static String actionUsunDziedzina(Dziedzina dziedzina){
        String toReturn = "<html>Nie udało się usunąć dziedziny.<br />Usuń najpierw ksiażki o danej tematyce.<html>";
        if(dziedzina == null){
            toReturn = "Wybierz dziedzinę."; 
        }
        else if(db.usunDziedzina(dziedzina.getId()))
            toReturn = "Dziedzina usunięte.";
    
        return toReturn;
    }

    private static String actionZmienDziedzina(Dziedzina dziedzina, String nazwa, Dziedzina nadDziedzina){
        String toReturn = "Nie udało się zaktualizować danych wydawnictwa.";
        if(dziedzina == null){
            toReturn = "Najpierw wybierz wydawnictwo."; 
        }
        else if(db.aktualizujDziedzina(dziedzina.getId(), nazwa, nadDziedzina.getId()))
            toReturn = "Wydawnictwo zaktualizowane.";
    
        return toReturn;
    }


    // Menu czytelnika
    private static void egzemplarzWyporzyczonyInfo(Egzemplarz egzemplarz, MenuCzytelnikaPanel panel/*, JList<Egzemplarz> lista*/){
        egzemplarzInfoWypozyczonePanel.restart();
        menuCzytelnikaButtons(egzemplarzInfoWypozyczonePanel);

        JLabel autor = new JLabel("Autor: " + egzemplarz.getAutorzy());
        JLabel dziedziny = new JLabel("Kategorie: " + egzemplarz.getDziedziny());
        JLabel rok = new JLabel("Rok wydania: " + egzemplarz.getRokwydania());
        JLabel isbn = new JLabel("ISBN: " + egzemplarz.getIsbn());
        JLabel data = new JLabel("Termin oddania: " + egzemplarz.getDataPlanowanegoOddania());
        JButton buttonOddaj = new JButton("Oddaj");

        egzemplarzInfoWypozyczonePanel.getHeader().setText(egzemplarz.getTytul());
        autor.setBounds(465, 150 ,270, 30);
        dziedziny.setBounds(465, 190 ,270, 30);
        rok.setBounds(465, 230 ,270, 30);
        isbn.setBounds(465, 270 ,270, 30);
        data.setBounds(465, 340 ,270, 30);
        buttonOddaj.setBounds(465, 380 ,270, 30);
        buttonOddaj.setFocusable(false);
        buttonOddaj.addActionListener(e -> {
            if(db.oddajEgzemplarz(zalogowanyCzytelnik.getId(),egzemplarz.getIdE())){
                data.setText("Oddane");
                egzemplarzInfoWypozyczonePanel.setVisible(false);
                wypozyczonePanelInit();
                wypozyczonePanel.setVisible(true);
                historiaPanelInit();
            }
        });


        egzemplarzInfoWypozyczonePanel.add(autor);
        egzemplarzInfoWypozyczonePanel.add(dziedziny);
        egzemplarzInfoWypozyczonePanel.add(rok);
        egzemplarzInfoWypozyczonePanel.add(isbn);
        egzemplarzInfoWypozyczonePanel.add(data);
        egzemplarzInfoWypozyczonePanel.add(buttonOddaj);

        egzemplarzInfoWypozyczonePanel.setVisible(true); panel.setVisible(false);
        // lista.clearSelection();
    }

    private static void ksiazkaZarezerwowanaInfo(Ksiazka ksiazka, MenuCzytelnikaPanel panel){
        ksiazkaInfoZarezerwowanaPanel.restart();
        menuCzytelnikaButtons(ksiazkaInfoZarezerwowanaPanel);

        int czyDostepna = db.dostepnoscZarezerwowanejKsiazki(ksiazka.getIdksiazka(), zalogowanyCzytelnik.getId());
        // czyDostepna: 0 - niedostepna, idEgzemplarza dostępnego jeśli dostępna

        JLabel autor = new JLabel("Autor: " + ksiazka.getAutorzy());
        JLabel dziedziny = new JLabel("Kategorie: " + ksiazka.getDziedziny());
        JLabel rok = new JLabel("Rok wydania: " + ksiazka.getRokwydania());
        JLabel isbn = new JLabel("ISBN: " + ksiazka.getIsbn());
        JLabel dostepnosc = new JLabel("Dostępność: ");
        JButton wypozycz = new JButton("Wypozycz");
        JButton usun = new JButton("Usuń rezerwację");

        ksiazkaInfoZarezerwowanaPanel.getHeader().setText(ksiazka.getTytul());
        autor.setBounds(465, 150 ,270, 30);
        dziedziny.setBounds(465, 190 ,270, 30);
        rok.setBounds(465, 230 ,270, 30);
        isbn.setBounds(465, 270 ,270, 30);
        dostepnosc.setBounds(465, 340 ,270, 30);
        
        wypozycz.setBounds(465, 380 ,270, 30);
        wypozycz.setFocusable(false);
        wypozycz.addActionListener(e -> {
            if(db.wypozyczEgzemplarz(zalogowanyCzytelnik.getId(),czyDostepna)){
                ksiazkaInfoZarezerwowanaPanel.setVisible(false);
                zarezerwowanePanelInit();
                zarezerwowanePanel.setVisible(true);
            }
        });

        usun.setBounds(465, 420 ,270, 30);
        usun.setFocusable(false);
        usun.addActionListener(e -> {
            if(db.usunRezerwacje(zalogowanyCzytelnik.getId(),ksiazka.getIdksiazka())){
                ksiazkaInfoZarezerwowanaPanel.setVisible(false);
                zarezerwowanePanelInit();
                zarezerwowanePanel.setVisible(true);
            }
        });

        if(czyDostepna != 0){
            dostepnosc.setText("Dostępność: dostępna");
        }
        else{
            dostepnosc.setText("Dostępność: niedostępna");
            wypozycz.setEnabled(false);
        }

        ksiazkaInfoZarezerwowanaPanel.add(autor);
        ksiazkaInfoZarezerwowanaPanel.add(dziedziny);
        ksiazkaInfoZarezerwowanaPanel.add(rok);
        ksiazkaInfoZarezerwowanaPanel.add(isbn);
        ksiazkaInfoZarezerwowanaPanel.add(dostepnosc);
        ksiazkaInfoZarezerwowanaPanel.add(wypozycz);
        ksiazkaInfoZarezerwowanaPanel.add(usun);

        ksiazkaInfoZarezerwowanaPanel.setVisible(true); panel.setVisible(false);
    }

    private static void ksiazkaKatalogInfo(Ksiazka ksiazka, MenuCzytelnikaPanel panel){
        ksiazkaInfoKatalogPanel.restart();
        menuCzytelnikaButtons(ksiazkaInfoKatalogPanel);

        int czyDostepna = db.dostepnoscKsiazki(ksiazka.getIdksiazka());
        // czyDostepna: 0 - niedostepna, idEgzemplarza dostępnego jeśli dostępna

        JLabel autor = new JLabel("Autor: " + ksiazka.getAutorzy());
        JLabel dziedziny = new JLabel("Kategorie: " + ksiazka.getDziedziny());
        JLabel rok = new JLabel("Rok wydania: " + ksiazka.getRokwydania());
        JLabel isbn = new JLabel("ISBN: " + ksiazka.getIsbn());
        JLabel dostepnosc = new JLabel("Dostępność: ");
        JButton wypozycz = new JButton("Wypozycz");
        JButton zarezerwuj = new JButton("Zarezerwuj");

        ksiazkaInfoKatalogPanel.getHeader().setText(ksiazka.getTytul());
        autor.setBounds(465, 150 ,270, 30);
        dziedziny.setBounds(465, 190 ,270, 30);
        rok.setBounds(465, 230 ,270, 30);
        isbn.setBounds(465, 270 ,270, 30);
        dostepnosc.setBounds(465, 340 ,270, 30);
        wypozycz.setBounds(465, 380 ,270, 30);
        wypozycz.setFocusable(false);
        wypozycz.addActionListener(e -> {
            if(db.wypozyczEgzemplarz(zalogowanyCzytelnik.getId(),czyDostepna)){
                dostepnosc.setText("Wypozyczono");
                wypozycz.setEnabled(false);
                zarezerwuj.setEnabled(false);
                wypozyczonePanelInit();
            }
        });
        zarezerwuj.setBounds(465, 410, 270, 30);
        zarezerwuj.setFocusable(false);
        zarezerwuj.addActionListener(e -> {
            if(db.rezerwujEgzemplarz(zalogowanyCzytelnik.getId(), ksiazka.getIdksiazka())){
                dostepnosc.setText("Zarezerwowano");
                zarezerwuj.setEnabled(false);
                zarezerwowanePanelInit();
            }
        });


        if(czyDostepna != 0){
            dostepnosc.setText("Dostępność: dostępna");
            wypozycz.setEnabled(true);
            zarezerwuj.setEnabled(false);
        }
        else{
            dostepnosc.setText("Dostępność: niedostępna");
            wypozycz.setEnabled(false);
            zarezerwuj.setEnabled(true);
        }

        ksiazkaInfoKatalogPanel.add(autor);
        ksiazkaInfoKatalogPanel.add(dziedziny);
        ksiazkaInfoKatalogPanel.add(rok);
        ksiazkaInfoKatalogPanel.add(isbn);
        ksiazkaInfoKatalogPanel.add(dostepnosc);
        ksiazkaInfoKatalogPanel.add(wypozycz);
        ksiazkaInfoKatalogPanel.add(zarezerwuj);

        ksiazkaInfoKatalogPanel.setVisible(true); panel.setVisible(false);
    }


    
    // Bez logowania    
    private static String actionUsunKsiazka(Ksiazka ksiazka){
        String toReturn = "<html>Nie udało się usunąć książki.<br />Usuń najpierw jego egzemplarze.<html>";
        if(ksiazka == null){
            toReturn = "Wybierz książkę."; 
        }
        // else if(db.usunKsiazka(wydawnictwo.getId()))
            // toReturn = "Wydawnictwo usunięte.";
    
        return toReturn;
    }

    private static void ksiazkaInfo(Ksiazka k){
        ksiazkaInfoPanel.setVisible(true); katalogKsiazekPanel.setVisible(false);
        ksiazkaInfoPanel.getTytul().setText(k.getTytul());
        ksiazkaInfoPanel.getAutor().setText("Autor: " + k.getAutorzy());
        ksiazkaInfoPanel.getRokWydania().setText("Rok wydania: " + String.valueOf(k.getRokwydania()));
        ksiazkaInfoPanel.getIsbn().setText("ISBN: " + String.valueOf(k.getIsbn()));
        ksiazkaInfoPanel.getDziedziny().setText("Kategorie: " + k.getDziedziny());
    }


    public static void main(String[] args){
        new MyFrame();
    }
}
