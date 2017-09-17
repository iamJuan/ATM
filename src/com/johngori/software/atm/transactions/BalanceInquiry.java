package com.johngori.software.atm.transactions;

import com.johngori.software.atm.utils.Statics;

public class BalanceInquiry extends Transaction{

	public BalanceInquiry(int accountNumber) {
		super(accountNumber);
	}

	@Override
	public void execute() {
		Statics.availableBalance = BankDatabase.getInstance().getAccount(getAccountNumber()).getAvailableBalance();
		Statics.totalBalance = BankDatabase.getInstance().getAccount(getAccountNumber()).getTotalBalance();
	}
	
}
