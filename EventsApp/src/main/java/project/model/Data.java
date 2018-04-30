package project.model;

public class Data {

    private static Data INSTANCE;
    private User user;

    public static Data getInstance() {
        if(INSTANCE==null){
            INSTANCE = new Data();
        }
        return INSTANCE;
    }

    private Data() {

    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
