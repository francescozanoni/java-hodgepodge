package it.francescozanoni.db;

import java.io.*;
import java.sql.*;
import java.util.concurrent.*;
import it.francescozanoni.Utils;

public class SQLite {

    public static void main(String[] args) throws IOException, SQLException, InterruptedException {
        
        String dsn = "jdbc:sqlite:" + Utils.getBasePath() + "/storage/db.sqlite";

        Connection conn = DriverManager.getConnection(dsn);

        // Create database structure and populate table.
        PreparedStatement pstmt_1 = conn.prepareStatement("CREATE TABLE IF NOT EXISTS my_table (my_field TEXT)");
        pstmt_1.executeUpdate();

        PreparedStatement pstmt_2 = conn.prepareStatement("SELECT COUNT(*) FROM my_table");
        int numberOfRecords = pstmt_2.executeQuery().getInt(1);

        if (numberOfRecords == 0) {
            PreparedStatement pstmt_3 = conn.prepareStatement("INSERT INTO my_table(my_field) VALUES ('AAAA')");
            pstmt_3.executeUpdate();
        }

        // Query table.
        // https://docs.oracle.com/javase/tutorial/jdbc/basics/processingsqlstatements.html
        try (Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery("SELECT * FROM my_table");
            while (rs.next()) {
                String myField = rs.getString("my_field");
                System.out.println(myField);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        
        // Update table via prepared statement.
        PreparedStatement pstmt = conn.prepareStatement("UPDATE my_table SET my_field = 'ABCD'");
        pstmt.executeUpdate();
        
        /*
        // Update table via updatable result set (SQLite does not support it).
    try (Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE)) {
      ResultSet uprs = stmt.executeQuery("SELECT * FROM my_table");
      while (uprs.next()) {
        String myField = uprs.getString("my_field");
        uprs.updateString("my_field", "OOoOo");
        uprs.updateRow();
      }
    } catch (SQLException e) {
      System.out.println(e.getMessage());
    }
        
        // Query table.
        // https://docs.oracle.com/javase/tutorial/jdbc/basics/processingsqlstatements.html
        try (Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery("SELECT * FROM my_table");
            while (rs.next()) {
                String myField = rs.getString("my_field");
                System.out.println(myField);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        */
        
        conn.close();
        
    }

}