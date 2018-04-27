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
    int height;

    public MenuPanel(MyFrame myFrame) throws SQLException {

        posts = loadFeed();
        height = getRowCount(posts) * 208;

        panel.setBorder(BorderFactory.createEmptyBorder());
        panel.setPreferredSize(new Dimension(795, height));

        final JScrollPane scroll = new JScrollPane(panel);

        setLayout(new BorderLayout());
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        this.add(scroll);
        setSize(795, 590);
        setVisible(true);

        while(posts.next()){
            PostView postPanel = new PostView(posts);
            panel.add(postPanel);
            setVisible(true);
        }

    }

    private ResultSet loadFeed(){
        ResultSet posts;
        posts = DbConnectionBuilder.getInstance().selectPosts(DbConnectionBuilder.getInstance().getUserId());

        return posts;
    }

    private int getRowCount(ResultSet resultSet) {
        if (resultSet == null) {
            return 0;
        }
        try {
            resultSet.last();
            return resultSet.getRow();
        } catch (SQLException exp) {
            exp.printStackTrace();
        } finally {
            try {
                resultSet.beforeFirst();
            } catch (SQLException exp) {
                exp.printStackTrace();
            }
        }
        return 0;
    }

}
