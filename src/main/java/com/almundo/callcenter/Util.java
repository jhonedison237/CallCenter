package com.almundo.callcenter;

import java.util.Random;

public class Util {
	
	public static int generateTime() {
		Random r = new Random();
		int Low = 5;
		int High = 10;
		return r.nextInt(High-Low) + Low;
	}

}
