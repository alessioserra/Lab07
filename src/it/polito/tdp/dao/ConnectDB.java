package it.polito.tdp.dao;

import java.sql.Connection;
import java.sql.SQLException;

import com.zaxxer.hikari.*;

/**
 * Utility class for connecting to the database
 * 
 * Uses the HikariCP library for managing a connection pool
 * @see <a href="https://brettwooldridge.github.io/HikariCP/">HikariCP</a>
 */
public class ConnectDB {

	private static final String jdbcURL = "jdbc:mysql://localhost/poweroutages?useTimezone=true&serverTimezone=UTC";
	private static HikariDataSource ds;

	public static Connection getConnection() {

		if (ds == null) {
			
			ds = new HikariDataSource();

			ds.setJdbcUrl(jdbcURL);
			ds.setUsername("root");
			ds.setPassword("alessio");

			// configurazione MySQL
			ds.addDataSourceProperty("cachePrepStmts", "true");
			ds.addDataSourceProperty("prepStmtCacheSize", "250");
			ds.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");

		}

		try {

			return ds.getConnection();

		} catch (SQLException e) {
			System.err.println("Errore connessione al DB");
			throw new RuntimeException(e);
		}
	}

}