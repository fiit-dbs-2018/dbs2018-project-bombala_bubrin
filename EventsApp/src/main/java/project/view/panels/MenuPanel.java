package project.view.panels;

import project.db_connection.DbConnectionBuilder;
import project.view.MyFrame;

import javax.swing.*;
import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MenuPanel extends JPanel {

    private final NavigationBar navigationBar;
    private final ContentPanel contentPanel;
    private final MyFrame parent;

    public MenuPanel(MyFrame myFrame) throws SQLException {

        parent = myFrame;
        setSize(800, 670);
        setLayout(null);

        navigationBar = new NavigationBar(this);
        add(navigationBar);

        contentPanel = new ContentPanel(this);
        add(contentPanel);


    }

    public void showPosts() {
        contentPanel.showPosts();
    }
}
