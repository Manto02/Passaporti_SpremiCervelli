package com.example.sus;

import java.util.ArrayList;
import java.util.List;

public class Citizens extends USER{

    // TODO: DB_citizens, DB_registeredUsers

    private final String nome, cognome,  luogoDiNascita,  codiceFiscale;
    //* SIGN-UP
    public Citizens(String nome,String cognome, String luogoDiNascita, String codiceFiscale, String password) {
        this.nome = nome;
        this.cognome = cognome;
        this.luogoDiNascita = luogoDiNascita;
        this.codiceFiscale = codiceFiscale;
        // uso codice fiscale come username
        this.userName = codiceFiscale;
        this.password = password;
    }

    //* LOG-IN
    protected static Citizens LogIn(String userName, String password) {
        //CONTROLLO validita credenziali
        return USER.checkDB(DB_Citizens,userName,password);
        // ritorna lo USER se trova un match
        // ritorna null altrimenti
    }
}
