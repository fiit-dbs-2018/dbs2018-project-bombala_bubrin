package project.view;

import project.view.panels.LoginPanel;
import project.view.panels.MenuPanel;
import project.view.panels.RegistrationPanel;

import javax.swing.*;
import java.sql.SQLException;

public class MyFrame extends JFrame {

    private final LoginPanel loginPanel;
    private MenuPanel menuPanel;
    private RegistrationPanel registrationPanel;

    public MyFrame() throws SQLException {
        setSize(805, 670);
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        setLayout(null);

        loginPanel = new LoginPanel(this);

        add(loginPanel);

        registrationPanel = new RegistrationPanel(this);
        add(registrationPanel);
        registrationPanel.setVisible(false);
        try {
            menuPanel = new MenuPanel(this);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        add(menuPanel);
        menuPanel.setVisible(false);

        refresh();
    }

    private void refresh() {
        repaint();
        revalidate();
    }

    public void openAfterLogin() {
        hideAllPanels();

        menuPanel.setVisible(true);
        menuPanel.showPosts();
        refresh();
    }

    public void openAfterReg(){
        hideAllPanels();

        registrationPanel.setVisible(true);
    }

    public void openAfterRegistered(){
        hideAllPanels();
        loginPanel.setVisible(true);
    }

    private void hideAllPanels() {
        loginPanel.setVisible(false);
        registrationPanel.setVisible(false);
    }

    public void closeAfterLogin() {
        JOptionPane.showMessageDialog(this, "Login failed");
    }
}
