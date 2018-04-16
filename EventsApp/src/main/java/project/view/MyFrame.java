package project.view;

import project.view.panels.LoginPanel;
import project.view.panels.MenuPanel;

import javax.swing.*;
import java.sql.SQLException;

public class MyFrame extends JFrame {

    private final LoginPanel loginPanel;
    private MenuPanel menuPanel;

    public MyFrame() throws SQLException {
        setSize(800, 600);
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        setLayout(null);

        loginPanel = new LoginPanel(this);

        add(loginPanel);

        refresh();
    }

    private void refresh() {
        repaint();
        revalidate();
    }

    public void openAfterLogin() {
        hideAllPanels();
        try {
            menuPanel = new MenuPanel(this);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        add(menuPanel);
        menuPanel.setVisible(true);
    }

    private void hideAllPanels() {
        loginPanel.setVisible(false);

    }

    public void closeAfterLogin() {
        JOptionPane.showMessageDialog(this, "Login failed");
    }
}
