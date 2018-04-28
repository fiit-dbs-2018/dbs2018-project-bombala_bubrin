package project.view.panels;

import project.view.MyFrame;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class NavigationBar extends JPanel {

    private final MenuPanel parent;

    public NavigationBar(MenuPanel menuPanel){

        parent = menuPanel;
        this.setBounds(0,0, 150,600);
        setLayout(null);
//        this.setBackground(Color.RED);
        JButton home = new JButton("Home");
        menuPanel.add(home);
        JButton profile = new JButton("Profile");
        menuPanel.add(profile);
        JButton events = new JButton("Events");
        menuPanel.add(events);
        JButton logout = new JButton("Logout");
        menuPanel.add(logout);

        home.setBounds(10,10,130,40);
        profile.setBounds(10,60,130,40);
        events.setBounds(10,110,130,40);
        logout.setBounds(10,590,130,40);

    }




}
