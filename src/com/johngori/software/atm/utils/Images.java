package com.johngori.software.atm.utils;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Images {

	public static BufferedImage atmsim;
	public static BufferedImage atmui;
	public static BufferedImage envelope;
	public static BufferedImage cashdispenser;
	public static BufferedImage enterkey;
	public static BufferedImage deletekey;
	public static BufferedImage numerickeys[];
	
	public Images(){
		
		//instantiate static vars
		numerickeys = new BufferedImage[10];
		
		try {
			atmsim 			= ImageIO.read(getClass().getResource("/images/atmsim.png"));
			atmui 			= ImageIO.read(getClass().getResource("/images/atmui.png"));
			envelope 		= ImageIO.read(getClass().getResource("/images/envelope.png"));
			cashdispenser 	= ImageIO.read(getClass().getResource("/images/cashdispenser.png"));
			enterkey 		= ImageIO.read(getClass().getResource("/images/keyenter.png"));
			deletekey 		= ImageIO.read(getClass().getResource("/images/keydel.png"));
			
			for (int i = 0; i < numerickeys.length-1; i++) {
				numerickeys[i]	=  ImageIO.read(getClass().getResource("/images/key"+(i+1)+".png"));
			}
			
			numerickeys[9] = ImageIO.read(getClass().getResource("/images/key0.png"));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
