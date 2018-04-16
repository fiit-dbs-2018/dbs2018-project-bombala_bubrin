package project.db_connection;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DbConnectionBuilder {

    private final DbResolver dbResolver;

    private DbConnectionBuilder() {
        dbResolver = new DbResolver();
        dbResolver.connect();
    }
    private int userId;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }


    private static DbConnectionBuilder INSTANCE;

    public static DbConnectionBuilder getInstance() {
        if(INSTANCE == null){
            INSTANCE = new DbConnectionBuilder();
        }
        return INSTANCE;
    }

    public ResultSet userExist(String username, String password) {
        String query = "SELECT * FROM \"user\" WHERE email LIKE '" + username + "' AND password LIKE '"+ password +"'";

        ResultSet result = dbResolver.select(query);

        try {
            if(result.next()) {
                setUserId(result.getInt(1));
            }
        } catch (SQLException e) {
//            e.printStackTrace();
        }

//        finally {
//            try {
//                if (result != null) {
//                    result.close();
//                }
//            } catch (SQLException e) {
//                System.err.println("problem with connection closing "  + e.getMessage());
//            }
//        }
        return result;
//
//        try {
//            return result.next();
//        } catch (SQLException e) {
//            return false;
//        }finally {
//            try {
//                result.getStatement().close();
//                result.close();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
    }


    public ResultSet selectPosts(int userId){
        String query =
                "SELECT p.*, e.name, coalesce(sss.opinion, 0) AS opinion FROM \"user\" AS u JOIN event_like AS el ON u.id = el.user_id JOIN event AS e\n" +
                        "JOIN post AS p On p.event_id = e.id\n" +
                        "ON e.id = el.event_id\n" +
                        "LEFT JOIN (SELECT p.opinion, p.post_id FROM \"user\" AS u JOIN posts_like AS p ON u.id = p.user_id WHERE u.id = "+userId+") AS sss\n" +
                        "ON sss.post_id = p.id\n" +
                        "WHERE u.id = "+userId+"\nLIMIT 10";
        ResultSet result = dbResolver.select(query);
        return result;
    }
}
