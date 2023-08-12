
import it.univr.datatype.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author sqlitetutorial.net
 */
public class Main {

    //100 nomi
    public static String[] nomi = {"Leonardo",
            "Alessandro",
            "Tommaso",
            "Francesco",
            "Lorenzo",
            "Edoardo",
            "Mattia",
            "Riccardo",
            "Gabriele",
            "Andrea",
            "Diego",
            "Matteo",
            "Nicolò",
            "Giuseppe",
            "Antonio",
            "Federico",
            "Pietro",
            "Samuele",
            "Giovanni",
            "Filippo",
            "Enea",
            "Davide",
            "Christian",
            "Gioele",
            "Giulio",
            "Michele",
            "Marco",
            "Gabriel",
            "Elia",
            "Luca",
            "Salvatore",
            "Vincenzo",
            "Emanuele",
            "Thomas",
            "Alessio",
            "Giacomo",
            "Nathan",
            "Liam",
            "Simone",
            "Samuel",
            "Jacopo",
            "Noah",
            "Daniele",
            "Giorgio",
            "Ettore",
            "Luigi",
            "Daniel",
            "Manuel",
            "Nicola",
            "Damiano",
            "Sofia",
            "Aurora",
            "Giulia",
            "Ginevra",
            "Beatrice",
            "Alice",
            "Vittoria",
            "Emma",
            "Ludovica",
            "Matilde",
            "Giorgia",
            "Camilla",
            "Chiara",
            "Anna",
            "Bianca",
            "Nicole",
            "Gaia",
            "Martina",
            "Greta",
            "Azzurra",
            "Sara",
            "Arianna",
            "Noemi",
            "Rebecca",
            "Mia",
            "Isabel",
            "Adele",
            "Chloe",
            "Elena",
            "Francesca",
            "Gioia",
            "Ambra",
            "Viola",
            "Carlotta",
            "Cecilia",
            "Diana",
            "Alessia",
            "Elisa",
            "Emily",
            "Marta",
            "Maria",
            "Margherita",
            "Anita",
            "Giada",
            "Eleonora",
            "Nina",
            "Miriam",
            "Asia",
            "Amelia",
            "Diletta"
    };
    ;

    //50 cognomi
    public static String[] cognomi = {

            "Rossi",
            "Ferrari",
            "Russo",
            "Esposito",
            "Bianchi",
            "Romano",
            "Gallo",
            "Costa",
            "Fontana",
            "Conti",
            "Ricci",
            "Bruno",
            "De Luca",
            "Moretti",
            "Marino",
            "Greco",
            "Barbieri",
            "Lombardi",
            "Giordano",
            "Cassano",
            "Colombo",
            "Mancini",
            "Longo",
            "Leone",
            "Martinelli",
            "Marchetti",
            "Martini",
            "Galli",
            "Gatti",
            "Mariani",
            "Ferrara",
            "Santoro",
            "Marini",
            "Bianco",
            "Conte",
            "Serra",
            "Farina",
            "Gentile",
            "Caruso",
            "Morelli",
            "Ferri",
            "Testa",
            "Ferraro",
            "Pellegrini",
            "Grassi",
            "Rossetti",
            "D'Angelo",
            "Bernardi",
            "Mazza",
            "Rizzi",
            "Natale"

    };

    //20 citta'
    static String[] luoghi = {"Milano",
            "Verona",
            "Padova",
            "Piacenza",
            "Modena",
            "Bergamo",
            "Bologna",
            "Bari",
            "Napoli",
            "Firenze",
            "Lucca",
            "Livorno",
            "Parma",
            "Torino",
            "Lecco",
            "Pesaro",
            "Ancona",
            "Como",
            "Brescia",
            "Ferrara"
    };


    public static void createNewDatabase(String fileName) {

        String url = "jdbc:sqlite:/home/manto/Scrivania/Database/" + fileName;

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
        String url = "jdbc:sqlite:/home/manto/Scrivania/Database/demografia_cittadini.db";

        String sql = "CREATE TABLE IF NOT EXISTS demografia (" +
                "CODICE_FISCALE text," +
                "NUMERO_TESSERA_SANITARIA text," +
                "COGNOME text," +
                "NOME text," +
                "LUOGO text," +
                "DATA_NASCITA text," +
                "CATEGORIA_APPATENENZA text" +
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
    public static void main(String[] args) throws SQLException {
        //createNewDatabase("demografia_cittadini");
        //createNewTable();
        ArrayList people = new ArrayList<>();
        Random random = new Random();
        String nome;
        String codice_fiscale;
        String cognome;
        String luogo;
        String categoria_appartenenza = "";
        String numero_tessera_sanitaria = "";
        //createNewDatabase("demografia_cittadini");
        //createNewTable();
        for (int i = 0; i < 100; i++) {
            nome = nomi[random.nextInt(100)];
            cognome = cognomi[random.nextInt(50)];
            luogo = luoghi[random.nextInt(20)];
            Data date;
            do {
                date = new Data(random.nextInt(32 -1) + 1, random.nextInt(12 - 1) + 1, random.nextInt(2015 - 1960) + 1960);
            }while (!date.isCorrect());
            for (int j = 0; j < 20; j++) {
                numero_tessera_sanitaria += String.valueOf(random.nextInt(10));
            }
            codice_fiscale = Data.buildCodiceFiscale(cognome, nome, date);
            people.add(new Person(codice_fiscale, numero_tessera_sanitaria, cognome, nome, luogo, date.toString(), categoria_appartenenza));
            numero_tessera_sanitaria = "";
        }
        /*for (Person person: people) {
            System.out.println(person.toString());
        }*/
        people.forEach(person -> System.out.println(person.toString()));
        System.out.println("Database riempito con 100 utenti");
        //DatabaseMethods db = new DatabaseMethods();
        //db.selectAll();
    }
}

