package data.models;

import java.util.Objects;

public class RestaurantDomain {
    private String ziploc,address, city, locality, country_id, city_id;
    private String aggregate_rating;

    public RestaurantDomain(String ziploc, String address, String city, String locality, String country_id, String city_id,String aggregate_rating) {
        this.ziploc = ziploc;
        this.address = address;
        this.city = city;
        this.locality = locality;
        this.country_id = country_id;
        this.city_id = city_id;
        this.aggregate_rating = aggregate_rating;
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

    public String getAggregate_rating() {
        return aggregate_rating;
    }

    public void setAggregate_rating(String aggregate_rating) {
        this.aggregate_rating = aggregate_rating;
    }

    @Override
    public String toString() {
        return "RestaurantDomain{" +
                "ziploc='" + ziploc + '\'' +
                ", address='" + address + '\'' +
                ", city='" + city + '\'' +
                ", locality='" + locality + '\'' +
                ", country_id='" + country_id + '\'' +
                ", city_id='" + city_id + '\'' +
                ", aggregate_rating='" + aggregate_rating + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RestaurantDomain that = (RestaurantDomain) o;
        return Objects.equals(ziploc, that.ziploc) &&
                Objects.equals(address, that.address) &&
                Objects.equals(city, that.city) &&
                Objects.equals(locality, that.locality) &&
                Objects.equals(country_id, that.country_id) &&
                Objects.equals(city_id, that.city_id) &&
                Objects.equals(aggregate_rating, that.aggregate_rating);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ziploc, address, city, locality, country_id, city_id, aggregate_rating);
    }
}


