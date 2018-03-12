package com.almundo.callcenter;

public class Call {

	int idCall;
	
	public Call(int idCall) {
		this.idCall= idCall;
	}

	public int getIdCall() {
		return idCall;
	}

	public void setIdCall(int idCall) {
		this.idCall = idCall;
	}
	
	@Override
	public String toString() {
		return "Call number [" + idCall + "]";
	}
	
}
