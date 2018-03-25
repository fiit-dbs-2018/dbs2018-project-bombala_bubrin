package project.controller;

import java.sql.*;

public class DbResolver {

    private Connection connection = null;

    public void connect(){
        System.out.println("-------- PostgreSQL "
                + "JDBC Connection Testing ------------");

        try {

            Class.forName("org.postgresql.Driver");

        } catch (ClassNotFoundException e) {

            System.out.println("Where is your PostgreSQL JDBC Driver? "
                    + "Include in your library path!");
            e.printStackTrace();
            return;

        }

        System.out.println("PostgreSQL JDBC Driver Registered!");

        connection = null;

        try {

            connection = DriverManager.getConnection(
                    "jdbc:postgresql://127.0.0.1:5432/eventapp", "event-app-user",
                    "123456");

        } catch (SQLException e) {

            System.out.println("Connection Failed! Check output console");
            e.printStackTrace();
            return;

        }

        if (connection != null) {
            System.out.println("You made it, take control your database now!");
        } else {
            System.out.println("Failed to make connection!");
        }
    }

    public boolean select(String query) {
        Statement stmt = null;
        boolean succesfullLogin = false;
        try {
            stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            succesfullLogin = rs.next();
        } catch (SQLException e) {
            System.err.println("Sql error " + e.getMessage());
        }
        finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException e) {
                System.err.println("problem with connection closing "  + e.getMessage());
            }
        }
        return succesfullLogin;
    }

    public void insert(String query) {
        Statement stmt = null;
        try {
            stmt = connection.createStatement();
            stmt.executeQuery(query);
        } catch (SQLException e) {
            System.err.println("Sql error " + e.getMessage());
        }
        finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException e) {
                System.err.println("problem with connection closing "  + e.getMessage());
            }
        }
    }
}
