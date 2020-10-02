package Data;

import java.util.Objects;

public class RestaurantData {
    private String zipcode,address, city, locality, country_id, city_id;

    public RestaurantData(String zipcode,String address, String city, String locality, String country_id, String city_id) {
        this.zipcode = zipcode;
        this.address = address;
        this.city = city;
        this.locality = locality;
        this.country_id = country_id;
        this.city_id = city_id;
    }
    public String getZipcode() {
        return zipcode;
    }

    @Override
    public String toString() {
        return "RestaurantData{" +
                "zipcode='" + zipcode + '\'' +
                ", address='" + address + '\'' +
                ", city='" + city + '\'' +
                ", locality='" + locality + '\'' +
                ", country_id='" + country_id + '\'' +
                ", city_id='" + city_id + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RestaurantData that = (RestaurantData) o;
        return Objects.equals(address, that.address) &&
                Objects.equals(city, that.city) &&
                Objects.equals(locality, that.locality) &&
                Objects.equals(country_id, that.country_id) &&
                Objects.equals(city_id, that.city_id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(address, city, locality, country_id, city_id);
    }
}
