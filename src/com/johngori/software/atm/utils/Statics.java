package com.johngori.software.atm.utils;

public class Statics {

	public static final int accountNumberInputCounter = 5;//maximum input for account
	public static final int pinInputCounter = 6;			//maximum input for pin
	public static final int transactionInputCounter = 1;	//maximum input for transaction
	public static final int withdrawalInputCounter = 5;	//maximum input for transaction
	public static final int depositInputCounter = 5;	//maximum input for transaction
	public static int activeKeyTimer;				//timer for buttons animation
	public static int errorDisplayTimer;			//timer for displaying error
	public static int whatKey;						//active key from keypad
	public static boolean accountEntered;			//check if entering account is finished
	public static boolean pinEntered;				//check if entering pin is finished
	public static boolean transactEntered;				//check if entering pin is finished
	public static boolean amountEntered;
	public static boolean enterWithdrawalAmount;
	public static boolean enterDepositAmount;
	public static boolean amountError;		
	public static boolean isError;					//check if error
	public static boolean takeCash;
	public static boolean giveCash;
	public static double availableBalance;
	public static double totalBalance;
	public static String stringTransactionMessage;
	public static String input = "";				//gets input from user
	public static UIPressed active;					//made static for mouse.class
	public static CurrentScreen currentScreen;		//enum
}
