package com.almundo.callcenter;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Main class that starts the operation
 * 
 * @author jhon.londono
 *
 */
public class CallCenter {
	private ExecutorService executorCalls;
	private Dispatcher dispatcher;
	private int numberOfCalls;
	
	/**
	 * Constructor method to initialize the pool of 10 concurrent calls and initialize
	 * the dispatcher class with the number of employees for every role
	 */
	public CallCenter() {
		executorCalls = Executors.newFixedThreadPool(10);
		dispatcher = new Dispatcher(4, 2, 1);
	}
	
	/**
	 * Method to create the pool of threads that represents the employees that are going
	 * to attend the calls and dispatch the calls
	 */
	public void startOperation() {

		ExecutorService executorEmployees = Executors.newFixedThreadPool(7);
		executorEmployees.execute(new ConsumerCall(dispatcher, EmployeeRole.OPERATOR));
		executorEmployees.execute(new ConsumerCall(dispatcher, EmployeeRole.OPERATOR));
		executorEmployees.execute(new ConsumerCall(dispatcher, EmployeeRole.OPERATOR));
		executorEmployees.execute(new ConsumerCall(dispatcher, EmployeeRole.OPERATOR));
		executorEmployees.execute(new ConsumerCall(dispatcher, EmployeeRole.SUPERVISOR));
		executorEmployees.execute(new ConsumerCall(dispatcher, EmployeeRole.SUPERVISOR));
		executorEmployees.execute(new ConsumerCall(dispatcher, EmployeeRole.DIRECTOR));
		
		for (int i=1; i <= numberOfCalls; i++) {
			attendsCall(i);
		}
		
	}
	
	/**
	 * Method that attends the received call
	 * 
	 * @param callNumber the number of the call
	 */
	public void attendsCall(int callNumber) {
		executorCalls.execute(new ProducerCall(dispatcher, new Call(callNumber)));
	}

	/**
	 * Mian method to start execution of call center with a given number of initial calls
	 * 
	 * @param args initial args
	 * @throws InterruptedException Exception thrown if something unexpected happens
	 */
	public static void main(String[] args) throws InterruptedException {	
		CallCenter callCenter = new CallCenter();
		callCenter.setNumberOfCalls(30);
		callCenter.startOperation();
	}

	/**
	 * Set the number of calls
	 * 
	 * @param numberOfCalls the number of call to set
	 */
	private void setNumberOfCalls(int numberOfCalls) {
		this.numberOfCalls = numberOfCalls;
	}
	
}
