module com.example.cancella {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.cancella to javafx.fxml;
    exports com.example.cancella;
}