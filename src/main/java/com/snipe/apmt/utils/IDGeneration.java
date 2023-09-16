package com.snipe.apmt.utils;

import java.util.Random;

import org.springframework.stereotype.Component;

@Component
public class IDGeneration {
	
	public  String generateRandomId() {
	    // It will generate 6 digit random Number.
	    // from 0 to 999999
	    Random rnd = new Random();
	    int number = rnd.nextInt(999999);

	    // this will convert any number sequence into 6 character.
	    return String.format("%06d", number);
	}

}
