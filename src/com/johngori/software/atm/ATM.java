package com.johngori.software.atm;

import javax.swing.JFrame;

public class ATM extends JFrame{
	private static final long serialVersionUID = 1L;
	private final static int PWIDTH = 419;
	private final static int PHEIGHT = 345;
	
	public ATM(){

		ATMGui atmGui = new ATMGui();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setSize(PWIDTH,PHEIGHT);
		setLocationRelativeTo(null);
		setResizable(false);
		add(atmGui);
	}
	
	public static void main(String[] args){
		new ATM();
	}
	
}
