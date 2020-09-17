package api;

import netscape.javascript.JSObject;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.*;
import java.net.URISyntaxException;

public class ReadFile {
    private final String fullPath;
    public ReadFile( String fileName ) throws URISyntaxException {
        this.fullPath = createPath(fileName);
    }
    private String createPath(String fileName) throws URISyntaxException {
        File dirs = new File(getPath());
        if (!dirs.exists())
            dirs.mkdirs();
        return getPath() + fileName;
    }
    private static String getPath() throws URISyntaxException {
        String path = ClassLoader.getSystemClassLoader().getResource("").toURI().getPath();
        path = path.substring(0, path.indexOf("classes"));
        path += "resources" + File.separator + "files" + File.separator;
        return path;
    }
    public void writeData(  JSONObject json) throws IOException {
       /* BufferedWriter bw = new BufferedWriter(new FileWriter(fullPath));
        bw.write( json );
        bw.close();*/
        FileWriter fw = new FileWriter(fullPath);
        JSONObject response = json;
        JSONArray results = (JSONArray) response.get("restaurants");
        for (Object r: results) {
            JSONObject result = (JSONObject) r;
            JSONObject result1 = (JSONObject) result.get("restaurant");
            System.out.println(result1.get("location"));
            fw.write(result1.get("location").toString());
            fw.write("\n");
        }
        fw.close();
    }
}
