package it.francescozanoni.app.concurrency;

import java.io.*;
import java.sql.*;
import java.util.concurrent.*;

public class Main {

    public static void main(String[] args) throws IOException, SQLException, InterruptedException {

        String query = "UPDATE my_table SET my_field = 'ABCD'";
        String dsn = getDsn("SQLite");
        int numberOfThreads = 10;

        setupDatabase(dsn);

        ExecutorService executorService = Executors.newFixedThreadPool(numberOfThreads);
        TaskCounter taskCounter = new TaskCounter();
        for (int i = 0; i < numberOfThreads; i++) {
            executorService.execute(new DatabaseWrite(dsn, query, taskCounter));
        }
        executorService.shutdown();
        executorService.awaitTermination(1, TimeUnit.SECONDS);

        System.out.println("-----------------------------------------------------------");
        System.out.printf("Total:%13d\n", numberOfThreads);
        System.out.printf(" - started:%8d\n", taskCounter.getStarted());
        System.out.printf(" - finished:%7d\n", taskCounter.getFinished());
        System.out.printf(" - failed:%9d\n", taskCounter.getFailed());
        System.out.println("-----------------------------------------------------------");

    }

    /**
     * Setup database before concurrent write execution:
     * - create table, if it does not exist;
     * - insert a record, if it does not exist.
     *
     * @param dsn database connection string
     */
    private static void setupDatabase(String dsn) throws SQLException {

        Connection connection = DriverManager.getConnection(dsn);

        PreparedStatement pstmt_1 = connection.prepareStatement(
                "CREATE TABLE IF NOT EXISTS my_table (my_field TEXT)"
        );
        pstmt_1.executeUpdate();

        PreparedStatement pstmt_2 = connection.prepareStatement(
                "SELECT COUNT(*) FROM my_table"
        );
        int numberOfRecords = pstmt_2.executeQuery().getInt(1);

        if (numberOfRecords == 0) {
            PreparedStatement pstmt_3 = connection.prepareStatement(
                    "INSERT INTO my_table(my_field) VALUES ('AAAA')"
            );
            pstmt_3.executeUpdate();
        }

        connection.close();

    }

    /**
     * Get DSN for the input database engine.
     *
     * @param engine database engine (SQLite, MySQL, MariaDB, ElasticSearch)
     * @return DSN string
     * @throws IOException in case of problems with current file path
     */
    public static String getDsn(String engine) throws IOException {

        String dsn;

        switch (engine.toLowerCase().replaceAll("/\\W/", "")) {
            case "sqlite":
                String databaseFilePath = new File(".").getCanonicalPath() + "/storage/db.sqlite";
                dsn = "jdbc:sqlite:" + databaseFilePath;
                break;
            case "mysql":
            case "mariadb":
                String host = "";
                String schema = "";
                String username = "";
                String password = "";
                dsn = "jdbc:mysql://" + host + "/" + schema + "?" + "user=" + username + "&password=" + password;
                break;
            default:
                dsn = "";
        }

        return dsn;

    }

}