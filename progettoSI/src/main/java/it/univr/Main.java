package it.univr;

import it.univr.database.CitizensDatabase;
import it.univr.database.DatabaseMethods;
import it.univr.database.PeopleDatabase;
import it.univr.datatype.Citizens;
import it.univr.datatype.Person;
import it.univr.datatype.Staff;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Main extends javafx.application.Application {
    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
        root.setId("pane");
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("GUIstyle.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) throws SQLException {






       launch();

    }
}