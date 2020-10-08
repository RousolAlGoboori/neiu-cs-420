package api;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.FileWriter;
import java.io.IOException;
import java.net.URISyntaxException;
public class WriteFile extends ReadFile {

    public WriteFile(String fileName) throws URISyntaxException {
        super(fileName);
    }

    public void writeData(JSONObject json) throws IOException {
        FileWriter fw = new FileWriter(fullPath);
        JSONObject response = json;
        JSONArray results = (JSONArray) response.get("restaurants");
        writeJsonObject(fw, results);
        fw.close();
    }

    private void writeJsonObject(FileWriter fw, JSONArray results) throws IOException {
        for (Object r : results) {
            JSONObject result = (JSONObject) r;
            JSONObject result1 = (JSONObject) result.get("restaurant");
            System.out.println(result1.get("location"));
            fw.write(result1.get("location").toString());
            fw.write("\n");
        }
    }
}
