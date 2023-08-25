package it.univr.datatype;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Random;

public class Appointment implements Comparable<Appointment>{

    static String[] type = {
      "Ritiro",
      "Rilascio"
    };

    static String[] rilascio_type={
           "nuovo",
           "furto",
           "smarrimento",
           "deterioramento",
           "scadenza"
    };
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
    static String[] hours ={
            "09:",
            "10:",
            "11:",
            "12:",
            "13:",
            "14:",
            "15:",
            "16:",
            "17:"
    };
    static String[] minutes = {
            "00",
            "30"
    };


    String appointment, appointment_type, place, date, time;

    public String getAppointment() {
        return appointment;
    }

    public String getAppointment_type() {
        return appointment_type;
    }

    public String getPlace() {
        return place;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public Appointment(String appointment, String appointment_type, String place, String date, String time) {

        this.appointment = appointment;
        this.appointment_type = appointment_type;
        this.place = place;
        this.date = date;
        this.time = time;

    }

    public Appointment(ArrayList<String> parameters){

        if(parameters.size() != 5)
            throw new IllegalArgumentException();

        this.appointment = parameters.get(0);
        this.appointment_type = parameters.get(1);
        this.place = parameters.get(2);
        this.date = parameters.get(3);
        this.time = parameters.get(4);

    }

    public String toString(){
        return appointment + "\t" + appointment_type + "\t" + place + "\t" + date + "\t" + time;
    }



    @Override
    public int compareTo(Appointment other) {
        LocalTime local_time1 = Data.timeFormat(this.getTime());
        LocalTime local_time2 = Data.timeFormat(other.getTime());
        return local_time1.compareTo(local_time2);
    }

    public static Appointment BuildRandomAppointment(){
        Random random = new Random();
        String appuntamento = type[random.nextInt(type.length)];
        String tipologia = rilascio_type[random.nextInt(rilascio_type.length)];
        if(appuntamento.equals("Ritiro"))
            tipologia = "nuovo";
        Data date = new Data(random.nextInt(32 -1) + 1, random.nextInt(13 -9)+9 ,  2023);
        Appointment appointment = new Appointment(appuntamento,tipologia,
                luoghi[random.nextInt(luoghi.length)],date.toString(),
                hours[random.nextInt(hours.length)]+minutes[random.nextInt(minutes.length)]
        );
        return appointment;
    }
}
