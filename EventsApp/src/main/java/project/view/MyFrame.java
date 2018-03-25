package project.view;

import project.controller.DbResolver;
import project.utils.RandomString;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyFrame extends JFrame {

    private DbResolver dbResolver;

    public MyFrame() {
        dbResolver = new DbResolver();
        dbResolver.connect();
        setSize(800, 600);
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
//        for (int i = 0; i < 10000; i++) {
//            System.out.println("inserting batch " + i);
//            insert10000users();
//        }

        final JButton registerButton = new JButton("register");
        this.add(registerButton);
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registerUser("Filip", "Sukenik", "muss@gmail.com", "password", 1);
            }
        });
        repaint();
        revalidate();
    }

    private void insert10000users() {
        StringBuilder builder = new StringBuilder();
        builder.append("INSERT INTO users (name, surname, password, email, sex) VALUES ");
        for (int i = 0; i < 100; i++) {
            if (i != 0) {
                builder.append(", ");
            }
            builder.append("('").append(randomString(10)).append("', '").append(randomString(10)).append("', '").append(randomString(5)).append("', '").append(randomString(4)).append("@gmail.com', 1)");
        }
        dbResolver.insert(builder.toString());
        System.out.println(builder);
    }

    private String randomString(int length) {
        return new RandomString(length).nextString();
    }

    private void login(String email, String password) {
        String query = "SELECT * FROM users WHERE email LIKE '" + email + "' AND password LIKE '" + password + "'";
        if (dbResolver.select(query)) {
            System.out.println("Succesfull login huraa");
        } else {
            System.out.println("RIP");
        }
    }





    private void registerUser(String name, String surname, String email, String password, int sex) {
        String query = "INSERT INTO users (name, surname, password, email, sex) VALUES ('" + name + "" +
                "', '" + surname + "', '" + password + "', '" + email +"', " + sex +")";
        System.out.println("I am inserting user " + name + " " + surname);
        System.out.print(query);
        dbResolver.insert(query);
    }

}
