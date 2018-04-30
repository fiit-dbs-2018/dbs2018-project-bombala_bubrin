package project.view.panels;

import project.db_connection.DbConnectionBuilder;
import project.model.Post;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PostView extends JPanel {

    //    private JPanel postPanel;
    private Post post;
    private JButton like;
    private JButton dislike;
    private JLabel likeCount;

    public PostView(Post post) {
        this.post = post;
        setLayout(null);
        setSize(650, 200);
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        JTextArea eventName = new JTextArea(post.getEventName());
        eventName.setBounds(0, 0, 650, 40);
        eventName.setFont(new Font("Calibri", Font.BOLD, 19));
        eventName.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        this.add(eventName);
        JTextArea postText = new JTextArea(post.getText());
        postText.setBounds(0, 40, 650, 100);
        postText.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        this.add(postText);

        JPanel likes = new JPanel();
        likes.setLayout(null);
        likes.setBounds(0, 140, 650, 60);
        likes.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        this.add(likes);

        like = new JButton("LIKE");
        likes.add(like);
        dislike = new JButton("DISLIKE");
        likes.add(dislike);
        likeCount = new JLabel(Integer.toString(post.getLikeCount()));
        likes.add(likeCount);
        drawButtons(post.getOpinion());

        like.setBounds(10, 10, 130, 40);
        dislike.setBounds(150, 10, 130, 40);
        likeCount.setBounds(290, 10, 40, 40);

        like.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int op = post.getOpinion();
                if (op == 1) {
                    opinionClicked(0);
                } else {
                    opinionClicked(1);
                }
            }
        });
        dislike.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int op = post.getOpinion();
                if (op == -1) {
                    opinionClicked(0);
                } else {
                    opinionClicked(-1);
                }
            }
        });

    }

    private int opinionClicked(int opinion) {
        int op = DbConnectionBuilder.getInstance().likeClick(post.getId(), opinion);
        post.setOpinion(op);
        drawButtons(opinion);
        return op;
    }

    private void drawButtons(int opinion) {
        if (opinion == 1) {
            like.setFont(like.getFont().deriveFont(Font.BOLD));
            dislike.setFont(like.getFont().deriveFont(Font.PLAIN));
        } else if (opinion == 0) {
            like.setFont(like.getFont().deriveFont(Font.PLAIN));
            dislike.setFont(like.getFont().deriveFont(Font.PLAIN));
        } else if (opinion == -1) {
            like.setFont(like.getFont().deriveFont(Font.PLAIN));
            dislike.setFont(like.getFont().deriveFont(Font.BOLD));
        }
        likeCount.setText(Integer.toString(post.getLikeCount()));
    }


}
