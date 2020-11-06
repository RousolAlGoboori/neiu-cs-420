import data.models.RestaurantDomain;
import data.models.RestaurantZipEnum;
import data.utils.RestaurantUtils;
import data.view.DisplayComboBoxes;
import data.view.Listener;
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
    Scene scene1, scene2;

    public static void main(String[] args) {

        launch(args);
    }
    @Override
    public void start(Stage primaryStage) throws IOException {
        RestaurantUtils.read("output.txt");
        comboBoxes = new DisplayComboBoxes();
        CreateScene1(primaryStage);
        BorderPane r = CreateScene2();
        Listener.CreateRadioButtons(r);
    }

    private BorderPane CreateScene2() {
        Stage primaryStage1 = new Stage();
        primaryStage1.setTitle("Restaurant charts ");
        BorderPane r = new BorderPane();
        scene2 = new Scene(r, 600, 600);
        primaryStage1.setScene(scene2);
        primaryStage1.show();
        return r;
    }

    private void CreateScene1(Stage primaryStage) {
        BorderPane borderPane = new BorderPane();
        setUpBorder1(borderPane);
        scene1 = new Scene(borderPane,800,200);
        primaryStage.setScene(scene1);
        primaryStage.setTitle("Restaurant App");
        primaryStage.show();
    }

    private void setUpBorder1(BorderPane borderPane){
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

