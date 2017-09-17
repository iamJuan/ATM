package com.johngori.software.atm.utils;

import java.awt.Rectangle;

public class UIdetection {
	
	public static Rectangle cashDispenser;
	public static Rectangle envelope;
	public static Rectangle enterKey;
	public static Rectangle deleteKey;
	public static Rectangle numericKeys[];
	
	public UIdetection(){
		
		//create all bounds for button detection ie clicked button
		numericKeys 	= new Rectangle[10];		
		cashDispenser 	= new Rectangle(150,150,250,65);
		envelope 		= new Rectangle(150,245,250,65);
		enterKey		= new Rectangle(90,270,26,25);
		deleteKey		= new Rectangle(60,270,26,25);
		
		for (int x = 0,y = 0, i = 0; i < numericKeys.length; i++) {
			
			if(i % 3 == 0 && i > 0){
				y+=36;
				x =0;
			}
			
			numericKeys[i] = new Rectangle(22+x,162+y,26,25);
			x+=37;
		}
	}
}
