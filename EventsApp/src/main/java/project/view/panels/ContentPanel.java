package project.view.panels;

import javax.swing.*;
import java.awt.*;

public class ContentPanel extends JPanel {

    private PostsPanel postsPanel;
    private ProfilPanel profilPanel;
    private Event event;
    private MenuPanel parent;

    public ContentPanel(MenuPanel menuPanel) {

        parent = menuPanel;
        this.setBounds(150,0, 650,630);
        setLayout(null);
        setBackground(Color.BLACK);
        postsPanel = new PostsPanel(this);
        add(postsPanel);

        profilPanel = new ProfilPanel(this);
        add(profilPanel);
        profilPanel.setVisible(false);

        event = new Event(this);
        add(event);
        event.setVisible(false);

    }


    public void showPosts() {
        hideAllChildren();
        postsPanel.setVisible(true);
        postsPanel.showPosts(0);
    }

    public void showProfile(){
        hideAllChildren();
        profilPanel.showProfile();
        profilPanel.setVisible(true);

    }

    private void hideAllChildren() {
        postsPanel.setVisible(false);
        profilPanel.setVisible(false);
        event.setVisible(false);

    }

    public void logout() {
        parent.logout();
    }

    public void showEvents() {
        hideAllChildren();
        event.setVisible(true);
    }

    public void removeEvents() {

        event.removeEvents();
    }
}
