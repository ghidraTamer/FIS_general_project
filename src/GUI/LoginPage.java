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

                String sql_select_username = "SELECT username FROM REGISTRATION WHERE username = '" + userName + "'";
                String char_to_string_password = md5.getMd5(String.valueOf(password));
                String sql_select_password = "SELECT password FROM REGISTRATION WHERE password = '" + char_to_string_password + "'";

                System.out.println(userName);
                try {
                   sqlitejdbc.selectStatement("test.db",sql_select_username);
                   sqlitejdbc.selectStatement("test.db,",sql_select_password);

                } catch (SQLException ex) {
                    //ex.printStackTrace();
                    System.out.println("ERROR ACCOUNT NOT FOUND");
                } catch (ClassNotFoundException ex) {
                    ex.printStackTrace();
                }

            }
        });

        registerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
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
