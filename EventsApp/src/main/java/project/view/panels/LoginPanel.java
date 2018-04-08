package project.view.panels;

import project.db_connection.DbConnectionBuilder;
import project.view.MyFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginPanel extends JPanel {

    private final JTextField mailTextField;
    private final JPasswordField passwordField;
    private final MyFrame parent;

    public LoginPanel(MyFrame myFrame) {
        parent = myFrame;
        setSize(800, 600);
        setLayout(null);

        mailTextField = new JTextField("Apostolos.Knee0@email.com");
        mailTextField.setBounds(20, 20, 200, 30);
        add(mailTextField);

        passwordField = new JPasswordField("KFyD07");
        passwordField.setBounds(20, 70, 200, 30);
        add(passwordField);

        JButton loginButton = new JButton("login");
        loginButton.setBounds(20, 120, 200, 30);
        add(loginButton);
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loginClicked();
            }
        });

    }

    private void loginClicked() {
        String username = mailTextField.getText();
        String password = String.valueOf(passwordField.getPassword());

        if (DbConnectionBuilder.getInstance().userExist(username, password)) {
            proceedAfterLogin();
        } else {
            showLoginError();
        }
    }

    private void showLoginError() {
        parent.closeAfterLogin();
    }

    private void proceedAfterLogin() {
        parent.openAfterLogin();
    }
}
