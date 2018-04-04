package data_filler.db_connection;

import data_filler.model.CityCountryPair;
import project.utils.RandomString;
import data_filler.filling_from_file.Filling;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class FillerDatabaseBuilder {

    private DbResolver dbResolver = null;

    private RandomString randomString = null;
    private static final int BATCH_SIZE = 100;

    private static FillerDatabaseBuilder instance;

    private FillerDatabaseBuilder() {
        System.out.println("vytvoril som komponent na pripajanie");
        dbResolver = new DbResolver();
        dbResolver.connect();
        randomString = new RandomString(6);
    }

    public ArrayList style;

    public void insertUser(String name, String surname, String password, String email, int sex) {
        String query = "INSERT INTO public.user (name, surname, password, email, sex) " +
                "VALUES ('" + name + "', '" + surname + "', '" + password + "', '" + email + "', " + sex + ")";
        dbResolver.insert(query);
    }

    public boolean isUserRegistered(String email, String password) {
        String query = "SELECT * FROM public.user WHERE email LIKE  '" + email + "' AND password LIKE '" + password + "'";
        ResultSet resultSet = dbResolver.select(query);
        boolean userLoggedIn = false;

        try {
            if (resultSet != null && resultSet.next()) {
                userLoggedIn = true;
            }
        } catch (SQLException exception) {
            System.err.println("result set error " + exception.getMessage());
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return userLoggedIn;
    }

    private Random randomGenerator = new Random();

    public String getRandomName(ArrayList list) {
        return (String) list.get(randomGenerator.nextInt(list.size()));
    }

    public CityCountryPair getRandomCC(ArrayList list) {
        return (CityCountryPair) list.get(randomGenerator.nextInt(list.size()));
    }

    public void insertManyArtistLikes(int count) {
        for (int j = 0; j < count; j++) {

//            INSERT INTO artist_like (user_id, artist_id, opinion) VALUES (1, 1, 0), (1, 2, 1)
            StringBuilder query = new StringBuilder("INSERT INTO artist_like (user_id, artist_id, opinion) VALUES ");
            for (int i = 0; i < BATCH_SIZE; i++) {
                if (i != 0) {
                    query.append(",");
                }
                query.append("(").append(getRandomIndex()).append(", ")
                        .append(getRandomIndex()).append(", ").append(getRandomOpinion()).append(")");
            }
            dbResolver.insert(query.toString());
            System.out.println("Inserted " + j + " batch of users");
        }
    }

    public void insertManyEventLikes(int count) {
        for (int j = 0; j < count; j++) {

//            INSERT INTO artist_like (user_id, artist_id, opinion) VALUES (1, 1, 0), (1, 2, 1)
            StringBuilder query = new StringBuilder("INSERT INTO event_like (user_id, event_id, opinion) VALUES ");
            for (int i = 0; i < BATCH_SIZE; i++) {
                if (i != 0) {
                    query.append(",");
                }
                query.append("(").append(getRandomIndex()).append(", ")
                        .append(getRandomIndex()).append(", ").append(getRandomOpinion()).append(")");
            }
            dbResolver.insert(query.toString());
            System.out.println("Inserted " + j + " batch of users");
        }
    }

    public void insertManyPostsLikes(int count) {
        for (int j = 0; j < count; j++) {

//            INSERT INTO artist_like (user_id, artist_id, opinion) VALUES (1, 1, 0), (1, 2, 1)
            StringBuilder query = new StringBuilder("INSERT INTO posts_like (user_id, post_id, opinion) VALUES ");
            for (int i = 0; i < BATCH_SIZE; i++) {
                if (i != 0) {
                    query.append(",");
                }
                query.append("(").append(getRandomIndex()).append(", ")
                        .append(getRandomIndex()).append(", ").append(getRandomOpinion()).append(")");
            }
            dbResolver.insert(query.toString());
            System.out.println("Inserted " + j + " batch of users");
        }
    }

    public void insertManyPosts(int count) {
        for (int j = 0; j < count; j++) {

//            INSERT INTO post (type, text, event_id, image_url) VALUES (1, 'ssada', 1, 'http://www.tusom.jpg')

            StringBuilder query = new StringBuilder("INSERT INTO post (type, text, event_id, image_url) VALUES ");
            for (int i = 0; i < BATCH_SIZE; i++) {
                if (i != 0) {
                    query.append(",");
                }
                query.append("(").append(getRandomPostType()).append(", '").append(getRandomString()).append("', ").append(getRandomIndex()).append(", 'http://www.someurl.sk/").append(getRandomString()).append("')");
            }
            dbResolver.insert(query.toString());
            System.out.println("Inserted " + j + " batch of users");
        }
    }

    private int getRandomPostType() {
        return new Random().nextInt(5);
    }


    private int getRandomOpinion() {
        return new Random().nextInt(2);
    }

    private int getRandomIndex() {
        return new Random().nextInt(999999) + 1;
    }

    /**
     * @param count number of batches
     */
    public void insertManyUsers(int count) {
        ArrayList firstname = Filling.readFirstnames();
        ArrayList lastname = Filling.readLastnames();

        String first_name;
        String last_name;

        for (int j = 0; j < count; j++) {
            StringBuilder query = new StringBuilder("INSERT INTO public.user (name, surname, password, email, sex) VALUES ");
            for (int i = 0; i < BATCH_SIZE; i++) {
                if (i != 0) {
                    query.append(",");
                }

                first_name = getRandomName(firstname);
                last_name = getRandomName(lastname);


                query.append("('").append(first_name).append("', '").append(last_name)
                        .append("', '").append(getRandomString()).append("', '")
                        .append(first_name).append(".").append(last_name).append(j * 100 + i).append("@email.com', ")
                        .append(getGender()).append(")");
            }
            dbResolver.insert(query.toString());
            System.out.println("Inserted " + j + " batch of users");

        }
    }

    /**
     * @param count number of batches
     */
    public void insertManyStyles(int count) {
        style = Filling.readStyle();

        for (int j = 0; j < count; j++) {
            StringBuilder query = new StringBuilder("INSERT INTO style (name) VALUES ");
            for (int i = 0; i < BATCH_SIZE; i++) {
                if (i != 0) {
                    query.append(",");
                }
                query.append("('").append(getRandomName(style)).append("')");
            }
            dbResolver.insert(query.toString());
            System.out.println("Inserted " + j + " batch of styles");
        }
    }

    /**
     * @param count number of batches
     */
    public void insertManyArtists(int count) {
        ArrayList firstname = Filling.readFirstnames();

        for (int j = 0; j < count; j++) {
            StringBuilder query = new StringBuilder("INSERT INTO artist (nick_name) VALUES ");
            for (int i = 0; i < BATCH_SIZE; i++) {
                if (i != 0) {
                    query.append(",");
                }
                query.append("('").append(getRandomName(firstname)).append("')");
            }
            dbResolver.insert(query.toString());
            System.out.println("Inserted " + j + " batch of artists");
        }
    }

    /**
     * @param count number of batches
     */
    public void insertManyEvents(int count) {
        System.out.println("4");
        ArrayList<CityCountryPair> cc = Filling.readCityCountry();
        System.out.println("1");
        CityCountryPair note;// = new CityCountryPair();
        String city;
        String country;
        System.out.println("1");

        //INSERT INTO event (name, time_of_start, city, country, address) VALUES ('name', '1900-01-01 00:00:00', 'city', 'country', 'address')
        for (int j = 0; j < count; j++) {
            StringBuilder query = new StringBuilder("INSERT INTO event (name, time_of_start, city, country, address, ticket_price) VALUES ");
            for (int i = 0; i < BATCH_SIZE; i++) {
                if (i != 0) {
                    query.append(",");
                }
                note = getRandomCC(cc);
                city = note.getCity();
                country = note.getCountry();


                query.append("('").append(city).append(" ").append(getRandomName(style)).append("', '").append(getRandomTime()).append("', '").append(city)
                        .append("', '").append(country).append("', '").append(city).append(" ").append(j * 100 + i).append("', ").append(getRandomPrice()).append(")");
            }
            dbResolver.insert(query.toString());
            System.out.println("Inserted " + j + " batch of events");
        }
    }

    private double getRandomPrice() {
        return ThreadLocalRandom.current().nextDouble(5, 100);
    }

    private String getRandomTime() {
        long offset = Timestamp.valueOf("2012-01-01 00:00:00").getTime();
        long end = Timestamp.valueOf("2029-01-01 00:00:00").getTime();
        long diff = end - offset + 1;
        Timestamp rand = new Timestamp(offset + (long) (Math.random() * diff));
        return rand.toString();
    }

    private int getGender() {
        Random rand = new Random();
        int number = rand.nextInt(4);
        switch (number) {
            case 0:
            case 1:
            case 2:
                return number;
            case 3:
                return 9;
        }
        return 0;
    }


    private String getRandomString() {
        return randomString.nextString();
    }


    public void insertOneMilionArtists() {

    }


    public static FillerDatabaseBuilder getInstance() {
        if (instance == null) {
            instance = new FillerDatabaseBuilder();
        }
        return instance;
    }

    public void insertManyArtistsStyles(int count) {
        for (int j = 0; j < count; j++) {

            StringBuilder query = new StringBuilder("INSERT INTO artists_style (artist_id, style_id) VALUES ");
            for (int i = 0; i < BATCH_SIZE; i++) {
                if (i != 0) {
                    query.append(",");
                }
                query.append("(").append(getRandomIndex()).append(", ").append(getRandomIndex()).append(")");
            }
            dbResolver.insert(query.toString());
            System.out.println("Inserted " + j + " batch of users");
        }
    }
    public void insertManyConcerts(int count) {
        for (int j = 0; j < count; j++) {

            StringBuilder query = new StringBuilder("INSERT INTO concert (event_id, artist_id, time_of_start) VALUES ");
            for (int i = 0; i < BATCH_SIZE; i++) {
                if (i != 0) {
                    query.append(",");
                }
                query.append("(").append(getRandomIndex()).append(", ").append(getRandomIndex()).append(", '").append(getRandomTime()).append("')");
            }
            dbResolver.insert(query.toString());
            System.out.println("Inserted " + j + " batch of concerts");
        }
    }

    public void insertManyTickets(int count) {
        for (int j = 0; j < count; j++) {

            StringBuilder query = new StringBuilder("INSERT INTO ticket (user_id, event_id) VALUES ");
            for (int i = 0; i < BATCH_SIZE; i++) {
                if (i != 0) {
                    query.append(",");
                }
                query.append("(").append(getRandomIndex()).append(", ").append(getRandomIndex()).append(")");
            }
            dbResolver.insert(query.toString());
            System.out.println("Inserted " + j + " batch of users");
        }
    }
}
