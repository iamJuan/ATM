package com.johngori.software.atm.utils;

import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Mouse extends MouseAdapter{
	public static Mouse instance = new Mouse();
	
	private int inputCounter = 0; 
	private int maxInput = 0;
	
	private Mouse(){}
	
	public static Mouse getInstance(){
		return instance;
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		Rectangle mouseRec = new Rectangle(x, y, 1, 1);
		
		if(UIdetection.enterKey.contains(mouseRec)){
			setActiveButton(UIPressed.ENTER);
			enterInput();
		}
		
		if(UIdetection.deleteKey.contains(mouseRec)){
			setActiveButton(UIPressed.DELETE);
			if(inputCounter > 0) deleteInput();
			
			if(Statics.currentScreen == CurrentScreen.BALANCEINQUIRY){
				Statics.currentScreen = CurrentScreen.TRANSACTIONS;
			}
			
			if(Statics.currentScreen == CurrentScreen.WITHDRAWAL){
				Statics.currentScreen = CurrentScreen.TRANSACTIONS;
			}
		}		
		
		if(UIdetection.cashDispenser.contains(mouseRec)){
			setActiveButton(UIPressed.CASHDISPENSER);
			
			if(Statics.takeCash && Statics.currentScreen == CurrentScreen.WITHDRAWALSUCCESSFUL){
				Statics.currentScreen = CurrentScreen.WELCOME;
			}
		}
		
		if(UIdetection.envelope.contains(mouseRec)){
			setActiveButton(UIPressed.ENVELOPE);

			Statics.giveCash = true;
			if(Statics.giveCash && Statics.currentScreen == CurrentScreen.DEPOSITSUCCESSFUL){
				Statics.currentScreen = CurrentScreen.WELCOME;
			}
		}
		
		for (int i = 0; i < UIdetection.numericKeys.length; i++) {
			if(UIdetection.numericKeys[i].contains(mouseRec)){
				setActiveButton(UIPressed.NUMKEY);
				setNumkey(i);
				setInput(i+1);
			}
		}
	}

	public void setActiveButton(UIPressed pressed){
		Statics.active = pressed;						//pass the pressed button to active
		Statics.activeKeyTimer = 10;					//.1 of a second animate(reset)
	}
	
	public void setNumkey(int key){
		//called by mouse.class
		Statics.whatKey = key;
	}
	
	public void setInput(int key){
		if(key == 10) key = 0; 							//because the value of key0 in our gui is 10, so key should be set to 0		
		
		if(Statics.currentScreen == CurrentScreen.WELCOME){
			maxInput = Statics.accountNumberInputCounter;
		}else if(Statics.currentScreen == CurrentScreen.PINREQUEST){
			maxInput = Statics.pinInputCounter;
		}else if(Statics.currentScreen == CurrentScreen.TRANSACTIONS){
			maxInput = Statics.transactionInputCounter;
		}else if(Statics.currentScreen == CurrentScreen.WITHDRAWAL){
			maxInput = Statics.withdrawalInputCounter;
		}else if(Statics.currentScreen == CurrentScreen.DEPOSIT){
			maxInput = Statics.depositInputCounter;
		}

		if(inputCounter < maxInput){
			Statics.input = Statics.input + key;
			inputCounter++;
		}
		
	}
	
	public void deleteInput(){
		//delete inputs
		Statics.input = "";
		inputCounter = 0;
	}

	public void enterInput(){
		if(inputCounter <= maxInput){
			if(Statics.currentScreen == CurrentScreen.WELCOME){
				Statics.accountEntered = true;
			}else if(Statics.currentScreen == CurrentScreen.PINREQUEST){
				Statics.pinEntered = true;
			}else if(Statics.currentScreen == CurrentScreen.TRANSACTIONS){
				Statics.transactEntered = true;
			}else if(Statics.currentScreen == CurrentScreen.WITHDRAWAL){
				Statics.enterWithdrawalAmount = true;
			}else if(Statics.currentScreen == CurrentScreen.DEPOSIT){
				Statics.enterDepositAmount = true;
			}
			
			inputCounter = 0;
		}
		
		if(Statics.currentScreen == CurrentScreen.BALANCEINQUIRY){
			Statics.currentScreen = CurrentScreen.WELCOME;
		}
	}
}
