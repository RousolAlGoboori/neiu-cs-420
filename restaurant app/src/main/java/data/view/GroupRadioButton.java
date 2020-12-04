package data.view;

import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;

public class GroupRadioButton {

    private static RadioButton button1;
    private static RadioButton button2;
    private static RadioButton button3;
    private ToggleGroup group;
    private HBox hBox;

    public GroupRadioButton(){
        this.hBox = createHBox();
        this.group = new ToggleGroup();
        createRadioButton1();
        createRadioButton2();
        createRadioButton3();
        setUpBorder2();

    }
    private void createRadioButton1() {

        button1 = new RadioButton("Show Number Of Restaurants");
        button1.setToggleGroup(group);
        button1.setUserData("ZipCode");
    }
    private void createRadioButton2() {
        button2 = new RadioButton("Count Number Of Locality");
        button2.setToggleGroup(group);
        button2.setUserData("Locality");
    }
    private void createRadioButton3() {
        button3 = new RadioButton("Average Rating ");
        button3.setToggleGroup(group);
        button3.setUserData("Rating");
    }
    private void setUpBorder2() {

        hBox.getChildren().addAll(button1,button2,button3);
    }
    private HBox createHBox() {
        hBox = new HBox();
        return hBox;
    }
    public HBox getHBox() {
        return hBox;
    }
    public ToggleGroup getGroup() {
        return group;
    }

}
