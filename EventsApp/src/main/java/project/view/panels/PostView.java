package project.view.panels;

import javax.swing.*;
import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PostView extends JPanel {

//    private JPanel postPanel;

    public PostView(ResultSet result) throws SQLException {
        this.setBorder(BorderFactory.createLineBorder(Color.green));
        this.setPreferredSize(new Dimension(695, 200));
        JTextArea area = new JTextArea(result.getString(6));
        this.add(area);
        setSize(700, 200);
        setVisible(true);
        area.setBounds(20, 20, 200, 30);
    }

}
