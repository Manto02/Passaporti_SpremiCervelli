package com.example.sus;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;



public class HelloApplication extends Application {
    private static boolean checkBox = false;
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 400, 320);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
        // loginPage
        if(checkBox){
            // login Staff
            if(Staff.LogIn(username, password)){

            }
            else{
                System.out.println("wrong credentials");
            }
        }
        else{
            // login citezen
            if(Citizens.LogIn(username, password)){

            }
            else{
                System.out.println("wrong credentials");
            }
        }
    }

}