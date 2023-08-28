package it.univr;


import it.univr.database.AppointmentDatabase;
import it.univr.database.BookingDatabase;
import it.univr.datatype.Appointment;
import it.univr.datatype.Booking;
import it.univr.datatype.Data;
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
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.*;


public class UserDatePicker implements Initializable {
    @FXML
    private Button back_button;

    @FXML
    private GridPane day_grid;

    @FXML
    private Button month_ahead;

    @FXML
    private Button month_back;

    @FXML
    private Label month_label;

    @FXML
    private Button prenota_button;

    @FXML
    private ChoiceBox<Integer> scroll_menu;

    @FXML
    private Label selected_date_label;

    private String place, activity, date, type;

    private boolean just_changed_month = false;
    private Rectangle selected_rectangle = new Rectangle(); // mi serve per la selezione dei giorni usa tmp_date
    private LocalDate tmp_date = LocalDate.ofYearDay(2021,1);
    private YearMonth currentYearMonth = YearMonth.now();
    private Map<LocalDate, Color> dateColorMap = new HashMap<>();
    int chosenYear = currentYearMonth.getYear();
    int chosenMonth = currentYearMonth.getMonth().getValue();

    YearMonth chosenYearMonth = currentYearMonth;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    Integer[] future_years = {currentYearMonth.getYear(), currentYearMonth.getYear()+1,currentYearMonth.getYear()+2,
            currentYearMonth.getYear()+3};
    double cellWidth = 90, cellHeight = 45;
    ArrayList<Label> list_day_labels = new ArrayList<>();
    ArrayList<StackPane> list_cell = new ArrayList<>();
    AppointmentDatabase appointment_database_access = new AppointmentDatabase();
    BookingDatabase booking_database_access = new BookingDatabase();
    List<Appointment> available_appointments;
    List<Booking> bookings;
    public void CreateCalendar() {
        ArrayList<String> param = new ArrayList<String>(){
            {
                add("APPUNTAMENTO");
                add("TIPOLOGIA_APPUNTAMENTO");
                add("LUOGO");

            }
        };
        ArrayList<String> compare = new ArrayList<String>(){
            {
                add(type);
                add(activity);
                add(place);
            }
        };
        month_label.setText(String.valueOf(YearMonth.of(chosenYear, chosenMonth)));

        available_appointments = appointment_database_access.selectFilteredDataMultiple(appointment_database_access.getTableName(),"=",param,compare);
        /*bookings = booking_database_access.selectFilteredData(booking_database_access.getTableName(),"=","LUOGO",place);
        for(Booking a: bookings){
            LocalDate date = Data.dateFormat(a.getDate());
            dateColorMap.put(date,Color.YELLOW);
        }*/
        for(Appointment a : available_appointments){
            LocalDate date = Data.dateFormat(a.getDate());
            dateColorMap.put(date,Color.GREEN);
        }


        /*dateColorMap.put(LocalDate.now(), Color.RED);
        dateColorMap.put(LocalDate.of(2023, 8, 29), Color.RED);
        dateColorMap.put(LocalDate.of(2023, 8, 16), Color.RED);
        dateColorMap.put(LocalDate.of(2023, 8, 24), Color.LIGHTGREEN);
        */
        for (int day = 1; day <= chosenYearMonth.lengthOfMonth(); day++) {

            LocalDate date = chosenYearMonth.atDay(day);
            Label dayLabel = new Label(String.valueOf(day));
            int row = date.getDayOfWeek().getValue() - 1;
            int col = (day + chosenYearMonth.atDay(1).getDayOfWeek().getValue() - 2) / 7;


            //* BACKGROUND
            StackPane cell = new StackPane();
            cell.setPrefSize(5,5);
            list_cell.add(cell);
            cell.getStyleClass().add("calendar-cell");
            cell.setPrefSize(cellWidth, cellHeight);
            Color color = getColorHex(date);
            Rectangle colorRect = new Rectangle(cellWidth*0.9, cellHeight*0.9, color);
            colorRect.autosize();
            colorRect.setId("rectangle");
            cell.getChildren().add(colorRect);


            // Add the cell to the GridPane
            day_grid.add(cell, row, col);

            //* DAY LABEL
            dayLabel.getStylesheets().add(getClass().getResource("label.css").toExternalForm());
            day_grid.add(dayLabel, row,col);

            list_day_labels.add(dayLabel);

            //* mouse click
            dayLabel.setOnMouseClicked(event ->handleDateClick(date,colorRect));
            cell.setOnMouseClicked(event ->handleDateClick(date,colorRect));
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

    //* di default è verde se la mappa dateColorMap ha un altro colore pianificato lo sostituisce
    private Color getColorHex(LocalDate date) {
        if(just_changed_month && date.isEqual(tmp_date))
            return Color.BLUE;
        return dateColorMap.getOrDefault(date, Color.LIGHTGREEN);

    }

    private void handleDateClick(LocalDate date, Rectangle rectangle) { //! aggiungere nuovi colori potrebbe dare bug
        just_changed_month = false;

        if(rectangle.getFill() == Color.RED){}
        else{
            System.out.println("Selected Date: " + date);

            //* resetta cella precedentemente selezionata
            selected_rectangle.setFill(getColorHex(tmp_date));

            //* colora il rettangolo selezionato
            rectangle.setFill(Color.BLUE);

            selected_rectangle = rectangle;
            tmp_date = date;

            selected_date_label.setText("data scelta: " + date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        }
        this.date = date.format(formatter);

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        scroll_menu.getItems().addAll(future_years);
        scroll_menu.setOnAction(actionEvent -> getYear());
        //visto che non viene creato in iniztialize ogni volta
        // che viene richiamata la classe bisogna chiamare il metodo CreateCalendar()
    }

    @FXML
    void MonthBack(ActionEvent event) {
        just_changed_month = true;
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
        just_changed_month = true;
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
        chosenYear = (int) scroll_menu.getValue();
        chosenYearMonth = YearMonth.of(chosenYear,chosenMonth);
        ClearGrid();
        CreateCalendar();
    };
    @FXML
    void ChooseHour(ActionEvent event) throws IOException {
        if(!Objects.equals(tmp_date, LocalDate.ofYearDay(2021, 1))){// se non hai ancora selezionato un giorno
            Stage stage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("UserHourPicker.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            UserHourPicker controller = fxmlLoader.getController();
            controller.setDatePlace(date,place);
            controller.setAppointment(type,activity);
            controller.buildTable();
            stage.setTitle("Calendario!");
            stage.setScene(scene);
            stage.show();
        }
        else {
            System.out.println("non hai selezionato alcuna data");
            selected_date_label.setText("NON HAI SELEZIONATO UNA DATA");
        }
    }

    protected void getPlace(String place){
        this.place = place;
    }
    protected void getActivity(String activity){
        this.activity = activity;
    }

    protected void getType(String type){    this.type = type;   }

}
/*
*cambia mese ricolora la data selezionata
* todo hour picker
* todo
* todo
* */