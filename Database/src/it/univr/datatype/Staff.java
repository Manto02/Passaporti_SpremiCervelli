package it.univr.datatype;

import it.univr.database.DatabaseMethods;
import it.univr.database.StaffDatabase;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;

public class Staff {

    String username, password, luogo;
    DatabaseMethods databaseMethods = new StaffDatabase();

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getLuogo() {
        return luogo;
    }

    public Staff(String username, String password, String luogo) throws SQLException {

        this.username = username;
        this.password = password;
        this.luogo = luogo;

        databaseMethods.insert(databaseMethods.getTableName(), StaffEnum.getDatabaseColumns(), username, password, luogo);
    }

    public Staff(ArrayList<String> parameters) throws SQLException {

        if(parameters.size() != 3)
            throw new IllegalArgumentException();

        this.username = parameters.get(0);
        this.password = parameters.get(1);
        this.luogo = parameters.get(2);

    }
    public static Staff buildRandomStaff(Person person) throws SQLException {
        Random random = new Random();
        String password;

        password = person.name + "_" + String.valueOf(random.nextInt(101));

        return new Staff(person.tax_id_code, password, person.place);
    }
    public static Staff buildStaff(String username, String password, String luogo) throws SQLException {
        return new Staff(username, password, luogo);
    }

    public String toString(){
        return username + "\t" + password + "\t" + luogo;
    }
}
