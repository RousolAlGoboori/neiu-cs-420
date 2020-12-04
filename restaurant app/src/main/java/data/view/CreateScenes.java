package data.view;

import data.models.RestaurantDomain;
import data.models.RestaurantZipEnum;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class CreateScenes {

    private DisplayComboBoxes comboBoxes;
    Scene scene1,scene2;

    public void AddScene1(Stage primaryStage) {
        comboBoxes = new DisplayComboBoxes();
        BorderPane borderPane = new BorderPane();
        setUpBorder1(borderPane);
        scene1 = new Scene(borderPane, 800, 200);
        primaryStage.setScene(scene1);
        primaryStage.setTitle("Restaurant App");
        primaryStage.show();
    }

    private void setUpBorder1(BorderPane borderPane) {
        HBox hBox = new HBox();
        setUpHBox(hBox);
        borderPane.setTop(hBox);
    }

    private void setUpHBox(HBox hBox) {
        hBox.setSpacing(10);
        ComboBox<RestaurantZipEnum> zip = comboBoxes.getComboBox1();
        ComboBox<RestaurantDomain> rd = comboBoxes.getComboBox2();
        Text text = comboBoxes.getText();
        hBox.getChildren().addAll(zip, rd, text);
        HBox.setMargin(zip, new Insets(20, 5, 5, 10));
        HBox.setMargin(rd, new Insets(20, 5, 5, 5));
        HBox.setMargin(text, new Insets(20, 5, 5, 5));

    }
    public BorderPane AddScene2() {
        Stage primaryStage1 = new Stage();
        primaryStage1.setTitle("Restaurant charts ");
        BorderPane pane = new BorderPane();
        scene2 = new Scene(pane, 600, 700);
        primaryStage1.setScene(scene2);
        primaryStage1.show();
        return pane;
    }
    public void getCharts() {
        BorderPane r = AddScene2();
        PieChart pieChart1 = new PieChart();
        PieChart pieChart2 = new PieChart();
        BarChart<String,  Number> barChart = BarChartAverageRating.getBarChart();
        pieChart1.setData(PieChartNumberOfRestaurants.getPieChart1());
        pieChart2.setData(PieChartCountByLocality.getPieChart2());
        GroupRadioButton groupRadioButton = new GroupRadioButton();
        Listener.changeListener(groupRadioButton.getGroup(), r, pieChart1, pieChart2, barChart);
        r.setTop(groupRadioButton.getHBox());
    }
}
