package javaFx;

import api.ReadFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.*;

public class RestaurantUtils {

    private static List<Domain> list = new ArrayList<>();
    public static void read (String fileName) throws IOException {
        BufferedReader br = ReadFile.readData(ReadFile.getFilePath(fileName));
        String strLine;
        while((strLine = br.readLine()) != null){
            parseLine(strLine);
        }
        br.close();
        System.out.println("list data\n"+list);
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
        list.add(new Domain(zipcode,address,city,locality,country_id, city_id));
    }

    public static Map<RestaurantZipEnum, List<Domain>> mapData() {
        Map<RestaurantZipEnum, List<Domain>> ls = new HashMap<>();
        for (Domain i : list) {
            for(RestaurantZipEnum zipCategory: RestaurantZipEnum.values()){
                if (Integer.parseInt(i.getZipcode()) == zipCategory.getZip())
                    addToMap(zipCategory,ls,i);
            }
        }
        return ls;
    }
    private static void addToMap(RestaurantZipEnum key, Map<RestaurantZipEnum, List<Domain>> map, Domain rd){
        if(!map.containsKey(key)){
            map.put(key, new ArrayList<>(Arrays.asList(rd)));
        }
        else
            map.get(key).add(rd);
    }
}
