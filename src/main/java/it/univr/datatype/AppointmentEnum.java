package it.univr.datatype;

public enum AppointmentEnum {
    ID("ID"),
    APPUNTAMENTO("APPUNTAMENTO"),
    TIPOLOGIA_APPUNTAMENTO("TIPOLOGIA_APPUNTAMENTO"),
    LUOGO("LUOGO"),
    DATA("DATA"),
    ORA("ORA");

    public final String value;

    AppointmentEnum(String label){
        this.value = label;
    }

    public static String[] getDatabaseColumns(){

        String[] database_columns = {
                "ID",
                "APPUNTAMENTO",
                "TIPOLOGIA_APPUNTAMENTO",
                "LUOGO",
                "DATA",
                "ORA"
        };
        return database_columns;
    }
}

