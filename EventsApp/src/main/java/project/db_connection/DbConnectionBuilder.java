package project.db_connection;

import project.model.Data;
import project.model.EventObj;
import project.model.Post;
import project.model.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DbConnectionBuilder {

    private final DbResolver dbResolver;

    private DbConnectionBuilder() {
        dbResolver = new DbResolver();
        dbResolver.connect();
        postOffset = 0;
        eventOffset = 0;
    }

    private int userId;
    private int postOffset;
    private int eventOffset;

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
                User user = new User(result);
                Data.getInstance().setUser(user);
                setUserId(Data.getInstance().getUser().getId());
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


    public ArrayList<Post> selectPosts(int userId, int actualPosition) {
        String query =
                "SELECT p.*, e.name, coalesce(sss.opinion, 0) AS opinion, coalesce(sub.like_count, 0) AS like_count FROM \"user\" AS u JOIN event_like AS el ON u.id = el.user_id JOIN event AS e" +
                        " JOIN post AS p On p.event_id = e.id" +
                        " ON e.id = el.event_id" +
                        " LEFT JOIN (SELECT p.opinion, p.post_id FROM \"user\" AS u JOIN posts_like AS p ON u.id = p.user_id WHERE u.id = " + userId + " ) AS sss" +
                        " ON sss.post_id = p.id LEFT JOIN (SELECT p.post_id, SUM(p.opinion) AS like_count FROM posts_like AS p GROUP BY p.post_id) AS sub" +
                        " ON sub.post_id = p.id" +
                        " WHERE u.id = " + userId +
                        " ORDER BY p.id " +
                        "LIMIT 3 OFFSET " + actualPosition*3;
        ResultSet result = dbResolver.select(query);
        ArrayList<Post> posts = new ArrayList<>();
        try {
            while (result.next()) {
                Post post = new Post(result);
                posts.add(post);
                postOffset = post.getId();
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

    public int likeClick(int postId, int opinion) {
        boolean alreadyExists = getOpinion(postId);
        if (alreadyExists) {
            updateOpinion(postId, opinion);
        } else {
            addOpinion(postId, opinion);
        }
        return opinion;
    }

    private void addOpinion(int postId, int opinion) {
        String query = "INSERT INTO posts_like (user_id, post_id, opinion) VALUES" +
                "  (" + getUserId() + "," + postId + "," + opinion + ");";
        dbResolver.insert(query);
    }

    private void updateOpinion(int postId, int opinion) {
        String query = "UPDATE posts_like " +
                "SET opinion = " + opinion + " " +
                "WHERE user_id = " + getUserId() + " AND " +
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
        } finally {
            try {
                result.getStatement().close();
                result.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public void updateUser(String name, String surname, int sex) {
        String query = "UPDATE \"user\" " +
                "SET name = '"+name+"', " +
                "    surname = '"+surname+"', " +
                "    sex = "+sex+" " +
                "WHERE id = "+Data.getInstance().getUser().getId()+";";

        dbResolver.update(query);

    }

    public void deleteUser() {
        String query = "DELETE FROM \"user\" " +
                "WHERE id = "+Data.getInstance().getUser().getId()+";";

        dbResolver.delete(query);
    }

    public ArrayList<EventObj> filterEvents(String name, String country, String city, double from, double to, int actualPosition) {
        StringBuilder queryBuilder = new StringBuilder("SELECT * FROM event WHERE ");
        queryBuilder.append("lower(name) LIKE LOWER('").append(name).append("%') AND ");
        queryBuilder.append("lower(country) LIKE lower('").append(country).append("%') AND ");
        queryBuilder.append("lower(city) LIKE LOWER('").append(city).append("%') AND ");
        queryBuilder.append("ticket_price < ").append(to).append(" AND ticket_price > ").append(from);
        queryBuilder.append(" LIMIT 3 OFFSET ").append(actualPosition*3);

        System.out.print(queryBuilder.toString());

        ResultSet result = dbResolver.select(queryBuilder.toString());
        ArrayList<EventObj> events = new ArrayList<>();
        try {
            while (result.next()) {
                EventObj eventObj = new EventObj(result);
                events.add(eventObj);
                eventOffset = eventObj.getId();
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

        return events;
    }
}
