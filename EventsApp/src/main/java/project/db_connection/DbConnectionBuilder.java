package project.db_connection;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DbConnectionBuilder {

    private final DbResolver dbResolver;

    private DbConnectionBuilder() {
        dbResolver = new DbResolver();
        dbResolver.connect();
    }

    private static DbConnectionBuilder INSTANCE;

    public static DbConnectionBuilder getInstance() {
        if(INSTANCE == null){
            INSTANCE = new DbConnectionBuilder();
        }
        return INSTANCE;
    }

    public boolean userExist(String username, String password) {
        String query = "SELECT * FROM \"user\" WHERE email LIKE '" + username + "' AND password LIKE '"+ password +"'";

        ResultSet result = dbResolver.select(query);

        try {
            return result.next();
        } catch (SQLException e) {
            return false;
        }finally {
            try {
                result.getStatement().close();
                result.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
