package com.johngori.software.atm;

import com.johngori.software.atm.transactions.BankDatabase;

public class Account {

	private int accountNumber;
	private int pin;
	private double availableBalance;
	private double totalBalance;
	
	public Account(){}
	
	public int getAccountNumber() {
		return accountNumber;
	}

	public int getPin() {
		return pin;
	}

	public double getAvailableBalance() {
		return availableBalance;
	}

	public double getTotalBalance() {
		return totalBalance;
	}

	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}

	public void setPin(int pin) {
		this.pin = pin;
	}

	public void setAvailableBalance(double availableBalance) {
		this.availableBalance = availableBalance;
	}

	public void setTotalBalance(double totalBalance) {
		this.totalBalance = totalBalance;
	}
	
	public void credit(double amount){
		BankDatabase.getInstance().update(getAccountNumber(), getAvailableBalance(), amount);
	}
	
	public void debit(double amount){
		BankDatabase.getInstance().update(getAccountNumber(), getAvailableBalance()-amount, getTotalBalance()-amount);
	}
}
