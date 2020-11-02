import api.HttpConnection;
import api.WriteFile;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
                try {
                    WriteFile wf = new WriteFile("output.txt");
                    HttpConnection hp = new HttpConnection();
                    wf.writeData(hp.getData());

                }catch (IOException ie){
                    System.out.println(ie.toString());
                }
    }
    }



