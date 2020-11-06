package data.view;

import data.models.RestaurantZipEnum;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.util.Arrays;

import static data.utils.ZipCodeCountData.getZipCodeCount;

public class PieChartNumberOfRestaurants {

   public static ObservableList<javafx.scene.chart.PieChart.Data> getChartData() {
        ObservableList<javafx.scene.chart.PieChart.Data> observableList = FXCollections.observableArrayList();
        Arrays.stream(RestaurantZipEnum.values())
                                      .forEach(zipped -> observableList
                                      .add(new javafx.scene.chart.PieChart.Data(zipped.toString(),getZipCodeCount().get(zipped.ordinal()))));
        return observableList;
    }
}
