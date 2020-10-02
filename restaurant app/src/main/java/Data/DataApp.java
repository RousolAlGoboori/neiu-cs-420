package Data;

import api.ReadFile;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.*;

public class DataApp {

    private static List<RestaurantData> list = new ArrayList<>();

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
        String zipcode,address, city, locality, country_id, city_id;
        String[] data = str.split(":");
        zipcode = data[1].split(",")[0].split("\"")[1];
        address = data[2].split("\"")[1];
        city = data[3].split(",")[0].split("\"")[1];
        locality = data[6].split(",")[0].split("\"")[1];
        country_id = data[7].split(",")[0];
        city_id = data[8].split(",")[0];
        list.add(new RestaurantData(zipcode,address,city,locality,country_id, city_id));
    }
    public static Map<String, List<RestaurantData>> mapData() {
        Map<String, List<RestaurantData>> ls = new HashMap<>();
        for (RestaurantData i : list) {
            if (i.getZipcode().equals("60607")) {
                addToMap("60607", ls, i);
            }
            else if(i.getZipcode().equals("60611")) {
                addToMap("60611", ls, i);
            }
            else if(i.getZipcode().equals("60654")) {
                addToMap("60654", ls, i);
            }
            else if(i.getZipcode().equals("60610")) {
                addToMap("60610", ls, i);
            }
            else if(i.getZipcode().equals("60618")) {
                addToMap("60618", ls, i);
            }
            else
                addToMap("other",ls,i);
        }
        return ls;
    }
    private static void addToMap(String key, Map<String, List<RestaurantData>> map, RestaurantData rd){
            if(!map.containsKey(key)){
                map.put(key, new ArrayList<>(Arrays.asList(rd)));
            }
            else
                map.get(key).add(rd);
        }

    }
