package it.univr.datatype;

import java.util.ArrayList;

public class Booking {
    public String getUsername() {
        return username;
    }

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

    String username, appointment, appointment_type, place, date, time;

    public Booking(String username, String appointment, String appointment_type, String place, String date, String time) {
        this.username = username;
        this.appointment = appointment;
        this.appointment_type = appointment_type;
        this.place = place;
        this.date = date;
        this.time = time;
    }

    public Booking(ArrayList<String> parameters) {

        if(parameters.size() != 6)
            throw new IllegalArgumentException();

        this.username = parameters.get(0);
        this.appointment = parameters.get(1);
        this.appointment_type = parameters.get(2);
        this.place = parameters.get(3);
        this.date = parameters.get(4);
        this.time = parameters.get(5);

    }

    public String toString(){
        return  username + "\t" + appointment + "\t" + appointment_type + "\t" + place + "\t" + date + "\t" + time;
    }
}
