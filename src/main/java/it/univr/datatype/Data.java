package it.univr.datatype;

import java.text.DateFormat;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Random;

public class Data{ // campi (fields) degli oggetti di tipo Date (stato)
        private int day;
        private int month; //i campi inizialmente sono inizializzati a 0
        private int year;

    public int getYear() {
        return year;
    }

    // costruttore
        public Data(int day, int month, int year) { //argomenti del costruttore
            this.day = day; //this indica l'oggetto che andiamo a creare facendo riferimento al campo e permette di eliminare le ambiguità
            this.month = month;
            this.year = year;
        }

        public String toString() { //ritorna String e il metodo non ha parametri (vuoto)
            String month_code = String.valueOf(month);
            String day_code = String.valueOf(day);
            if(month < 10)
                month_code = "0" + month;
            if(day < 10)
                day_code = "0" + day;
            return day_code + "/" + month_code + "/" + year; //in questo caso potrei scrivere this, ma non essendoci ambiguità posso non usarlo
        }

        public int getDay() {
            return day;
        }
        public int getMonth(){
            return month;
        }

        public boolean isCorrect(){
            boolean ris = true;
            if(getDay() == 0)
                return false;
            if((getMonth() == 2 || getMonth() == 4 || getMonth() == 6 ||
                    getMonth() == 9 || getMonth() == 11) && getDay() > 30)
                return false;
            return true;
        }

        public boolean isAdult(){
            boolean result = true;
            if(2023 - year < 18)
                result = false;
            return result;
        };
    static char[] tax_id_month = {'A', 'B', 'C', 'D', 'E', 'H', 'L', 'M', 'P', 'R', 'S', 'T'};

    public static String buildTaxIdCode(String surname, String name, Data date){
        String code;
        String code_surname = "";
        String code_name = "";
        String code_year = "";
        String code_day;
        String code_month;
        String code_city;
        String control_code;
        Random random = new Random();

        int year = date.getYear();
        for (int i = 0; i < 2; i++) {
            code_year = year % 10 + code_year;
            year = year / 10;
        }

        code_month = String.valueOf(tax_id_month[date.getMonth() - 1]);

        if(date.getDay() >= 10)
            code_day = String.valueOf(date.getDay());
        else
            code_day = "0" + String.valueOf(date.getDay());

        String sur_name = surname.toUpperCase();
        int range = surname.length();
        for (int i = 0; i < 3; i++)
            code_surname += sur_name.charAt(random.nextInt(range));

        String name_ = name.toUpperCase();
        range = name.length();
        for (int i = 0; i < 3; i++)
            code_name += name_.charAt(random.nextInt(range));

        control_code = String.valueOf(tax_id_month[random.nextInt(12)]);

        code_city = tax_id_month[random.nextInt(12)]
                + String.valueOf(random.nextInt(999 - 100) + 100);

        code = code_surname + code_name + code_year + code_month + code_day + code_city + control_code;

        return code;
    };

    public static LocalDate dateFormat(String string_date){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return LocalDate.parse(string_date,formatter);

    }
    public static LocalTime timeFormat(String string_time){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        return LocalTime.parse(string_time,formatter);
    }

    public String dateToStringFotmat(LocalDate date){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String date_to_string = date.format(formatter);
        return date_to_string;
    }
}
