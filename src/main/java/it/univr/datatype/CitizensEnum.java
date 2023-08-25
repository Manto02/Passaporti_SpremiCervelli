package it.univr.datatype;

public enum CitizensEnum {

    USERNAME("USERNAME"),
    PASSWORD("PASSWORD");

    public final String value;
    CitizensEnum(String label) {
        this.value = label;
    }

    public static String[] getDatabaseColumns(){
        String[] database_columns = {
                "USERNAME",
                "PASSWORD"
        };
        return database_columns;
    }
}
