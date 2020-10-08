package javaFx;

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


public class DisplayRestaurantData extends Application {


    public static void main(String[] args) {

        launch(args);
    }
    @Override
    public void start(Stage primaryStage) throws IOException {

        RestaurantUtils.read("output.txt");
        final ComboBox<RestaurantZipEnum> comboBox1 = new ComboBox<>();
        comboBox1.setPromptText(" --Select a value-- ");
        final ComboBox<Domain> comboBox2 = getDomainComboBox();
        ComboJavaFx(comboBox1, comboBox2);
        borderJavaFx(primaryStage, comboBox1, comboBox2);
    }

    private ComboBox<Domain> getDomainComboBox() {
        final ComboBox<Domain> comboBox2 = new ComboBox<>();
        comboBox2.setPromptText(" --Select a value-- ");
        comboBox2.setVisible(false);
        return comboBox2;
    }

    private void ComboJavaFx(ComboBox<RestaurantZipEnum> comboBox1, ComboBox<Domain> comboBox2) {
        Map<RestaurantZipEnum, List<Domain>> rm = getRestaurantZipListMap(comboBox1);
        comboBox1.valueProperty().addListener(new ChangeListener<RestaurantZipEnum>(){
            @Override
            public void changed(ObservableValue<? extends RestaurantZipEnum> observable, RestaurantZipEnum oldValue, RestaurantZipEnum newValue) {
                comboBox2.getItems().clear();
                comboBox2.getItems().addAll(rm.get(newValue));
                comboBox2.setVisible(true);
            }
        });
    }

    private Map<RestaurantZipEnum, List<Domain>> getRestaurantZipListMap(ComboBox<RestaurantZipEnum> comboBox1) {
        Map<RestaurantZipEnum, List<Domain>> rm = RestaurantUtils.mapData();
        ObservableList<RestaurantZipEnum> zip = FXCollections.observableArrayList(rm.keySet());
        zip.sort(new RestaurantZipComparator());
        comboBox1.getItems().addAll(zip);
        return rm;
    }

    private void borderJavaFx(Stage primaryStage, ComboBox<RestaurantZipEnum> comboBox1, ComboBox<Domain> comboBox2) {
        BorderPane pane = new BorderPane();
        pane.setTop((comboBox1));
        pane.setCenter(comboBox2);
        Scene scene = new Scene(pane,700,700);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Restaurant App");
        primaryStage.show();
    }

}

