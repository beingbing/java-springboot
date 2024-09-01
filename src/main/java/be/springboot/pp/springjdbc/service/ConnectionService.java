package be.springboot.pp.springjdbc.service;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@Service
public class ConnectionService {

    @Value("${spring.datasource.url}")
    private String dbUrl;

    @Value("${spring.datasource.username}")
    private String userName;

    @Value("${spring.datasource.password}")
    private String password;

//    @PostConstruct
    public void establishConnection() {
        System.out.println("Establishing connection...");
        try (Connection con = DriverManager.getConnection(dbUrl, userName, password); Statement statement = con.createStatement()) {
            System.out.println("Connection established: " + con + " " + statement);
            printProductsTable(statement);

            int isUpdated = statement.executeUpdate("update products set full_version = 2.8 where id = 200");
            System.out.println("isUpdated: " + isUpdated);

            printProductsTable(statement);
        } catch (SQLException e) {
            throw new RuntimeException("Failed to establish connection", e);
        }
    }

    private void printProductsTable(Statement statement) throws SQLException {
        ResultSet rs = statement.executeQuery("SELECT * FROM products");
        printResultSet(rs);
    }

    private void printResultSet(ResultSet rs) throws SQLException {
        while (rs.next()) {
            System.out.println(rs.getInt(1) + " " + rs.getString(2) + " " + rs.getString(3) + " " + rs.getString(4));
        }
        rs.close();
    }

    /*
    * Caused by: java.sql.SQLNonTransientConnectionException:
    * Connection exception, SQL-server rejected establishment of SQL-connection,  message from server: "Too many connections"
    * */
//    @PostConstruct
    public void tooManyConnections() throws SQLException {
        System.out.println("Too many connections...");
        while (true) {
            Connection con = DriverManager.getConnection(dbUrl, userName, password);
            Statement statement = con.createStatement();
            printProductsTable(statement);
        }
    }

//    @PostConstruct
    public void makeUpdateOnResultSet() {
        System.out.println("makeUpdateOnResultSet...");
        try (Connection con = DriverManager.getConnection(dbUrl, userName, password); Statement statement = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE)) {
            System.out.println("Connection established: " + con + " " + statement);

            ResultSet rs = statement.executeQuery("SELECT * FROM products");
            printAndUpdateResultSet(rs);
        } catch (SQLException e) {
            throw new RuntimeException("Failed to establish connection", e);
        }
    }

    // TODO: has a bug, gets stuck in an infinite loop
    private void printAndUpdateResultSet(ResultSet rs) throws SQLException {
        while (rs.next()) {
            System.out.println(rs.getInt(1) + " " + rs.getString(2) + " " + rs.getString(3) + " " + rs.getFloat(4));
            rs.moveToInsertRow();
            rs.updateString(2, rs.getString(2) + " 2");
            rs.updateString(3, rs.getString(3) + " with upgrades");
            rs.updateFloat(4, 1.0f);
            rs.insertRow();
            rs.moveToCurrentRow();
        }
        rs.close();
    }

//    @PostConstruct
    private void commitTransactionExample() {
        System.out.println("commitTransactionExample...");
        try (Connection con = DriverManager.getConnection(dbUrl, userName, password);
             Statement statement = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE)) {
            System.out.println("Connection established: " + con + " " + statement);

            con.setAutoCommit(false);

            statement.execute("UPDATE products SET full_version = 3.0 WHERE id = 200");
            statement.execute("COMMIT"); // con.commit()
        } catch (SQLException e) {
            throw new RuntimeException("Failed to establish connection", e);
        }
    }

    /*
    * java.sql.SQLSyntaxErrorException:
    * You have an error in your SQL syntax; check the manual that corresponds to your MySQL server version for the right syntax to use near
    * 'UPDATE products SET full_version = 1.7 WHERE id < 200' at line 1
    * */
//    @PostConstruct
    private void batchQueries() throws SQLException {
        System.out.println("batchQueries...");
        try (Connection con = DriverManager.getConnection(dbUrl, userName, password);
             Statement statement = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE)) {
            System.out.println("Connection established: " + con + " " + statement);

            boolean hasResult = statement.execute("SELECT * FROM products WHERE name = 'JBL'; UPDATE products SET full_version = 1.7 WHERE id < 200");

            do {
                if (hasResult) {
                    try (ResultSet rs = statement.getResultSet()) {
                        printResultSet(rs);
                    }
                } else {
                    int updateCount = statement.getUpdateCount();
                    if (updateCount != -1) {
                        System.out.println(updateCount + " rows impacted");
                    }
                }
                hasResult = statement.getMoreResults();
            } while (hasResult || statement.getUpdateCount() != -1);

        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }
}
