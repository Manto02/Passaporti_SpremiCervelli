package it.univr.datatype;

public class Person  {
    String name, tax_id_code, surname, place, date, belonging_category, health_card_number;

    public Person(String tax_id_code, String health_card_number, String surname, String name, String place, String date, String belonging_category) {
        this.tax_id_code = tax_id_code;
        this.health_card_number = health_card_number;
        this.surname = surname;
        this.name = name;
        this.place = place;
        this.date = date;
        this.belonging_category = belonging_category;

    }

    public String toString(){
        return name + "\t" + surname+ "\t" + place + "\t"+ date.toString()
                + "\t"+ tax_id_code + "\t"+ health_card_number + "\t"+ belonging_category;
    }
}