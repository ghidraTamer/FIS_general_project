package project_interface;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Project1 implements  ActionListener{


    private static JLabel userLabel;
    private static JTextField userText;
    private static JLabel passwordLabel;
    private static JPasswordField passwordText;
    private static JButton button;
    private static JButton button2;
    private static JLabel succes;

    public static void main(String[] args) {
        // TODO Auto-generated method stub


        JPanel panel =new JPanel();// the layout of the frame
        JFrame frame =new JFrame(); //rama
        //acestea nu se fac globale

        frame.setSize(450, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true); // the window
        //trebuie sa adaugam panel in frame
        frame.add(panel);

        panel.setLayout(null);
        userLabel= new JLabel("User"); // un fel de eticheta
        userLabel.setBounds(10,20,80,25);
        panel.add(userLabel);

        passwordLabel= new JLabel("Password");
        passwordLabel.setBounds(10,40,80,25);
        panel.add(passwordLabel);

        userText = new  JTextField(20);
        userText.setBounds(100,20,165,25);
        panel.add(userText);

        passwordText= new JPasswordField(20);
        passwordText.setBounds(100,50,165,25);
        panel.add(passwordText);

        button =new JButton("Login");
        button.setBounds(10,80,80,25);
        panel.add(button);
        button.addActionListener(new Project1());


        button2 =new JButton("Register");
        button2.setBounds(10,150,100,25);
        panel.add(button2);
        button2.addActionListener(new Project1());


        succes =new JLabel("");
        succes.setBounds(10,110,300,25);
        panel.add(succes);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        //System.out.println("Button clicked");
        String user =userText.getText();
        String password =passwordText.getText();
        System.out.println(user + ", " +password);

        if(user.equals("Roger") && password.equals("stardusty12")) {
            succes.setText("Login successful! ");
        }
        else {
            succes.setText("Login unsuccessful!");
        }
    }
}
