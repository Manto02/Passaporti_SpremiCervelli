package it.univr.database;

import it.univr.datatype.Appointment;
import it.univr.datatype.Booking;
import it.univr.datatype.BookingEnum;
import it.univr.datatype.CitizensEnum;

import java.sql.*;
import java.util.ArrayList;

public class BookingDatabase extends DatabaseMethods{

    String table;
    @Override
    public void createNewTable(String table_name) {

        String cwd = System.getProperty("user.dir");
        String url = "jdbc:sqlite:" + cwd + "/database.db";

        String sql = "CREATE TABLE IF NOT EXISTS " + table_name + "(" +
                "USERNAME text," +
                "APPUNTAMENTO text," +
                "TIPOLOGIA_APPUNTAMENTO text," +
                "LUOGO text," +
                "DATA text," +
                "ORA text" +
                ")";

        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement()) {
            // create a new table
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        table = table_name;
    }

    @Override
    public ArrayList<Booking> selectData(String table_name, String... database_columns) {

        ArrayList<Booking> bookings = new ArrayList<>();
        String sql = "SELECT *"  + " FROM " + table_name;

        try(Connection conn = connect()){

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            ArrayList<String> data = new ArrayList<>();

            while (rs.next()){
                for (int i = 0; i < database_columns.length; i++) {
                    data.add(rs.getString(database_columns[i]));
                }
                Booking booking = new Booking(data);
                data.clear();
                bookings.add(booking);
            }

        }catch (SQLException e){
            System.out.println(e.getMessage());
        }

        return bookings;
    }

    @Override
    public ArrayList<Booking> selectFilteredData(String table_name, String operator, String param, String compare) {

        String sql = "SELECT * FROM " + table_name + " WHERE " + param + operator +  " ?";
        ArrayList<Booking> bookings = new ArrayList<>();

        try(Connection conn = connect()){
            PreparedStatement pr = conn.prepareStatement(sql);
            pr.setString(1, compare);
            ResultSet rs = pr.executeQuery();
            ArrayList<String> data = new ArrayList<>();

            while (rs.next()){
                for (int i = 0; i < BookingEnum.getDatabaseColumns().length; i++) {
                    data.add(rs.getString(BookingEnum.getDatabaseColumns()[i]));
                }
                Booking booking = new Booking(data);
                data.clear();
                bookings.add(booking);
            }

        }catch (SQLException e){
            System.out.println(e.getMessage());
        }

        return bookings;

    }
}
