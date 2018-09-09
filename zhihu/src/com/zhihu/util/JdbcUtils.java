package com.zhihu.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class JdbcUtils {

	private static String url;
	private static String user;
	private static String password;
	private static String driverClass;
	private static Connection conn;

	static {

		Properties prop = new Properties();
		InputStream in = JdbcUtils.class
				.getResourceAsStream("/jdbcConnection.properties");
		try {
			prop.load(in);
			// 加载驱动程序
			url = prop.getProperty("url");
			user = prop.getProperty("user");
			password = prop.getProperty("password");
			driverClass = prop.getProperty("driver");
			Class.forName(driverClass);
			in.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static JdbcUtils jdbcUtils2;

	private JdbcUtils() {

	}

	public static JdbcUtils getInstance() {
		if (jdbcUtils2 == null) {
			jdbcUtils2 = new JdbcUtils();
		}
		return jdbcUtils2;
	}

	public Connection getDateBase() {

		try {
			// 连续数据库
			conn = DriverManager.getConnection(url, user, password);
			conn.setAutoCommit(true);
		if (!conn.isClosed())
			System.out.println("Succeeded connecting to the Database!");
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}

	public static void closeDateBase(Connection conn, Statement statement) {

		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
				throw new RuntimeException();
			}
		}

		if (statement != null) {
			try {
				statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
				throw new RuntimeException();
			}
		}
	}
}
