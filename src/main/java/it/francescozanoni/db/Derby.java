package it.francescozanoni.db;

import java.io.*;
import java.sql.*;
import java.util.concurrent.*;
import it.francescozanoni.Utils;

public class Derby {

    public static void main(String[] args) throws IOException, SQLException, InterruptedException {
        
        String dsn = "jdbc:derby:" + Utils.getBasePath() + "/storage/db.derby;create=true";

        Connection conn = DriverManager.getConnection(dsn);

        // Create database structure and populate table.
        PreparedStatement pstmt_1 = conn.prepareStatement("CREATE TABLE my_table (my_field VARCHAR(127))");
        pstmt_1.executeUpdate();

        PreparedStatement pstmt_2 = conn.prepareStatement("SELECT COUNT(*) AS c FROM my_table");
        // https://stackoverflow.com/questions/16576331/java-sql-exception-invalid-cursor-state-no-current-row
        int numberOfRecords = 0;
        ResultSet rs_ = pstmt_2.executeQuery();
            if (rs_.next()) {
                numberOfRecords = rs_.getInt(1);
            }
        rs_.close();

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
            rs.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        
        // Update table via prepared statement.
        PreparedStatement pstmt = conn.prepareStatement("UPDATE my_table SET my_field = 'ABCD'");
        pstmt.executeUpdate();
        
        // Update table via updatable result set (SQLite does not support it).
    try (Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE)) {
      ResultSet uprs = stmt.executeQuery("SELECT * FROM my_table");
      while (uprs.next()) {
        String myField = uprs.getString("my_field");
        uprs.updateString("my_field", "OOoOo");
        uprs.updateRow();
      }
      uprs.close();
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
            rs.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        
        conn.close();
        
    }

}