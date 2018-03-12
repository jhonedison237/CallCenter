package com.almundo.callcenter;

public class ProducerCall implements Runnable {

	Dispatcher dispatcher;
	Call call;

	ProducerCall(Dispatcher dispatcher, Call call) {
		this.dispatcher = dispatcher;
		this.call = call;
	}

	public void run() {
		dispatcher.dispatchCall(call);
	}
}
