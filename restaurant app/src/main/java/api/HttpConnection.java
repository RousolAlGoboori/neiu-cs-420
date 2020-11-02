package api;

import javax.ws.rs.HttpMethod;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class HttpConnection {

    private HttpURLConnection connection;
    private  final int connectTimeout = 1000;

    public List<String> getData( ) throws IOException{

        HttpURLConnection connection = getConnection();
        return getString(connection);
    }

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
        List<String> apiDataList = lines.map(line -> new org.json.JSONObject(line))
                .map(jsonObject -> jsonObject.getJSONArray("restaurants"))
                .map(jsonArray -> {
                    List<String> ob = new ArrayList<>();
                    for(int i =0; i < jsonArray.length();i++)
                        ob.add(jsonArray.getJSONObject(i).getJSONObject("restaurant").get("location").toString());
                    return ob;
                })
                .flatMap(list -> list.stream())
                .collect(Collectors.toList());
        return apiDataList;
    }


    private  HttpURLConnection getConnection() throws IOException {
        String urlString = "https://developers.zomato.com/api/v2.1/search?entity_id=292&entity_type=city";
        HttpURLConnection connection;
        final URL url = new URL(urlString);
        connection = getHttpURLConnection(connectTimeout, url);
        return connection;
    }
    private HttpURLConnection getHttpURLConnection(int connectTimeout, URL url) throws IOException {
        connection = (HttpURLConnection) url.openConnection();
        connection.setConnectTimeout(connectTimeout);
        connection.setRequestMethod(HttpMethod.GET);
        connection.setRequestProperty("user-key", System.getenv("USER_KEY"));
        connection.setRequestProperty("application", "json");
        return connection;
    }
}
