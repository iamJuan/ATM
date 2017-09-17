package com.johngori.software.atm.transactions;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.johngori.software.atm.Account;
import com.johngori.software.atm.db.ConnectionManager;
import com.johngori.software.atm.utils.CurrentScreen;
import com.johngori.software.atm.utils.Statics;

public class BankDatabase {
	
	private Connection conn = ConnectionManager.getInstance().getConnection();
	private Account account = new Account();
	
	public static BankDatabase instance = new BankDatabase();
	
	private BankDatabase(){}
	
	public static BankDatabase getInstance(){
		return instance;
	}
	
	public Account getAccount(int accountNumber){
		
		String query = "SELECT * FROM accounts WHERE AccountNumber = ?";
		ResultSet rs = null;
		
		try {
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setInt(1, accountNumber);
			rs = stmt.executeQuery();
			
			if(rs.next()){
				account.setAccountNumber(rs.getInt("AccountNumber"));
				account.setPin(rs.getInt("PIN"));
				account.setAvailableBalance(rs.getDouble("AvailableBalance"));
				account.setTotalBalance(rs.getDouble("TotalBalance"));
				
				return account;
			}else{
				return null;
			}
			
			
		} catch (SQLException e) {
			System.err.println(e);
			return null;
		} finally{
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void updateAvailableBalance(){
		String query = "UPDATE accounts SET AvailableBalance = TotalBalance";
		
		try{
			Statement stmt = conn.createStatement();
			stmt.execute(query);
			
		}catch(SQLException e){
			System.err.println(e);
		}
	}
	
	public void update(int accountNumber, double availableBalance, double totalBalance){
		String query = "UPDATE accounts SET AvailableBalance = ?, TotalBalance = ? WHERE AccountNumber = ?";
		
		try{
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setDouble(1, availableBalance);
			stmt.setDouble(2, totalBalance);
			stmt.setInt(3, accountNumber);
			stmt.executeUpdate();			
			
		}catch (SQLException e) {
			System.err.println(e);
		}
	}
	
	public boolean authenticateUser(int pin){
		if(account.getPin() == pin)
			return true;
		else
			return false;
	}
	
	public double getAvailableBalance(){
		return account.getAvailableBalance();
	}
	
	public double getTotalBalance(){
		return account.getTotalBalance();
	}
	
	public void debit(double amount){

		if(amount <= account.getAvailableBalance()){
			account.debit(amount);
			Statics.currentScreen = CurrentScreen.WITHDRAWALSUCCESSFUL;
			Statics.takeCash = true;
		}else if(amount > account.getAvailableBalance())
			Statics.amountError = true;
	}
	
	public void credit(double amount){
		double availableBalance = getAvailableBalance();
		double totalBalance = availableBalance + amount;
		
		account.credit(totalBalance);
		Statics.currentScreen = CurrentScreen.DEPOSITSUCCESSFUL;
	}
}
