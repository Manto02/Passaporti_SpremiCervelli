package it.univr.database;

import java.sql.*;

public class DatabaseMethods {

    //metodo per la connessione
    public static Connection connect() {
        String url = "jdbc:sqlite:/home/manto/Scrivania/Database/demografia_cittadini.db";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return conn;
    }

    //metodo inserimento dati nella tabella
    public void insert(String table_name, String nome, int simpatia) throws SQLException {
        //stringa di comando
        String sql = "INSERT INTO" + table_name + "(nome, simpatia) VALUES (?, ?)";

        try (Connection conn = connect()) {
            //Preparestatement serve per l'assegnazione di parametri
            PreparedStatement psmt = conn.prepareStatement(sql);
            psmt.setString(1, nome);
            psmt.setInt(2, simpatia);
            psmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    //metodo per stampare dati dal database corrispondenti ai parametri
    public void selectDataFromDatabase(String table_name, String... parameters){

        String sql = "SELECT nome, cognome FROM " + table_name;

        try(Connection conn = connect()){
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()){
                System.out.println(
                        rs.getString("nome") + "\t" +
                                rs.getInt("simpatia"));
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }

    }

    //metodo per filtraggio dati
    public void getSimpatiaGreaterThan(String table_name, int range){
        String sql = "SELECT nome, simpatia FROM " + table_name + " WHERE simpatia > ?";

        try(Connection conn = connect()){
            PreparedStatement pr = conn.prepareStatement(sql);
            pr.setInt(1, range);
            ResultSet rs = pr.executeQuery();

            while(rs.next()){
                System.out.println(rs.getString("nome") + "\t" + rs.getInt("simpatia"));
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }

    //metodo per aggiornare i dati

    public void update(String table_name, int id, String name, double capacity) {
        String sql = "UPDATE " + table_name + " SET nome = ? , "
                + "simpatia = ? "
                + "WHERE id = ?";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // set the corresponding param
            pstmt.setString(1, name);
            pstmt.setDouble(2, capacity);
            pstmt.setInt(3, id);
            // update
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    //metodo per eliminare dati
    public void delete(String table_name, int id) {
        String sql = "DELETE FROM " + table_name + " WHERE id = ?";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // set the corresponding param
            pstmt.setInt(1, id);
            // execute the delete statement
            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
