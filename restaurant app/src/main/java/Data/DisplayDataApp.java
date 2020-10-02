package Data;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.List;
import java.util.Map;


public class DisplayDataApp extends Application {


    public static void main(String[] args) {

        launch(args);
    }
    @Override
    public void start(Stage primaryStage) throws IOException {

        DataApp.read("output.txt");
        final ComboBox<String> comboBox1 = new ComboBox<>();
        comboBox1.setPromptText(" **Select a value** ");
        final ComboBox<RestaurantData> comboBox2 = new ComboBox<>();
        comboBox2.setVisible(false);
        ComboJavaFx(comboBox1, comboBox2);
        borderJavaFx(primaryStage, comboBox1, comboBox2);
    }

    private void ComboJavaFx(ComboBox<String> comboBox1, ComboBox<RestaurantData> comboBox2) {
        Map<String, List<RestaurantData>> rm = DataApp.mapData();
        ObservableList<String> Ranges = FXCollections.observableArrayList(rm.keySet());
        comboBox1.getItems().addAll(Ranges);
        comboBox1.valueProperty().addListener(new ChangeListener<String>(){
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                comboBox2.getItems().addAll(rm.get(newValue));
                comboBox2.setVisible(true);
            }
        });
    }

    private void borderJavaFx(Stage primaryStage, ComboBox<String> comboBox1, ComboBox<RestaurantData> comboBox2) {
        BorderPane pane = new BorderPane();
        pane.setTop((comboBox1));
        pane.setCenter(comboBox2);
        Scene scene = new Scene(pane,700,700);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Restaurant App");
        primaryStage.show();
    }

}
