package paketGUI;

import paketClasses.Login;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;

//import paketClasses.DBZugriff;

public class LoginFenster extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel jContentPane = null;
    private JLabel lbLogin = null;
    private JLabel lbBenutzer = null;
    private JLabel lbPasswort = null;
    private JTextField tfBenutzer = null;
    private JPasswordField pfPasswort = null;
    private JButton btAnmelden = null;
    private Login objLogin;
    ResultSet objErgebnis = null;
    private JLabel lbBackground = null;

    public LoginFenster() {
        super();
        initialize();
    }

    private void initialize() {
        this.setSize(389, 340);
        this.setContentPane(getJContentPane());
        this.setTitle("Praktikumstool");
        this.setLocationRelativeTo(null);
    }

    private JPanel getJContentPane() {
        if (jContentPane == null) {
            lbBackground = new JLabel();
            lbBackground.setBounds(new Rectangle(1, 0, 382, 316));
            lbBackground.setText("");
            lbPasswort = new JLabel();
            lbPasswort.setBounds(new Rectangle(190, 90, 70, 20));
            lbPasswort.setFont(new Font("Dialog", Font.BOLD, 14));
            lbPasswort.setDisplayedMnemonic(KeyEvent.VK_UNDEFINED);
            lbPasswort.setText("Passwort:");
            lbBenutzer = new JLabel();
            lbBenutzer.setBounds(new Rectangle(30, 90, 70, 20));
            lbBenutzer.setFont(new Font("Dialog", Font.BOLD, 14));
            lbBenutzer.setText("Benutzer:");
            lbLogin = new JLabel();
            lbLogin.setBounds(new Rectangle(30, 40, 60, 24));
            lbLogin.setFont(new Font("Dialog", Font.BOLD, 20));
            lbLogin.setText("Login");
            jContentPane = new JPanel();
            jContentPane.setLayout(null);
            jContentPane.add(lbLogin, null);
            jContentPane.add(lbBenutzer, null);
            jContentPane.add(lbPasswort, null);
            jContentPane.add(getTfBenutzer(), null);
            jContentPane.add(getPfPasswort(), null);
            jContentPane.add(getBtAnmelden(), null);
            jContentPane.add(lbBackground, null);
        }
        return jContentPane;
    }

    private JTextField getTfBenutzer() {
        if (tfBenutzer == null) {
            tfBenutzer = new JTextField();
            tfBenutzer.setBounds(new Rectangle(30, 116, 120, 20));
        }
        return tfBenutzer;
    }

    private JPasswordField getPfPasswort() {
        if (pfPasswort == null) {
            pfPasswort = new JPasswordField();
            pfPasswort.setBounds(new Rectangle(190, 116, 120, 20));
        }
        return pfPasswort;
    }

    private JButton getBtAnmelden() {
        if (btAnmelden == null) {
            btAnmelden = new JButton();
            btAnmelden.setBounds(new Rectangle(30, 166, 120, 34));
            btAnmelden.setText("Anmelden");
            btAnmelden.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent e) {
                    System.out.println("actionPerformed()"); //

                    String mBenutzerEingabe = tfBenutzer.getText();
                    String mPasswortEingabe = String.valueOf(pfPasswort.getPassword());
                    objLogin = new Login();

                    loginDatenLesen(mBenutzerEingabe);
                    zuweisen();
                    boolean mAnmeldenOK	= checkPassword(mPasswortEingabe);


                }
            });
        }
        return btAnmelden;
    }

    private void loginDatenLesen(String mBenutzerEingabe)
    {
        try
        {
            String mSQL;
          //  DBZugriff objZugriff = new DBZugriff();
            mSQL = "SELECT Benutzername, Passwort FROM Login WHERE Benutzername = '"+mBenutzerEingabe+"'";
         //   objZugriff.oeffnen();
         //   objErgebnis = objZugriff.lesen(mSQL);
        }
        catch (Exception e)
        {
            System.out.println("Suche fehlgeschlagen !");
            JOptionPane.showMessageDialog(null, "Falscher Benutzername");
        }
    }

    public void zuweisen()
    {
        try
        {
            objErgebnis.next();
            objLogin.setBenutzername(objErgebnis.getString("Benutzername"));
            objLogin.setPasswort(objErgebnis.getString("Passwort"));
        }
        catch (Exception e)
        {
            System.out.println("Zuweisung Ergebnisobjekt fehlgeschlagen");
            JOptionPane.showMessageDialog(null, "Falscher Benutzername !");
        }
    }

    public boolean checkPassword(String mPasswortEingabe)
    {
        boolean mAnmelden;
        if(mPasswortEingabe == objLogin.getPasswort())
        {
            mAnmelden = true;
        }
        else
        {
            mAnmelden = false;
        }
        return mAnmelden;
    }

}