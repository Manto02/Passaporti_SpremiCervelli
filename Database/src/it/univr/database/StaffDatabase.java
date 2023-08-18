package it.univr.database;

import it.univr.datatype.Citizens;
import it.univr.datatype.CitizensEnum;
import it.univr.datatype.Staff;
import it.univr.datatype.StaffEnum;

import java.sql.*;
import java.util.ArrayList;

public class StaffDatabase extends DatabaseMethods implements Login{

    public void createNewTable(String table_name){

        String cwd = System.getProperty("user.dir");
        String url = "jdbc:sqlite:" + cwd + "/database.db";

        String sql = "CREATE TABLE IF NOT EXISTS " + table_name + "(" +
                "USERNAME text," +
                "PASSWORD text," +
                "LUOGO text" +
                ")";

        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement()) {
            // create a new table
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    @Override
    public ArrayList<Staff> selectData(String table_name, String... database_columns) {

        ArrayList<Staff> staff = new ArrayList<>();
        String sql = "SELECT *"  + " FROM " + table_name;

        try(Connection conn = connect()){

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            ArrayList<String> data = new ArrayList<>();

            while (rs.next()){
                for (int i = 0; i < database_columns.length; i++) {
                    data.add(rs.getString(database_columns[i]));
                }
                Staff staff1 = new Staff(data);
                staff.add(staff1);
            }

        }catch (SQLException e){
            System.out.println(e.getMessage());
        }

        return staff;

    }

    @Override
    public ArrayList<Staff> selectFilteredData(String table_name, String operator, String param, String compare) {

        String sql = "SELECT * FROM " + table_name + " WHERE " + param + operator +  " ?";
        ArrayList<Staff> staff = new ArrayList<>();

        try(Connection conn = connect()){
            PreparedStatement pr = conn.prepareStatement(sql);
            pr.setString(1, compare);
            ResultSet rs = pr.executeQuery();
            ArrayList<String> data = new ArrayList<>();

            while (rs.next()){
                for (int i = 0; i < StaffEnum.getDatabaseColumns().length; i++) {
                    data.add(rs.getString(StaffEnum.getDatabaseColumns()[i]));
                }
                Staff staff1 = new Staff(data);
                staff.add(staff1);
            }

        }catch (SQLException e){
            System.out.println(e.getMessage());
        }

        return staff;
    }

    @Override
    public boolean isInDatabase(String username, String password) {

        String sql = "SELECT * FROM " + getTableName() + " WHERE USERNAME = " + username +" ?";
        Staff staff = null;

        try(Connection conn = connect()){
            PreparedStatement pr = conn.prepareStatement(sql);
            pr.setString(1, username);
            ResultSet rs = pr.executeQuery();
            ArrayList<String> data = new ArrayList<>();

            while (rs.next()){
                for (int i = 0; i < CitizensEnum.getDatabaseColumns().length; i++) {
                    data.add(rs.getString(CitizensEnum.getDatabaseColumns()[i]));
                }
                staff = new Staff(data);
            }

            if(staff == null)
                return false;

            if (!(staff.getPassword().equals(password)))
                return false;

        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return true;
    }
}
