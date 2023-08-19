module com.example.progettosi {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;
    requires java.sql;

    opens it.univr to javafx.fxml;
    exports it.univr;

}