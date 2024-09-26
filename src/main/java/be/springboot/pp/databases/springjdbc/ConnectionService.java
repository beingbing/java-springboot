package be.springboot.pp.databases.springjdbc;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.apache.tomcat.jdbc.pool.PoolProperties;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/*
* A resultSet can be thought as a list of key value pairs. Where
* list elements are the records and each key is the column name with value
* being the column value.
* */

@Service
public class ConnectionService {

    @Value("${spring.datasource.url}")
    private String dbUrl;

    @Value("${spring.datasource.username}")
    private String userName;

    @Value("${spring.datasource.password}")
    private String password;

    private DataSource dataSource;

//    @PostConstruct
    public void establishConnection() {
        System.out.println("Establishing connection..."); // show how to add dependency in pom and properties changes
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
        while (rs.next()) { // moves the iterator to the next row
            System.out.println(rs.getInt(1) + " " + rs.getString(2) + " " + rs.getString(3) + " " + rs.getString(4));
        }
        rs.close();
    }

    /*
    * Caused by: java.sql.SQLNonTransientConnectionException:
    * Connection exception, SQL-server rejected establishment of SQL-connection,  message from server: "sorry, too many clients already"
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
    // inserting a new row based on existing row
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

            // executing multiple queries at once (batching queries)
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

    private PoolProperties createPoolProperties(String dbUrl, String userName, String password) {
        PoolProperties props = new PoolProperties();
        props.setUrl(dbUrl);
        props.setUsername(userName);
        props.setPassword(password);
        props.setDriverClassName("com.mysql.cj.jdbc.Driver");
        props.setJmxEnabled(true);
        props.setTestWhileIdle(false);
        props.setTestOnBorrow(true);
        props.setValidationQuery("SELECT 1");
        props.setTestOnReturn(false);
        props.setValidationInterval(3000);
        props.setTimeBetweenEvictionRunsMillis(3000);
        props.setMaxActive(100);
        props.setInitialSize(10); // imp
        props.setMaxWait(1000);
        props.setRemoveAbandonedTimeout(60);
        props.setMinEvictableIdleTimeMillis(3000);
        props.setMinIdle(10);
        props.setLogAbandoned(true);
        props.setRemoveAbandoned(true);
        props.setJdbcInterceptors("org.apache.tomcat.jdbc.pool.interceptor.ConnectionState;org.apache.tomcat.jdbc.pool.interceptor.StatementFinalizer");
        return props;
    }

    // for DataSource add 'tomcat-jdbc'
    private DataSource createDataSource(String dbUrl, String userName, String password) {
        PoolProperties props = createPoolProperties(dbUrl, userName, password);

        // data-source is our connection pool
        DataSource dataSource = new DataSource();
        dataSource.setPoolProperties(props);
        return dataSource;
    }

    public void runExample() {
        long startTime = System.currentTimeMillis();

        for (int i = 0; i < 10_000; i++) {
//            executeQuery();
            executeNoPoolQuery();
        }

        long endTime = System.currentTimeMillis();
        System.out.println("Time taken: " + (endTime - startTime));
    }

    private void executeNoPoolQuery() {
        try (Connection con = DriverManager.getConnection(dbUrl, userName, password);
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM products")) {

            printResultSet(rs);

        } catch (SQLException e) {
            System.err.println("Failed to execute query: " + e.getMessage());
        }
    }

    private void executeQuery() {
        try (Connection con = dataSource.getConnection();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM products")) {

            printResultSet(rs);

        } catch (SQLException e) {
            System.err.println("Failed to execute query: " + e.getMessage());
        }
    }


    /*
    * after running the example first with pool and then without pool, we found that,
    * pool -    1533ms
    * no pool - 6953ms
    * this is the difference.
    * */
//    @PostConstruct
    public void connectionPool() {
//        this.dataSource = createDataSource(dbUrl, userName, password);
        runExample();
    }
}
