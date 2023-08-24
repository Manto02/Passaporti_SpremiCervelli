package com.example.cancella;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class UserHourPicker {

    @FXML
    private TableView<HourSlot> table;

    @FXML
    private TableColumn<HourSlot, String> orari_column;

    @FXML
    private Label orari_label;

    @FXML
    private TableColumn<HourSlot, String> disponibilita_column;

    private HourSlot hour_slot = new HourSlot("", false);

    public static class HourSlot {
        private final SimpleStringProperty orari;
        private final boolean disponibilita;

        public HourSlot(String orari, boolean disponibilita) {
            this.orari = new SimpleStringProperty(orari);
            this.disponibilita = disponibilita;
        }

        public String getOrari() {
            return orari.get();
        }

        public SimpleStringProperty orariProperty() {
            return orari;
        }

        public String disponibilitaToString() {
            return (disponibilita)? "libero" : "occupato";
        }

        public SimpleStringProperty disponibilitaProperty() {
            return (disponibilita)? new SimpleStringProperty("libero") : new SimpleStringProperty("occupato") ;
        }
    }

    @FXML
    public void initialize() {

        //* inserire quà i dati
        ObservableList<HourSlot> list_data = FXCollections.observableArrayList(
                new HourSlot("08:00", true),
                new HourSlot("09:00", false),
                new HourSlot("10:00", true),
                new HourSlot("11:00", true),
                new HourSlot("12:00", false),
                new HourSlot("13:00", false),
                new HourSlot("14:00", true),
                new HourSlot("15:00", false),
                new HourSlot("16:00", true)
                // Add more data as needed
        );


        //! queste 2 righe sono magia nera, non toccarle
        orari_column.setCellValueFactory(cellData -> cellData.getValue().orariProperty());
        disponibilita_column.setCellValueFactory(cellData -> cellData.getValue().disponibilitaProperty());

        //* impostiamo come sono visualizzate le righe (se le commenti togli i COLORI)
        disponibilita_column.setCellFactory(column -> new TableCell<HourSlot, String>() {
            // stile del testo puoi ignorarlo
            @Override
            protected void updateItem(String item, boolean empty) {
                if (item == null || empty) {
                    setText(null);
                    setStyle("");
                } else {
                    setText(item);
                    // se è occupato rosso
                    if (item.equals(new HourSlot("", false).disponibilitaToString())) {
                        setTextFill(Color.RED);
                    } else { // se libero verde
                        setTextFill(Color.GREEN);
                    }
                }
            }
        });


        //* inserisce i dati
        table.setItems(list_data);

        //* QUANDO LO CLICKI
        table.setOnMouseClicked(event -> {
            HourSlot selectedHourSlot = table.getSelectionModel().getSelectedItem();
            if (selectedHourSlot != null && selectedHourSlot.disponibilita) { // non nullo e libero

                hour_slot = selectedHourSlot;
                System.out.println("Clicked on: " + selectedHourSlot.getOrari() + " - " + selectedHourSlot.disponibilitaToString());

                orari_label.setText("confermi di voler prenotare alle " + selectedHourSlot.getOrari() + "? ");
            }
        });
    }



    @FXML
    void prenota(ActionEvent event) {

    }
}