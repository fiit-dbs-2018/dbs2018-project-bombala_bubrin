package project.view.panels;

import project.db_connection.DbConnectionBuilder;
import project.view.MyFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RegistrationPanel extends JPanel {

    private final JTextField name;
    private final JTextField surname;
    private final JTextField mailTextField;
    private final JPasswordField passwordField;
    private final ButtonGroup sex;
    private final MyFrame parent;
    private final JRadioButton unspecified;
    private final JRadioButton female;
    private final JRadioButton male;

    public RegistrationPanel(MyFrame myFrame) {
        parent = myFrame;
        setSize(800, 600);
        setLayout(null);
        name = new JTextField("name");
        name.setBounds(20, 20, 200, 30);
        add(name);
        surname = new JTextField("surname");
        surname.setBounds(20, 60, 200, 30);
        add(surname);
        passwordField = new JPasswordField("password");
        passwordField.setBounds(20, 100, 200, 30);
        add(passwordField);
        mailTextField = new JTextField("email");
        mailTextField.setBounds(20, 140, 200, 30);
        add(mailTextField);
         male = new JRadioButton("male");
         female = new JRadioButton("female");
         unspecified = new JRadioButton("unspecified");
        sex = new ButtonGroup();
        male.setBounds(40, 180, 100, 40);
        female.setBounds(40, 230, 100, 40);
        unspecified.setBounds(40, 280, 100, 40);
        unspecified.setSelected(true);
        sex.add(male);
        sex.add(female);
        sex.add(unspecified);
        add(male);
        add(female);
        add(unspecified);

        JButton registerButton = new JButton("register");
        registerButton.setBounds(20, 330, 200, 30);
        add(registerButton);
//        registerButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                    registerClicked();
//
//        });
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registerClicked();
            }
        });


    }



    private void registerClicked() {
        int sex;
        String name = this.name.getText();
        String surname = this.surname.getText();
        String password = String.valueOf(passwordField.getPassword());
        String email = this.mailTextField.getText();
        if(male.isSelected()){
            sex = 1;
        }else if(female.isSelected()){
            sex = 2;
        }else {
            sex = 0;
        }
        DbConnectionBuilder.getInstance().registerUser(name,surname,password,email,sex);
        proceedAfterRegistration();
//        ResultSet register;
//        login = DbConnectionBuilder.getInstance().userExist(username, password);
//        login.beforeFirst();
//        if (login.next()) {
//            proceedAfterLogin();
//        } else {
//            showLoginError();
//        }
    }

    private void proceedAfterRegistration() {
        parent.openAfterRegistered();
    }

}
