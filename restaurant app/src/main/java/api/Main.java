package api;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.net.URISyntaxException;

public class Main {
    public static void main(String[] args) throws ParseException {
        try {
            WriteFile wf = new WriteFile("output.txt");
            GetRequest s = new GetRequest();
            wf.writeData(s.getData());
        }catch (IOException ie){
            System.out.println(ie.toString());
        }catch (URISyntaxException u){
            System.out.println( u.toString());
        }
    }




}

