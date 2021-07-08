package com.revature.connection;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory {
	private static Properties properties = new Properties();
	static {
		//Load the properties file into function
		try {
			FileInputStream file = new FileInputStream("src/main/resources/Connection.properties");
			properties.load(file);
			file.close();
		}catch(FileNotFoundException e) {
			e.printStackTrace();
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	static {
		//Load the register  - JDBC driver
		try {
			Class.forName(properties.getProperty("Connection.driver"));
		} catch (ClassNotFoundException  e) {
			e.printStackTrace();
		}
	}
	public static Connection getConnection() throws SQLException{
		//Create the db connection with URL,UserName,Password.
		String Url = properties.getProperty("Connection.url");
		String Username = properties.getProperty("Connection.username");
		String Password = properties.getProperty("Connection.password");
		
		//Connection
		Connection con = DriverManager.getConnection(Url,Username,Password);
		return con;
	}
		
}
