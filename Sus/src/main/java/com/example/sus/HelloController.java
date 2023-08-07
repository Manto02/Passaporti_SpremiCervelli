package com.example.sus;

import java.util.*;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class HelloController {



    @FXML
    private Label Text_above;
    @FXML
    private Label Text_below;
    @FXML
    private Label welcomeText;
    @FXML
    private Button bottone;
    @FXML
    private Button bottone1;
    @FXML
    private TextField username;
    @FXML
    private CheckBox checkBox_login;

    private List<String> Utenti_Cittadini = new ArrayList();



    @FXML
    protected void onButtonClick(){
        String name = username.getCharacters().toString();
        if(Utenti_Cittadini.contains(name)!= true){
            welcomeText.setText("username non esistente");
        }else{
            welcomeText.setText(name);
        }

    }

    @FXML
    protected void onCheckBoxClick(){
        if(checkBox_login.isSelected()){
            Text_above.setText("id personale");
            Text_below.setText("password personale");
        }else{
            Text_above.setText("Codice Fiscale");
            Text_below.setText("password utente");
        }
    }

}



