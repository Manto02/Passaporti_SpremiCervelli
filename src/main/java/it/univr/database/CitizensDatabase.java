package it.univr.database;

import it.univr.datatype.Citizens;
import it.univr.datatype.CitizensEnum;
import it.univr.datatype.Person;

import java.sql.*;
import java.util.ArrayList;

public class CitizensDatabase extends DatabaseMethods implements SignIn {

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
                data.clear();
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
                data.clear();
                citizens.add(citizen);
            }

        }catch (SQLException e){
            System.out.println(e.getMessage());
        }

        return citizens;
    }

    public boolean signIn(String username, String password) {

        String sql = "SELECT * FROM " + getTableName() + " WHERE USERNAME = ?";
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

    public boolean controlSignUp(String name, String surname, String date, String place, String tax_id){

        String error = " non corrisponde coi dati inseriti e quelli presenti nel sistema.\n";

        ArrayList people;
        ArrayList citizens;
        DatabaseMethods databaseMethods = new PeopleDatabase();
        Person person = null ;

        citizens = selectFilteredData(getTableName(), "=", "USERNAME", tax_id);
        if(citizens.size() != 0) {
            System.out.println("Utente gia' registrato nel sistema. Usa la pagina di login per accedere.\n");
            return false;
        }

        people = databaseMethods.selectFilteredData(databaseMethods.getTableName(), "=", "CODICE_FISCALE ", tax_id);
        try{person = (Person) people.get(0);
        }catch (IndexOutOfBoundsException e){
            System.out.println("sei un coglione");
        }

        if(person == null) {
            System.out.println("Le credenziali per la registrazione non corrispondono con l'anagrafica del sistema controllare i dati inseriti. " +
                    "Se i dati inseriti sono corretti ma l'errore si ripresenta contattare la seguente mail:\n ");
            return false;
        }

        if(!person.getName().equals(name)){
            System.out.println(name + error);
            return false;
        }
        else if(!person.getSurname().equals(surname)){
            System.out.println(surname + error);
            return false;
        }
        else if(!person.getDate().equals(date)){
            System.out.println(date + error);
            return false;
        }
        else if(!person.getPlace().equals(place)){
            System.out.println(place + error);
            return false;
        }
        else{
            System.out.println("I dati inseriti sono corretti. Procedere con l'inserimento Username e Password\n");
            return true;
        }

    }

    public String signUp(String username, String password) throws SQLException {
        try {
            insert(getTableName(), CitizensEnum.getDatabaseColumns(), username, password);
        }catch (SQLException e){
            System.out.println(e.getMessage());
            return "Errore nella registrazione.\n";
        }
        return "Registrazione eseguita con successo.\n";
    }

}
