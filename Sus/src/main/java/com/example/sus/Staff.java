package com.example.sus;

public class Staff extends USER{
    // TODO: DB_Staff DB_authorizations

    public static Staff LogIn(String userName, String password) {
        return checkDB(DB_Staff, userName, password);
        // ritorna lo USER se trova un match
        // ritorna null altrimenti
    }

    private void setDisponibilita(){

    }
}
