
import it.univr.database.CitizensDatabase;
import it.univr.database.DatabaseMethods;
import it.univr.database.PeopleDatabase;
import it.univr.database.StaffDatabase;
import it.univr.datatype.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author sqlitetutorial.net
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {

        DatabaseMethods peopledb = new PeopleDatabase();
        DatabaseMethods staffdb = new StaffDatabase();
        DatabaseMethods citizendb = new CitizensDatabase();
        peopledb.createNewTable("demografia");
        staffdb.createNewTable("staff");
        citizendb.createNewTable("cittadini");

        ArrayList people = new ArrayList<>();

        for (int i = 0; i < 20; i++) {
            people.add(Person.buildRandomPerson());
        }

        ArrayList staff = new ArrayList<>();

        for (Object person: people) {
            staff.add(Staff.buildRandomStaff((Person) person));
        }

        ArrayList citizens = new ArrayList<>();

        for (Object person: people) {
            citizens.add(Citizens.buildRandomCitizen((Person) person));
        }

        //staff.forEach(st -> System.out.println(st.toString()));
        System.out.println("PERSONA:");
        people = peopledb.selectFilteredData("demografia", "=", "CODICE_FISCALE", "CRIOOO60C01E847D");
        people.forEach(person -> System.out.println(person.toString()));

        System.out.println("\nSTAFF");
        staff = staffdb.selectFilteredData("staff", "=", "USERNAME", "INGULU76L09P305L");
        staff.forEach(st -> System.out.println(st.toString()));

        System.out.println("\nCITTADINO:");
        citizens = citizendb.selectFilteredData("cittadini", "=", "USERNAME", "CRIOOO60C01E847D");
        citizens.forEach(st -> System.out.println(st.toString()));

        Person person = (Person) people.get(0);





    }
}

