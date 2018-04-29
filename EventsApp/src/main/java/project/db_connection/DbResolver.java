package project.db_connection;

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
            connection.setAutoCommit(false);
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

    public ResultSet select(String query) {
        System.out.println(query);
        Statement stmt = null;
        ResultSet rs = null;
        try {
            stmt = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            rs = stmt.executeQuery(query);
        } catch (SQLException e) {
            System.err.println("Sql error " + e.getMessage());
        }
        return rs;

    }

    public void insert(String query) {
        System.out.println(query);

        Statement stmt = null;
        try {
            stmt = connection.createStatement();
            stmt.executeUpdate(query);
            connection.commit();
        } catch (SQLException e) {
            System.err.println("Sql error " + e.getMessage());
            try {
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
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

    public void update(String query) {
        System.out.println(query);

        Statement stmt = null;
        try {
            stmt = connection.createStatement();
            stmt.executeUpdate(query);
            connection.commit();
        } catch (SQLException e) {
            System.err.println("Sql error " + e.getMessage());
            try {
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
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
    public void delete(String query) {
        System.out.println(query);

        Statement stmt = null;
        try {
            stmt = connection.createStatement();
            stmt.executeUpdate(query);
            connection.commit();
        } catch (SQLException e) {
            System.err.println("Sql error " + e.getMessage());
            try {
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException e) {
                System.err.println("problem with connection closing " + e.getMessage());
            }
        }
    }
}

