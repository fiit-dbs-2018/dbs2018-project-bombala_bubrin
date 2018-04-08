package project.view;

import project.view.panels.LoginPanel;
import project.view.panels.MenuPanel;

import javax.swing.*;

public class MyFrame extends JFrame {


    private final LoginPanel loginPanel;
    private final MenuPanel menuPanel;

    public MyFrame() {
        setSize(800, 600);
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        setLayout(null);

        loginPanel = new LoginPanel(this);
        menuPanel = new MenuPanel(this);

        add(loginPanel);
        add(menuPanel);

        refresh();
    }

    private void refresh() {
        repaint();
        revalidate();
    }

    public void openAfterLogin() {
        hideAllPanels();
        menuPanel.setVisible(true);
    }

    private void hideAllPanels() {
        loginPanel.setVisible(false);
        menuPanel.setVisible(false);
    }

    public void closeAfterLogin() {
        JOptionPane.showMessageDialog(this, "Login failed");
    }
}
