package com.johngori.software.atm.transactions;

public abstract class Transaction {
	
	private int accountNumber;
	private double amount;
	
	public Transaction(int accountNumber){
		setAccountNumber(accountNumber);
	}

	public int getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}
	
	public void setAmount(double amount){
		this.amount = amount;
	}
	
	public double getAmount(){
		return amount;
	}
	
	public abstract void execute();
	
}
