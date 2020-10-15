import data.view.DisplayComboBoxes;
import data.models.RestaurantDomain;
import data.models.RestaurantUtils;
import data.models.RestaurantZipEnum;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;


public class DisplayRestaurantData extends Application {

    private DisplayComboBoxes comboBoxes;
    public static void main(String[] args) {

        launch(args);
    }
    @Override
    public void start(Stage primaryStage) throws IOException {
        RestaurantUtils.read("output.txt");
        comboBoxes = new DisplayComboBoxes();
        BorderPane borderPane = new BorderPane();
        setUpBorder(borderPane);
        Scene scene = new Scene(borderPane,800,200);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Restaurant App");
        primaryStage.show();
    }
    private void setUpBorder(BorderPane borderPane){
        HBox hBox = new HBox();
        setUpHBox(hBox);
        borderPane.setTop(hBox);
    }
    private void setUpHBox(HBox hBox) {
        hBox.setSpacing(10);
        ComboBox<RestaurantZipEnum> zip = comboBoxes.getComboBox1();
        ComboBox<RestaurantDomain> rd = comboBoxes.getComboBox2();
        Text text = comboBoxes.getText();
        hBox.getChildren().addAll(zip, rd,text);
        HBox.setMargin(zip, new Insets(20,5,5,10));
        HBox.setMargin(rd, new Insets(20,5,5,5));
        HBox.setMargin(text, new Insets(20,5,5,5));

    }

}

