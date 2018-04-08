package project.view.panels;

import project.view.MyFrame;

import javax.swing.*;

public class MenuPanel extends JPanel {


    public MenuPanel(MyFrame myFrame){
        setLayout(null);
        JTextArea area = new JTextArea("Login succesful");
        this.add(area);
        setSize(800,600);
        setVisible(false);
        area.setBounds(20, 20, 200, 30);
    }

}
