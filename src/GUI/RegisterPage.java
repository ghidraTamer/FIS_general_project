package GUI;

import javax.swing.*;
import java.awt.event.*;
import java.sql.SQLException;
import Encryption.*;
import sqlconnection.*;

public class RegisterPage extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton registerButton;
    private JTextField usernameField;
    private JTextField lastnameField;
    private JTextField emailfield;
    private JPasswordField passwordField;
    private JTextField statusField;
    private JLabel firstnameLabel;
    private JLabel lastnameLabel;
    private JLabel emailLabel;
    private JLabel usernameLabel;
    private JLabel passwordLabel;
    private JTextField firstnameField;
    private SQLiteJDBC sqlitejdbc = new SQLiteJDBC();
    private MD5 md5 = new MD5();

    public RegisterPage() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);


        registerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                //variables are for checking if the email/username already exists
                //the SQL statement will return "1" the e/u already exists
                String checkgetEmail = "1";
                String checkgetUsername = "1";

                String username = usernameField.getText();
                String email = emailfield.getText();
                String firstname = firstnameField.getText();
                String lastname = lastnameField.getText();
                char[] password = passwordField.getPassword();
                String password_hashed = md5.getMd5(String.valueOf(password));


                String sql_email_check = "SELECT CASE WHEN EXISTS (" +
                        "SELECT email " +
                        "FROM REGISTRATION " +
                        "WHERE email = '" + email + "'" +
                        ") " +
                        "THEN CAST(1 AS BIT) " +
                        "ELSE CAST(0 AS BIT) END ";

                String sql_username_check = "SELECT CASE WHEN EXISTS (" +
                        "SELECT username " +
                        "FROM REGISTRATION " +
                        "WHERE username = '" + username + "'" +
                        ") " +
                        "THEN CAST(1 AS BIT) " +
                        "ELSE CAST(0 AS BIT) END ";

                //update the values for existance checking
                checkgetEmail = sqlitejdbc.selectStatement("test.db",sql_email_check);
                checkgetUsername = sqlitejdbc.selectStatement("test.db",sql_username_check);

                
                if(checkgetEmail.equals("0") && checkgetUsername.equals("0")) {
                    sqlitejdbc.insertRegistration(firstname, lastname, email, username, password_hashed);
                    statusField.setText("ACCOUNT CREATED");
                }
                else {
                    if(checkgetEmail.equals("1") && checkgetUsername.equals("1")) {
                        statusField.setText("Email and Username already exist");
                    }
                    else if(checkgetEmail.equals("1")) {
                        statusField.setText("Email already exists");
                    }
                    else if(checkgetUsername.equals("1")) {
                        statusField.setText("Username already exists");
                    }
                }
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void onOK() {
        // add your code here
        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    public static void startRegisterPage() {
        RegisterPage dialog = new RegisterPage();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
