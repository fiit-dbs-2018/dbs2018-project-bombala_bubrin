package data_filler;


import data_filler.db_connection.FillerDatabaseBuilder;

public class DataFillerRunner {

    public static void main(String[] args) {
        FillerDatabaseBuilder.getInstance().insertManyUsers(20000);
        FillerDatabaseBuilder.getInstance().insertManyStyles(20000);
        FillerDatabaseBuilder.getInstance().insertManyArtists(20000);
        FillerDatabaseBuilder.getInstance().insertManyEvents(200000);
        FillerDatabaseBuilder.getInstance().insertManyPosts(20000);
        FillerDatabaseBuilder.getInstance().insertManyTickets(20000);
        FillerDatabaseBuilder.getInstance().insertManyConcerts(20000);
        FillerDatabaseBuilder.getInstance().insertManyArtistLikes(10000);
        FillerDatabaseBuilder.getInstance().insertManyEventLikes(10000);
        FillerDatabaseBuilder.getInstance().insertManyPostsLikes(10000);
        FillerDatabaseBuilder.getInstance().insertManyArtistsStyles(10000);
    }

}

