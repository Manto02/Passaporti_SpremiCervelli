package it.univr.database;

import it.univr.datatype.Citizens;
import it.univr.datatype.CitizensEnum;

import java.sql.*;
import java.util.ArrayList;

public class CitizensDatabase extends DatabaseMethods implements Login{

    String table;
    @Override
    public void createNewTable(String table_name) {

        String cwd = System.getProperty("user.dir");
        String url = "jdbc:sqlite:" + cwd + "/database.db";

        String sql = "CREATE TABLE IF NOT EXISTS " + table_name + "(" +
                "USERNAME text," +
                "PASSWORD text" +
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
    public ArrayList<Citizens> selectData(String table_name, String... database_columns) {

        ArrayList<Citizens> citizens = new ArrayList<>();
        String sql = "SELECT *"  + " FROM " + table_name;

        try(Connection conn = connect()){

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            ArrayList<String> data = new ArrayList<>();

            while (rs.next()){
                for (int i = 0; i < database_columns.length; i++) {
                    data.add(rs.getString(database_columns[i]));
                }
                Citizens citizen = new Citizens(data);
                citizens.add(citizen);
            }

        }catch (SQLException e){
            System.out.println(e.getMessage());
        }

        return citizens;
    }

    @Override
    public ArrayList<Citizens> selectFilteredData(String table_name, String operator, String param, String compare) {

        String sql = "SELECT * FROM " + table_name + " WHERE " + param + operator +  " ?";
        ArrayList<Citizens> citizens = new ArrayList<>();

        try(Connection conn = connect()){
            PreparedStatement pr = conn.prepareStatement(sql);
            pr.setString(1, compare);
            ResultSet rs = pr.executeQuery();
            ArrayList<String> data = new ArrayList<>();

            while (rs.next()){
                for (int i = 0; i < CitizensEnum.getDatabaseColumns().length; i++) {
                    data.add(rs.getString(CitizensEnum.getDatabaseColumns()[i]));
                }
                Citizens citizen = new Citizens(data);
                citizens.add(citizen);
            }

        }catch (SQLException e){
            System.out.println(e.getMessage());
        }

        return citizens;
    }

    @Override
    public boolean isInDatabase(String username, String password) {

        String sql = "SELECT * FROM " + getTableName() + " WHERE USERNAME = " + username +" ?";
        Citizens citizen = null;

        try(Connection conn = connect()){
            PreparedStatement pr = conn.prepareStatement(sql);
            pr.setString(1, username);
            ResultSet rs = pr.executeQuery();
            ArrayList<String> data = new ArrayList<>();

            while (rs.next()){
                for (int i = 0; i < CitizensEnum.getDatabaseColumns().length; i++) {
                    data.add(rs.getString(CitizensEnum.getDatabaseColumns()[i]));
                }
                citizen = new Citizens(data);
            }

            if(citizen == null)
                return false;

            if (!(citizen.getPassword().equals(password)))
                return false;

        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return true;
    }
}
