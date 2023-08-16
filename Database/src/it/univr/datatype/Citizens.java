package it.univr.datatype;

import it.univr.database.CitizensDatabase;
import it.univr.database.DatabaseMethods;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;

public class Citizens {

    String username, password;
    DatabaseMethods databaseMethods = new CitizensDatabase();

    public Citizens(String username, String password) throws SQLException {

        this.username = username;
        this.password = password;

        databaseMethods.insert("cittadini", CitizensEnum.getDatabaseColumns(), username, password);
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


}
