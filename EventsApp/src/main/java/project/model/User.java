package project.model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class User {

    private int id;
    private String name;
    private String surneme;
    private String email;
    private int sex;

    public User(ResultSet result) throws SQLException {

        id = result.getInt("id");
        name = result.getString("name");
        surneme = result.getString("surname");
        email = result.getString("email");
        sex = result.getInt("sex");

    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurneme() {
        return surneme;
    }

    public String getEmail() {
        return email;
    }

    public int getSex() {
        return sex;
    }

    public void setChange(String name,String surneme,int sex) {
        this.name = name;
        this.surneme = surneme;
        this.sex = sex;
    }

}
