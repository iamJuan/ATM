package com.johngori.software.atm;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.text.NumberFormat;

import com.johngori.software.atm.utils.CurrentScreen;
import com.johngori.software.atm.utils.Statics;

public class Display {

	public static void writeToScreen(Graphics g, CurrentScreen currentScreen){
		
		g.setColor(Color.BLACK);
		g.setFont(new Font("Serif", Font.BOLD, 12));
		g.drawString("", 0, 0);
		
		switch (currentScreen) {
		case WELCOME:
				g.drawString("Enter your account number: ", 50, 60);
			break;
		case PINREQUEST:
				g.drawString("WELCOME!", 50, 40);
				g.drawString("Enter your PIN: ", 50, 80);
			break;
		case TRANSACTIONS:
				g.drawString("MAIN MENU", 50, 40);
				g.drawString("1 - View my balance", 70, 60);
				g.drawString("2 - Withdraw cash", 70, 75);
				g.drawString("3 - Deposit funds", 70, 90);
				g.drawString("4 - Exit", 70, 105);
				g.drawString("Enter a choice: ", 70, 125);
			break;
		case BALANCEINQUIRY:
				String balance = "";
				NumberFormat nf = NumberFormat.getCurrencyInstance();
							
				g.drawString("BALANCE INQUIRY", 50, 40);
				g.drawString("Your available balance is: ", 50, 70);
				balance = nf.format(Statics.availableBalance);				
				g.drawString(balance, 185, 70);

				g.drawString("Your total balance is: ", 50, 80);
				balance = nf.format(Statics.totalBalance);			
				g.drawString(balance, 185, 80);
				
				g.drawString("[Ent] to exit system", 50, 100);
				g.drawString("[Del] for new transaction", 50, 110);
			break;
		case WITHDRAWAL:
				g.drawString("ENTER AMOUNT:", 50, 60);
				
				g.drawString("[Ent] to confirm", 50, 100);
				g.drawString("[Del] for new transaction", 50, 110);
		break;
		case ERROR:
				g.drawString("Sorry, wrong input. Please try Again.", 50, 60);
			break;
		case INSUFFICIENT:
				g.drawString("Sorry, insufficient funds. Please try Again.", 50, 60);
		break;
		case DEPOSIT:
				g.drawString("ENTER AMOUNT:", 50, 60);
				
				g.drawString("[Ent] to confirm", 50, 100);
				g.drawString("[Del] for new transaction", 50, 110);
		break;
		case WITHDRAWALSUCCESSFUL:
				g.drawString("Transaction successful. Please take your cash.", 50, 60);
		break;
		case DEPOSITSUCCESSFUL:
				g.drawString("Insert envelope.", 50, 60);
				g.drawString("Validation of deposited cash takes 3 working days.", 50, 70);
	break;
		
		default:
			break;
		}
	
		
	}
	
}
