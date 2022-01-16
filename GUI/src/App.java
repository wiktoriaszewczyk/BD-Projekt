// import javax.swing.*;
// import javax.swing.border.Border;
// import javax.swing.plaf.BorderUIResource;

// import java.awt.Color;
// import java.awt.Font;
// import java.awt.event.*;

public class App /*implements ActionListener*/{
  // private static JFrame frame;
  // private static JPanel panel;
  // private static JLabel userlabel;
  // private static JLabel passwordlabel;
  // private static JTextField userText;
  // private static JPasswordField passwordText;
  // private static JButton button;
  // private static JLabel success;
  // private static DB db;
  public static void main(String[] args){
    // db = new DB(); 
    
    // frame = new JFrame();
    // frame.setTitle("Biblioteka - Projekt BD Wiktoria Szewczyk");
    // frame.setSize(1200,700);
    // frame.setLocationRelativeTo(null);  // center frame
    // frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
    // frame.getContentPane().setBackground(Color.green); // nie zmienia się jak jest panel

    new MyFrame();


    // panel = new JPanel();
    // frame.add(panel);
    // panel.setLayout(null);

    // userlabel = new JLabel("User");
    // userlabel.setBounds(10, 20, 80, 25);
    // panel.add(userlabel);
    
    // userText = new JTextField();
    // userText.setBounds(100, 20, 165, 25);
    // panel.add(userText);

    // passwordlabel = new JLabel("Password");
    // passwordlabel.setBounds(10, 50, 80, 25);
    // panel.add(passwordlabel);

    // passwordText = new JPasswordField();
    // passwordText.setBounds(100, 50, 165, 25);
    // panel.add(passwordText);

    // button = new JButton("Login");
    // button.setBounds(10, 80, 80, 25);
    // button.addActionListener(new App());
    // panel.add(button);

    // success = new JLabel("");
    // success.setBounds(10,110,300,25);
    // panel.add(success);

    // frame.setVisible(true);
  }

  // @Override
  // public void actionPerformed(ActionEvent e) {
  //   String user = userText.getText();
  //   String password = passwordText.getText();
  //   if(db.logPracownik(user, password)){
  //     success.setText("Login successful!");
  //   }
  //   else{
  //     success.setText("Incorrect passwordą!");
  //   }
    // panel.setVisible(false);
    // System.out.println("aaa");
  // }

}





/* LABELS

    Border border = BorderFactory.createLineBorder(Color.MAGENTA,3);
    JLabel label = new JLabel();
    label.setText("aaaa");
    label.setForeground(new Color(255,0,0));  // kolor czcionki
    label.setFont(new Font("MV Boli",Font.PLAIN,20)); // czcionka
    label.setBackground(Color.green);
    label.setOpaque(true);  // wyswietli kolor tła
    label.setBorder(border);
    label.setVerticalAlignment(JLabel.CENTER);
    label.setHorizontalAlignment(JLabel.CENTER);

    frame.add(label);
    frame.pack();   // ustawia wielkość frame na najmnijeszą możliwą mieszczącą elementy

*/


/* PANELS
  JLabel label = new JLabel("Hi");
    label.setVerticalAlignment(JLabel.TOP);

    frame.setLayout(null);
    JPanel redPanel = new JPanel();
    redPanel.setBackground(Color.red);
    redPanel.setBounds(0,0,250,250);

    JPanel bluePanel = new JPanel();
    bluePanel.setBackground(Color.blue);
    bluePanel.setBounds(250,0,250,250);

    JPanel greenPanel = new JPanel();
    greenPanel.setBackground(Color.green);
    greenPanel.setBounds(0,250,500,250);

    greenPanel.add(label);

    frame.add(redPanel);
    frame.add(bluePanel);
    frame.add(greenPanel);
*/