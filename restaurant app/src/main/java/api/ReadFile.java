package api;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

public class ReadFile {
    public final Path fullPath;

    public ReadFile( String fileName) throws IOException {
        this.fullPath = createPath(fileName);
    }

    public Path createPath(String fileName) throws IOException {

        if(!Files.exists(getPath())){
            Files.createDirectories(getPath());
        }

        return Path.of(getPath().toString(), fileName);
    }
    private static Path getPath() {
       return Path.of("build", "resources","apidata").toAbsolutePath();
    }

    public static Path getFilePath( String fileName ){
        return Path.of("build","resources", "main", fileName).toAbsolutePath();
    }

    public static BufferedReader readData( Path filePath ) throws FileNotFoundException {
        InputStream is = new FileInputStream(filePath.toString());
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
      return br;
    }
}


