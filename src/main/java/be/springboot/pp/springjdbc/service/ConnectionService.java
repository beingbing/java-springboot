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

    @PostConstruct
    public void establishConnection() {
        System.out.println("Establishing connection...");
        try (Connection con = DriverManager.getConnection(dbUrl, userName, password); Statement statement = con.createStatement()) {
            System.out.println("Connection established: " + con + " " + statement);
            ResultSet rs = statement.executeQuery("SELECT * FROM products");
            while (rs.next()) {
                System.out.println(rs.getInt(1) + " " + rs.getString(2) + " " + rs.getString(3) + " " + rs.getString(4));
            }
            rs.close();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to establish connection", e);
        }
    }
}
