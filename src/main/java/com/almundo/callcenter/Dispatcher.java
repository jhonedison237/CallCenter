package com.almundo.callcenter;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Class to dispatch the incoming calls to respective employee and attend the calls
 * @author jhon.londono
 *
 */
public class Dispatcher {
	
	/** The queue of calls to be attended by an operator */
	private LinkedBlockingQueue<Call> queueOperator;
	
	/** The queue of calls to be attended by a supervisor */
	private LinkedBlockingQueue<Call> queueSupervisor;
	
	/** The queue of calls to be attended by a director */
	private LinkedBlockingQueue<Call> queueDirector;
	
	/** The queue of calls to be attended when an employee is available */
	ConcurrentLinkedQueue<Call> queueGeneral;

	/**
	 * Method constructor to assign the total of employees for every role available to attend the incoming calls
	 * 
	 * @param totalOperator the number of operators
	 * @param totalSupervisor the number of supervisors
	 * @param totalDirector the number of directors
	 */
	public Dispatcher(int totalOperator, int totalSupervisor, int totalDirector) {	
		queueOperator = new LinkedBlockingQueue<Call>(totalOperator);
		queueSupervisor = new LinkedBlockingQueue<Call>(totalSupervisor);
		queueDirector = new LinkedBlockingQueue<Call>(totalDirector);
		queueGeneral = new ConcurrentLinkedQueue<Call>();
	}
	
	/**
	 * Dispatch the incoming call starting by assigning this one to available operators
	 * 
	 * @param call the incoming call to be attended
	 */
	public void dispatchCall(Call call) {
		assignCallToOperator(call);
	}

	/**
	 * If there is an available operator the call is assign to it, otherwise it is assigned to a supervisor
	 * 
	 * @param call the incoming call to be attended
	 */
	private void assignCallToOperator(Call call) {
		try {
			queueOperator.add(call);			
		} catch (IllegalStateException ex) {
			assignCallToSupervisor(call);
		}
	}
	
	/**
	 * If there is an available supervisor the call is assign to it, otherwise it is assigned to a director
	 * 
	 * @param call the incoming call to be attended
	 */
	public void assignCallToSupervisor(Call call) {
		try {
			queueSupervisor.add(call);			
		} catch (IllegalStateException ex) {
			assignCallToDirector(call);
		}
	}
	
	/**
	 * If there is an available director the call is assign to it, otherwise it is assigned to a waiting queue
	 * to be attended when some employee gets free
	 * 
	 * @param call the incoming call to be attended
	 */
	public void assignCallToDirector(Call call) {
		try {
			queueDirector.add(call);			
		} catch (IllegalStateException ex) {
			assignCallToGeneralQueue(call);
		}
	}
	
	/**
	 * It assign the call on the waiting queue
	 * 
	 * @param call the incoming call to be attended
	 */
	public void assignCallToGeneralQueue(Call call) {
		queueGeneral.add(call);
	}
	
	/**
	 * This method is responsible for attending the calls, if an employee does not have calls to attend for its role
	 * then this one take a call from the waiting queue
	 * @param employeeRole
	 */
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
	
	/**
	 * Method to attend a call from the waiting queue
	 * 
	 * @param employeeRole the employee role taking the call
	 */
	private void takeCallInQueue(EmployeeRole employeeRole) {
		if (!queueGeneral.isEmpty()) {
			System.out.println("Employee " + employeeRole + " taking the call on waiting list: " + queueGeneral.poll());
		}
	}
	
}
