import javax.lang.model.util.ElementScanner14;
import javax.swing.*;
import java.awt.Font;
import java.math.BigDecimal;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MyFrame extends JFrame{

    private static DB db = new DB(); 

    private static Czytelnik zalogowanyCzytelnik = new Czytelnik();
    // private static boolean zalogowany_pracownik = false;

    private static JPanel startPanel;
    private static JPanel loginPracownikPanel;
    private static MenuPracownikaPanel menuPracownikaPanel;
    private static MenuPracownikaPanel pracownikPanel;  // dodawanie usuwanie pracownika
    private static MenuPracownikaPanel autorPanel;  // dodawanie usuwanie autora
    private static MenuPracownikaPanel wydawnictwoPanel;
    private static MenuPracownikaPanel dziedzinaPanel;
    private static MenuPracownikaPanel ksiazkaPanel;
    private static MenuPracownikaPanel ksiazkaDodajUsunAutoraPanel;
    private static MenuPracownikaPanel ksiazkaDodajUsunDziedzinePanel;

    private static MenuPracownikaPanel egzemplarzPanel;
    private static MenuPracownikaPanel egzemplarzDodajUsunPanel;

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
        ksiazkaPanel = new MenuPracownikaPanel();
        ksiazkaPanelInit();
        ksiazkaDodajUsunAutoraPanel = new MenuPracownikaPanel();
        ksiazkaDodajUsunDziedzinePanel = new MenuPracownikaPanel();
        egzemplarzPanelInit();
        egzemplarzDodajUsunPanel = new MenuPracownikaPanel();

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
        this.add(ksiazkaDodajUsunAutoraPanel);
        this.add(ksiazkaDodajUsunDziedzinePanel);
        this.add(egzemplarzPanel);
        this.add(egzemplarzDodajUsunPanel);

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
        header.setFont(new Font("TimesRoman",Font.BOLD,40));

        loginLabel.setBounds(465, 200, 80, 25);
        login.setBounds(565, 200, 170, 25);

        hasloLabel.setBounds(465, 240, 80, 25);
        haslo.setBounds(565, 240, 170, 25);

        buttonLogin.setBounds(465, 280 ,130, 30);
        buttonLogin.setFocusable(false);
        buttonLogin.addActionListener(e -> {
            bladLogowania.setText(actionZaloguj(false,login.getText(), String.valueOf(haslo.getPassword())));
            login.setText("");
            haslo.setText("");
        });

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
        buttonLogin.addActionListener(e -> {
            bladLogowania.setText(actionZaloguj(true,login.getText(), String.valueOf(haslo.getPassword())));
            login.setText("");
            haslo.setText("");
        });

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

    // Pracownik

    private static void menuPracownikaButtons(MenuPracownikaPanel panel){
        panel.getWylogujButton().addActionListener(e -> {
            // zalogowany_pracownik = false; 
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
        JButton buttonRejestracja = new JButton("Dodaj");
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
        buttonSzukaj.setBounds(890,200,110,25);
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

        buttonUsun.setBounds(700, 510 ,300, 30);
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
        ksiazkaPanel.restart();
        ksiazkaPanel.setLayout(null);
        ksiazkaPanel.setBounds(0, 0, 1200, 700);

        menuPracownikaButtons(ksiazkaPanel);

        ksiazkaPanel.getHeader().setText("Książki");

        JLabel dodajLabel = new JLabel("Dodaj książkę",SwingConstants.CENTER);
        JLabel nazwaLabel = new JLabel("Nazwa* ");
        JTextField nazwa = new JTextField();
        JLabel rokLabel = new JLabel("Rok wydania* ");
        JTextField rok = new JTextField();
        JLabel isbnLabel = new JLabel("ISBN* ");
        JTextField isbn = new JTextField();
        JLabel wydawnictwoLabel = new JLabel("Wydawnictwo* ");
        Vector<Wydawnictwo> wydawnictwa = new Vector<>();
        JComboBox<Wydawnictwo> wydawnictwo = new JComboBox<>(wydawnictwa);
        JButton buttonDodaj = new JButton("Dodaj");
        JLabel infoDodajLabel = new JLabel("* pole wymagane");

        JLabel dodajLabel2 = new JLabel("Aktualizuj książkę",SwingConstants.CENTER);
        JLabel nazwaLabel2 = new JLabel("Nazwa* ");
        JTextField nazwa2 = new JTextField();
        JLabel rokLabel2 = new JLabel("Rok wydania* ");
        JTextField rok2 = new JTextField();
        JLabel isbnLabel2 = new JLabel("ISBN* ");
        JTextField isbn2 = new JTextField();
        JLabel wydawnictwoLabel2 = new JLabel("Wydawnictwo* ");
        Vector<Wydawnictwo> wydawnictwa2 = new Vector<>();
        JComboBox<Wydawnictwo> wydawnictwo2 = new JComboBox<>(wydawnictwa);
        JButton buttonAktualizuj2 = new JButton("Aktualizuj");
        JLabel infoAktualizujLabel2 = new JLabel("* pole wymagane");

        JLabel usunLabel = new JLabel("Usuń ksiażkę",SwingConstants.CENTER);
        JTextField szukaj = new JTextField();
        JButton buttonSzukaj = new JButton("Szukaj");
        DefaultListModel<Ksiazka> ksiazki = new DefaultListModel<>();
        JList<Ksiazka> listaKsiazka = new JList<>(ksiazki); 
        JScrollPane scrollPane = new JScrollPane();

        JButton buttonUsun = new JButton("Usuń");
        JLabel infoUsunLabel = new JLabel("");

        db.infoWydawnictwo(wydawnictwa);

        dodajLabel.setFont(new Font("TimesRoman",Font.BOLD,20));
        dodajLabel.setBounds(300, 90, 270, 25);

        nazwaLabel.setBounds(300, 140, 80, 25);
        nazwa.setBounds(400, 140, 170, 25);

        rokLabel.setBounds(300, 180, 80, 25);
        rok.setBounds(400, 180, 170, 25);

        isbnLabel.setBounds(300, 220, 80, 25);
        isbn.setBounds(400, 220, 170, 25);

        wydawnictwoLabel.setBounds(300, 260, 85, 25);
        wydawnictwo.setBounds(400, 260, 170, 25);
        wydawnictwo.setSelectedIndex(0);

        buttonDodaj.setBounds(300, 300 ,270, 30);
        buttonDodaj.setFocusable(false);
        buttonDodaj.addActionListener(e -> {
            actionDodajKsiazke(nazwa.getText(), rok.getText(), isbn.getText(), (Wydawnictwo)wydawnictwo.getSelectedItem(), infoDodajLabel);
        });

        infoDodajLabel.setBounds(300, 330, 270, 30);

        // modyfikacja

        db.infoWydawnictwo(wydawnictwa2);

        dodajLabel2.setFont(new Font("TimesRoman",Font.BOLD,20));
        dodajLabel2.setBounds(300, 370, 270, 25);

        nazwaLabel2.setBounds(300, 420, 80, 25);
        nazwa2.setBounds(400, 420, 170, 25);

        rokLabel2.setBounds(300, 460, 80, 25);
        rok2.setBounds(400, 460, 170, 25);

        isbnLabel2.setBounds(300, 500, 80, 25);
        isbn2.setBounds(400, 500, 170, 25);

        wydawnictwoLabel2.setBounds(300, 540, 85, 25);
        wydawnictwo2.setBounds(400, 540, 170, 25);
        wydawnictwo2.setSelectedIndex(0);

        buttonAktualizuj2.setBounds(300, 580 ,270, 30);
        buttonAktualizuj2.setFocusable(false);
        buttonAktualizuj2.addActionListener(e -> {
            actionAutualizujKsiazke(listaKsiazka.getSelectedValue().getIdksiazka(), nazwa2.getText(), rok2.getText(), isbn2.getText(), (Wydawnictwo)wydawnictwo2.getSelectedItem(), infoAktualizujLabel2);
        });

        infoAktualizujLabel2.setBounds(300, 610, 270, 30);


        db.infoKsiazka(ksiazki);
        usunLabel.setFont(new Font("TimesRoman",Font.BOLD,20));
        usunLabel.setBounds(700, 150, 270, 25);

        szukaj.setBounds(700,200,170,25);
        buttonSzukaj.setBounds(890,200,110,25);
        buttonSzukaj.addActionListener(e -> {db.infoKsiazka(ksiazki, szukaj.getText());});

        listaKsiazka.setFixedCellHeight(40);
        listaKsiazka.addListSelectionListener(e -> {
            if(listaKsiazka.getSelectedValue() != null){
                Ksiazka tmp = listaKsiazka.getSelectedValue();
                nazwa2.setText(tmp.getTytul());
                rok2.setText("" + tmp.getRokwydania());
                isbn2.setText("" + tmp.getIsbn());
                wydawnictwo2.setSelectedIndex(tmp.getWydawnictwo().getId()+1);
            }
        });
        listaKsiazka.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        scrollPane.setViewportView(listaKsiazka);
        scrollPane.setBounds(700,240,300,250);

        buttonUsun.setBounds(700, 510 ,300, 30);
        buttonUsun.setFocusable(false);
        buttonUsun.addActionListener(e -> {
            infoUsunLabel.setText(actionUsunKsiazka(listaKsiazka.getSelectedValue()));
            db.infoKsiazka(ksiazki);
        });

        infoUsunLabel.setBounds(700, 550, 270, 50);


        ksiazkaPanel.add(dodajLabel);
        ksiazkaPanel.add(nazwaLabel);
        ksiazkaPanel.add(nazwa);
        ksiazkaPanel.add(rokLabel);
        ksiazkaPanel.add(rok);
        ksiazkaPanel.add(isbnLabel);
        ksiazkaPanel.add(isbn);
        ksiazkaPanel.add(wydawnictwoLabel);
        ksiazkaPanel.add(wydawnictwo);
        ksiazkaPanel.add(buttonDodaj);
        ksiazkaPanel.add(infoDodajLabel);

        ksiazkaPanel.add(dodajLabel2);
        ksiazkaPanel.add(nazwaLabel2);
        ksiazkaPanel.add(nazwa2);
        ksiazkaPanel.add(rokLabel2);
        ksiazkaPanel.add(rok2);
        ksiazkaPanel.add(isbnLabel2);
        ksiazkaPanel.add(isbn2);
        ksiazkaPanel.add(wydawnictwoLabel2);
        ksiazkaPanel.add(wydawnictwo2);
        ksiazkaPanel.add(buttonAktualizuj2);
        ksiazkaPanel.add(infoAktualizujLabel2);

        ksiazkaPanel.add(usunLabel);
        ksiazkaPanel.add(szukaj);
        ksiazkaPanel.add(buttonSzukaj);
        ksiazkaPanel.add(scrollPane);
        ksiazkaPanel.add(buttonUsun);
        ksiazkaPanel.add(infoUsunLabel);

        ksiazkaPanel.setVisible(false);
    }

    private static void ksiazkaDodajUsunAutoraPanelInit(int idKsiazka, String nazwa, String rok, String isbn, String wydawnictwo){
        ksiazkaDodajUsunAutoraPanel.restart();
        ksiazkaDodajUsunAutoraPanel.setLayout(null);
        ksiazkaDodajUsunAutoraPanel.setBounds(0, 0, 1200, 700);

        // menuPracownikaButtons(ksiazkaDodajUsunAutoraPanel);
        ksiazkaDodajUsunAutoraPanel.getWylogujButton().setEnabled(false);
        ksiazkaDodajUsunAutoraPanel.getPracownikButton().setEnabled(false);
        ksiazkaDodajUsunAutoraPanel.getAutorButton().setEnabled(false);
        ksiazkaDodajUsunAutoraPanel.getWydawnictwoButton().setEnabled(false);
        ksiazkaDodajUsunAutoraPanel.getDziedzinaButton().setEnabled(false);
        ksiazkaDodajUsunAutoraPanel.getKsiazkaButton().setEnabled(false);
        ksiazkaDodajUsunAutoraPanel.getEgzemplarzButton().setEnabled(false);


        ksiazkaDodajUsunAutoraPanel.getHeader().setText("Autor książki");
        ksiazkaDodajUsunAutoraPanel.getHeader().setBounds(180,20,1020,50);

        ksiazkaPanel.getHeader().setText("Książki");

        JLabel infoK = new JLabel("Tytuł: " + nazwa + ", Rok: " + rok + ", ISBN: " + isbn + ", Wydawnictwo: "+ wydawnictwo,SwingConstants.CENTER);

        JLabel dodajLabel = new JLabel("Dodaj autora",SwingConstants.CENTER);
        JLabel autorLabel = new JLabel("Autor* ");
        Vector<Autor> autorzy = new Vector<>();
        JComboBox<Autor> autor = new JComboBox<>(autorzy);
        JButton buttonDodaj = new JButton("Dodaj");
        JLabel infoDodajLabel = new JLabel("* pole wymagane");
        JButton buttonDalej = new JButton("Dalej");

        JLabel usunLabel = new JLabel("Usuń autora",SwingConstants.CENTER);
        DefaultListModel<Autor> autorzy2 = new DefaultListModel<>();
        db.infoAutorKsiazka(autorzy2, idKsiazka);
        JList<Autor> listaAutor = new JList<>(autorzy2); 
        JScrollPane scrollPane = new JScrollPane();

        JButton buttonUsun = new JButton("Usuń");
        JLabel infoUsunLabel = new JLabel("");

        db.infoAutor(autorzy);

        infoK.setBounds(180,90,1020,50);

        dodajLabel.setFont(new Font("TimesRoman",Font.BOLD,20));
        dodajLabel.setBounds(300, 150, 270, 25);

        autorLabel.setBounds(300, 200, 85, 25);
        autor.setBounds(400, 200, 170, 25);
        autor.setSelectedIndex(0);

        buttonDodaj.setBounds(300, 240 ,270, 30);
        buttonDodaj.setFocusable(false);
        buttonDodaj.addActionListener(e -> {
            if(db.dodajAutorKsiazka(idKsiazka, ((Autor)(autor.getSelectedItem())).getId())){
                db.infoAutorKsiazka(autorzy2, idKsiazka);
                infoDodajLabel.setText("Autor dodany do książki. Dodaj kolejnego autora lub kliknij przycisk dalej.");
            }
            else
                infoDodajLabel.setText("Nie udało się dodać autora do książki.");
        });

        infoDodajLabel.setBounds(300, 270, 270, 30);

        buttonDalej.setBounds(300, 310, 270, 30);
        buttonDalej.setFocusable(false);
        buttonDalej.addActionListener(e -> {
            ksiazkaDodajUsunDziedzinePanelInit(idKsiazka, nazwa, rok, isbn, wydawnictwo);
            infoDodajLabel.setText("Dalej");
        });

        usunLabel.setFont(new Font("TimesRoman",Font.BOLD,20));
        usunLabel.setBounds(700, 150, 270, 25);

        listaAutor.setFixedCellHeight(40);
        listaAutor.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        scrollPane.setViewportView(listaAutor);
        scrollPane.setBounds(700,240,300,250);

        buttonUsun.setBounds(700, 510 ,300, 30);
        buttonUsun.setFocusable(false);
        buttonUsun.addActionListener(e -> {
            if(db.usunAutorKsiazka(idKsiazka, listaAutor.getSelectedValue().getId())){
                infoUsunLabel.setText("Autor usunięty.");
                db.infoAutorKsiazka(autorzy2, idKsiazka);
            }
            else    
                infoUsunLabel.setText("Nie udało się usunąć autora.");
        });

        infoUsunLabel.setBounds(700, 550, 270, 50);

        ksiazkaDodajUsunAutoraPanel.add(infoK);
        ksiazkaDodajUsunAutoraPanel.add(dodajLabel);
        ksiazkaDodajUsunAutoraPanel.add(autorLabel);
        ksiazkaDodajUsunAutoraPanel.add(autor);
        ksiazkaDodajUsunAutoraPanel.add(buttonDodaj);
        ksiazkaDodajUsunAutoraPanel.add(infoDodajLabel);
        ksiazkaDodajUsunAutoraPanel.add(buttonDalej);

        ksiazkaDodajUsunAutoraPanel.add(usunLabel);
        ksiazkaDodajUsunAutoraPanel.add(buttonUsun);
        ksiazkaDodajUsunAutoraPanel.add(scrollPane);

        ksiazkaDodajUsunAutoraPanel.setVisible(true);
        ksiazkaPanel.setVisible(false);
    }

    private static void ksiazkaDodajUsunDziedzinePanelInit(int idKsiazka, String nazwa, String rok, String isbn, String wydawnictwo){
        ksiazkaDodajUsunDziedzinePanel.restart();
        ksiazkaDodajUsunDziedzinePanel.setLayout(null);
        ksiazkaDodajUsunDziedzinePanel.setBounds(0, 0, 1200, 700);

        // menuPracownikaButtons(ksiazkaDodajUsunDziedzinePanel);
        ksiazkaDodajUsunDziedzinePanel.getWylogujButton().setEnabled(false);
        ksiazkaDodajUsunDziedzinePanel.getPracownikButton().setEnabled(false);
        ksiazkaDodajUsunDziedzinePanel.getAutorButton().setEnabled(false);
        ksiazkaDodajUsunDziedzinePanel.getWydawnictwoButton().setEnabled(false);
        ksiazkaDodajUsunDziedzinePanel.getDziedzinaButton().setEnabled(false);
        ksiazkaDodajUsunDziedzinePanel.getKsiazkaButton().setEnabled(false);
        ksiazkaDodajUsunDziedzinePanel.getEgzemplarzButton().setEnabled(false);

        ksiazkaDodajUsunDziedzinePanel.getHeader().setText("Wydawnictwo książki");
        ksiazkaDodajUsunDziedzinePanel.getHeader().setBounds(180,20,1020,50);

        ksiazkaPanel.getHeader().setText("Książki");

        JLabel infoK = new JLabel("Tytuł: " + nazwa + ", Rok: " + rok + ", ISBN: " + isbn + ", Wydawnictwo: "+ wydawnictwo,SwingConstants.CENTER);

        JLabel dodajLabel = new JLabel("Dodaj dziedzinę",SwingConstants.CENTER);
        JLabel dziedzinaLabel = new JLabel("Dziedzina* ");
        Vector<Dziedzina> dziedziny = new Vector<>();
        JComboBox<Dziedzina> dziedzina = new JComboBox<>(dziedziny);
        JButton buttonDodaj = new JButton("Dodaj");
        JLabel infoDodajLabel = new JLabel("* pole wymagane");
        JButton buttonZakoncz = new JButton("Zakończ");

        JLabel usunLabel = new JLabel("Usuń autora",SwingConstants.CENTER);
        DefaultListModel<Dziedzina> dziedziny2 = new DefaultListModel<>();
        db.infoDziedzinaKsiazka(dziedziny2, idKsiazka);
        JList<Dziedzina> listaDziedzina = new JList<>(dziedziny2); 
        JScrollPane scrollPane = new JScrollPane();

        JButton buttonUsun = new JButton("Usuń");
        JLabel infoUsunLabel = new JLabel("");

        db.infoDziedzina(dziedziny, new DefaultListModel<Dziedzina>());

        infoK.setBounds(180,90,1020,50);

        dodajLabel.setFont(new Font("TimesRoman",Font.BOLD,20));
        dodajLabel.setBounds(300, 150, 270, 25);

        dziedzinaLabel.setBounds(300, 200, 85, 25);
        dziedzina.setBounds(400, 200, 170, 25);
        dziedzina.setSelectedIndex(0);

        buttonDodaj.setBounds(300, 240 ,270, 30);
        buttonDodaj.setFocusable(false);
        buttonDodaj.addActionListener(e -> {
            if(db.dodajDziedzinaKsiazka(idKsiazka, ((Dziedzina)(dziedzina.getSelectedItem())).getId())){
                db.infoDziedzinaKsiazka(dziedziny2, idKsiazka);
                infoDodajLabel.setText("Dziedzina dodana do książki. Możesz dodać kolejną dziedzinę.");
            }
            else
                infoDodajLabel.setText("Nie udało się dodać autora do książki.");
        });

        infoDodajLabel.setBounds(300, 270, 270, 30);

        buttonZakoncz.setBounds(300, 310 ,270, 30);
        buttonZakoncz.setFocusable(false);
        buttonZakoncz.addActionListener(e -> {
            ksiazkaDodajUsunDziedzinePanel.setVisible(false);
            ksiazkaPanelInit();
            ksiazkaPanel.setVisible(true);

        });


        usunLabel.setFont(new Font("TimesRoman",Font.BOLD,20));
        usunLabel.setBounds(700, 150, 270, 25);


        listaDziedzina.setFixedCellHeight(40);
        listaDziedzina.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        scrollPane.setViewportView(listaDziedzina);
        scrollPane.setBounds(700,240,300,250);

        buttonUsun.setBounds(700, 510 ,300, 30);
        buttonUsun.setFocusable(false);
        buttonUsun.addActionListener(e -> {
            if(db.usunDziedzinaKsiazka(idKsiazka, listaDziedzina.getSelectedValue().getId())){
                infoUsunLabel.setText("Dziedzina usunięta.");
                db.infoDziedzinaKsiazka(dziedziny2, idKsiazka);
            }
            else    
                infoUsunLabel.setText("Nie udało się usunąć dziedziny.");
        });


        infoUsunLabel.setBounds(700, 550, 270, 50);

        ksiazkaDodajUsunDziedzinePanel.add(infoK);
        ksiazkaDodajUsunDziedzinePanel.add(dodajLabel);
        ksiazkaDodajUsunDziedzinePanel.add(dziedzinaLabel);
        ksiazkaDodajUsunDziedzinePanel.add(dziedzina);
        ksiazkaDodajUsunDziedzinePanel.add(buttonDodaj);
        ksiazkaDodajUsunDziedzinePanel.add(infoDodajLabel);
        ksiazkaDodajUsunDziedzinePanel.add(buttonZakoncz);

        ksiazkaDodajUsunDziedzinePanel.add(usunLabel);
        ksiazkaDodajUsunDziedzinePanel.add(buttonUsun);
        ksiazkaDodajUsunDziedzinePanel.add(scrollPane);

        ksiazkaDodajUsunDziedzinePanel.setVisible(true);
        ksiazkaDodajUsunAutoraPanel.setVisible(false);
    }

    private static void egzemplarzPanelInit(){
        egzemplarzPanel = new MenuPracownikaPanel();
        egzemplarzPanel.setLayout(null);
        egzemplarzPanel.setBounds(0, 0, 1200, 700);

        menuPracownikaButtons(egzemplarzPanel);

        egzemplarzPanel.getHeader().setText("Egzemplarze");
        egzemplarzPanel.getHeader().setBounds(180,20,1020,50);

        JLabel info = new JLabel("Kliknij na książkę aby dodać lub usunąć jej egzemplarz.",SwingConstants.CENTER);
        DefaultListModel<Ksiazka> ksiazki = new DefaultListModel<>();
        JList<Ksiazka> listaKsiazek = new JList<>(ksiazki); 
        JScrollPane scrollPane = new JScrollPane();
        JLabel szukajInfo = new JLabel("Wpisz autora lub tytuł: ");
        JTextField szukaj = new JTextField();
        JButton buttonSzukaj = new JButton("Szukaj");

        db.infoKsiazka(ksiazki);    // pobranie danych
        
        if(ksiazki.size()==0)
            info.setText("Brak książek.");

        info.setBounds(180,90,1020,50);
        info.setFont(new Font("Dialog",Font.BOLD,14));

        szukajInfo.setBounds(300,150,170,25);
        szukaj.setBounds(450,150,490,25);
        buttonSzukaj.setBounds(950,150,100,25);
        buttonSzukaj.setFocusable(false);
        buttonSzukaj.addActionListener(e -> {db.infoKsiazka(ksiazki,szukaj.getText());});


        listaKsiazek.setFixedCellHeight(40);
        listaKsiazek.addListSelectionListener(e -> {
            Ksiazka tmp = listaKsiazek.getSelectedValue();
            Ksiazka book = new Ksiazka(tmp.getIdksiazka(), tmp.getTytul(), tmp.getRokwydania(), tmp.getIsbn(), tmp.getWydawnictwo(), tmp.getAutorzy(), tmp.getDziedziny());
            EgzemplarzDodajUsun(book, egzemplarzPanel);
        });
        listaKsiazek.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listaKsiazek.clearSelection();

        DefaultListCellRenderer renderer = (DefaultListCellRenderer)listaKsiazek.getCellRenderer();
        renderer.setHorizontalAlignment(JLabel.CENTER);
        
        scrollPane.setViewportView(listaKsiazek);
        scrollPane.setBounds(300,180,750,400);

        egzemplarzPanel.add(info);
        egzemplarzPanel.add(szukajInfo);
        egzemplarzPanel.add(szukaj);
        egzemplarzPanel.add(buttonSzukaj);
        egzemplarzPanel.add(scrollPane);
        

        egzemplarzPanel.setVisible(false);
    }

    // Czytelnik

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
            panel.setVisible(false);
            wypozyczonePanel.setVisible(true);
        });

        panel.getRezerwacjeButton().addActionListener(e -> {
            panel.setVisible(false);
            zarezerwowanePanel.setVisible(true);

        });

        panel.getHistoriaButton().addActionListener(e -> {
            panel.setVisible(false);
            historiaPanel.setVisible(true);
        });

        panel.getKatalogButton().addActionListener(e -> {
            panel.setVisible(false);
            katalogPanel.setVisible(true);
        });

    }
    
    private static void menuCzytelnikaPanelInit(){
        menuCzytelnikaPanel.restart();
        menuCzytelnikaButtons(menuCzytelnikaPanel);

        JLabel imieLabel = new JLabel("Imie ");
        JLabel imie = new JLabel();
        JLabel nazwiskoLabel = new JLabel("Nazwisko ");
        JLabel nazwisko = new JLabel();
        JLabel emailLabel = new JLabel("Email ");
        JLabel email = new JLabel();
        JLabel telefonLabel = new JLabel("Telefon ");
        JLabel telefon= new JLabel();
        JLabel loginLabel = new JLabel("Login ");
        JLabel login= new JLabel();
        JLabel karaLabel = new JLabel("<html>Kara<br />do zapłaty: </html>");
        JLabel kara = new JLabel();
        JButton buttonZaplac = new JButton("Opłać karę");
        
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

        buttonZaplac.setBounds(600, 350 ,135, 30);
        buttonZaplac.setFocusable(false);
        buttonZaplac.addActionListener(e -> {
            if(db.aktualizujKaraCzytelnik(zalogowanyCzytelnik))
                kara.setText(""+ zalogowanyCzytelnik.getKara());
        });

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
        menuCzytelnikaPanel.add(buttonZaplac);

        menuCzytelnikaPanel.setVisible(false);
    }

    private static void wypozyczonePanelInit(){
        wypozyczonePanel.restart();

        menuCzytelnikaButtons(wypozyczonePanel);

        wypozyczonePanel.getHeader().setText("Wypożyczone książki");

        JLabel info = new JLabel("Kliknij na książkę po więcej informacji.",SwingConstants.CENTER);
        DefaultListModel<Egzemplarz> egzemplarzeWypozyczone = new DefaultListModel<>();
        JList<Egzemplarz> listaKsiazek = new JList<>(egzemplarzeWypozyczone); 
        JScrollPane scrollPane = new JScrollPane();

        db.infoWypozyczone(egzemplarzeWypozyczone, zalogowanyCzytelnik.getId());    // pobranie danych
        
        if(egzemplarzeWypozyczone.size()==0)
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

        DefaultListModel<Ksiazka> ksiazki = new DefaultListModel<>();
        JList<Ksiazka> listaKsiazek = new JList<>(ksiazki); 
        JScrollPane scrollPane = new JScrollPane();
        JLabel info = new JLabel("Kliknij na książkę po więcej informacji.",SwingConstants.CENTER);
        db.infoKsiazka(ksiazki);
        JLabel szukajLabel = new JLabel("<html>Wpisz autora/tytuł i/lub<br />wybierz kategorię: <html>");
        JTextArea szukaj = new JTextArea();
        Vector<Dziedzina> dziedziny = new Vector<>();
        JComboBox<Dziedzina> dziedzina = new JComboBox<>(dziedziny);
        JButton buttonSzukaj = new JButton("Szukaj");
        
        szukajLabel.setBounds(300, 105, 250, 30);
        szukaj.setBounds(450, 110, 225, 25);
        db.infoDziedzina(dziedziny, new DefaultListModel<Dziedzina>());
        dziedzina.setBounds(685, 110, 225, 25);
        dziedzina.setSelectedIndex(0);
        buttonSzukaj.setBounds(920, 110, 130, 25);
        buttonSzukaj.setFocusable(false);
        buttonSzukaj.addActionListener(e -> {
            db.infoKsiazka(ksiazki, szukaj.getText(), (Dziedzina)dziedzina.getSelectedItem());
        });

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

        katalogPanel.add(szukajLabel);
        katalogPanel.add(szukajLabel);
        katalogPanel.add(szukaj);
        katalogPanel.add(dziedzina);
        katalogPanel.add(buttonSzukaj);
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
        db.infoKsiazka(ksiazki);
        JList<Ksiazka> listaKsiazek = new JList<>(ksiazki); 
        JScrollPane scrollPane = new JScrollPane();

        JLabel szukajLabel = new JLabel("Wpisz autora/tytuł i/lub wybierz kategorię: ");
        JTextArea szukaj = new JTextArea();
        Vector<Dziedzina> dziedziny = new Vector<>();
        JComboBox<Dziedzina> dziedzina = new JComboBox<>(dziedziny);
        JButton buttonSzukaj = new JButton("Szukaj");

        header.setBounds(0,20,1200,50);
        header.setFont(new Font("TimesRoman",Font.BOLD,40));
        
        szukajLabel.setBounds(200, 120, 250, 25);
        szukaj.setBounds(450, 120, 200, 25);
        db.infoDziedzina(dziedziny, new DefaultListModel<Dziedzina>());
        dziedzina.setBounds(660, 120, 200, 25);
        dziedzina.setSelectedIndex(0);
        buttonSzukaj.setBounds(870, 120, 130, 25);
        buttonSzukaj.setFocusable(false);
        buttonSzukaj.addActionListener(e -> {
            db.infoKsiazka(ksiazki, szukaj.getText(), (Dziedzina)dziedzina.getSelectedItem());
        });

        listaKsiazek.setFixedCellHeight(40);
        listaKsiazek.addListSelectionListener(e -> ksiazkaInfo(listaKsiazek.getSelectedValue()));
        listaKsiazek.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        DefaultListCellRenderer renderer = (DefaultListCellRenderer)listaKsiazek.getCellRenderer();
        renderer.setHorizontalAlignment(JLabel.CENTER);
        
        scrollPane.setViewportView(listaKsiazek);
        scrollPane.setBounds(200,160,800,350);

        powrotButton.setBounds(465, 530, 270, 30);
        powrotButton.setFocusable(false);
        powrotButton.addActionListener(e -> {startPanel.setVisible(true); katalogKsiazekPanel.setVisible(false);});

        katalogKsiazekPanel.add(header);
        katalogKsiazekPanel.add(szukajLabel);
        katalogKsiazekPanel.add(szukaj);
        katalogKsiazekPanel.add(dziedzina);
        katalogKsiazekPanel.add(buttonSzukaj);
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
            toReturn = "";
            if(kto){
                // dla pracownika id to 1, bo klucz główny to login
                // zalogowany_pracownik = true;
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

    private static void actionDodajKsiazke(String nazwa, String rok, String isbn, Wydawnictwo wydawnictwo, JLabel label){
        String blad = "";
        boolean valid = true;

        Pattern patternRok = Pattern.compile("^\\d{4}$");
        Matcher matcherRok = patternRok.matcher(rok);
        Pattern patternISBN = Pattern.compile("^\\d{13}$");
        Matcher matcherISBN = patternISBN.matcher(isbn);
        
        int rok_i = Integer.valueOf(rok);
        BigDecimal isbn_i = new BigDecimal(isbn);

        if(nazwa.equals("") || rok.equals("") || isbn.equals("") || wydawnictwo.getId() == 0){
            valid = false;
            blad = "Wypełnij wszystkie wymagane pola. ";
        }
        else if(!matcherRok.matches()){
            valid = false;
            blad = "Błędny rok. ";
        }
        else if(!matcherISBN.matches()){
            valid = false;
            blad = "Błędny ISBN. ";
        }
        if(valid){
            int id = db.dodajKsiazka(nazwa, rok_i, isbn_i, wydawnictwo.getId());
            if(id == 0){
                label.setText("Nie udało się dodać książki. Nieprawidłowy rok lub ISBN.");
            }
            else
                ksiazkaDodajUsunAutoraPanelInit(id, nazwa, rok, isbn, wydawnictwo.getNazwa());
        }
        else{
            label.setText(blad);
        }
    }

    
    private static void actionAutualizujKsiazke(int idKsiazka, String nazwa, String rok, String isbn, Wydawnictwo wydawnictwo, JLabel label){
        String blad = "";
        boolean valid = true;

        Pattern patternRok = Pattern.compile("^\\d{4}$");
        Matcher matcherRok = patternRok.matcher(rok);
        Pattern patternISBN = Pattern.compile("^\\d{13}$");
        Matcher matcherISBN = patternISBN.matcher(isbn);
        
        int rok_i = Integer.valueOf(rok);
        BigDecimal isbn_i = new BigDecimal(isbn);

        if(nazwa.equals("") || rok.equals("") || isbn.equals("") || wydawnictwo.getId() == 0){
            valid = false;
            blad = "Wypełnij wszystkie wymagane pola. ";
        }
        else if(!matcherRok.matches()){
            valid = false;
            blad = "Błędny rok. ";
        }
        else if(!matcherISBN.matches()){
            valid = false;
            blad = "Błędny ISBN. ";
        }
        if(valid){
            int id = db.autualizujKsiazka(idKsiazka, nazwa, rok_i, isbn_i, wydawnictwo.getId());
            if(id == 0){
                label.setText("Nie udało się dodać książki. Nieprawidłowy rok lub ISBN.");
            }
            else
                ksiazkaDodajUsunAutoraPanelInit(id, nazwa, rok, isbn, wydawnictwo.getNazwa());
        }
        else{
            label.setText(blad);
        }
    }

    private static String actionUsunKsiazka(Ksiazka ksiazka){
        String toReturn = "<html>Nie udało się usunąć książki.<br />Usuń najpierw jego egzemplarze.<html>";
        if(ksiazka == null){
            toReturn = "Wybierz książkę."; 
        }
        else if(db.usunKsiazka(ksiazka.getIdksiazka()))
            toReturn = "Książka usunięta.";
    
        return toReturn;
    }


    // Menu czytelnika
    
    private static void egzemplarzWyporzyczonyInfo(Egzemplarz egzemplarz, MenuCzytelnikaPanel panel){
        egzemplarzInfoWypozyczonePanel.restart();
        menuCzytelnikaButtons(egzemplarzInfoWypozyczonePanel);

        JLabel autor = new JLabel("Autor: " + egzemplarz.getAutorzy());
        JLabel dziedziny = new JLabel("Kategorie: " + egzemplarz.getDziedziny());
        JLabel rok = new JLabel("Rok wydania: " + egzemplarz.getRokwydania());
        JLabel isbn = new JLabel("ISBN: " + egzemplarz.getIsbn());
        JLabel wydawnictwo = new JLabel("Wydawnictwo: " + egzemplarz.getWydawnictwo());
        JLabel data = new JLabel("Termin oddania: " + egzemplarz.getDataPlanowanegoOddania());
        JButton buttonOddaj = new JButton("Oddaj");

        egzemplarzInfoWypozyczonePanel.getHeader().setText(egzemplarz.getTytul());
        autor.setBounds(465, 150 ,670, 30);
        dziedziny.setBounds(465, 190 ,670, 30);
        rok.setBounds(465, 230 ,270, 30);
        isbn.setBounds(465, 270 ,270, 30);
        wydawnictwo.setBounds(465,310, 670, 30);

        data.setBounds(465, 370 ,270, 30);
        buttonOddaj.setBounds(465, 410 ,270, 30);
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
        egzemplarzInfoWypozyczonePanel.add(wydawnictwo);
        egzemplarzInfoWypozyczonePanel.add(data);
        egzemplarzInfoWypozyczonePanel.add(buttonOddaj);

        egzemplarzInfoWypozyczonePanel.setVisible(true); panel.setVisible(false);
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
        JLabel wydawnictwo = new JLabel("Wydawnictwo: " + ksiazka.getWydawnictwo());
        JLabel dostepnosc = new JLabel("Dostępność: ");
        JButton wypozycz = new JButton("Wypozycz");
        JButton usun = new JButton("Usuń rezerwację");

        ksiazkaInfoZarezerwowanaPanel.getHeader().setText(ksiazka.getTytul());
        autor.setBounds(465, 150 ,670, 30);
        dziedziny.setBounds(465, 190 ,670, 30);
        rok.setBounds(465, 230 ,270, 30);
        isbn.setBounds(465, 270 ,270, 30);
        wydawnictwo.setBounds(465, 310 ,670, 30);
        dostepnosc.setBounds(465, 370 ,270, 30);
        
        wypozycz.setBounds(465, 420 ,270, 30);
        wypozycz.setFocusable(false);
        wypozycz.addActionListener(e -> {
            if(db.wypozyczEgzemplarz(zalogowanyCzytelnik.getId(),czyDostepna)){
                ksiazkaInfoZarezerwowanaPanel.setVisible(false);
                zarezerwowanePanelInit();
                zarezerwowanePanel.setVisible(true);
            }
        });

        usun.setBounds(465, 460 ,270, 30);
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
        ksiazkaInfoZarezerwowanaPanel.add(wydawnictwo);
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
        JLabel wydawnictwo = new JLabel("Wydawnictwo: " + ksiazka.getWydawnictwo());    ////!!!!!!!!!!
        JLabel dostepnosc = new JLabel("Dostępność: ");
        JButton wypozycz = new JButton("Wypozycz");
        JButton zarezerwuj = new JButton("Zarezerwuj");

        ksiazkaInfoKatalogPanel.getHeader().setText(ksiazka.getTytul());
        autor.setBounds(465, 150 ,670, 30);
        dziedziny.setBounds(465, 190 ,670, 30);
        rok.setBounds(465, 230 ,270, 30);
        isbn.setBounds(465, 270 ,270, 30);
        wydawnictwo.setBounds(465, 310 ,670, 30);
        dostepnosc.setBounds(465, 370 ,270, 30);
        wypozycz.setBounds(465, 410 ,270, 30);
        wypozycz.setFocusable(false);
        wypozycz.addActionListener(e -> {
            if(db.wypozyczEgzemplarz(zalogowanyCzytelnik.getId(),czyDostepna)){
                dostepnosc.setText("Wypozyczono");
                wypozycz.setEnabled(false);
                zarezerwuj.setEnabled(false);
                wypozyczonePanelInit();
            }
        });
        zarezerwuj.setBounds(465, 450, 270, 30);
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
        ksiazkaInfoKatalogPanel.add(wydawnictwo);
        ksiazkaInfoKatalogPanel.add(dostepnosc);
        ksiazkaInfoKatalogPanel.add(wypozycz);
        ksiazkaInfoKatalogPanel.add(zarezerwuj);

        ksiazkaInfoKatalogPanel.setVisible(true); panel.setVisible(false);
    }

    private static void EgzemplarzDodajUsun(Ksiazka ksiazka, MenuPracownikaPanel panel){
        egzemplarzDodajUsunPanel.restart();
        egzemplarzDodajUsunPanel.setLayout(null);
        egzemplarzDodajUsunPanel.setBounds(0, 0, 1200, 700);

        menuPracownikaButtons(egzemplarzDodajUsunPanel);

        egzemplarzDodajUsunPanel.getHeader().setText("Egzemplarze");
        egzemplarzDodajUsunPanel.getHeader().setBounds(180,20,1020,50);

        JLabel info = new JLabel("",SwingConstants.CENTER);
        DefaultListModel<Egzemplarz> egzemplarze = new DefaultListModel<>();
        JList<Egzemplarz> listaKsiazek = new JList<>(egzemplarze); 
        JScrollPane scrollPane = new JScrollPane();
        JLabel infoDodajUsun = new JLabel("",SwingConstants.CENTER);
        JButton buttonDodaj = new JButton("Dodaj egzemplarz");
        JButton buttonUsun = new JButton("Usuń wybrany egzemplarz");

        db.infoEgzemplarze(egzemplarze,ksiazka.getIdksiazka());    // pobranie danych
        
        if(egzemplarze.size()==0)
            info.setText("Brak książek.");

        info.setBounds(180,80,1020,50);
        info.setFont(new Font("Dialog",Font.BOLD,14));

        listaKsiazek.setFixedCellHeight(40);
        listaKsiazek.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listaKsiazek.clearSelection();

        DefaultListCellRenderer renderer = (DefaultListCellRenderer)listaKsiazek.getCellRenderer();
        renderer.setHorizontalAlignment(JLabel.CENTER);
        
        scrollPane.setViewportView(listaKsiazek);
        scrollPane.setBounds(300,120,750,400);

        infoDodajUsun.setBounds(180,560,1020,50);

        buttonDodaj.setBounds(300,530,370,25);
        buttonDodaj.setFocusable(false);
        buttonDodaj.addActionListener(e -> {
            if(db.dodajEgzemplarz(ksiazka.getIdksiazka())){
                infoDodajUsun.setText("Egzemplarz dodany.");
                db.infoEgzemplarze(egzemplarze, ksiazka.getIdksiazka());
            }
            else
                infoDodajUsun.setText("Nie udało się dodać egzemplarza.");
        });

        buttonUsun.setBounds(680,530,370,25);
        buttonUsun.setFocusable(false);
        buttonUsun.addActionListener(e -> {
            if(listaKsiazek.getSelectedValue() == null){
                infoDodajUsun.setText("Najpierw wybierz egzemplarz z listy.");
            }
            else{
                if(db.usunEgzemplarz(listaKsiazek.getSelectedValue().getIdE())){
                    infoDodajUsun.setText("Egzemplarz usunięty.");
                    db.infoEgzemplarze(egzemplarze, ksiazka.getIdksiazka());
                }
                else
                    infoDodajUsun.setText("Nie udało się usunąć egzemplarza. Jest wypozyczony.");
            }
        });

        egzemplarzDodajUsunPanel.add(info);
        egzemplarzDodajUsunPanel.add(infoDodajUsun);
        egzemplarzDodajUsunPanel.add(buttonDodaj);
        egzemplarzDodajUsunPanel.add(buttonUsun);
        egzemplarzDodajUsunPanel.add(scrollPane);

        egzemplarzDodajUsunPanel.setVisible(true); panel.setVisible(false);
    }
    
    // Bez logowania    

    private static void ksiazkaInfo(Ksiazka k){
        ksiazkaInfoPanel.setVisible(true); katalogKsiazekPanel.setVisible(false);
        ksiazkaInfoPanel.getTytul().setText(k.getTytul());
        ksiazkaInfoPanel.getAutor().setText("Autor: " + k.getAutorzy());
        ksiazkaInfoPanel.getRokWydania().setText("Rok wydania: " + String.valueOf(k.getRokwydania()));
        ksiazkaInfoPanel.getIsbn().setText("ISBN: " + String.valueOf(k.getIsbn()));
        ksiazkaInfoPanel.getDziedziny().setText("Kategorie: " + k.getDziedziny());
        ksiazkaInfoPanel.getWydawnictwo().setText("Wydawnictwo: " + k.getWydawnictwo());
    }


    // public static void main(String[] args){
    //     new MyFrame();
    // }
}
