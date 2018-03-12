package com.almundo.callcenter;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class CallCenter {
	private ExecutorService executorCalls;
	private Dispatcher dispatcher;
	private int numberOfCalls;
	
	public CallCenter() {
		executorCalls = Executors.newFixedThreadPool(10);
		dispatcher = new Dispatcher(4, 2, 1);
	}
	
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
	
	public void attendsCall(int callNumber) {
		executorCalls.execute(new ProducerCall(dispatcher, new Call(callNumber)));
	}

	public static void main(String[] args) throws InterruptedException {	
		CallCenter callCenter = new CallCenter();
		callCenter.setNumberOfCalls(60);
		callCenter.startOperation();
	}

	private void setNumberOfCalls(int numberOfCalls) {
		this.numberOfCalls = numberOfCalls;
	}
	
}
