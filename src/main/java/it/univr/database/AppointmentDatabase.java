package it.univr.database;

import it.univr.datatype.Appointment;
import it.univr.datatype.AppointmentEnum;
import it.univr.datatype.CitizensEnum;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AppointmentDatabase extends DatabaseMethods{

    String table;
    @Override
    public void createNewTable(String table_name) {

        String cwd = System.getProperty("user.dir");
        String url = "jdbc:sqlite:" + cwd + "/database.db";

        String sql = "CREATE TABLE IF NOT EXISTS " + table_name + "(" +
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

    public void insert(String table_name, String[] database_columns, String... parameters) throws SQLException {
        //stringa di comando
        String sql = "INSERT INTO " + table_name + "(APPUNTAMENTO,TIPOLOGIA_APPUNTAMENTO,LUOGO,DATA,ORA) VALUES (?,?,?,?,?)";

        try (Connection conn = connect()) {
            //Preparestatement serve per l'assegnazione di parametri
            PreparedStatement psmt = conn.prepareStatement(sql);
            for (int i = 0; i < parameters.length; i++) {
                psmt.setString(i + 1, parameters[i]);
            }
            psmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    @Override
    public ArrayList<Appointment> selectData(String table_name, String... database_columns) {

        ArrayList<Appointment> appointments = new ArrayList<>();
        String sql = "SELECT *"  + " FROM " + table_name;

        try(Connection conn = connect()){

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            ArrayList<String> data = new ArrayList<>();

            while (rs.next()){
                for (int i = 1; i < database_columns.length; i++) {
                    data.add(rs.getString(database_columns[i]));
                }
                Appointment appointment = new Appointment(data);
                data.clear();
                appointments.add(appointment);
            }

        }catch (SQLException e){
            System.out.println(e.getMessage());
        }

        return appointments;
    }

    @Override
    public ArrayList<Appointment> selectFilteredData(String table_name, String operator, String param, String compare) {

        String sql = "SELECT * FROM " + table_name + " WHERE " + param + " " +operator +  " ?";
        ArrayList<Appointment> appointments = new ArrayList<>();

        try(Connection conn = connect()){
            PreparedStatement pr = conn.prepareStatement(sql);
            pr.setString(1, compare);
            ResultSet rs = pr.executeQuery();
            ArrayList<String> data = new ArrayList<>();

            while (rs.next()){
                for (int i = 1; i < AppointmentEnum.getDatabaseColumns().length; i++) {
                    data.add(rs.getString(AppointmentEnum.getDatabaseColumns()[i]));
                }
                Appointment appointment = new Appointment(data);
                data.clear();
                appointments.add(appointment);
            }

        }catch (SQLException e){
            System.out.println(e.getMessage());
        }

        return appointments;

    }

    public ArrayList<Appointment> selectFilteredDataMultiple(String table_name, String operator, ArrayList<String> param, ArrayList<String> compare) {


        String sql = "SELECT * FROM " + table_name+" WHERE ";
        for(int i = 0; i < param.size(); i++){
            sql +=  param.get(i) + " " + operator + " ?";
            if( i != param.size()-1){
                sql += " AND ";
            }
        }
        System.out.println(sql);

        ArrayList<Appointment> appointments = new ArrayList<>();

        try(Connection conn = connect()){
            PreparedStatement pr = conn.prepareStatement(sql);
            for(int i = 1; i <= compare.size() ; i++ ){
                pr.setString(i, compare.get(i-1));
            }

            ResultSet rs = pr.executeQuery();
            ArrayList<String> data = new ArrayList<>();

            while (rs.next()){
                for (int i = 1; i < AppointmentEnum.getDatabaseColumns().length; i++) {
                    data.add(rs.getString(AppointmentEnum.getDatabaseColumns()[i]));
                }
                Appointment appointment = new Appointment(data);
                data.clear();
                appointments.add(appointment);
            }

        }catch (SQLException e){
            System.out.println(e.getMessage());
        }

        return appointments;

    }
}
