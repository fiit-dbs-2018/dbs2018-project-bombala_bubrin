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

    public PostView(Post post){
        this.post = post;
        setLayout(null);
        setSize(650, 200);
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        JTextArea eventName = new JTextArea(post.getEventName());
        eventName.setBounds(0,0,650,40);
        eventName.setFont(new Font("Calibri", Font.BOLD, 19));
        eventName.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        this.add(eventName);
        JTextArea postText = new JTextArea(post.getText());
        postText.setBounds(0,40,650,100);
        postText.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        this.add(postText);

        JPanel likes = new JPanel();
        likes.setLayout(null);
        likes.setBounds(0,140,650,60);
        likes.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        this.add(likes);

        JButton like = new JButton("LIKE");
        likes.add(like);
        JButton dislike = new JButton("DISLIKE");
        likes.add(dislike);
        JLabel likeCount = new JLabel(Integer.toString(post.getLikeCount()));
        likes.add(likeCount);

        like.setBounds(10,10,130,40);
        dislike.setBounds(150,10,130,40);
        likeCount.setBounds(290,10,40,40);

        like.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(opinionClicked(1)){
                    like.setFont(like.getFont().deriveFont(Font.BOLD));
                }
            }
        });
        dislike.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(opinionClicked(-1)){
                    dislike.setFont(dislike.getFont().deriveFont(Font.BOLD));
                }
            }
        });


//        this.setPreferredSize(new Dimension(200, 200));
//
//        JLabel eventName = new JLabel("");
//        setSize(700, 200);
//        eventName.setFont(new Font("Calibri", Font.BOLD, 19));
//        eventName.setForeground(Color.BLACK);
//        this.add(eventName, BorderLayout.PAGE_START);
//
//        JLabel opinion = new JLabel(""+("tu"+""));
//        opinion.setBackground(Color.blue);
//        this.add(opinion, BorderLayout.EAST);
//
//        JLabel postText = new JLabel("tu");
//        postText.setFont(new Font("Calibri", Font.BOLD, 12));
//        this.add(postText, BorderLayout.CENTER);
//
//        JButton like = new JButton("Like");
//        this.add(like, BorderLayout.PAGE_END);
//        like.setEnabled(true);
//
//
//
//
//        this.setVisible(true);
    }

    private boolean opinionClicked(int opinion) {
        if(DbConnectionBuilder.getInstance().likeClick(post.getId(),opinion)){
            return true;
        }else return false;
    }


}
