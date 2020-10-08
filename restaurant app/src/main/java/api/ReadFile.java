package api;

import java.io.*;
import java.net.URISyntaxException;
import java.nio.file.Path;

public class ReadFile {
    public final String fullPath;

    public ReadFile( String fileName) throws URISyntaxException{
        this.fullPath = createPath(fileName);;
    }

    public String createPath(String fileName) throws URISyntaxException {
        File dirs = new File(getPath());
        if (!dirs.exists())
            dirs.mkdirs();
        return getPath() + fileName;
    }
    private static String getPath() throws URISyntaxException {
        String path = ClassLoader.getSystemClassLoader().getResource("").toURI().getPath();
        return path;
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


