package it.univr;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ActivitySelectionController implements Initializable {

    boolean staff;
    @FXML
    ComboBox<String> activity_selection;
    @FXML
    ComboBox<String> type_selection;
    @FXML
    ComboBox<String> place_selection;
    @FXML
    Label must_insert_placeactivity;
    String activity, place, tipo;
    Parent root;
    Scene scene;
    Stage stage;
    static String[] luoghi = {
            "Milano",
            "Verona",
            "Padova",
            "Piacenza",
            "Modena",
            "Bergamo",
            "Bologna",
            "Bari",
            "Napoli",
            "Firenze",
            "Lucca",
            "Livorno",
            "Parma",
            "Torino",
            "Lecco",
            "Pesaro",
            "Ancona",
            "Como",
            "Brescia",
            "Ferrara"
    };
    List<String> type = new ArrayList<>(){{
        add("Ritiro");
        add("Rilascio");
    }};
    List<String> activities = new ArrayList<String>(){
        {
            add("nuovo");
            add("furto");
            add("scadenza");
            add("deterioramento");
            add("smarrimento");
        }
    };

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        type_selection.getItems().addAll(type);
        type_selection.setPromptText("Tipologia Appuntamento");
        
        activity_selection.getItems().addAll(activities);
        activity_selection.setOnAction(actionEvent->GetActivity());
        place_selection.getItems().addAll(luoghi);
        place_selection.setOnAction(actionEvent-> GetPlace());
        if(staff == true){
            place_selection.setVisible(false);
        }
        activity_selection.visibleProperty().bind(type_selection.valueProperty().isNotEqualTo("Ritiro"));

    }

    protected  String GetActivity(){
        if(activity_selection.isVisible())
            this.activity = activity_selection.getValue();
        else
            this.activity = "nuovo";
        System.out.println(this.activity);
        return activity;
    }
    protected String GetType(){
        this.tipo = type_selection.getValue();
        return tipo;
    }

    protected String GetPlace(){
        this.place = place_selection.getValue();
        System.out.println(this.place);
        return place;
    }



    @FXML
    protected void NextPage(ActionEvent event) throws IOException {
        activity = this.GetActivity();
        tipo = this.GetType();
        place = this.GetPlace();
        if(!staff) {
            if (activity!=null && place != null && tipo != null) {
                must_insert_placeactivity.setVisible(false);
                FXMLLoader loader = new FXMLLoader((getClass().getResource("UserDatePicker.fxml")));
                root = loader.load();
                UserDatePicker controller = loader.getController();
                controller.getPlace(place);
                controller.getActivity(activity);
                controller.getType(tipo);
                controller.CreateCalendar();
                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                scene.getStylesheets().add(getClass().getResource("GUIstyle.css").toExternalForm());
                stage.setScene(scene);
                stage.show();
            } else {
                must_insert_placeactivity.setVisible(true);
            }
        }else {
            must_insert_placeactivity.setVisible(false);
            FXMLLoader loader = new FXMLLoader((getClass().getResource("StaffDatePicker.fxml")));
            root = loader.load();
            StaffDatePicker controller = loader.getController();
            /*controller.getPlace(place);
            controller.getActivity(activity);
            */controller.CreateCalendar();
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("GUIstyle.css").toExternalForm());
            stage.setScene(scene);
            stage.show();
        }

    }

    protected void getStaff(Boolean staff){
        this.staff = staff;
    }
    protected void getPlace(String place){
        this.place =place;
    }

    protected void staffInvisibility(){
        place_selection.setVisible(false);
    }


}
