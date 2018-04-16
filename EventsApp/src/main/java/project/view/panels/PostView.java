package project.view.panels;

import javax.swing.*;
import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PostView extends JPanel {

//    private JPanel postPanel;

    public PostView(ResultSet result) throws SQLException {

        this.setLayout(new BorderLayout());
        this.setBorder(BorderFactory.createLineBorder(Color.black));
        this.setPreferredSize(new Dimension(695, 200));

        JLabel eventName = new JLabel(result.getString(6));
        setSize(700, 200);
        eventName.setFont(new Font("Calibri", Font.BOLD, 19));
        eventName.setForeground(Color.BLACK);
        this.add(eventName, BorderLayout.PAGE_START);

        JLabel opinion = new JLabel(""+(result.getInt(7))+"");
        opinion.setBackground(Color.blue);
        this.add(opinion, BorderLayout.EAST);

        JLabel postText = new JLabel(result.getString(3));
        postText.setFont(new Font("Calibri", Font.BOLD, 12));
        this.add(postText, BorderLayout.CENTER);

        JButton like = new JButton("Like");
        this.add(like, BorderLayout.PAGE_END);
        like.setEnabled(true);




        this.setVisible(true);
    }

}
