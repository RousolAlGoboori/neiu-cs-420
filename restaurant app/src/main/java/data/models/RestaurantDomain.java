package data.models;

import java.util.Objects;

public class RestaurantDomain {
    private String ziploc,address, city, locality, country_id, city_id;

    public RestaurantDomain(String ziploc, String address, String city, String locality, String country_id, String city_id) {
        this.ziploc = ziploc;
        this.address = address;
        this.city = city;
        this.locality = locality;
        this.country_id = country_id;
        this.city_id = city_id;
    }
    public String getZiploc() {
        return ziploc;
    }

    public String getAddress() {
        return address;
    }

    public String getLocality() {
        return locality;
    }

    public String getCountry_id() {
        return country_id;
    }

    public String getCity_id() {
        return city_id;
    }

    @Override
    public String toString() {
        return "RestaurantData{" +
                "zipcode='" + ziploc + '\'' +
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
        RestaurantDomain that = (RestaurantDomain) o;
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


