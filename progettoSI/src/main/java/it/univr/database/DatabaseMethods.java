package it.univr.database;

import java.sql.*;
import java.util.ArrayList;

public abstract class DatabaseMethods {

    //metodo per la connessione
    public Connection connect() {
        String cwd = System.getProperty("user.dir");
        String url = "jdbc:sqlite:" + cwd + "/database.db";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return conn;
    }

    public void createNewDatabase(String fileName) {
        String cwd = System.getProperty("user.dir");
        String url = "jdbc:sqlite:" + cwd + "/" + fileName;

        try (Connection conn = DriverManager.getConnection(url)) {
            if (conn != null) {
                DatabaseMetaData meta = conn.getMetaData();
                System.out.println("The driver name is " + meta.getDriverName());
                System.out.println("A new database has been created.");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public abstract void createNewTable(String table_name);

    //genera una stringa con le colonne o la stringa coi ? per i valori da inserire a seconda dei parametri passati
    private String getParameters(TypeOfSqlString string_type, String[] database_columns){
        String result = "";
        if(string_type == TypeOfSqlString.INSERT) {
            result += "(";
            for (int i = 0; i < database_columns.length; i++) {
                result += database_columns[i];
                if (i != database_columns.length - 1)
                    result += ",";
            }
            return result + ")";
        }
        else if(string_type == TypeOfSqlString.QUESTION_MARK){
            result += "(";
            for (int i = 0; i < database_columns.length; i++) {
                result += "?";
                if (i != database_columns.length - 1)
                    result += ",";
            }
            return result + ")";
        }
        else if(string_type == TypeOfSqlString.SELECT){
            for (int i = 0; i < database_columns.length; i++) {
                result += database_columns[i];
                if (i != database_columns.length - 1)
                    result += ", ";
            }
            return result;
        }
        else {
            for (int i = 0; i < database_columns.length; i++) {
                result += database_columns[i] + " = ?";
                if (i != database_columns.length - 1)
                    result += ", ";
            }
            return result;
        }
    }

    //metodo inserimento dati nella tabella
    public void insert(String table_name, String[] database_columns, String... parameters) throws SQLException {
        //stringa di comando
        String sql = "INSERT INTO " + table_name + getParameters(TypeOfSqlString.INSERT, database_columns)
                +  " VALUES" + getParameters(TypeOfSqlString.QUESTION_MARK, database_columns);

        try (Connection conn = connect()) {
            //Preparestatement serve per l'assegnazione di parametri
            PreparedStatement psmt = conn.prepareStatement(sql);
            for (int i = 0; i < parameters.length; i++) {
                psmt.setString(i + 1, parameters[i]);
            }
            psmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    //metodo per aggiornare i dati
    public void update(String table_name, String... columns_want_to_update) {

        String sql_parameters = getParameters(TypeOfSqlString.UPDATE, columns_want_to_update);
        String sql = "UPDATE " + table_name + " SET " + sql_parameters;

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // set the corresponding param
            for (int i = 0; i < columns_want_to_update.length; i++) {
                pstmt.setString(i + 1, columns_want_to_update[i]);
            }
            // update
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    public abstract <T extends Comparable<T>> ArrayList<T> selectData(String table_name, String... database_columns);

    public abstract <T extends Comparable<T>> ArrayList<T>  selectFilteredData(String table_name, String operator, String param, String compare);

    //metodo per eliminare dati
    public void delete(String table_name, String... columns_want_delete) {
        String sql_parameters = getParameters(TypeOfSqlString.DELETE, columns_want_delete);
        String sql = "DELETE FROM " + table_name + " WHERE " + sql_parameters;

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // set the corresponding param
            for (int i = 0; i < columns_want_delete.length; i++) {
                pstmt.setString(i + 1, columns_want_delete[i]);
            }
            // execute the delete statement
            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public String getTableName(){
        if(this instanceof CitizensDatabase)
            return "utente";
        else if (this instanceof PeopleDatabase) {
            return "persone";
        }
        else
            return "staff";
    }
}
