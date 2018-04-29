package project.view.panels;

import project.db_connection.DbConnectionBuilder;
import project.model.Data;
import project.model.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProfilPanel extends JPanel {

    ContentPanel parent;
    private JTextField name;
    private JTextField surname;
    private JLabel email;
    private JTextField sex;

    public ProfilPanel(ContentPanel contentPanel) {
        parent = contentPanel;
        setSize(650, 630);
        setLayout(null);

        JPanel labels = new JPanel();
        labels.setBounds(0,0,200,380);
        this.add(labels);
        labels.setLayout(null);
        JLabel nameLabel = new JLabel("name:",SwingConstants.RIGHT);
        nameLabel.setBounds(30,0,170,90);
        JLabel surnameLabel = new JLabel("surname:",SwingConstants.RIGHT);
        surnameLabel.setBounds(30,90,170,90);
        JLabel emailLabel = new JLabel("email:",SwingConstants.RIGHT);
        emailLabel.setBounds(30,180,170,90);
        JLabel sexLabel = new JLabel("sex:",SwingConstants.RIGHT);
        sexLabel.setBounds(30,270,170,90);
        labels.add(nameLabel);
        labels.add(surnameLabel);
        labels.add(emailLabel);
        labels.add(sexLabel);

        JPanel fields = new JPanel();
        fields.setBounds(200,0,430,360);
        fields.setLayout(null);
        this.add(fields);
        name = new JTextField("");
        name.setBounds(20,0,430,90);
        surname = new JTextField("");
        surname.setBounds(20,90,430,90);
        email = new JLabel("",SwingConstants.LEFT);
        email.setBounds(20,180,430,90);
        sex = new JTextField("");
        sex.setBounds(20,270,430,90);
        fields.add(name);
        fields.add(surname);
        fields.add(email);
        fields.add(sex);

        JButton submit = new JButton("Submit Changes");
        JButton deleteAcc = new JButton("Delete Account");

        submit.setBounds(220,380,150,20);
        deleteAcc.setBounds(10,380,150,20);
        this.add(submit);
        this.add(deleteAcc);

        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sumbitChanges();
            }
        });

        deleteAcc.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteAccount();
            }
        });




    }

    private void deleteAccount() {
        DbConnectionBuilder.getInstance().deleteUser();
        parent.logout();
    }

    private void sumbitChanges() {
        DbConnectionBuilder.getInstance().updateUser(name.getText(),surname.getText(),Integer.parseInt(sex.getText()));
        Data.getInstance().getUser().setChange(name.getText(),surname.getText(),Integer.parseInt(sex.getText()));
    }

    public void showProfile(){
        User user = Data.getInstance().getUser();
        name.setText(user.getName());
        surname.setText(user.getSurneme());
        email.setText(user.getEmail());
        sex.setText(Integer.toString(user.getSex()));

    }


}
