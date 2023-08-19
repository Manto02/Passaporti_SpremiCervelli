package it.univr;

import it.univr.database.CitizensDatabase;
import it.univr.database.DatabaseMethods;
import it.univr.datatype.Citizens;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.CacheHint;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;

public class LoginController {
    @FXML
    TextField usename_cittadino;

    @FXML
    PasswordField password_cittadino;
    @FXML
    Button registerButton;
    @FXML
    CheckBox staff_check;
    @FXML
    Label label;

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

        } else {
            label.setText("LOGIN");
            registerButton.setVisible(true);

        }
    }

    public void onLoginButtonClcik(ActionEvent event) throws IOException {
        /*TODO : mettere il controllo di username e password attraverso il Database,
             se quest'ultimo è stato un successo far lanciare la finestra per la selezione delle date(il file c'è già in /resoruces)
        */

            CitizensDatabase citizen_database = new CitizensDatabase();



            if(citizen_database.signIn(usename_cittadino.getText().toString(),password_cittadino.getText().toString())) {

                    root = FXMLLoader.load(getClass().getResource("designCarino.fxml"));
                    stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();
            }else{
                System.out.println("Username o password errata");
            }


    }

}
