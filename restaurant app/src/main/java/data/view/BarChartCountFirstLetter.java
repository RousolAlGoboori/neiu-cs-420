package data.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.XYChart;

import static data.utils.RestaurantUtils.getAllData;

public class BarChartCountFirstLetter {

     public static  ObservableList<XYChart.Series<String, Long>> getBarChartData() {
         ObservableList<XYChart.Series<String, Long>> observableList = FXCollections.observableArrayList();
         XYChart.Series<String, Long> aSeries = new XYChart.Series<>();
                 aSeries.getData().add(new XYChart.Data<>("R", getAllData().stream().filter( value -> value.getLocality().startsWith("R") ).count()));
                 aSeries.getData().add(new XYChart.Data<>("W", getAllData().stream().filter( value -> value.getLocality().startsWith("W") ).count()));
                 aSeries.getData().add(new XYChart.Data<>("L", getAllData().stream().filter( value -> value.getLocality().startsWith("L") ).count()));
                 aSeries.getData().add(new XYChart.Data<>("G", getAllData().stream().filter( value -> value.getLocality().startsWith("G") ).count()));
                 observableList.addAll(aSeries);
         return observableList;
    }

}