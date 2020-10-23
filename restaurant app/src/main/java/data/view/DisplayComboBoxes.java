package data.view;

import data.models.RestaurantDomain;
import data.models.RestaurantZipEnum;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.text.Text;
import javafx.util.StringConverter;

import java.util.List;
import java.util.Map;

import static data.models.RestaurantUtils.getAllData;
import static data.models.RestaurantUtils.mapData;
import static javafx.collections.FXCollections.observableArrayList;

public class DisplayComboBoxes {
    private ComboBox<RestaurantZipEnum> comboBox1;
    private ComboBox<RestaurantDomain> comboBox2;
    private Text text;
    private Map<RestaurantZipEnum, List<RestaurantDomain>> rm;
    private ObservableList<RestaurantZipEnum> zip;

    public DisplayComboBoxes(){
        rm =mapData();
        zip=observableArrayList(rm.keySet());
        text =new Text();
        setUpComboBox1();
        setUpComboBox2();
    }
    private static class comboBox2StringConverter extends StringConverter<RestaurantDomain> {
        private final String SEP = ", address: ";
        @Override
        public String toString(RestaurantDomain restaurantDomain) {
           if (restaurantDomain == null)
               return null;
           else
               return restaurantDomain.getZiploc() + SEP + restaurantDomain.getAddress();
        }

         @Override
        public RestaurantDomain fromString(String string) {
           String zip = string.split(SEP)[0];
            for(RestaurantDomain restaurantDomain:getAllData()){
                if(restaurantDomain.getZiploc().equals(zip))
                    return restaurantDomain;
            }
            return null;
        }
    }

    private void setUpComboBox1(){
        comboBox1 = new ComboBox<>();
        comboBox1.getItems().addAll(sortZipCodes());
        comboBox1.setPromptText(" --Select a zip code-- ");
        comboBox1.valueProperty().addListener((observable, oldValue, newValue) -> {
            text.setVisible(false);
            comboBox2.getItems().clear();
            comboBox2.getItems().addAll(rm.get(newValue));
            comboBox2.setVisible(true);
        });
    }
    private ObservableList<RestaurantZipEnum> sortZipCodes(){
    return zip.sorted((o1, o2) -> {

        if( o1.getZip() < o2.getZip())
            return -1;
        else if( o1.getZip() > o2.getZip())
            return 1;
        else
            return 0;
    });
    }
    private void setUpComboBox2(){
        comboBox2 = new ComboBox<>();
        comboBox2.setPromptText(" --Select a value-- ");
        comboBox2.setConverter(new comboBox2StringConverter());
        comboBox2.setVisible(false);
        createCombBox2SelectionListener();
        handleComboBox2Update();
    }
    private void handleComboBox2Update(){
        comboBox2.setButtonCell(new ListCell<>(){
           @Override
            protected void  updateItem (RestaurantDomain restaurantDomain,boolean empty){
                   super.updateItem(restaurantDomain,empty);
                   if(empty || restaurantDomain == null)
                       setText(" --Select a value-- ");
                   else{
                       comboBox2StringConverter converter = new comboBox2StringConverter();
                       setText(converter.toString(restaurantDomain));
                   }
               }
        });
    }
    private void createCombBox2SelectionListener(){
        comboBox2.valueProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null){
                String text1 = "Locality: " + newValue.getLocality() +"\n" +
                        "country_id: " + newValue.getCountry_id() +"\n" +
                        "city_id: " + newValue.getCity_id() +"\n" ;
                text.setText(text1);
                text.setVisible(true);
            }
        });
    }

    public ComboBox<RestaurantZipEnum> getComboBox1() {
        return comboBox1;
    }

    public ComboBox<RestaurantDomain> getComboBox2() {
        return comboBox2;
    }

    public Text getText() {
        return text;
    }
}
