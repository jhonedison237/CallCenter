package com.almundo.callcenter;

import java.util.concurrent.TimeUnit;

/**
 * The task responsible to attend the call
 * 
 * @author jhon.londono
 *
 */
public class ConsumerCall implements Runnable {
	
	/** Class to attend the incoming calls */
	private Dispatcher dispatcher;
	
	/** The employee role that attends the call */
	private EmployeeRole employeeRole;

	/**
	 * Constructor method to assign the dispatcher and employeeRole objects
	 * 
	 * @param dispatcher to attend the call
	 * @param employeeRole the role who is going to take the call
	 */
	public ConsumerCall(Dispatcher dispatcher, EmployeeRole employeeRole) {
		this.dispatcher = dispatcher;
		this.employeeRole = employeeRole;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
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
