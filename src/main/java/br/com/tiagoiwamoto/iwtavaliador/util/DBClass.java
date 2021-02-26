package br.com.tiagoiwamoto.iwtavaliador.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBClass {
	public static Connection getConnection() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException{
	String connection = "jdbc:sqlite:iwt.db";
	Connection conexao = DriverManager.getConnection(connection);
	return conexao;
	}
	
	public static ResultSet getResultSet(Connection conn, String sql) throws SQLException{
		Statement statement = conn.createStatement();
		return statement.executeQuery(sql);
	}
	
	public static PreparedStatement getPreparedStatement(Connection conn, String sql) throws SQLException{
		return conn.prepareStatement(sql);
	}
}
