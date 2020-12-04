package data.utils;

import api.ReadFile;
import data.models.RestaurantDomain;
import data.models.RestaurantZipEnum;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;


public class RestaurantUtils {

    private static List<RestaurantDomain> list = new ArrayList<>();
    public static List<RestaurantDomain> getAllData(){
        return list;
    }
    private static int count=0;

    private RestaurantUtils() { }

    public static void read (String fileName) throws IOException {

        BufferedReader br = ReadFile.readData(ReadFile.getFilePath(fileName));
        List<String> stringList=br.lines().collect(Collectors.toList());
        List<String> list2=stringList.stream().filter(line->stringList.indexOf(line)%2==0).collect(Collectors.toList());
        List<String> list3=stringList.stream().filter(line->stringList.indexOf(line)%2==1).collect(Collectors.toList());

        createList(list2, list3);
    }

    private static void createList(List<String> list2, List<String> list3) {
        list2.forEach(RestaurantUtils::parseLine);
        list3.forEach(RestaurantUtils::setAggregateRating);
        //list.forEach(System.out::println);
    }

    private static void parseLine(String str){
       String zipcode =" ", address =" " , city =" ", locality =" " , country_id =" ", city_id =" ", aggregate_rating =" " ;
        String[] data = str.split(":");
          if (data[0].contains("zipcode")) {
            zipcode = data[1].split(",")[0].split("\"")[1];
            address = data[2].split("\"")[1];
            city = data[3].split(",")[0].split("\"")[1];
            locality = data[6].split(",")[0].split("\"")[1];
            country_id = data[7].split(",")[0];
            city_id = data[8].split(",")[0];
        }
        list.add(new RestaurantDomain(zipcode, address, city, locality, country_id, city_id, aggregate_rating));
    }

    private static void setAggregateRating(String str) {
        String aggregate_rating;
        String[] data = str.split(":");
        aggregate_rating = data[1].split(",")[0].split("\"")[1];
        list.get(count).setAggregate_rating(aggregate_rating); ///
        count++;
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
            map.put(key, new ArrayList<>(Collections.singletonList(rd)));
        }
        else
            map.get(key).add(rd);
    }
}
