package com.ebay.local.exception;

import java.io.Serializable;

public class AvailabilityServiceException extends Exception implements
		Serializable {

	private static final long serialVersionUID = 1L;

	public AvailabilityServiceException() {
		super();
	}

	public AvailabilityServiceException(String msg) {
		super(msg);
	}
	
	public AvailabilityServiceException(String msg, Exception e){
		super(msg,e);
	}

}
