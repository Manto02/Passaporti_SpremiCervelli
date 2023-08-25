package it.univr.datatype;

import it.univr.database.DatabaseMethods;
import it.univr.database.PeopleDatabase;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;

public class Person  {

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
            "Mantovani",
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
            "Menegatti",
            "Ferri",
            "Testa",
            "Ferraro",
            "Pellegrini",
            "Grassi",
            "Rossetti",
            "Bologna",
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

    static String[] categorie = {
            "Cittadino",
            "Cittadino con figli minori",
            "Cittadino con passaporto diplomatico",
            "Cittadino con passaporto di servizio",
            "Cittadino minorenne"
    };
    String name, tax_id_code, surname, place, date, belonging_category, health_card_number;
    DatabaseMethods databaseMethods = new PeopleDatabase();

    public String getName() {
        return name;
    }

    public String getTax_id_code() {
        return tax_id_code;
    }

    public String getSurname() {
        return surname;
    }

    public String getPlace() {
        return place;
    }

    public String getDate() {
        return date;
    }

    public Person(String tax_id_code, String health_card_number, String surname, String name, String place,
                  String date, String belonging_category) throws SQLException {

        this.tax_id_code = tax_id_code;
        this.health_card_number = health_card_number;
        this.surname = surname;
        this.name = name;
        this.place = place;
        this.date = date;
        this.belonging_category = belonging_category;

        databaseMethods.insert(databaseMethods.getTableName(), PeopleEnum.getDatabaseColumns(), tax_id_code, health_card_number,
                surname, name, place, date, belonging_category);

    }

    public Person(ArrayList<String> parameters){

       if(parameters.size() != 7)
           throw new IllegalArgumentException();

        this.tax_id_code = parameters.get(0);
        this.health_card_number = parameters.get(1);
        this.surname = parameters.get(2);
        this.name = parameters.get(3);
        this.place = parameters.get(4);
        this.date = parameters.get(5);
        this.belonging_category = parameters.get(6);

    }

    public static Person buildRandomPerson() throws SQLException {

        Random random = new Random();
        String name;
        String tax_id_code;
        String surname;
        String place;
        String belonging_category = "";
        String health_card_number = "";

            name = nomi[random.nextInt(100)];
            surname = cognomi[random.nextInt(50)];
            place = luoghi[random.nextInt(20)];
            Data date;

            do {
                date = new Data(random.nextInt(32 -1) + 1, random.nextInt(13 - 1) + 1, random.nextInt(2015 - 1960) + 1960);
            }while (!date.isCorrect());

            for (int j = 0; j < 20; j++) {
                health_card_number += String.valueOf(random.nextInt(10));
            }

            tax_id_code = Data.buildTaxIdCode(surname, name, date);

            if(!date.isAdult()){
                belonging_category = categorie[categorie.length - 1];
            }
            else
                belonging_category = categorie[random.nextInt(categorie.length - 1)];


           return new Person(tax_id_code, health_card_number, surname, name, place, date.toString(), belonging_category);

    }


    public String toString(){
        return name + "\t" + surname+ "\t" + place + "\t"+ date.toString()
                + "\t"+ tax_id_code + "\t"+ health_card_number + "\t"+ belonging_category;
    }
}