package data_filler.model;

public class CityCountryPair {
    private String country;
    private String city;

    public CityCountryPair() {

    }

    public String getCountry() {
        return country;
    }

    public String getCity() {
        return city;
    }

    public CityCountryPair(String line){
        String[] splittedLine = line.split(";");
        city = splittedLine[0];
        country = splittedLine[1];
    }
}
