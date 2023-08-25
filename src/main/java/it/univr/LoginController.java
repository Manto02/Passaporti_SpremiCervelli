package it.univr;

import it.univr.database.CitizensDatabase;
import it.univr.database.DatabaseMethods;
import it.univr.database.StaffDatabase;
import it.univr.datatype.Citizens;
import it.univr.datatype.Staff;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.CacheHint;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.w3c.dom.Text;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    @FXML
    TextField usename_cittadino;
    @FXML
    TextField password_text;
    @FXML
    ToggleButton password_show;
    @FXML
    PasswordField password_cittadino;
    @FXML
    Button registerButton;
    @FXML
    CheckBox staff_check;
    @FXML
    Label label;
    private boolean staff = false ;
    private Stage stage;
    private Scene scene;
    private Parent root;


    public void onRegisterButtonClick(ActionEvent event) throws IOException {
        //sostituisco la scena corrente con quella della registrazione dei dati
        root = FXMLLoader.load(getClass().getResource("registration.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("GUIstyle.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }

    public void isStaff() {
        if (staff_check.isSelected()) {
            label.setText("LOGIN STAFF");
            registerButton.setVisible(false);
            this.staff  =  true;

        } else {
            label.setText("LOGIN");
            registerButton.setVisible(true);
            this.staff = false;
        }
    }

    public void onLoginButtonClcik(ActionEvent event) throws IOException {
        /*TODO : mettere il controllo di username e password attraverso il Database,
             se quest'ultimo è stato un successo far lanciare la finestra per la selezione delle date(il file c'è già in /resoruces)
        */
            String username = usename_cittadino.getText();
            String password = password_cittadino.getText();
            if(!staff_check.isSelected()) {
                CitizensDatabase citizen_database = new CitizensDatabase();
                if (citizen_database.signIn(username, password)) {

                    root = FXMLLoader.load(getClass().getResource("actitvity_selection.fxml"));
                    stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    scene = new Scene(root);
                    scene.getStylesheets().add(getClass().getResource("GUIstyle.css").toExternalForm());
                    stage.setScene(scene);
                    stage.show();
                } else {
                    System.out.println("Username o password errata");
                }
            }else{
                StaffDatabase staff_database = new StaffDatabase();
                
                if(staff_database.signIn(username, password)) {
                    FXMLLoader loader = new FXMLLoader((getClass().getResource("actitvity_selection.fxml")));
                    root = loader.load();
                    ActivitySelectionController controller = loader.getController();
                    controller.getStaff(staff);
                    List lista = staff_database.selectFilteredData(staff_database.getTableName(),"=","USERNAME",username);
                    Staff staff1 = (Staff) lista.get(0);
                    controller.getPlace(staff1.getLuogo());
                    controller.staffInvisibility();
                    stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                    scene = new Scene(root);
                    scene.getStylesheets().add(getClass().getResource("GUIstyle.css").toExternalForm());
                    stage.setScene(scene);
                    stage.show();
                }else{
                    System.out.println("Username o password errata");
                }
            }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        label.getStyleClass().add("outline");
        password_cittadino.textProperty().bindBidirectional(password_text.textProperty());
        password_cittadino.managedProperty().bind(password_show.selectedProperty().not());
        password_cittadino.visibleProperty().bind(password_show.selectedProperty().not());
        password_text.managedProperty().bind(password_show.selectedProperty());
        password_text.visibleProperty().bind(password_show.selectedProperty());

    }


}
