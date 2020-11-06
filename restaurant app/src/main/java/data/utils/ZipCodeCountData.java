package data.utils;

import data.models.RestaurantZipEnum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static data.utils.RestaurantUtils.getAllData;

public class ZipCodeCountData {

    private ZipCodeCountData(){}

    public static List<Long> getZipCodeCount() {

        List<Long> zipCountList = new ArrayList<>();
                                  Arrays.stream(RestaurantZipEnum.values())
                                 .forEach( zipEnum -> zipCountList.add(getAllData()
                                 .stream()
                                 .filter( restaurantDomain -> Integer.parseInt(restaurantDomain.getZiploc()) == zipEnum.getZip())
                                 .count()));
        return zipCountList;
    }


}
