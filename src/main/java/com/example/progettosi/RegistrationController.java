package com.example.progettosi;
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
import java.util.Timer;
import java.util.TimerTask;

public class RegistrationController {
    @FXML
    private TextField inputName;

    @FXML
    private TextField inputSurname;

    @FXML
    private TextField inputSecurityNumber;

    @FXML
    private TextField inputCardNumber;

    @FXML
    private Button sendButton;

    @FXML
    private Label confirmationLabel;

    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    protected void onSendButtonClick(){
        String name = inputName.getCharacters().toString();
        String surname = inputSurname.getCharacters().toString();
        String securityNumber = inputSecurityNumber.getCharacters().toString();
        String cardNumber = inputCardNumber.getCharacters().toString();

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