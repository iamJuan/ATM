package com.johngori.software.atm;

import java.awt.Graphics;

import javax.swing.JPanel;

import com.johngori.software.atm.db.ConnectionManager;
import com.johngori.software.atm.db.DBType;
import com.johngori.software.atm.transactions.BalanceInquiry;
import com.johngori.software.atm.transactions.BankDatabase;
import com.johngori.software.atm.transactions.Deposit;
import com.johngori.software.atm.transactions.Transaction;
import com.johngori.software.atm.transactions.Withdrawal;
import com.johngori.software.atm.utils.CurrentScreen;
import com.johngori.software.atm.utils.Images;
import com.johngori.software.atm.utils.Mouse;
import com.johngori.software.atm.utils.Statics;
import com.johngori.software.atm.utils.UIPressed;
import com.johngori.software.atm.utils.UIdetection;

public class ATMGui extends JPanel implements Runnable{
	
	private static final long serialVersionUID = 1L;
	
	private Thread threader;							//needed to show a clicked butto
	
	private CurrentScreen prevScreen;
	private Account account;
	
	public ATMGui(){
		new Images();									//for instantiation
		new UIdetection();								//for instantiation
		
		Statics.currentScreen = CurrentScreen.WELCOME;			//assign the current screen
		
		setFocusable(true);								//request focus
		
		ConnectionManager.getInstance().setDBType(DBType.MYSQL);
		
		updateAvailableBalance();
		
		addMouseListener(Mouse.getInstance());			//listener
		
		if (threader == null) {							//
			threader = new Thread(this);				//run thread
			threader.start( );							//
		}
	}
	
	public void updateAvailableBalance(){
		BankDatabase.getInstance().updateAvailableBalance();
	}
	
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		
		g.drawImage(Images.atmsim, 0, 0, null);			
		g.drawImage(Images.atmui, 10, 150, null);
		
		Display.writeToScreen(g,Statics.currentScreen);				//prompts the user
		
		if(Statics.currentScreen == CurrentScreen.WELCOME){		
			g.drawString(Statics.input, 200, 60);	//displays the input y-60
		}else if(Statics.currentScreen == CurrentScreen.PINREQUEST){
			g.drawString(Statics.input, 150, 80);	//displays the input y-80
		}else if(Statics.currentScreen == CurrentScreen.TRANSACTIONS){
			g.drawString(Statics.input, 150, 125);	//displays the input y-125
		}else if(Statics.currentScreen == CurrentScreen.WITHDRAWAL){
			g.drawString(Statics.input, 150, 60);	//displays the input y-125
		}else if(Statics.currentScreen == CurrentScreen.DEPOSIT){
			g.drawString(Statics.input, 150, 60);	//displays the input y-125
		}
			
		if(Statics.activeKeyTimer >= 0){							//draws the click button image if the activeKeyTimer >= 0
			
			if(Statics.active == UIPressed.ENTER){
				g.drawImage(Images.enterkey, 95, 270, null);
			}
			else if(Statics.active == UIPressed.DELETE){
				g.drawImage(Images.deletekey, 58, 270, null);
			}
			else if(Statics.active == UIPressed.CASHDISPENSER){
				g.drawImage(Images.cashdispenser, 149, 152, null);
			}
			else if(Statics.active == UIPressed.ENVELOPE){
				g.drawImage(Images.envelope, 150, 245, null);
			}
			else if(Statics.active == UIPressed.NUMKEY){
				g.drawImage(Images.numerickeys[Statics.whatKey], UIdetection.numericKeys[Statics.whatKey].x, UIdetection.numericKeys[Statics.whatKey].y, null);
			}
		}
	}
	
	@Override
	public void run() {
		while(true){
			repaint();
			
			if(Statics.accountEntered){					
				
				if(BankDatabase.getInstance().getAccount(Integer.parseInt(Statics.input)) != null){
					
					account = BankDatabase.getInstance().getAccount(Integer.parseInt(Statics.input));					
					Statics.currentScreen = CurrentScreen.PINREQUEST;

				}else{
					displayError();
				}

				Statics.accountEntered = false;
				Statics.input = "";
				
			}else if(Statics.pinEntered){
				
				if(BankDatabase.getInstance().authenticateUser(Integer.parseInt(Statics.input))){
					Statics.currentScreen = CurrentScreen.TRANSACTIONS;
					
				}else{
					displayError();
				}

				Statics.pinEntered = false;
				Statics.input = "";
				
			}else if(Statics.transactEntered){
				
				if(!Statics.input.equals("")){
					switch (Integer.parseInt(Statics.input)) {
					case 1:
						Statics.currentScreen = CurrentScreen.BALANCEINQUIRY;
						Transaction balInq = new BalanceInquiry(account.getAccountNumber());
						balInq.execute();
						
						break;
					
					case 2:
						Statics.currentScreen = CurrentScreen.WITHDRAWAL;
						break;
					case 3:
						Statics.currentScreen = CurrentScreen.DEPOSIT;
						break;
					case 4:
						Statics.currentScreen = CurrentScreen.WELCOME;
						break;
	
					default:
						displayError();
						break;
					}

					Statics.transactEntered = false;
					Statics.input = "";
				}
			}
			
			if(Statics.enterWithdrawalAmount){
				
				Transaction withdrawal = new Withdrawal(account.getAccountNumber());
				
				if(!Statics.input.equals("")){
					withdrawal.setAmount(Double.parseDouble(Statics.input));
					withdrawal.execute();
					Statics.enterWithdrawalAmount = false;
					Statics.input = "";
				}
			}
			
			if(Statics.enterDepositAmount){
				
				Transaction deposit = new Deposit(account.getAccountNumber());
				
				if(!Statics.input.equals("")){
					deposit.setAmount(Double.parseDouble(Statics.input));
					deposit.execute();
					Statics.enterDepositAmount = false;
					Statics.input = "";
				}
			}
						
			if(Statics.amountError){
				displayInsufficient();
				Statics.amountError = false;
			}
			
			if(Statics.activeKeyTimer >= 0){
				Statics.activeKeyTimer--;			//decrease 1 value of activeKeyTimer / 1ms  									
			}
			
			if(Statics.errorDisplayTimer >= 0){
				Statics.errorDisplayTimer--;		//decrease 1 value of errorDisplayTimer / 1ms
			}else {
													//if errorDisplayTimer == 0
				if(Statics.isError){				//if true
					Statics.currentScreen = prevScreen; //go back to previous screen
					Statics.isError = false;		//and set isError to false so it wont come back here
				}
			}
			
			try{
				Thread.sleep(10);
				
			}catch(InterruptedException ie){
				System.err.println(ie);
			}
		}
	}

	private void displayError() {
		prevScreen = Statics.currentScreen;
		Statics.currentScreen = CurrentScreen.ERROR;
		Statics.errorDisplayTimer = 90;
		Statics.isError = true;
	}

	private void displayInsufficient() {
		prevScreen = Statics.currentScreen;
		Statics.currentScreen = CurrentScreen.INSUFFICIENT;
		Statics.errorDisplayTimer = 60;
		Statics.isError = true;
	}
}