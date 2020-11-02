package api;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class WriteFile extends ReadFile {

    public WriteFile(String fileName) throws IOException {
        super(fileName);
    }

    public void writeData(List<String> jsonObjectList) throws IOException {
        FileWriter fw = new FileWriter(fullPath.toString());

        jsonObjectList.forEach(line -> {
            try {
                fw.write(line);
                fw.write("\n");

            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        fw.close();
    }
}


