package br.com.tiagoiwamoto.config;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConnect implements Serializable {

    private static final long serialVersionUID = 3751382098756144074L;

    public static Connection connection() throws SQLException {
        var url = "jdbc:sqlite:avaliador.db";
        Connection connection = DriverManager.getConnection(url);
        return connection;
    }
    public static ResultSet resultSet(Connection connection, String sql) throws SQLException {
        Statement statement = connection.createStatement();
        return statement.executeQuery(sql);
    }
    public static PreparedStatement preparedStatement(Connection connection, String sql) throws SQLException {
        return connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
    }
}
