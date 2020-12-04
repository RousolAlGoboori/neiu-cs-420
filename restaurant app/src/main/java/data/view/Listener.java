package data.view;

import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;

public class Listener {

    private Listener(){}
    public static void changeListener(ToggleGroup group, BorderPane r, PieChart pieChart, PieChart pi, BarChart<String, Number> barChart) {
        group.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
            if (group.getSelectedToggle() != null) {
                if (group.getSelectedToggle().getUserData() == "ZipCode") {
                    pieChart.setTitle("Percentage Number Of Restaurants By Zip Code");
                    r.setCenter(pieChart);
                }
                if (group.getSelectedToggle().getUserData() == "Locality") {
                    pi.setTitle("Percentage Number Of Locality  ");
                    pi.setLabelLineLength(5);
                    r.setCenter(pi);

                }
                if (group.getSelectedToggle().getUserData() == "Rating") {
                    r.setCenter(barChart);
                }
            }
        });
    }
}
