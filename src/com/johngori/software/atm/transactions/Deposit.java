package com.johngori.software.atm.transactions;

public class Deposit extends Transaction{

	public Deposit(int accountNumber){
		super(accountNumber);
	}

	@Override
	public void execute() {
		BankDatabase.getInstance().credit(getAmount());
	}
	
}
