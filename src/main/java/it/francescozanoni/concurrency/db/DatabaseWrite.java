package it.francescozanoni.concurrency.db;

import java.sql.*;

public class DatabaseWrite extends Thread {

    private final Connection connection;
    private final String query;
    private boolean withTransactions = false;
    private final TaskCounter taskCounter;

    /**
     * @param dsn         database connection string
     * @param query       SQL query to execute
     * @param taskCounter object reporting global task status count
     * @throws SQLException in case of connection error
     */
    public DatabaseWrite(String dsn, String query, TaskCounter taskCounter) throws SQLException {
        this.connection = DriverManager.getConnection(dsn);
        this.query = query;
        this.taskCounter = taskCounter;
        this.taskCounter.incrementStarted();
    }

    /**
     * @param dsn              database connection string
     * @param query            SQL query to execute
     * @param taskCounter      object reporting global task status count
     * @param withTransactions whether transactions must be enabled
     * @throws SQLException in case of connection error
     */
    public DatabaseWrite(String dsn, String query, TaskCounter taskCounter, boolean withTransactions) throws SQLException {
        this(dsn, query, taskCounter);
        this.withTransactions = withTransactions;
    }

    public void run() {

        try {

            if (this.withTransactions) {
                this.connection.setAutoCommit(false);
            }
            PreparedStatement pstmt = this.connection.prepareStatement(this.query);
            // pstmt.setQueryTimeout(15);
            pstmt.executeUpdate();
            if (this.withTransactions) {
                this.connection.commit();
            }

            this.taskCounter.incrementFinished();

        } catch (SQLException e) {
            this.taskCounter.incrementFailed();
            if (this.withTransactions) {
                try {
                    this.connection.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        } finally {
            try {
                this.connection.close();
            } catch (SQLException ex) {
                // System.out.println(ex.getMessage());
            }
        }

    }

}