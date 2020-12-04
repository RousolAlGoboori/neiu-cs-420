import data.utils.RestaurantUtils;
import data.view.CreateScenes;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;


public class DisplayRestaurantData extends Application {

    CreateScenes createScenes = new CreateScenes();
    public static void main(String[] args) {

        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        RestaurantUtils.read("output.txt");
        createScenes.AddScene1(primaryStage);
        createScenes.getCharts();

    }

}

