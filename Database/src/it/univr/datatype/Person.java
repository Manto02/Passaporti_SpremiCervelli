package it.univr.datatype;

public class Person  {
    String nome, codice_fiscale, cognome, luogo, data, categoria_appartenenza, numero_tessera_sanitaria;

    public String getNome() {
        return nome;
    }

    public String getCodice_fiscale() {
        return codice_fiscale;
    }

    public String getCognome() {
        return cognome;
    }

    public String getLuogo() {
        return luogo;
    }

    public String getData() {
        return data;
    }

    public String getCategoria_appartenenza() {
        return categoria_appartenenza;
    }

    public String getNumero_tessera_sanitaria() {
        return numero_tessera_sanitaria;
    }

    public Person(String codice_fiscale, String numero_tessera_sanitaria, String cognome, String nome, String luogo, String data, String categoria_appartenenza) {

        this.nome = nome;
        this.cognome = cognome;
        this.codice_fiscale = codice_fiscale;
        this.luogo = luogo;
        this.data = data;
        this.categoria_appartenenza = categoria_appartenenza;
        this.numero_tessera_sanitaria = numero_tessera_sanitaria;

    }

    public String toString(){
        String result = "";
        result = nome + "\t" + cognome+ "\t" + luogo + "\t"+ data.toString() + "\t"+ codice_fiscale + "\t"+ numero_tessera_sanitaria + "\t"+ categoria_appartenenza;

        return result;
    }
}