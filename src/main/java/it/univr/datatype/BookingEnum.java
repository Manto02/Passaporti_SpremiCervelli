package it.univr.datatype;

public enum BookingEnum {

    USERNAME("USERNAME"),
    APPUNTAMENTO("APPUNTAMENTO"),
    TIPOLOGIA_APPUNTAMENTO("TIPOLOGIA_APPUNTAMENTO"),
    LUOGO("LUOGO"),
    DATA("DATA"),
    ORA("ORA");

    public final String value;

    BookingEnum(String label){
        this.value = label;
    }

    public static String[] getDatabaseColumns(){

        String[] database_columns = {
                "USERNAME",
                "APPUNTAMENTO",
                "TIPOLOGIA_APPUNTAMENTO",
                "LUOGO",
                "DATA",
                "ORA"
        };
        return database_columns;
    }
}
