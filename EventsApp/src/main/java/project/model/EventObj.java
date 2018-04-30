package project.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class EventObj {

    private int id;
    private String name;
    private String country;
    private String city;
    private int ticketPrice;
    private Timestamp timeOfStart;
    private String address;

    public EventObj(ResultSet result) throws SQLException {

        id = result.getInt("id");
        name = result.getString("name");
        timeOfStart = result.getTimestamp("time_of_start");
        city = result.getString("city");
        country = result.getString("country");
        address = result.getString("address");
        ticketPrice = result.getInt("ticket_price");

    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCountry() {
        return country;
    }

    public String getCity() {
        return city;
    }

    public int getTicketPrice() {
        return ticketPrice;
    }

    public Timestamp getTimeOfStart() {
        return timeOfStart;
    }

    public String getAddress() {
        return address;
    }
}
