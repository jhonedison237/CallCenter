package com.almundo.callcenter;

import java.util.concurrent.TimeUnit;

public class ConsumerCall implements Runnable {
	
	Dispatcher dispatcher;
	
	EmployeeRole employeeRole;

	ConsumerCall(Dispatcher dispatcher, EmployeeRole employeeRole) {
		this.dispatcher = dispatcher;
		this.employeeRole = employeeRole;
	}

	public void run() {
		try {
			while (true) {
				int time = Util.generateTime();
				dispatcher.attendCall(employeeRole);
				TimeUnit.SECONDS.sleep(time);				
			}
		} catch (InterruptedException ex) {
			ex.printStackTrace();
		}
	}

}
