package project.view.panels;

import project.view.MyFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class NavigationBar extends JPanel {

    private final MenuPanel parent;

    public NavigationBar(MenuPanel menuPanel){

        parent = menuPanel;
        this.setBounds(0,0, 150,600);
        setLayout(null);
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

        home.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                goHome();
            }
        });

        profile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showProfile();
            }
        });

        logout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                logout();
            }
        });

        events.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showEvents();
            }
        });
    }

    private void showEvents() {
        parent.showEvents();
    }

    private void showProfile() {
        parent.showProfile();
    }

    private void logout() {
        parent.logout();
    }


    private void goHome(){
        parent.showPosts();
    }





}
