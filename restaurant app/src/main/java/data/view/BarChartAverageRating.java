package data.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

import java.util.ArrayList;
import java.util.List;

import static data.utils.RestaurantUtils.getAllData;

public class BarChartAverageRating {


    @SuppressWarnings("unchecked")
     public static  ObservableList<XYChart.Series<String, Number>> getBarChartData() {
         ObservableList<XYChart.Series<String,  Number>> observableList = FXCollections.observableArrayList();
         XYChart.Series<String,  Number> aSeries = new XYChart.Series<>();
         addData(aSeries);
         observableList.addAll(aSeries);
         return observableList;
    }

    private static void addData(XYChart.Series<String,  Number> aSeries) {
        List<String> localityNamesList=PieChartCountByLocality.getLocalityNames();
        ArrayList<Double> averagesList=new ArrayList<>();
        getAverage(localityNamesList, averagesList);
        localityNamesList
                         .forEach(value->aSeries.getData()
                         .add(new XYChart.Data<>(value, averagesList.get(localityNamesList.indexOf(value)))));
        }

    private static void getAverage(List<String> localityNamesList, ArrayList<Double> averagesList) {
        localityNamesList
                         .forEach(name->averagesList.add(getAllData()
                         .stream()
                         .filter(value->value.getLocality()
                         .equals(name)).mapToDouble(value->Double.parseDouble(value.getAggregate_rating()))
                         .sum()/(double)getAllData().stream().filter(value->value.getLocality().equals(name)).count()));
    }

    public static BarChart<String,  Number> getBarChart() {
        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();
        BarChart<String,  Number>  barChart = new BarChart<>( xAxis, yAxis, BarChartAverageRating.getBarChartData());
        barChart.setCategoryGap(0.2);
        barChart.setTitle("Average Rating By Locality");
        xAxis.setLabel("Locality");
        yAxis.setLabel("Value");
        return barChart;
    }


}