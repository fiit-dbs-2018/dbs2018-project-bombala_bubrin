package project.view.panels;

import project.db_connection.DbConnectionBuilder;
import project.model.Post;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class PostsPanel extends JPanel {

    private JPanel postsPanel;
    private JPanel pagination;
    private int actualPosition;

    public PostsPanel(ContentPanel contentPanel) {
        setSize(650, 630);
        setBackground(Color.RED);
        setLayout(null);
        setBorder(BorderFactory.createLineBorder(Color.RED));


        postsPanel = new JPanel();
//        postsPanel.setBorder(BorderFactory.createEmptyBorder());
        postsPanel.setSize(650,600);
        postsPanel.setLayout(new BoxLayout(postsPanel, BoxLayout.Y_AXIS));
        postsPanel.setBackground(Color.DARK_GRAY);//
        add(postsPanel);



        pagination = new JPanel();
        pagination.setBounds(0,600,650,30);
        pagination.setLayout(null);
        pagination.setBackground(Color.DARK_GRAY);
        add(pagination);
        pagination.setVisible(true);

        JButton next = new JButton("Next");
        pagination.add(next);
        next.setBounds(540,0,100,20);

        JButton previous = new JButton("Previous");
        pagination.add(previous);
        previous.setBounds(10,0,100,20);

        next.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showPosts(actualPosition + 1);
            }
        });

        previous.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showPosts(actualPosition - 1);
            }
        });


    }



    private void drawPost(Post post) {
        PostView postPanel = new PostView(post);
        postsPanel.add(postPanel);
        postsPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        setVisible(true);
    }


    public void showPosts(int actualPosition) {
        if(actualPosition < 0){
            actualPosition = 0;
        }
        this.actualPosition = actualPosition;
        clearView();
        List<Post> posts = DbConnectionBuilder.getInstance().selectPosts(DbConnectionBuilder.getInstance().getUserId(), actualPosition);
        for (Post post : posts) {
            drawPost(post);
        }
    }

    private void clearView() {
        postsPanel.removeAll();
        postsPanel.repaint();
        postsPanel.revalidate();
    }
}
