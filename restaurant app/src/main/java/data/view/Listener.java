package data.view;

import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

public class Listener {

    private Listener(){}
    public static void CreateRadioButtons(BorderPane r) {
        final ToggleGroup group = new ToggleGroup();
        RadioButton rb1 = new RadioButton("Show Number Of Restaurants");
        RadioButton rb2 = new RadioButton("Count Number Of Locality");
        RadioButton rb3 = new RadioButton("Count Number Of First Letters ");
        setToggleAndUser(r, group, rb1, rb2, rb3);
    }

    private static void setToggleAndUser(BorderPane r, ToggleGroup group, RadioButton rb1, RadioButton rb2, RadioButton rb3) {
        rb1.setToggleGroup(group);
        rb1.setUserData("A");
        rb2.setToggleGroup(group);
        rb2.setUserData("B");
        rb3.setToggleGroup(group);
        rb3.setUserData("C");
        setUpBorder2(r, rb1, rb2, rb3);
        Listener.getCharts(r, group);
    }

    public static void changeListener(ToggleGroup group, BorderPane r, PieChart pieChart, PieChart pi, BarChart<String, Double> barChart) {
        group.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
            if (group.getSelectedToggle() != null) {
                if (group.getSelectedToggle().getUserData() == "A") {
                    setPropertiesChart1(r, pieChart);
                }
                if (group.getSelectedToggle().getUserData() == "B") {
                    pi.setTitle("Percentage Number Of Locality  ");
                    r.setCenter(pi);
                }
                if (group.getSelectedToggle().getUserData() == "C") {
                    r.setCenter(barChart);
                }
            }
        });
    }
    private static void setPropertiesChart1(BorderPane r, PieChart pieChart) {
        pieChart.setTitle("Percentage Number Of Restaurants By Zip Code");
        r.setCenter(pieChart);
    }
    public static BarChart getBarChart() {
        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();
        BarChart barChart = new BarChart(xAxis, yAxis, BarChartCountFirstLetter.getBarChartData());
        barChart.setCategoryGap(0.2);
        barChart.setTitle("Count The Multiple First Letters using The Locality");
        xAxis.setLabel("Letter");
        yAxis.setLabel("Value");
        return barChart;
    }
   public static void getCharts(BorderPane r, ToggleGroup group) {
        PieChart pieChart = new PieChart();
        PieChart pi = new PieChart();
        BarChart barChart = Listener.getBarChart();

        pieChart.setData(PieChartNumberOfRestaurants.getChartData());
        pi.setData(PieChartCountByLocality.getChart());
        Listener.changeListener(group, r, pieChart, pi, barChart);
    }
    private static void setUpBorder2(BorderPane r, RadioButton rb1, RadioButton rb2, RadioButton rb3) {
        HBox hBox = new HBox();
        r.setTop(hBox);
        hBox.getChildren().addAll(rb1,rb2,rb3);
    }
}
