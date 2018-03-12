package com.almundo.callcenter;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Dispatcher {
	
	private LinkedBlockingQueue<Call> queueOperator;
	
	private LinkedBlockingQueue<Call> queueSupervisor;
	
	private LinkedBlockingQueue<Call> queueDirector;
	
	ConcurrentLinkedQueue<Call> queueGeneral;

	public Dispatcher(int totalOperator, int totalSupervisor, int totalDirector) {
		
		queueOperator = new LinkedBlockingQueue<Call>(totalOperator);
		queueSupervisor = new LinkedBlockingQueue<Call>(totalSupervisor);
		queueDirector = new LinkedBlockingQueue<Call>(totalDirector);
		queueGeneral = new ConcurrentLinkedQueue<Call>();
	}
	
	public void dispatchCall(Call call) {
		assignCallToOperator(call);
	}

	private void assignCallToOperator(Call call) {
		try {
			queueOperator.add(call);			
		} catch (IllegalStateException ex) {
			assignCallToSupervisor(call);
		}
	}
	
	public void assignCallToSupervisor(Call call) {
		try {
			queueSupervisor.add(call);			
		} catch (IllegalStateException ex) {
			assignCallToDirector(call);
		}
	}
	
	public void assignCallToDirector(Call call) {
		try {
			queueDirector.add(call);			
		} catch (IllegalStateException ex) {
			assignCallToGeneralQueue(call);
		}
	}
	
	public void assignCallToGeneralQueue(Call call) {
		queueGeneral.add(call);
	}
	
	public void attendCall(EmployeeRole employeeRole) {
		switch (employeeRole) {
			case OPERATOR:
				if (queueOperator.isEmpty())
					takeCallInQueue(employeeRole);
				else
					System.out.println("Employee " + employeeRole + " taking the call: " + queueOperator.poll());
				break;
			case SUPERVISOR:
				if (queueSupervisor.isEmpty())
					takeCallInQueue(employeeRole);
				else
					System.out.println("Employee " + employeeRole + " taking the call: " + queueSupervisor.poll());
				break;
			case DIRECTOR:
				if (queueDirector.isEmpty())
					takeCallInQueue(employeeRole);					
				else
					System.out.println("Employee " + employeeRole + " taking the call: " + queueDirector.poll());					
				break;
		}
			
	}
	
	private void takeCallInQueue(EmployeeRole employeeRole) {
		if (!queueGeneral.isEmpty()) {
			System.out.println("Employee " + employeeRole + " taking the call on waiting list: " + queueGeneral.poll());
		}
	}
	
}
