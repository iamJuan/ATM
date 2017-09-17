package com.johngori.software.atm.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {
	private static ConnectionManager instance = null;
	
	private final String USERNAME = "root";
	private final String PASSWORD = "dmf8lsdq";
	private final String M_CONN_STRING = "jdbc:mysql://localhost/bankdatabase";
	
	private DBType dbType = DBType.MYSQL;
	private Connection conn = null;
	
	private ConnectionManager(){}
	
	public static ConnectionManager getInstance(){
		if(instance == null){
			instance = new ConnectionManager();
		}
		return instance;
	}
	
	public void setDBType(DBType dbType){
		this.dbType = dbType;
	}
	
	public boolean openConnection(){
		try{
			switch (dbType) {
			case MYSQL:
				conn = DriverManager.getConnection(M_CONN_STRING, USERNAME, PASSWORD);
				return true;

			default:
				return false;
			}
			
		}catch(SQLException se){
			System.err.println(se);
			return false;
		}
	}
	
	public Connection getConnection(){
		if(conn == null){
			if(openConnection()){
				return conn;
			}else{
				return null;
			}
		}
		return conn;
	}
	
	public void close(){
		try{
			conn.close();
			conn = null;
		}catch (Exception e) {}
	}
}
