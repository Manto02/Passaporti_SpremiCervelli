package it.univr.database;

import it.univr.datatype.PeopleEnum;
import it.univr.datatype.Person;

import java.sql.*;
import java.util.ArrayList;

public class PeopleDatabase extends DatabaseMethods{

    @Override //metodo che ritorna le persone del database
    public ArrayList<Person> selectData(String table_name, String... database_columns) {

        ArrayList<Person> people = new ArrayList<>();
        String sql = "SELECT *"  + " FROM " + table_name;

        try(Connection conn = connect()){

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            ArrayList<String> data = new ArrayList<>();

            while (rs.next()){
                for (int i = 0; i < database_columns.length; i++) {
                    data.add(rs.getString(database_columns[i]));
                }
                Person person = new Person(data);
                people.add(person);
            }

        }catch (SQLException e){
            System.out.println(e.getMessage());
        }

        return people;

    }

    @Override //metodo che ritorna persone filtrate secondo parametri dal database
    public ArrayList<Person> selectFilteredData(String table_name, String operator, String param, String compare) {

        String sql = "SELECT * FROM " + table_name + " WHERE " + param + operator +  " ?";
        ArrayList<Person> people = new ArrayList<>();

        try(Connection conn = connect()){
            PreparedStatement pr = conn.prepareStatement(sql);
            pr.setString(1, compare);
            ResultSet rs = pr.executeQuery();
            ArrayList<String> data = new ArrayList<>();

            while (rs.next()){
                for (int i = 0; i < PeopleEnum.getDatabaseColumns().length; i++) {
                    data.add(rs.getString(PeopleEnum.getDatabaseColumns()[i]));
                }
                Person person = new Person(data);
                people.add(person);
            }

        }catch (SQLException e){
            System.out.println(e.getMessage());
        }

        return people;

    }

    @Override
    public void createNewTable(String table_name) {
        String cwd = System.getProperty("user.dir");
        String url = "jdbc:sqlite:" + cwd + "/database.db";

        String sql = "CREATE TABLE IF NOT EXISTS " + table_name + "(" +
                "CODICE_FISCALE text," +
                "NUMERO_TESSERA_SANITARIA text," +
                "COGNOME text," +
                "NOME text," +
                "LUOGO text," +
                "DATA_NASCITA text," +
                "CATEGORIA_APPARTENENZA text" +
                ")";

        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement()) {
            // create a new table
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
