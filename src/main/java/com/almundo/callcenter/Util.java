package com.almundo.callcenter;

import java.util.Random;

/**
 * Util class
 * 
 * @author jhon.londono
 *
 */
public class Util {
	
	/**
	 * This method generates a random value between 5 and 10
	 * 
	 * @return the random generated value
	 */
	public static int generateTime() {
		Random r = new Random();
		int Low = 5;
		int High = 10;
		return r.nextInt(High-Low) + Low;
	}

}
