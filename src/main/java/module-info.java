module com.example.progettosi {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;
    requires java.sql;

    opens com.example.progettosi to javafx.fxml;
    exports com.example.progettosi;
}