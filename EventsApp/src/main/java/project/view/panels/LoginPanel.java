package project.view.panels;

import project.db_connection.DbConnectionBuilder;
import project.view.MyFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginPanel extends JPanel {

    private final JTextField mailTextField;
    private final JPasswordField passwordField;
    private final MyFrame parent;


    public LoginPanel(MyFrame myFrame) {
        parent = myFrame;
        setSize(800, 600);
        setLayout(null);

        mailTextField = new JTextField("Melisent.Hoene2@email.com");
        mailTextField.setBounds(20, 20, 200, 30);
        add(mailTextField);

        passwordField = new JPasswordField("YO8zpt");
        passwordField.setBounds(20, 70, 200, 30);
        add(passwordField);

        JButton loginButton = new JButton("login");
        loginButton.setBounds(20, 120, 200, 30);
        add(loginButton);
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    loginClicked();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }

        });

        JButton regButton = new JButton("registration");
        regButton.setBounds(20,160,200,30);
        add(regButton);
        regButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registerClicked();
            }
        });
    }

    private void loginClicked() throws SQLException {
        String username = mailTextField.getText();
        String password = String.valueOf(passwordField.getPassword());
        if(DbConnectionBuilder.getInstance().userExist(username, password)) {
            proceedAfterLogin();
        } else {
            showLoginError();
        }
    }

    private void registerClicked() {
        parent.openAfterReg();
    }

    private void showLoginError() {
        parent.closeAfterLogin();
    }

    private void proceedAfterLogin() {
        parent.openAfterLogin();
    }
}
