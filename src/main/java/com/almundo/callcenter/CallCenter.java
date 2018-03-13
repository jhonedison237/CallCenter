package com.almundo.callcenter;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Main class that starts the operation
 * 
 * @author jhon.londono
 *
 */
public class CallCenter {
	
	/** The executor to attend calls */
	private ExecutorService executorCalls;
	
	/**
	 * The object responsible to dispatch the incoming 
	 * calls to respective employee and attend the calls
	 */
	private Dispatcher dispatcher;
	
	/** The executor to start the operation of employees */
	private ExecutorService executorEmployees;
	
	/** The received number of calls */
	private int numberOfCalls;
	
	/** The list of employees that are going to attend the calls */
	private List<EmployeeRole> employeeList;
	
	/**
	 * Constructor method to initialize the default configuration. The pool of 10 concurrent calls 
	 * and initialize the dispatcher class with a specific number of employees for every role
	 */
	public CallCenter() {
		numberOfCalls = 10;
		executorCalls = Executors.newFixedThreadPool(10);
		dispatcher = new Dispatcher(3, 2, 1);
		executorEmployees = Executors.newFixedThreadPool(7);
		employeeList = new ArrayList<EmployeeRole>();
		employeeList.add(EmployeeRole.OPERATOR);
		employeeList.add(EmployeeRole.OPERATOR);
		employeeList.add(EmployeeRole.OPERATOR);
		employeeList.add(EmployeeRole.SUPERVISOR);
		employeeList.add(EmployeeRole.SUPERVISOR);
		employeeList.add(EmployeeRole.DIRECTOR);
	}
	
	/**
	 * Constructor to initialize the call center operation with the given
	 * configuration.
	 * 
	 * @param executorCalls
	 *            the executor to attend calls
	 * @param dispatcher
	 *            the object responsible to dispatch the incoming calls to
	 *            respective employee and attend the calls
	 * @param executorEmployees
	 *            the executor to start the operation of employees
	 */
	public CallCenter(ExecutorService executorCalls, Dispatcher dispatcher, ExecutorService executorEmployees) {
		this.executorCalls = executorCalls;
		this.dispatcher = dispatcher;
		this.executorEmployees = executorEmployees;
	}



	/**
	 * Method to create the pool of threads that represents the employees that are going
	 * to attend the calls and dispatch the calls
	 */
	public void startOperation() {
		
		startEmployeeOperation();

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
	 * Method to start the employees work
	 */
	public void startEmployeeOperation() {
		for (EmployeeRole employeeRole : employeeList) {
			executorEmployees.execute(new ConsumerCall(dispatcher, employeeRole));
		}
	}

	/**
	 * Set the number of calls
	 * 
	 * @param numberOfCalls the number of call to set
	 */
	public void setNumberOfCalls(int numberOfCalls) {
		this.numberOfCalls = numberOfCalls;
	}
	
	/**
	 * Set the employee list
	 * 
	 * @param employeeList the list of employees to set
	 */
	public void setEmployeeList(List<EmployeeRole> employeeList) {
		this.employeeList = employeeList;
	}
	
	/**
	 * Mian method to start execution of call center with a given number of initial calls
	 * 
	 * @param args initial args
	 * @throws InterruptedException Exception thrown if something unexpected happens
	 */
	public static void main(String[] args) throws InterruptedException {	
		CallCenter callCenter = new CallCenter();
		callCenter.startOperation();
	}
	
}
