package project.view.panels;

import javax.swing.*;
import java.awt.*;

public class ContentPanel extends JPanel {

    private  PostsPanel postsPanel;
    private MenuPanel parent;

    public ContentPanel(MenuPanel menuPanel) {

        parent = menuPanel;
        this.setBounds(150,0, 650,630);
        setLayout(null);
        setBackground(Color.BLACK);
        postsPanel = new PostsPanel(this);
        add(postsPanel);

    }


    public void showPosts() {
        hideAllChildren();
        postsPanel.setVisible(true);
        postsPanel.showPosts(0);
    }

    private void hideAllChildren() {
        postsPanel.setVisible(false);
    }
}
