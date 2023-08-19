package it.univr;
import it.univr.database.CitizensDatabase;
import it.univr.database.PeopleDatabase;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Timer;
import java.util.TimerTask;

public class RegistrationController {
    @FXML
    private TextField input_name;

    @FXML
    private TextField input_surname;

    @FXML
    private TextField input_security_number;

    @FXML
    private TextField input_date_of_birth;
    @FXML
    private TextField input_place_of_birth;

    @FXML
    private Button sendButton;

    @FXML
    private Label confirmationLabel;

    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    protected void onSendButtonClick(ActionEvent event) throws SQLException, IOException {
        String name = input_name.getText();
        String surname = input_surname.getText();
        String security_number = input_security_number.getText();
        String date_of_birth = input_date_of_birth.getText();
        String place_of_birth = input_place_of_birth.getText();

        CitizensDatabase cittadini = new CitizensDatabase();
        if(cittadini.controlSignUp(name,surname,date_of_birth,place_of_birth,security_number)){

            FXMLLoader loader = new FXMLLoader((getClass().getResource("password_configuration.fxml")));
            root = loader.load();
            PasswordConfigurationController controller = loader.getController();
            controller.getUsername(security_number);

            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("GUIstyle.css").toExternalForm());
            stage.setScene(scene);
            stage.show();
        }

        // TODO:qui va il codice per l'inserimento dei dati elencati(si possono aggiungere altri) sopra nel database
    }


    @FXML
    protected void onGoBackButtonClick(ActionEvent event) throws IOException {
        //sostituisco la scena corrente con quella di login
        root = FXMLLoader.load(getClass().getResource("login.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("GUIstyle.css").toExternalForm());
        stage.setScene(scene);
        stage.show();

    }




}