import javax.naming.AuthenticationNotSupportedException;
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
    private static MenuPracownikaPanel menuPracownikaPanel;
    private static MenuPracownikaPanel PracownikPanel;  // dodawanie usuwanie pracownika
    private static MenuPracownikaPanel AutorPanel;  // dodawanie usuwanie autora


    private static JPanel zarejestrujCzytelnikPanel;
    private static JPanel katalogKsiazekPanel;

    private static KsiazkaPanel ksiazkaInfoPanel;

    MyFrame(){
        this.setTitle("Biblioteka - Projekt BD Wiktoria Szewczyk");
        this.setSize(1200,700);
        this.setLocationRelativeTo(null);  // center frame
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);

        startPanelInit();

        loginPracownikPanelInit();
        zarejestrujPracownikPanelInit();
        menuPracownikaPanelInit();
        dodajAutoraPanelInit();

        zarejestrujCzytelnikPanelInit();
        katalogKsiazekPanelInit();
        ksiazkaInfoPanelInit();

        this.add(startPanel);
        this.add(loginPracownikPanel);
        this.add(menuPracownikaPanel);
        this.add(PracownikPanel);
        this.add(AutorPanel);

        this.add(zarejestrujCzytelnikPanel);
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
            PracownikPanel.setVisible(true);
        });

        panel.getAutorButton().addActionListener(e ->{
            panel.setVisible(false);
            AutorPanel.setVisible(true);
        });
    }

    private static void menuPracownikaPanelInit(){
        menuPracownikaPanel = new MenuPracownikaPanel();
        menuPracownikaButtons(menuPracownikaPanel);

    }

    private static void zarejestrujPracownikPanelInit(){
        PracownikPanel = new MenuPracownikaPanel();
        PracownikPanel.setLayout(null);
        PracownikPanel.setBounds(0, 0, 1200, 700);

        menuPracownikaButtons(PracownikPanel);
        PracownikPanel.getHeader().setText("Dodaj Pracownika");

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

        PracownikPanel.add(imieLabel);
        PracownikPanel.add(imie);
        PracownikPanel.add(nazwiskoLabel);
        PracownikPanel.add(nazwisko);
        PracownikPanel.add(emailLabel);
        PracownikPanel.add(email);
        PracownikPanel.add(loginLabel);
        PracownikPanel.add(login);
        PracownikPanel.add(haslo1Label);
        PracownikPanel.add(haslo1);
        PracownikPanel.add(haslo2Label);
        PracownikPanel.add(haslo2);
        PracownikPanel.add(buttonRejestracja);
        PracownikPanel.add(infoLabel);

        PracownikPanel.setVisible(false);
    }

    private static void dodajAutoraPanelInit(){
        AutorPanel = new MenuPracownikaPanel();
        AutorPanel.setLayout(null);
        AutorPanel.setBounds(0, 0, 1200, 700);

        menuPracownikaButtons(AutorPanel);

        AutorPanel.getHeader().setText("Autorzy");

        JLabel dodajAutoraLabel = new JLabel("Dodaj autora",SwingConstants.CENTER);
        JLabel imieLabel = new JLabel("Imie* ");
        JTextField imie = new JTextField();
        JLabel nazwiskoLabel = new JLabel("Nazwisko* ");
        JTextField nazwisko = new JTextField();
        JButton buttonDodaj = new JButton("Dodaj");
        JLabel infoDodajLabel = new JLabel("* pole wymagane");

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
        imieLabel.setBounds(300, 230, 80, 25);
        imie.setBounds(400, 230, 170, 25);

        nazwiskoLabel.setBounds(300, 270, 80, 25);
        nazwisko.setBounds(400, 270, 170, 25);

        buttonDodaj.setBounds(300, 320 ,270, 30);
        buttonDodaj.setFocusable(false);
        buttonDodaj.addActionListener(e -> {
            infoDodajLabel.setText(actionDodajAutor(imie.getText(), nazwisko.getText())); 
            db.infoAutor(autorzy);
        });

        infoDodajLabel.setBounds(300, 360, 270, 30);


        usunAutoraLabel.setFont(new Font("TimesRoman",Font.BOLD,20));
        usunAutoraLabel.setBounds(700, 150, 270, 25);

        szukaj.setBounds(700,230,170,25);
        buttonSzukaj.setBounds(890,230,80,25);
        buttonSzukaj.addActionListener(e -> {db.infoAutor(autorzy,szukaj.getText());});

        listaAutor.setFixedCellHeight(40);
        // listaAutor.addListSelectionListener(e -> ksiazkaInfo(listaAutor.getSelectedValue()));
        listaAutor.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        scrollPane.setViewportView(listaAutor);
        scrollPane.setBounds(700,270,270,250);

        buttonUsun.setBounds(700, 540 ,270, 30);
        buttonUsun.setFocusable(false);
        buttonUsun.addActionListener(e -> {
            infoUsunLabel.setText(actionUsunAutor(listaAutor.getSelectedValue()));
            db.infoAutor(autorzy);
        });

        infoUsunLabel.setBounds(700, 580, 270, 50);



        AutorPanel.add(dodajAutoraLabel);
        AutorPanel.add(imieLabel);
        AutorPanel.add(imie);
        AutorPanel.add(nazwiskoLabel);
        AutorPanel.add(nazwisko);
        AutorPanel.add(buttonDodaj);
        AutorPanel.add(infoDodajLabel);

        AutorPanel.add(usunAutoraLabel);
        AutorPanel.add(szukaj);
        AutorPanel.add(buttonSzukaj);
        AutorPanel.add(scrollPane);

        AutorPanel.add(buttonUsun);
        AutorPanel.add(infoUsunLabel);



        AutorPanel.setVisible(false);
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
        if(db.logowanie(kto, user, password)){
            // bladLogowaniaCztyelnik.setText("Logowanie powiodło się!");
            toReturn = "";
            if(kto){
                zalogowany_pracownik = true;
                loginPracownikPanel.setVisible(false);
                menuPracownikaPanel.setVisible(true);
            }
            else{
                zalogowany_czytelnik = true;
                // startPanel.setVisible(false);
                // menuCzytelnikPanel.setVisible(true);
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
