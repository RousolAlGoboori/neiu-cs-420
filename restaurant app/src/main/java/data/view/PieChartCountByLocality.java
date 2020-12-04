package data.view;

import data.models.RestaurantDomain;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import static data.utils.RestaurantUtils.getAllData;

public class PieChartCountByLocality {
    public static ObservableList<javafx.scene.chart.PieChart.Data> getPieChart2() {

        ObservableList<javafx.scene.chart.PieChart.Data> observableList = FXCollections.observableArrayList();
        AtomicInteger count = new AtomicInteger();
                              getLocalityCount()
                             .forEach( localityCount -> observableList
                             .add(new javafx.scene.chart.PieChart.Data(getLocalityNames()
                             .get(count.getAndIncrement()), localityCount)));
        return observableList;
    }

    private static List<Long> getLocalityCount() {
        List<Long> LocalityCountList = new ArrayList<>();
                                       getAllData().stream()
                                       .map(RestaurantDomain::getLocality)
                                       .distinct()
                                       .forEach(locality -> LocalityCountList
                                       .add(getAllData()
                                       .stream()
                                       .filter(data -> data.getLocality().equals(locality))
                                       .count()));
        return LocalityCountList;
    }
    public static List<String> getLocalityNames() {
          return getAllData()
                .stream()
                .map(RestaurantDomain::getLocality)
                .distinct()
                .collect(Collectors.toList());
    }
}
