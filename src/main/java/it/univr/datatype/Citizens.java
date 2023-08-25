package it.univr.datatype;

import it.univr.database.AppointmentDatabase;
import it.univr.database.BookingDatabase;
import it.univr.database.CitizensDatabase;
import it.univr.database.DatabaseMethods;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;

public class Citizens {

    String username, password;
    DatabaseMethods databaseMethods = new CitizensDatabase();

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public Citizens(String username, String password) throws SQLException {

        this.username = username;
        this.password = password;

        databaseMethods.insert(databaseMethods.getTableName(), CitizensEnum.getDatabaseColumns(), username, password);
    }

    public Citizens(ArrayList<String> parameters) throws SQLException {

        this.username = parameters.get(0);
        this.password = parameters.get(1);

    }

    public static Citizens buildRandomCitizen(Person person) throws SQLException {
        Random random = new Random();
        String password;

        password = person.name + "_" + String.valueOf(random.nextInt(101));

        return new Citizens(person.tax_id_code, password);
    }

    public String toString(){
        return username + "\t" + password;
    }

    public void booking(Booking booking) throws SQLException {

        BookingDatabase bookingDatabase = new BookingDatabase();
        AppointmentDatabase appointmentDatabase = new AppointmentDatabase();
        try {
            bookingDatabase.insert(bookingDatabase.getTableName(), BookingEnum.getDatabaseColumns(), booking.username,
                    booking.appointment, booking.appointment_type, booking.place, booking.date, booking.time);
            System.out.println("Inserimento prenotazione andato a buon fine");
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }

        String[] delete_columns = {booking.appointment, booking.appointment_type,
                booking.place, booking.date, booking.time};
        int id;
        id = appointmentDatabase.selectId(appointmentDatabase.getTableName(), booking.appointment, booking.place, booking.date);
        bookingDatabase.delete(appointmentDatabase.getTableName(), id);
        System.out.println("ID della riga: " + id);
        System.out.println("Colonne eliminate dal database: " + delete_columns);

    };

}
