package com.almundo.callcenter;

/**
 * Class to represent a call
 * 
 * @author jhon.lonodno
 *
 */
public class Call {

	/** The id of the call */
	private int idCall;
	
	/**
	 * The constructor class to give id call
	 * 
	 * @param idCall the number of the call
	 */
	public Call(int idCall) {
		this.idCall= idCall;
	}

	/**
	 * Gets the call id
	 * @return id call
	 */
	public int getIdCall() {
		return idCall;
	}

	/**
	 * Sets the call id
	 * 
	 * @param idCall the id call to set
	 */
	public void setIdCall(int idCall) {
		this.idCall = idCall;
	}
	
	@Override
	public String toString() {
		return "Call number [" + idCall + "]";
	}
	
}
