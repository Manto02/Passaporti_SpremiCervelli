package com.example.progettosi;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;


import java.io.IOException;
import java.util.EventObject;

public class LoginController {

    @FXML
    PasswordField passwordCittadino;
    @FXML
    DatePicker date;
    @FXML
    Button registerButton;
    private Stage stage;
    private Scene scene;
    private Parent root;
    public void onRegisterButtonClick(ActionEvent event) throws IOException {
        //sostituisco la scena corrente con quella della registrazione dei dati
        root = FXMLLoader.load(getClass().getResource("registration.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void onLoginButtonClcik(ActionEvent event) throws IOException{
        /*TODO : mettere il controllo di username e password attraverso il Database,
             se quest'ultimo è stato un successo far lanciare la finestra per la selezione delle date(il file c'è già in /resoruces)
        */
    }


}
