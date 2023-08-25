package it.univr;

import it.univr.database.AppointmentDatabase;
import it.univr.database.CitizensDatabase;
import it.univr.database.DatabaseMethods;
import it.univr.database.PeopleDatabase;
import it.univr.datatype.*;
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
        List staff = new ArrayList();
        /*for (int i = 0; i < 20; i++) {
            staff.add(Staff.buildRandomStaff(Person.buildRandomPerson()));
        }*/
        /*AppointmentDatabase appointment_database_access = new AppointmentDatabase();
        for(int i = 0; i < 5000; i++){
            Appointment appointment = Appointment.BuildRandomAppointment();
            appointment_database_access.insert(appointment_database_access.getTableName(), AppointmentEnum.getDatabaseColumns(),appointment.getAppointment(),
                    appointment.getAppointment_type(),appointment.getPlace(),appointment.getDate(),appointment.getTime());
        }
        */


       launch();

    }
}