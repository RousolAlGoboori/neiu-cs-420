package javaFx;

import java.util.Comparator;

public class RestaurantZipComparator implements Comparator<RestaurantZipEnum> {

    public RestaurantZipComparator() { }
    @Override
    public int compare(RestaurantZipEnum o1, RestaurantZipEnum o2) {

        if( o1.getZip() < o2.getZip())
            return -1;
        else if( o1.getZip() > o2.getZip())
            return 1;
        else
            return 0;
    }
}
