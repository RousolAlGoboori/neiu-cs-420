package api;

import org.json.JSONObject;

import javax.ws.rs.HttpMethod;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class HttpConnection {

    private HttpURLConnection connection;
    private List<String> list1 = null;
    private List<String> list2 = null;
    private List<String> list3 = null;

    private List<String> getString( HttpURLConnection connection) throws IOException {

      List<String> jsonObjectList = null;
        if (connection.getResponseCode() == 200) {
            final InputStream inputStream = connection.getInputStream();
            final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            jsonObjectList = getJsonObject(bufferedReader);
        }
        return jsonObjectList;
    }

    private List<String> getJsonObject(BufferedReader bufferedReader){
        Stream<String> lines = bufferedReader.lines();
        return lines.map(JSONObject::new)
                .map(jsonObject -> jsonObject.getJSONArray("restaurants"))
                .map(jsonArray -> {
                    List<String> ob = new ArrayList<>();
                    for(int i =0; i < jsonArray.length();i++) {
                        ob.add(jsonArray.getJSONObject(i).getJSONObject("restaurant").get("location").toString());
                        ob.add(jsonArray.getJSONObject(i).getJSONObject("restaurant").get("user_rating").toString());

                    }
                    return ob; })
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }


   public List<String> getData( ) throws IOException{
        String [] urlString = new String[3] ;
        urlString[0]= "https://developers.zomato.com/api/v2.1/search?entity_id=292&entity_type=city&start=0&count=20";
        urlString[1]= "https://developers.zomato.com/api/v2.1/search?entity_id=292&entity_type=city&start=20&count=40";
        urlString[2]= "https://developers.zomato.com/api/v2.1/search?entity_id=292&entity_type=city&start=40&count=60";
        getLists(urlString);
        return Stream.of( list1,  list2,list3)
               .flatMap(Collection::stream)
               .collect(Collectors.toList());
    }

    private void getLists(String[] urlString) throws IOException {
        for (int j = 0; j < urlString.length; j++) {
                if (j == 0){
                    final URL url = new URL(urlString[0]);
                    connection = getHttpURLConnection(url);
                     list1 = getString(connection);
                }
                if (j == 1){
                   final URL url = new URL(urlString[1]);
                   connection = getHttpURLConnection(url);
                   list2 = getString(connection);
                }
                if (j == 2){
                  final URL url = new URL(urlString[2]);
                  connection = getHttpURLConnection(url);
                  list3 = getString(connection);
                }
            }
    }

    private HttpURLConnection getHttpURLConnection(URL url) throws IOException {
        connection = (HttpURLConnection) url.openConnection();
        connection.setConnectTimeout(1000);
        connection.setRequestMethod(HttpMethod.GET);
        connection.setRequestProperty("user-key", System.getenv("USER_KEY"));
        connection.setRequestProperty("application", "json");
        return connection;
    }
}
