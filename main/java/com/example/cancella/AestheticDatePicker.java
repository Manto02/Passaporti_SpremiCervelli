package com.example.cancella;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.*;


public class AestheticDatePicker implements Initializable {
    @FXML
    private Button back_button;

    @FXML
    private GridPane day_grid;

    @FXML
    private Button month_ahead;

    @FXML
    private Button month_back;

    @FXML
    private Button prenota_button;
    @FXML
    private Label selected_date;


    @FXML
    private ChoiceBox<Integer> scroll_menu;

    private YearMonth currentYearMonth = YearMonth.now();
    private Map<LocalDate, Color> dateColorMap = new HashMap<>();
    int chosenYear = currentYearMonth.getYear();
    int chosenMonth = currentYearMonth.getMonth().getValue();

    YearMonth chosenYearMonth = currentYearMonth;

    Integer[] future_years = {currentYearMonth.getYear(), currentYearMonth.getYear()+1,currentYearMonth.getYear()+2,
            currentYearMonth.getYear()+3};
    double cellWidth = 100, cellHeight = 50;
    ArrayList<Label> list_day_labels = new ArrayList<>();
    ArrayList<StackPane> list_cell = new ArrayList<>();
    public void CreateCalendar() {
        dateColorMap.put(LocalDate.now(), Color.RED);
        dateColorMap.put(LocalDate.of(2023, 8, 29), Color.RED);
        dateColorMap.put(LocalDate.of(2023, 8, 16), Color.RED);
        dateColorMap.put(LocalDate.of(2023, 8, 24), Color.LIGHTGREEN);

        for (int day = 1; day <= chosenYearMonth.lengthOfMonth(); day++) {

            LocalDate date = chosenYearMonth.atDay(day);
            Label dayLabel = new Label(String.valueOf(day));
            int row = date.getDayOfWeek().getValue() - 1;
            int col = (day + chosenYearMonth.atDay(1).getDayOfWeek().getValue() - 2) / 7;


            //* BACKGROUND
            StackPane cell = new StackPane();
            list_cell.add(cell);
            cell.setPrefSize(cellWidth, cellHeight);
            Color color = getColorHex(date);
            Rectangle colorRect = new Rectangle(cellWidth, cellHeight, color);
            cell.getChildren().add(colorRect);


            // Add the cell to the GridPane
            day_grid.add(cell, row, col);

            //* DAY LABEL
            dayLabel.getStylesheets().add(getClass().getResource("label.css").toExternalForm());
            day_grid.add(dayLabel, row,col);

            list_day_labels.add(dayLabel);

            //* mouse click
            dayLabel.setOnMouseClicked(event ->handleDateClick(date));
            cell.setOnMouseClicked(event ->handleDateClick(date));
        }
    }

    private void ClearGrid(){
        for (Label day_label : list_day_labels) {
            day_label.setText("");
        }
        for (StackPane cell : list_cell) {
            // Close the StackPane by removing it from its parent
            if (cell.getParent() instanceof GridPane parentGrid) {
                parentGrid.getChildren().remove(cell);
            }
        }
   }

    //* di default è verde se la mappa dateColorMap ha un'altro colore pianificato lo sostistituisce
    private Color getColorHex(LocalDate date) {
        return dateColorMap.getOrDefault(date, Color.LIGHTGREEN);
    }

    private void handleDateClick(LocalDate date) {
        System.out.println("Selected Date: " + date);
        selected_date.setText("data scelta: " + date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        scroll_menu.getItems().addAll(future_years);
        scroll_menu.setOnAction(actionEvent -> getYear());
        CreateCalendar();
    }

    @FXML
    void MonthBack(ActionEvent event) {
        if(chosenMonth <= 1){
            chosenYear--;
            chosenMonth = 12;
        }
        else{
            chosenMonth--;
        }
        chosenYearMonth = YearMonth.of(chosenYear,chosenMonth);
        ClearGrid();
        CreateCalendar();
    }

    @FXML
    void MonthAhead(ActionEvent event) {

        if(chosenMonth >= 12){
            chosenYear++;
            chosenMonth = 1;
        }
        else{
            chosenMonth++;
        }
        chosenYearMonth = YearMonth.of(chosenYear,chosenMonth);
        ClearGrid();
        CreateCalendar();
    }

    public void getYear(){
        chosenYear = scroll_menu.getValue();
        chosenYearMonth = YearMonth.of(chosenYear,chosenMonth);
        ClearGrid();
        CreateCalendar();
    };
}

//todo: year selection
