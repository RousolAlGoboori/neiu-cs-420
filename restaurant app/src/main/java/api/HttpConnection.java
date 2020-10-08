package api;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.ws.rs.HttpMethod;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
public class HttpConnection {

    private HttpURLConnection connection;
    private  final int connectTimeout = 1000;

    public JSONObject getData( ) throws IOException, ParseException {
        String jsonData = "";
        HttpURLConnection connection = getConnection();
        return getString(jsonData, connection);
    }

    private JSONObject getString(String jsonData, HttpURLConnection connection) throws IOException, ParseException {
      JSONObject json = null;
        if (connection.getResponseCode() == 200) {
            final InputStream inputStream = connection.getInputStream();
            final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            json = getJsonObject(jsonData, bufferedReader);
        }
        return json;
    }

    private JSONObject getJsonObject(String jsonData, BufferedReader bufferedReader) throws IOException, ParseException {
        JSONObject json;
        String line;
        while ((line = bufferedReader.readLine()) != null) {
             jsonData += line;
         }
        JSONParser parse = new JSONParser();
        json = (JSONObject) parse.parse(jsonData);
        return json;
    }

    private  HttpURLConnection getConnection() throws IOException {
        String urlString = "https://developers.zomato.com/api/v2.1/search?entity_id=292&entity_type=city";
        HttpURLConnection connection = null;
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
