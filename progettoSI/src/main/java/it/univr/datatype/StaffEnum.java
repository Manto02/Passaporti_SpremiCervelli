package it.univr.datatype;

public enum StaffEnum {
    USERNAME("USERNAME"),
    PASSWORD("PASSWORD"),
    PLACE("LUOGO");

    public final String value;
    StaffEnum(String label) {
        this.value = label;
    }

    public static String[] getDatabaseColumns(){
        String[] database_columns = {
                "USERNAME",
                "PASSWORD",
                "LUOGO"
        };
        return database_columns;
    }
}
