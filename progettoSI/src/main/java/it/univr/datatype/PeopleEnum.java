package it.univr.datatype;

public enum PeopleEnum {
    CODICE_FISCALE("CODICE_FISCALE"),
    NUMERO_TESSERA_SANITARIA("NUMERO_TESSERA_SANITARIA"),
    COGNOME("COGNOME"),
    NOME("NOME"),
    LUOGO("LUOGO"),
    DATA_NASCITA("DATA_NASCITA"),
    CATEGORIA_APPARTENENZA("CATEGORIA_APPARTENENZA");

    public final String value;
    PeopleEnum(String label) {
        this.value = label;
    }

    public static String[] getDatabaseColumns(){
        String[] database_columns = {
                "CODICE_FISCALE",
                "NUMERO_TESSERA_SANITARIA",
                "COGNOME",
                "NOME",
                "LUOGO",
                "DATA_NASCITA",
                "CATEGORIA_APPARTENENZA"
        };
        return database_columns;
    }
}
