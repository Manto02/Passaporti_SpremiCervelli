
import java.sql.*;

/**
 *
 * @author sqlitetutorial.net
 */
public class Main {

    /**
     * Connect to a sample database
     *
     * @param fileName the database file name
     */
    public static void createNewDatabase(String fileName) {

        String url = "jdbc:sqlite:/home/manto/Scrivania/Passaporti/Database/" + fileName;

        try (Connection conn = DriverManager.getConnection(url)) {
            if (conn != null) {
                DatabaseMetaData meta = conn.getMetaData();
                System.out.println("The driver name is " + meta.getDriverName());
                System.out.println("A new database has been created.");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void createNewTable(){
        String url = "jdbc:sqlite:/home/manto/Scrivania/Passaporti/Database/test.db";

        String sql = "CREATE TABLE IF NOT EXISTS topolino (" +
                "nome text," +
                "simpatia integer" +
                ")";

        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement()) {
            // create a new table
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //createNewDatabase("test.db");
        createNewTable();
    }
}