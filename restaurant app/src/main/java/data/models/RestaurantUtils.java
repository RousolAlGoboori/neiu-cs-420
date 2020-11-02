package data.models;

import api.ReadFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.*;

public class RestaurantUtils {

    private static List<RestaurantDomain> list = new ArrayList<>();
    private RestaurantUtils () { }
    public static List<RestaurantDomain> getAllData(){
        return list;
    }

    public static void read (String fileName) throws IOException {

        BufferedReader br = ReadFile.readData(ReadFile.getFilePath(fileName));
        br.lines().forEach(RestaurantUtils::parseLine);
    }

    private static void parseLine(String str){
        String zipcode,address, city;
        String[] data = str.split(":");
        zipcode = data[1].split(",")[0].split("\"")[1];
        address = data[2].split("\"")[1];
        city = data[3].split(",")[0].split("\"")[1];
        parseRemainingData(data, zipcode, address, city);

    }

    private static void parseRemainingData(String[] data, String zipcode, String address,String city){

        String locality, country_id, city_id;
        locality = data[6].split(",")[0].split("\"")[1];
        country_id = data[7].split(",")[0];
        city_id = data[8].split(",")[0];
        list.add(new RestaurantDomain(zipcode,address,city,locality,country_id, city_id));
    }

    public static Map<RestaurantZipEnum, List<RestaurantDomain>> mapData() {
        Map<RestaurantZipEnum, List<RestaurantDomain>> ls = new HashMap<>();
        list.forEach(restaurantDomain ->  placeValuesInZipCategory(ls, restaurantDomain));
        return ls;
    }


    private static void placeValuesInZipCategory(Map<RestaurantZipEnum, List<RestaurantDomain>> ls, RestaurantDomain i) {
        Arrays.asList(RestaurantZipEnum.values()).forEach(zipCategory ->{
            if (Integer.parseInt(i.getZiploc()) == zipCategory.getZip())
                addToMap(zipCategory,ls,i);

        });
    }

    private static void addToMap(RestaurantZipEnum key, Map<RestaurantZipEnum, List<RestaurantDomain>> map, RestaurantDomain rd){
        if(!map.containsKey(key)){
            map.put(key, new ArrayList<>(Arrays.asList(rd)));
        }
        else
            map.get(key).add(rd);
    }
}
