package project.view.panels;

import project.db_connection.DbConnectionBuilder;
import project.view.MyFrame;

import javax.swing.*;
import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MenuPanel extends JPanel {

    final JPanel panel = new JPanel();

    ResultSet posts;
    private JScrollPane vertical;

    public MenuPanel(MyFrame myFrame) throws SQLException {
        panel.setBorder(BorderFactory.createLineBorder(Color.red));
        panel.setPreferredSize(new Dimension(795, 590));

        final JScrollPane scroll = new JScrollPane(panel);

        setLayout(new BorderLayout());
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        add(scroll);
        setSize(795, 590);
        setVisible(true);
        
        posts = loadFeed();

        while(posts.next()){
            PostView postPanel = new PostView(posts);
            panel.add(postPanel);
            setVisible(true);
        }
//        setPreferredSize(new Dimension(200, 250));
//
//        vertical = new JScrollPane(console);
//        vertical.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
//        add(vertical);
//        setLayout(null);

//        JTextArea area = new JTextArea("Login succesful");
//        this.add(area);
//        setSize(800,600);
//        setVisible(false);
//        area.setBounds(20, 20, 200, 30);
    }

    private ResultSet loadFeed(){
        ResultSet posts;
        posts = DbConnectionBuilder.getInstance().selectPosts(DbConnectionBuilder.getInstance().getUserId());

        return posts;
    }

}
