package it.univr;

import it.univr.database.CitizensDatabase;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class PasswordConfigurationController {

    @FXML
    private PasswordField nuova_password;
    @FXML
    private PasswordField conferma_password;
    @FXML
    private Button back_button;
    @FXML
    private Button conferma;
    @FXML
    private Label success;
    private String username;
    Parent root;
    Stage stage;
    Scene scene;

    protected void getUsername(String username){
        this.username = username;
    }
    @FXML
    public void onConfirmation(ActionEvent event) throws SQLException, IOException {
        System.out.println(username);
        if (nuova_password.getText().equals(conferma_password.getText()) && nuova_password != null) {
            CitizensDatabase cittadini = new CitizensDatabase();
            cittadini.signUp(username, nuova_password.getText());
            success.setVisible(true);
            root = FXMLLoader.load(getClass().getResource("login.fxml"));
            scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("GUIstyle.css").toExternalForm());
            stage = new Stage();
            stage.setScene(scene);
            stage.show();

        } else {
            System.out.println("fai schifo");
        }
    }



}
