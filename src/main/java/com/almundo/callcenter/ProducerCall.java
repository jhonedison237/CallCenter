package com.almundo.callcenter;

/**
 * The task responsible to dispatch the call
 * 
 * @author jhon.londono
 *
 */
public class ProducerCall implements Runnable {

	/** Class to dispatch the incoming calls */
	private Dispatcher dispatcher;
	
	/** The received call */
	private Call call;

	/**
	 * Constructor method to assign the dispatcher and employeeRole objects
	 * 
	 * @param dispatcher to dispatch the call
	 * @param call the received call
	 */
	public ProducerCall(Dispatcher dispatcher, Call call) {
		this.dispatcher = dispatcher;
		this.call = call;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	public void run() {
		dispatcher.dispatchCall(call);
	}
}
