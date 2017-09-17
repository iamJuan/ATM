package com.johngori.software.atm.transactions;
public class Withdrawal extends Transaction{
	
	private double amount;
	
	public Withdrawal(int accountNumber) {
		super(accountNumber);
	}
	
	public void setAmount(double amount){
		this.amount = amount;
	}
	
	public double getAmount(){
		return amount;
	}
	
	@Override
	public void execute() {
		BankDatabase.getInstance().debit(getAmount());
	}
	
}
