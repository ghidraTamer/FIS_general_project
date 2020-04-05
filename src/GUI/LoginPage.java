package GUI;

import javax.swing.*;
import java.awt.event.*;
import java.sql.SQLException;

import Encryption.MD5;
import sqlconnection.*;

public class LoginPage extends JDialog {
    private JPanel contentPane;
    private JButton loginButton;
    private JButton registerButton;
    private JTextField usernameField;
    private JPasswordField passwordField1;
    private JLabel loginLabel;
    private JPanel textFieldPanel;
    private SQLiteJDBC sqlitejdbc = new SQLiteJDBC();
    private MD5 md5 = new MD5();

    public LoginPage()  {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(loginButton);

        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String userName = usernameField.getText();
                char[] password = passwordField1.getPassword();

                String sql_username_check = "SELECT CASE WHEN EXISTS (" +
                        "SELECT username " +
                        "FROM REGISTRATION " +
                        "WHERE username = '" + userName + "'" +
                        ") " +
                        "THEN CAST(1 AS BIT) " +
                        "ELSE CAST(0 AS BIT) END ";

                String char_to_string_password = md5.getMd5(String.valueOf(password));

                String sql_password_check = "SELECT CASE WHEN EXISTS (" +
                        "SELECT password " +
                        "FROM REGISTRATION " +
                        "WHERE password = '" + char_to_string_password + "'" +
                        ") " +
                        "THEN CAST(1 AS BIT) " +
                        "ELSE CAST(0 AS BIT) END ";

                if(sqlitejdbc.selectStatement("test.db",sql_username_check).equals("1") &&
                sqlitejdbc.selectStatement("test.db",sql_password_check).equals("1")) {
                    System.out.println("LOGGED IN");
                }



            }
        });

        registerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                RegisterPage rp = new RegisterPage();
                rp.startRegisterPage();
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

    public static void startLoginPage () {
        LoginPage dialog = new LoginPage();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
