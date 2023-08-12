package it.univr.datatype;

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
            return day + "/" + month + "/" + year; //in questo caso potrei scrivere this, ma non essendoci ambiguità posso non usarlo
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
            switch (this.month){
                case 2:
                    if(day > 28)
                        ris = false;
                    break;
                case 4:
                    if(day > 30)
                        ris = false;
                    break;
                case 6:
                    if(day > 30)
                        ris = false;
                    break;
                case 9:
                    if(day > 30)
                        ris = false;
                    break;
                case 11:
                    if(day > 30)
                        ris = false;
                    break;
                default:
                    ris = true;
            }
            return ris;
        }
    static char[] mese_fiscale = {'A', 'B', 'C', 'D', 'E', 'H', 'L', 'M', 'P', 'R', 'S', 'T'};

    public static String buildCodiceFiscale(String cognome, String nome, Data date){
        String codice = "";
        String code_cognome = "";
        String code_nome = "";
        String code_anno = "";
        String code_giorno = "";
        String code_mese = "";
        String code_comune = "";
        String code_controllo = "";
        Random random = new Random();

        int anno = date.getYear();
        for (int i = 0; i < 2; i++) {
            code_anno = anno % 10 + code_anno;
            anno = anno / 10;
        }

        code_mese = String.valueOf(mese_fiscale[date.getMonth()]);

        if(date.getDay() >= 10)
            code_giorno = String.valueOf(date.getDay());
        else{
            code_giorno = "0" + String.valueOf(date.getDay());
        }

        String cogn = cognome.toUpperCase();
        int range = cognome.length();
        for (int i = 0; i < 3; i++) {
            code_cognome += cogn.charAt(random.nextInt(range));
        }

        String nom = nome.toUpperCase();
        range = nome.length();
        for (int i = 0; i < 3; i++) {
            code_nome += nom.charAt(random.nextInt(range));
        }

        code_controllo = String.valueOf(mese_fiscale[random.nextInt(12)]);

        code_comune = String.valueOf(mese_fiscale[random.nextInt(12)]) + String.valueOf(random.nextInt(999 - 100) + 100);

        codice = code_cognome + code_nome + code_anno + code_mese + code_giorno + code_comune + code_controllo;
        return codice;
    };
}
