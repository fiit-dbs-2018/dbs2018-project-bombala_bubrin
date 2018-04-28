package project.db_connection;

import project.model.Post;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
        if (INSTANCE == null) {
            INSTANCE = new DbConnectionBuilder();
        }
        return INSTANCE;
    }

    public boolean userExist(String username, String password) {
        String query = "SELECT * FROM \"user\" WHERE email LIKE '" + username + "' AND password LIKE '" + password + "'";

        ResultSet result = dbResolver.select(query);

        try {
            if (result.next()) {
                setUserId(result.getInt("id"));
                return true;
            }
        } catch (SQLException e) {
            return false;
//            e.printStackTrace();
        }
        return false;
//        finally {
//            try {
//                if (result != null) {
//                    result.close();
//                }
//            } catch (SQLException e) {
//                System.err.println("problem with connection closing "  + e.getMessage());
//            }
//        }
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

    public void registerUser(String name, String surname, String password, String email, int sex) {
        String query = "INSERT INTO public.user (name, surname, password, email, sex) VALUES" +
                " ('" + name + "', '" + surname + "', '" + password + "', '" + email + "', " + sex + ");";
        dbResolver.insert(query);

    }


    public ArrayList<Post> selectPosts(int userId) {
        String query =
                "SELECT p.*, e.name, coalesce(sss.opinion, 0) AS opinion, coalesce(sub.like_count, 0) AS like_count FROM \"user\" AS u JOIN event_like AS el ON u.id = el.user_id JOIN event AS e" +
                        " JOIN post AS p On p.event_id = e.id" +
                        " ON e.id = el.event_id" +
                        " LEFT JOIN (SELECT p.opinion, p.post_id FROM \"user\" AS u JOIN posts_like AS p ON u.id = p.user_id WHERE u.id = " + userId + " ) AS sss" +
                        " ON sss.post_id = p.id LEFT JOIN (SELECT p.post_id, SUM(p.opinion) AS like_count FROM posts_like AS p GROUP BY p.post_id) AS sub" +
                        " ON sub.post_id = p.id" +
                        " WHERE u.id = " + userId + " " +
                        "LIMIT 3;";
        ResultSet result = dbResolver.select(query);
        ArrayList<Post> posts = new ArrayList<>();
        try {
            while (result.next()) {
                Post post = new Post(result);
                posts.add(post);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                result.getStatement().close();
                result.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return posts;
    }

    public boolean likeClick(int postId, int opinion) {
        boolean alreadyExists = getOpinion(postId);
        try {
            if (alreadyExists) {
                updateOpinion(postId, opinion);
            } else {
                addOpinion(postId, opinion);
            }
        } catch (SQLException e) {

        }
        return opinion;
    }

    private void addOpinion(int postId, int opinion) {
        String query = "INSERT INTO posts_like (user_id, post_id, opinion) VALUES" +
                "  (" + getUserId() + "," + postId + "," + opinion + ");";
        dbResolver.insert(query);
    }

    private void updateOpinion(int postId, int opinion) {
        String query = "UPDATE posts_like" +
                "SET opinion = " + opinion + "" +
                "WHERE user_id = " + getUserId() + " AND" +
                "post_id = " + postId + ";";

        dbResolver.update(query);
    }

    private boolean getOpinion(int postId) {
        String query = "SELECT * FROM posts_like WHERE user_id = " + getUserId() + " AND post_id = " + postId + ";";

        ResultSet result = dbResolver.select(query);

        try {
            if (result.next()) {
                return true;
            }
        } catch (SQLException e) {
            return false;
//            e.printStackTrace();
        }finally {
            try {
                result.getStatement().close();
                result.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }
}
